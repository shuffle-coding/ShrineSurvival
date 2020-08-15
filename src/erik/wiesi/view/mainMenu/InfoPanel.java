package erik.wiesi.view.mainMenu;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

import java.io.IOException;

public class InfoPanel extends Label {

    private final String FONT = "/Fonts/kenvector_future.ttf";

    /**
     * Creats new {@link InfoPanel} with Predefined Parameters for:
     * PrefWidth, PrefHeight, Padding
     * @param text String shown on this {@link InfoPanel}
     */
    public InfoPanel(String text) {
        setPrefWidth(600);
        setPrefHeight(25);
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

    /**
     * Method for Changing the Font Size on this {@link InfoPanel}
     * @param fontSize new Font Size for this {@link InfoPanel}
     */
    public void setFontSize(int fontSize) {
        try {
            setFont(Font.loadFont(getClass().getResource(FONT).openStream(), fontSize));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
