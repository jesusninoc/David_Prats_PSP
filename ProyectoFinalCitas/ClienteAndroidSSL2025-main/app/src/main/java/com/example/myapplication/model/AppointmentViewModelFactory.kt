package com.example.myapplication.model

import android.content.Context
import androidx.lifecycle.ViewModel

class AppointmentViewModelFactory(private val context: Context) : androidx.lifecycle.ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AppointmentViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AppointmentViewModel(context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}