package com.algonquincollege.shee0058.hsvcolorpicker;

import android.app.DialogFragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;

import java.util.Observable;
import java.util.Observer;

import model.HSVModel;

/**
 *.Java
 * App changes a colorSwatch using HSV values
 *
 * @author Owen Sheehan (shee0058@algonquinlive.com)
 */


public class MainActivity extends AppCompatActivity implements Observer, SeekBar.OnSeekBarChangeListener {

    private static final String ABOUT_DIALOG_TAG = "About";

    private TextView mColorSwatch;
    private HSVModel mModel;
    private SeekBar mHueSB;
    private SeekBar mSaturationSB;
    private SeekBar mValueSB;
    private TextView mHueLabel;
    private TextView mSaturationLabel;
    private TextView mValueLabel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mModel = new HSVModel();
        mModel.setHue(HSVModel.MIN_HSV);
        mModel.setSaturation(HSVModel.MIN_HSV);
        mModel.setValue(HSVModel.MIN_HSV);

        mModel.addObserver(this);

        mColorSwatch = (TextView) findViewById(R.id.colorSwatch);
        mHueLabel = (TextView) findViewById(R.id.hue);
        mSaturationLabel = (TextView) findViewById(R.id.saturation);
        mValueLabel = (TextView) findViewById(R.id.value);

        mHueSB = (SeekBar) findViewById(R.id.hSB);
        mSaturationSB = (SeekBar) findViewById(R.id.sSB);
        mValueSB = (SeekBar) findViewById(R.id.vSB);

        mHueSB.setMax(HSVModel.MAX_HUE);
        mSaturationSB.setMax(HSVModel.MAX_SATURATION);
        mValueSB.setMax(HSVModel.MAX_VALUE);

        mHueSB.setOnSeekBarChangeListener(this);
        mSaturationSB.setOnSeekBarChangeListener(this);
        mValueSB.setOnSeekBarChangeListener(this);

        mColorSwatch.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                Toast.makeText(getApplicationContext(), "HSV: " + mHueSB.getProgress() + "\u00B0/" + mSaturationSB.getProgress() + "%/" + mValueSB.getProgress() + "%", Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        this.updateView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_about) {
            DialogFragment newFragment = new AboutDialogFragment();
            newFragment.show(getFragmentManager(), ABOUT_DIALOG_TAG);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        if (!fromUser) {
            return;
        }

        switch (seekBar.getId()) {
            case R.id.hSB:
                mModel.setHue(mHueSB.getProgress());
                mHueLabel.setText(getResources().getString(R.string.hueProgress, progress).toUpperCase());
                break;
            case R.id.sSB:
                mModel.setSaturation(mSaturationSB.getProgress());
                mSaturationLabel.setText(getResources().getString(R.string.saturationProgress, progress).toUpperCase());
                break;
            case R.id.vSB:
                mModel.setValue(mValueSB.getProgress());
                mValueLabel.setText(getResources().getString(R.string.valueProgress, progress).toUpperCase());
                break;
        }
    }


    public void onButtonTap(View item) {
        switch (item.getId()) {
            case R.id.buttonBlack:
                mModel.asBlack();
                break;
            case R.id.buttonBlue:
                mModel.asBlue();
                break;
            case R.id.buttonCyan:
                mModel.asCyan();
                break;
            case R.id.buttonGray:
                mModel.asGray();
                break;
            case R.id.buttonGreen:
                mModel.asGreen();
                break;
            case R.id.buttonLime:
                mModel.asLime();
                break;
            case R.id.buttonMagenta:
                mModel.asMagenta();
                break;
            case R.id.buttonMaroon:
                mModel.asMaroon();
                break;
            case R.id.buttonNavy:
                mModel.asNavy();
                break;
            case R.id.buttonOlive:
                mModel.asOlive();
                break;
            case R.id.buttonPurple:
                mModel.asPurple();
                break;
            case R.id.buttonRed:
                mModel.asRed();
                break;
            case R.id.buttonSilver:
                mModel.asSilver();
                break;
            case R.id.buttonTeal:
                mModel.asTeal();
                break;
            case R.id.buttonYellow:
                mModel.asYellow();
                break;

        }
        Toast.makeText(getApplicationContext(), "HSV: " + mHueSB.getProgress() + "\u00B0/" + mSaturationSB.getProgress() + "%/" + mValueSB.getProgress() + "%", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        switch (seekBar.getId()) {
            case R.id.hSB:
                mHueLabel.setText(getResources().getString(R.string.hue));
                break;
            case R.id.sSB:
                mSaturationLabel.setText(getResources().getString(R.string.saturation));
                break;
            case R.id.vSB:
                mValueLabel.setText(getResources().getString(R.string.value));
                break;
        }
    }

    @Override
    public void update(Observable observable, Object data) {
        this.updateView();
    }

    private void updateColorSwatch() {
        int newColor = Color.HSVToColor(new float[]
                {
                        mModel.getHue(),
                        mModel.getSaturation() / 100,
                        mModel.getValue() / 100
                }
        );
        mHueSB.setProgress(updateHueSB());
        mSaturationSB.setProgress(updateSaturationSB());
        mValueSB.setProgress(updateValueSB());
        mColorSwatch.setBackgroundColor(newColor);
    }

    private int updateHueSB() {
        int intHue = (int) mModel.getHue();
        return intHue;
    }

    private int updateSaturationSB() {
        int intSaturation = (int) mModel.getSaturation();
        return intSaturation;
    }

    private int updateValueSB() {
        int intValue = (int) mModel.getValue();
        return intValue;
    }

    public void updateView() {
        this.updateColorSwatch();
        this.updateHueSB();
        this.updateSaturationSB();
        this.updateValueSB();
    }

}
