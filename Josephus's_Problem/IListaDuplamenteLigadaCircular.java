/**
 * @author Julio Arakaki
 * @version 20190520
 */
public interface IListaDuplamenteLigadaCircular{
    public boolean estaVazia(); 
    
    public void inserirInicio(Object novo); 

    public void inserirFim(Object novo);
    
    public boolean inserirApos(long chave, Object novo);

    public Object removerInicio();

    public Object removerFim();
    
    public Object remover(long chave);
    
    public String toStringDoFim();
    
    public No getInicio();
    
    public No getFim();
    
    public void setFim(No fim);
    
    public void setInicio(No inicio);
}
