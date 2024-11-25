package org.example

class HiloA{
    fun arrancar(){


    }
}


class HiloB: Thread(){
    var contador=0
    override fun run() {
        for (i in 1..1000){
            contador += i
        }
    }
}

class HiloC:Thread(){
    var contador=0
    override fun run() {
        for (i in 1..1000){
            contador -= i
        }
    }
}

fun main(){
    val b=HiloB()
    val c=HiloC()
    b.start()
    c.start()
    println("Total suma: ${b.contador}")
    println("Total suma: ${c.contador}")
}