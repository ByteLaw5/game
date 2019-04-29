package com.game.ai

import com.game.objects.GameObject
import com.game.objects.GameObjectLiving
import com.game.objects.Player
import com.game.util.ID

class EnemyAI : IHasAI {
    override fun onInit(living: GameObjectLiving, obj: GameObject) {
        if (obj.id == ID.Player) {
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
        if (obj.id == ID.Block) {
            if (living.boundingBoxTop.intersects(obj.boundingBox)) {
                living.y = obj.y + living.boundingBox.height / 2
                living.vely = 0f
            }
            if (living.boundingBoxDown.intersects(obj.getBoundingBox())) {
                living.y = obj.y - living.boundingBox.height
                living.vely = 0f
                living.setFalling(false)
            } else {
                living.setFalling(true)
            }
            if (living.boundingBoxRight.intersects(obj.boundingBox)) {
                living.x = obj.x - 32
                living.vely = -10f
                living.setJumping(true)
            }
            if (living.boundingBoxLeft.intersects(obj.boundingBox)) {
                living.x = obj.x + 32
                living.vely = -10f
                living.setJumping(true)
            }
        } else if (obj.id == ID.Player && living.boundingBox.intersects(obj.boundingBox) && living.tickLeft == 0) {
            val player = obj as Player
            player.hit(10, living)
            living.tickLeft = 50 // Ticks left for another hit
            player.isJumping = true
            player.vely = -6f
        }
    }
}
