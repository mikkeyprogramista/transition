package com.adz1q.transition;

import javafx.animation.FillTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private Slider startWidth;

    @FXML
    private Slider startHeight;

    @FXML
    private ColorPicker finalColor;

    @FXML
    private Slider finalWidth;

    @FXML
    private Slider finalHeight;

    @FXML
    private Slider finalRotate;

    @FXML
    private TextField txtMultiplier;

    @FXML
    private TextField txtFinalX;

    @FXML
    private TextField txtFinalY;

    @FXML
    private Button btnTransform;

    @FXML
    private Rectangle square;

    private static final Duration DURATION = new Duration(2000);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        startWidth.valueProperty().addListener((observableValue, value, newValue) -> {
            square.setScaleX(Double.parseDouble(newValue.toString()));
        });

        startHeight.valueProperty().addListener((observableValue, value, newValue) -> {
            square.setScaleY(Double.parseDouble(newValue.toString()));
        });

        btnTransform.setOnAction(actionEvent -> transition());
    }

    private void transition() {
        if (txtMultiplier.getText().isBlank()) return;
        if (txtFinalX.getText().isBlank()) return;
        if (txtFinalY.getText().isBlank()) return;

        rotate();
        scale();
        translate();
        changeColor();
    }

    private void rotate() {
        var rotate = new RotateTransition(DURATION, square);
        rotate.setFromAngle(square.getRotate() * Double.parseDouble(txtMultiplier.getText()));
        rotate.setToAngle(finalRotate.getValue());
        rotate.play();
    }

    private void scale() {
        var scale = new ScaleTransition(DURATION, square);
        scale.setFromX(square.getScaleX());
        scale.setFromY(square.getScaleY());
        scale.setToX(finalWidth.getValue());
        scale.setToY(finalHeight.getValue());
        scale.play();
    }

    private void translate() {
        var translate = new TranslateTransition(DURATION, square);
        translate.setFromX(square.getX());
        translate.setFromY(square.getY());
        translate.setToX(Double.parseDouble(txtFinalX.getText()));
        translate.setToY(Double.parseDouble(txtFinalY.getText()));
        translate.play();
    }

    private void changeColor() {
        var fill = new FillTransition(DURATION, square);
        fill.setFromValue((Color) square.getFill());
        fill.setToValue(finalColor.getValue());
        fill.play();
    }
}
