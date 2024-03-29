package com.game.objects;

import com.game.GameBase;
import com.game.ai.AIType;
import com.game.ai.ShootingEnemyAI;
import com.game.ai.ShootingEnemyAIWalk;
import com.game.entity.CommonEntity;
import com.game.util.ID;

@Deprecated public class ShootingCommonEntity extends CommonEntity {
    public ShootingCommonEntity(float x, float y, GameBase game) {
        super(x, y, 40, 40, game);
        this.ticksLeft = 200;
    }

    @Override
    public ID getId() {
        return ID.EnemyShooting;
    }

    @Override
    protected void initAI() {
        if(ticksLeft == 0) {
            appendAI(new ShootingEnemyAI(), AIType.Looped);
            ticksLeft = 200;
        }
        appendAI(new ShootingEnemyAIWalk(), AIType.Looped);
    }
}
