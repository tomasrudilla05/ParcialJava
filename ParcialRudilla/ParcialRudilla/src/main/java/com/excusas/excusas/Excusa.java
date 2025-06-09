package com.excusas.excusas;

import com.excusas.empleados.Empleado;
import com.excusas.empleados.encargados.*;
import com.excusas.interfaces.IExcusa;


public class Excusa implements IExcusa {
    private Empleado empleado;
    private MotivoExcusa motivo;

    public Excusa(Empleado empleado, MotivoExcusa motivo) {
        this.empleado = empleado;
        this.motivo = motivo;
    }

    @Override
    public boolean puedeSerManejadaPor(Recepcionista recepcionista) {
        return motivo.puedeSerManejadaPor(recepcionista);
    }

    @Override
    public boolean puedeSerManejadaPor(SupervisorArea supervisor) {
        return motivo.puedeSerManejadaPor(supervisor);
    }

    @Override
    public boolean puedeSerManejadaPor(GerenteRRHH gerente) {
        return motivo.puedeSerManejadaPor(gerente);
    }

    @Override
    public boolean puedeSerManejadaPor(CEO ceo) {
        return motivo.puedeSerManejadaPor(ceo);
    }

    @Override
    public void serProcesadaPor(Recepcionista recepcionista) {
        motivo.serProcesadaPor(recepcionista, this);
    }

    @Override
    public void serProcesadaPor(SupervisorArea supervisor) {
        motivo.serProcesadaPor(supervisor, this);
    }

    @Override
    public void serProcesadaPor(GerenteRRHH gerente) {
        motivo.serProcesadaPor(gerente, this);
    }

    @Override
    public void serProcesadaPor(CEO ceo) {
        motivo.serProcesadaPor(ceo, this);
    }

    @Override
    public Empleado getEmpleado() {
        return empleado;
    }

    @Override
    public MotivoExcusa getMotivo() {
        return motivo;
    }
}
