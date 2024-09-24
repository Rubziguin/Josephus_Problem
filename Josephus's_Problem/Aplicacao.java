
/**
 * Escreva uma descrição da classe Aplciacao aqui.
 * 
 * @author (seu nome) 
 * @version (um número da versão ou uma data)
 */
public class Aplicacao
{
    public static void main(String args[]){
        System.out.println("Hello worlkd");
        int qtd = 5;
        IListaDuplamenteLigadaCircular l = new ListaDuplamenteLigadaCircular();
        Pessoa obj[] = new Pessoa[qtd];
     
            
        Josephus A = new Josephus(l);
        A.preencher(qtd);
        A.laMuerte(qtd);
    }
}
