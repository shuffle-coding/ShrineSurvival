package erik.wiesi.view.mainMenu;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

import java.io.IOException;

public class PanelInfo extends Label {

    private final String FONT = "/Fonts/kenvector_future.ttf";

    public PanelInfo(String text) {
        setPrefWidth(600);
        setPrefHeight(400);
        setPadding(new Insets(40,40,40,40));
        setText(text);
        setWrapText(true);
        setLabelFont();
    }

    private void setLabelFont(){
        try {
            setFont(Font.loadFont(getClass().getResource(FONT).openStream(), 23));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
