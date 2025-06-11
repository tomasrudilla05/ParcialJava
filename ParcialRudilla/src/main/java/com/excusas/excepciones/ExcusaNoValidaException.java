package com.excusas.excepciones;


public class ExcusaNoValidaException extends Exception {
    private final String tipoExcusa;
    private final String razonRechazo;

    public ExcusaNoValidaException(String tipoExcusa, String razonRechazo) {
        super(String.format("Excusa '%s' no válida: %s", tipoExcusa, razonRechazo));
        this.tipoExcusa = tipoExcusa;
        this.razonRechazo = razonRechazo;
    }

    public String getTipoExcusa() { return tipoExcusa; }
    public String getRazonRechazo() { return razonRechazo; }
}
