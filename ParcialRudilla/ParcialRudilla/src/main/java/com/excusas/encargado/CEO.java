package com.excusas.encargado;

import com.excusas.excusa.Excusa;
import com.excusas.excusa.Inverosimil;
import com.excusas.mail.EmailSender;
import com.excusas.prontuario.Prontuario;
import com.excusas.prontuario.AdministradorProntuarios;

import java.util.ArrayList;
import java.util.List;

public class CEO extends EncargadoBase {
    private static List<ObservadorCEO> observadores = new ArrayList<>();

    public CEO(String nombre, String email, int numeroLegajo, EmailSender emailSender) {
        super(nombre, email, numeroLegajo, emailSender);
        observadores.add(new ObservadorCEO() {
            @Override
            public void notificarNuevoProntuario(Prontuario prontuario) {
                if (!prontuario.getEmpleado().equals(CEO.this)) {
                    emailSender.enviarEmail(
                            CEO.this.getEmail(),
                            "sistema@excusas-sa.com",
                            "Nuevo Prontuario Creado",
                            "Se ha creado un nuevo prontuario para: " + prontuario.getEmpleado().getNombre()
                    );
                }
            }
        });
    }

    @Override
    protected boolean puedeProcesar(Excusa excusa) {
        return excusa.getMotivo() instanceof Inverosimil;
    }

    @Override
    protected String procesarExcusa(Excusa excusa) {
        emailSender.enviarEmail(
                excusa.getEmpleado().getEmail(),
                this.getEmail(),
                "Excusa Aprobada",
                "Aprobado por creatividad"
        );

        Prontuario prontuario = new Prontuario(
                excusa.getEmpleado(),
                excusa,
                excusa.getEmpleado().getNumeroLegajo()
        );

        AdministradorProntuarios.getInstance().persistirProntuario(prontuario);

        notificarObservadores(prontuario);

        return "Excusa extremadamente inverosímil aceptada por CEO - Prontuario creado";
    }

    private void notificarObservadores(Prontuario prontuario) {
        for (ObservadorCEO observador : observadores) {
            observador.notificarNuevoProntuario(prontuario);
        }
    }

    public static void agregarObservador(ObservadorCEO observador) {
        observadores.add(observador);
    }
}
