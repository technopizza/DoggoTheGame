/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doggo;

import java.util.HashMap;
import java.util.Map;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import static doggo.Property.SOLID;

/**
 *
 * @author jconklin2391
 */
public abstract class Entity {

    String name;

    float positionX;
    float positionY;
    float speedX;
    float speedY;
    float accelerationX;
    float accelerationY;
    
    float jumpSpeed;
    float walkSpeed;

    Shape hitBox;
    float hitBoxWidth;
    float hitBoxHeight;
    
//    Image[] motionRight;
//    Image[] motionLeft;
    
    Image currentSprite;
    SpriteSheet spriteSheet;

    int animateInterval;
    int animateCounter;
    Map<String, LinkedList<Image>> spriteAnimations;

    

//    @Override
//    public void update(Tile[][] tileArray) {
//        updatePosition(tileArray);
//    }
//
//    public Entity(float positionX, float positionY, String spriteSheetPath) throws SlickException {
//        this.positionX = positionX;
//        this.positionY = positionY;
//        this.hitBoxWidth = Tile.TILE_SIZE;
//        this.hitBoxHeight = Tile.TILE_SIZE;
//        this.spriteSheet = new SpriteSheet(spriteSheetPath, Tile.TILE_SIZE, Tile.TILE_SIZE);
//         this.hitBox = new Rectangle(0, 0, this.hitBoxWidth, this.hitBoxHeight);
//        directionalMovementSprites = new HashMap();
//        currentSprite = new Image(Tile.TILE_SIZE, Tile.TILE_SIZE);
//    }
//
//    public Entity(String name, float stepX, float stepY, float hitBoxWidth, float hitBoxHeight, String spriteSheetPath) throws SlickException {
//        this.name = name;
//        this.stepX = stepX;
//        this.stepY = stepY;
//        this.hitBoxWidth = hitBoxWidth;
//        this.hitBoxHeight = hitBoxHeight;
//        this.spriteSheet = new SpriteSheet(spriteSheetPath, Tile.TILE_SIZE, Tile.TILE_SIZE);
//        currentSprite = new Image(Tile.TILE_SIZE, Tile.TILE_SIZE);
//        this.hitBox = new Rectangle(0, 0, this.hitBoxWidth, this.hitBoxHeight);
//        directionalMovementSprites = new HashMap();
//
////        motionRight = new Image[9];
////        for (int i = 0; i < motionRight.length; i++) {
////               motionRight[i] = this.spriteSheet.getSprite(i, 11);        
////        }
////        motionLeft = new Image[9];
////        for (int i = 0; i < motionLeft.length; i++) {
////               motionLeft[i] = this.spriteSheet.getSprite(i, 9);        
////        }
//    }
//
//    @Override
//    public void updatePosition(Tile[][] tileArray) {
//        updatePositionY(stepY, tileArray);
//        updatePositionX(stepX);
//        
//        applyGravity(Dynamic.GRAVITY, tileArray);
//        hitBox.setCenterX(positionX);
//        hitBox.setCenterY(positionY);
//    }
//
//    @Override
//    public void applyGravity(float gravity, Tile[][] tileArray) {
//        if(jump == 0){
//            boolean tmp = false;
//            for (float f = positionX - (hitBoxWidth / 2) + 1; f <= positionY + (hitBoxWidth / 2); f ++) {
//
//                if ((((positionY + (hitBoxHeight / 2)) > (((((int) (positionY / Tile.TILE_SIZE)) + 1 ) * Tile.TILE_SIZE) - 1)) && (tileArray[((int) (f / Tile.TILE_SIZE))][((int) positionY / (Tile.TILE_SIZE)) + 1].getProperty() == SOLID))) {
//                    
//                    
//                    tmp = true;
//                }
//            }
//            if (!tmp) {
//                positionY += gravity;
//            }
//    }
//
//    }
//
//    @Override
//    public void updatePositionX(float increment) {
//        positionX += increment;
//    }
//
//    @Override
//    public void updatePositionY(float increment, Tile[][] tileArray) {
//        
//        if(jump > 0){
//       boolean tmp = false;
//            for (float f = positionX - (hitBoxWidth / 2) + 1; f <= positionX + (hitBoxWidth / 2); f += (hitBoxWidth / 2) - 1) {
//
//                if ((((positionY - stepY - (hitBoxHeight / 2)) < (((int) (positionY / Tile.TILE_SIZE)) * Tile.TILE_SIZE)) && (tileArray[((int) (f / Tile.TILE_SIZE))][((int) (positionY / Tile.TILE_SIZE)) - 1].getProperty() == SOLID))) {
//                    tmp = true;
//                }
//            }
//            if (!tmp) {
//                positionY -= increment;
//                
//            }
//            jump--;
//        } 
//    }

    public Entity(String name, String spriteSheetPath) throws SlickException {
        this.name = name;
        this.spriteSheet = new SpriteSheet(spriteSheetPath, Tile.TILE_SIZE, Tile.TILE_SIZE);
        
    }
    
    
    
    public boolean isBlockedRight(){
        boolean blocked = false;
        
        float rightEdge = (positionX + (hitBoxWidth / 2));
        float rightBound = (((((int) (positionX / Tile.TILE_SIZE)) + 1) * Tile.TILE_SIZE) - 1);
        
        for (float i = (positionY - (hitBoxHeight / 2)); i < (positionY + (hitBoxHeight / 2)); i++) {
            
            if((World.tileArray[((int)(positionX / Tile.TILE_SIZE)) + 1][(int)(i / Tile.TILE_SIZE)].getProperty() == SOLID)
                    && ((rightBound - rightEdge) + 1 < walkSpeed)){
                blocked = true;
            }
                    
            
        }
        return blocked;
    }
    public boolean isBlockedLeft(){
        boolean blocked = false;
        
        float leftEdge = (positionX - (hitBoxWidth / 2));
        float leftBound = (((int) (positionX / Tile.TILE_SIZE)) * Tile.TILE_SIZE);
        for (float i = (positionY - (hitBoxHeight / 2)); i < (positionY + (hitBoxHeight / 2)); i++) {
            
            if((World.tileArray[((int)(positionX / Tile.TILE_SIZE)) - 1][(int)(i / Tile.TILE_SIZE)].getProperty() == SOLID)
                    && (Math.abs(leftBound - leftEdge) < walkSpeed)){
                blocked = true;
            }
                    
            
        }
        return blocked;
    }
    public boolean isBlockedUp(){
        boolean blocked = false;
        
        float upEdge = (positionY - (hitBoxWidth / 2));
        float upBound = (((int) (positionY / Tile.TILE_SIZE)) * Tile.TILE_SIZE);
        
        for (float i = (positionX - (hitBoxWidth / 2)); i < (positionX + (hitBoxWidth / 2)); i++) {
            
            if((World.tileArray[((int)(i / Tile.TILE_SIZE))][((int)(positionY / Tile.TILE_SIZE)) - 1].getProperty() == SOLID)
                    && (Math.abs(upBound - upEdge) < jumpSpeed)){
                blocked = true;
            }
                    
            
        }
        return blocked;
    }
    public boolean isBlockedDown(){
        boolean blocked = false;
        
        float downEdge = (positionY + (hitBoxWidth / 2));
        float downBound = (((((int) (positionY / Tile.TILE_SIZE)) + 1) * Tile.TILE_SIZE) - 1);
        
        for (float i = (positionX - (hitBoxWidth / 2)); i < (positionX + (hitBoxWidth / 2)); i++) {
            
            if((World.tileArray[((int)(i / Tile.TILE_SIZE))][((int)(positionY / Tile.TILE_SIZE)) + 1].getProperty() == SOLID)
                    && ((downBound - downEdge)  < Math.abs(speedY - World.GRAVITY))){
                blocked = true;
            }
                    
            
        }
        return blocked;
    }
    
    public void gravity(){
        speedY += World.GRAVITY;
        if(isBlockedDown() && speedY > 0){
            speedY = 0;
        }
        if(isBlockedUp() && speedY < 0){
            speedY = 0;
        }
    }
            
    public void update(){
        gravity();
        updatePos();
    }
    
    public void updatePos(){
        
        if(!(isBlockedUp()) && (speedY < 0)){
            positionY += speedY;
        }
        
        if(!(isBlockedRight()) && (speedX > 0)){
            positionX += speedX;
        }
        else if (!(isBlockedLeft()) && (speedX < 0)){
            positionX += speedX;
        }
        
        
//        else if(isBlockedLeft() && speedX < 0){
//            speedX = -speedX;
//        }
                
//        if(!isBlockedRight() && speedX > 0){
//            speedX = -speedX;
//        }
//        
//        else if(!isBlockedLeft() && speedX < 0){
//            speedX = -speedX;
//        }

        
        positionY += speedY;
        
                
        hitBox.setCenterX(positionX);
        hitBox.setCenterY(positionY);
        
        

    }
//    public void updateSpeed(){
//        speedX += accelerationX;
//        speedY -= accelerationY;
//    }

}
