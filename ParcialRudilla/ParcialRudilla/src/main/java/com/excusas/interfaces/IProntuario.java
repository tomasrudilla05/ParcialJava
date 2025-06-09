package com.excusas.interfaces;

import com.excusas.empleados.Empleado;


public interface IProntuario {
    Empleado getEmpleado();
    IExcusa getExcusa();
    int getNumeroLegajo();
}
