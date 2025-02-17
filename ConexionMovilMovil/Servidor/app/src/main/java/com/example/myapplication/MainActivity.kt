package com.example.myapplication

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.ServerSocket

class MainActivity : ComponentActivity() {
    private val port = 6000 // Puerto para la conexión

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            startServer()
        }
    }

    private fun startServer() {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val serverSocket = ServerSocket(port)

                while (true) {
                    val client = serverSocket.accept()
                    val inputStream = client.getInputStream()
                    val bufferedReader = BufferedReader(InputStreamReader(inputStream))

                    val message = bufferedReader.readLine()
                    showMessage(message)

                    bufferedReader.close()
                    client.close()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun showMessage(message: String?) {
        runOnUiThread {
            Toast.makeText(this, "Mensaje recibido: $message", Toast.LENGTH_SHORT).show()
        }
    }
}