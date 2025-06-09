package com.excusas.empleados.encargados;

import com.excusas.excusas.Excusa;
import com.excusas.mail.EmailSender;
import com.excusas.mail.EmailSenderImpl;


public class EncargadoRechazador extends Encargado {
    private EmailSender emailSender;

    public EncargadoRechazador() {
        super();
        this.emailSender = new EmailSenderImpl();
    }

    @Override
    public void manejarExcusa(Excusa excusa) {
        System.out.println("=== EXCUSA RECHAZADA ===");
        System.out.println("Empleado: " + excusa.getEmpleado().getNombre());
        System.out.println("Motivo: " + excusa.getMotivo().getDescripcion());
        System.out.println("Respuesta: Excusa rechazada: necesitamos pruebas contundentes");

        emailSender.enviarEmail(
                excusa.getEmpleado().getEmail(),
                this.getEmail(),
                "Excusa rechazada",
                "Necesitamos pruebas contundentes para su excusa: " +
                        excusa.getMotivo().getDescripcion()
        );
    }

    @Override
    public String getEmail() {
        return "rechazos@excusas-sa.com";
    }
}
