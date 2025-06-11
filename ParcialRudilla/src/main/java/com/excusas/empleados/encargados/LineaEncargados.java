package com.excusas.empleados.encargados;

import com.excusas.excusas.Excusa;

public class LineaEncargados {
    private static LineaEncargados instancia;
    private IEncargado primerEncargado;

    private LineaEncargados() {
        CEO ceo = new CEO("Roberto Silva", "ceo@excusas-sa.com", 1004);
        GerenteRRHH gerente = new GerenteRRHH("María Rodríguez", "gerente@excusas-sa.com", 1003);
        SupervisorArea supervisor = new SupervisorArea("Carlos López", "supervisor@excusas-sa.com", 1002);
        Recepcionista recepcionista = new Recepcionista("Ana García", "recepcion@excusas-sa.com", 1001);

        recepcionista.setSiguiente(supervisor);
        supervisor.setSiguiente(gerente);
        gerente.setSiguiente(ceo);
        ceo.setSiguiente(new EncargadoRechazador());

        this.primerEncargado = recepcionista;
    }

    public static LineaEncargados getInstance() {
        if (instancia == null) {
            instancia = new LineaEncargados();
        }
        return instancia;
    }

    public void manejarExcusa(Excusa excusa) {
        primerEncargado.manejarExcusa(excusa);
    }

    public static void resetearInstancia() {
        instancia = null;
    }
}
