package br.com.Comum;

public class Util {

    public static  final  String CODEBASE = "java.rmi.server.codebase";

    public static void setCodigoBase(Class<?> c){
      String rota = c.getProtectionDomain().getCodeSource()
                    .getLocation().toString();

      String path = System.getProperty(CODEBASE);

      if (path!=null && !path.isEmpty()){
          rota = path + " "+ rota;
      }
      System.setProperty(CODEBASE, rota);
    }
}
