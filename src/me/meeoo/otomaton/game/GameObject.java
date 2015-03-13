/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.meeoo.otomaton.game;

import me.meeoo.otomaton.automata.UniqueID;
import me.meeoo.otomaton.json.JSONable;
import me.meeoo.otomaton.json.JSONifier;

/**
 *
 * @author duncan.berenguier
 */
public class GameObject implements UniqueID, JSONable {

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

    @Override
    public StringBuilder toJSON(StringBuilder sb) {
        sb.append('{');
        
        sb.append("\"id\":");
        JSONifier.toJSON(sb, id);
        
        sb.append(",\"name\":");
        JSONifier.toJSON(sb, name);
        
        sb.append('}');
        return sb;
    }

}
