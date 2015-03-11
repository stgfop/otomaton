/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.meeoo.otomaton.automata;

import java.util.LinkedList;
import java.util.List;
import me.meeoo.otomaton.dot.Dotable;
import me.meeoo.otomaton.json.JSONable;

public class State implements JSONable, Dotable, UniqueID {

    private static long nextID = 0;

    protected static long getNewID() {
        return nextID++;
    }

    private final long id;
    private final List<Transition> in;
    private final List<Transition> out;
    private String name;

    public State() {
        this.id = getNewID();
        this.in = new LinkedList<>();
        this.out = new LinkedList<>();
        this.name = String.format("s%4d", id);
    }

    public State(String name) {
        this.id = getNewID();
        this.in = new LinkedList<>();
        this.out = new LinkedList<>();
        this.name = name;
    }

    @Override
    public StringBuilder toJSON(StringBuilder sb) {
        sb.append('{');
        sb.append("\"type\":\"State\",");

        sb.append("\"name\":");
        sb.append(name);

        sb.append(",\"id\":");
        sb.append(id);

        sb.append('}');

        return sb;
    }

    @Override
    public StringBuilder toGraphviz(StringBuilder sb) {
        sb.append(id);
        sb.append(" [label=\"");
        sb.append(name);
        sb.append("\",");
        if (getIn().isEmpty() || getOut().isEmpty()) {
            sb.append("shape=circle");
        } else {
            sb.append("shape=box");
        }
        sb.append("];");
        return sb;
    }

    @Override
    public long getId() {
        return id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the in
     */
    public List<Transition> getIn() {
        return in;
    }

    /**
     * @return the out
     */
    public List<Transition> getOut() {
        return out;
    }

}
