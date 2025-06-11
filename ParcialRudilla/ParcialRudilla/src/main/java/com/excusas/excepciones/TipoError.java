package com.excusas.excepciones;

public enum TipoError {
    CONFIGURACION_LINEA("CONFIG_001", "Error en configuración de línea"),
    ENCARGADO_INVALIDO("CONFIG_002", "Encargado inválido"),
    EXCUSA_NO_MANEJABLE("PROC_001", "Excusa no puede ser manejada"),
    EMPLEADO_INVALIDO("PROC_002", "Datos de empleado inválidos"),
    PRONTUARIO_ERROR("DATA_001", "Error en prontuario");

    private final String codigo;
    private final String descripcion;

    TipoError(String codigo, String descripcion) {
        this.codigo = codigo;
        this.descripcion = descripcion;
    }

    public String getCodigo() { return codigo; }
    public String getDescripcion() { return descripcion; }
}
