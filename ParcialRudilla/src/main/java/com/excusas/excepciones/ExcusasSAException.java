package com.excusas.excepciones;


public abstract class ExcusasSAException extends RuntimeException {
    private final String codigoError;

    protected ExcusasSAException(String codigoError, String mensaje) {
        super(mensaje);
        this.codigoError = codigoError;
    }

    protected ExcusasSAException(String codigoError, String mensaje, Throwable causa) {
        super(mensaje, causa);
        this.codigoError = codigoError;
    }

    public String getCodigoError() {
        return codigoError;
    }

    public abstract String getTipoError();
}
