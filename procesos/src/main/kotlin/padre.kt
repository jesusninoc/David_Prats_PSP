import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {

    val proceso = ProcessBuilder(
        "C:\\Users\\david\\.jdks\\openjdk-22.0.2\\bin\\java.exe",
        "-javaagent:C:\\Program Files\\JetBrains\\IntelliJ IDEA Community Edition 2024.2.1\\lib\\idea_rt.jar=55776:C:\\Program Files\\JetBrains\\IntelliJ IDEA Community Edition 2024.2.1\\bin",
        "-Dfile.encoding=UTF-8",
        "-Dsun.stdout.encoding=UTF-8",
        "-Dsun.stderr.encoding=UTF-8",
        "-classpath",
        "C:\\Users\\david\\Documents\\GitHub\\David_Prats_PSP\\procesos\\target\\classes;C:\\Users\\david\\.m2\\repository\\org\\jetbrains\\kotlin\\kotlin-stdlib\\2.0.10\\kotlin-stdlib-2.0.10.jar;C:\\Users\\david\\.m2\\repository\\org\\jetbrains\\annotations\\13.0\\annotations-13.0.jar",
        "HijoKt"
    )

    val hijo = proceso.start()

    print("Introduce un número: ")
    val numero = readLine() ?: return

    val output = BufferedWriter(OutputStreamWriter(hijo.outputStream))
    output.write("$numero\n")
    output.flush()

    val input = BufferedReader(InputStreamReader(hijo.inputStream))
    val mensaje = input.readLine()
    println("El número $numero $mensaje")

    hijo.waitFor()
}
