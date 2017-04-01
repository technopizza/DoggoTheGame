/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doggo;

import static doggo.Property.NONE;

/**
 *
 * @author Jason
 */
public class Tile {
    public static final int TILE_SIZE = 64;
    
    
    private Property property;
    private float speedMultiplier;
    private int tileID;

    public Tile() {
        this.property = NONE;
        this.speedMultiplier = 1;
    }
    
    public Tile(Property property) {
        this.property = property;
        this.speedMultiplier = 1;
    }
    
    public Tile(Property property, float speedMultiplier) {
        this.property = property;
        this.speedMultiplier = speedMultiplier;
    }

    public void setProperty(Property property){
        this.property = property;
    }
    
    public Property getProperty() {
        return property;
    }

    public float getSpeedMultiplier() {
        return speedMultiplier;
    }
    

    
    
    
    
    
}
