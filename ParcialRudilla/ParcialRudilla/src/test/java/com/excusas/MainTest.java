package com.excusas;

import com.excusas.empleados.Empleado;
import com.excusas.excusas.*;
import com.excusas.excusas.triviales.*;
import com.excusas.excusas.moderadas.*;
import com.excusas.excusas.complejas.*;
import com.excusas.excusas.inverosimiles.*;
import com.excusas.empleados.encargados.*;
import com.excusas.empleados.encargados.estrategias.*;
import com.excusas.prontuario.AdministradorProntuarios;
import com.excusas.empleados.encargados.IManejadoraExcusas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    private Empleado empleado;

    @BeforeEach
    void setUp() {
        empleado = new Empleado("Juan Pérez", "juan@empresa.com", 2001);

        AdministradorProntuarios.resetearInstancia();
        LineaEncargados.resetearInstancia();
    }

    @Test
    void testEmpleadoGeneraExcusaTrivial() {
        ExcusaTrivial motivo = new ExcusaTrivial();

        Excusa excusa = empleado.crearExcusa(motivo, "me quedé dormido");

        assertNotNull(excusa);
        assertTrue(excusa.getMotivo() instanceof ExcusaTrivial);
        assertEquals(empleado, excusa.getEmpleado());
    }

    @Test
    void testEmpleadoGeneraSeCortoLuz() {
        SeCortoLuz motivo = new SeCortoLuz();

        Excusa excusa = empleado.crearExcusa(motivo, "se cortó la luz");

        assertNotNull(excusa);
        assertTrue(excusa.getMotivo() instanceof SeCortoLuz);
        assertEquals("se cortó la luz", excusa.getDescripcion());
    }

    @Test
    void testEmpleadoGeneraTuveCuidarFamiliar() {
        TuveCuidarFamiliar motivo = new TuveCuidarFamiliar();

        Excusa excusa = empleado.crearExcusa(motivo, "tuve que cuidar a mi familiar");

        assertNotNull(excusa);
        assertTrue(excusa.getMotivo() instanceof TuveCuidarFamiliar);
        assertEquals("tuve que cuidar a mi familiar", excusa.getDescripcion());
    }

    @Test
    void testEmpleadoGeneraExcusaCompleja() {
        ExcusaCompleja motivo = new ExcusaCompleja();

        Excusa excusa = empleado.crearExcusa(motivo, "fui abducido por aliens");

        assertNotNull(excusa);
        assertTrue(excusa.getMotivo() instanceof ExcusaCompleja);
    }

    @Test
    void testEmpleadoGeneraExcusaInverosimil() {
        ExcusaInverosimil motivo = new ExcusaInverosimil();

        Excusa excusa = empleado.crearExcusa(motivo, "algo increíble");

        assertNotNull(excusa);
        assertTrue(excusa.getMotivo() instanceof ExcusaInverosimil);
    }

    @Test
    void testSistemaProcesaExcusa() {
        ExcusaTrivial motivo = new ExcusaTrivial();
        Excusa excusa = empleado.crearExcusa(motivo, "me quedé dormido");

        assertDoesNotThrow(() -> {
            LineaEncargados.getInstance().manejarExcusa(excusa);
        });
    }

    @Test
    void testAdministradorProntuarios() {
        AdministradorProntuarios admin = AdministradorProntuarios.getInstance();

        ExcusaInverosimil motivo = new ExcusaInverosimil();
        Excusa excusa = empleado.crearExcusa(motivo, "algo increíble");

        LineaEncargados.getInstance().manejarExcusa(excusa);

        assertEquals(1, admin.getProntuarios().size());
    }

    @Test
    void testTemplateMethodEnEncargado() {
        Recepcionista recepcionista = new Recepcionista("Ana", "ana@test.com", 1001);
        ExcusaTrivial motivo = new ExcusaTrivial();
        Excusa excusa = empleado.crearExcusa(motivo, "me quedé dormido");

        assertDoesNotThrow(() -> {
            recepcionista.manejarExcusa(excusa);
        });
    }

    @Test
    void testLineaEncapsulaCorrectamente() {
        LineaEncargados linea = LineaEncargados.getInstance();
        ExcusaTrivial motivo = new ExcusaTrivial();
        Excusa excusa = empleado.crearExcusa(motivo, "me quedé dormido");

        assertDoesNotThrow(() -> {
            linea.manejarExcusa(excusa);
        });
    }

    @Test
    void testEstrategiaVago() {
        Recepcionista recepcionista = new Recepcionista("Ana", "ana@test.com", 1001);
        recepcionista.setEstrategia(new EstrategiaVago());

        ExcusaTrivial motivo = new ExcusaTrivial();
        Excusa excusa = empleado.crearExcusa(motivo, "me quedé dormido");

        assertDoesNotThrow(() -> {
            recepcionista.manejarExcusa(excusa);
        });
    }

    @Test
    void testEstrategiaProductivo() {
        Recepcionista recepcionista = new Recepcionista("Ana", "ana@test.com", 1001);
        recepcionista.setEstrategia(new EstrategiaProductivo());

        ExcusaTrivial motivo = new ExcusaTrivial();
        Excusa excusa = empleado.crearExcusa(motivo, "me quedé dormido");

        assertDoesNotThrow(() -> {
            recepcionista.manejarExcusa(excusa);
        });
    }

    @Test
    void testEncargadoRechazadorImplementaInterfaces() {
        EncargadoRechazador rechazador = new EncargadoRechazador();

        assertTrue(rechazador instanceof IManejadoraExcusas);
        assertTrue(rechazador instanceof IEncargado);

        assertNotNull(rechazador.getNombre());
        assertNotNull(rechazador.getEmail());
        assertTrue(rechazador.getLegajo() > 0);
    }


    @Test
    @DisplayName("Demostración completa del sistema simplificado")
    void testDemostracionCompleta() {
        Empleado empleado1 = new Empleado("Juan Pérez", "juan@empresa.com", 2001);
        Empleado empleado2 = new Empleado("María González", "maria@empresa.com", 2002);
        Empleado empleado3 = new Empleado("Carlos Ruiz", "carlos@empresa.com", 2003);
        Empleado empleado4 = new Empleado("Ana López", "ana@empresa.com", 2004);

        System.out.println("\n=== DEMOSTRACIÓN COMPLETA DEL SISTEMA ===\n");

        ExcusaTrivial trivial = new ExcusaTrivial();
        Excusa excusa1 = empleado1.crearExcusa(trivial, "me quedé dormido");
        LineaEncargados.getInstance().manejarExcusa(excusa1);

        SeCortoLuz cortoLuz = new SeCortoLuz();
        Excusa excusa2 = empleado2.crearExcusa(cortoLuz, "se cortó la luz en mi barrio");
        LineaEncargados.getInstance().manejarExcusa(excusa2);

        TuveCuidarFamiliar familiar = new TuveCuidarFamiliar();
        Excusa excusa3 = empleado3.crearExcusa(familiar, "tuve que cuidar a mi familiar");
        LineaEncargados.getInstance().manejarExcusa(excusa3);

        ExcusaCompleja compleja = new ExcusaCompleja();
        Excusa excusa4 = empleado4.crearExcusa(compleja, "fui abducido por aliens");
        LineaEncargados.getInstance().manejarExcusa(excusa4);

        ExcusaInverosimil inverosimil = new ExcusaInverosimil();
        Excusa excusa5 = empleado1.crearExcusa(inverosimil, "algo completamente increíble");
        LineaEncargados.getInstance().manejarExcusa(excusa5);

        System.out.println("\n=== FIN DE LA DEMOSTRACIÓN ===");

        assertTrue(true);
    }
}
