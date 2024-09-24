
/**
 * Escreva uma descrição da classe Pessoa aqui.
 * 
 * @author (seu nome) 
 * @version (um número da versão ou uma data)
 */
public class Pessoa
{
  boolean condicao;
  int id;
  
  /**
   * Pessoa Construtor
   *
   * @param status boolean para representar o estado do objeto
   * @param indice inteiro como indentificador do objeto
   */
  Pessoa(boolean status, int indice){
      condicao = status;
      id = indice;
  }

  
  /**
   * Método setStatus
   *
   * @param status boolean que modificara o campo 'condicao'
   */
  public void setStatus(boolean status){
      this.condicao = status;
  }
  
  /**
   * Método setId
   *
   * @param indice inteiro que modificara o campo 'id'
   */
  public void setId(int indice){
      this.id = indice;
  }
  
  /**
   * Método getId
   *
   * @return inteiro do campo 'id' do objeto 
   */
  public int getId(){
      return this.id;
  }
  
  /**
   * Método getStatus
   *
   * @return boolean do campo 'condicao' do objeto
   */
  public boolean getStatus(){
      return this.condicao;
  }
  
  /**
   * Método toString
   *
   * @return Uma representacao em String da pessoa
   */
  public String toString(){
      String s = condicao + " " + id + "\n";
      return s;
  }
 
}
