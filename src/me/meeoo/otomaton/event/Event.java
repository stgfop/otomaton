/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.meeoo.otomaton.event;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import me.meeoo.otomaton.automata.State;
import me.meeoo.otomaton.game.Game;

public abstract class Event<G extends Game> {

    private EventStatus status;
    private State nextState;
    private EventFailedStatus failStatus;
    private long transitionId;
    private String gameVisualHash;

    public Event() {
        this.status = EventStatus.RECIEVED;
        this.nextState = null;
        this.failStatus = null;
    }

    public abstract boolean execute(G game);

    protected abstract void sendEvent(DataOutput out) throws IOException;

    protected abstract void readEvent(DataInput in) throws IOException;

    public void read(DataInput in) throws IOException {
        gameVisualHash = in.readUTF();
        transitionId = in.readLong();
        readEvent(in);
    }

    public void send(DataOutput out) throws IOException {
        out.writeInt(status.ordinal());
        if (isFailed()) {
            out.writeUTF(failStatus.toString());
        } else if (isTriggered()) {
            long d = -1;
            if (nextState != null) {
                nextState.getId();
            }
            out.writeLong(d);
        }
        sendEvent(out);
    }

    public boolean isTriggered() {
        return this.status == EventStatus.TRIGGERED;
    }

    public boolean isFailed() {
        return this.status == EventStatus.FAILED;
    }

    public EventStatus getStatus() {
        return status;
    }

    public void setStatus(EventStatus status) {
        this.status = status;
    }

    public State getNextState() {
        return nextState;
    }

    public EventFailedStatus getFailStatus() {
        return failStatus;
    }

    public void setFailStatus(EventFailedStatus failStatus) {
        this.failStatus = failStatus;
    }

    public void setNextState(State nextState) {
        this.nextState = nextState;
    }

    public long getTransitionId() {
        return transitionId;
    }

    public void setTransitionId(long transitionId) {
        this.transitionId = transitionId;
    }

    public String getGameVisualHash() {
        return gameVisualHash;
    }

    public void setGameVisualHash(String gameVisualHash) {
        this.gameVisualHash = gameVisualHash;
    }

}
