package erik.wiesi.model;

import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

import java.io.IOException;

public class ShrineSurvivalTextField extends TextField {


    private double width = 190;
    private double height = 49;
    private double fontSize = 18;

    private final String BACKGROUND = "/Background/yellow_button03.png";
    private final String FONT = "/Fonts/kenvector_future.ttf";

    /**
     * Creats new TextField with ShrineSurvival modifiers
     * @param text sets Prompt text
     */
    public ShrineSurvivalTextField (String text) {
        setPromptText(text);
        setTextFont();
        setPrefWidth(width);
        setPrefHeight(height);
        setBackground();
    }

    private void setBackground() {

        try {
            setBackground(new Background(new BackgroundImage(
                    new Image(getClass().getResource(BACKGROUND).toString()),
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.CENTER,
                    BackgroundSize.DEFAULT
            )));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setTextFont() {
        try {
            setFont(Font.loadFont(getClass().getResource(FONT).openStream(), fontSize));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}