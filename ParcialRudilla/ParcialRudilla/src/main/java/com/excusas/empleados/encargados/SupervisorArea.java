package com.excusas.empleados.encargados;

import com.excusas.excusas.Excusa;
import com.excusas.mail.EmailSender;
import com.excusas.mail.EmailSenderImpl;


public class SupervisorArea extends EncargadoEmpleado {

    public SupervisorArea(String nombre, String email, int numeroLegajo) {
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
        throw new UnsupportedOperationException("Supervisor no puede procesar excusas triviales");
    }

    @Override
    public void procesarExcusaModerada(Excusa excusa) {
        System.out.println("=== PROCESANDO EXCUSA MODERADA ===");
        System.out.println("Empleado: " + excusa.getEmpleado().getNombre());
        System.out.println("Motivo: " + excusa.getMotivo().getDescripcion());
        System.out.println("Procesada por: " + this.getNombre());
    }

    @Override
    public void procesarExcusaCompleja(Excusa excusa) {
        throw new UnsupportedOperationException("Supervisor no puede procesar excusas complejas");
    }

    @Override
    public void procesarExcusaInverosimil(Excusa excusa) {
        throw new UnsupportedOperationException("Supervisor no puede procesar excusas inverosímiles");
    }
}
