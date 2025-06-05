package com.excusas.encargado;

import com.excusas.excusa.Excusa;
import com.excusas.mail.EmailSender;

public class EstrategiaProductivo implements EstrategiaManejo {
    private EmailSender emailSender;

    public EstrategiaProductivo(EmailSender emailSender) {
        this.emailSender = emailSender;
    }

    @Override
    public String ejecutar(Excusa excusa, Runnable procesarExcusa, Runnable pasarAlSiguiente) {
        emailSender.enviarEmail("cto@excusas-sa.com", "sistema@excusas-sa.com",
                "Excusa en modo productivo",
                "Se está procesando una excusa en modo productivo: " + excusa.getDescripcion());

        procesarExcusa.run();
        return "Excusa procesada productivamente (CTO notificado)";
    }
}
