package com.excusas.empleados.encargados;

import com.excusas.excusas.Excusa;

public class EncargadoRechazador extends Encargado {

    @Override
    protected boolean puedeManear(Excusa excusa) {
        return true;
    }

    @Override
    protected void procesarExcusa(Excusa excusa) {

        System.out.println("=== EXCUSA RECHAZADA ===");
        System.out.println("Empleado: " + excusa.getEmpleado().getNombre());
        System.out.println("Motivo: " + excusa.getMotivo().getDescripcion());
        System.out.println("Respuesta: Excusa rechazada: necesitamos pruebas contundentes");
    }

    @Override
    public String getEmail() {
        return "rechazos@excusas-sa.com";
    }
}
