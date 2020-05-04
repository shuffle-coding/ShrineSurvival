package erik.wiesi.model.characters;

import erik.wiesi.model.SpriteTest;
import erik.wiesi.model.SpritesheetTest;

public class PlayerSprite {

    private SpriteTest player;

    private int[] bodyModel;
    private int[] pants;
    private int[] shoes;
    private int[] top;
    private int[] head;
    private int[] beard;
    private int[] leftHand;
    private int[] rightHand;

    public PlayerSprite(int[] bodyModel, int[] pants, int[] shoes, int[] top, int[] head, int[] beard, int[] leftHand, int[] rightHand) {
        this.bodyModel = bodyModel;
        this.pants = pants;
        this.shoes = shoes;
        this.top = top;
        this.head = head;
        this.beard = beard;
        this.leftHand = leftHand;
        this.rightHand = rightHand;
    }
}
