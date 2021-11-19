package com.example.tennisball;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Point;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventCallback;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Display;
import android.widget.FrameLayout;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

import Model.Vec;
import Presenter.BallPresenter;
import View.BallView;

public class MainActivity extends AppCompatActivity implements BallPresenter.View {

    BallPresenter presenter;

    SensorManager sm;
    List sensorList;
    BallView ballView;

    // Time
    Instant lastTime = Instant.now();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

        presenter = new BallPresenter(this, new Vec(100, 100, width - 150, height - 300));

        Vec startPosition = new Vec(width / 2, height / 2, 1.f);

        ballView = new BallView(this, startPosition.getX(), startPosition.getY(), 50);
        ((FrameLayout)findViewById(R.id.lin)).addView(ballView);
        presenter.onBallCreate(startPosition);

        sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorList = sm.getSensorList(Sensor.TYPE_ACCELEROMETER);
        if (sensorList.size() > 0);
            sm.registerListener(sensorEventCall, (Sensor) sensorList.get(0), SensorManager.SENSOR_DELAY_FASTEST);
    }

    SensorEventCallback sensorEventCall = new SensorEventCallback() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            super.onSensorChanged(event);

            // Calculate delta
            Duration delta = Duration.between(lastTime, Instant.now());
            lastTime = Instant.now();

            Vec vec = new Vec(-1 * event.values[0], event.values[1], event.values[2]);
            presenter.onBallUpdate(vec, (delta.toMillis() / 1000.f));
        }
    };

    @Override
    protected void onStop() {
        if (sensorList.size() > 0)
            sm.unregisterListener(sensorEventCall);

        super.onStop();
    }

    @Override
    public void ballMoved(Vec position) {
        ballView.x = position.getX();
        ballView.y = position.getY();
        ballView.invalidate();
    }
}