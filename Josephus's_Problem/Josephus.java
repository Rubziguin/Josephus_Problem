import javax.swing.JOptionPane;
/**
 * Escreva uma descrição da classe Aplicacao aqui.
 * 
 * @author (seu nome) 
 * @version (um número da versão ou uma data)
 */
public class Josephus
{
     static int passo = 3;
     static IListaDuplamenteLigadaCircular l = new ListaDuplamenteLigadaCircular();
     Pessoa obj[]; 
     
     Josephus(IListaDuplamenteLigadaCircular l){
         this.l = l;
    }   
    public void preencher(int qtd){
        obj = new Pessoa[qtd];
        for(int i = 0; i < qtd; i++){
            obj[i] = new Pessoa(true, i+1);
            //obj[i].mostrar();
            l.inserirFim(obj[i]);
        }
    }
  
    public void laMuerte(int qtd){
        int cont, i = qtd;
        boolean status;
        Pessoa dead;
        No inicio, fim, prox;
        //System.out.println(l);
        
        //Comecar a partir do inicio da lista
        //Andar passo-1
        //Reiniciar o inicio com o proximo vivo
        //Procurar o proximo a ser morto
        
        while(i > 1){ //Executar ate Josephus restar vivo
            inicio = l.getInicio();
            cont = 0;
            while(cont < passo-1){ //Procurar la muerte
                prox = inicio.getProximo();
                inicio = prox;
                dead = (Pessoa)inicio.getConteudo();
                status = dead.getStatus();
                if(status == true)
                    cont++;
            }
            
            dead = (Pessoa)inicio.getConteudo();
            dead.setStatus(false); //La Muerte
            System.out.printf("%d     ", dead.getId());
            
            
            prox = inicio.getProximo();
            dead = (Pessoa)prox.getConteudo();
            status = dead.getStatus();
            if(status == false){ //Verifica se o proximo esta morto
                while(status == false){ //Procura o proximo vivo
                    inicio = prox;
                     prox = inicio.getProximo();
                    dead = (Pessoa)prox.getConteudo();
                    status = dead.getStatus();
                }
            }
            l.setInicio(prox); //Atualiza o inicio
            
            i--;
        } System.out.println("\n" + l);  
    
    }
    

}
