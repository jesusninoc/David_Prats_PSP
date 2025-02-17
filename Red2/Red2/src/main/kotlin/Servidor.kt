import java.io.ObjectInputStream
import java.net.ServerSocket

class Servidor(private val puerto: Int) {
    fun iniciar() {
        val servidor = ServerSocket(puerto)
        val cliente = servidor.accept()
        val leer=  ObjectInputStream(cliente.getInputStream())
        val objetoSerializado = leer.readObject() as Persona
        println(objetoSerializado.toString())
        leer.close()
        cliente.close()
        servidor.close()

    }
}