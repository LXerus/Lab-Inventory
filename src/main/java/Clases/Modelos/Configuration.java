package Clases.Modelos;

import java.io.*;

public class Configuration {

    public Configuration(){}

    public void configurationFile(String server_config, String port_config, String dataBase_config){
        directory = new File("/Configuration");
        file = new File(directory, "config.txt");
        try{
            if(!file.exists()){
                System.out.println("el archivo config no existe");
                if(!directory.exists()){
                    System.out.println("el directorio de configuracion no existe");
                    if(directory.mkdir()){
                        System.out.println("el direcorio de configuracion creado");
                        file.createNewFile();
                        printWriter = new PrintWriter(file);
                        printWriter.println(server_config);
                        printWriter.println(port_config);
                        printWriter.println(dataBase_config);
                    }
                }else {
                    System.out.println("archivo de configuracion creado");
                    file.createNewFile();
                    printWriter = new PrintWriter(file);
                    printWriter.println(server_config);
                    printWriter.println(port_config);
                    printWriter.println(dataBase_config);
                }
            }else {
                System.out.println("el archivo ya existe, sobreescribiendo datos");
                printWriter = new PrintWriter(file);
                printWriter.println(server_config);
                printWriter.println(port_config);
                printWriter.println(dataBase_config);
            }
        }catch (IOException ex){
            ex.printStackTrace();
        }finally {
            if(printWriter != null){
                printWriter.close();
            }
        }
    }

    public void readConfiguration(){
        directory = new File("/Configuration");
        file = new File(directory, "config.txt");

        try{
            if(!file.exists()) {
                if(!directory.exists()){
                    if(directory.mkdir()){
                        file.createNewFile();
                    }
                }else {
                    file.createNewFile();
                }
            }


            fileReader = new FileReader(file);
            input = new BufferedReader(fileReader);
            server = input.readLine();
            port = input.readLine();
            dataBase = input.readLine();

        }catch (IOException ex){
            ex.printStackTrace();
        }finally {
            try{
                if(fileReader != null){
                    fileReader.close();
                }
            }catch (IOException ex){
                ex.printStackTrace();
            }
        }
    }



    public static String getServer() {
        return server;
    }

    public static void setServer(String server) {
        Configuration.server = server;
    }

    public static String getPort() {
        return port;
    }

    public static void setPort(String port) {
        Configuration.port = port;
    }

    public static String getDataBase() {
        return dataBase;
    }

    public static void setDataBase(String dataBase) {
        Configuration.dataBase = dataBase;
    }

    private static String dataBase;
    private static String server;
    private static String port;
    private File directory;
    private File file;
    private BufferedReader input;
    private FileReader fileReader = null;
    private PrintWriter printWriter = null;
}
