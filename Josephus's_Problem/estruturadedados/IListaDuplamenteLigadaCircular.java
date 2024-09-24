package estruturadedados;
/**
 * A interface IListaDuplamenteLigadaCircular define os métodos para manipulação de uma
 * lista duplamente ligada circular.
 */
public interface IListaDuplamenteLigadaCircular {
    
    /**
     * Verifica se a lista esta vazia.
     */
    public boolean estaVazia(); 

    /**
     * Insere um novo objeto no início da lista.
     */
    public void inserirInicio(Object novo); 

    /**
     * Insere um novo objeto no fim da lista.
     */
    public void inserirFim(Object novo);
    
    /**
     * Insere um novo objeto após o no que contem a chave especificada.
     */
    public boolean inserirApos(long chave, Object novo);

    /**
     * Remove e retorna o objeto no inicio da lista.
     */
    public Object removerInicio();

    /**
     * Remove e retorna o objeto no fim da lista.
     */
    public Object removerFim();
    
    /**
     * Remove e retorna o objeto que contem a chave especificada.
     */
    public Object remover(long chave);
    
    /**
     * Retorna uma representação em String da lista, comecando do fim.
     */
    public String toStringDoFim();
    
    /**
     * Retorna o no inicial da lista.
     */
    public No getInicio();
    
    /**
     * Retorna o no final da lista.
     */
    public No getFim();
    
    /**
     * Define o no final da lista.
     */
    public void setFim(No fim);
    
    /**
     * Define o no inicial da lista.
     */
    public void setInicio(No inicio);
}
