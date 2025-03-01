package org.example

import java.net.DatagramPacket
import java.net.InetAddress
import java.net.MulticastSocket

fun main() {
    try {
        val direccion=InetAddress.getByName("224.0.0.4")
        val socketmulti=MulticastSocket(5000)
        socketmulti.joinGroup(direccion)
        val mensaje="Prats".toByteArray()
        val data=DatagramPacket(mensaje,mensaje.size,direccion,5000)
        socketmulti.send(data)

        while (true){
            val recibir=ByteArray(1024)
            val recibirpaquete=DatagramPacket(recibir,recibir.size)
            socketmulti.receive(recibirpaquete)
            val palabra=String(recibirpaquete.data,recibirpaquete.offset,recibirpaquete.length)
            println(palabra)
        }
    }catch (e:Exception){
        e.printStackTrace()
    }
}