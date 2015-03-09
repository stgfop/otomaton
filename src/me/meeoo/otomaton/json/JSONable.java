/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package me.meeoo.otomaton.json;

/**
 *
 * @author duncan.berenguier
 */
public interface JSONable {
    /**
     * Construct a StringBuilder representing this object in JSON
     * @return a StringBuilder representing this object
     */
    public StringBuilder toJSON(StringBuilder sb);
}
