package com.excusas.excusas.complejas;

import com.excusas.excusas.MotivoExcusa;
import com.excusas.excusas.Excusa;
import com.excusas.empleados.encargados.Recepcionista;
import com.excusas.empleados.encargados.SupervisorArea;
import com.excusas.empleados.encargados.GerenteRRHH;
import com.excusas.empleados.encargados.CEO;


public class ExcusaCompleja extends MotivoExcusa {

    public ExcusaCompleja(String descripcion) {
        super(descripcion);
    }

    @Override
    public boolean puedeSerManejadaPor(Recepcionista recepcionista) {
        return false;
    }

    @Override
    public boolean puedeSerManejadaPor(SupervisorArea supervisor) {
        return false;
    }

    @Override
    public boolean puedeSerManejadaPor(GerenteRRHH gerente) {
        return true;
    }

    @Override
    public boolean puedeSerManejadaPor(CEO ceo) {
        return false;
    }

    @Override
    public void serProcesadaPor(Recepcionista recepcionista, Excusa excusa) {
        throw new UnsupportedOperationException("Excusa compleja no puede ser procesada por recepcionista");
    }

    @Override
    public void serProcesadaPor(SupervisorArea supervisor, Excusa excusa) {
        throw new UnsupportedOperationException("Excusa compleja no puede ser procesada por supervisor");
    }

    @Override
    public void serProcesadaPor(GerenteRRHH gerente, Excusa excusa) {

        System.out.println("=== PROCESANDO EXCUSA COMPLEJA ===");
        System.out.println("Empleado: " + excusa.getEmpleado().getNombre());
        System.out.println("Motivo: " + this.getDescripcion());
        System.out.println("Procesada por Gerente RRHH: " + gerente.getNombre());
    }

    @Override
    public void serProcesadaPor(CEO ceo, Excusa excusa) {
        throw new UnsupportedOperationException("Excusa compleja no puede ser procesada por CEO");
    }
}
