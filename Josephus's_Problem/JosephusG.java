import estruturadedados.*;
/**
 * Esta classe JosephusG representa a lógica base para o problema de Josephus.
 * Ela mantém um ambiente bidimensional de pessoas, gerencia o status das pessoas
 * (vivas ou mortas) e inicializa o ambiente.
 */
public class JosephusG {
    Pessoa _ambiente[][];
    int passo, qtd;
    Pessoa obj[]; 
    
    /**
     * JosephusG Construtor
     *
     * @param linhas Quantidade de linhas da matriz
     * @param colunas Quantidade de colunas da matriz
     */
    JosephusG(int linhas, int colunas) {
        setAmbiente(new Pessoa[linhas][colunas]);
        iniciarAmbiente();
    }

    
    /**
     * Método getAmbiente
     *
     * @return matriz do tipo 'Pessoa'
     */
    Pessoa[][] getAmbiente() {
        return _ambiente;
    }
    
  
    /**
     * Método setStatusAmbiente
     * Define o status de uma pessoa no ambiente.
     * @param status boolean como novo valor do campo do objeto
     * @param i inteiro indicando a linha da matriz
     * @param j inteiro indicando a coluna da matriz
     */
    public void setStatusAmbiente(boolean status, int i, int j) {
        _ambiente[i][j].setStatus(status);
    }

   
    /**
     * Método setAmbiente
     * Define o ambiente de pessoas.
     * @param ambiente atualiza a matriz
     */
    void setAmbiente(Pessoa[][] ambiente) {
        _ambiente = ambiente;
    }
    
    /**
     * Método iniciarAmbiente
     * Inicializa o ambiente com objetos do tipo 'Pessoa'.
     */
    public void iniciarAmbiente() {
        int cont = 1;
        for (int i = 0; i < getAmbiente().length; i++) {
            for (int j = 0; j < getAmbiente()[i].length; j++) {
                getAmbiente()[i][j] = new Pessoa(true, cont); // Inicia todas as pessoas com a condicao 'true'
                cont++;
            }
        }
    }

  
    /**
     * Método devolverStatus
     *
     * @param linha inteiro indicando a linha da matriz
     * @param coluna inteiro indicando a coluna da matriz
     * @return boolean do campo 'condicao' de um objeto da classe 'Pessoa'
     */
    boolean devolverStatus(int linha, int coluna) {
        return getAmbiente()[linha][coluna].getStatus(); //True para viva e False para morta
    }
}

