package com.excusas.testutils;

import com.excusas.mail.EmailSender;
import java.util.ArrayList;
import java.util.List;

public class EmailSenderTest implements EmailSender {
    private List<EmailEnviado> emailsEnviados = new ArrayList<>();

    @Override
    public void enviarEmail(String unEmailDestino, String unEmailOrigen, String unAsunto, String unCuerpo) {
        emailsEnviados.add(new EmailEnviado(unEmailDestino, unEmailOrigen, unAsunto, unCuerpo));
    }

    public List<EmailEnviado> getEmailsEnviados() {
        return new ArrayList<>(emailsEnviados);
    }

    public int getCantidadEmailsEnviados() {
        return emailsEnviados.size();
    }

    public EmailEnviado getUltimoEmail() {
        if (emailsEnviados.isEmpty()) {
            return null;
        }
        return emailsEnviados.get(emailsEnviados.size() - 1);
    }

    public boolean seEnvioEmailA(String destinatario) {
        return emailsEnviados.stream()
                .anyMatch(email -> email.getDestino().equals(destinatario));
    }

    public boolean seEnvioEmailConAsunto(String asunto) {
        return emailsEnviados.stream()
                .anyMatch(email -> email.getAsunto().equals(asunto));
    }

    public void limpiar() {
        emailsEnviados.clear();
    }

    public static class EmailEnviado {
        private String destino;
        private String origen;
        private String asunto;
        private String cuerpo;

        public EmailEnviado(String destino, String origen, String asunto, String cuerpo) {
            this.destino = destino;
            this.origen = origen;
            this.asunto = asunto;
            this.cuerpo = cuerpo;
        }

        public String getDestino() { return destino; }
        public String getOrigen() { return origen; }
        public String getAsunto() { return asunto; }
        public String getCuerpo() { return cuerpo; }

        @Override
        public String toString() {
            return "EmailEnviado{" +
                    "destino='" + destino + '\'' +
                    ", origen='" + origen + '\'' +
                    ", asunto='" + asunto + '\'' +
                    ", cuerpo='" + cuerpo + '\'' +
                    '}';
        }
    }
}
