import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Classe para criar o mundo 1.
 * 
 * @author (Jhonatan Morais - jhonatanvinicius@gmail.com | jhonatan@dfjug.org) 
 * @version (1.0)
 */
public class Mundo1 extends World
{
    //Constantes da natureza do mundo
    public static final int LARGURA_CENARIO = 700; 
    public static final int ALTURA_CENARIO = 390; 
    public static final int QUANTIDADE_DE_QUADROS = 350; 
    public static final int TAMANHO_DO_QUADRO = 4; 
    public static final int NIVEL_DO_SOLO = 339; 
    public static final int FORCA_DE_GRAVIDADE = 5; 
    public static final String NOME_ARQUIVO_IMAGEM = "cenarios/mundo1/m1f1_"; 
    public static final String EXTENSAO_ARQUIVO_IMAGEM = ".png"; 

    private int quadroAtual = 1;
    private int cicloAtual = 0;
    private Gato heroi;
    private Placar pontuacao;

    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public Mundo1()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(LARGURA_CENARIO, ALTURA_CENARIO, 1);
        GreenfootImage cenarioInicial = new GreenfootImage("mundo1.png");
        setBackground(cenarioInicial);
        heroi = new Gato();
        pontuacao = new Placar();
        addObject(heroi, 100, 303);
        addObject(new RunnerCat(), 67, 18);
        addObject(pontuacao, 67, 41);
         
        
       
        //addObject(new LHorizontal(), 100, heroi.alturaDosPes() );
        //addObject(new LVertical(), 100, 303);


    }
    public Placar pontuacao(){
    
        return pontuacao;
    }
    
    public void acrescentaPontos(int valor){
        
        pontuacao.addPontos(valor);
    
    }

    /**
     * Retorna o valor do ciclo atual do jogo
     */
    public int cicloAtual(){
        return cicloAtual;
    }

    /**
     * Conta os ciclos do jogo
     */
    private void contaCiclo(){
        cicloAtual++;
        if(cicloAtual > 1000){
            cicloAtual = 0;
        }

    }

    /**
     * Aplica a força da gravidade sobre os objetos o mundo
     */
    public void aplicarForcaDaGravidade(){
        if(heroi.alturaAtual() > 0){
            heroi.setLocation(heroi.getX(), heroi.getY() + FORCA_DE_GRAVIDADE);

        }

    }

    public void act(){

        projetor(proximaCena());
        aplicarForcaDaGravidade();
        criadorDeMoedas();
        criadorDeBichos();
        contaCiclo();
    }
    private void criadorDeMoedas(){
       if(cicloAtual() % (Greenfoot.getRandomNumber(50) + 50) == 0) {
           
           int y = Greenfoot.getRandomNumber(100) + 200;
           
           addObject(new Moedaria(), 700,y );
        
        }    
    
       
    }
     private void criadorDeBichos(){
       if(cicloAtual() % (Greenfoot.getRandomNumber(50) + 50) == 0) {
           
                    
           addObject(new Bicho(), 700,321);
        
        }    
    
       
    }
    
    

    /**
     *  Solicita que o cenário seja atualizado com a proxima cena
     */
    private void projetor(GreenfootImage novaCena){
        setBackground(novaCena);
    }

    private GreenfootImage proximaCena(){
        GreenfootImage novaCena = filme();

        quadroAtual++;
        if(quadroAtual > QUANTIDADE_DE_QUADROS){
            quadroAtual = 1;
        }
        return novaCena;
    }

    /**
     *  Cria a nova cena do jogo de acordo com o quadro atualmente apresentado.
     */
    private GreenfootImage filme(){

        GreenfootImage novaCena  =  new GreenfootImage(LARGURA_CENARIO,ALTURA_CENARIO);
        int posicaoDoQuadro = 0;
        int quadro = quadroAtual;
        while(posicaoDoQuadro < LARGURA_CENARIO ){
            if(quadro > QUANTIDADE_DE_QUADROS){
                quadro = 1;
            }
            novaCena.drawImage(imagemDoNovoQuadro(quadro), posicaoDoQuadro, 0);
            quadro++;
            posicaoDoQuadro += TAMANHO_DO_QUADRO;

        }
        return novaCena;
    }

    /**
     * Apenas busca as novas filetas dentro do diretório de imagem
     */
    private GreenfootImage imagemDoNovoQuadro(int proximoQuadro){
        String nomeDoArquivo = NOME_ARQUIVO_IMAGEM + proximoQuadro + EXTENSAO_ARQUIVO_IMAGEM;
        GreenfootImage novoQuadro = new GreenfootImage(nomeDoArquivo );
        return novoQuadro;

    }

    
    
}
