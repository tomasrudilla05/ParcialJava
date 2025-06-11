package com.excusas.excusas.complejas;

import com.excusas.excusas.MotivoExcusa;


public class ExcusaCompleja extends MotivoExcusa {

    public ExcusaCompleja() {
        super("Excusa compleja");
    }

    @Override
    public boolean puedeSerManejadoPorComplejo() {
        return true;
    }
}
