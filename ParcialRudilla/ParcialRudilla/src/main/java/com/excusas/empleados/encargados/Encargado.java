package com.excusas.empleados.encargados;

import com.excusas.excusas.Excusa;


public abstract class Encargado implements ManejadoraExcusas {
    protected Encargado siguiente;

    public void setSiguiente(Encargado siguiente) {
        this.siguiente = siguiente;
    }

    protected void delegarAlSiguiente(Excusa excusa) {
        if (siguiente != null) {
            System.out.println("Delegando al siguiente encargado...");
            siguiente.manejarExcusa(excusa);
        } else {
            System.out.println("No hay más encargados en la línea");
        }
    }

    public abstract String getEmail();
}
