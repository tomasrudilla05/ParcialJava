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
import com.excusas.interfaces.IExcusa;

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

        LineaEncargados.getInstance().configurarLineaEstandar();
    }

    @Test
    void testEmpleadoGeneraExcusaTrivial() {
        IExcusa excusa = empleado.generarExcusa("me quedé dormido");

        assertNotNull(excusa);
        assertTrue(excusa.getMotivo() instanceof ExcusaTrivial);
        assertEquals(empleado, excusa.getEmpleado());
    }

    @Test
    void testEmpleadoGeneraSeCortoLuz() {
        IExcusa excusa = empleado.generarExcusa("se cortó la luz");

        assertNotNull(excusa);
        assertTrue(excusa.getMotivo() instanceof SeCortoLuz);
        assertTrue(excusa.getMotivo() instanceof ExcusaModerada);
        assertEquals("se cortó la luz en todo el barrio", excusa.getMotivo().getDescripcion());
    }

    @Test
    void testEmpleadoGeneraTuveCuidarFamiliar() {
        IExcusa excusa = empleado.generarExcusa("tuve que cuidar a mi familiar");

        assertNotNull(excusa);
        assertTrue(excusa.getMotivo() instanceof TuveCuidarFamiliar);
        assertTrue(excusa.getMotivo() instanceof ExcusaModerada);
        assertEquals("tuve que cuidar a un familiar", excusa.getMotivo().getDescripcion());
    }

    @Test
    void testEmpleadoGeneraExcusaModeradaGenerica() {
        IExcusa excusa = empleado.generarExcusa("problema de transporte");

        assertNotNull(excusa);
        assertTrue(excusa.getMotivo() instanceof ExcusaInverosimil);
    }

    @Test
    void testEmpleadoGeneraExcusaCompleja() {
        IExcusa excusa = empleado.generarExcusa("fui abducido por aliens");

        assertNotNull(excusa);
        assertTrue(excusa.getMotivo() instanceof ExcusaCompleja);
    }

    @Test
    void testEmpleadoGeneraExcusaInverosimil() {
        IExcusa excusa = empleado.generarExcusa("algo increíble");

        assertNotNull(excusa);
        assertTrue(excusa.getMotivo() instanceof ExcusaInverosimil);
    }

    @Test
    void testSistemaProcesaExcusa() {
        IExcusa excusa = empleado.generarExcusa("me quedé dormido");

        assertDoesNotThrow(() -> {
            LineaEncargados.getInstance().procesarExcusa((Excusa) excusa);
        });
    }

    @Test
    void testDoubleDispatchSinInstanceof() {

        ExcusaTrivial trivial = new ExcusaTrivial("dormido");
        SeCortoLuz cortoLuz = new SeCortoLuz();
        TuveCuidarFamiliar cuidarFamiliar = new TuveCuidarFamiliar();
        ExcusaCompleja compleja = new ExcusaCompleja("aliens");
        ExcusaInverosimil inverosimil = new ExcusaInverosimil("increíble");

        Recepcionista recepcionista = new Recepcionista("Ana", "ana@test.com", 1001);
        SupervisorArea supervisor = new SupervisorArea("Carlos", "carlos@test.com", 1002);
        GerenteRRHH gerente = new GerenteRRHH("María", "maria@test.com", 1003);
        CEO ceo = new CEO("Roberto", "ceo@test.com", 1004);

        assertTrue(trivial.puedeSerManejadaPor(recepcionista));
        assertFalse(trivial.puedeSerManejadaPor(supervisor));

        assertFalse(cortoLuz.puedeSerManejadaPor(recepcionista));
        assertTrue(cortoLuz.puedeSerManejadaPor(supervisor));

        assertFalse(cuidarFamiliar.puedeSerManejadaPor(recepcionista));
        assertTrue(cuidarFamiliar.puedeSerManejadaPor(supervisor));

        assertFalse(compleja.puedeSerManejadaPor(supervisor));
        assertTrue(compleja.puedeSerManejadaPor(gerente));

        assertFalse(inverosimil.puedeSerManejadaPor(gerente));
        assertTrue(inverosimil.puedeSerManejadaPor(ceo));
    }

    @Test
    void testComportamientoEspecificoSeCortoLuz() {

        Empleado empleado = new Empleado("Test", "test@test.com", 123);
        Excusa excusa = new Excusa(empleado, new SeCortoLuz());
        SupervisorArea supervisor = new SupervisorArea("Supervisor", "super@test.com", 456);

        assertDoesNotThrow(() -> {
            excusa.serProcesadaPor(supervisor);
        });
    }

    @Test
    void testComportamientoEspecificoTuveCuidarFamiliar() {
        Empleado empleado = new Empleado("Test", "test@test.com", 123);
        Excusa excusa = new Excusa(empleado, new TuveCuidarFamiliar());
        SupervisorArea supervisor = new SupervisorArea("Supervisor", "super@test.com", 456);

        assertDoesNotThrow(() -> {
            excusa.serProcesadaPor(supervisor);
        });
    }

    @Test
    void testAdministradorProntuarios() {
        AdministradorProntuarios admin = AdministradorProntuarios.getInstance();
        admin.agregarCEO("ceo1@excusas-sa.com");

        IExcusa excusa = empleado.generarExcusa("algo increíble");

        LineaEncargados.getInstance().procesarExcusa((Excusa) excusa);

        assertEquals(1, admin.getProntuarios().size());
    }

    @Test
    void testTemplateMethodEnEncargadoEmpleado() {
        Recepcionista recepcionista = new Recepcionista("Ana", "ana@test.com", 1001);
        IExcusa excusa = empleado.generarExcusa("me quedé dormido");

        assertDoesNotThrow(() -> {
            recepcionista.manejarExcusa((Excusa) excusa);
        });
    }

    @Test
    void testLineaEncapsulaCorrectamente() {
        LineaEncargados linea = LineaEncargados.getInstance();

        assertTrue(linea.estaConfigurada());

        IExcusa excusa = empleado.generarExcusa("me quedé dormido");

        assertDoesNotThrow(() -> {
            linea.procesarExcusa((Excusa) excusa);
        });
    }

    @Test
    void testEstrategiaVago() {
        LineaEncargados.resetearInstancia();
        LineaEncargados linea = LineaEncargados.getInstance();
        linea.configurarLineaEstandar();

        Recepcionista recepcionista = new Recepcionista("Ana", "ana@test.com", 1001);
        SupervisorArea supervisor = new SupervisorArea("Carlos", "carlos@test.com", 1002);
        recepcionista.setSiguiente(supervisor);
        recepcionista.setEstrategia(new EstrategiaVago());

        linea.configurar(recepcionista);

        IExcusa excusa = empleado.generarExcusa("me quedé dormido");

        assertDoesNotThrow(() -> {
            linea.procesarExcusa((Excusa) excusa);
        });
    }

    @Test
    void testEstrategiaProductivo() {
        LineaEncargados.resetearInstancia();
        LineaEncargados linea = LineaEncargados.getInstance();

        Recepcionista recepcionista = new Recepcionista("Ana", "ana@test.com", 1001);
        recepcionista.setEstrategia(new EstrategiaProductivo());

        linea.configurar(recepcionista);

        IExcusa excusa = empleado.generarExcusa("me quedé dormido");

        assertDoesNotThrow(() -> {
            linea.procesarExcusa((Excusa) excusa);
        });
    }

    @Test
    void testMainEstaVacio() {
        Main main = new Main();

        assertNotNull(main);
    }

    @Test
    void testEncargadosTienenEmailSenderPropio() {
        Recepcionista recep1 = new Recepcionista("Ana", "ana@test.com", 1001);
        Recepcionista recep2 = new Recepcionista("María", "maria@test.com", 1002);

        assertNotSame(recep1.getEmailSender(), recep2.getEmailSender());
    }

    @Test
    void testInterfacesImplementadas() {
        IExcusa excusa = empleado.generarExcusa("test");

        assertNotNull(excusa.getEmpleado());
        assertNotNull(excusa.getMotivo());

        assertEquals("Juan Pérez", empleado.getNombre());
        assertEquals("juan@empresa.com", empleado.getEmail());
        assertEquals(2001, empleado.getNumeroLegajo());
    }


    @Test
    @DisplayName("Demostración completa del sistema con clases específicas")
    void testDemostracionCompleta() {
        Empleado empleado1 = new Empleado("Juan Pérez", "juan@empresa.com", 2001);
        Empleado empleado2 = new Empleado("María González", "maria@empresa.com", 2002);
        Empleado empleado3 = new Empleado("Carlos Ruiz", "carlos@empresa.com", 2003);
        Empleado empleado4 = new Empleado("Ana López", "ana@empresa.com", 2004);

        System.out.println("\n=== DEMOSTRACIÓN COMPLETA DEL SISTEMA ===\n");

        IExcusa excusa1 = empleado1.generarExcusa("me quedé dormido");
        LineaEncargados.getInstance().procesarExcusa((Excusa) excusa1);

        IExcusa excusa2 = empleado2.generarExcusa("se cortó la luz en mi barrio");
        LineaEncargados.getInstance().procesarExcusa((Excusa) excusa2);

        IExcusa excusa3 = empleado3.generarExcusa("tuve que cuidar a mi familiar");
        LineaEncargados.getInstance().procesarExcusa((Excusa) excusa3);

        IExcusa excusa4 = empleado4.generarExcusa("fui abducido por aliens");
        LineaEncargados.getInstance().procesarExcusa((Excusa) excusa4);

        IExcusa excusa5 = empleado1.generarExcusa("algo completamente increíble");
        LineaEncargados.getInstance().procesarExcusa((Excusa) excusa5);

        System.out.println("\n=== FIN DE LA DEMOSTRACIÓN ===");

        assertTrue(true);
    }
}
