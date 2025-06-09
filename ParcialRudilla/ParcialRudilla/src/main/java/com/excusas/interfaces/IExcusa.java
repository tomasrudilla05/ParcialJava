package com.excusas.interfaces;

import com.excusas.empleados.Empleado;
import com.excusas.excusas.MotivoExcusa;
import com.excusas.empleados.encargados.*;


public interface IExcusa {
    Empleado getEmpleado();
    MotivoExcusa getMotivo();

    boolean puedeSerManejadaPor(Recepcionista recepcionista);
    boolean puedeSerManejadaPor(SupervisorArea supervisor);
    boolean puedeSerManejadaPor(GerenteRRHH gerente);
    boolean puedeSerManejadaPor(CEO ceo);

    void serProcesadaPor(Recepcionista recepcionista);
    void serProcesadaPor(SupervisorArea supervisor);
    void serProcesadaPor(GerenteRRHH gerente);
    void serProcesadaPor(CEO ceo);
}
