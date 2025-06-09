package com.excusas.empleados;

import com.excusas.excusas.*;
import com.excusas.excusas.triviales.*;
import com.excusas.excusas.moderadas.*;
import com.excusas.excusas.complejas.*;
import com.excusas.excusas.inverosimiles.*;
import com.excusas.empleados.encargados.LineaEncargados;
import com.excusas.interfaces.IEmpleado;
import com.excusas.interfaces.IExcusa;


public class Empleado implements IEmpleado {
    private String nombre;
    private String email;
    private int numeroLegajo;

    public Empleado(String nombre, String email, int numeroLegajo) {
        this.nombre = nombre;
        this.email = email;
        this.numeroLegajo = numeroLegajo;
    }


    @Override
    public IExcusa generarExcusa(String motivo) {
        return new Excusa(this, determinarTipoMotivo(motivo));
    }


    @Override
    public void enviarExcusa(IExcusa excusa) {
        LineaEncargados.getInstance().procesarExcusa((Excusa) excusa);
    }


    private MotivoExcusa determinarTipoMotivo(String descripcion) {
        descripcion = descripcion.toLowerCase();

        if (descripcion.contains("luz")) {
            return new SeCortoLuz();
        }
        else if (descripcion.contains("familiar")) {
            return new TuveCuidarFamiliar();
        }
        else if (descripcion.contains("dormido") || descripcion.contains("colectivo")) {
            return new ExcusaTrivial(descripcion);
        }
        else if (descripcion.contains("aliens") || descripcion.contains("paloma") ||
                descripcion.contains("bicicleta")) {
            return new ExcusaCompleja(descripcion);
        }
        else {
            return new ExcusaInverosimil(descripcion);
        }
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public int getNumeroLegajo() {
        return numeroLegajo;
    }
}
