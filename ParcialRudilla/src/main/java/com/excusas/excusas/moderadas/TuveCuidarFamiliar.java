package com.excusas.excusas.moderadas;

import com.excusas.excusas.Excusa;
import com.excusas.empleados.encargados.SupervisorArea;


public class TuveCuidarFamiliar extends ExcusaModerada {

    public TuveCuidarFamiliar() {
        super();
        this.descripcion = "Tuve que cuidar a un familiar";
    }

    @Override
    public void serProcesadaPor(SupervisorArea supervisor, Excusa excusa) {
        System.out.println("=== PROCESANDO EXCUSA: CUIDADO FAMILIAR ===");
        System.out.println("Empleado: " + excusa.getEmpleado().getNombre());
        System.out.println("Motivo: " + excusa.getDescripcion());
        System.out.println("Procesada por: " + supervisor.getNombre());
        System.out.println("Enviando consulta al empleado...");

        supervisor.getEmailSender().enviarEmail(
                excusa.getEmpleado().getEmail(),
                supervisor.getEmail(),
                "Consulta familiar",
                "¿Todo está bien?"
        );
    }
}
