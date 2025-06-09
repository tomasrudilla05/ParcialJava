package com.excusas.empleados.encargados;

import com.excusas.excusas.Excusa;
import com.excusas.excepciones.ExcusasSAError;
import com.excusas.excepciones.TipoError;


public class LineaEncargados {
    private static LineaEncargados instancia;
    private Encargado primerEncargado;
    private boolean configurada = false;

    private LineaEncargados() {}

    public static LineaEncargados getInstance() {
        if (instancia == null) {
            instancia = new LineaEncargados();
            instancia.configurarAutomaticamente();
        }
        return instancia;
    }


    private void configurarAutomaticamente() {
        if (!configurada) {
            Recepcionista recepcionista = new Recepcionista("Ana García", "recepcion@excusas-sa.com", 1001);
            SupervisorArea supervisor = new SupervisorArea("Carlos López", "supervisor@excusas-sa.com", 1002);
            GerenteRRHH gerente = new GerenteRRHH("María Rodríguez", "gerente@excusas-sa.com", 1003);
            CEO ceo = new CEO("Roberto Silva", "ceo@excusas-sa.com", 1004);
            EncargadoRechazador rechazador = new EncargadoRechazador();

            recepcionista.setSiguiente(supervisor);
            supervisor.setSiguiente(gerente);
            gerente.setSiguiente(ceo);
            ceo.setSiguiente(rechazador);

            this.primerEncargado = recepcionista;
            this.configurada = true;

            System.out.println("Línea de encargados configurada automáticamente");
            System.out.println("Orden: Recepcionista → Supervisor → Gerente → CEO → Rechazador");
        }
    }

    public void configurar(Encargado primerEncargado) {
        if (primerEncargado == null) {
            throw new ExcusasSAError(TipoError.ENCARGADO_INVALIDO,
                    "El primer encargado no puede ser null");
        }
        this.primerEncargado = primerEncargado;
        this.configurada = true;
    }

    public void procesarExcusa(Excusa excusa) {
        if (!configurada) {
            configurarAutomaticamente();
        }
        if (excusa == null) {
            throw new ExcusasSAError(TipoError.EXCUSA_NO_MANEJABLE,
                    "La excusa no puede ser null");
        }
        primerEncargado.manejarExcusa(excusa);
    }

    public boolean estaConfigurada() {
        return configurada;
    }

    public static void resetearInstancia() {
        instancia = null;
    }
}
