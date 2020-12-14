package br.com.Servidor;

import br.com.Comum.Servidor;
import br.com.Comum.Util;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class MainServer {

    public static void main(String[] args) throws Exception {
        Util.setCodigoBase(Servidor.class);

        OServidor servidor = new OServidor();

        Servidor remote = (Servidor) UnicastRemoteObject.exportObject(servidor, 8888);

        Registry registry = LocateRegistry.getRegistry();
        registry.rebind("Ponta", remote);


    }
}
