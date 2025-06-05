package com.excusas.encargado;

import com.excusas.empleado.Empleado;
import com.excusas.excusa.Excusa;
import com.excusas.mail.EmailSender;

public abstract class EncargadoBase extends Empleado implements ManejadorExcusas {
    protected ManejadorExcusas siguienteEncargado;
    protected EmailSender emailSender;
    protected EstrategiaManejo estrategia;

    public EncargadoBase(String nombre, String email, int numeroLegajo, EmailSender emailSender) {
        super(nombre, email, numeroLegajo);
        this.emailSender = emailSender;
    }

    public void setSiguienteEncargado(ManejadorExcusas siguienteEncargado) {
        this.siguienteEncargado = siguienteEncargado;
    }

    public void setEstrategia(EstrategiaManejo estrategia) {
        this.estrategia = estrategia;
    }

    @Override
    public String manejarExcusa(Excusa unaExcusa) {
        if (estrategia != null) {
            return estrategia.ejecutar(unaExcusa,
                    () -> procesarExcusa(unaExcusa),
                    () -> pasarAlSiguiente(unaExcusa)
            );
        } else {
            if (puedeProcesar(unaExcusa)) {
                return procesarExcusa(unaExcusa);
            } else {
                return pasarAlSiguiente(unaExcusa);
            }
        }
    }

    protected abstract boolean puedeProcesar(Excusa excusa);
    protected abstract String procesarExcusa(Excusa excusa);

    protected String pasarAlSiguiente(Excusa excusa) {
        if (siguienteEncargado != null) {
            return siguienteEncargado.manejarExcusa(excusa);
        } else {
            return "Excusa rechazada: necesitamos pruebas contundentes";
        }
    }
}
