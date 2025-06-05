package com.excusas.prontuario;

import java.util.ArrayList;
import java.util.List;

public class AdministradorProntuarios {
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
        prontuarios.add(prontuario);
        System.out.println("Prontuario persistido: " + prontuario);
    }

    public List<Prontuario> getProntuarios() {
        return new ArrayList<>(prontuarios);
    }

    public int getCantidadProntuarios() {
        return prontuarios.size();
    }

    public void limpiarProntuarios() {
        prontuarios.clear();
    }

    public static void resetearInstancia() {
        if (instancia != null) {
            instancia.prontuarios.clear();
        }
    }
}
