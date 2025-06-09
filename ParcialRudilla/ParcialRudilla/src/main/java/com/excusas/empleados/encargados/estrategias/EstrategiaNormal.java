package com.excusas.empleados.encargados.estrategias;

import com.excusas.excusas.Excusa;
import com.excusas.empleados.encargados.Encargado;

public class EstrategiaNormal implements EstrategiaManejo {

    @Override
    public boolean debeEvaluar(Encargado encargado, Excusa excusa) {
        return true;
    }
}
