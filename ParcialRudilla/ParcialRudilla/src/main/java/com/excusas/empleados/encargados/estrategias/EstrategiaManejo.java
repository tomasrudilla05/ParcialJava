package com.excusas.empleados.encargados.estrategias;

import com.excusas.excusas.Excusa;
import com.excusas.empleados.encargados.Encargado;

public interface EstrategiaManejo {
    boolean debeEvaluar(Encargado encargado, Excusa excusa);
}
