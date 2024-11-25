package org.example

fun main() {
   val hilo=Thread{
       try {
           while (Thread.currentThread().isInterrupted){
               println("hilo em ejecucion")
               Thread.sleep(1000)
           }
       }catch (e: InterruptedException){
            print("hijo interrumpido")
       }
   }
    Thread.sleep(5000)
    hilo.start()
    hilo.interrupt()
    hilo.join()
}