package com.excusas.excusas.inverosimiles;

import com.excusas.excusas.MotivoExcusa;
import com.excusas.excusas.Excusa;
import com.excusas.empleados.encargados.Recepcionista;
import com.excusas.empleados.encargados.SupervisorArea;
import com.excusas.empleados.encargados.GerenteRRHH;
import com.excusas.empleados.encargados.CEO;
import com.excusas.prontuario.AdministradorProntuarios;
import com.excusas.prontuario.Prontuario;


public class ExcusaInverosimil extends MotivoExcusa {

    public ExcusaInverosimil(String descripcion) {
        super(descripcion);
    }

    @Override
    public boolean puedeSerManejadaPor(Recepcionista recepcionista) {
        return false;
    }

    @Override
    public boolean puedeSerManejadaPor(SupervisorArea supervisor) {
        return false;
    }

    @Override
    public boolean puedeSerManejadaPor(GerenteRRHH gerente) {
        return false;
    }

    @Override
    public boolean puedeSerManejadaPor(CEO ceo) {
        return true;
    }

    @Override
    public void serProcesadaPor(Recepcionista recepcionista, Excusa excusa) {
        throw new UnsupportedOperationException("Excusa inverosímil no puede ser procesada por recepcionista");
    }

    @Override
    public void serProcesadaPor(SupervisorArea supervisor, Excusa excusa) {
        throw new UnsupportedOperationException("Excusa inverosímil no puede ser procesada por supervisor");
    }

    @Override
    public void serProcesadaPor(GerenteRRHH gerente, Excusa excusa) {
        throw new UnsupportedOperationException("Excusa inverosímil no puede ser procesada por gerente");
    }

    @Override
    public void serProcesadaPor(CEO ceo, Excusa excusa) {


        System.out.println("=== PROCESANDO EXCUSA INVEROSÍMIL ===");
        System.out.println("Empleado: " + excusa.getEmpleado().getNombre());
        System.out.println("Motivo: " + this.getDescripcion());
        System.out.println("Procesada por CEO: " + ceo.getNombre());
        System.out.println("Respuesta: Aprobado por creatividad");
        System.out.println("Creando prontuario...");


        ceo.getEmailSender().enviarEmail(
                excusa.getEmpleado().getEmail(),
                ceo.getEmail(),
                "Excusa aprobada",
                "Aprobado por creatividad"
        );


        Prontuario prontuario = new Prontuario(
                excusa.getEmpleado(),
                excusa,
                excusa.getEmpleado().getNumeroLegajo()
        );

        AdministradorProntuarios.getInstance().persistirProntuario(prontuario);
    }
}
