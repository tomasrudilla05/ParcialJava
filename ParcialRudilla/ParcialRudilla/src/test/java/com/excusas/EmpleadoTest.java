package com.excusas;

import com.excusas.empleados.Empleado;
import com.excusas.excusas.Excusa;
import com.excusas.excusas.triviales.ExcusaTrivial;
import com.excusas.excusas.moderadas.ExcusaModerada;
import com.excusas.excusas.moderadas.SeCortoLuz;
import com.excusas.excusas.moderadas.TuveCuidarFamiliar;
import com.excusas.excusas.complejas.ExcusaCompleja;
import com.excusas.excusas.inverosimiles.ExcusaInverosimil;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests de Empleado")
class EmpleadoTest {

    private Empleado empleado;

    @BeforeEach
    void setUp() {
        empleado = new Empleado("Juan Pérez", "juan@empresa.com", 2001);
    }

    @Test
    @DisplayName("Empleado se crea correctamente")
    void testEmpleadoSeCreaCorrectamente() {
        assertEquals("Juan Pérez", empleado.getNombre());
        assertEquals("juan@empresa.com", empleado.getEmail());
        assertEquals(2001, empleado.getLegajo());
    }

    @Test
    @DisplayName("Empleado puede crear excusa con motivo trivial")
    void testEmpleadoPuedeCrearExcusaConMotivoTrivial() {
        ExcusaTrivial motivo = new ExcusaTrivial();
        String descripcion = "Llegué tarde por el tráfico";

        Excusa excusa = empleado.crearExcusa(motivo, descripcion);

        assertNotNull(excusa);
        assertEquals(empleado, excusa.getEmpleado());
        assertEquals(motivo, excusa.getMotivo());
        assertEquals(descripcion, excusa.getDescripcion());
    }

    @Test
    @DisplayName("Empleado puede crear excusa con motivo moderado")
    void testEmpleadoPuedeCrearExcusaConMotivoModerado() {
        ExcusaModerada motivo = new ExcusaModerada();
        String descripcion = "Problemas con el transporte público";

        Excusa excusa = empleado.crearExcusa(motivo, descripcion);

        assertNotNull(excusa);
        assertEquals(empleado, excusa.getEmpleado());
        assertEquals(motivo, excusa.getMotivo());
        assertEquals(descripcion, excusa.getDescripcion());
    }

    @Test
    @DisplayName("Empleado puede crear excusa con motivo complejo")
    void testEmpleadoPuedeCrearExcusaConMotivoComplejo() {
        ExcusaCompleja motivo = new ExcusaCompleja();
        String descripcion = "Fui abducido por aliens";

        Excusa excusa = empleado.crearExcusa(motivo, descripcion);

        assertNotNull(excusa);
        assertEquals(empleado, excusa.getEmpleado());
        assertEquals(motivo, excusa.getMotivo());
        assertEquals(descripcion, excusa.getDescripcion());
    }

    @Test
    @DisplayName("Empleado puede crear excusa con motivo inverosímil")
    void testEmpleadoPuedeCrearExcusaConMotivoInverosimil() {
        ExcusaInverosimil motivo = new ExcusaInverosimil();
        String descripcion = "Algo completamente increíble";

        Excusa excusa = empleado.crearExcusa(motivo, descripcion);

        assertNotNull(excusa);
        assertEquals(empleado, excusa.getEmpleado());
        assertEquals(motivo, excusa.getMotivo());
        assertEquals(descripcion, excusa.getDescripcion());
    }

    @Test
    @DisplayName("Empleado puede crear excusa específica - Se cortó la luz")
    void testEmpleadoPuedeCrearExcusaSeCortoLuz() {
        SeCortoLuz motivo = new SeCortoLuz();
        String descripcion = "Se cortó la luz en todo el barrio";

        Excusa excusa = empleado.crearExcusa(motivo, descripcion);


        assertNotNull(excusa);
        assertTrue(excusa.getMotivo() instanceof SeCortoLuz);
        assertEquals(descripcion, excusa.getDescripcion());
    }

    @Test
    @DisplayName("Empleado puede crear excusa específica - Tuve que cuidar familiar")
    void testEmpleadoPuedeCrearExcusaTuveCuidarFamiliar() {
        TuveCuidarFamiliar motivo = new TuveCuidarFamiliar();
        String descripcion = "Tuve que cuidar a mi familiar enfermo";

        Excusa excusa = empleado.crearExcusa(motivo, descripcion);

        assertNotNull(excusa);
        assertTrue(excusa.getMotivo() instanceof TuveCuidarFamiliar);
        assertEquals(descripcion, excusa.getDescripcion());
    }
}
