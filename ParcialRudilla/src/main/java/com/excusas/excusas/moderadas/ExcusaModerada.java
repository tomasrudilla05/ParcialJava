package com.excusas.excusas.moderadas;

import com.excusas.excusas.MotivoExcusa;
import com.excusas.excusas.Excusa;
import com.excusas.empleados.encargados.SupervisorArea;

public class ExcusaModerada extends MotivoExcusa {

    public ExcusaModerada() {
        super("Excusa moderada");
    }

    @Override
    public boolean puedeSerManejadoPorModerado() {
        return true;
    }


    public void procesarCon(SupervisorArea supervisor, Excusa excusa) {
        this.serProcesadaPor(supervisor, excusa);
    }


    public void serProcesadaPor(SupervisorArea supervisor, Excusa excusa) {
        System.out.println("=== PROCESANDO EXCUSA MODERADA GENÉRICA ===");
        System.out.println("Empleado: " + excusa.getEmpleado().getNombre());
        System.out.println("Motivo: " + excusa.getDescripcion());
        System.out.println("Procesada por: " + supervisor.getNombre());

        supervisor.getEmailSender().enviarEmail(
                excusa.getEmpleado().getEmail(),
                supervisor.getEmail(),
                "Excusa aceptada",
                "Su excusa ha sido aceptada"
        );
    }
}
