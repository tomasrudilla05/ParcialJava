package com.excusas.excusa;

import com.excusas.empleado.Empleado;

public class Excusa {
    private Empleado empleado;
    private MotivoExcusa motivo;
    private String descripcion;

    public Excusa(Empleado empleado, MotivoExcusa motivo, String descripcion) {
        this.empleado = empleado;
        this.motivo = motivo;
        this.descripcion = descripcion;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public MotivoExcusa getMotivo() {
        return motivo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    @Override
    public String toString() {
        return "Excusa{" +
                "empleado=" + empleado +
                ", motivo=" + motivo +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
