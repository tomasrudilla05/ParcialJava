package com.excusas.encargado;

import com.excusas.empleado.Empleado;
import com.excusas.excusa.Excusa;
import com.excusas.excusa.CorteLuz;
import com.excusas.excusa.CuidadoFamiliar;
import com.excusas.excusa.Trivial;
import com.excusas.testutils.EmailSenderTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SupervisorAreaTest {

    private EmailSenderTest emailSender;
    private SupervisorArea supervisor;
    private Empleado empleado;

    @BeforeEach
    void setUp() {
        emailSender = new EmailSenderTest();
        supervisor = new SupervisorArea("Carlos López", "carlos@excusas-sa.com", 1002, emailSender);
        empleado = new Empleado("Juan Pérez", "juan@empresa.com", 2001);
    }

    @Test
    void testProcesarExcusaCorteLuz() {
        Excusa excusa = new Excusa(empleado, new CorteLuz(), "Se cortó la luz");

        String resultado = supervisor.manejarExcusa(excusa);

        assertEquals("Excusa moderada aceptada por Supervisor de Área", resultado);
        assertEquals(1, emailSender.getCantidadEmailsEnviados());

        EmailSenderTest.EmailEnviado email = emailSender.getUltimoEmail();
        assertEquals("EDESUR@mailfake.com.ar", email.getDestino());
        assertEquals("carlos@excusas-sa.com", email.getOrigen());
        assertEquals("Consulta corte de luz", email.getAsunto());
        assertTrue(email.getCuerpo().contains("Juan Pérez"));
    }

    @Test
    void testProcesarExcusaCuidadoFamiliar() {
        Excusa excusa = new Excusa(empleado, new CuidadoFamiliar(), "Tuve que cuidar a mi hijo");

        String resultado = supervisor.manejarExcusa(excusa);

        assertEquals("Excusa moderada aceptada por Supervisor de Área", resultado);
        assertEquals(1, emailSender.getCantidadEmailsEnviados());

        EmailSenderTest.EmailEnviado email = emailSender.getUltimoEmail();
        assertEquals("juan@empresa.com", email.getDestino());
        assertEquals("carlos@excusas-sa.com", email.getOrigen());
        assertEquals("Consulta familiar", email.getAsunto());
        assertEquals("¿Todo está bien con tu familiar?", email.getCuerpo());
    }

    @Test
    void testNoProcesarExcusaTrivial() {
        Excusa excusa = new Excusa(empleado, new Trivial(), "Me quedé dormido");
        EncargadoPorDefecto porDefecto = new EncargadoPorDefecto("Sistema", "sistema@excusas-sa.com", 9999, emailSender);
        supervisor.setSiguienteEncargado(porDefecto);

        String resultado = supervisor.manejarExcusa(excusa);

        assertEquals("Excusa rechazada: necesitamos pruebas contundentes", resultado);
        assertEquals(0, emailSender.getCantidadEmailsEnviados());
    }
}
