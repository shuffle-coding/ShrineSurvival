package erik.wiesi.model.characters;

import erik.wiesi.model.Sprite;
import erik.wiesi.model.Spritesheet;

public class PlayerSprite {

    private Sprite player;

    private int[] bodyModel;
    private int[] pants;
    private int[] shoes;
    private int[] top;
    private int[] head;
    private int[] leftHand;
    private int[] rightHand;
    Spritesheet spritesheet;

    public PlayerSprite(Spritesheet spritesheet, int[] bodyModel, int[] pants, int[] shoes, int[] top, int[] head, int[] leftHand, int[] rightHand) {
        this.spritesheet = spritesheet;
        this.bodyModel = bodyModel;
        this.pants = pants;
        this.shoes = shoes;
        this.top = top;
        this.head = head;
        this.leftHand = leftHand;
        this.rightHand = rightHand;
    }

    public PlayerSprite(Spritesheet spritesheet, int[] bodyModel, int[] pants, int[] shoes, int[] top, int[] head) {
        this.spritesheet = spritesheet;
        this.bodyModel = bodyModel;
        this.pants = pants;
        this.shoes = shoes;
        this.top = top;
        this.head = head;
    }

    private void buildSprite() {
        player = new Sprite(spritesheet);
        player.addSprite(bodyModel[0], bodyModel[1]);
        player.addSprite(pants[0], pants[1]);
        player.addSprite(shoes[0], shoes[1]);
        player.addSprite(top[0], top[1]);
        player.addSprite(head[0], head[1]);
        if (!(leftHand.length > 0) && !(rightHand.length > 0)) {
            player.addSprite(leftHand[0], leftHand[1]);
            player.addSprite(rightHand[0], rightHand[1]);
        }
    }
}
