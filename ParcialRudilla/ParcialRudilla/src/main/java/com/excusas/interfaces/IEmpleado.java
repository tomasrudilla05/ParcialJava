package com.excusas.interfaces;

import com.excusas.excusas.Excusa;


public interface IEmpleado {
    String getNombre();
    String getEmail();
    int getNumeroLegajo();
    IExcusa generarExcusa(String motivo);
    void enviarExcusa(IExcusa excusa);
}
