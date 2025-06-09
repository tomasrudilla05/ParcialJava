package com.excusas.excusas.moderadas;

import com.excusas.excusas.Excusa;
import com.excusas.empleados.encargados.SupervisorArea;


public class SeCortoLuz extends ExcusaModerada {

    public SeCortoLuz() {
        super("se cortó la luz en todo el barrio");
    }

    @Override
    public void serProcesadaPor(SupervisorArea supervisor, Excusa excusa) {
        System.out.println("=== PROCESANDO EXCUSA: CORTE DE LUZ ===");
        System.out.println("Empleado: " + excusa.getEmpleado().getNombre());
        System.out.println("Motivo: " + this.getDescripcion());
        System.out.println("Procesada por: " + supervisor.getNombre());
        System.out.println("Enviando consulta a EDESUR...");

        supervisor.getEmailSender().enviarEmail(
                "EDESUR@mailfake.com.ar",
                supervisor.getEmail(),
                "Consulta corte de luz",
                "¿Hubo corte de luz en la zona de " + excusa.getEmpleado().getNombre() + "?"
        );
    }
}
