package com.game.ai

import com.game.objects.GameObject
import com.game.objects.GameObjectLiving
import com.game.entity.Player
import com.game.util.ID

class EnemyAI: IHasAI {
    override fun onInit(living: GameObjectLiving, obj: GameObject) {
        if (obj.id == ID.Player) {
            val player = obj as Player
            if(player.health > 0 && living.nearTo(300F, obj)) {
                when {
                    living.x > player.x -> living.velx = -1.5f
                    living.x < player.x -> living.velx = 1.5f
                    living.x == player.x -> living.velx = 0.0f
                }
            } else {
                living.velx = 0.0f
            }
        }
        /*else if(obj.id == ID.Block && (living.boundingBoxLeft.intersects(obj.boundingBox) || living.boundingBoxRight.intersects(obj.boundingBox))) {
            living.vely = -10.0f
            living.setJumping(true)
        }*/
    }
}
