/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.meeoo.otomaton.automata;

import java.util.HashMap;
import java.util.Map;
import me.meeoo.otomaton.dot.Dotable;
import me.meeoo.otomaton.event.Event;
import me.meeoo.otomaton.game.Game;
import me.meeoo.otomaton.json.JSONable;
import me.meeoo.otomaton.json.JSONifier;

public class Otomaton<G extends Game> implements JSONable, Dotable {

    private State edenState;
    private State currentState;
    private final Map<Long, State> states;
    private final Map<Long, Transition> transitions;

    public Otomaton() {
        this.states = new HashMap<>();
        this.transitions = new HashMap<>();
    }

    public boolean doTransition(Transition t, G game, Event event) {
        boolean success = t.trigger(currentState, game, event);
        if (success) {
            currentState = event.getNextState();
        }
        return success;
    }

    public boolean doTransition(long tid, G game, Event event) {
        return doTransition(transitions.get(tid), game, event);
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
        states.put(s.getId(), s);
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
        transitions.put(t.getId(), t);
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

    /**
     * @return the currentState
     */
    public State getCurrentState() {
        return currentState;
    }

    /**
     * @return the states
     */
    public Map<Long, State> getStates() {
        return states;
    }

    /**
     * @return the transitions
     */
    public Map<Long, Transition> getTransitions() {
        return transitions;
    }

}
