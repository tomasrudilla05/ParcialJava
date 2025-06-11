package com.excusas.excusas.inverosimiles;

import com.excusas.excusas.MotivoExcusa;


public class ExcusaInverosimil extends MotivoExcusa {

    public ExcusaInverosimil() {
        super("Excusa inverosímil");
    }

    @Override
    public boolean puedeSerManejadoPorInverosimil() {
        return true;
    }
}
