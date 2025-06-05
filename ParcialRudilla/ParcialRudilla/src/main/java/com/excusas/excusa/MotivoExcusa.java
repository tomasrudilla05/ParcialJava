package com.excusas.excusa;

public abstract class MotivoExcusa {
    private String descripcion;

    public MotivoExcusa(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    @Override
    public String toString() {
        return descripcion;
    }
}
