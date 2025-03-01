package org.example

import java.io.*
import java.security.KeyStore
import javax.net.ssl.*

class ServidorSSL {
    private val puerto = 4000
    private val claveAlmacen = "1234567".toCharArray()
    private val rutaAlmacen = "C:\\Users\\David\\Documents\\GitHub\\David_Prats_PSP\\ProyectoFinalCitas\\servidorMedica\\src\\reservas.p12"

    fun iniciarServidor() {
        try {
            // Configurar SSL
            val contexto = configurarSSL()

            val socketFabrica = contexto.serverSocketFactory
            val servidorSSL = socketFabrica.createServerSocket(puerto) as SSLServerSocket

            println("🔹 Servidor SSL esperando conexiones en el puerto $puerto...")

            while (true) {
                val cliente = servidorSSL.accept() as SSLSocket
                Thread { manejarCliente(cliente) }.start() // Cada cliente en un hilo separado
            }
        } catch (e: Exception) {
            println("❌ Error en el servidor: ${e.message}")
            e.printStackTrace()
        }
    }

    private fun manejarCliente(cliente: SSLSocket) {
        try {
            println("🔹 Cliente conectado.")

            val input = DataInputStream(cliente.inputStream)
            val output = DataOutputStream(cliente.outputStream)

            val mensaje = input.readUTF()
            println("📩 Mensaje recibido: $mensaje")

            if (mensaje.startsWith("CITA")) {
                val partes = mensaje.split("|")
                if (partes.size >= 6) {
                    val medico = partes[1].split(":")[1]
                    val motivo = partes[2].split(":")[1]
                    val fecha = partes[3].split(":")[1]
                    val hora = partes[4].split(":")[1]
                    val usuario=partes[5].split(":")[1]

                    println("✅ Cita procesada:")
                    println("👨‍⚕️ Médico: $medico")
                    println("📌 Motivo: $motivo")
                    println("📅 Fecha: $fecha")
                    println("⏰ Hora: $hora")
                    println("👤 Usuario: $usuario")

                    output.writeUTF("✅ Cita procesada correctamente en el servidor")
                } else {
                    output.writeUTF("❌ Error: Formato de cita incorrecto")
                }
            } else {
                output.writeUTF("⚠️ Comando no reconocido")
            }

        } catch (e: Exception) {
            println("❌ Error con cliente: ${e.message}")
        } finally {
            cliente.close()
        }
    }

    private fun configurarSSL(): SSLContext {
        val almacen = KeyStore.getInstance("JKS")
        almacen.load(FileInputStream(rutaAlmacen), claveAlmacen)

        val manager = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm())
        manager.init(almacen, claveAlmacen)

        val confianza = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm())
        confianza.init(almacen)

        val contexto = SSLContext.getInstance("TLS")
        contexto.init(manager.keyManagers, confianza.trustManagers, null)

        return contexto
    }
}

fun main() {
    val servidor = ServidorSSL()
    servidor.iniciarServidor()
}
