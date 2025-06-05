package com.excusas.encargado;

import com.excusas.excusa.Excusa;

public class EstrategiaNormal implements EstrategiaManejo {
    @Override
    public String ejecutar(Excusa excusa, Runnable procesarExcusa, Runnable pasarAlSiguiente) {
        procesarExcusa.run();
        return "Excusa procesada normalmente";
    }
}
