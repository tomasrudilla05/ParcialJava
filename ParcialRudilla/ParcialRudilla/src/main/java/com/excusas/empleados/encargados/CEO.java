package com.excusas.empleados.encargados;

import com.excusas.excusas.Excusa;
import com.excusas.prontuario.AdministradorProntuarios;
import com.excusas.prontuario.Observer;
import com.excusas.prontuario.Prontuario;


public class CEO extends Encargado implements Observer {

    public CEO(String nombre, String email, int numeroLegajo) {
        super(nombre, email, numeroLegajo);
        AdministradorProntuarios.getInstance().agregarObserver(this);
    }

    @Override
    public boolean puedeManejarInverosimil() {
        return true;
    }

    @Override
    public void procesarExcusa(Excusa excusa) {
        System.out.println("CEO procesando excusa extremadamente inverosímil para: " + excusa.getEmpleado().getNombre());

        getEmailSender().enviarEmail(
                excusa.getEmpleado().getEmail(),
                this.getEmail(),
                "Respuesta CEO",
                "Aprobado por creatividad"
        );
        System.out.println("Respuesta: Aprobado por creatividad");

        Prontuario prontuario = new Prontuario(
                excusa.getEmpleado(),
                excusa,
                excusa.getEmpleado().getLegajo()
        );

        AdministradorProntuarios.getInstance().persistirProntuario(prontuario);
    }

    @Override
    public void actualizar(Prontuario prontuario) {
        System.out.println("CEO " + this.getNombre() + " notificado sobre nuevo prontuario de: " +
                prontuario.getEmpleado().getNombre());
    }
}
