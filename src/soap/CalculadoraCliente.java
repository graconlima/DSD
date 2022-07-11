package soap;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.ws.Service;
import javax.xml.namespace.QName;

public class CalculadoraCliente {
    public static void main(String[] args) throws MalformedURLException {
        URL url = new URL("http://localhost:9876/calcsoap?wsdl");
        QName qname = new QName("http://soap/", "ServicoCalculadoraService");
        
        Service ws = Service.create(url, qname);
        InterfaceCalculadora calculadora = ws.getPort(InterfaceCalculadora.class);
        System.out.println("Soma (7+7): " + calculadora.soma(7, 7));
        System.out.println("Subt (7-7): "+ calculadora.subtracao(7, 7));
        System.out.println("Multi (7*7): "+ calculadora.multiplicacao(7, 7));
        System.out.println("Divi (7/7): "+ calculadora.divisao(7, 7));
    }
}
