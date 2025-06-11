package com.excusas.empleados.encargados;

import com.excusas.empleados.Empleado;
import com.excusas.excusas.Excusa;
import com.excusas.mail.EmailSender;
import com.excusas.mail.EmailSenderImpl;
import com.excusas.empleados.encargados.estrategias.EstrategiaManejo;
import com.excusas.empleados.encargados.estrategias.EstrategiaNormal;

public abstract class Encargado extends Empleado implements IEncargado {
    private IEncargado siguiente;
    private EmailSender emailSender;
    private EstrategiaManejo estrategia;

    public Encargado(String nombre, String email, int numeroLegajo) {
        super(nombre, email, numeroLegajo);
        this.emailSender = new EmailSenderImpl();
        this.estrategia = new EstrategiaNormal();
    }

    public void setEstrategia(EstrategiaManejo estrategia) {
        this.estrategia = estrategia;
    }

    public EstrategiaManejo getEstrategia() {
        return this.estrategia;
    }

    @Override
    public void setSiguiente(IEncargado siguiente) {
        this.siguiente = siguiente;
    }

    @Override
    public IEncargado getSiguiente() {
        return this.siguiente;
    }

    @Override
    public void manejarExcusa(Excusa excusa) {
        if (estrategia.debeEvaluar(this, excusa)) {
            ejecutarProcesamiento(excusa);
        } else {
            pasarAlSiguiente(excusa);
        }
    }

    @Override
    public final void ejecutarProcesamiento(Excusa excusa) {
        if (puedeManearEstaExcusa(excusa)) {
            this.procesarExcusa(excusa);
        } else {
            pasarAlSiguiente(excusa);
        }
    }


    private void pasarAlSiguiente(Excusa excusa) {
        if (this.getSiguiente() != null) {
            this.getSiguiente().manejarExcusa(excusa);
        } else {
            this.manejarExcusaNoManejable(excusa);
        }
    }


    private boolean puedeManearEstaExcusa(Excusa excusa) {
        return (excusa.getMotivo().puedeSerManejadoPorTrivial() && this.puedeManejarTrivial()) ||
                (excusa.getMotivo().puedeSerManejadoPorModerado() && this.puedeManejarModerado()) ||
                (excusa.getMotivo().puedeSerManejadoPorComplejo() && this.puedeManejarComplejo()) ||
                (excusa.getMotivo().puedeSerManejadoPorInverosimil() && this.puedeManejarInverosimil());
    }

    protected void manejarExcusaNoManejable(Excusa excusa) {
        System.out.println("Excusa no pudo ser manejada por ningún encargado - enviando al rechazador");
        EncargadoRechazador rechazador = new EncargadoRechazador();
        rechazador.manejarExcusa(excusa);
    }

    public EmailSender getEmailSender() {
        return emailSender;
    }

    @Override
    public abstract void procesarExcusa(Excusa excusa);

    @Override
    public boolean puedeManejarTrivial() {
        return false;
    }

    @Override
    public boolean puedeManejarModerado() {
        return false;
    }

    @Override
    public boolean puedeManejarComplejo() {
        return false;
    }

    @Override
    public boolean puedeManejarInverosimil() {
        return false;
    }
}
