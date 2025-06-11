package com.excusas.empleados;

import com.excusas.excusas.Excusa;
import com.excusas.excusas.MotivoExcusa;


public interface IEmpleado {
    String getNombre();
    String getEmail();
    int getLegajo();
    Excusa crearExcusa(MotivoExcusa motivo, String descripcion);
}
