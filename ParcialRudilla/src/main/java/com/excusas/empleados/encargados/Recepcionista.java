package com.excusas.empleados.encargados;

import com.excusas.excusas.Excusa;
import com.excusas.mail.EmailSenderImpl;


public class Recepcionista extends Encargado {

    public Recepcionista(String nombre, String email, int numeroLegajo) {
        super(nombre, email, numeroLegajo);
    }

    @Override
    public boolean puedeManejarTrivial() {
        return true;
    }

    @Override
    public void procesarExcusa(Excusa excusa) {
        System.out.println("=== PROCESANDO EXCUSA TRIVIAL ===");
        System.out.println("Recepcionista procesando excusa trivial para: " + excusa.getEmpleado().getNombre());
        System.out.println("Motivo: " + excusa.getMotivo().getDescripcion());
        System.out.println("Procesada por: " + this.getNombre());

        new EmailSenderImpl().enviarEmail(
                excusa.getEmpleado().getEmail(),
                this.getEmail(),
                "motivo demora",
                "la licencia fue aceptada"
        );
    }
}
