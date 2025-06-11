package com.excusas.empleados.encargados.estrategias;

import com.excusas.excusas.Excusa;
import com.excusas.empleados.encargados.Encargado;


public class EstrategiaVago implements EstrategiaManejo {

    @Override
    public boolean debeEvaluar(Encargado encargado, Excusa excusa) {
        System.out.println("*** ESTRATEGIA VAGO ACTIVADA ***");
        System.out.println("Pasando directamente al siguiente sin evaluar...");
        return false;
    }
}
