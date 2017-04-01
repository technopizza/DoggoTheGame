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
import org.newdawn.slick.Sound;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

/**
 *
 * @author jconklin2391
 */
public class Enemy extends Entity {

    private Sound dieSound;
    
//    int counter;
//    
//    public Enemy(String name, float stepX, float stepY, float hitBoxWidth, float hitBoxHeight, String spriteSheetPath) throws SlickException {
//        super(name, stepX, stepY, hitBoxWidth, hitBoxHeight, spriteSheetPath);
//    }
//    public Enemy(float posX, float posY, String spriteSheetPath) throws SlickException{
//        super(posX, posY, spriteSheetPath);
//        name = "Enemy";
//        stepX = 0;
//        stepY = 0;
//        hitBoxWidth = Tile.TILE_SIZE;
//        hitBoxHeight = Tile.TILE_SIZE;
//        counter = 0;
//        animateInterval = 10;
//        
//        
//    }
//    public void initAnimations(){
//        
//        for (int i = 0; i < 3; i++) {
//             if(i==0){
//            directionalMovementSprites.put("LEFT", new LinkedList(new Node(spriteSheet.getSprite(i, 1))));}
//             else{
//                 directionalMovementSprites.get("LEFT").addNodeFront(new Node(spriteSheet.getSprite(i, 1)));
//             }
//             
//        }
//         directionalMovementSprites.get("LEFT").loopLinkedList();
//         
//         
//         for (int i = 0; i < 3; i++) {
//             if(i==0){
//            directionalMovementSprites.put("RIGHT", new LinkedList(new Node(spriteSheet.getSprite(i, 2))));}
//             else{
//                 directionalMovementSprites.get("RIGHT").addNodeFront(new Node(spriteSheet.getSprite(i, 2)));
//             }
//            
//        }
//         directionalMovementSprites.get("RIGHT").loopLinkedList();
//    }
//    
//    @Override
//    public void updatePosition(Tile[][] tileArray) {
//        super.updatePosition(tileArray);
//        if(counter < 0){
//                counter = -counter;
//                currentSprite = (Image) directionalMovementSprites.get("LEFT").nextNode().getData();
//            }
//        else if(counter < animateInterval){
//                counter++;
//            }else{
//            currentSprite = (Image) directionalMovementSprites.get("LEFT").nextNode().getData();
//            counter = 0;
//            }
////        if(counter > 0){
////                counter = -counter;
////                currentSprite = (Image) directionalMovementSprites.get("RIGHT").nextNode().getData();
////            }
////            else if(counter > -animateInterval){
////                counter--;
////            }else{
////            currentSprite = (Image) directionalMovementSprites.get("RIGHT").nextNode().getData();
////            counter = 0;
////            }
//    }
//    
//public float getPositionX() {
//        return positionX;
//    }
//
//    public void setPositionX(float positionX) {
//        this.positionX = positionX;
//    }
//
//    public float getPositionY() {
//        return positionY;
//    }
//
//    public void setPositionY(float positionY) {
//        this.positionY = positionY;
//    }
//
//    public Image getCurrentSprite() {
//        return currentSprite;
//    }
//
//    public String getName() {
//        return name;
//    }
//    public float getHitBoxWidth() {
//        return hitBoxWidth;
//    }
//
//    public float getHitBoxHeight() {
//        return hitBoxHeight;
//    }
//    public Shape getHitBox() {
//        return hitBox;
//    }
    private int delayTime;
    private int delayCount;

    public Enemy(String name, String spriteSheetPath) throws SlickException {
        super(name, spriteSheetPath);

    }

    public void init(float posX, float posY, float hBxW, float hBxH, float jumpSpeed, float walkSpeed) throws SlickException {
        positionX = posX;
        positionY = posY;
        hitBoxWidth = hBxW;
        hitBoxHeight = hBxH;
        this.jumpSpeed = jumpSpeed;
        this.walkSpeed = walkSpeed;
        hitBox = new Rectangle(positionX, positionY, hitBoxWidth, hitBoxHeight);
        currentSprite = new Image((int) hitBoxWidth, (int) hitBoxHeight);
        spriteAnimations = new HashMap();
        animateInterval = 10;
        animateCounter = 0;
        delayTime = 100;
        dieSound = new Sound("res/ribbit.wav");
        delayCount = 0;

        initAnimations();
    }

    public void initAnimations() {

//        for (int i = 0; i < 3; i++) {
//            if (i == 0) {
//                spriteAnimations.put("IDLELEFT", new LinkedList(new Node(spriteSheet.getSprite(i, 0))));
//            } else {
//                spriteAnimations.get("IDLELEFT").addNodeFront(new Node(spriteSheet.getSprite(i, 0)));
//            }
//
//        }
//        spriteAnimations.get("IDLELEFT").loopLinkedList();
//
//        for (int i = 0; i < 3; i++) {
//            if (i == 0) {
//                spriteAnimations.put("IDLERIGHT", new LinkedList(new Node(spriteSheet.getSprite(i, 3))));
//            } else {
//                spriteAnimations.get("IDLERIGHT").addNodeFront(new Node(spriteSheet.getSprite(i, 3)));
//            }
//
//        }
//        spriteAnimations.get("IDLERIGHT").loopLinkedList();

        for (int i = 0; i < 3; i++) {
            if (i == 0) {
                spriteAnimations.put("LEFT", new LinkedList(new Node(spriteSheet.getSprite(i, 0))));
            } else {
                spriteAnimations.get("LEFT").addNodeFront(new Node(spriteSheet.getSprite(i, 0)));
            }

        }
        spriteAnimations.get("LEFT").loopLinkedList();
        for (int i = 0; i < 3; i++) {
            if (i == 0) {
                spriteAnimations.put("RIGHT", new LinkedList(new Node(spriteSheet.getSprite(i, 1))));
            } else {
                spriteAnimations.get("RIGHT").addNodeFront(new Node(spriteSheet.getSprite(i, 1)));
            }

        }
        spriteAnimations.get("RIGHT").loopLinkedList();
    }

    @Override
    public void update() {
        
        gravity();
        if (speedX > 0 && isBlockedRight()) {
            speedX = -1 * Math.abs(speedX);
        } else if (speedX < 0 && isBlockedLeft()) {
            speedX = Math.abs(speedX);
        }

        updatePos();

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

    public int getAnimateInterval() {
        return animateInterval;
    }

    public int getAnimateCounter() {
        return animateCounter;
    }

    public void setAnimateCounter(int val) {
        animateCounter = val;
    }

    public String getName() {
        return name;
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

    public int getDelayTime() {
        return delayTime;
    }

    public void setDelayTime(int delayTime) {
        this.delayTime = delayTime;
    }

    public int getDelayCount() {
        return delayCount;
    }

    public void setDelayCount(int delayCount) {
        this.delayCount = delayCount;
    }

    public Sound getDieSound() {
        return dieSound;
    }

}
