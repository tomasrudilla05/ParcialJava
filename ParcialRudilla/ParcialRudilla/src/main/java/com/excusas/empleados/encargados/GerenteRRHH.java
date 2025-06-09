package com.excusas.empleados.encargados;

import com.excusas.excusas.Excusa;


public class GerenteRRHH extends EncargadoEmpleado {

    public GerenteRRHH(String nombre, String email, int numeroLegajo) {
        super(nombre, email, numeroLegajo);
    }

    @Override
    protected boolean puedeManear(Excusa excusa) {
        return excusa.puedeSerManejadaPor(this);
    }

    @Override
    protected void procesarExcusa(Excusa excusa) {
        excusa.serProcesadaPor(this);
    }
}

