package com.excusas.excusas.triviales;

import com.excusas.excusas.MotivoExcusa;


public class ExcusaTrivial extends MotivoExcusa {

    public ExcusaTrivial() {
        super("Excusa trivial");
    }

    @Override
    public boolean puedeSerManejadoPorTrivial() {
        return true;
    }
}
