package com.excusas;

import com.excusas.empleados.Empleado;
import com.excusas.excusas.Excusa;
import com.excusas.excusas.triviales.ExcusaTrivial;
import com.excusas.excusas.moderadas.ExcusaModerada;
import com.excusas.excusas.moderadas.SeCortoLuz;
import com.excusas.excusas.moderadas.TuveCuidarFamiliar;
import com.excusas.excusas.complejas.ExcusaCompleja;
import com.excusas.excusas.inverosimiles.ExcusaInverosimil;
import com.excusas.empleados.encargados.LineaEncargados;
import com.excusas.prontuario.AdministradorProntuarios;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests de Integración del Sistema")
class SistemaTest {

    private Empleado empleado1;
    private Empleado empleado2;
    private Empleado empleado3;

    @BeforeEach
    void setUp() {
        empleado1 = new Empleado("Juan Pérez", "juan@empresa.com", 2001);
        empleado2 = new Empleado("María González", "maria@empresa.com", 2002);
        empleado3 = new Empleado("Carlos Ruiz", "carlos@empresa.com", 2003);

        AdministradorProntuarios.resetearInstancia();
        LineaEncargados.resetearInstancia();
    }

    @Test
    @DisplayName("Sistema procesa excusa trivial correctamente")
    void testSistemaProcesaExcusaTrivial() {
        ExcusaTrivial motivo = new ExcusaTrivial();
        Excusa excusa = empleado1.crearExcusa(motivo, "Me quedé dormido");

        assertDoesNotThrow(() -> {
            LineaEncargados.getInstance().manejarExcusa(excusa);
        });
    }

    @Test
    @DisplayName("Sistema procesa excusa moderada correctamente")
    void testSistemaProcesaExcusaModerada() {
        ExcusaModerada motivo = new ExcusaModerada();
        Excusa excusa = empleado2.crearExcusa(motivo, "Problemas con el transporte");

        assertDoesNotThrow(() -> {
            LineaEncargados.getInstance().manejarExcusa(excusa);
        });
    }

    @Test
    @DisplayName("Sistema procesa excusa compleja correctamente")
    void testSistemaProcesaExcusaCompleja() {
        ExcusaCompleja motivo = new ExcusaCompleja();
        Excusa excusa = empleado3.crearExcusa(motivo, "Fui abducido por aliens");

        assertDoesNotThrow(() -> {
            LineaEncargados.getInstance().manejarExcusa(excusa);
        });
    }

    @Test
    @DisplayName("Sistema procesa excusa inverosímil correctamente")
    void testSistemaProcesaExcusaInverosimil() {
        ExcusaInverosimil motivo = new ExcusaInverosimil();
        Excusa excusa = empleado1.crearExcusa(motivo, "Algo completamente increíble");

        assertDoesNotThrow(() -> {
            LineaEncargados.getInstance().manejarExcusa(excusa);
        });
    }

    @Test
    @DisplayName("Sistema procesa SeCortoLuz correctamente")
    void testSistemaProcesaSeCortoLuz() {
        SeCortoLuz motivo = new SeCortoLuz();
        Excusa excusa = empleado2.crearExcusa(motivo, "Se cortó la luz en mi barrio");

        assertDoesNotThrow(() -> {
            LineaEncargados.getInstance().manejarExcusa(excusa);
        });
    }

    @Test
    @DisplayName("Sistema procesa TuveCuidarFamiliar correctamente")
    void testSistemaProcesaTuveCuidarFamiliar() {
        TuveCuidarFamiliar motivo = new TuveCuidarFamiliar();
        Excusa excusa = empleado3.crearExcusa(motivo, "Tuve que cuidar a mi familiar");

        assertDoesNotThrow(() -> {
            LineaEncargados.getInstance().manejarExcusa(excusa);
        });
    }



    @Test
    @DisplayName("Sistema maneja múltiples excusas correctamente")
    void testSistemaManejaMultiplesExcusas() {
        ExcusaTrivial trivial = new ExcusaTrivial();
        ExcusaModerada moderada = new ExcusaModerada();
        ExcusaCompleja compleja = new ExcusaCompleja();
        ExcusaInverosimil inverosimil = new ExcusaInverosimil();

        Excusa excusa1 = empleado1.crearExcusa(trivial, "Excusa trivial");
        Excusa excusa2 = empleado2.crearExcusa(moderada, "Excusa moderada");
        Excusa excusa3 = empleado3.crearExcusa(compleja, "Excusa compleja");
        Excusa excusa4 = empleado1.crearExcusa(inverosimil, "Excusa inverosímil");

        assertDoesNotThrow(() -> {
            LineaEncargados linea = LineaEncargados.getInstance();
            linea.manejarExcusa(excusa1);
            linea.manejarExcusa(excusa2);
            linea.manejarExcusa(excusa3);
            linea.manejarExcusa(excusa4);
        });
    }
}
