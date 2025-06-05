package com.excusas.encargado;

import com.excusas.empleado.Empleado;
import com.excusas.excusa.Excusa;
import com.excusas.excusa.Trivial;
import com.excusas.testutils.EmailSenderTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EstrategiaProductivoTest {

    private EmailSenderTest emailSender;
    private EstrategiaProductivo estrategia;
    private Excusa excusa;

    @BeforeEach
    void setUp() {
        emailSender = new EmailSenderTest();
        estrategia = new EstrategiaProductivo(emailSender);
        Empleado empleado = new Empleado("Juan Pérez", "juan@empresa.com", 2001);
        excusa = new Excusa(empleado, new Trivial(), "Me quedé dormido");
    }

    @Test
    void testEjecutarEstrategiaProductivo() {
        boolean[] procesarEjecutado = {false};
        Runnable procesarExcusa = () -> procesarEjecutado[0] = true;
        Runnable pasarAlSiguiente = () -> fail("No debería pasar al siguiente");

        String resultado = estrategia.ejecutar(excusa, procesarExcusa, pasarAlSiguiente);

        assertEquals("Excusa procesada productivamente (CTO notificado)", resultado);
        assertTrue(procesarEjecutado[0]);

        assertEquals(1, emailSender.getCantidadEmailsEnviados());
        EmailSenderTest.EmailEnviado email = emailSender.getUltimoEmail();
        assertEquals("cto@excusas-sa.com", email.getDestino());
        assertEquals("sistema@excusas-sa.com", email.getOrigen());
        assertEquals("Excusa en modo productivo", email.getAsunto());
        assertTrue(email.getCuerpo().contains("Me quedé dormido"));
    }
}
