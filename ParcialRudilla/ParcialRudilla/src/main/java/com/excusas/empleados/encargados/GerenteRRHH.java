package com.excusas.empleados.encargados;

import com.excusas.excusas.Excusa;

public class GerenteRRHH extends Encargado {

    public GerenteRRHH(String nombre, String email, int numeroLegajo) {
        super(nombre, email, numeroLegajo);
    }

    @Override
    public boolean puedeManejarComplejo() {
        return true;
    }

    @Override
    public void procesarExcusa(Excusa excusa) {
        System.out.println("Gerente de RRHH procesando excusa compleja para: " + excusa.getEmpleado().getNombre());
        System.out.println("Excusa: " + excusa.getMotivo().getDescripcion());

        getEmailSender().enviarEmail(
                excusa.getEmpleado().getEmail(),
                this.getEmail(),
                "Excusa aceptada",
                "Su excusa ha sido aceptada después de una evaluación detallada"
        );
    }
}
