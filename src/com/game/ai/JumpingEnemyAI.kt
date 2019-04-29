package com.game.ai

import com.game.objects.GameObject
import com.game.objects.GameObjectLiving
import com.game.objects.Player
import com.game.util.ID

class JumpingEnemyAI : IHasAI {
    override fun onInit(living: GameObjectLiving, obj: GameObject) {
        if(obj.id == ID.Player) {
            val player = obj as Player
            if(player.health > 0) {
                when {
                    living.x > player.x && living.velx > -1.5f -> living.velx += -0.44f
                    living.x < player.x && living.velx < 1.5f ->  living.velx += 0.44f
                    living.x.toInt() == player.x.toInt() -> living.velx = 0.0f
                }
            } else {
                living.velx = 0.0f
            }
        }
    }
}