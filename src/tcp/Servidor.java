package tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class Servidor {
    
    int porta = 1234;
    Date d = new Date();
    int numeroSecreto = (int) (Math.random() * 10) + 1;
    
    public Servidor(){
        try{
            ServerSocket ss = new ServerSocket(porta);
            System.out.println("Servidor em funcionamento... Numero gerado: "+numeroSecreto);
            while(true){
                Socket s = ss.accept();
                System.out.println("Nova Conexão");

                InputStream is = s.getInputStream();
                byte[] b = new byte[20];
                is.read(b);
                
                int informado = Integer.parseInt(new String(b).trim());
                OutputStream os = s.getOutputStream();
                
                System.out.println("V: "+ informado);
                
                if(informado == numeroSecreto){
                    os.write("Ah miseravi".getBytes());
                    System.out.println("Certo");
                    return;
                }else{
                    os.write("Tente novamente".getBytes());
                    System.out.println("Errado");
                }                
            }
            
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        new Servidor();
    }
}
