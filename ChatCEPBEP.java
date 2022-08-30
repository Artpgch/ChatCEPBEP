import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ChatCEPBEP {
    ArrayList<Klient> klienty = new ArrayList<>();
    ServerSocket CEPBEPCOKET;
    ChatCEPBEP() throws IOException {
        CEPBEPCOKET = new ServerSocket(1234);
    }
    void sendAll(String soobsch) {
        for (Klient klient : klienty) { klient.vhodiaschee(soobsch);}
    }
    public void run(){
        while(true) {
            System.out.println("Ожидание...");
            try {
                Socket COKET = CEPBEPCOKET.accept();
                System.out.println("Клиент подключен");
                klienty.add(new Klient(COKET, this));
            }catch (IOException e) { e.printStackTrace();}
        }
    }
    public static void main(String[] args) throws IOException {
        new ChatCEPBEP().run();
    }
}
