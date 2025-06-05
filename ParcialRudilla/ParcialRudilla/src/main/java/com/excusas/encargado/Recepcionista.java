package com.excusas.encargado;

import com.excusas.excusa.Excusa;
import com.excusas.excusa.Trivial;
import com.excusas.mail.EmailSender;

public class Recepcionista extends EncargadoBase {

    public Recepcionista(String nombre, String email, int numeroLegajo, EmailSender emailSender) {
        super(nombre, email, numeroLegajo, emailSender);
    }

    @Override
    protected boolean puedeProcesar(Excusa excusa) {
        return excusa.getMotivo() instanceof Trivial;
    }

    @Override
    protected String procesarExcusa(Excusa excusa) {
        emailSender.enviarEmail(
                excusa.getEmpleado().getEmail(),
                this.getEmail(),
                "motivo demora",
                "la licencia fue aceptada"
        );
        System.out.println("Excusa trivial aceptada por Recepcionista");

        return "Excusa trivial aceptada por Recepcionista";
    }
}
