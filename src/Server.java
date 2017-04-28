/**
 * Created by USER on 02.03.2017.
 */

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Server implements Hello {
    public Server() {}
    // реализация интерфейса
    String pattern = "[-+]?\\d+";
    String response;
    public String sayHello(String b) {


        String masOfStr[]=b.split(" ");
        int mastOfDigit[]= new int[masOfStr.length];
        Pattern p = Pattern.compile(pattern);
       // Matcher m = p.matcher(b);

        for(int i=0;i<masOfStr.length;i++){
            Matcher m = p.matcher(masOfStr[i]);
            if(!m.find()){
                response="Некорректный ввод" ;
                return response;
            }
        }
        for(int i=0;i<masOfStr.length;i++){
            mastOfDigit[i]=Integer.valueOf(masOfStr[i]);
        }
        int min= mastOfDigit[0];
        for(int i=0;i<mastOfDigit.length;i++){
            if (mastOfDigit[i] < min) {

                min=mastOfDigit[i];
            }
        }


        response=String.valueOf(min);
        return response;
       /* Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(b);

        if (m.find()){
            response="Latin";
        }
        else
            response="Russian";

        return response;*/

    }



    public static void main(String args[]) {
        try {
// создаем и экспортируем удаленный объект
            Server obj = new Server();
            Hello stub = (Hello) UnicastRemoteObject.exportObject(obj, 0);
// Регистрируем удаленный объект в RMI-регистраторе под именем
            LocateRegistry.createRegistry(1099);

            Registry registry = LocateRegistry.getRegistry();
            registry.bind("Hello", stub);
            System.err.println("Server ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
