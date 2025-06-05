package com.excusas.encargado;

import com.excusas.excusa.Excusa;
import com.excusas.excusa.Complejo;
import com.excusas.mail.EmailSender;

public class GerenteRRHH extends EncargadoBase {

    public GerenteRRHH(String nombre, String email, int numeroLegajo, EmailSender emailSender) {
        super(nombre, email, numeroLegajo, emailSender);
    }

    @Override
    protected boolean puedeProcesar(Excusa excusa) {
        return excusa.getMotivo() instanceof Complejo;
    }

    @Override
    protected String procesarExcusa(Excusa excusa) {
        return "Excusa compleja aceptada por Gerente de RRHH";
    }
}
