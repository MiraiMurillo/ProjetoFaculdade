package br.com.Comum;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface Servidor extends Remote {

    int autenticar(String nome) throws RemoteException;

    int juntar(String nome, int sessao) throws RemoteException;

    void enviar(String carta, int sessaoDe, int sessaoA) throws RemoteException;

    List<mensagem> receber(int sessao) throws RemoteException;

    void limparBuffer (int sessao) throws RemoteException;


}
