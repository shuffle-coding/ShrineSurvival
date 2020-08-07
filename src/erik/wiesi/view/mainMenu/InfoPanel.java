package erik.wiesi.view.mainMenu;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

import java.io.IOException;

public class InfoPanel extends Label {

    private final String FONT = "/Fonts/kenvector_future.ttf";

    public InfoPanel(String text) {
        setPrefWidth(600);
        setPrefHeight(100);
        setPadding(new Insets(5,5,5,5));
        setText(text);
        setWrapText(true);
        setLabelFont();
    }

    private void setLabelFont() {
        try {
            setFont(Font.loadFont(getClass().getResource(FONT).openStream(), 20));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setFontSize(int fontSize) {
        try {
            setFont(Font.loadFont(getClass().getResource(FONT).openStream(), fontSize));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
