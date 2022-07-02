package org.trufanoff.task;

public class Rectangle {
    private float x1;
    private float x2;
    private float y1;
    private float y2;

    public Rectangle(float x1, float x2, float y1, float y2) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
    }

    public Rectangle(float[] coordinates) {
        this(coordinates[0], coordinates[1], coordinates[2], coordinates[3]);
    }

    public float getX1() {
        return x1;
    }

    public float getX2() {
        return x2;
    }

    public float getY1() {
        return y1;
    }

    public float getY2() {
        return y2;
    }

    public float[] getBottomLeft() {
        float x = Math.min(x1, x2);
        float y = Math.min(y1, y2);
        return new float[]{x, y};
    }

    public float[] getTopLeft() {
        float x = Math.min(x1, x2);
        float y = Math.max(y1, y2);
        return new float[]{x, y};
    }

    public float[] getBottomRight() {
        float x = Math.max(x1, x2);
        float y = Math.min(y1, y2);
        return new float[]{x, y};
    }

    public float[] getTopRight() {
        float x = Math.max(x1, x2);
        float y = Math.max(y1, y2);
        return new float[]{x, y};
    }

    @Override
    public String toString() {
        return "Rect{" +
                "x1=" + x1 +
                ", x2=" + x2 +
                ", y1=" + y1 +
                ", y2=" + y2 +
                '}';
    }
}
