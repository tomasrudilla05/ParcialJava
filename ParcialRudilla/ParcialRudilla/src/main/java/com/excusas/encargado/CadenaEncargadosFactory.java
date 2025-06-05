package com.excusas.encargado;

import com.excusas.mail.EmailSender;

public class CadenaEncargadosFactory {

    public static ManejadorExcusas crearCadenaCompleta(EmailSender emailSender) {
        Recepcionista recepcionista = new Recepcionista("Ana García", "ana@excusas-sa.com", 1001, emailSender);
        SupervisorArea supervisor = new SupervisorArea("Carlos López", "carlos@excusas-sa.com", 1002, emailSender);
        GerenteRRHH gerente = new GerenteRRHH("María Rodríguez", "maria@excusas-sa.com", 1003, emailSender);
        CEO ceo = new CEO("Roberto Silva", "roberto@excusas-sa.com", 1004, emailSender);
        EncargadoPorDefecto porDefecto = new EncargadoPorDefecto("Sistema", "sistema@excusas-sa.com", 9999, emailSender);

        recepcionista.setSiguienteEncargado(supervisor);
        supervisor.setSiguienteEncargado(gerente);
        gerente.setSiguienteEncargado(ceo);
        ceo.setSiguienteEncargado(porDefecto);

        return recepcionista;
    }
}
