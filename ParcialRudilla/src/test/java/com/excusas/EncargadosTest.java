package com.excusas;

import com.excusas.empleados.Empleado;
import com.excusas.excusas.Excusa;
import com.excusas.excusas.triviales.ExcusaTrivial;
import com.excusas.excusas.moderadas.ExcusaModerada;
import com.excusas.excusas.moderadas.SeCortoLuz;
import com.excusas.excusas.moderadas.TuveCuidarFamiliar;
import com.excusas.excusas.complejas.ExcusaCompleja;
import com.excusas.excusas.inverosimiles.ExcusaInverosimil;
import com.excusas.empleados.encargados.*;
import com.excusas.empleados.encargados.estrategias.*;
import com.excusas.empleados.encargados.IManejadoraExcusas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests de Encargados")
class EncargadosTest {

    private Empleado empleado;
    private Recepcionista recepcionista;
    private SupervisorArea supervisor;
    private GerenteRRHH gerente;
    private CEO ceo;

    @BeforeEach
    void setUp() {
        empleado = new Empleado("Carlos Ruiz", "carlos@empresa.com", 2003);
        recepcionista = new Recepcionista("Ana García", "recepcion@excusas-sa.com", 1001);
        supervisor = new SupervisorArea("Carlos López", "supervisor@excusas-sa.com", 1002);
        gerente = new GerenteRRHH("María Rodríguez", "gerente@excusas-sa.com", 1003);
        ceo = new CEO("Roberto Silva", "ceo@excusas-sa.com", 1004);
    }

    @Test
    @DisplayName("Recepcionista puede manejar excusas triviales")
    void testRecepcionistaPuedeManejarTriviales() {
        assertTrue(recepcionista.puedeManejarTrivial());
        assertFalse(recepcionista.puedeManejarModerado());
        assertFalse(recepcionista.puedeManejarComplejo());
        assertFalse(recepcionista.puedeManejarInverosimil());
    }

    @Test
    @DisplayName("Supervisor puede manejar excusas moderadas")
    void testSupervisorPuedeManejarModeradas() {
        assertFalse(supervisor.puedeManejarTrivial());
        assertTrue(supervisor.puedeManejarModerado());
        assertFalse(supervisor.puedeManejarComplejo());
        assertFalse(supervisor.puedeManejarInverosimil());
    }

    @Test
    @DisplayName("Gerente puede manejar excusas complejas")
    void testGerentePuedeManejarComplejas() {
        assertFalse(gerente.puedeManejarTrivial());
        assertFalse(gerente.puedeManejarModerado());
        assertTrue(gerente.puedeManejarComplejo());
        assertFalse(gerente.puedeManejarInverosimil());
    }

    @Test
    @DisplayName("CEO puede manejar excusas inverosímiles")
    void testCEOPuedeManejarInverosimiles() {
        assertFalse(ceo.puedeManejarTrivial());
        assertFalse(ceo.puedeManejarModerado());
        assertFalse(ceo.puedeManejarComplejo());
        assertTrue(ceo.puedeManejarInverosimil());
    }

    @Test
    @DisplayName("Recepcionista procesa excusa trivial correctamente")
    void testRecepcionistaProcesaExcusaTrivial() {
        ExcusaTrivial motivo = new ExcusaTrivial();
        Excusa excusa = empleado.crearExcusa(motivo, "Llegué tarde por el tráfico");

        assertDoesNotThrow(() -> {
            recepcionista.procesarExcusa(excusa);
        });
    }

    @Test
    @DisplayName("Supervisor procesa excusa moderada genérica")
    void testSupervisorProcesaExcusaModeradaGenerica() {
        ExcusaModerada motivo = new ExcusaModerada();
        Excusa excusa = empleado.crearExcusa(motivo, "Problemas con el transporte");

        assertDoesNotThrow(() -> {
            supervisor.procesarExcusa(excusa);
        });
    }

    @Test
    @DisplayName("Supervisor procesa SeCortoLuz con lógica específica")
    void testSupervisorProcesaSeCortoLuz() {
        SeCortoLuz motivo = new SeCortoLuz();
        Excusa excusa = empleado.crearExcusa(motivo, "Se cortó la luz en mi barrio");

        assertDoesNotThrow(() -> {
            supervisor.procesarExcusa(excusa);
        });
    }

    @Test
    @DisplayName("Supervisor procesa TuveCuidarFamiliar con lógica específica")
    void testSupervisorProcesaTuveCuidarFamiliar() {
        TuveCuidarFamiliar motivo = new TuveCuidarFamiliar();
        Excusa excusa = empleado.crearExcusa(motivo, "Tuve que cuidar a mi familiar");

        assertDoesNotThrow(() -> {
            supervisor.procesarExcusa(excusa);
        });
    }

    @Test
    @DisplayName("Estrategia Vago funciona correctamente")
    void testEstrategiaVago() {
        recepcionista.setEstrategia(new EstrategiaVago());
        ExcusaTrivial motivo = new ExcusaTrivial();
        Excusa excusa = empleado.crearExcusa(motivo, "Test excusa");

        assertDoesNotThrow(() -> {
            recepcionista.manejarExcusa(excusa);
        });
    }

    @Test
    @DisplayName("Estrategia Productivo funciona correctamente")
    void testEstrategiaProductivo() {
        recepcionista.setEstrategia(new EstrategiaProductivo());
        ExcusaTrivial motivo = new ExcusaTrivial();
        Excusa excusa = empleado.crearExcusa(motivo, "Test excusa");

        assertDoesNotThrow(() -> {
            recepcionista.manejarExcusa(excusa);
        });
    }

    @Test
    @DisplayName("EncargadoRechazador implementa interfaces correctamente")
    void testEncargadoRechazadorImplementaInterfaces() {
        EncargadoRechazador rechazador = new EncargadoRechazador();

        assertTrue(rechazador instanceof IManejadoraExcusas);
        assertTrue(rechazador instanceof IEncargado);

        assertNotNull(rechazador.getNombre());
        assertNotNull(rechazador.getEmail());
        assertTrue(rechazador.getLegajo() > 0);
    }
}
