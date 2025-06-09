package com.excusas.empleados.encargados.estrategias;

import com.excusas.excusas.Excusa;
import com.excusas.empleados.encargados.Encargado;
import com.excusas.mail.EmailSender;
import com.excusas.mail.EmailSenderImpl;


public class EstrategiaProductivo implements EstrategiaManejo {
    private EmailSender emailSender = new EmailSenderImpl();

    @Override
    public boolean debeEvaluar(Encargado encargado, Excusa excusa) {
        System.out.println("*** ESTRATEGIA PRODUCTIVO ACTIVADA ***");
        System.out.println("Enviando notificación al CTO...");
        emailSender.enviarEmail(
                "cto@excusas-sa.com",
                encargado.getEmail(),
                "Evaluación de excusa",
                "Evaluando excusa de " + excusa.getEmpleado().getNombre()
        );

        return true;
    }
}
