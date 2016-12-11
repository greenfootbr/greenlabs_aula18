import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Classe para criar o personagem Gato.
 * 
 * @author (Jhonatan Morais - jhonatanvinicius@gmail.com | jhonatan@dfjug.org) 
 * @version (1.0)
 */
public class Gato extends Actor
{
    public int proximoPasso = 2;
    public static final int TAXA_DE_ATUALIZACAO = 6;
    public static final int ALTURA_MAXIMA_PULO = 15;

    public boolean estaEmTerraFirme = true;
    public boolean estaEmPulo = false;
    public int alturaAtualDoPulo = 0;


    /**
     * Act - do whatever the Gato wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        gerenciamentoDaCaminhada();
        gerenciamentoDoPulo();
        cataMoeda();

    }
    private void cataMoeda(){
    
        Actor obj = getOneIntersectingObject(Moedaria.class);
        if (obj != null){
            Greenfoot.playSound("coin1.wav");
            Mundo1 mundo = (Mundo1) getWorld();
            mundo.acrescentaPontos(100);
            mundo.removeObject(obj);
                    
        }
    
    
    }
    
    /**
     * Gerenciar todos as instruções de movimento durante a caminhada do personagem.
     */
    private void gerenciamentoDaCaminhada(){
        setImage(new GreenfootImage("personagens/gato/ze_"+proximoPasso+".png"));

        if( possoAtualizar()){
            proximoPasso++;
        }

        if(proximoPasso > 6){
            proximoPasso = 1;
        }

    }
    
    

    /**
     * Gerenciar todos as instruções da execução do pulo.
     */
    private void gerenciamentoDoPulo(){
        capturaInicioDoPulo();
        executaSubidaDoPulo();
        executaDescidaDoPulo();
        executaApiceDoPulo();
        executaPousoDoPulo();

    }
    /**
     * Verifica a entrada do comando de pulo vinda do teclado.
     */
    private void capturaInicioDoPulo(){
        if(Greenfoot.isKeyDown("space") && estaEmTerraFirme){
            estaEmTerraFirme = false;
            estaEmPulo = true;

        }
    }
    
    /**
     * Controla o momento da subida do pulo
     */
    private void executaSubidaDoPulo(){
        if( alturaAtualDoPulo < ALTURA_MAXIMA_PULO  && estaEmPulo ){
            alturaAtualDoPulo++;
            setLocation(getX(), getY() - Mundo1.FORCA_DE_GRAVIDADE * 2);

        }

    }

    /**
     * Controla o momento da inversão do sentido do pulo
     */
    private void executaApiceDoPulo(){
        if( alturaAtualDoPulo == ALTURA_MAXIMA_PULO   ){
           
            estaEmPulo = false;

        }
    }
    
    /**
     * Controla o momento da descida do pulo
     */
    private void executaDescidaDoPulo(){
        if( alturaAtualDoPulo > 0  && !estaEmPulo ){
            alturaAtualDoPulo--;

        }
    }
    /**
     * Controla o momento da pouso do pulo
     */
    private void executaPousoDoPulo(){
        if( alturaAtualDoPulo == 0  && !estaEmPulo ){
            estaEmTerraFirme = true;
        }

    }

    /**
     * Retorna o valor do limite inferior do personagem
     */
    public int alturaDosPes(){

        return  getY() + getImage().getHeight()/2;

    }

    /**
     * Retorna a altura atual do personagem dentro do jogo
     */
    public int alturaAtual(){ 

        return (alturaDosPes() - Mundo1.NIVEL_DO_SOLO) * -1;

    }  
    /**
     * Verifica se a imgem do ator ja pode ser atualizada
     */
    private boolean possoAtualizar(){
        Mundo1 mundo = (Mundo1) getWorld();
        return (mundo.cicloAtual() % TAXA_DE_ATUALIZACAO) == 0 ;
    }

}
