package com.excusas.encargado;

import com.excusas.empleado.Empleado;
import com.excusas.excusa.Excusa;
import com.excusas.excusa.Complejo;
import com.excusas.excusa.Trivial;
import com.excusas.testutils.EmailSenderTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecepcionistaTest {

    private EmailSenderTest emailSender;
    private Recepcionista recepcionista;
    private Empleado empleado;

    @BeforeEach
    void setUp() {
        emailSender = new EmailSenderTest();
        recepcionista = new Recepcionista("Ana García", "ana@excusas-sa.com", 1001, emailSender);
        empleado = new Empleado("Juan Pérez", "juan@empresa.com", 2001);
    }

    @Test
    void testProcesarExcusaTrivial() {
        Excusa excusa = new Excusa(empleado, new Trivial(), "Me quedé dormido");

        String resultado = recepcionista.manejarExcusa(excusa);

        assertEquals("Excusa trivial aceptada por Recepcionista", resultado);
        assertEquals(1, emailSender.getCantidadEmailsEnviados());

        EmailSenderTest.EmailEnviado email = emailSender.getUltimoEmail();
        assertEquals("juan@empresa.com", email.getDestino());
        assertEquals("ana@excusas-sa.com", email.getOrigen());
        assertEquals("motivo demora", email.getAsunto());
        assertEquals("la licencia fue aceptada", email.getCuerpo());
    }

    @Test
    void testNoProcesarExcusaCompleja() {
        Excusa excusa = new Excusa(empleado, new Complejo(), "Excusa compleja");
        EncargadoPorDefecto porDefecto = new EncargadoPorDefecto("Sistema", "sistema@excusas-sa.com", 9999, emailSender);
        recepcionista.setSiguienteEncargado(porDefecto);

        String resultado = recepcionista.manejarExcusa(excusa);

        assertEquals("Excusa rechazada: necesitamos pruebas contundentes", resultado);
        assertEquals(0, emailSender.getCantidadEmailsEnviados());
    }

    @Test
    void testEstrategiaVago() {
        recepcionista.setEstrategia(new EstrategiaVago());
        Excusa excusa = new Excusa(empleado, new Trivial(), "Me quedé dormido");
        EncargadoPorDefecto porDefecto = new EncargadoPorDefecto("Sistema", "sistema@excusas-sa.com", 9999, emailSender);
        recepcionista.setSiguienteEncargado(porDefecto);

        String resultado = recepcionista.manejarExcusa(excusa);

        assertEquals("Excusa pasada al siguiente (modo vago)", resultado);
        assertEquals(0, emailSender.getCantidadEmailsEnviados());
    }

    @Test
    void testEstrategiaNormal() {
        recepcionista.setEstrategia(new EstrategiaNormal());
        Excusa excusa = new Excusa(empleado, new Trivial(), "Me quedé dormido");

        String resultado = recepcionista.manejarExcusa(excusa);

        assertEquals("Excusa procesada normalmente", resultado);
        assertEquals(1, emailSender.getCantidadEmailsEnviados());
    }
}
