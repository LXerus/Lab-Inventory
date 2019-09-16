package Clases.Modelos;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.List;

public class Configuracion {

    public Configuracion(){}

    public void archivoConfiguracion(String servidor_config, String puerto_config, String dataBase_config){
        ruta = new File("/Configuracion");
        archivo = new File(ruta, "config.txt");
        try{
            if(!archivo.exists()){
                System.out.println("archivo config no existe");
                if(!ruta.exists()){
                    System.out.println("ruta de configuracion no existe");
                    if(ruta.mkdir()){
                        System.out.println("ruta de configuracion creada");
                        archivo.createNewFile();
                        escritor = new PrintWriter(archivo);
                        escritor.println(servidor_config);
                        escritor.println(puerto_config);
                        escritor.println(dataBase_config);
                    }
                }else {
                    System.out.println("archivo de configuracion creado");
                    archivo.createNewFile();
                    escritor = new PrintWriter(archivo);
                    escritor.println(servidor_config);
                    escritor.println(puerto_config);
                    escritor.println(dataBase_config);
                }
            }else {
                System.out.println("el archivo ya existe, sobreescribiendo datos");
                escritor = new PrintWriter(archivo);
                escritor.println(servidor_config);
                escritor.println(puerto_config);
                escritor.println(dataBase_config);
            }
        }catch (IOException ex){
            ex.printStackTrace();
        }finally {
            if(escritor != null){
                escritor.close();
            }
        }
    }

    public void leerConfiguracion(){
        ruta = new File("/Configuracion");
        archivo = new File(ruta, "config.txt");

        try{
            if(!archivo.exists()) {
                if(!ruta.exists()){
                    if(ruta.mkdir()){
                        archivo.createNewFile();
                    }
                }else {
                    archivo.createNewFile();
                }
            }


            lectura = new FileReader(archivo);
            entrada = new BufferedReader(lectura);
            servidor = entrada.readLine();
            puerto = entrada.readLine();
            baseDeDatos = entrada.readLine();

        }catch (IOException ex){
            ex.printStackTrace();
        }finally {
            try{
                if(lectura != null){
                    lectura.close();
                }
            }catch (IOException ex){
                ex.printStackTrace();
            }
        }
    }



    public static String getServidor() {
        return servidor;
    }

    public static void setServidor(String servidor) {
        Configuracion.servidor = servidor;
    }

    public static String getPuerto() {
        return puerto;
    }

    public static void setPuerto(String puerto) {
        Configuracion.puerto = puerto;
    }

    public static String getBaseDeDatos() {
        return baseDeDatos;
    }

    public static void setBaseDeDatos(String baseDeDatos) {
        Configuracion.baseDeDatos = baseDeDatos;
    }

    private static String baseDeDatos;
    private static String servidor;
    private static String puerto;
    private File ruta;
    private File archivo;
    private BufferedReader entrada;
    private FileReader lectura = null;
    private PrintWriter escritor = null;
}
