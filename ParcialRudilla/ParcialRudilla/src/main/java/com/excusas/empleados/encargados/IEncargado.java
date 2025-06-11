package com.excusas.empleados.encargados;

import com.excusas.empleados.IEmpleado;
import com.excusas.excusas.Excusa;
import com.excusas.mail.EmailSender;
import com.excusas.empleados.encargados.estrategias.EstrategiaManejo;


public interface IEncargado extends IEmpleado, IManejadoraExcusas {
    void setSiguiente(IEncargado siguiente);
    IEncargado getSiguiente();
    void ejecutarProcesamiento(Excusa excusa);
    void procesarExcusa(Excusa excusa);
    boolean puedeManejarTrivial();
    boolean puedeManejarModerado();
    boolean puedeManejarComplejo();
    boolean puedeManejarInverosimil();
    EmailSender getEmailSender();
    void setEstrategia(EstrategiaManejo estrategia);
    EstrategiaManejo getEstrategia();
}
