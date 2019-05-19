package com.game.ai

import com.game.objects.GameObject
import com.game.objects.GameObjectLiving

interface IHasAI {
    fun onInit(living: GameObjectLiving, obj: GameObject)
}