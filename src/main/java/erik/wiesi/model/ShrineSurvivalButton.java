package erik.wiesi.model;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

import java.io.IOException;

public class ShrineSurvivalButton extends Button {

    private final String RELEASED = "/Buttons/yellow_button_released.png";
    private final String PRESSED = "/Buttons/yellow_button_pressed.png";
    private final String FONT = "/Fonts/kenvector_future.ttf";
    private final String BUTTON_NAME;


    private Background released = new Background(new BackgroundImage(
            new Image(getClass().getResource(RELEASED).toString()),
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.CENTER,
            BackgroundSize.DEFAULT
    ));
    private Background pressed = new Background(new BackgroundImage(
            new Image(getClass().getResource(PRESSED).toString()),
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.CENTER,
            BackgroundSize.DEFAULT
    ));

    public String getButtonName() {
        return BUTTON_NAME;
    }

    public ShrineSurvivalButton(String text, String button_name) {
        BUTTON_NAME = button_name;

        setText(text);
        setButtonFont();

        setBackground(released);

        setPrefHeight(49);
        setPrefWidth(190);
        initializeButtonListeners();
    }

    private void convertException(Exception e) {
        throw new RuntimeException(e.getMessage(), e);
    }

    private void setButtonFont() {
        try {
            setFont(Font.loadFont(getClass().getResource(FONT).openStream(), 18));
        } catch (IOException e) {
            convertException(e);
        }
    }

    private void setButtonPressedStyle() {
        setBackground(pressed);
        setPrefHeight(45);
        setLayoutY(getLayoutY() + 4);
    }

    private void setButtonReleasedStyle() {
        setBackground(released);
        setPrefHeight(49);
        setLayoutY(getLayoutY() - 4);
    }

    private void initializeButtonListeners() {

        setOnMousePressed(event -> {
            if (event.getButton().equals(MouseButton.PRIMARY)) {
                setButtonPressedStyle();
            }
        });

        setOnMouseReleased(event -> {
            if (event.getButton().equals(MouseButton.PRIMARY)) {
                setButtonReleasedStyle();
            }
        });

        setOnMouseEntered(event -> setEffect(new DropShadow()));

        setOnMouseExited(event -> setEffect(null));
    }


}
