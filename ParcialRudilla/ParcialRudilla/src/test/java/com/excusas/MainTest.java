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
        Excusa excusa = empleado.generarExcusa("me quedé dormido");


        assertNotNull(excusa);
        assertTrue(excusa.getMotivo() instanceof ExcusaTrivial);
        assertEquals(empleado, excusa.getEmpleado());
    }

    @Test
    void testEmpleadoGeneraExcusaModeradaCorte() {
        Excusa excusa = empleado.generarExcusa("se cortó la luz");

        assertNotNull(excusa);
        assertTrue(excusa.getMotivo() instanceof SeCortoLuz);
        assertTrue(excusa.getMotivo() instanceof ExcusaModerada);
    }

    @Test
    void testEmpleadoGeneraExcusaModeradaFamiliar() {
        Excusa excusa = empleado.generarExcusa("tuve que cuidar a mi familiar");

        assertNotNull(excusa);
        assertTrue(excusa.getMotivo() instanceof TuveCuidarFamiliar);
        assertTrue(excusa.getMotivo() instanceof ExcusaModerada);
    }

    @Test
    void testEmpleadoGeneraExcusaCompleja() {
        Excusa excusa = empleado.generarExcusa("fui abducido por aliens");

        assertNotNull(excusa);
        assertTrue(excusa.getMotivo() instanceof ExcusaCompleja);
    }

    @Test
    void testEmpleadoGeneraExcusaInverosimil() {
        Excusa excusa = empleado.generarExcusa("algo increíble");

        assertNotNull(excusa);
        assertTrue(excusa.getMotivo() instanceof ExcusaInverosimil);
    }

    @Test
    void testSistemaProcesaExcusa() {
        Excusa excusa = empleado.generarExcusa("me quedé dormido");

        assertDoesNotThrow(() -> {
            LineaEncargados.getInstance().procesarExcusa(excusa);
        });
    }

    @Test
    void testDoubleDispatchSinInstanceof() {
        ExcusaTrivial trivial = new ExcusaTrivial("dormido");
        ExcusaModerada moderada = new ExcusaModerada("problema moderado");
        ExcusaCompleja compleja = new ExcusaCompleja("aliens");
        ExcusaInverosimil inverosimil = new ExcusaInverosimil("increíble");

        Recepcionista recepcionista = new Recepcionista("Ana", "ana@test.com", 1001);
        SupervisorArea supervisor = new SupervisorArea("Carlos", "carlos@test.com", 1002);
        GerenteRRHH gerente = new GerenteRRHH("María", "maria@test.com", 1003);
        CEO ceo = new CEO("Roberto", "ceo@test.com", 1004);

        assertTrue(trivial.puedeSerManejadaPor(recepcionista));
        assertFalse(trivial.puedeSerManejadaPor(supervisor));

        assertFalse(moderada.puedeSerManejadaPor(recepcionista));
        assertTrue(moderada.puedeSerManejadaPor(supervisor));

        assertFalse(compleja.puedeSerManejadaPor(supervisor));
        assertTrue(compleja.puedeSerManejadaPor(gerente));

        assertFalse(inverosimil.puedeSerManejadaPor(gerente));
        assertTrue(inverosimil.puedeSerManejadaPor(ceo));
    }

    @Test
    void testAdministradorProntuarios() {
        AdministradorProntuarios admin = AdministradorProntuarios.getInstance();
        admin.agregarCEO("ceo1@excusas-sa.com");

        Excusa excusa = empleado.generarExcusa("algo increíble");

        LineaEncargados.getInstance().procesarExcusa(excusa);

        assertEquals(1, admin.getProntuarios().size());
    }

    @Test
    void testTemplateMethodEnEncargado() {
        Recepcionista recepcionista = new Recepcionista("Ana", "ana@test.com", 1001);
        Excusa excusa = empleado.generarExcusa("me quedé dormido");

        assertDoesNotThrow(() -> {
            recepcionista.manejarExcusa(excusa);
        });
    }

    @Test
    void testLineaEncapsulaCorrectamente() {
        LineaEncargados linea = LineaEncargados.getInstance();

        assertTrue(linea.estaConfigurada());

        Excusa excusa = empleado.generarExcusa("me quedé dormido");

        assertDoesNotThrow(() -> {
            linea.procesarExcusa(excusa);
        });
    }

    @Test
    void testEstrategiaVago() {
        Recepcionista recepcionista = new Recepcionista("Ana", "ana@test.com", 1001);
        SupervisorArea supervisor = new SupervisorArea("Carlos", "carlos@test.com", 1002);
        recepcionista.setSiguiente(supervisor);

        recepcionista.setEstrategia(new EstrategiaVago());

        Excusa excusa = empleado.generarExcusa("me quedé dormido");

        assertDoesNotThrow(() -> {
            recepcionista.manejarExcusa(excusa);
        });
    }

    @Test
    void testEstrategiaProductivo() {
        Recepcionista recepcionista = new Recepcionista("Ana", "ana@test.com", 1001);

        recepcionista.setEstrategia(new EstrategiaProductivo());

        Excusa excusa = empleado.generarExcusa("me quedé dormido");

        assertDoesNotThrow(() -> {
            recepcionista.manejarExcusa(excusa);
        });
    }

    @Test
    void testMainEstaVacio() {
        Main main = new Main();

        assertNotNull(main);
    }

    @Test
    void testConfiguracionAutomatica() {
        LineaEncargados linea = LineaEncargados.getInstance();

        assertTrue(linea.estaConfigurada());
    }


    @Test
    @DisplayName("Demostración completa del sistema")
    void testDemostracionCompleta() {
        Empleado empleado1 = new Empleado("Juan Pérez", "juan@empresa.com", 2001);
        Empleado empleado2 = new Empleado("María González", "maria@empresa.com", 2002);
        Empleado empleado3 = new Empleado("Carlos Ruiz", "carlos@empresa.com", 2003);
        Empleado empleado4 = new Empleado("Ana López", "ana@empresa.com", 2004);

        System.out.println("\n=== DEMOSTRACIÓN COMPLETA DEL SISTEMA ===\n");

        Excusa excusa1 = empleado1.generarExcusa("me quedé dormido");
        LineaEncargados.getInstance().procesarExcusa(excusa1);

        Excusa excusa2 = empleado2.generarExcusa("se cortó la luz en mi barrio");
        LineaEncargados.getInstance().procesarExcusa(excusa2);

        Excusa excusa3 = empleado3.generarExcusa("tuve que cuidar a mi familiar");
        LineaEncargados.getInstance().procesarExcusa(excusa3);

        Excusa excusa4 = empleado4.generarExcusa("fui abducido por aliens");
        LineaEncargados.getInstance().procesarExcusa(excusa4);

        Excusa excusa5 = empleado1.generarExcusa("algo completamente increíble");
        LineaEncargados.getInstance().procesarExcusa(excusa5);

        System.out.println("\n=== FIN DE LA DEMOSTRACIÓN ===");

        assertTrue(true);
    }
}
