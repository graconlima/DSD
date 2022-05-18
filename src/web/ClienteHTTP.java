package web;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ClienteHTTP extends JFrame{
    
    JTextArea jta = new JTextArea(20, 20);
    GridLayout gl = new GridLayout(1,2);
    JPanel jp = new JPanel(gl);
    JTextField jtf = new JTextField("http://");
    JComboBox<String> jc = new JComboBox<String>(new String[]{"GET", "POST", "PUT", "DELETE"});
    JButton jb = new JButton("Enviar");
    
    ClienteHTTP(){
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        jta.setLineWrap(true);
        add(jta, BorderLayout.NORTH);
        
        jb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                jta.setText(acessar(jtf.getText(), jc.getItemAt(jc.getSelectedIndex())));
                setVisible(true);                        
            }
        });
        
        jp.add(jc, BorderLayout.EAST);
        jp.add(jtf, BorderLayout.CENTER);
        jp.add(jb, BorderLayout.WEST);
        
        add(jp, BorderLayout.SOUTH);
        setVisible(true);        
    }

    String acessar(String s, String metodo){
    
        StringBuffer sb = new StringBuffer();
        try{
            URL url = new URL(s);
            HttpURLConnection huc = (HttpURLConnection) url.openConnection();
            huc.setRequestMethod(metodo);
            
            if(metodo.equals("POST")){
                huc.setRequestProperty("Accept", "application/xml");
                huc.setRequestProperty("Content-Type", "application/xml");
                huc.setDoOutput(true);
                
                OutputStream os = huc.getOutputStream();
                os.write(jta.getText().trim().getBytes());
                os.close();
                
                BufferedReader br = new BufferedReader(new InputStreamReader(huc.getInputStream()));
                while((s = br.readLine()) != null){
                    sb.append(s);
                }
                
                jta.setText(sb.toString());
            }
            
            huc.setReadTimeout(15000);
            huc.connect();
            
            InputStream is = huc.getInputStream();
            int caractere;
            while((caractere = is.read()) != -1){
                sb.append((char) caractere);
            }
            
        }catch(MalformedURLException mue){
            sb.append(mue.getMessage());
        }catch(IOException ioe){
            sb.append(ioe.getMessage());
        }
        
        return sb.toString();
    }
    
    public static void main(String[] args) {
        new ClienteHTTP();
    }
}
