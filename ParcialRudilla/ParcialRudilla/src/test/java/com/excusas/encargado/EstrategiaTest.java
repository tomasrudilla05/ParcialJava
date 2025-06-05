package com.excusas.encargado;

import com.excusas.empleado.Empleado;
import com.excusas.excusa.Excusa;
import com.excusas.excusa.Trivial;
import com.excusas.testutils.EmailSenderTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EstrategiaTest {

    private Excusa excusa;

    @BeforeEach
    void setUp() {
        Empleado empleado = new Empleado("Juan Pérez", "juan@empresa.com", 2001);
        excusa = new Excusa(empleado, new Trivial(), "Me quedé dormido");
    }

    @Test
    void testEstrategiaNormal() {
        EstrategiaNormal estrategia = new EstrategiaNormal();
        boolean[] procesarEjecutado = {false};
        boolean[] pasarEjecutado = {false};

        Runnable procesarExcusa = () -> procesarEjecutado[0] = true;
        Runnable pasarAlSiguiente = () -> pasarEjecutado[0] = true;

        String resultado = estrategia.ejecutar(excusa, procesarExcusa, pasarAlSiguiente);

        assertEquals("Excusa procesada normalmente", resultado);
        assertTrue(procesarEjecutado[0]);
        assertFalse(pasarEjecutado[0]);
    }

    @Test
    void testEstrategiaVago() {
        EstrategiaVago estrategia = new EstrategiaVago();
        boolean[] procesarEjecutado = {false};
        boolean[] pasarEjecutado = {false};

        Runnable procesarExcusa = () -> procesarEjecutado[0] = true;
        Runnable pasarAlSiguiente = () -> pasarEjecutado[0] = true;

        String resultado = estrategia.ejecutar(excusa, procesarExcusa, pasarAlSiguiente);

        assertEquals("Excusa pasada al siguiente (modo vago)", resultado);
        assertFalse(procesarEjecutado[0]);
        assertTrue(pasarEjecutado[0]);
    }

    @Test
    void testEstrategiaProductivo() {
        EmailSenderTest emailSender = new EmailSenderTest();
        EstrategiaProductivo estrategia = new EstrategiaProductivo(emailSender);
        boolean[] procesarEjecutado = {false};
        boolean[] pasarEjecutado = {false};

        Runnable procesarExcusa = () -> procesarEjecutado[0] = true;
        Runnable pasarAlSiguiente = () -> pasarEjecutado[0] = true;

        String resultado = estrategia.ejecutar(excusa, procesarExcusa, pasarAlSiguiente);

        assertEquals("Excusa procesada productivamente (CTO notificado)", resultado);
        assertTrue(procesarEjecutado[0]);
        assertFalse(pasarEjecutado[0]);
        assertEquals(1, emailSender.getCantidadEmailsEnviados());
    }
}
