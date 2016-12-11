import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Classe para criar o personagem Bicho.
 * 
 * @author (Jhonatan Morais - jhonatanvinicius@gmail.com | jhonatan@dfjug.org) 
 * @version (1.0)
 */
public class Bicho extends Actor
{
    public int proximoPasso = 2;
    public static final int TAXA_DE_ATUALIZACAO = 6;


    /**
     * Act - do whatever the Bicho wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        gerenciamentoDaCaminhada();
        movimentaBicho();
        sairDeCena();

    }
    
    public void sairDeCena(){
        
        if(getX() == 0){
            
            getWorld().removeObject(this);
        
        }
    
    
    }
    private void movimentaBicho(){

        move((Mundo1.TAMANHO_DO_QUADRO + 2)  * -1);

    }
    
    /**
     * Gerenciar todos as instruções de movimento durante a caminhada do personagem.
     */
    private void gerenciamentoDaCaminhada(){
        setImage(new GreenfootImage("personagens/inimigos/bicho_"+proximoPasso+".png"));

        if( possoAtualizar()){
            proximoPasso++;
        }

        if(proximoPasso > 2){
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
