import java.io.File
import javax.sound.sampled.AudioSystem
import kotlin.concurrent.thread

// Función para reproducir un archivo de sonido .wav
fun playSound(filePath: String) {
    try {
        val soundFile = File(filePath)
        val audioInputStream = AudioSystem.getAudioInputStream(soundFile)
        val clip = AudioSystem.getClip()
        clip.open(audioInputStream)
        clip.start()

        // Esperar a que el sonido termine de reproducirse
        while (clip.isRunning) {
            Thread.sleep(100)
        }

        clip.close()
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

// Función para la canción con la letra impresa
fun hokeSong() {
    val intro = """
        **Suena la instrumental...**
        Piensa en la vida, piénsalo dos veces,
        que la vida viene y va, pero el alma no desaparece.
    """.trimIndent()

    val verso1 = """
        Empiezo desde el barrio donde todo se derrumba,
        sigo los latidos aunque el mundo a veces tumba.
        Me levanto entre cenizas, no me dejo consumir,
        porque sé que el destino se labra al sobrevivir.
    """.trimIndent()

    val estribillo = """
        Son días grises, noches en vela,
        pero la esperanza se cuela en cada estrella.
        Camino firme, aunque el suelo se parta,
        con el alma en la mano y el futuro en mi carta.
    """.trimIndent()

    val verso2 = """
        Yo vine pa' ganar, aunque pierda en el intento,
        lo que importa no es la meta, sino cómo lo siento.
        Y si caigo diez veces, diez veces me levanto,
        con la fe en mis venas y la vida en mi canto.
    """.trimIndent()

    val outro = """
        Y al final de todo, solo quedan las historias,
        las derrotas, los triunfos, y todas las memorias.
        Que se apague el ruido, que suene la verdad,
        que el rap nunca muere, vive en la eternidad.
    """.trimIndent()

    // Imprimir letra con pausas entre cada verso
    println(intro)
    Thread.sleep(5000)

    println(verso1)
    Thread.sleep(8000)

    println(estribillo)
    Thread.sleep(8000)

    println(verso2)
    Thread.sleep(8000)

    println(estribillo)
    Thread.sleep(8000)

    println(outro)
    println("\n**Fin de la canción**")
}

// Main para ejecutar sonido y la canción simultáneamente
fun main() {
    // Archivo de sonido .wav
    val musicFile = "ruta/del/archivo/sonido.wav"

    // Ejecutar la música en un hilo separado para que se reproduzca mientras imprimimos la letra
    thread {
        playSound(musicFile)
    }

    // Ejecutar la canción con la letra
    hokeSong()
}
