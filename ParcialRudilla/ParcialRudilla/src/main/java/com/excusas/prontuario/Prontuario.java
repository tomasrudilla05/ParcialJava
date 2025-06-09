package com.excusas.prontuario;

import com.excusas.empleados.Empleado;
import com.excusas.excusas.Excusa;
import com.excusas.interfaces.IProntuario;
import com.excusas.interfaces.IExcusa;

public class Prontuario implements IProntuario {
    private Empleado empleado;
    private Excusa excusa;
    private int numeroLegajo;

    public Prontuario(Empleado empleado, Excusa excusa, int numeroLegajo) {
        this.empleado = empleado;
        this.excusa = excusa;
        this.numeroLegajo = numeroLegajo;
    }

    @Override
    public Empleado getEmpleado() {
        return empleado;
    }

    @Override
    public IExcusa getExcusa() {
        return excusa;
    }

    @Override
    public int getNumeroLegajo() {
        return numeroLegajo;
    }
}
