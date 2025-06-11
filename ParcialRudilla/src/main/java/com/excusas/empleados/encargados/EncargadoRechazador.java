package com.excusas.empleados.encargados;

import com.excusas.empleados.Empleado;
import com.excusas.excusas.Excusa;
import com.excusas.excusas.MotivoExcusa;
import com.excusas.mail.EmailSender;
import com.excusas.mail.EmailSenderImpl;
import com.excusas.empleados.encargados.estrategias.EstrategiaManejo;
import com.excusas.empleados.encargados.estrategias.EstrategiaNormal;


public class EncargadoRechazador implements IEncargado {
    private EmailSender emailSender;
    private String nombre;
    private String email;
    private int legajo;
    private EstrategiaManejo estrategia;

    public EncargadoRechazador() {
        this.emailSender = new EmailSenderImpl();
        this.nombre = "Rechazador";
        this.email = "rechazos@excusas-sa.com";
        this.legajo = 9999;
        this.estrategia = new EstrategiaNormal();
    }

    @Override
    public void manejarExcusa(Excusa excusa) {
        System.out.println("=== EXCUSA RECHAZADA ===");
        System.out.println("Empleado: " + excusa.getEmpleado().getNombre());
        System.out.println("Motivo: " + excusa.getDescripcion());
        System.out.println("Respuesta: Excusa rechazada: necesitamos pruebas contundentes");

        emailSender.enviarEmail(
                excusa.getEmpleado().getEmail(),
                this.getEmail(),
                "Excusa rechazada",
                "Necesitamos pruebas contundentes para su excusa: " +
                        excusa.getDescripcion()
        );
    }

    @Override
    public void setSiguiente(IEncargado siguiente) {
    }

    @Override
    public IEncargado getSiguiente() {
        return null;
    }

    @Override
    public void ejecutarProcesamiento(Excusa excusa) {
        manejarExcusa(excusa);
    }

    @Override
    public void procesarExcusa(Excusa excusa) {
    }

    @Override
    public boolean puedeManejarTrivial() {
        return false;
    }

    @Override
    public boolean puedeManejarModerado() {
        return false;
    }

    @Override
    public boolean puedeManejarComplejo() {
        return false;
    }

    @Override
    public boolean puedeManejarInverosimil() {
        return false;
    }

    @Override
    public void setEstrategia(EstrategiaManejo estrategia) {
        this.estrategia = estrategia;
    }

    @Override
    public EstrategiaManejo getEstrategia() {
        return this.estrategia;
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public int getLegajo() {
        return legajo;
    }

    @Override
    public Excusa crearExcusa(MotivoExcusa motivo, String descripcion) {
        return new Excusa(new Empleado(nombre, email, legajo), motivo, descripcion);
    }

    @Override
    public EmailSender getEmailSender() {
        return emailSender;
    }
}
