package com.excusas.empleados.encargados;

import com.excusas.excusas.Excusa;
import com.excusas.mail.EmailSender;
import com.excusas.mail.EmailSenderImpl;


public class Recepcionista extends EncargadoEmpleado {

    public Recepcionista(String nombre, String email, int numeroLegajo) {
        super(nombre, email, numeroLegajo);
        this.emailSender = new EmailSenderImpl();
    }

    @Override
    public EmailSender getEmailSender() {
        return emailSender;
    }

    @Override
    protected boolean puedeManear(Excusa excusa) {
        return excusa.puedeSerManejadaPor(this);
    }

    @Override
    protected void procesarExcusa(Excusa excusa) {
        excusa.serProcesadaPor(this);
    }

    @Override
    public void procesarExcusaTrivial(Excusa excusa) {
        System.out.println("=== PROCESANDO EXCUSA TRIVIAL ===");
        System.out.println("Empleado: " + excusa.getEmpleado().getNombre());
        System.out.println("Motivo: " + excusa.getMotivo().getDescripcion());
        System.out.println("Procesada por: " + this.getNombre());

        emailSender.enviarEmail(
                excusa.getEmpleado().getEmail(),
                this.getEmail(),
                "motivo demora",
                "la licencia fue aceptada"
        );
    }

    @Override
    public void procesarExcusaModerada(Excusa excusa) {
        throw new UnsupportedOperationException("Recepcionista no puede procesar excusas moderadas");
    }

    @Override
    public void procesarExcusaCompleja(Excusa excusa) {
        throw new UnsupportedOperationException("Recepcionista no puede procesar excusas complejas");
    }

    @Override
    public void procesarExcusaInverosimil(Excusa excusa) {
        throw new UnsupportedOperationException("Recepcionista no puede procesar excusas inverosímiles");
    }
}
