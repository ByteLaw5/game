package com.game.listeners;

import com.game.entity.Player;
import com.game.util.IsListener;
import org.newdawn.slick.Input;

import java.awt.event.MouseAdapter;

@IsListener("mouse")
public class MouseListener extends MouseAdapter implements org.newdawn.slick.MouseListener {
    Player player;

    public MouseListener(Player player) {
        this.player = player;
        player.game.listeners.add(this);
    }

    @Override
    public void mouseWheelMoved(int i) {

    }

    @Override
    public void mouseClicked(int i, int i1, int i2, int i3) {

    }

    @Override
    public void mousePressed(int i, int i1, int i2) {

    }

    @Override
    public void mouseReleased(int i, int i1, int i2) {

    }

    @Override
    public void mouseMoved(int i, int i1, int i2, int i3) {

    }

    @Override
    public void mouseDragged(int i, int i1, int i2, int i3) {

    }

    @Override
    public void setInput(Input input) {

    }

    @Override
    public boolean isAcceptingInput() {
        return false;
    }

    @Override
    public void inputEnded() {

    }

    @Override
    public void inputStarted() {

    }
}
