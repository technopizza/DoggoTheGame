/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doggo;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

/**
 *
 * @author Jason
 */
public class Lose extends BasicGameState {


    


    private StateBasedGame game;

    public Image startimage;
    
public Music backgroundMusic;

     public Lose(int xSize, int ySize) {



    }



    


    public void init(GameContainer container, StateBasedGame game)


            throws SlickException {
    	
    	startimage = new Image("res/lose.jpg");
        backgroundMusic = new Music("res/music4.ogg");
        
backgroundMusic.play();
        this.game = game;
      

// TODO AutoÃ¢â‚¬Âgenerated method stub


    }



   


    public void render(GameContainer container, StateBasedGame game, Graphics g)


            throws SlickException {


// TODO AutoÃ¢â‚¬Âgenerated method stub
    	
    	


        g.setColor(Color.white);
        
        startimage.draw();
   
        //g.drawString("stuff", 300, 200);
        
        //g.drawString("collect the antidote before time runs out! red potions are health and yellow potions make you run faster", 50, 300);


        //g.drawString("1. Play Game", 50, 100);

        //g.drawString("2. High Scores(", 50, 120);


        //g.drawString("3. Quit", 50, 140);


    }



    


    public void update(GameContainer container, StateBasedGame game, int delta)


            throws SlickException {


// TODO AutoÃ¢â‚¬Âgenerated method stub


    }



  


    public int getID() {


// TODO AutoÃ¢â‚¬Âgenerated method stub


        return 3;


    }



    @Override


    public void keyReleased(int key, char c) {


        switch (key) {


            case Input.KEY_1:


                //game.enterState(1, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));


                break;


            case Input.KEY_2:


// TODO: Implement later


                break;


            case Input.KEY_3:


// TODO: Implement later


                break;


            default:


                break;


        }


    }


}
