/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.meeoo.otomaton.event;

/**
 *
 * @author duncan.berenguier
 */
public enum EventStatus {

    RECIEVED(0, "recieved"),
    TRIGGERED(1, "triggered"),
    FAILED(2, "failed");

    private int id;
    private final String name;

    private EventStatus(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

}
