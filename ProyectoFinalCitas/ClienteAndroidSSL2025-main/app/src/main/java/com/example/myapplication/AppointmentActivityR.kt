package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.model.AppointmentViewModel
import com.example.myapplication.model.AppointmentViewModelFactory

class AppointmentActivityR : AppCompatActivity() {
        private val viewModel: AppointmentViewModel by viewModels { AppointmentViewModelFactory(this) }

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_appointment_r)

            val motivoEditText = findViewById<EditText>(R.id.etMotivo)
            val fechaEditText = findViewById<EditText>(R.id.etFecha)
            val horaEditText = findViewById<EditText>(R.id.etHora)
            val medicoEditText = findViewById<EditText>(R.id.etMedico)
            val responseTextView = findViewById<TextView>(R.id.tvResultado)
            val sendButton = findViewById<Button>(R.id.btnAgendar)

            sendButton.setOnClickListener {
                val motivo = motivoEditText.text.toString()
                val fecha = fechaEditText.text.toString()
                val hora = horaEditText.text.toString()
                val medico = medicoEditText.text.toString()

                viewModel.sendAppointment(motivo, fecha, hora, medico) { response ->
                    runOnUiThread {
                        responseTextView.text = response
                    }
                }
            }
        }

}