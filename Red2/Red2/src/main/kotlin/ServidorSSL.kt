import java.io.DataInput
import java.io.DataInputStream
import java.io.FileInputStream
import java.io.ObjectInputStream
import java.security.KeyStore
import javax.net.ssl.KeyManagerFactory
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocket
import javax.net.ssl.TrustManagerFactory

class ServidorSSL {
    fun main(){
        val almacen=KeyStore.getInstance("JKS")
        almacen.load(FileInputStream("C:\\Users\\David\\.jdks\\openjdk-23.0.1\\bin\\almacen"), "1234567".toCharArray())

       val manager=KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm())
        manager.init(almacen,"1234567".toCharArray())

        val  confianza=TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm())
        confianza.init(almacen)

        val contexto=SSLContext.getInstance("TLS")
        contexto.init(manager.keyManagers,confianza.trustManagers,null)

        val socketFabrica=contexto.serverSocketFactory
        val servidor=socketFabrica.createServerSocket(6000)

        var cliente:SSLSocket?=null
        var entrada:DataInput?=null
        var salida:DataInput?=null
        cliente=servidor.accept() as SSLSocket
        entrada=DataInputStream(cliente.inputStream)
        println(entrada.readUTF())
        salida=DataInputStream(cliente.inputStream)
        println(salida.readUTF())
        entrada.close()
        salida.close()
        cliente.close()
        servidor.close()

    }


}
fun main(){
    val servidor=ServidorSSL()
    servidor.main()
}