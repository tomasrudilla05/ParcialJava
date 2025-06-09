package com.excusas.excepciones;


public class ErrorConfiguracion extends ExcusasSAException {

    public ErrorConfiguracion(String mensaje) {
        super("CONFIG_001", mensaje);
    }

    @Override
    public String getTipoError() {
        return "Error de Configuración";
    }

    public static ErrorConfiguracion lineaNoConfigurada() {
        return new ErrorConfiguracion("La línea de encargados no está configurada");
    }

    public static ErrorConfiguracion encargadoInvalido(String nombre) {
        return new ErrorConfiguracion("Encargado inválido: " + nombre);
    }
}
