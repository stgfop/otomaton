/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.meeoo.otomaton.automata;

import me.meeoo.otomaton.dot.Dotable;
import me.meeoo.otomaton.event.Event;
import me.meeoo.otomaton.event.EventStatus;
import me.meeoo.otomaton.event.InDifferentStateFailStatus;
import me.meeoo.otomaton.event.InvalidEventFailStatus;
import me.meeoo.otomaton.game.Game;
import me.meeoo.otomaton.json.JSONable;

public class Transition implements JSONable, Dotable, UniqueID {

    private static long nextID = 0;

    protected static long getNewID() {
        return nextID++;
    }

    private final long id;
    private State in;
    private State out;
    private TransitionCondition condition;

    public Transition(State in, State out) {
        this.id = getNewID();
        this.in = in;
        this.out = out;
        this.condition = null;
    }

    public Transition(State in, State out, TransitionCondition condition) {
        this.id = getNewID();
        this.in = in;
        this.out = out;
        this.condition = condition;
    }

    public boolean isValid(Game game, Event event) {
        if (condition == null) {
            return true;
        }
        return condition.isValid(game, event);
    }

    public boolean trigger(State currentState, Game game, Event event) {
        if (false == in.equals(currentState)) {
            event.setStatus(EventStatus.FAILED);
            event.setFailStatus(new InDifferentStateFailStatus());
            return false;
        }
        if (isValid(game, event)) {
            event.setStatus(EventStatus.TRIGGERED);
            event.setNextState(out);
            return true;
        } else {
            event.setStatus(EventStatus.FAILED);
            if (event.getFailStatus() == null) {
                event.setFailStatus(new InvalidEventFailStatus());
            }
            return false;
        }
    }

    @Override
    public StringBuilder toJSON(StringBuilder sb) {
        sb.append('{');
        sb.append("\"type\":\"Transition\",");

        sb.append(",\"id\":");
        sb.append(id);

        sb.append(",\"in\":");
        getIn().toJSON(sb);

        sb.append(",\"out\":");
        getOut().toJSON(sb);

        sb.append(",\"condition\":");
        if (getCondition() != null) {
            getCondition().toJSON(sb);
        } else {
            sb.append("null");
        }

        sb.append('}');

        return sb;
    }

    @Override
    public StringBuilder toGraphviz(StringBuilder sb) {

        sb.append(getIn().getId());
        sb.append("->");
        sb.append(getOut().getId());
        if (getCondition() != null) {
            sb.append("[label=\"");
            getCondition().toGraphviz(sb);
            sb.append("\"]");
        }
        sb.append(';');

        return sb;
    }

    @Override
    public long getId() {
        return id;
    }

    /**
     * @return the in
     */
    public State getIn() {
        return in;
    }

    /**
     * @return the out
     */
    public State getOut() {
        return out;
    }

    /**
     * @return the condition
     */
    public TransitionCondition getCondition() {
        return condition;
    }

}
