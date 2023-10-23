package com.url.administradorarchivos.administradorarchivos;

import java.io.File;

public class Nodo {
   File file;
   Nodo next;
   long pos;
    public Nodo(){
        this.file=null;
        this.next=null;
        this.pos=(long)0;
   } 
   public void setFile(File file){
       this.file=file;
   }
   public File getFile(){
       return this.file;
   }
    public long getPos() {
        return this.pos;
    }
    public void setPos(long pos){
        this.pos=pos;
    }
   public void setNext(Nodo next){
       this.next=next;
   }
   public Nodo getNext(){
       return this.next;
   }
}
