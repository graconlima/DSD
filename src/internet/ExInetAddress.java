package internet;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class ExInetAddress {
    public static void main(String[] args) {
        try{
            
            byte[] b = InetAddress.getByName("ifrn.edu.br").getAddress();
            System.out.println((b[0]&0xFF)+"."+(b[1]&0xFF)+"."+(b[2]&0xFF)+"."+(b[3]&0xFF));
            
            System.out.println(""+InetAddress.getByAddress(b).getHostName());
        }catch(UnknownHostException uhe){
            uhe.printStackTrace();
        }
    }
}
