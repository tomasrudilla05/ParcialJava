package com.excusas.encargado;

import com.excusas.excusa.Excusa;

public class EstrategiaVago implements EstrategiaManejo {
    @Override
    public String ejecutar(Excusa excusa, Runnable procesarExcusa, Runnable pasarAlSiguiente) {
        pasarAlSiguiente.run();
        return "Excusa pasada al siguiente (modo vago)";
    }
}
