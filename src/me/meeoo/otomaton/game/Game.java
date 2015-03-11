/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.meeoo.otomaton.game;

import java.util.LinkedList;
import java.util.List;
import me.meeoo.otomaton.automata.Otomaton;
import me.meeoo.otomaton.event.Event;

public abstract class Game<O extends Otomaton, P extends Player> extends GameObject {

    protected final List<P> players;

    public Game(String name) {
        super(name);
        this.players = new LinkedList<>();
    }

    public abstract O getOtomaton();

    public void addPlayer(P p) {
        players.add(p);
    }

    public List<P> getPlayers() {
        return players;
    }

    public P getPlayer(long playerId) {
        for (P p : players) {
            if (p.getId() == playerId) {
                return p;
            }
        }
        return null;
    }

    public boolean recieveEvent(Event event) {
        Otomaton otomaton = getOtomaton();
        boolean success = otomaton.doTransition(event.getTransitionId(), this, event);
        if (success) {
            success = event.execute(this);
        }
        return success;
    }

    public String getVisualHash() {
        final int hashCode = ("mymagicstring" + getId() + getName()).hashCode();
        final String toHexString = Integer.toHexString(hashCode);
        return toHexString.substring(0, Math.min(6, toHexString.length()));
    }

}
