/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doggo;
import org.newdawn.slick.*;

import org.newdawn.slick.state.*;



public class Main extends StateBasedGame{

   

   public static final String gamename = "Doggo: Heckin Good Boy";

   public static final int play = 0;

   public static final int xSize = 704;

   public static final int ySize = 640;

   

   public Main(String gamename) throws SlickException{

      super(gamename);

     this.addState(new Start(xSize, ySize));
//
    this.addState(new World(1, "res/box3.tmx"));
//      
      this.addState(new Win(xSize, ySize));
//      
      this.addState(new Lose(xSize, ySize));

   }

   

   @Override
   public void initStatesList(GameContainer gc) throws SlickException{

      this.getState(play).init(gc, this);

      this.enterState(play);

   }

   

   public static void main(String[] args) {

      AppGameContainer appgc;

      try{

         appgc = new AppGameContainer(new Main(gamename));

         appgc.setDisplayMode(xSize, ySize, false);
         
         int maxFPS = 60;
        
         appgc.setTargetFrameRate(maxFPS);

         appgc.setTargetFrameRate(60);

         appgc.start();

      }catch(SlickException e){

         e.printStackTrace();

      }

   }

}