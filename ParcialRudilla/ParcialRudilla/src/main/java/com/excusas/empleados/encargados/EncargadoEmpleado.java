package com.excusas.empleados.encargados;

import com.excusas.empleados.Empleado;
import com.excusas.excusas.Excusa;
import com.excusas.mail.EmailSender;
import com.excusas.mail.EmailSenderImpl;
import com.excusas.empleados.encargados.estrategias.EstrategiaManejo;
import com.excusas.empleados.encargados.estrategias.EstrategiaNormal;


public abstract class EncargadoEmpleado extends Encargado {
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

    public String getNombre() {
        return empleado.getNombre();
    }

    public int getNumeroLegajo() {
        return empleado.getNumeroLegajo();
    }

    public EmailSender getEmailSender() {
        return emailSender;
    }

    public void setEstrategia(EstrategiaManejo estrategia) {
        this.estrategia = estrategia;
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
