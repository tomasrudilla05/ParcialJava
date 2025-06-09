package com.excusas.excusas;

import com.excusas.empleados.encargados.*;


public abstract class MotivoExcusa {
    protected String descripcion;

    public MotivoExcusa(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public abstract boolean puedeSerManejadaPor(Recepcionista recepcionista);
    public abstract boolean puedeSerManejadaPor(SupervisorArea supervisor);
    public abstract boolean puedeSerManejadaPor(GerenteRRHH gerente);
    public abstract boolean puedeSerManejadaPor(CEO ceo);

    public abstract void serProcesadaPor(Recepcionista recepcionista, Excusa excusa);
    public abstract void serProcesadaPor(SupervisorArea supervisor, Excusa excusa);
    public abstract void serProcesadaPor(GerenteRRHH gerente, Excusa excusa);
    public abstract void serProcesadaPor(CEO ceo, Excusa excusa);
}
