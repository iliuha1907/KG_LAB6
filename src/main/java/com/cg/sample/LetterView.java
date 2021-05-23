package com.cg.sample;

import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;

public interface LetterView {
    void rotateHorizontal();
    void rotateVertical();
    void rotateDefault();
    void rotateLetter(Rotate rotate);
    void scaleLetter(Scale scale);
    void translateXLetter(Translate translate);
}

