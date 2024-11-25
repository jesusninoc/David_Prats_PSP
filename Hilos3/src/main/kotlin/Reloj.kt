package org.example

import java.text.SimpleDateFormat
import java.util.*
import kotlin.concurrent.thread

fun main(){
    thread {
        while (true){
            val hora= Calendar.getInstance().time
            val format=SimpleDateFormat("HH:mm:ss").format(hora)
            println(format)
            Thread.sleep(1000)
        }
    }
}