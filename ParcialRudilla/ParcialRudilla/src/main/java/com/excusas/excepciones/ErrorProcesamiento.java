package com.excusas.excepciones;


public class ErrorProcesamiento extends ExcusasSAException {

    public ErrorProcesamiento(String codigoError, String mensaje) {
        super(codigoError, mensaje);
    }

    @Override
    public String getTipoError() {
        return "Error de Procesamiento";
    }

    public static ErrorProcesamiento excusaNoManejable(String tipoExcusa) {
        return new ErrorProcesamiento("PROC_001",
                "No se puede procesar excusa de tipo: " + tipoExcusa);
    }

    public static ErrorProcesamiento empleadoInvalido(String nombre) {
        return new ErrorProcesamiento("PROC_002",
                "Empleado inválido: " + nombre);
    }
}
