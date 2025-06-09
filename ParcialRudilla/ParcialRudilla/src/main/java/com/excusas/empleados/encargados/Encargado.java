package com.excusas.empleados.encargados;

import com.excusas.excusas.Excusa;
import com.excusas.empleados.encargados.estrategias.EstrategiaManejo;
import com.excusas.empleados.encargados.estrategias.EstrategiaNormal;


public abstract class Encargado implements ManejadoraExcusas {
    protected Encargado siguiente;
    protected EstrategiaManejo estrategia;

    public Encargado() {
        this.estrategia = new EstrategiaNormal();
    }

    public void setSiguiente(Encargado siguiente) {
        this.siguiente = siguiente;
    }

    public void setEstrategia(EstrategiaManejo estrategia) {
        this.estrategia = estrategia;
    }


    @Override
    public final void manejarExcusa(Excusa unaExcusa) {
        System.out.println("--- Evaluando excusa en: " + this.getClass().getSimpleName() + " ---");

        if (estrategia.debeEvaluar(this, unaExcusa)) {

            if (puedeManear(unaExcusa)) {

                procesarExcusa(unaExcusa);
            } else {

                delegarAlSiguiente(unaExcusa);
            }
        } else {

            delegarAlSiguiente(unaExcusa);
        }
    }

    protected final void delegarAlSiguiente(Excusa excusa) {
        if (siguiente != null) {
            System.out.println("Delegando al siguiente encargado...");
            siguiente.manejarExcusa(excusa);
        } else {
            System.out.println("No hay más encargados en la línea");
        }

    }


    protected abstract boolean puedeManear(Excusa excusa);
    protected abstract void procesarExcusa(Excusa excusa);

    public abstract String getEmail();
}
