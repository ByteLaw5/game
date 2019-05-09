package com.game.ai

import com.game.objects.GameObject
import com.game.objects.GameObjectLiving
import com.game.entity.Player
import com.game.util.Direction
import com.game.util.ID

class ShootingEnemyAIWalk : IHasAI {
    override fun onInit(living: GameObjectLiving, obj: GameObject) {
        if(obj.id == ID.Player) {
            val player = obj as Player
            if(player.health > 0) {
                if(living.y < player.y) {
                    if (living.facing(living, obj) == Direction.LEFT) {
                        living.velx = -1.5f
                    } else if (living.facing(living, obj) == Direction.RIGHT) {
                        living.velx = 1.5f
                    }
                } else if(living.y > player.y) {
                    living.velx = 0.0f
                } else if(living.y == player.y) {
                    living.velx = 0.0f
                }
            }
        }
    }
}