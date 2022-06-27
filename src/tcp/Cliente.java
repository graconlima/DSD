package tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.JOptionPane;

public class Cliente {
    
    String endereco = "localhost";
    int porta = 1234;
    
    public Cliente(){
        try{
            
            while(true){
                Socket s = new Socket(endereco, porta);

                OutputStream os = s.getOutputStream();
                os.write(JOptionPane.showInputDialog("Informe um valor:").getBytes());

                InputStream is = s.getInputStream();
                byte[] b = new byte[200];
                is.read(b);
                is.close();

                String m = new String(b).trim();
                JOptionPane.showMessageDialog(null, m);
                
                if(m.equals("Ah miseravi"))
                    return;
            }
            
        }catch(UnknownHostException uhe){
            uhe.printStackTrace();
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        new Cliente();
    }
}
