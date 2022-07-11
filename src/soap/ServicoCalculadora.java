package soap;

import javax.jws.WebService;

@WebService(endpointInterface = "soap.InterfaceCalculadora")
public class ServicoCalculadora implements InterfaceCalculadora{
    
    public float soma(float num1, float num2){
        System.out.println("Operação SOMA acessada!");
        return num1+num2;
    }
    
    public float subtracao(float num1, float num2){
        System.out.println("Operação SUBTRACAO acessada!");
        if(num1 > num2)
            return num1-num2;
        else
            return num2-num1;
    }
    
    public float multiplicacao(float num1, float num2){
        System.out.println("Operação MULTIPLICACAO acessada!");
        return num1*num2;
    }
    
    public float divisao(float num1, float num2){
        System.out.println("Operação DIVISAO acessada!");
        if(num2==0){
            System.out.println("DIVISAO POR ZERO. NÃO EFETUADA");
            return 0;
        }else{
            return num1/num2;
        }
    }
}
