package com.excusas.excusas;

import com.excusas.empleados.Empleado;
import com.excusas.empleados.encargados.*;


public class Excusa {
    private Empleado empleado;
    private MotivoExcusa motivo;

    public Excusa(Empleado empleado, MotivoExcusa motivo) {
        this.empleado = empleado;
        this.motivo = motivo;
    }

    public boolean puedeSerManejadaPor(Recepcionista recepcionista) {
        return motivo.puedeSerManejadaPor(recepcionista);
    }

    public boolean puedeSerManejadaPor(SupervisorArea supervisor) {
        return motivo.puedeSerManejadaPor(supervisor);
    }

    public boolean puedeSerManejadaPor(GerenteRRHH gerente) {
        return motivo.puedeSerManejadaPor(gerente);
    }

    public boolean puedeSerManejadaPor(CEO ceo) {
        return motivo.puedeSerManejadaPor(ceo);
    }

    public void serProcesadaPor(Recepcionista recepcionista) {
        motivo.serProcesadaPor(recepcionista, this);
    }

    public void serProcesadaPor(SupervisorArea supervisor) {
        motivo.serProcesadaPor(supervisor, this);
    }

    public void serProcesadaPor(GerenteRRHH gerente) {
        motivo.serProcesadaPor(gerente, this);
    }

    public void serProcesadaPor(CEO ceo) {
        motivo.serProcesadaPor(ceo, this);
    }


    public Empleado getEmpleado() { return empleado; }
    public MotivoExcusa getMotivo() { return motivo; }
}
