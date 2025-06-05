package com.excusas.integration;

import com.excusas.encargado.CadenaEncargadosFactory;
import com.excusas.encargado.ManejadorExcusas;
import com.excusas.empleado.Empleado;
import com.excusas.excusa.Excusa;
import com.excusas.excusa.*;
import com.excusas.prontuario.AdministradorProntuarios;
import com.excusas.testutils.EmailSenderTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SistemaExcusasIntegrationTest {

    private ManejadorExcusas cadenaEncargados;
    private AdministradorProntuarios administradorProntuarios;
    private EmailSenderTest emailSender;

    @BeforeEach
    void setUp() {
        emailSender = new EmailSenderTest();
        cadenaEncargados = CadenaEncargadosFactory.crearCadenaCompleta(emailSender);
        administradorProntuarios = AdministradorProntuarios.getInstance();
        administradorProntuarios.limpiarProntuarios();
        emailSender.limpiar();
    }

    @Test
    void testFlujoCompletoExcusaInverosimil() {
        Empleado empleado = new Empleado("Juan Pérez", "juan@empresa.com", 2001);
        Excusa excusa = new Excusa(empleado, new Inverosimil(), "Una paloma robó mi bicicleta");

        int prontuariosAntes = administradorProntuarios.getCantidadProntuarios();

        String resultado = cadenaEncargados.manejarExcusa(excusa);

        assertEquals("Excusa extremadamente inverosímil aceptada por CEO - Prontuario creado", resultado);
        assertEquals(prontuariosAntes + 1, administradorProntuarios.getCantidadProntuarios());

        var prontuarios = administradorProntuarios.getProntuarios();
        var ultimoProntuario = prontuarios.get(prontuarios.size() - 1);
        assertEquals(empleado, ultimoProntuario.getEmpleado());
        assertEquals(excusa, ultimoProntuario.getExcusa());
        assertEquals(2001, ultimoProntuario.getNumeroLegajo());

        assertTrue(emailSender.seEnvioEmailConAsunto("Excusa Aprobada"));
    }

    @Test
    void testFlujoCompletoVariasExcusas() {
        Empleado empleado1 = new Empleado("Ana García", "ana@empresa.com", 3001);
        Empleado empleado2 = new Empleado("Carlos López", "carlos@empresa.com", 3002);
        Empleado empleado3 = new Empleado("María Rodríguez", "maria@empresa.com", 3003);

        Excusa excusa1 = new Excusa(empleado1, new Trivial(), "Me quedé dormido");
        Excusa excusa2 = new Excusa(empleado2, new CorteLuz(), "Se cortó la luz");
        Excusa excusa3 = new Excusa(empleado3, new Inverosimil(), "Fui abducido por aliens");

        String resultado1 = cadenaEncargados.manejarExcusa(excusa1);
        String resultado2 = cadenaEncargados.manejarExcusa(excusa2);
        String resultado3 = cadenaEncargados.manejarExcusa(excusa3);

        assertEquals("Excusa trivial aceptada por Recepcionista", resultado1);
        assertEquals("Excusa moderada aceptada por Supervisor de Área", resultado2);
        assertEquals("Excusa extremadamente inverosímil aceptada por CEO - Prontuario creado", resultado3);

        assertEquals(1, administradorProntuarios.getCantidadProntuarios());

        assertTrue(emailSender.seEnvioEmailA("ana@empresa.com"));
        assertTrue(emailSender.seEnvioEmailA("EDESUR@mailfake.com.ar"));
        assertTrue(emailSender.seEnvioEmailA("maria@empresa.com"));
    }

    @Test
    void testSistemaCompleto() {
        assertEquals(0, administradorProntuarios.getCantidadProntuarios());


        Empleado empleado = new Empleado("Test User", "test@empresa.com", 9999);

        Excusa[] excusas = {
                new Excusa(empleado, new Trivial(), "Dormí de más"),
                new Excusa(empleado, new CorteLuz(), "Sin luz"),
                new Excusa(empleado, new CuidadoFamiliar(), "Hijo enfermo"),
                new Excusa(empleado, new Complejo(), "Gato perdido"),
                new Excusa(empleado, new Inverosimil(), "Abducción alienígena")
        };

        String[] resultadosEsperados = {
                "Excusa trivial aceptada por Recepcionista",
                "Excusa moderada aceptada por Supervisor de Área",
                "Excusa moderada aceptada por Supervisor de Área",
                "Excusa compleja aceptada por Gerente de RRHH",
                "Excusa extremadamente inverosímil aceptada por CEO - Prontuario creado"
        };

        for (int i = 0; i < excusas.length; i++) {
            String resultado = cadenaEncargados.manejarExcusa(excusas[i]);
            assertEquals(resultadosEsperados[i], resultado,
                    "Fallo en excusa " + i + ": " + excusas[i].getMotivo());
        }

        assertEquals(1, administradorProntuarios.getCantidadProntuarios());

        assertTrue(emailSender.getCantidadEmailsEnviados() > 0);
    }
}
