package com.excusas.empleados.encargados;

import com.excusas.excusas.Excusa;
import com.excusas.excusas.moderadas.ExcusaModerada;


public class SupervisorArea extends Encargado {

    public SupervisorArea(String nombre, String email, int numeroLegajo) {
        super(nombre, email, numeroLegajo);
    }

    @Override
    public boolean puedeManejarModerado() {
        return true;
    }

    @Override
    public void procesarExcusa(Excusa excusa) {
        System.out.println("Supervisor de Área procesando excusa moderada para: " + excusa.getEmpleado().getNombre());

        ExcusaModerada excusaModerada = (ExcusaModerada) excusa.getMotivo();
        excusaModerada.procesarCon(this, excusa);
    }
}
