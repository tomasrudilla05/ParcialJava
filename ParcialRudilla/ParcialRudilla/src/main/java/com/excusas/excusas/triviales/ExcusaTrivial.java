package com.excusas.excusas.triviales;

import com.excusas.excusas.MotivoExcusa;
import com.excusas.excusas.Excusa;
import com.excusas.empleados.encargados.*;


public class ExcusaTrivial extends MotivoExcusa {

    public ExcusaTrivial(String descripcion) {
        super(descripcion);
    }

    @Override
    public boolean puedeSerManejadaPor(Recepcionista recepcionista) {
        return true;
    }

    @Override
    public boolean puedeSerManejadaPor(SupervisorArea supervisor) {
        return false;
    }

    @Override
    public boolean puedeSerManejadaPor(GerenteRRHH gerente) {
        return false;
    }

    @Override
    public boolean puedeSerManejadaPor(CEO ceo) {
        return false;
    }

    @Override
    public void serProcesadaPor(Recepcionista recepcionista, Excusa excusa) {
        System.out.println("=== PROCESANDO EXCUSA TRIVIAL ===");
        System.out.println("Empleado: " + excusa.getEmpleado().getNombre());
        System.out.println("Motivo: " + this.getDescripcion());
        System.out.println("Procesada por: " + recepcionista.getNombre());
        recepcionista.getEmailSender().enviarEmail(
                excusa.getEmpleado().getEmail(),
                recepcionista.getEmail(),
                "motivo demora",
                "la licencia fue aceptada"
        );
    }

    @Override
    public void serProcesadaPor(SupervisorArea supervisor, Excusa excusa) {
        throw new UnsupportedOperationException("Excusa trivial no puede ser procesada por supervisor");
    }

    @Override
    public void serProcesadaPor(GerenteRRHH gerente, Excusa excusa) {
        throw new UnsupportedOperationException("Excusa trivial no puede ser procesada por gerente");
    }

    @Override
    public void serProcesadaPor(CEO ceo, Excusa excusa) {
        throw new UnsupportedOperationException("Excusa trivial no puede ser procesada por CEO");
    }
}
