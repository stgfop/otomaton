/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package me.meeoo.otomaton.core;

import me.meeoo.otomaton.dot.Dotable;
import me.meeoo.otomaton.json.JSONable;

/**
 *
 * @author duncan.berenguier
 */
public abstract class TransitionCondition implements JSONable, Dotable {

    public abstract boolean isValid(Event event);
}
