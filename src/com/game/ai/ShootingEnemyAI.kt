package com.game.ai

import com.game.objects.Bullet
import com.game.objects.GameObject
import com.game.objects.GameObjectLiving
import com.game.entity.Player
import com.game.util.ID

class ShootingEnemyAI : IHasAI {
    override fun onInit(living: GameObjectLiving, obj: GameObject) {
        if(obj.id == ID.Player) {
            val player = obj as Player
            if(player.health > 0) {
               living.game.addObject(Bullet(living.x, living.y, living.facing(living, obj), living, living.game))
            }
        }
    }
}