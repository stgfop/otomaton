/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.meeoo.otomaton.core;

import me.meeoo.otomaton.core.exception.InvalidTransitionException;
import me.meeoo.otomaton.core.exception.WrongStateException;
import me.meeoo.otomaton.dot.Dotable;
import me.meeoo.otomaton.json.JSONable;
import me.meeoo.otomaton.json.JSONifier;

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

    public boolean isValid(Event event) {
        if (getCondition() == null) {
            return true;
        }
        return getCondition().isValid(event);
    }

    public State fire(State currentState, Event event) throws InvalidTransitionException, WrongStateException {
        if (false == in.equals(currentState)) {
            throw new WrongStateException();
        }
        if (isValid(event)) {
            return getOut();
        } else {
            throw new InvalidTransitionException();
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

        sb.append(getIn().getID());
        sb.append("->");
        sb.append(getOut().getID());
        if (getCondition() != null) {
            sb.append("[label=\"");
            getCondition().toGraphviz(sb);
            sb.append("\"]");
        }
        sb.append(';');

        return sb;
    }

    @Override
    public long getID() {
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
