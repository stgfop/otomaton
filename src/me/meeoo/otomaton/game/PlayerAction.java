/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.meeoo.otomaton.game;

import java.io.DataInput;
import java.io.IOException;
import me.meeoo.otomaton.event.Event;

public abstract class PlayerAction<G extends Game<?, P>, P extends Player> extends Event<G> {

    private long playerId;

    public P getPlayer(G game) {
        return game.getPlayer(playerId);
    }

    @Override
    public void read(DataInput in) throws IOException {
        super.read(in);
        playerId = in.readLong();
    }
    
    
}
