/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doggo;

/**
 *
 * @author Jason
 */
public interface Dynamic {
    
    public static final float GRAVITY = 1f;
    
    void update(Tile[][] tileArray);
    void applyGravity(float gravity, Tile[][] tileArr);
    void updatePositionX(float val);
    void updatePositionY(float val, Tile[][] tileArr);
    void updatePosition(Tile[][] tileArray);
    
    
    //void draw();
    
}
