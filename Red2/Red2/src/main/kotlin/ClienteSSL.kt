import java.io.DataInputStream
import java.io.DataOutputStream
import java.io.FileInputStream
import java.security.KeyStore
import javax.net.ssl.KeyManagerFactory
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManagerFactory

class ClienteSSL {
    fun main(){
        val almacen= KeyStore.getInstance("JKS")
        almacen.load(FileInputStream("C:\\Users\\David\\.jdks\\openjdk-23.0.1\\bin\\almacen"), "1234567".toCharArray())

        val manager= KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm())
        manager.init(almacen,"1234567".toCharArray())

        val  confianza= TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm())
        confianza.init(almacen)

        val contexto= SSLContext.getInstance("TLS")
        contexto.init(manager.keyManagers,confianza.trustManagers,null)

        val salfabrica=contexto.socketFactory
        val cliente=salfabrica.createSocket("localhost",6000)

        val salida=DataOutputStream(cliente.getOutputStream())
        salida.writeUTF("soy cliente")

        val entrada=DataInputStream(cliente.getInputStream())
        println(entrada.readUTF())

        salida.close()
        entrada.close()
        cliente.close()
    }
}
fun main(){
    ClienteSSL().main()
}