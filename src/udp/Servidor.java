package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.DatagramPacket;

public class Servidor {
    
    static int porta = 3000;
    private static byte[] mensagem = "Resposta".getBytes();
    
    public static void main(String[] args) {
        try{
            DatagramSocket soquete = new DatagramSocket(porta);
            System.out.println("Servidor em funcionamento. Aguardando conexoes...");
            byte[] buffer = new byte[65535];
            DatagramPacket dp = new DatagramPacket(buffer, buffer.length);
            soquete.receive(dp);
            
            System.out.println("Dados: "+new String(dp.getData()).trim());
            System.out.println("Porta: "+dp.getPort());
            System.out.println("Endereco: "+dp.getAddress());
            
            DatagramPacket resposta = new DatagramPacket(
                    mensagem, 
                    mensagem.length, 
                    dp.getAddress(), 
                    dp.getPort());
            soquete.send(resposta);
            soquete.close();
        }catch(SocketException se){
            se.printStackTrace();
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }
}