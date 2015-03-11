/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.meeoo.otomaton.automata;

import me.meeoo.otomaton.dot.Dotable;
import me.meeoo.otomaton.event.Event;
import me.meeoo.otomaton.game.Game;
import me.meeoo.otomaton.json.JSONable;

/**
 *
 * @author duncan.berenguier
 */
public abstract class TransitionCondition<G extends Game> implements JSONable, Dotable {

    public abstract boolean isValid(G game, Event event);

    @Override
    public StringBuilder toJSON(StringBuilder sb) {
        sb.append('"');
        sb.append(this.getClass().getSimpleName());
        sb.append('"');
        return sb;
    }

    @Override
    public StringBuilder toGraphviz(StringBuilder sb) {
        sb.append(this.getClass().getSimpleName());
        return sb;
    }
}
