/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doggo;

import java.util.HashMap;
import java.util.Map;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Shape;
import static doggo.Property.*;
import org.newdawn.slick.Sound;
import org.newdawn.slick.geom.Rectangle;

/**
 *
 * @author jconklin2391
 */
public class Player extends Entity {

    private String direction;
    private Sound attackSound;
    
    public Player(String name, String spriteSheetPath) throws SlickException{
        super(name, spriteSheetPath);
                
    }
    
    public void init(float posX, float posY, float hBxW, float hBxH, float jumpSpeed, float walkSpeed) throws SlickException{
        positionX = posX;
        positionY = posY;
        hitBoxWidth = hBxW;
        hitBoxHeight = hBxH;
        this.jumpSpeed = jumpSpeed;
        this.walkSpeed = walkSpeed;
        hitBox = new Rectangle(positionX, positionY, hitBoxWidth, hitBoxHeight);
        currentSprite = new Image((int) hitBoxWidth, (int) hitBoxHeight);
        spriteAnimations = new HashMap();
        direction = "RIGHT";
        animateInterval = 10;
        animateCounter = 0;
        attackSound = new Sound("res/bork.wav");
        
        initAnimations();
    }
    
    public void initAnimations(){
        
         for (int i = 0; i < 3; i++) {
             if(i==0){
            spriteAnimations.put("LEFT", new LinkedList(new Node(spriteSheet.getSprite(i, 0))));}
             else{
                 spriteAnimations.get("LEFT").addNodeFront(new Node(spriteSheet.getSprite(i, 0)));
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
        gravity();
        updatePos();
        speedX=0;
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

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public Sound getAttackSound() {
        return attackSound;
    }

    public void setAttackSound(Sound attackSound) {
        this.attackSound = attackSound;
    }
    
    
    
    
}
