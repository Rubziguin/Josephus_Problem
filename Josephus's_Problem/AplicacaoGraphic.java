import estruturadedados.*;
/**
 * A classe AplicacaoGraphic é responsável por iniciar a aplicação gráfica
 * que demonstra o problema de Josephus.
 * 
 * Nesta implementação, a aplicação cria uma instância de JosephusGraphic com dois parâmetros:
 * o número de pessoas no círculo e o passo de eliminação. Em seguida, chama o método para
 * mostrar a interface gráfica do usuário (GUI).
 * 
 * @autores : Daniel Teles de Oliveira
 *            Joao Victor Torres Soares
 *            Rubens Rodrigues Maranesi
 */
public class AplicacaoGraphic
{
    /**
     * Este método cria uma matriz 10 por 10, e então chama o método mostraAmbienteGUI() para exibir a interface gráfica
     */
    public static void main(String args[]){
        JosephusGraphic jg = new JosephusGraphic(10, 10);
        jg.mostraAmbienteGUI();
    }
}

