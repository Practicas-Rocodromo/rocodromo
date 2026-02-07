package com.gbjoanna.rocodromo.Util;

import java.io.*;
import java.util.ArrayList;

public class FileData {

    public final static String USUARIOS_DAT = "usuarios.dat";
    public final static String RESERVAS_DAT = "reservas.dat";
    public final static String ROCODROMO_DAT = "rocodromo.dat";

    // Serializer the object to save
    public static <T> void saveFile(ArrayList<T> objectSave, String nameFile) {
        ObjectOutputStream serializer = null;
        try {
            serializer = new ObjectOutputStream(new FileOutputStream(nameFile));
            serializer.writeObject(objectSave);
            IO.println("Los datos se han guardado correctamente");
        } catch (IOException e) {
            IO.println("Se ha producido un error al guardar el fichero: " + e);
        } finally {
            if ( serializer != null) {
                try {
                    // closed execution
                    serializer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // deserializer object load app
    public static <T>  ArrayList<T> loadFile(String nameFile) {
        ArrayList<T> listDataFile = null;
        ObjectInputStream deserializador = null;
        try {
            deserializador = new ObjectInputStream(new FileInputStream(nameFile));
            listDataFile = (ArrayList<T>)deserializador.readObject();
        } catch (FileNotFoundException e) {
            IO.println("No se encontrado el fichero. Error: ");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            IO.println("El formato del fichero no es el esperado.Error: ");
            e.printStackTrace();
        } catch (IOException e) {
            IO.println("Se a producido un error al leer el fichero.Error: ");
            e.printStackTrace();
        } finally {
            if (deserializador != null) {
                try {
                    deserializador.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return  listDataFile;
    }
}

