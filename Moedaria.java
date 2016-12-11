import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Classe para gerenciar as moedas do jogo
 * 
 * @author (Jhonatan Morais - jhonatanvinicius@gmail.com | jhonatan@dfjug.org) 
 * @version (1.0)
 */
public class Moedaria extends Actor
{
    public int proximoPasso = 2;
    public static final int TAXA_DE_ATUALIZACAO = 6;

    public void act() 
    {
        rodaMoeda();
        movimentaMoeda();
        sairDeCena();
        

    } 
    public void sairDeCena(){
        
        if(getX() == 0){
            
            getWorld().removeObject(this);
        
        }
    
    
    }
    
    

    private void movimentaMoeda(){

        move(Mundo1.TAMANHO_DO_QUADRO * -1);

    }
    
    /**
     * Gerenciar todos as instruções de movimento durante o giro da moeda.
     */
    private void rodaMoeda(){
        setImage(new GreenfootImage("objetos/coin/coin_"+proximoPasso+".png"));

        if( possoAtualizar()){
            proximoPasso++;
        }

        if(proximoPasso > 6){
            proximoPasso = 1;
        }

    }

    /**
     * Verifica se a imgem do ator ja pode ser atualizada
     */
    private boolean possoAtualizar(){
        Mundo1 mundo = (Mundo1) getWorld();
        return (mundo.cicloAtual() % TAXA_DE_ATUALIZACAO) == 0 ;
    }

}
