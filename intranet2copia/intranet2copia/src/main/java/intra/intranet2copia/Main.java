package intra.intranet2copia;

import java.util.Base64;

public class Main {
    public static void main(String[] args) {
        String credentials = "admin123:admin123prueba";
        String encodedCredentials = Base64.getEncoder().encodeToString(credentials.getBytes());
        System.out.println(encodedCredentials);
    }
}