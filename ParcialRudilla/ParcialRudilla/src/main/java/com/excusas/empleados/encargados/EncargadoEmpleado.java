package com.excusas.empleados.encargados;

import com.excusas.empleados.Empleado;
import com.excusas.excusas.Excusa;
import com.excusas.mail.EmailSender;

public abstract class EncargadoEmpleado extends Encargado {
    protected Empleado empleado;
    protected EmailSender emailSender;

    public EncargadoEmpleado(String nombre, String email, int numeroLegajo) {
        super();
        this.empleado = new Empleado(nombre, email, numeroLegajo);
    }

    @Override
    public String getEmail() {
        return empleado.getEmail();
    }

    public String getNombre() {
        return empleado.getNombre();
    }

    public int getNumeroLegajo() {
        return empleado.getNumeroLegajo();
    }

    public abstract EmailSender getEmailSender();

    public abstract void procesarExcusaTrivial(Excusa excusa);
    public abstract void procesarExcusaModerada(Excusa excusa);
    public abstract void procesarExcusaCompleja(Excusa excusa);
    public abstract void procesarExcusaInverosimil(Excusa excusa);
}
