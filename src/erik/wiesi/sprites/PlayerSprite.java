package erik.wiesi.sprites;

import javafx.scene.canvas.Canvas;

public class PlayerSprite {

    private Sprite player;

    private final int[] bodyModel;
    private final int[] pants;
    private final int[] shoes;
    private final int[] top;
    private final int[] head;
//    private int[] leftHand;
//    private int[] rightHand;
    private Spritesheet spritesheet;

    /* public PlayerSprite(Spritesheet spritesheet, int[] bodyModel, int[] pants, int[] shoes, int[] top, int[] head, int[] leftHand, int[] rightHand) {
        this.spritesheet = spritesheet;
        this.bodyModel = bodyModel;
        this.pants = pants;
        this.shoes = shoes;
        this.top = top;
        this.head = head;
        this.leftHand = leftHand;
        this.rightHand = rightHand;
        buildSprite();
        addHands();
    } */

    /**
     * Constructs new {@link PlayerSprite} with given parameters and builds it as a new {@link Sprite}
     * @param spritesheet {@link Spritesheet} as reference for the other parameters
     * @param bodyModel int[x, y] Values for Body Model referencing {@link Spritesheet} X and Y counter from top left, starting with 0
     * @param pants int[x, y] Values  for Pants referencing {@link Spritesheet} X and Y counter from top left, starting with 0
     * @param shoes int[x, y] Values  for Shoes referencing {@link Spritesheet} X and Y counter from top left, starting with 0
     * @param top int[x, y] Values  for Top referencing {@link Spritesheet} X and Y counter from top left, starting with 0
     * @param head int[x, y] Values  for Head referencing {@link Spritesheet} X and Y counter from top left, starting with 0
     */
    public PlayerSprite(Spritesheet spritesheet, int[] bodyModel, int[] pants, int[] shoes, int[] top, int[] head) {
        this.spritesheet = spritesheet;
        this.bodyModel = bodyModel;
        this.pants = pants;
        this.shoes = shoes;
        this.top = top;
        this.head = head;
        buildSprite();
    }

    private void buildSprite() {
        player = new Sprite(spritesheet);
        player.addSprite(bodyModel[0], bodyModel[1]);
        player.addSprite(pants[0], pants[1]);
        player.addSprite(shoes[0], shoes[1]);
        player.addSprite(top[0], top[1]);
        player.addSprite(head[0], head[1]);
    }

    /* private void addHands() {
        player.addSprite(leftHand[0], leftHand[1]);
        player.addSprite(rightHand[0], rightHand[1]);

    } */

    /**
     * @return returns Canvas of this {@link PlayerSprite}
     */
    public Canvas getCanvas() {
        return player.getCanvas();
    }

//    public String toJSON() {
//    }
}
