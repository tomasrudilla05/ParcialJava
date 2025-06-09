package com.excusas.empleados.encargados;

import com.excusas.excusas.Excusa;
import com.excusas.mail.EmailSender;
import com.excusas.mail.EmailSenderImpl;
import com.excusas.prontuario.Prontuario;
import com.excusas.prontuario.AdministradorProntuarios;


public class CEO extends EncargadoEmpleado {

    public CEO(String nombre, String email, int numeroLegajo) {
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
        throw new UnsupportedOperationException("CEO no puede procesar excusas triviales");
    }

    @Override
    public void procesarExcusaModerada(Excusa excusa) {
        throw new UnsupportedOperationException("CEO no puede procesar excusas moderadas");
    }

    @Override
    public void procesarExcusaCompleja(Excusa excusa) {
        throw new UnsupportedOperationException("CEO no puede procesar excusas complejas");
    }

    @Override
    public void procesarExcusaInverosimil(Excusa excusa) {
        System.out.println("=== PROCESANDO EXCUSA INVEROSÍMIL ===");
        System.out.println("Empleado: " + excusa.getEmpleado().getNombre());
        System.out.println("Motivo: " + excusa.getMotivo().getDescripcion());
        System.out.println("Procesada por CEO: " + this.getNombre());
        System.out.println("Respuesta: Aprobado por creatividad");
        System.out.println("Creando prontuario...");

        emailSender.enviarEmail(
                excusa.getEmpleado().getEmail(),
                this.getEmail(),
                "Excusa aprobada",
                "Aprobado por creatividad"
        );

        Prontuario prontuario = new Prontuario(
                excusa.getEmpleado(),
                excusa,
                excusa.getEmpleado().getNumeroLegajo()
        );

        AdministradorProntuarios.getInstance().persistirProntuario(prontuario);
    }
}
