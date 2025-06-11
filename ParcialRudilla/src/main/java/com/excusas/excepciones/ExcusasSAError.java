package com.excusas.excepciones;


public class ExcusasSAError extends RuntimeException {
    private final TipoError tipoError;
    private final String contexto;

    public ExcusasSAError(TipoError tipoError, String contexto) {
        super(String.format("[%s] %s - %s",
                tipoError.getCodigo(),
                tipoError.getDescripcion(),
                contexto));
        this.tipoError = tipoError;
        this.contexto = contexto;
    }

    public TipoError getTipoError() { return tipoError; }
    public String getContexto() { return contexto; }

    public static ExcusasSAError lineaNoConfigurada() {
        return new ExcusasSAError(TipoError.CONFIGURACION_LINEA,
                "Debe configurar la línea antes de procesar excusas");
    }

    public static ExcusasSAError excusaNoManejable(String tipoExcusa) {
        return new ExcusasSAError(TipoError.EXCUSA_NO_MANEJABLE,
                "Tipo: " + tipoExcusa);
    }
}
