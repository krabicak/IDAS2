package view.gui.libs;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import model.LearningAction;

public class Action extends VBox {
    private LearningAction learningAction;

    public Action(LearningAction learningAction) {
        super();
        this.learningAction = learningAction;
        if (learningAction.getId() != null) {
            Label ucitel = new Label(learningAction.getVyucujici().toString());
            ucitel.setTextAlignment(TextAlignment.CENTER);
            Label ucebna = new Label(learningAction.getUcebna().toString());
            ucebna.setTextAlignment(TextAlignment.CENTER);
            Label predmet = new Label(learningAction.getPredmet().toString());
            predmet.setTextAlignment(TextAlignment.CENTER);

            this.getChildren().addAll(ucitel, ucebna, predmet);
        }
        this.prefWidth(200);
        this.prefHeight(100);
        this.setBackground(new Background(new BackgroundFill(Color.LIGHTYELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
    }

    public LearningAction getLearningAction() {
        return learningAction;
    }

    public void setLearningAction(LearningAction learningAction) {
        this.learningAction = learningAction;
    }

    @Override
    public String toString() {
        return this.learningAction.toString();
    }
}