package com.excusas.prontuario;

import com.excusas.empleado.Empleado;
import com.excusas.excusa.Excusa;
import com.excusas.excusa.Inverosimil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdministradorProntuariosTest {

    private AdministradorProntuarios administrador;

    @BeforeEach
    void setUp() {
        administrador = AdministradorProntuarios.getInstance();
        administrador.limpiarProntuarios();
    }

    @Test
    void testSingleton() {
        AdministradorProntuarios instancia1 = AdministradorProntuarios.getInstance();
        AdministradorProntuarios instancia2 = AdministradorProntuarios.getInstance();

        assertSame(instancia1, instancia2);
    }

    @Test
    void testPersistirProntuario() {
        Empleado empleado = new Empleado("Juan Pérez", "juan@empresa.com", 2001);
        Excusa excusa = new Excusa(empleado, new Inverosimil(), "Fui abducido por aliens");
        Prontuario prontuario = new Prontuario(empleado, excusa, 2001);

        int cantidadAntes = administrador.getCantidadProntuarios();
        administrador.persistirProntuario(prontuario);

        assertEquals(cantidadAntes + 1, administrador.getCantidadProntuarios());
        assertTrue(administrador.getProntuarios().contains(prontuario));
    }

    @Test
    void testMultiplesProntuarios() {
        assertEquals(0, administrador.getCantidadProntuarios());

        Empleado empleado1 = new Empleado("Juan Pérez", "juan@empresa.com", 2001);
        Empleado empleado2 = new Empleado("Ana García", "ana@empresa.com", 2002);

        Excusa excusa1 = new Excusa(empleado1, new Inverosimil(), "Aliens");
        Excusa excusa2 = new Excusa(empleado2, new Inverosimil(), "Paloma ladrona");

        Prontuario prontuario1 = new Prontuario(empleado1, excusa1, 2001);
        Prontuario prontuario2 = new Prontuario(empleado2, excusa2, 2002);

        administrador.persistirProntuario(prontuario1);
        assertEquals(1, administrador.getCantidadProntuarios());

        administrador.persistirProntuario(prontuario2);
        assertEquals(2, administrador.getCantidadProntuarios());

        assertTrue(administrador.getProntuarios().contains(prontuario1));
        assertTrue(administrador.getProntuarios().contains(prontuario2));
    }

    @Test
    void testLimpiarProntuarios() {
        Empleado empleado = new Empleado("Test", "test@empresa.com", 1000);
        Excusa excusa = new Excusa(empleado, new Inverosimil(), "Test");
        Prontuario prontuario = new Prontuario(empleado, excusa, 1000);

        administrador.persistirProntuario(prontuario);
        assertEquals(1, administrador.getCantidadProntuarios());

        administrador.limpiarProntuarios();
        assertEquals(0, administrador.getCantidadProntuarios());
    }
}
