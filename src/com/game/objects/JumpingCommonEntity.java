package com.game.objects;

import com.game.GameBase;
import com.game.ai.AIType;
import com.game.ai.JumpAI;
import com.game.entity.CommonEntity;
import com.game.util.ID;

@Deprecated public class JumpingCommonEntity extends CommonEntity {
    public boolean hasTouched = false;
    private float maxspeed = 3f;

    public JumpingCommonEntity(float x, float y, GameBase game) {
        super(x, y, 40, 40, game);
    }

    @Override
    public void tick() {
        super.tick();

        if(velx > maxspeed) {
            velx = maxspeed;
        } else if(velx < -maxspeed) {
            velx = -maxspeed;
        }
    }

    @Override
    protected void initAI() {
        appendAI(new JumpAI(), AIType.Looped);
    }

    @Override
    public ID getId() {
        return ID.EnemyJumping;
    }

    @Override
    protected void jump() {
        this.vely = -7.0f;
        if(velx > 0) {
            velx -= 4f;
        } else {
            velx += 4f;
        }
        this.setJumping(true);
    }

    @Override
    public void checkCollisions() {
        for(GameObject object : GameBase.OBJECTS) {
            if(object.getId() == ID.Block) {
                if(this.getBoundingBoxTop().intersects(object.getBoundingBox())) {
                    this.y = object.getY() + (this.getBoundingBox().height >> 1);
                    this.vely = 0;
                }
                if(this.getBoundingBoxDown().intersects(object.getBoundingBox())) {
                    this.y = object.getY() - this.getBoundingBox().height;
                    this.vely = 0;
                    this.falling = false;
                    hasTouched = false;
                } else {
                    this.falling = true;
                }
                if(this.getBoundingBoxRight().intersects(object.getBoundingBox()) && !hasTouched) {
                    this.x = object.getX() - 32;
                    this.hasTouched = true;
                    this.jump();
                }
                if(this.getBoundingBoxLeft().intersects(object.getBoundingBox()) && !hasTouched) {
                    this.x = object.getX() + 32;
                    this.hasTouched = true;
                    this.jump();
                }
            } else if(object.getId() == ID.Player && this.getBoundingBox().intersects(object.getBoundingBox()) && ticksLeft == 0) {
                Player player = (Player)object;
                player.hit(10, this);
                ticksLeft = 50; // Ticks left for another hit
                player.setJumping(true);
                player.setVely(-6F);
            }
        }
    }
}
