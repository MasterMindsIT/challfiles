import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

public class Main {
    static Scanner sc =  new Scanner(System.in);
    static File directorioDefault = null;
    static File archivoDefault = null;
    public static void main(String[] args) {
        int menu = 0;
        do{
            menu();
            menu = leerOpcion();
            if(menu==1){
                System.out.println("Ingresa el nombre para el directorio a crear: ");
                crearDirectorio(sc.next());
            } else if (menu==2) {
                System.out.println("Ingresa el nombre para el archivo a crear: ");
                crearArchivo(sc.next());
            } else if (menu==3) {
                System.out.println("Ingresa el nombre para el directorio a verificar: ");
                validarDirectorio(sc.next());
            } else if (menu==4) {
                System.out.println("Ingresa el nombre del archivo a verificar: ");
                validarArchivo(sc.next());
            } else if (menu==5) {
                precrgarDatos();
            } else if (menu==6) {
                System.out.println("Ingresa el texto a verificar: ");
                buscarTexto(sc.next());
            }else if(menu==7){
                System.out.println("Ingresa el texto a verificar y contar: ");
                buscarContarTexto(sc.next());
            } else if (menu==8) {
                System.out.println("Gracias por tu visita... Hasta!!!");
            } else if (menu>8) {
                System.out.println("Opción incorrecta");
            }
        }while (menu!=8);
    }

    public static int leerOpcion() {
        return sc.nextInt();
    }
    public static void menu() {
        System.out.println("=================================");
        System.out.println("   M E N Ú   P R I N C I P A L");
        System.out.println("=================================");
        System.out.println("  1.-CREAR DIRECTORIO");
        System.out.println("  2.-CREAR ARCHIVO");
        System.out.println("  3.-VERIFICAR DIRECTORIO");
        System.out.println("  4.-VERIFICAR ARCHIVO");
        System.out.println("  5.-PRECARGAR DATOS");
        System.out.println("  6.-VERIFICAR TEXTO");
        System.out.println("  7.-VERIFICAR Y CONTAR TEXTO");
        System.out.println("  8.-SALIR");
        System.out.println("=================================");
        System.out.print("Ingresa la opción de tu preferencia: ");
    }

    public static boolean buscarTexto(String textoABuscar){
        if(archivoDefault!=null){
        try {
            FileReader fr = new FileReader(archivoDefault);
            BufferedReader br = new BufferedReader(fr);
            boolean comprobar = false;
            String data;
            while ((data = br.readLine()) != null) {
                if (data.equalsIgnoreCase(textoABuscar)){
                    comprobar= true;
                }
            }
            fr.close();
            br.close();
            if (comprobar){
                System.out.println("La palabra existe");
            } else {
                System.out.println("La palabra no existe ");
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        }else{
            System.out.println("El archivo aún no ha sido creado");
        }
        return false;
    }
    public static void buscarContarTexto(String textoABuscar){
        if(archivoDefault!=null){
            try {
                int contador = 0;
                FileReader fr = new FileReader(archivoDefault);
                BufferedReader br = new BufferedReader(fr);
                boolean comprobar = false;
                String data;
                while ((data = br.readLine()) != null) {
                    if (data.equalsIgnoreCase(textoABuscar)){
                        comprobar= true;
                        contador ++;
                    }
                }
                fr.close();
                br.close();
                if (comprobar){
                    System.out.println("La palabra existe en el archivo y se encontro: "+ contador+ " veces");
                } else {
                    System.out.println("La palabra no existe en el archivo");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            System.out.println("El archivo aún no ha sido creado");
        }
    }

    static void precrgarDatos(){
        if(archivoDefault!=null) {
            try {
                String miArchivo = directorioDefault.getParentFile() +"/"+ directorioDefault.getName() +"/"+archivoDefault.getName();
                FileWriter fw = new FileWriter(miArchivo,true);
                BufferedWriter bw = new BufferedWriter(fw);
                ArrayList<String> lista = new ArrayList<>(Arrays.asList("perro", "gato", "juan", "daniel", "juan", "gato", "perro", "camila", "daniel", "camila"));
                for (Iterator<String> iterator = lista.iterator(); iterator.hasNext();) {
                    bw.write(iterator.next() + "\n");
                }
                bw.close();
                fw.close();
                System.out.println("Han sido agregados datos de prueba");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }else{
            System.out.println("Aún no se ha creado ningún archivo");
        }
    }
    public static boolean validarDirectorio (String file) {
        File sendFile = new File("src/"+file);
        if (!sendFile.exists()) {
            System.out.println("El directorio no existe");
            return false;
        } else {
            System.out.println("El directorio ya existe");
            return true;
        }
    }

    public static boolean validarArchivo(String file){
        if(directorioDefault!=null) {
            File midirectorio = new File(String.valueOf(directorioDefault.getAbsoluteFile()));
            File fichero = new File(midirectorio.getAbsoluteFile() + "/" + file + ".txt");
            if (fichero.exists()) {
                System.out.println("El archivo existe");
                return true;
            }

            System.out.println("El archivo no existe");
            return false;
        }
        System.out.println("El archivo no existe");
        return false;
    }
    public static void crearDirectorio(String file){
        File newFile = new File("src/"+file);

        if (!newFile.exists()) {
            if (newFile.mkdirs()) {
                System.out.println("Directorio creado exitosamente, este será su directorio de trabajo para crear archivos!");
                directorioDefault = newFile;
            }
            else {
                System.out.println("Error al crear directorio");
            }
        }else{
            System.out.println("El directorio ya existia, ahora es elárea de trabajo por defecto");
            directorioDefault = newFile;
        }
    }
    public static void crearArchivo(String file){
        if(directorioDefault != null){
            File midirectorio = new File(String.valueOf(directorioDefault.getAbsoluteFile()));
            File fichero = new File(midirectorio.getAbsoluteFile() + "/" + file +".txt");
               try {
                   if(!fichero.exists()) {
                       if (fichero.createNewFile()) {
                           System.out.println("Archivo creado y ahora esta en su contexto de uso");
                           archivoDefault = fichero;
                       }
                   }else{
                       System.out.println("Archivo ya existia, ahora esta en su contexto de uso");
                       archivoDefault = fichero;
                   }
               } catch (IOException e) {
                   System.out.println("Error al crear el archivo");
                    throw new RuntimeException(e);
               }
        }else{
            System.out.println("No existe directorio por defecto a trabajar, debe crear uno");
        }
    }
}