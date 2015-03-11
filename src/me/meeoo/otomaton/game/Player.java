/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package me.meeoo.otomaton.game;

public class Player extends GameObject{
   
    public Player(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return getName();
    }
    
    

}
