import java.rmi.Remote
import kotlin.jvm.Throws


interface SumaR :Remote{
    @Throws fun suma(numero1:Int,numero2:Int):Int{
        return numero1+numero2
    }
}