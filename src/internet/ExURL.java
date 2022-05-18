package internet;

import java.net.URL;
import java.net.MalformedURLException;
import java.io.IOException;
import java.net.URLConnection;
import java.io.InputStream;
import java.net.HttpURLConnection;

public class ExURL {
    public static void main(String[] args){
        try{
            String s = "http://142.250.219.14/";//ou google.com
            System.out.println("Acessando o site: "+s);
            
            URL url = new URL(s);
            /*System.out.println(url.getProtocol());
            System.out.println(url.getHost());
            System.out.println(url.getFile());
            System.out.println(url.toExternalForm());

            URLConnection uc = url.openConnection();
            System.out.println(uc.getContentType());
            System.out.println(uc.getConnectTimeout());
            
            InputStream is = uc.getInputStream();
            int caractere;
            while((caractere = is.read()) != -1){
                System.out.print((char)caractere);
            }*/
            
            HttpURLConnection huc = (HttpURLConnection) url.openConnection();
            huc.setRequestMethod("GET");
            huc.setReadTimeout(15000);
            huc.connect();
            
            System.out.println(huc.getContentType());
            System.out.println(huc.getContentLength());
            
            InputStream is = huc.getInputStream();
            int caractere;
            while((caractere = is.read()) != -1){
                System.out.print((char)caractere);
            }
        }catch(MalformedURLException mue){
            mue.printStackTrace();
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }
}