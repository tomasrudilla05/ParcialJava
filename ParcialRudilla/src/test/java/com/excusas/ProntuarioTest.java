package com.excusas;

import com.excusas.empleados.Empleado;
import com.excusas.excusas.Excusa;
import com.excusas.excusas.inverosimiles.ExcusaInverosimil;
import com.excusas.empleados.encargados.LineaEncargados;
import com.excusas.empleados.encargados.CEO;
import com.excusas.prontuario.AdministradorProntuarios;
import com.excusas.prontuario.Prontuario;
import com.excusas.prontuario.Observer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests de Prontuario y Observer")
class ProntuarioTest {

    private Empleado empleado;
    private AdministradorProntuarios administrador;

    @BeforeEach
    void setUp() {
        empleado = new Empleado("Ana López", "ana@empresa.com", 2004);

        AdministradorProntuarios.resetearInstancia();
        LineaEncargados.resetearInstancia();

        administrador = AdministradorProntuarios.getInstance();
    }

    @Test
    @DisplayName("AdministradorProntuarios es singleton")
    void testAdministradorProntuariosEsSingleton() {
        AdministradorProntuarios admin1 = AdministradorProntuarios.getInstance();
        AdministradorProntuarios admin2 = AdministradorProntuarios.getInstance();

        assertSame(admin1, admin2);
    }

    @Test
    @DisplayName("Prontuario se crea correctamente")
    void testProntuarioSeCreaCorrectamente() {
        ExcusaInverosimil motivo = new ExcusaInverosimil();
        Excusa excusa = empleado.crearExcusa(motivo, "Algo increíble");

        Prontuario prontuario = new Prontuario(empleado, excusa, empleado.getLegajo());

        assertEquals(empleado, prontuario.getEmpleado());
        assertEquals(excusa, prontuario.getExcusa());
        assertEquals(empleado.getLegajo(), prontuario.getNumeroLegajo());
    }

    @Test
    @DisplayName("AdministradorProntuarios persiste prontuarios correctamente")
    void testAdministradorPersisteProntuarios() {
        ExcusaInverosimil motivo = new ExcusaInverosimil();
        Excusa excusa = empleado.crearExcusa(motivo, "Algo increíble");
        Prontuario prontuario = new Prontuario(empleado, excusa, empleado.getLegajo());

        assertEquals(0, administrador.getProntuarios().size());

        administrador.persistirProntuario(prontuario);

        assertEquals(1, administrador.getProntuarios().size());
        assertEquals(prontuario, administrador.getProntuarios().get(0));
    }

    @Test
    @DisplayName("CEO se registra como observer correctamente")
    void testCEOSeRegistraComoObserver() {
        CEO ceo = new CEO("Roberto Silva", "ceo@excusas-sa.com", 1004);

        assertTrue(ceo instanceof Observer);
    }

    @Test
    @DisplayName("Sistema completo con prontuario funciona correctamente")
    void testSistemaCompletoConProntuario() {
        ExcusaInverosimil motivo = new ExcusaInverosimil();
        Excusa excusa = empleado.crearExcusa(motivo, "Algo completamente increíble");

        assertEquals(0, administrador.getProntuarios().size());

        assertDoesNotThrow(() -> {
            LineaEncargados.getInstance().manejarExcusa(excusa);
        });

        assertEquals(1, administrador.getProntuarios().size());
    }

    @Test
    @DisplayName("Observer recibe notificaciones correctamente")
    void testObserverRecibeNotificaciones() {
        TestObserver testObserver = new TestObserver();
        administrador.agregarObserver(testObserver);

        ExcusaInverosimil motivo = new ExcusaInverosimil();
        Excusa excusa = empleado.crearExcusa(motivo, "Test");
        Prontuario prontuario = new Prontuario(empleado, excusa, empleado.getLegajo());

        assertFalse(testObserver.fueNotificado);

        administrador.persistirProntuario(prontuario);

        assertTrue(testObserver.fueNotificado);
        assertEquals(prontuario, testObserver.ultimoProntuario);
    }

    @Test
    @DisplayName("Observer puede ser removido correctamente")
    void testObserverPuedeSerRemovido() {
        TestObserver testObserver = new TestObserver();
        administrador.agregarObserver(testObserver);
        administrador.removerObserver(testObserver);

        ExcusaInverosimil motivo = new ExcusaInverosimil();
        Excusa excusa = empleado.crearExcusa(motivo, "Test");
        Prontuario prontuario = new Prontuario(empleado, excusa, empleado.getLegajo());

        administrador.persistirProntuario(prontuario);

        assertFalse(testObserver.fueNotificado);
    }

    private static class TestObserver implements Observer {
        boolean fueNotificado = false;
        Prontuario ultimoProntuario = null;

        @Override
        public void actualizar(Prontuario prontuario) {
            fueNotificado = true;
            ultimoProntuario = prontuario;
        }
    }
}
