package com.excusas.prontuario;

import com.excusas.empleados.Empleado;
import com.excusas.excusas.Excusa;


public interface IProntuario {
    Empleado getEmpleado();
    Excusa getExcusa();
    int getNumeroLegajo();
}
