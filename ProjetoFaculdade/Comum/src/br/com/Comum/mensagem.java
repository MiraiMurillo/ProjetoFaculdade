package br.com.Comum;

import java.io.Serializable;

public class mensagem implements Serializable {

    private String corpo, remetente;

    public mensagem(String corpo, String remetente){
        this.corpo = corpo;
        this.remetente = remetente;
    }

    public String getCorpo() {
        return corpo;
    }

    public String getRemetente() {
        return remetente;
    }
}
