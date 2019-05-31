package com.game.item;

import com.game.GameBase;
import com.game.entity.Player;
import com.game.objects.Block;
import com.game.objects.GameObject;
import com.game.util.ICollision;
import com.game.util.ID;
import com.game.util.IMoveable;

import java.awt.*;
import java.util.Arrays;

public class WorldItem extends GameObject implements ICollision, IMoveable {
    private final StackedItem content;
    private float velx, vely;

    public WorldItem(StackedItem stackedItem, float x, float y, GameBase game) {
        super(x, y, ID.Item, game);
        this.content = stackedItem;
    }

    @Override
    public void tick() {
        x += velx;
        y += vely;
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

    @Override
    public void render(Graphics g) {
        g.drawImage(content.getItem().getTexture(), (int)this.x, (int)this.y, this.getBoundingBox().width, this.getBoundingBox().height, null);
    }

    @Override
    public Rectangle getBoundingBox() {
        return new Rectangle((int)this.x, (int)this.y, 32, 32);
    }

    @Override
    public void checkCollisions() {
        for(GameObject object : GameBase.OBJECTS) {
            if(object.getId() == ID.Block && ((Block)object).getCollidable()) {
                if(this.getBoundingBoxTop().intersects(object.getBoundingBox())) {
                    this.y = object.getY() + (this.getBoundingBox().height >> 1);
                    this.vely = 0;
                }
                if(this.getBoundingBoxDown().intersects(object.getBoundingBox())) {
                    this.y = object.getY() - this.getBoundingBox().height;
                    this.vely = 0;
                } else {
                    this.vely = 5f;
                }
                if(this.getBoundingBoxRight().intersects(object.getBoundingBox())) {
                    this.x = object.getX() - 32;
                }
                if(this.getBoundingBoxLeft().intersects(object.getBoundingBox())) {
                    this.x = object.getX() + 32;
                }
            }
        }
    }

    public void pickUp(Player player) {
        player.inv.push(this.getContent());
        System.out.println(Arrays.deepToString(player.inv.getItems()));
        this.remove();
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

    public StackedItem getContent() {
        return content;
    }
}
