package com.game.entity

import com.game.GameBase
import com.game.ai.AIType

class ZombieEntity constructor(x: Float, y: Float, game: GameBase): CommonEntity(x, y, 30, 80, game) {
    protected override fun initAI() {
        appendAI(AI_JUMP, AIType.Looped)
        appendAI(AI_ENEMY, AIType.Looped)
    }
}