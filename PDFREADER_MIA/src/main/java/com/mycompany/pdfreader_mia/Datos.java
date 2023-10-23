package com.url.administradorarchivos.administradorarchivos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;

public class Datos {
    // Declaración de atributos
    private DefaultListModel modelo = new DefaultListModel();
    private String path;
    private Nodo s;
    private Escritor w = new Escritor();
    private Lector read = new Lector();

    // Constructor de la clase Data
    public Datos() {
        s = null;
    }

    // Método para obtener una lista de archivos en un directorio
    public DefaultListModel Files1(File Folder) {
        modelo = new DefaultListModel();
        return Files2(Folder);
    }

    // Método recursivo para obtener una lista de archivos en un directorio y sus subdirectorios
    private DefaultListModel Files2(File Folder) {
        for (File file : Folder.listFiles()) {
            if (!file.isDirectory()) {
                // Crear un nodo para representar el archivo
                Nodo n = new Nodo();
                n.setFile(file);

                if (s == null) {
                    n.setNext(null);
                    s = n;
                } else {
                    Nodo prev = prev(s, null);
                    prev.setNext(n);
                    n.setNext(null);
                }

                String fileN = file.getName();

                // Si el archivo es un PDF, agregar su nombre al modelo y escribir metadatos
                if ("pdf".equals(l3(fileN))) {
                    modelo.addElement(fileN);
                    w.addPdf(file);
                    try {
                        w.addDesc(values(fileN));
                    } catch (IOException ex) {
                        Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } else {
                // Recursión si se encuentra un directorio
                Files1(file);
            }
        }
        w.posiciones();
        return modelo;
    }

    // Método para leer el contenido de un archivo binario
    public void rb() {
        try {
            RandomAccessFile archive = new RandomAccessFile("archive.bin", "r");
            String L = archive.readLine();
            while (archive.getFilePointer() != archive.length() && !L.equals(" / / / / / / /")) {
                L = archive.readLine();
                System.out.println(L);
            }
            System.out.println("se encontro:" + L);
            archive.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Método para obtener las últimas 3 letras de una cadena (utilizado para verificar la extensión "pdf")
    private String l3(String fileN) {
        String r = "";
        for (int i = fileN.length() - 1; i >= fileN.length() - 3; i--) {
            r = fileN.charAt(i) + r;
        }
        return r;
    }

    // Método para encontrar el nodo anterior en una lista enlazada
    private Nodo prev(Nodo aux, Nodo value) {
        while (aux.getNext() != value && aux != null) {
            aux = aux.getNext();
        }
        return aux;
    }

    // Método para obtener información detallada sobre un archivo
    public String values(String name) throws IOException {
        String r = "";
        Nodo aux = s;
        while (!aux.getFile().getName().equals(name) && aux != null) {
            aux = aux.getNext();
        }
        File f = aux.getFile();
        BasicFileAttributes at = Files.readAttributes(f.toPath(), BasicFileAttributes.class);
        String Pathvar = f.toPath().toString();
        String a = read.Read(Pathvar);

        r = "Nombre: " + f.getName() +
                "\nDirección: " + f.toPath() +
                "\nTamaño: " + at.size() +
                " bytes" + a;

        return r;
    }
}

