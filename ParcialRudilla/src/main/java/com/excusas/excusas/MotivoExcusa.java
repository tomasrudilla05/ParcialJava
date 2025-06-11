package com.excusas.excusas;


public abstract class MotivoExcusa {
    protected String descripcion;

    public MotivoExcusa(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public boolean puedeSerManejadoPorTrivial() {
        return false;
    }

    public boolean puedeSerManejadoPorModerado() {
        return false;
    }

    public boolean puedeSerManejadoPorComplejo() {
        return false;
    }

    public boolean puedeSerManejadoPorInverosimil() {
        return false;
    }
}
