package com.excusas.encargado;

import com.excusas.empleado.Empleado;
import com.excusas.excusa.Excusa;
import com.excusas.excusa.Inverosimil;
import com.excusas.excusa.Trivial;
import com.excusas.prontuario.AdministradorProntuarios;
import com.excusas.testutils.EmailSenderTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CEOTest {

    private EmailSenderTest emailSender;
    private CEO ceo;
    private Empleado empleado;

    @BeforeEach
    void setUp() {
        emailSender = new EmailSenderTest();
        ceo = new CEO("Roberto Silva", "roberto@excusas-sa.com", 1004, emailSender);
        empleado = new Empleado("Juan Pérez", "juan@empresa.com", 2001);

        AdministradorProntuarios.getInstance().limpiarProntuarios();
        emailSender.limpiar();
    }

    @Test
    void testProcesarExcusaInverosimil() {
        Excusa excusa = new Excusa(empleado, new Inverosimil(), "Fui abducido por aliens");
        int prontuariosAntes = AdministradorProntuarios.getInstance().getCantidadProntuarios();

        String resultado = ceo.manejarExcusa(excusa);

        assertEquals("Excusa extremadamente inverosímil aceptada por CEO - Prontuario creado", resultado);
        assertEquals(prontuariosAntes + 1, AdministradorProntuarios.getInstance().getCantidadProntuarios());

        assertTrue(emailSender.seEnvioEmailA("juan@empresa.com"));
        assertTrue(emailSender.seEnvioEmailConAsunto("Excusa Aprobada"));

        EmailSenderTest.EmailEnviado emailAprobacion = emailSender.getEmailsEnviados().stream()
                .filter(email -> email.getAsunto().equals("Excusa Aprobada"))
                .findFirst()
                .orElse(null);

        assertNotNull(emailAprobacion);
        assertEquals("Aprobado por creatividad", emailAprobacion.getCuerpo());
    }

    @Test
    void testNoProcesarExcusaNoInverosimil() {
        Excusa excusa = new Excusa(empleado, new Trivial(), "Me quedé dormido");
        EncargadoPorDefecto porDefecto = new EncargadoPorDefecto("Sistema", "sistema@excusas-sa.com", 9999, emailSender);
        ceo.setSiguienteEncargado(porDefecto);

        String resultado = ceo.manejarExcusa(excusa);

        assertEquals("Excusa rechazada: necesitamos pruebas contundentes", resultado);
        assertFalse(emailSender.seEnvioEmailConAsunto("Excusa Aprobada"));
    }

    @Test
    void testNotificacionEntreCEOs() {
        EmailSenderTest emailSender2 = new EmailSenderTest();
        CEO ceo2 = new CEO("Ana García", "ana@excusas-sa.com", 1005, emailSender2);

        Excusa excusa = new Excusa(empleado, new Inverosimil(), "Una paloma robó mi bicicleta");

        ceo.manejarExcusa(excusa);

        assertTrue(emailSender2.seEnvioEmailConAsunto("Nuevo Prontuario Creado"));
    }
}
