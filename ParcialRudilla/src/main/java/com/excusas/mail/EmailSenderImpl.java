package com.excusas.mail;

public class EmailSenderImpl implements EmailSender {

    @Override
    public void enviarEmail(String unEmailDestino, String unEmailOrigen, String unAsunto, String unCuerpo) {
        System.out.println("=== EMAIL ENVIADO ===");
        System.out.println("De: " + unEmailOrigen);
        System.out.println("Para: " + unEmailDestino);
        System.out.println("Asunto: " + unAsunto);
        System.out.println("Cuerpo: " + unCuerpo);
        System.out.println("====================");
    }
}
