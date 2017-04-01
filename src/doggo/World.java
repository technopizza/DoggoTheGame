/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doggo;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;
import static doggo.Property.*;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

/**
 *
 * @author Jason
 */
public class World extends BasicGameState {

    private static AppGameContainer appGameContainer;
    private static Camera camera;

    private TiledMap tiledMap;
    public static Tile[][] tileArray;
    public static final float GRAVITY = 1f;

    private Player player;
    private LinkedList<Enemy> enemyLinkedList;
    
    private LinkedList<Projectile> projectileLinkedList;

    private Input input;
    private int id;

    int i = 1;
    
    public World(int id, String tiledMapPath) throws SlickException {
        //this.id = id;

    }

    @Override
    public int getID() {
        //return id;
        return 1;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {

        tiledMap = new TiledMap("res/box3.tmx");
        tileArray = new Tile[tiledMap.getWidth()][tiledMap.getHeight()];

        gameContainer.setTargetFrameRate(60);

        gameContainer.setShowFPS(false);

        camera = new Camera(gameContainer, tiledMap);

        tiledArrayInit();

        enemyInit();
        playerInit();
        
        Projectile tmp1 = new Projectile("Bork", "res/dog1.png");
        tmp1.init(player.getPositionX(), player.getPositionY(), Tile.TILE_SIZE / 2, Tile.TILE_SIZE / 2, 
                (player.getSpeedX() / Math.abs(player.getSpeedX())) * 6, Tile.TILE_SIZE * 3);
            
        projectileLinkedList = new LinkedList(new Node(tmp1));
        

    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {

//        g.setColor(Color.red);
//g.drawString("Hey" , 10,   20);
//        g.drawString("Score: " + duBois.getScore(), camera.cameraX + 20,
//                camera.cameraY + 20);
        camera.centerOn((int) player.getPositionX(), (int) player.getPositionY());
          //camera.centerOn( 700, ((int) Main.ySize / 2));
        

        camera.drawMap();
        camera.translateGraphics();

//      g.draw(player.hitBox);
//      
//      g.setColor(Color.blue);
        //g.draw(new Rectangle(0,0, player.positionX, player.positionY));
        //g.drawString("PosX: " + player.positionX + "   PosY: " + player.positionY, player.getPositionX() - 20,   player.getPositionY() - 20);
//        player.getCurrentSprite().drawCentered(player.getPositionX(), player.getPositionY());
//        g.drawString(player.getName(), player.getPositionX() - (Tile.TILE_SIZE / 3),   player.getPositionY() - (Tile.TILE_SIZE / 3));
        g.setColor(Color.red);
      
        while(projectileLinkedList.getCurrentNode() != null){
            Projectile tmp = ((Projectile) projectileLinkedList.getCurrentNode().getData());
            tmp.getCurrentSprite().drawCentered(tmp.getPositionX(), tmp.getPositionY());
            //g.drawString(tmp.getName(), tmp.getPositionX() - (tmp.getHitBoxWidth() / 2),   tmp.getPositionY() - ((tmp.getHitBoxHeight() / 2) - (Tile.TILE_SIZE / 4)));
                        //g.draw(tmp.getHitBox());

            projectileLinkedList.nextNode();
        }
        projectileLinkedList.reset();
        
        while(enemyLinkedList.getCurrentNode() != null){
            Enemy eTmp = ((Enemy) enemyLinkedList.getCurrentNode().getData());
            eTmp.getCurrentSprite().drawCentered(eTmp.getPositionX(), eTmp.getPositionY());
            g.drawString(eTmp.getName(), eTmp.getPositionX() - (eTmp.getHitBoxWidth() / 2),   eTmp.getPositionY() - ((eTmp.getHitBoxHeight() / 2) - (Tile.TILE_SIZE / 4)));
                        g.draw(eTmp.getHitBox());

            enemyLinkedList.nextNode();
        }
        enemyLinkedList.reset();
        g.setColor(Color.green);
        player.getCurrentSprite().drawCentered(player.getPositionX(), player.getPositionY());
        g.drawString(player.getName(), player.getPositionX() - (player.getHitBoxWidth() / 2), player.getPositionY() - ((player.getHitBoxHeight() / 2) + (Tile.TILE_SIZE / 4)));
        g.setColor(Color.blue);
        g.draw(player.hitBox);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {

        input = gc.getInput();

        
        
      while(projectileLinkedList.getCurrentNode() != null){
            Projectile tmp = ((Projectile) projectileLinkedList.getCurrentNode().getData());
            while(enemyLinkedList.getCurrentNode() != null){
                Enemy eTmp = ((Enemy) enemyLinkedList.getCurrentNode().getData());
                
                
                if(tmp.getHitBox().intersects(eTmp.getHitBox())){
                    projectileLinkedList.removeNodeWithData(tmp);
                   // System.out.println(i); i++;
                    //System.out.println(eTmp);
                    enemyLinkedList.removeNodeWithData(eTmp);
                    enemyLinkedList.removeNodeWithData(eTmp);
                    eTmp.getDieSound().play();
                }
                enemyLinkedList.nextNode();
            }enemyLinkedList.reset();  
            
            
            tmp.update();
             if(tmp.getDistanceTravelled() >= tmp.getDistanceLife()){
                projectileLinkedList.removeNodeWithData(tmp);
            }
            
            if(tmp.getSpeedX()<0){
                
                
                
                if (tmp.getAnimateCounter() < 0) {
                tmp.setAnimateCounter(-tmp.getAnimateCounter());
                tmp.setCurrentSprite((Image) tmp.getSpriteAnimations().get("LEFT").nextNode().getData());

            } else if (tmp.getAnimateCounter() < tmp.getAnimateInterval()) {
                tmp.setAnimateCounter(tmp.getAnimateCounter() + 1);
            } else {
                tmp.setCurrentSprite((Image) tmp.getSpriteAnimations().get("LEFT").nextNode().getData());
                tmp.setAnimateCounter(0);
            }
                //tmp.setCurrentSprite((Image) tmp.getSpriteAnimations().get("LEFT").nextNode().getData());
                
            }
            else if (tmp.getSpeedX() > 0){
                
                
                
                
                if (tmp.getAnimateCounter() > 0) {
               tmp.setAnimateCounter(-tmp.getAnimateCounter());
                tmp.setCurrentSprite((Image) tmp.getSpriteAnimations().get("RIGHT").nextNode().getData());
            } else if (tmp.getAnimateCounter() > -tmp.getAnimateInterval()) {
                tmp.setAnimateCounter(tmp.getAnimateCounter() - 1);
            } else {
               tmp.setCurrentSprite((Image) tmp.getSpriteAnimations().get("RIGHT").nextNode().getData());
                tmp.setAnimateCounter(0);
            }
               // tmp.setCurrentSprite((Image) tmp.getSpriteAnimations().get("RIGHT").nextNode().getData());
               
            }
            projectileLinkedList.nextNode();
        }
        projectileLinkedList.reset();  
        
        
        
      while(enemyLinkedList.getCurrentNode() != null){
            Enemy eTmp = ((Enemy) enemyLinkedList.getCurrentNode().getData());
            eTmp.update();
            
            if( eTmp.getHitBox().intersects(player.getHitBox())){
                sbg.enterState(3, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
                
          
      }
            
            if(eTmp.getSpeedX()<0){
                
                                
                if (eTmp.getAnimateCounter() < 0) {
                eTmp.setAnimateCounter(-eTmp.getAnimateCounter());
                eTmp.setCurrentSprite((Image) eTmp.getSpriteAnimations().get("LEFT").nextNode().getData());

            } else if (eTmp.getAnimateCounter() < eTmp.getAnimateInterval()) {
                eTmp.setAnimateCounter(eTmp.getAnimateCounter() + 1);
            } else {
                eTmp.setCurrentSprite((Image) eTmp.getSpriteAnimations().get("LEFT").nextNode().getData());
                eTmp.setAnimateCounter(0);
            }
                //tmp.setCurrentSprite((Image) tmp.getSpriteAnimations().get("LEFT").nextNode().getData());
                
            }
            else if (eTmp.getSpeedX() > 0){
                
//                if(eTmp.getDelayCount() > 0){
//                eTmp.setCurrentSprite((Image) eTmp.getSpriteAnimations().get("IDLELEFT").nextNode().getData());
//            }else{
//                
                
                if (eTmp.getAnimateCounter() > 0) {
               eTmp.setAnimateCounter(-eTmp.getAnimateCounter());
                eTmp.setCurrentSprite((Image) eTmp.getSpriteAnimations().get("RIGHT").nextNode().getData());
            } else if (eTmp.getAnimateCounter() > -eTmp.getAnimateInterval()) {
                eTmp.setAnimateCounter(eTmp.getAnimateCounter() - 1);
            } else {
               eTmp.setCurrentSprite((Image) eTmp.getSpriteAnimations().get("RIGHT").nextNode().getData());
                eTmp.setAnimateCounter(0);
            }
               // tmp.setCurrentSprite((Image) tmp.getSpriteAnimations().get("RIGHT").nextNode().getData());
           // }
            }
            enemyLinkedList.nextNode();
        }
        enemyLinkedList.reset();
        
        if (input.isKeyPressed(Input.KEY_SPACE)) {
            int tmpInt = 0;
            if(player.getDirection().equalsIgnoreCase("RIGHT")) tmpInt = 1;
            else if (player.getDirection().equalsIgnoreCase("LEFT")) tmpInt = -1;
            Projectile tmp = new Projectile("Bork", "res/dog1.png");
        tmp.init(player.getPositionX(), player.getPositionY(), Tile.TILE_SIZE / 2, Tile.TILE_SIZE / 2, 
                (6 * tmpInt), Tile.TILE_SIZE * 3);
        
        projectileLinkedList.addNodeBack(new Node(tmp));
        player.getAttackSound().play();
            
            
        }
        
        if (input.isKeyDown(Input.KEY_UP) && player.getSpeedY() <= 0 && player.isBlockedDown()) {
            player.setSpeedY(-player.getJumpSpeed());
        }
        
       if((World.tileArray[((int)(player.getPositionX() / Tile.TILE_SIZE))][((int)(player.getPositionY() / Tile.TILE_SIZE))].getProperty() == LADDER)){
           sbg.enterState(2, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
       }
                    
            
        
        if (input.isKeyDown(Input.KEY_LEFT)) {
//            if(player.getSpeedX() > 0){
//                player.setSpeedX(0);
//            }
            player.setSpeedX(-player.getWalkSpeed());
            player.setDirection("LEFT");
            if (player.getAnimateCounter() < 0) {
                player.setAnimateCounter(-player.getAnimateCounter());
                player.setCurrentSprite((Image) player.getSpriteAnimations().get("LEFT").nextNode().getData());

            } else if (player.getAnimateCounter() < player.getAnimateInterval()) {
                player.setAnimateCounter(player.getAnimateCounter() + 1);
            } else {
                player.setCurrentSprite((Image) player.getSpriteAnimations().get("LEFT").nextNode().getData());
                player.setAnimateCounter(0);
            }
        } else if (input.isKeyDown(Input.KEY_RIGHT)) {
//            if(player.getSpeedX() < 0){
//                player.setSpeedX(0);
//            }
            player.setDirection("RIGHT");
            player.setSpeedX(player.getWalkSpeed());
            if (player.getAnimateCounter() > 0) {
               player.setAnimateCounter(-player.getAnimateCounter());
                player.setCurrentSprite((Image) player.getSpriteAnimations().get("RIGHT").nextNode().getData());
            } else if (player.getAnimateCounter() > -player.getAnimateInterval()) {
                player.setAnimateCounter(player.getAnimateCounter() - 1);
            } else {
                player.setCurrentSprite((Image) player.getSpriteAnimations().get("RIGHT").nextNode().getData());
                player.setAnimateCounter(0);
            }

        }
        
        
        
        
        player.update();

        
    }

    public void playerInit() throws SlickException {
        player = new Player("Doggo", "res/corgi.png");
        int startX = 0, startY = 0;
        for (int i = 0; i < tileArray.length; i++) {
            for (int j = 0; j < tileArray[i].length; j++) {
                if (tileArray[i][j].getProperty() == PLAYERSPAWN) {
                    startX = ((i * Tile.TILE_SIZE) + (Tile.TILE_SIZE / 2));
                    startY = ((j * Tile.TILE_SIZE) + (Tile.TILE_SIZE / 2));
                }

            }
        }
        player.init(startX, startY, Tile.TILE_SIZE, Tile.TILE_SIZE, 10, 4);
    }
    public void enemyInit() throws SlickException{
        Enemy tmp;
        for (int i = 0; i < tileArray.length; i++) {
            for (int j = 0; j < tileArray[i].length; j++) {
                if(tileArray[i][j].getProperty() == ENEMYSPAWN){
                    tmp = new Enemy("froggo", "res/frog1.png");
                    tmp.init((Tile.TILE_SIZE * i), (Tile.TILE_SIZE * j), Tile.TILE_SIZE, Tile.TILE_SIZE, 2, 10);
                    tmp.setSpeedX(2f);
                    if(enemyLinkedList == null){
                        
                        enemyLinkedList = new LinkedList(new Node(tmp));
                        
                    }
                    else{
                        enemyLinkedList.addNodeFront(new Node(tmp));
                    }
                } 
                
            }   
        }
        
//        while(enemyLinkedList.getCurrentNode() != null){
//            tmp = ((Enemy) enemyLinkedList.getCurrentNode().getData());
//            tmp.initAnimations();
//            enemyLinkedList.nextNode();
//        }
//        enemyLinkedList.reset();
    }

    public void tiledArrayInit() {
        //NONE, SOLID, PLAYERSPAWN, ENEMYSPAWN, LADDER;

//        for (int i = 0; i < tileArray.length; i++) {
//            for (int j = 0; j < tileArray[i].length; j++) {
//                tileArray[i][j] = new Tile();
//            }
//        }
        for (int i = 0; i < tileArray.length; i++) {
            for (int j = 0; j < tileArray[i].length; j++) {
//                if(tiledMap.getTileProperty(tiledMap.getTileId(i , j, 1), "none", "false").equalsIgnoreCase("true")){
//                    tileArray[i][j] = new Tile(NONE);
//                } 
                if (tiledMap.getTileProperty(tiledMap.getTileId(i, j, 1), "solid", "false").equalsIgnoreCase("true")) {
                    tileArray[i][j] = new Tile(SOLID);
                } else if (tiledMap.getTileProperty(tiledMap.getTileId(i, j, 1), "playerspawn", "false").equalsIgnoreCase("true")) {
                    tileArray[i][j] = new Tile(PLAYERSPAWN);
                } else if (tiledMap.getTileProperty(tiledMap.getTileId(i, j, 1), "enemyspawn", "false").equalsIgnoreCase("true")) {
                    tileArray[i][j] = new Tile(ENEMYSPAWN);
                } else if (tiledMap.getTileProperty(tiledMap.getTileId(i, j, 1), "ladder", "false").equalsIgnoreCase("true")) {
                    tileArray[i][j] = new Tile(LADDER);
                } else {
                    tileArray[i][j] = new Tile(NONE);
                }
            }
        }
    }
}
