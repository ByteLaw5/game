package com.game.entity;

import com.game.ai.AIType;
import com.game.ai.IHasAI;

public class EntityAI {
    private int ti;
    private AIType ty;
    private IHasAI ha;
    public EntityAI(IHasAI ai, AIType type) {
        ty = type;
        ha = ai;
    }
    public EntityAI(IHasAI ai, AIType type, int time) {
        ty = type;
        ha = ai;
        ti = time;
    }
    public int getTime() {
        return ti;
    }
    public int setTime(int time) {
        return ti = time;
    }
    public AIType getType() {
        return ty;
    }
    public AIType setType(AIType type) {
        return ty = type;
    }
    public IHasAI getAi() {
        return ha;
    }
    public IHasAI setAi(IHasAI ai) {
        return ha = ai;
    }
    public boolean equal(EntityAI o) {
        return (o.getAi() == getAi() || o.getAi().equals(getAi())) && (o.getType() == getType() || o.getType().equals(getType()));
    }
}
