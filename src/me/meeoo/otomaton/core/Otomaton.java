/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.meeoo.otomaton.core;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import me.meeoo.otomaton.core.exception.InvalidTransitionException;
import me.meeoo.otomaton.core.exception.MultipleTransitionException;
import me.meeoo.otomaton.core.exception.WrongStateException;
import me.meeoo.otomaton.dot.Dotable;
import me.meeoo.otomaton.json.JSONable;
import me.meeoo.otomaton.json.JSONifier;

public class Otomaton implements JSONable, Dotable {

    private State edenState;
    private State currentState;
    private final Map<Long, State> states;
    private final Map<Long, Transition> transitions;

    public Otomaton() {
        this.states = new HashMap<>();
        this.transitions = new HashMap<>();
    }

    public void doTransition(long tid, Event event) throws WrongStateException, InvalidTransitionException {
        Transition t = transitions.get(tid);
        currentState = t.fire(currentState, event);
    }

    public void doTransition(Event event) throws WrongStateException, InvalidTransitionException, MultipleTransitionException {
        Transition validTransition = null;
        for (Transition transition : currentState.getOut()) {
            if (transition.isValid(event)) {
                if (validTransition == null) {
                    validTransition = transition;
                } else {
                    throw new MultipleTransitionException();
                }
            }
        }
        if (validTransition == null) {
            throw new InvalidTransitionException();
        } else {
            currentState = validTransition.fire(currentState, event);
        }
    }

    public State addState(String name) {
        State s = new State(name);
        return addState(s);
    }

    public State addState() {
        State s = new State();
        return addState(s);
    }

    public State addState(State s) {
        states.put(s.getID(), s);
        return s;
    }

    public Transition addTransition(State in, State out) {
        Transition t = new Transition(in, out);
        return addTransition(t);
    }

    public Transition addTransition(State in, State out, TransitionCondition condition) {
        Transition t = new Transition(in, out, condition);
        return addTransition(t);
    }

    public Transition addTransition(long in, long out) {
        Transition t = new Transition(states.get(in), states.get(out));
        return addTransition(t);
    }

    public Transition addTransition(long in, long out, TransitionCondition condition) {
        Transition t = new Transition(states.get(in), states.get(out), condition);
        return addTransition(t);
    }

    public Transition addTransition(Transition t) {
        transitions.put(t.getID(), t);
        return t;
    }

    @Override
    public StringBuilder toJSON(StringBuilder sb) {
        sb.append('{');
        sb.append("\"type\":\"Otomaton\",");

        sb.append("\"eden\":");
        edenState.toJSON(sb);

        sb.append(",\"states\":");
        JSONifier.toJSON(sb, states);

        sb.append(",\"transitions\":");
        JSONifier.toJSON(sb, transitions);

        sb.append('}');

        return sb;
    }

    @Override
    public StringBuilder toGraphviz(StringBuilder sb) {
        sb.append("digraph otomaton {\n");

        for (State state : states.values()) {
            state.toGraphviz(sb);
            sb.append('\n');
        }

        sb.append('\n');

        for (Transition transition : transitions.values()) {
            transition.toGraphviz(sb);
            sb.append('\n');
        }

        sb.append('}');
        return sb;
    }

}
