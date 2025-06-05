package com.excusas.encargado;

import com.excusas.empleado.Empleado;
import com.excusas.excusa.Excusa;
import com.excusas.excusa.Complejo;
import com.excusas.excusa.Trivial;
import com.excusas.testutils.EmailSenderTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GerenteRRHHTest {

    private EmailSenderTest emailSender;
    private GerenteRRHH gerente;
    private Empleado empleado;

    @BeforeEach
    void setUp() {
        emailSender = new EmailSenderTest();
        gerente = new GerenteRRHH("María Rodríguez", "maria@excusas-sa.com", 1003, emailSender);
        empleado = new Empleado("Juan Pérez", "juan@empresa.com", 2001);
    }

    @Test
    void testProcesarExcusaCompleja() {
        Excusa excusa = new Excusa(empleado, new Complejo(), "Mi gato se enfermó");

        String resultado = gerente.manejarExcusa(excusa);

        assertEquals("Excusa compleja aceptada por Gerente de RRHH", resultado);
        assertEquals(0, emailSender.getCantidadEmailsEnviados());
    }

    @Test
    void testNoProcesarExcusaTrivial() {
        Excusa excusa = new Excusa(empleado, new Trivial(), "Me quedé dormido");
        EncargadoPorDefecto porDefecto = new EncargadoPorDefecto("Sistema", "sistema@excusas-sa.com", 9999, emailSender);
        gerente.setSiguienteEncargado(porDefecto);

        String resultado = gerente.manejarExcusa(excusa);

        assertEquals("Excusa rechazada: necesitamos pruebas contundentes", resultado);
        assertEquals(0, emailSender.getCantidadEmailsEnviados());
    }
}
