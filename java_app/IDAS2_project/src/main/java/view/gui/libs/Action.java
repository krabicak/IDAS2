package view.gui.libs;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import model.LearningAction;

public class Action extends VBox {
    private LearningAction learningAction;

    public Action(LearningAction learningAction) {
        this.learningAction = learningAction;
        Label ucitel = new Label(learningAction.getVyucujici().toString());
        Label ucebna = new Label(learningAction.getUcebna().toString());
        Label predmet = new Label(learningAction.getPredmet().toString());

        this.getChildren().addAll(ucitel, ucebna, predmet);
    }

    public LearningAction getLearningAction() {
        return learningAction;
    }

    public void setLearningAction(LearningAction learningAction) {
        this.learningAction = learningAction;
    }
}
