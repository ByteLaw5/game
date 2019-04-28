package com.game.objects;

import com.game.GameBase;
import com.game.util.Direction;
import com.game.util.ICollision;
import com.game.util.ID;
import com.game.util.IMoveable;

import java.awt.*;

public class Bullet extends GameObject implements IMoveable, ICollision {
    private float velx, vely;
    private Direction direction;
    private GameObjectLiving parent;

    public Bullet(float x, float y, Direction direction, GameObjectLiving parent, GameBase game) {
        super(x, y, ID.Bullet, game);
        this.direction = direction;
        this.parent = parent;
        switchDirections();
    }

    private void switchDirections() {
        switch(direction) {
            case LEFT:
                this.setVelx(-6F);
                break;
            case RIGHT:
                this.setVelx(6F);
                break;
            case DOWN:
                this.setVely(-6F);
                break;
            case UP:
                this.setVely(6F);
                break;
            case NONE:
                this.setVelx(0F);
                this.setVely(0F);
                break;
        }
    }

    @Override
    public void tick() {
        x += velx;
        y += vely;

        checkCollisions();
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.GRAY);
        g.fillRect(this.getBoundingBox().x, this.getBoundingBox().y, this.getBoundingBox().width, this.getBoundingBox().height);
    }

    @Override
    public Rectangle getBoundingBox() {
        return new Rectangle((int)this.x, (int)this.y, 16, 16);
    }

    @Override
    public void checkCollisions() {
        for(GameObject object : GameBase.OBJECTS) {
            if(object.getId() == ID.Player) {
                if(this.getBoundingBox().intersects(object.getBoundingBox())) {
                    Player player = (Player)object;
                    player.hit(20, parent);
                    this.remove();
                }
            } else if(object.getId() == ID.Block) {
                if(this.getBoundingBox().intersects(object.getBoundingBox())) {
                    this.remove();
                }
            }
        }
    }

    public Rectangle getBoundingBoxTop() {
        return new Rectangle(((int)x+(this.getBoundingBox().width/2)-((this.getBoundingBox().width/2)/2)), (int)y, this.getBoundingBox().width/2, this.getBoundingBox().height/2);
    }

    public Rectangle getBoundingBoxDown() {
        return new Rectangle(((int)x + (this.getBoundingBox().width / 2) - ((this.getBoundingBox().width / 2) / 2)), ((int)y + (this.getBoundingBox().height / 2)), this.getBoundingBox().width / 2, this.getBoundingBox().height / 2);
    }

    public Rectangle getBoundingBoxRight() {
        return new Rectangle(((int)x + this.getBoundingBox().width - 5), (int)this.y + 5, 5, this.getBoundingBox().height - 10);
    }

    public Rectangle getBoundingBoxLeft() {
        return new Rectangle((int)this.x, (int)this.y + 5, 5, this.getBoundingBox().height - 10);
    }

    @Override
    public float getVelx() {
        return velx;
    }

    @Override
    public float getVely() {
        return vely;
    }

    @Override
    public void setVelx(float velx) {
        this.velx = velx;
    }

    @Override
    public void setVely(float vely) {
        this.vely = vely;
    }
}
