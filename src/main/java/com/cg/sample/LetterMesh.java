package com.cg.sample;
import javafx.scene.shape.TriangleMesh;

public class LetterMesh {

    private static final int[] FACES = new int[] {
            2,0, 3,0, 0,0,
            2,0, 0,0, 1,0,
            5,0, 4,0, 7,0,
            5,0, 7,0, 6,0,
            6,0, 7,0, 3,0,
            6,0, 3,0, 2,0,
            7,0, 4,0, 0,0,
            7,0, 0,0, 3,0,
            5,0, 6,0, 2,0,
            5,0, 2,0, 1,0,
            4,0, 5,0, 1,0,
            4,0, 1,0, 0,0,
            8,0,5,0,6,0,
            8,0,6,0,9,0,
            9,0,6,0,11,0,
            9,0,11,0,13,0,
            8,0,9,0,13,0,
            8,0,13,0,12,0,
            5,0,8,0,12,0,
            5,0,12,0,10,0,
            13,0,11,0,10,0,
            13,0,10,0,12,0,

    };

    private TriangleMesh mesh;

    public LetterMesh(float width, float height) {
        this.mesh = new TriangleMesh();

        this.mesh.getPoints().addAll(LetterMesh.calculatePoints(width, height));
        this.mesh.getTexCoords().addAll(0, 0);
        this.mesh.getFaces().addAll(LetterMesh.FACES);
    }

    public TriangleMesh getMesh() {
        return this.mesh;
    }

    private static float[] calculatePoints(float width, float height) {
        return new float[] {

               0, 1.7f*height, 0, //0
                50, 1.7f*height,0, //1
               50, 1.7f*height, 50, //2
                0, 1.7f*height, 50, //3

                0, 0, 0, //4
                50, 0,0, //5
                50, 0, 50, //6
                0, 0, 50, //7

                150, 0,0, //8
                150, 0, 50, //9

                50, 50,0, //10
                50, 50, 50, //11

                150, 50,0, //12
                150, 50, 50, //13

                50, 100,0, //14
                50, 100, 50, //15

                50, 150,0, //16
                50, 150, 50, //17

                100, 100,0, //18
                100, 100, 50, //19

                100, 150,0, //20
                100, 150, 50, //21

        };
    }

}
