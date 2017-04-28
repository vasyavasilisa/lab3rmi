/**
 * Created by USER on 02.03.2017.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {

    private Client() {
    }

    public static void main(String[] args) throws IOException {
        String host = (args.length < 1) ? null : args[0];
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String b = br.readLine();
        try {
// Получаем стаб регистратора с хоста, определенного в командной строке
// если в командной строке хост не указывается, то он считается как localhost
            Registry registry = LocateRegistry.getRegistry(host);
// получаем стаб удаленного объекта от регистратора сервера
            Hello stub = (Hello) registry.lookup("Hello");
            String response = stub.sayHello(b);
            System.out.println("response: " + response);
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}