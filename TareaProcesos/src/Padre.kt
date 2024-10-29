import java.io.BufferedReader
import java.io.InputStreamReader

class Padre {

    fun iniciarProceso() {
        // Solicitar al usuario que introduzca un número
        print("Introduce un número: ")
        val numero = readLine() ?: return

        println("Número introducido por el usuario: $numero")

        // Crear el proceso para ejecutar el programa Hijo
        val processBuilder = ProcessBuilder(
            "C:\\Users\\david\\.jdks\\openjdk-22.0.2\\bin\\java.exe",
            "-javaagent:C:\\Program Files\\JetBrains\\IntelliJ IDEA Community Edition 2024.2.1\\lib\\idea_rt.jar=52016:C:\\Program Files\\JetBrains\\IntelliJ IDEA Community Edition 2024.2.1\\bin",
            "-Dfile.encoding=UTF-8",
            "-Dsun.stdout.encoding=UTF-8",
            "-Dsun.stderr.encoding=UTF-8",
            "-classpath",
            "C:\\Users\\david\\Documents\\GitHub\\David_Prats_PSP\\TareaProcesos\\out\\production\\TareaProcesos;" +
                    "C:\\Users\\david\\.m2\\repository\\org\\jetbrains\\kotlin\\kotlin-stdlib\\2.0.10\\kotlin-stdlib-2.0.10.jar;" +
                    "C:\\Users\\david\\.m2\\repository\\org\\jetbrains\\annotations\\13.0\\annotations-13.0.jar",
            "HijoKt"
        )

        // Iniciar el proceso
        val proceso = processBuilder.start()

        // Enviar el número al proceso hijo a través de stdin
        val outputStream = proceso.outputStream.bufferedWriter()

        println("Enviando número al proceso hijo...")

        // Enviar el número con un salto de línea para que el hijo lo procese
        outputStream.write(numero)
        outputStream.newLine()
        outputStream.flush()
        outputStream.close()  // Cerramos el flujo para indicar al hijo que ya no enviamos más datos

        // Capturar la salida estándar del proceso hijo
        val inputStream = BufferedReader(InputStreamReader(proceso.inputStream))
        val resultado = inputStream.readLine()

        // Capturar posibles errores (stderr)
        val errorStream = BufferedReader(InputStreamReader(proceso.errorStream))
        val error = errorStream.readLine()

        if (resultado != null) {
            println("El proceso hijo devolvió: $resultado")
        } else {
            println("El proceso hijo no devolvió ningún resultado.")
        }

        // Mostrar cualquier error generado por el proceso hijo
        if (error != null) {
            println("Error en el proceso hijo: $error")
        }

        proceso.waitFor()  // Esperar a que el proceso hijo termine
        println("Proceso hijo terminado.")
    }
}

fun main() {
    // Crear una instancia de la clase Padre e iniciar el proceso
    val padre = Padre()
    padre.iniciarProceso()
}
