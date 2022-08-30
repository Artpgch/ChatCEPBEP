import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
class Klient implements Runnable {
    Socket COKET;
    Scanner priem;
    PrintStream otpravka;
    ChatCEPBEP CEPBEP;
    public Klient(Socket soket, ChatCEPBEP server) {
        this.COKET = soket;
        this.CEPBEP = server;
        new Thread(this).start();
    }

    void vhodiaschee(String soobsch) {
        otpravka.println(soobsch);
    }

    //@Override
    public void run() {
        try {
            InputStream potok_priem = COKET.getInputStream();
            OutputStream potok_otpravka = COKET.getOutputStream();
            priem = new Scanner(potok_priem);
            otpravka = new PrintStream(potok_otpravka);
            otpravka.println("Добро пожаловать!");
            String input = priem.nextLine();
            while (!input.equals("Конец")) {
                CEPBEP.sendAll(input);
                input = priem.nextLine();
            }
            COKET.close();
            } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
