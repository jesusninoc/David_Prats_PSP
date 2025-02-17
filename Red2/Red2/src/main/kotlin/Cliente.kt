import java.io.ObjectOutputStream
import java.net.ServerSocket
import java.net.Socket

class Cliente (private val host:String, private val puerto:Int) {
    fun enviar(persona: Persona){
        val cliente=Socket(host,puerto)
        val mandar=ObjectOutputStream(cliente.getOutputStream())
        mandar.writeObject(persona)
        mandar.flush()
        mandar.close()
        cliente.close()
    }

}