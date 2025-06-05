package com.excusas.encargado;

import com.excusas.excusa.Excusa;
import com.excusas.mail.EmailSender;

public class EncargadoPorDefecto extends EncargadoBase {

    public EncargadoPorDefecto(String nombre, String email, int numeroLegajo, EmailSender emailSender) {
        super(nombre, email, numeroLegajo, emailSender);
    }

    @Override
    protected boolean puedeProcesar(Excusa excusa) {
        return false;
    }

    @Override
    protected String procesarExcusa(Excusa excusa) {
        return "Excusa rechazada: necesitamos pruebas contundentes";
    }
}
