package com.excusas.excusas;

import com.excusas.empleados.Empleado;


public class Excusa implements IExcusa {
    private final Empleado empleado;
    private final MotivoExcusa motivo;
    private final String descripcion;

    public Excusa(Empleado empleado, MotivoExcusa motivo, String descripcion) {
        this.empleado = empleado;
        this.motivo = motivo;
        this.descripcion = descripcion;
    }

    @Override
    public Empleado getEmpleado() {
        return empleado;
    }

    @Override
    public MotivoExcusa getMotivo() {
        return motivo;
    }

    public String getDescripcion() {
        return descripcion;
    }


    public String getNombreEmpleado() {
        return empleado.getNombre();
    }


    public String getEmailEmpleado() {
        return empleado.getEmail();
    }
}
