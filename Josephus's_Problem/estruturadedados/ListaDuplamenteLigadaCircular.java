package estruturadedados;
import java.util.Random;
/**
 * A classe ListaDuplamenteLigadaCircular implementa a interface 
 * IListaDuplamenteLigadaCircular, representando uma lista duplamente ligada circular.
 * 
 * Nesta lista, cada no possui referências tanto para o proximo no quanto para o no anterior,
 * e o ultimo no está ligado ao primeiro, formando um circulo, sendo assim uma lista circular.
 */
public class ListaDuplamenteLigadaCircular implements IListaDuplamenteLigadaCircular {
    private No inicio; // referência para o primeiro elemento
    private No fim;    // referência para o último elemento

    int qtdNos; // Quantidade de nós
    private Random r = new Random(System.currentTimeMillis());

    /**
     * Construtor da classe ListaDuplamenteLigadaCircular.
     * Inicializa a lista vazia.
     */
    public ListaDuplamenteLigadaCircular() {   
        setInicio(null);
        setFim(null);
        setQtdNos(0);
    }

    /**
     * Method getInicio
     *
     * @return endereco do primeiro no
     */
    public No getInicio() {
        return inicio;
    }

 
    /**
     * Método setInicio
     *
     * @param inicio atualiza o no inicial
     */
    public void setInicio(No inicio) {
        this.inicio = inicio;
    }

    /**
     * Method getFim
     *
     * @return endereco do ultimo no
     */
    public No getFim() {
        return fim;
    }

   
    /**
     * Método setFim
     *
     * @param fim atualiza o endereco do ultimo no
     */
    public void setFim(No fim) {
        this.fim = fim;
    }

   /**
     * Metodo getQtdNos
     *
     * @return qtidade de nos
     */
    public int getQtdNos() {
        return this.qtdNos;
    }

    /**
     * Metodo setQtdNos
     *
     * @param qtdNos atualiza qtde de nos
     */
    private void setQtdNos(int qtdNos) {
        this.qtdNos = qtdNos;
    }

    
    /**
     * Método estaVazia
     *
     * @return true se o inicio for null, false caso contrario
     */
    public boolean estaVazia() {
        return getInicio() == null; 
    }

 
    /**
     * Método inserirInicio
     *
     * @param elem objeto a ser inserido no inicio da lista
     */
    public void inserirInicio(Object elem) {
        No novoNo = new No(elem, r.nextLong());

        if (estaVazia()) { 
            setFim(novoNo);
        } else {
            getInicio().setAnterior(novoNo);
        }
        novoNo.setProximo(getInicio());
        setInicio(novoNo);
        getFim().setProximo(getInicio());
        getInicio().setAnterior(getFim());
        setQtdNos(getQtdNos() + 1);
    }

 
    /**
     * Método inserirFim
     *
     * @param elem objeto a ser inserido no fim da lista
     */
    public void inserirFim(Object elem) {
        No novoNo = new No(elem, r.nextLong());

        if (estaVazia()) {
            setInicio(novoNo);
        } else {
            getFim().setProximo(novoNo);
            novoNo.setAnterior(getFim());
        }
        setFim(novoNo);
        getFim().setProximo(getInicio());
        getInicio().setAnterior(getFim());
        setQtdNos(getQtdNos() + 1);
    }

   
    /**
     * Método removerInicio
     *
     * @return objeto no inicio da lista apos a remocao
     */
    public No removerInicio() {
        No temp = null;
        if (getInicio() != null && getFim() != null) {
            temp = getInicio();

            if (getInicio() == getFim()) {
                setInicio(null);
                setFim(null);
            } else {
                getFim().setProximo(getInicio().getProximo());
                getInicio().getProximo().setAnterior(getFim());
                setInicio(getInicio().getProximo());
            }
            temp.setAnterior(null);
            temp.setProximo(null);
        }
        setQtdNos(getQtdNos() - 1);
        return temp;
    }


    /**
     * Método removerFim
     * 
     * @return objeto no fim da lista apos a remocao.
     */
    public No removerFim() {
        No temp = null;
        if (getFim() != null && getInicio() != null) {
            temp = getFim();

            if (getFim() == getInicio()) {
                setFim(null);
                setInicio(null);
            } else {
                getInicio().setAnterior(getFim().getAnterior());
                setFim(getFim().getAnterior());
                getFim().setProximo(getInicio());
            }
            temp.setAnterior(null);
            temp.setProximo(null);
        }
        setQtdNos(getQtdNos() - 1);
        return temp;
    }

    /**
     * Método inserirApos
     * @param key Procurar por no que contem o valor especificado
     * @param elem objeto a ser inserido apos o no que contem a chave especificada.
     * @return true se foi possivel inserir, false caso o contrario
     */
    public boolean inserirApos(long key, Object elem) {
        No noAtual = getInicio();

        while ((Long) noAtual.getId() != key) {
            if (noAtual == getFim()) {
                return false;
            }
            noAtual = noAtual.getProximo();
        }
        No novoNo = new No(elem, r.nextLong());

        if (noAtual == getFim()) {
            novoNo.setProximo(getInicio());
            setFim(novoNo);
        } else {
            novoNo.setProximo(noAtual.getProximo());
            noAtual.getProximo().setAnterior(novoNo);
        }
        novoNo.setAnterior(noAtual);
        noAtual.setProximo(novoNo);
        setQtdNos(getQtdNos() + 1);
        return true;
    }

  
    /**
     * Método remover
     * @param chave Procurar por o no que contem essa chave
     * @return objeto que contem a chave especificada.
     */
    public No remover(long chave) {
        No noAtual = null;
        if (getInicio() != null) {
            noAtual = getInicio();

            while ((Long) noAtual.getId() != chave) {
                if (noAtual == getFim()) {
                    return null;
                }
                noAtual = noAtual.getProximo();
            }

            if (noAtual == getInicio()) {
                noAtual = removerInicio();
            } else if (noAtual == getFim()) {
                noAtual = removerFim();
            } else {
                noAtual.getAnterior().setProximo(noAtual.getProximo());
                noAtual.getProximo().setAnterior(noAtual.getAnterior());
                setQtdNos(getQtdNos() - 1);
            }
            noAtual.setAnterior(null);
            noAtual.setProximo(null);
        }
        return noAtual;
    }

  
    /**
     * Método toString
     *
     * @return uma representacao em String da lista, começando do inicio.
     */
    public String toString() {
        String s = "[ ";
        No noAtual = getInicio();
        if (noAtual != null) {
            do {   
                s = s + noAtual.toString() + " ";  
                noAtual = noAtual.getProximo();   
            } while (noAtual != getInicio());
        }
        s = s + "]";
        return s;
    }

    
    /**
     * Método toStringDoFim
     * 
     * @return Uma representacao em String da lista, comecando do fim.
     */
    public String toStringDoFim() {
        String s = "[ ";
        No noAtual = getFim();
        if (noAtual != null) {
            do {
                s = s + noAtual.toString() + " ";
                noAtual = noAtual.getAnterior();
            } while (noAtual != getFim());
        }
        s = s + "]";
        return s;
    }

       /**
        * Método limparLista
        * Remove todos os nos da lista.
        */
       public void limparLista() {
        No temp = getInicio();

        while (temp != getFim()) {
            removerFim();
        }

        setInicio(null);
        setFim(null);
    }
}
