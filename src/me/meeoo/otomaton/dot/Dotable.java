/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package me.meeoo.otomaton.dot;

/**
 *
 * @author duncan.berenguier
 */
public interface Dotable {

    /**
     * Construct a StringBuilder representing this object in graphviz's dot format
     * @return a StringBuilder representing this object
     */
    public StringBuilder toGraphviz(StringBuilder sb);

}
