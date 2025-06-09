package com.excusas.empleados.encargados;

import com.excusas.empleados.Empleado;
import com.excusas.excusas.Excusa;
import com.excusas.mail.EmailSender;
import com.excusas.mail.EmailSenderImpl;
import com.excusas.empleados.encargados.estrategias.EstrategiaManejo;
import com.excusas.empleados.encargados.estrategias.EstrategiaNormal;
import com.excusas.interfaces.IEncargadoEmpleado;
import com.excusas.interfaces.IExcusa;

public abstract class EncargadoEmpleado extends Encargado implements IEncargadoEmpleado {
    protected Empleado empleado;
    protected EmailSender emailSender;
    protected EstrategiaManejo estrategia;

    public EncargadoEmpleado(String nombre, String email, int numeroLegajo) {
        super();
        this.empleado = new Empleado(nombre, email, numeroLegajo);
        this.emailSender = new EmailSenderImpl();
        this.estrategia = new EstrategiaNormal();
    }

    @Override
    public String getEmail() {
        return empleado.getEmail();
    }

    @Override
    public String getNombre() {
        return empleado.getNombre();
    }

    @Override
    public int getNumeroLegajo() {
        return empleado.getNumeroLegajo();
    }

    @Override
    public EmailSender getEmailSender() {
        return emailSender;
    }

    @Override
    public void setEstrategia(EstrategiaManejo estrategia) {
        this.estrategia = estrategia;
    }

    @Override
    public IExcusa generarExcusa(String motivo) {
        return empleado.generarExcusa(motivo);
    }

    @Override
    public void enviarExcusa(IExcusa excusa) {
        empleado.enviarExcusa(excusa);
    }


    @Override
    public final void manejarExcusa(Excusa unaExcusa) {
        System.out.println("--- Evaluando excusa en: " + this.getClass().getSimpleName() + " ---");

        if (estrategia.debeEvaluar(this, unaExcusa)) {
            if (puedeManear(unaExcusa)) {
                System.out.println(this.getClass().getSimpleName() + " procesando excusa...");
                procesarExcusa(unaExcusa);
            } else {
                delegarAlSiguiente(unaExcusa);
            }
        } else {
            delegarAlSiguiente(unaExcusa);
        }
    }

    protected abstract boolean puedeManear(Excusa excusa);
    protected abstract void procesarExcusa(Excusa excusa);
}
