/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.meeoo.otomaton.game;

import me.meeoo.otomaton.automata.UniqueID;

/**
 *
 * @author duncan.berenguier
 */
public class GameObject implements UniqueID {

    private static long nextID = 0;

    protected static long getNewID() {
        return nextID++;
    }

    private final long id;
    private final String name;

    public GameObject(String name) {
        this.id = getNewID();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public long getId() {
        return id;
    }

}
