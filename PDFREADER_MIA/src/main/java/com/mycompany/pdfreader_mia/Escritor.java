package com.url.administradorarchivos.administradorarchivos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Escritor {

    // Nodo raíz de la lista enlazada
    private Nodo Root = new Nodo();

    // Nodo auxiliar para recorrer la lista
    private Nodo n = null;

    public Escritor(){

    }

    // Método para agregar un archivo PDF a la lista
    public void addPdf(File file){

        try {

            // Abre el archivo binario en modo escritura
            RandomAccessFile archive = new RandomAccessFile("archive.bin","rw");

            // Se posiciona al final del archivo
            archive.seek(archive.length());

            // Crea un nuevo nodo
            Nodo Nw = new Nodo();

            // Enlaza el nuevo nodo a la lista
            if(n == null){
                n = Root;
            }else{
                n.setNext(Nw);
                n = Nw;
            }

            // Guarda la posición del nodo en el archivo
            n.setPos(archive.getFilePointer());

            // Escribe el nombre del archivo
            archive.writeChars(file.getName());
            archive.writeChars("\n");

            // Cierra el archivo
            archive.close();

        } catch (IOException ex) {
            Logger.getLogger(Escritor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Método para agregar descripción de un archivo
    public void addDesc(String Desc){

        try {

            // Abre el archivo binario en modo escritura
            RandomAccessFile archive = new RandomAccessFile("archive.bin","rw");

            // Se posiciona al final
            archive.seek(archive.length());

            // Escribe la descripción
            archive.writeChars(Desc);
            archive.writeChars("\n");

            // Cierra el archivo
            archive.close();

        } catch (IOException ex) {
            Logger.getLogger(Escritor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Método para escribir las posiciones de los nodos
    public void posiciones(){

        try {

            // Abre el archivo binario en modo escritura
            RandomAccessFile archive = new RandomAccessFile("archive.bin","rw");

            // Se posiciona al final
            archive.seek(archive.length());

            // Escribe separador
            archive.writeChars("///////");
            archive.writeChars("\n");

            // Recorre la lista escribiendo las posiciones
            Nodo aux = Root;
            while(aux.getNext() != null){
                if(aux != null){
                    archive.writeChars("\n" + aux.getPos());
                }
                aux = aux.getNext();
            }

            // Escribe fin de línea
            archive.writeChars("\n");

            // Cierra archivo
            archive.close();

        } catch (IOException ex) {
            Logger.getLogger(Escritor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
