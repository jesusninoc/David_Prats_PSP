import java.rmi.Remote
import java.rmi.server.UnicastRemoteObject


class SumaI : UnicastRemoteObject(),SumaR{
    override fun suma(numero1:Int,numero2:Int):Int{
        return numero1+numero2
    }
}