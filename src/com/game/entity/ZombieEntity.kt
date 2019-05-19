package com.game.entity

import com.game.GameBase
import com.game.ai.AIType
import com.game.objects.GameObjectLiving
import com.game.util.Assets
import com.game.util.Direction
import com.game.util.Sounds
import java.awt.Graphics

class ZombieEntity constructor(x: Float, y: Float, game: GameBase): CommonEntity(x, y, 32, 64, game) {
    override fun initAI() {
        appendAI(AI_JUMP, AIType.Looped)
        appendAI(AI_ENEMY, AIType.Looped)
    }

    override fun tick() {
        super.tick()
        val sound = rand.nextInt(4)
        var soundInterval = rand.nextInt(2500)
        if (soundInterval < 700) soundInterval = 500
        when {
            sound == 0 && ticks.rem(soundInterval) == 0 -> Sounds.zombieambient1.play(1.0f, 0.15f)
            sound == 1 && ticks.rem(soundInterval) == 0 -> Sounds.zombieambient2.play(1.0f, 0.15f)
            sound == 2 && ticks.rem(soundInterval) == 0 -> Sounds.zombieambient3.play(1.0f, 0.15f)
            sound == 3 && ticks.rem(soundInterval) == 0 -> Sounds.zombieambient4.play(1.0f, 0.15f)
            sound == 4 && ticks.rem(soundInterval) == 0 -> Sounds.zombieambient5.play(1.0f, 0.15f)
        }
    }

    override fun die() {
        super.die()
        val sound = rand.nextInt(2)
        when (sound) {
            0 -> Sounds.zombiedeath1.play(1.0f, 0.15f)
            1 -> Sounds.zombiedeath2.play(1.0f, 0.15f)
            2 -> Sounds.zombiedeath3.play(1.0f, 0.15f)
        }
    }

    override fun render(g: Graphics) {
        super.render(g)
        val imgX = (if (looks == Direction.LEFT) x + 32f else x).toInt()
        val imgWidth = if (looks == Direction.LEFT) -32 else 32
        if (this.health > 0) {
            g.drawImage(Assets.zombie, imgX, y.toInt(), imgWidth, 64, null)
        }
    }

    override fun hit(hp: Int, living: GameObjectLiving?) {
        super.hit(hp, living)
        val sound = rand.nextInt(2)
        when (sound) {
            0 -> Sounds.zombiehit1.play(1.0f, 0.15f)
            1 -> Sounds.zombiehit2.play(1.0f, 0.15f)
            2 -> Sounds.zombiehit3.play(1.0f, 0.15f)
        }
    }
}