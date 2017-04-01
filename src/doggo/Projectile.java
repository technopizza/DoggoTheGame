/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doggo;

import java.util.HashMap;
import java.util.Map;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

/**
 *
 * @author Jason
 */
public class Projectile extends Entity{
    
    private float distanceTravelled;
    private float distanceLife;
    
    public Projectile(String name, String spriteSheetPath) throws SlickException{
        super(name, spriteSheetPath);
                
    }
    
    public void init(float posX, float posY, float hBxW, float hBxH, float walkSpeed, float life) throws SlickException{
        positionX = posX;
        positionY = posY;
        hitBoxWidth = hBxW;
        hitBoxHeight = hBxH;
        this.walkSpeed = Math.abs(walkSpeed);
        this.speedX = walkSpeed;
        distanceLife = life;
        distanceTravelled = 0;
        hitBox = new Rectangle(positionX, positionY, hitBoxWidth, hitBoxHeight);
        currentSprite = new Image((int) hitBoxWidth, (int) hitBoxHeight);
        spriteAnimations = new HashMap();
        animateInterval = 10;
        animateCounter = 0;
        initAnimations();
    }
    
    public void initAnimations(){
        
         for (int i = 0; i < 3; i++) {
             if(i==0){
            spriteAnimations.put("LEFT", new LinkedList(new Node(spriteSheet.getSprite(i, 2))));}
             else{
                 spriteAnimations.get("LEFT").addNodeFront(new Node(spriteSheet.getSprite(i, 2)));
             }
             
            
        }
         spriteAnimations.get("LEFT").loopLinkedList();
         for (int i = 0; i < 3; i++) {
             if(i==0){
            spriteAnimations.put("RIGHT", new LinkedList(new Node(spriteSheet.getSprite(i, 1))));}
             else{
                 spriteAnimations.get("RIGHT").addNodeFront(new Node(spriteSheet.getSprite(i, 1)));
             }
            
        }
         spriteAnimations.get("RIGHT").loopLinkedList();
    }
    
    @Override
    public void update(){
        //gravity();
        updatePos();
        distanceTravelled += Math.abs(speedX);
        //speedX=0;
    }
    


    public float getPositionX() {
        return positionX;
    }

    public void setPositionX(float positionX) {
        this.positionX = positionX;
    }

    public float getPositionY() {
        return positionY;
    }

    public void setPositionY(float positionY) {
        this.positionY = positionY;
    }

    public float getSpeedX() {
        return speedX;
    }

    public void setSpeedX(float speedX) {
        this.speedX = speedX;
    }

    public float getSpeedY() {
        return speedY;
    }

    public void setSpeedY(float speedY) {
        this.speedY = speedY;
    }

    public float getJumpSpeed() {
        return jumpSpeed;
    }

    public float getWalkSpeed() {
        return walkSpeed;
    }

    public String getName() {
        return name;
    }

    public int getAnimateInterval() {
        return animateInterval;
    }

    public int getAnimateCounter() {
        return animateCounter;
    }
    public void setAnimateCounter(int val) {
        animateCounter = val;
    }
    public Shape getHitBox() {
        return hitBox;
    }

    public float getHitBoxWidth() {
        return hitBoxWidth;
    }

    public float getHitBoxHeight() {
        return hitBoxHeight;
    }

    public Image getCurrentSprite() {
        return currentSprite;
    }

    public void setCurrentSprite(Image currentSprite) {
        this.currentSprite = currentSprite;
    }
    
    public Map<String, LinkedList<Image>> getSpriteAnimations() {
        return spriteAnimations;
    }

    public float getDistanceTravelled() {
        return distanceTravelled;
    }

    public float getDistanceLife() {
        return distanceLife;
    }
    
    
    
    
}
