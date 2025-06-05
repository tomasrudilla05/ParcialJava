package com.excusas.encargado;

import com.excusas.empleado.Empleado;
import com.excusas.excusa.Excusa;
import com.excusas.excusa.*;
import com.excusas.testutils.EmailSenderTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CadenaEncargadosFactoryTest {

    private EmailSenderTest emailSender;
    private ManejadorExcusas cadena;
    private Empleado empleado;

    @BeforeEach
    void setUp() {
        emailSender = new EmailSenderTest();
        cadena = CadenaEncargadosFactory.crearCadenaCompleta(emailSender);
        empleado = new Empleado("Juan Pérez", "juan@empresa.com", 2001);
    }

    @Test
    void testCadenaCompletaExcusaTrivial() {
        Excusa excusa = new Excusa(empleado, new Trivial(), "Me quedé dormido");

        String resultado = cadena.manejarExcusa(excusa);

        assertEquals("Excusa trivial aceptada por Recepcionista", resultado);
        assertEquals(1, emailSender.getCantidadEmailsEnviados());
    }

    @Test
    void testCadenaCompletaExcusaModeradaCorteLuz() {
        Excusa excusa = new Excusa(empleado, new CorteLuz(), "Se cortó la luz");

        String resultado = cadena.manejarExcusa(excusa);

        assertEquals("Excusa moderada aceptada por Supervisor de Área", resultado);
        assertTrue(emailSender.seEnvioEmailA("EDESUR@mailfake.com.ar"));
    }

    @Test
    void testCadenaCompletaExcusaModeradaCuidadoFamiliar() {
        Excusa excusa = new Excusa(empleado, new CuidadoFamiliar(), "Cuidé a mi hijo");

        String resultado = cadena.manejarExcusa(excusa);

        assertEquals("Excusa moderada aceptada por Supervisor de Área", resultado);
        assertTrue(emailSender.seEnvioEmailA("juan@empresa.com"));
    }

    @Test
    void testCadenaCompletaExcusaCompleja() {
        Excusa excusa = new Excusa(empleado, new Complejo(), "Excusa compleja");

        String resultado = cadena.manejarExcusa(excusa);

        assertEquals("Excusa compleja aceptada por Gerente de RRHH", resultado);
    }

    @Test
    void testCadenaCompletaExcusaInverosimil() {
        Excusa excusa = new Excusa(empleado, new Inverosimil(), "Fui abducido por aliens");

        String resultado = cadena.manejarExcusa(excusa);

        assertEquals("Excusa extremadamente inverosímil aceptada por CEO - Prontuario creado", resultado);
        assertTrue(emailSender.seEnvioEmailConAsunto("Excusa Aprobada"));
    }

    @Test
    void testCadenaCompletaVariosMotivos() {
        Excusa excusaTrivial = new Excusa(empleado, new Trivial(), "Me quedé dormido");
        Excusa excusaModerada1 = new Excusa(empleado, new CorteLuz(), "Se cortó la luz");
        Excusa excusaModerada2 = new Excusa(empleado, new CuidadoFamiliar(), "Cuidé a mi hijo");
        Excusa excusaCompleja = new Excusa(empleado, new Complejo(), "Mi gato se enfermó");
        Excusa excusaInverosimil = new Excusa(empleado, new Inverosimil(), "Aliens");

        assertEquals("Excusa trivial aceptada por Recepcionista", cadena.manejarExcusa(excusaTrivial));
        assertEquals("Excusa moderada aceptada por Supervisor de Área", cadena.manejarExcusa(excusaModerada1));
        assertEquals("Excusa moderada aceptada por Supervisor de Área", cadena.manejarExcusa(excusaModerada2));
        assertEquals("Excusa compleja aceptada por Gerente de RRHH", cadena.manejarExcusa(excusaCompleja));
        assertEquals("Excusa extremadamente inverosímil aceptada por CEO - Prontuario creado", cadena.manejarExcusa(excusaInverosimil));
    }
}
