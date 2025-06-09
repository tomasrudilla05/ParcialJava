package com.excusas.interfaces;


public interface IEncargadoEmpleado extends IEmpleado {
    void setEstrategia(com.excusas.empleados.encargados.estrategias.EstrategiaManejo estrategia);
    com.excusas.mail.EmailSender getEmailSender();
}
