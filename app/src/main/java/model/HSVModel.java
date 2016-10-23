package model;


import android.widget.SeekBar;

import java.util.Observable;

/**
 * @author Owen Sheehan (shee0058@algonquinlive.com)
 */

public class HSVModel extends Observable {

    private float hue;
    private float saturation;
    private float value;

    public static final int MAX_HUE = 360;
    public static final int MAX_SATURATION = 100;
    public static final int MAX_VALUE = 100;
    public static final int MIN_HSV = 0;

    public HSVModel() {
        this.hue = MAX_HUE;
        this.saturation = MAX_SATURATION;
        this.value = MAX_VALUE;
    }

    public void asBlack() {
        this.setHSV(MIN_HSV, MIN_HSV, MIN_HSV);
    }

    public void asRed() {
        this.setHSV(MIN_HSV, MAX_SATURATION, MAX_VALUE);
    }

    public void asLime() {
        this.setHSV(120f, MAX_SATURATION, MAX_VALUE);
    }

    public void asBlue() {
        this.setHSV(240f, MAX_SATURATION, MAX_VALUE);
    }

    public void asYellow() {
        this.setHSV(60f, MAX_SATURATION, MAX_VALUE);
    }

    public void asCyan() {
        this.setHSV(180f, MAX_SATURATION, MAX_VALUE);
    }

    public void asMagenta() {
        this.setHSV(300f, MAX_SATURATION, MAX_VALUE);
    }

    public void asSilver() {
        this.setHSV(MIN_HSV, MIN_HSV, 75f);
    }

    public void asGray() {
        this.setHSV(MIN_HSV, MIN_HSV, 50f);
    }

    public void asMaroon() {
        this.setHSV(MIN_HSV, MAX_SATURATION, 50f);
    }

    public void asOlive() {
        this.setHSV(60f, MAX_SATURATION, 50f);
    }

    public void asGreen() {
        this.setHSV(120f, MAX_SATURATION, 50f);
    }

    public void asPurple() {
        this.setHSV(300f, MAX_SATURATION, 50f);
    }

    public void asTeal() {
        this.setHSV(180f, MAX_SATURATION, 50f);
    }

    public void asNavy() {
        this.setHSV(240f, MAX_SATURATION, 50f);
    }


    //GETTERS

    public float getHue() {
        return hue;
    }

    public float getSaturation() {
        return saturation;
    }

    public float getValue() {
        return value;
    }

    //SETTERS

    public void setHue(float hue) {
        this.hue = hue;
        this.updateObservers();
    }

    public void setSaturation(float saturation) {
        this.saturation = saturation;
        this.updateObservers();
    }

    public void setValue(float value) {
        this.value = value;
        this.updateObservers();
    }

    public void setHSV(float hue, float saturation, float value) {
        this.hue = hue;
        this.saturation = saturation;
        this.value = value;

        this.updateObservers();
    }

    // the model has changed!
    // broadcast the update method to all registered observers
    private void updateObservers() {
        this.setChanged();
        this.notifyObservers();
    }


    @Override
    public String toString() {
        return "HSV [h=" + hue + "s=" + saturation + "v=" + value + "]";
    }
}
