package com.cg.sample;

import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Box;
import javafx.scene.shape.MeshView;
import javafx.scene.transform.*;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class LetterViewController implements LetterView {

    @FXML
    private GridPane gridPane;

    private GridPane gridPaneSmall;
    private Group mainGroup;
    private TextArea transformationMatrixTextArea;

    private LetterPresenter presenter;

    private final List<Rotate> initialRotations = new ArrayList<Rotate>() {{
        add(new Rotate(-15, 0, 0, 0, Rotate.Y_AXIS));
        add(new Rotate(-15, 0, 0, 0, Rotate.X_AXIS));
    }};

    public void init() {

        gridPaneSmall = new GridPane();
        gridPane.getChildren().clear();
        gridPane.add(gridPaneSmall, 0, 0);

        this.presenter = new LetterPresenter(this);
        this.setupKeyEventHandlers();


        this.gridPaneSmall.setHgap(20);
        this.gridPaneSmall.setGridLinesVisible(true);

        this.setupColumns();
        this.setupRows();

        this.mainGroup = new Group();
        this.mainGroup.getTransforms().addAll(this.initialRotations);
        this.gridPaneSmall.add(this.mainGroup, 0, 0);

        this.transformationMatrixTextArea = ControlUtils.createReadonlyTextArea(120, 100);
        this.gridPaneSmall.add(this.transformationMatrixTextArea, 1, 0);

        this.addLetterMeshViewGroup();
        this.addAxisGroup();


    }

    private void setupColumns() {
        this.gridPaneSmall.getColumnConstraints().clear();

        this.gridPaneSmall.getColumnConstraints().add(ControlUtils.createColumn(80, HPos.CENTER));
        this.gridPaneSmall.getColumnConstraints().add(ControlUtils.createColumn(15, HPos.RIGHT));
        this.gridPaneSmall.getColumnConstraints().add(ControlUtils.createColumn(5, HPos.CENTER));
    }

    private void setupRows() {
        this.gridPaneSmall.getRowConstraints().clear();

        this.gridPaneSmall.getRowConstraints().add(ControlUtils.createRow(90, VPos.CENTER));
    }

    private void addLetterMeshViewGroup() {
        LetterMesh letterMesh = new LetterMesh(100, 130);
        MeshView letterMeshView = MeshViewUtils.createMeshView(letterMesh.getMesh());
        this.mainGroup.getChildren().add(letterMeshView);
    }

    private void addAxisGroup() {
        Group axis = new Group();

        int width = 300;

        Box xAxis = ControlUtils.createBox(width, 3, 3);
        xAxis.setTranslateY(width / 2);
        xAxis.setTranslateZ(width / 2);

        Box yAxis = ControlUtils.createBox(3, width, 3);
        yAxis.setTranslateX(-width / 2);
        yAxis.setTranslateZ(width / 2);

        Box zAxis = ControlUtils.createBox(3, 3, width);
        zAxis.setTranslateX(-width / 2);
        zAxis.setTranslateY(width / 2);

        axis.getChildren().addAll(xAxis, yAxis, zAxis);
        axis.getTransforms().addAll(this.initialRotations);

        this.gridPaneSmall.add(axis, 0, 0);
    }

    private void setupKeyEventHandlers() {
        this.gridPaneSmall.requestFocus();
        this.gridPaneSmall.addEventHandler(KeyEvent.KEY_PRESSED, this.presenter::onKeyPressed);
    }

    @Override
    public void rotateHorizontal() {
        rotateXoY();
    }

    @Override
    public void rotateVertical() {
        rotateYoZ();
    }

    @Override
    public void rotateDefault() {
        init();
    }

    @Override
    public void rotateLetter(Rotate rotate) {
        this.transform(rotate);
    }

    @Override
    public void scaleLetter(Scale scale) {
        this.transform(scale);
    }

    @Override
    public void translateXLetter(Translate translate) {
        this.transform(translate);
    }

    private void transform(Transform transform) {
        this.mainGroup.getTransforms().add(transform);
        this.showTransformationMatrix(transform);
    }

    private void showTransformationMatrix(Transform transform) {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        StringBuilder matrix = new StringBuilder();

        matrix
                .append(transform.getClass().getSimpleName())
                .append(": \n");

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                matrix
                        .append(String.format("%1s", decimalFormat.format(transform.getElement(MatrixType.MT_3D_3x4, i, j))))
                        .append(" ");
            }
            matrix.append("\n");
        }

        this.transformationMatrixTextArea.setText(matrix.toString());
    }

    private void rotateXoY() {
        gridPaneSmall.setRotationAxis(Rotate.X_AXIS);
        gridPaneSmall.setRotate(-90);
    }

    private void rotateYoZ() {
        gridPaneSmall.setRotationAxis(Rotate.Z_AXIS);
        gridPaneSmall.setRotate(90);
    }

    private void setCameraRotation() {
        PerspectiveCamera camera = new PerspectiveCamera(true);
        mainGroup.getScene().setCamera(camera);
    }
}