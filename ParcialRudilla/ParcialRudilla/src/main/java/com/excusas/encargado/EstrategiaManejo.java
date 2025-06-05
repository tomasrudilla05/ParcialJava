package com.excusas.encargado;

import com.excusas.excusa.Excusa;

public interface EstrategiaManejo {
    String ejecutar(Excusa excusa, Runnable procesarExcusa, Runnable pasarAlSiguiente);
}
