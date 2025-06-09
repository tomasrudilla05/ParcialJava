package com.excusas.excusas.moderadas;

import com.excusas.excusas.MotivoExcusa;
import com.excusas.excusas.Excusa;
import com.excusas.empleados.encargados.*;


public class ExcusaModerada extends MotivoExcusa {

    public ExcusaModerada(String descripcion) {
        super(descripcion);
    }

    @Override
    public boolean puedeSerManejadaPor(Recepcionista recepcionista) {
        return false;
    }

    @Override
    public boolean puedeSerManejadaPor(SupervisorArea supervisor) {
        return true;
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
        throw new UnsupportedOperationException("Excusa moderada no puede ser procesada por recepcionista");
    }

    @Override
    public void serProcesadaPor(SupervisorArea supervisor, Excusa excusa) {
        System.out.println("=== PROCESANDO EXCUSA MODERADA ===");
        System.out.println("Empleado: " + excusa.getEmpleado().getNombre());
        System.out.println("Motivo: " + this.getDescripcion());
        System.out.println("Procesada por: " + supervisor.getNombre());

        supervisor.getEmailSender().enviarEmail(
                excusa.getEmpleado().getEmail(),
                supervisor.getEmail(),
                "Excusa moderada aceptada",
                "Su excusa ha sido aceptada"
        );
    }

    @Override
    public void serProcesadaPor(GerenteRRHH gerente, Excusa excusa) {
        throw new UnsupportedOperationException("Excusa moderada no puede ser procesada por gerente");
    }

    @Override
    public void serProcesadaPor(CEO ceo, Excusa excusa) {
        throw new UnsupportedOperationException("Excusa moderada no puede ser procesada por CEO");
    }
}
