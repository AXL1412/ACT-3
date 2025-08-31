package com.example.myapplication;

import android.os.Bundle;
import android.view.ScaleGestureDetector;
import android.view.MotionEvent;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private float scaleFactor = 1.0f;
    private ScaleGestureDetector scaleGestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        imageView = findViewById(R.id.imageView);

        scaleGestureDetector = new ScaleGestureDetector(this, new ScaleListener());

        imageView.setOnTouchListener((v, event) -> {
            scaleGestureDetector.onTouchEvent(event);
            return true;
        });
    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            float scale = detector.getScaleFactor();
            scaleFactor *= scale;
            scaleFactor = Math.max(0.1f, Math.min(scaleFactor, 5.0f)); // lÃ­mites de zoom

            imageView.setScaleX(scaleFactor);
            imageView.setScaleY(scaleFactor);
            return true;
        }
    }
}
