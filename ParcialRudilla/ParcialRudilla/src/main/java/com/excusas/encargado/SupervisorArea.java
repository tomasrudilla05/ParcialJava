package com.excusas.encargado;

import com.excusas.excusa.Excusa;
import com.excusas.excusa.CorteLuz;
import com.excusas.excusa.CuidadoFamiliar;
import com.excusas.excusa.Moderado;
import com.excusas.mail.EmailSender;

public class SupervisorArea extends EncargadoBase {

    public SupervisorArea(String nombre, String email, int numeroLegajo, EmailSender emailSender) {
        super(nombre, email, numeroLegajo, emailSender);
    }

    @Override
    protected boolean puedeProcesar(Excusa excusa) {
        return excusa.getMotivo() instanceof Moderado;
    }

    @Override
    protected String procesarExcusa(Excusa excusa) {
        if (excusa.getMotivo() instanceof CorteLuz) {
            emailSender.enviarEmail(
                    "EDESUR@mailfake.com.ar",
                    this.getEmail(),
                    "Consulta corte de luz",
                    "¿Hubo corte de luz en el barrio del empleado " + excusa.getEmpleado().getNombre() + "?"
            );
        } else if (excusa.getMotivo() instanceof CuidadoFamiliar) {
            emailSender.enviarEmail(
                    excusa.getEmpleado().getEmail(),
                    this.getEmail(),
                    "Consulta familiar",
                    "¿Todo está bien con tu familiar?"
            );
        }
        return "Excusa moderada aceptada por Supervisor de Área";
    }
}
