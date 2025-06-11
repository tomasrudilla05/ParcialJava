package com.excusas.prontuario;

import java.util.ArrayList;
import java.util.List;


public class AdministradorProntuarios extends ObservableBase {
    private static AdministradorProntuarios instancia;
    private List<Prontuario> prontuarios;

    private AdministradorProntuarios() {
        this.prontuarios = new ArrayList<>();
    }

    public static AdministradorProntuarios getInstance() {
        if (instancia == null) {
            instancia = new AdministradorProntuarios();
        }
        return instancia;
    }

    public void persistirProntuario(Prontuario prontuario) {
        System.out.println("=== PERSISTIENDO PRONTUARIO ===");
        System.out.println("Empleado: " + prontuario.getEmpleado().getNombre());
        System.out.println("Legajo: " + prontuario.getNumeroLegajo());

        prontuarios.add(prontuario);

        notificarObservers(prontuario);

        System.out.println("Prontuario persistido exitosamente");
    }

    public List<Prontuario> getProntuarios() {
        return new ArrayList<>(prontuarios);
    }

    public static void resetearInstancia() {
        instancia = null;
    }
}
