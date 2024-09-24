import estruturadedados.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.Container;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

/**
 * A classe JosephusGraphic representa a interface gráfica para o problema de Josephus.
 * Esta classe herda de JosephusG e implementa ActionListener para manipulação de eventos.
 * 
 * Esta implementação usa uma interface gráfica com Java Swing para visualizar o processo de
 * eliminação de pessoas até que reste apenas uma.
 * 
 */

public class JosephusGraphic extends JosephusG implements ActionListener {
    JButton _jbtnExecutar, _jbtnReiniciar, _jbtnConfigurar, _jbtnSair, _jbtnPausar;
    Container _cBotoes = null;
    Container _cTextField = null;
    JFrame _jframe = null;
    Container _cPessoas = null;
    JLabel[][] _labels = null;
    JLabel _survivor;
    JTextField _qtdPessoas, _intervalo, _tiempo;
    JMenuBar _menuBar; 
    Color _corVivo = null;
    Color _corMorto = null;
    Color _corBorda = null;
    LaExecution _simular;
    IListaDuplamenteLigadaCircular l;
    int pass, qtdp, ant, sl;
    boolean verified;
   
    /**
     * JosephusGraphic Construtor
     *
     * @param linha Quantidade de linhas da matriz
     * @param coluna Quantidade de colunas da matriz 
     */
    JosephusGraphic(int linha, int coluna) {
        super(linha, coluna);
        _jframe = new JFrame("Josephus's Problem");
        _jframe.setSize(500, 500);
        _jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        _cPessoas = new JPanel();
        _cPessoas.setLayout(new GridLayout(linha, coluna));
        _labels = new JLabel[linha][coluna];
        setCorVivo(Color.BLUE);
        setCorMorto(Color.RED);
        setCorBorda(Color.GRAY);
        _cTextField = new JPanel();
        _cTextField.setLayout(new FlowLayout());
        _cBotoes = new JPanel();
        _cBotoes.setLayout(new FlowLayout());
        
        _tiempo = new JTextField(10);
        _tiempo.setText("300");
        _qtdPessoas = new JTextField(10);
        _qtdPessoas.setText("20");
        _qtdPessoas.setForeground(Color.GRAY);
        
        _intervalo = new JTextField(10);
        _intervalo.setText("3");
        _intervalo.setForeground(Color.GRAY);
        _menuBar = new JMenuBar();
    }

   
    /**
     * Método adicionarComponentes
     * Adiciona os componentes à interface gráfica.
     */
    public void adicionarComponentes() {
        Container pane = _jframe.getContentPane();
        inserePainelAmbiente(pane);
        inserePainelTextField(pane);
        inserePainelBotoes(pane);
    }

   
    /**
     * Método inserePainelBotoes
     * Insere o painel de botões na interface gráfica.
     * @param pane Um container que recebera o container '_cBotoes'
     */
    private void inserePainelBotoes(Container pane) {
        _jbtnExecutar = new JButton("Comecar");
        _jbtnExecutar.setToolTipText("Iniciar o processo de execuoes");
        _jbtnExecutar.addActionListener(this);
        
        _jbtnReiniciar = new JButton("Reiniciar");
        _jbtnReiniciar.setToolTipText("Reiniciar estado dos individuos");
        _jbtnReiniciar.addActionListener(this);

        _jbtnConfigurar = new JButton("Configurar");
        _jbtnConfigurar.setToolTipText("Configurar o processo");
        _jbtnConfigurar.addActionListener(this);

        _jbtnPausar = new JButton("Pausar");
        _jbtnPausar.setToolTipText("Pausar processo");
        _jbtnPausar.addActionListener(this);
        _jbtnPausar.setEnabled(false);

        _jbtnSair = new JButton("Sair");
        _jbtnSair.setToolTipText("Terminar programa");
        _jbtnSair.addActionListener(this);

        _cBotoes.add(_jbtnExecutar);
        _cBotoes.add(_jbtnReiniciar);
        _cBotoes.add(_jbtnConfigurar);
        _cBotoes.add(_jbtnPausar);
        _cBotoes.add(_jbtnSair);
        pane.add("South", _cBotoes); //Coloca esses botoes na parte de baixo da janela
    }

    /**
     * Método inserePainelTextField
     * Insere o painel de text fields na interface gráfica.
     * @param pane Container que ira possuir como componentes texfields e labels
     */
    private void inserePainelTextField(Container pane) {
        _survivor = new JLabel("        ");
        _menuBar.add(_survivor);
        _survivor.setVisible(false);
        JLabel people = new JLabel("   Quantidade de pessoas: ");
        _qtdPessoas = new JTextField(10);
        _qtdPessoas.setText("100");
        _qtdPessoas.setForeground(Color.GRAY);
        _menuBar.add(people);
        _menuBar.add(_qtdPessoas);
        _qtdPessoas.setEnabled(false);
        
        JLabel pass = new JLabel("Passo: ");
        _intervalo = new JTextField(10);
        _intervalo.setText("50");
        _intervalo.setForeground(Color.GRAY);
        _menuBar.add(pass);
        _menuBar.add(_intervalo);
        _intervalo.setEnabled(false);
        
        JLabel tt = new JLabel("Tempo: ");
        _menuBar.add(tt);
        _tiempo = new JTextField(10);
        _tiempo.setText("300");
        _menuBar.add(_tiempo);
        _tiempo.setEnabled(false);
        _tiempo.setForeground(Color.GRAY);
        pane.add(_menuBar);
    }

  
    /**
     * Método inserePainelAmbiente
     * Insere o painel de ambiente na interface gráfica.
     * @param pane Container que recebera o container '_cPessoas'
     */
    private void inserePainelAmbiente(Container pane) {
        for (int i = _labels.length - 1; i >= 0; i--) {
            for (int j = _labels[i].length - 1; j >= 0; j--) {
                _labels[i][j] = new JLabel("" + i + "," + j);
                _labels[i][j].setPreferredSize(new Dimension(-50, 50));
                _labels[i][j].setMaximumSize(new Dimension(-50, 50));  //Configura as dimensoes dos quadrados
                _labels[i][j].setMinimumSize(new Dimension(-50, 50));
                _labels[i][j].setToolTipText("(" + (i+1) + "," + (j+1) + ")");
                _labels[i][j].setOpaque(true);
                _labels[i][j].setForeground(getCorVivo());
                _labels[i][j].setBorder(BorderFactory.createLineBorder(getCorBorda()));
                _labels[i][j].setBackground(getCorVivo());
                _cPessoas.add(_labels[i][j], 0);
            }
        }
        pane.add("North", _cPessoas);
    }

 
    /**
     * Método desenhaAmbiente
     * Desenha o ambiente, atualizando a interface gráfica com os status dos objetos.
     */
    private void desenhaAmbiente() {
        for (int i = 0; i < getAmbiente().length; i++) {
            for (int j = 0; j < getAmbiente()[i].length; j++) {
                if (getAmbiente()[i][j].getStatus()) {
                    _labels[i][j].setBackground(getCorVivo());
                    _labels[i][j].setForeground(getCorVivo());
                } else {
                    _labels[i][j].setBackground(getCorMorto());
                    _labels[i][j].setForeground(getCorMorto());
                }
            }
        }
    }

    
    /**
     * Método mostraAmbienteGUI
     * Mostra a interface gráfica do usuário.
     */
    public void mostraAmbienteGUI() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        adicionarComponentes();
        _jframe.pack();
        _jframe.setVisible(true);
    }

   
    /**
     * Método iniciarAnimacao
     * Inicia a animação do processo de eliminacao de pessoas.
     */
    public void iniciarAnimacao() {
        _simular = new LaExecution();
        _simular.start();
        _jbtnExecutar.setEnabled(false); 
        _jbtnPausar.setEnabled(true);
    }

   
    /**
     * Método finalizarAnimacao
     * Finaliza a animação do processo de eliminação de pessoas.
     */
    public void finalizarAnimacao() {
        _simular.continuar = false;
        _jbtnExecutar.setEnabled(true);
        _jbtnPausar.setEnabled(false);
        _simular = null;
    }

    
    /**
     * Método actionPerformed
     * Manipula os eventos de ação dos botões.
     * @param e Um evento ocorrido e capturado pelo 'ouvinte'
     */
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        if (comando.equals("Sair")) {
            System.exit(0);
        } else if (comando.equals("Comecar")) {
            iniciarAnimacao();
            _jbtnConfigurar.setEnabled(false);
            _jbtnReiniciar.setEnabled(false);
        } else if (comando.equals("Reiniciar")) {
            qtdp = getQtdPessoas();
            pass = getPasso();
            sl = getSleep();
            verified = verificarQtd(qtdp);
            if(verified == true)
                verified = verificarPasso(pass);
            
            if(verified == true)
                verified = verificarSleep(sl);
                
            if(verified == true){
                _survivor.setVisible(false);
                desableConfig();
                _jbtnConfigurar.setEnabled(true);
                resetarAmbiente();
                resetarLista();
                atualizarLabels(qtdp);
                desenhaAmbiente();
            }

        } else if (comando.equals("Configurar")) { //Quando pressionado habilida editar as opcoes do programa
            ableConfig();
            ant = getQtdPessoas();
            _jbtnConfigurar.setEnabled(false);
        } else if (comando.equals("Pausar")) { //Quando pressionado pausa
            finalizarAnimacao();     
            _jbtnReiniciar.setEnabled(true);
            _jbtnConfigurar.setEnabled(true);
        } 
    }

    
    /**
     * Método ableConfig
     * Habilita os campos de configuracao.
     */
    private void ableConfig() {  
        _qtdPessoas.setEnabled(true);
        _qtdPessoas.setForeground(Color.BLACK);
        _intervalo.setEnabled(true);
        _intervalo.setForeground(Color.BLACK);
        _tiempo.setEnabled(true);
        _tiempo.setForeground(Color.BLACK);
        _jbtnExecutar.setEnabled(false);   
    }

   
    /**
     * Método desableConfig
     * Desabilita os campos de configuração.
     */
    private void desableConfig() {
        _qtdPessoas.setEnabled(false);
        _qtdPessoas.setForeground(Color.GRAY);
        _intervalo.setEnabled(false);
        _intervalo.setForeground(Color.GRAY); 
        _tiempo.setEnabled(false);
        _tiempo.setForeground(Color.GRAY);
        _jbtnExecutar.setEnabled(true); 
    }

    /**
     * Classe interna responsável por executar a animacao e o algoritmo do problema de Josephus.
     */
    private class LaExecution extends Thread {
        public boolean continuar = true;

        public void run() {
            try {
                int cont, i = getQtdPessoas(), pos = 0, qtdCol, col, linha, clock = getSleep();
                preencherLista(i);
                int passo = getPasso();
                boolean status;
                Pessoa dead;
                No inicio, prox, newstart;
                newstart = l.getInicio();
                qtdCol = getAmbiente()[0].length;
                _survivor.setVisible(true);
                while (continuar && i > 1) {   //Loop ate restar 1 ou ser interrompido externamente        
                    inicio = newstart; //Define um novo inicio para a proxima busca
                    cont = 0;
                    while (cont < passo - 1) { //Procura qual objeto recebera o status de false na lista
                        prox = inicio.getProximo();
                        inicio = prox;
                        dead = (Pessoa) inicio.getConteudo();
                        status = dead.getStatus();
                        if (status == true) cont++;
                    }       
                   
                    dead = (Pessoa) inicio.getConteudo(); //Pega o individuo a ser morto
                    dead.setStatus(false); //Define o status do objeto 
                    pos = dead.getId() - 1;
                    _survivor.setText("Individuo morto: " + (pos + 1));
                    linha = pos / qtdCol; //Calcula a linha do objeto que tera o status alterado
                    col = pos % qtdCol; //Calcula a coluna do objeto que tera o status alterado
                 
                    setStatusAmbiente(false, linha, col); //Atualiza o status do objeto na matriz
                    
                    prox = inicio.getProximo(); 
                    dead = (Pessoa) prox.getConteudo();
                    status = dead.getStatus(); //Verifica se o proximo objeto da lista esta com o status verdadeiro
                   
                    if (status == false) {
                        while (!status) { //Procura o proximo objeto da lista com o status verdadeiro
                            inicio = prox;
                            prox = inicio.getProximo();
                            dead = (Pessoa) prox.getConteudo();
                            status = dead.getStatus();
                        }
                    }
                    
                    newstart = prox; //Redefine o inicio da lista
        
                    desenhaAmbiente(); 
                    sleep(clock);
                    
                    //Procura o ultimo objeto com status true
                    if (i == 2) {
                        status = false;
                        while (!status) {
                            inicio = prox;
                            prox = inicio.getProximo();
                            dead = (Pessoa) prox.getConteudo();
                            status = dead.getStatus();
                        }
                        _survivor.setText("Sobrevivente: " + dead.getId());
                    }
                    
                    i--;
                }
                _jbtnReiniciar.setEnabled(true);   
                _jbtnConfigurar.setEnabled(true);
                _jbtnPausar.setEnabled(false);
            } catch (InterruptedException e) {
                System.out.println("ERRO INESPERADO");
                System.exit(0);
            }    
        }
        
    }


  
    /**
     * Método getQtdPessoas
     * 
     * @return A quantidade de pessoas do JTextfield ou 0 caso seja invalido
     */
    private int getQtdPessoas() {        
        int qtd = 0, q = -1, i = 0;
        boolean ok = true;
        if (!_qtdPessoas.getText().isEmpty()) { //Verifica se nao eh vazia
            while (i < _qtdPessoas.getText().length() && ok) {
                if (!Character.isDigit(_qtdPessoas.getText().charAt(i))) { //Verifica se e digito
                     ok = false;
                }
                i++;
            }
            if (ok) {
                int tam = getAmbiente().length * getAmbiente()[0].length;
                q = Integer.parseInt(_qtdPessoas.getText());
                if (q > 1 && q <= tam) { //Verifica se o valor pertence ao intervalo
                    qtd = q;    
                }
            }
        }
        return qtd;
    }

  
    /**
     * Método getPasso
     *
     * @return O valor do passo capturado no JTextfield ou 0 caso invalido
     */
    private int getPasso() {
        int passo = 0, qtd = -1, i = 0;
        boolean ok = true;
        if (!_qtdPessoas.getText().isEmpty()) { //Verifica se nao esta vazia
            while (i < _qtdPessoas.getText().length() && ok) {
                if (!Character.isDigit(_qtdPessoas.getText().charAt(i))) { //Verifica se e digito
                    ok = false;
                }
                i++;
            }
            if (ok) {
                qtd = Integer.parseInt(_qtdPessoas.getText());
                if (qtd > 1 && !_intervalo.getText().isEmpty()) { 
                    i = 0;
                    while (i < _intervalo.getText().length() && ok) {
                        if (!Character.isDigit(_intervalo.getText().charAt(i))) {
                            ok = false;
                        }
                        i++;
                    }
                    if (ok) {
                        int p = Integer.parseInt(_intervalo.getText());
                        if (p > 0 && p <= qtd) {  //Verifica se pertence ao intervalo
                            passo = p;   
                        }    
                    }
                } 
            }
        }
        return passo;
    }

    
    /**
     * Método getSleep
     *
     * @return O valor de sleep capturado no JTextfield ou -1 caso invalido
     */
    private int getSleep() {
        int clock = -1, i = 0;
        boolean ok = true;
        if (!_tiempo.getText().isEmpty()) { //Verifica se nao esta vazia
            while (i < _tiempo.getText().length() && ok) {
                if (!Character.isDigit(_tiempo.getText().charAt(i))) { //Verifica se e digito 
                    ok = false;
                }
                i++;
            }
            if (ok) {
                int s = Integer.parseInt(_tiempo.getText());
                if (s >= 0) { 
                    clock = s;
                }      
            }
        }
        return clock;
    }

   
    /**
     * Método verificarQtd
     *
     * @param qtd Valor da quantidade de pessoas
     * @return valor true se valido, false se invalido
     */
    private boolean verificarQtd(int qtd) {
        boolean ok = true;
        if(qtd == 0){
            ok = false;
            JOptionPane.showMessageDialog(null, "Campo de 'Quantidade de pessoas' invalido!", "Valor invalido", JOptionPane.ERROR_MESSAGE);
        }
        return ok;
    }

    
    /**
     * Método verificarPasso
     *
     * @param p Intervalo do passo
     * @return true se valido, false se invalido
     */
    private boolean verificarPasso(int p) {
        boolean ok = true;
        if(p == 0){
            ok = false;
            JOptionPane.showMessageDialog(null, "Campo de 'Passo' invalido!", "Valor invalido", JOptionPane.ERROR_MESSAGE);
        }
        return ok;
    }


    /**
     * Método verificarSleep
     *
     * @param time atualiza o valor do tempo de espera
     * @return true se o valor e valido, false se invalido
     */
    private boolean verificarSleep(int time) {
        boolean ok = true;
        if(time == -1){
            ok = false;
            JOptionPane.showMessageDialog(null, "Campo de 'Tempo' invalido!", "Valor invalido", JOptionPane.ERROR_MESSAGE);
        }
        return ok;
    }

       /**
        * Método preencherLista
        * @param qtd quantidade de objetos que a lista sera preenchida
        */
       public void preencherLista(int qtd) {
        l = new ListaDuplamenteLigadaCircular();
        obj = new Pessoa[qtd];
        for (int i = 0; i < qtd; i++) {
            obj[i] = new Pessoa(true, i + 1);
            l.inserirFim(obj[i]);
        }
    }

    
    /**
     * Método resetarAmbiente
     * Reseta o ambiente, configurando todos objetos na matriz com status 'true'
     */
    public void resetarAmbiente() {
        for (int i = 0; i < getAmbiente().length; i++) {
            for (int j = 0; j < getAmbiente()[i].length; j++) {
                getAmbiente()[i][j].setStatus(true);
            }
        }
    }

   
    /**
     * Método resetarLista
     * Reseta a lista de pessoas, configurando todos objetos da lista com status 'true'
     */
    public void resetarLista() {
        if (this.l != null) {
            for (int i = 0; i < obj.length; i++) {
                obj[i].setStatus(true);
            }
        }
    }

    
    /**
     * Método atualizarLabels
     * 
     * @param p define a quantidade de labels a serem mostradas.
     */
    private void atualizarLabels(int p) {
        int tam = getAmbiente().length * getAmbiente()[0].length;
        int qtdCol = getAmbiente()[0].length;
        int posL = p / qtdCol; //Calcula a partir de qual linha a label se tornara invisivel
        int posC = p % qtdCol; //Calcula a partir de qual coluna a label se tornara invisivel
        int posLA = ant / qtdCol; //Calcula a partir de qual linha a label se tornara visivel
        int posCA = ant % qtdCol; //Calcula a partir de qual coluna a label se tornara visivel

        if (p < ant) { //Verifica se o valor atual e menor que o anterior
            for (int j = posC; j < getAmbiente()[0].length; j++) {
                _labels[posL][j].setVisible(false);
            }
            if (posL != getAmbiente().length) { //Verifica se e a ultima linha
                for (int i = posL + 1; i < getAmbiente().length; i++) {
                    for (int j = 0; j < getAmbiente()[i].length; j++) {
                        _labels[i][j].setVisible(false);
                    }
                }
            }
        } else {
            int remaining = p - ant;
            for (int i = 0; i < remaining; i++) {
                _labels[posLA][posCA].setVisible(true);
                posCA++;
                if (posCA > getAmbiente()[0].length - 1) {
                    posCA = 0;
                    posLA++;
                }
            }  
        }
    }

    
    /**
     * Método getCorBorda
     *
     * @return A cor da borda.
     */
    protected Color getCorBorda() {
        return _corBorda;
    }

  
    /**
     * Método setCorBorda
     * Define a cor da borda.
     * @param borda Uma cor
     */
    protected void setCorBorda(Color borda) {
        _corBorda = borda;
    }

       /**
        * Método getCorVivo
        *
        * @return A cor da pessoa com status 'true'
        */
       protected Color getCorVivo() {
        return _corVivo;
    }

 
    /**
     * Método setCorVivo
     *
     * @param vivo Uma cor
     */
    protected void setCorVivo(Color vivo) {
        _corVivo = vivo;
    }

    
    /**
     * Método getCorMorto
     *
     * @return A cor das celulas com status de 'false'
     */
    protected Color getCorMorto() {
        return _corMorto;
    }

   
    /**
     * Método setCorMorto
     * Define a cor das celulas com status 'false'.
     * @param morto Uma cor
     */
    protected void setCorMorto(Color morto) {
        _corMorto = morto;
    }
    
}
