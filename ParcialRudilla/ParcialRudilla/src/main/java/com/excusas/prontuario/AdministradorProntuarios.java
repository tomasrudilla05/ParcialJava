package com.excusas.prontuario;

import com.excusas.mail.EmailSender;
import com.excusas.mail.EmailSenderImpl;
import java.util.ArrayList;
import java.util.List;


public class AdministradorProntuarios extends ObservableBase {
    private static AdministradorProntuarios instancia;
    private List<Prontuario> prontuarios;
    private List<String> emailsCEOs;
    private EmailSender emailSender;

    private AdministradorProntuarios() {
        this.prontuarios = new ArrayList<>();
        this.emailsCEOs = new ArrayList<>();
        this.emailSender = new EmailSenderImpl();
    }

    public static AdministradorProntuarios getInstance() {
        if (instancia == null) {
            instancia = new AdministradorProntuarios();
        }
        return instancia;
    }

    public void agregarCEO(String emailCEO) {
        emailsCEOs.add(emailCEO);
    }


    public void persistirProntuario(Prontuario prontuario) {
        System.out.println("=== PERSISTIENDO PRONTUARIO ===");
        System.out.println("Empleado: " + prontuario.getEmpleado().getNombre());
        System.out.println("Legajo: " + prontuario.getNumeroLegajo());

        prontuarios.add(prontuario);

        for (String emailCEO : emailsCEOs) {
            emailSender.enviarEmail(
                    emailCEO,
                    "admin@excusas-sa.com",
                    "Nuevo prontuario",
                    "Se ha creado un nuevo prontuario para " +
                            prontuario.getEmpleado().getNombre()
            );
        }

        System.out.println("Emails enviados a " + emailsCEOs.size() + " CEOs");
        System.out.println("Prontuario persistido exitosamente");

        notificarObservers("Nuevo prontuario para " + prontuario.getEmpleado().getNombre());
    }

    public List<Prontuario> getProntuarios() {
        return new ArrayList<>(prontuarios);
    }

    public static void resetearInstancia() {
        instancia = null;
    }
}
