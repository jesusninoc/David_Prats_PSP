package com.example.myapplication.model

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.DataInputStream
import java.io.DataOutputStream
import java.io.InputStream
import java.security.KeyStore
import java.security.cert.Certificate
import java.security.cert.CertificateFactory
import javax.net.ssl.*

class AppointmentViewModel(private val context: Context) : ViewModel() {
    private lateinit var firebaseAuth: FirebaseAuth

    fun sendAppointment(motivo: String, fecha: String, hora: String, medico: String, onResponseReceived: (String) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            var cliente: SSLSocket? = null
            var flujoEntrada: DataInputStream? = null
            var flujoSalida: DataOutputStream? = null
            firebaseAuth = FirebaseAuth.getInstance()
            var usuario: String? = firebaseAuth.currentUser?.email

            try {
                val host = "192.168.1.70"
                val puerto = 4000

                val sslSocketFactory = createSSLSocketFactory(context)
                cliente = sslSocketFactory.createSocket(host, puerto) as SSLSocket
                cliente.soTimeout = 10000

                cliente.startHandshake()
                flujoSalida = DataOutputStream(cliente.getOutputStream())
                flujoEntrada = DataInputStream(cliente.getInputStream())

                val message = "CITA|Medico:$medico|Motivo:$motivo|Fecha:$fecha|Hora:$hora|Usuario:$usuario"
                flujoSalida.writeUTF(message)
                flujoSalida.flush()

                val serverResponse = flujoEntrada.readUTF()
                onResponseReceived(serverResponse)
            } catch (e: Exception) {
                onResponseReceived("Error en la conexión: ${e.message}")
            } finally {
                flujoEntrada?.close()
                flujoSalida?.close()
                cliente?.close()
            }
        }
    }

    private fun createSSLSocketFactory(context: Context): SSLSocketFactory {
        try {
            val certificateFactory = CertificateFactory.getInstance("X.509")
            val certInputStream: InputStream = context.resources.openRawResource(R.raw.reservas)
            val certificado: Certificate = certificateFactory.generateCertificate(certInputStream)
            certInputStream.close()

            val keyStore = KeyStore.getInstance(KeyStore.getDefaultType())
            keyStore.load(null, null)
            keyStore.setCertificateEntry("servidor", certificado)

            val trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm())
            trustManagerFactory.init(keyStore)

            val sslContext = SSLContext.getInstance("TLSv1.3")
            sslContext.init(null, trustManagerFactory.trustManagers, null)

            return sslContext.socketFactory
        } catch (e: Exception) {
            throw RuntimeException("Error al cargar el certificado en Android: ${e.message}", e)
        }
    }
}