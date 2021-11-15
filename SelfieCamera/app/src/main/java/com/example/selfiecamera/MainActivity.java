package com.example.selfiecamera;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    Bitmap currentBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(resultCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK)
        {
            Bundle extra = data.getExtras();
            currentBitmap = (Bitmap) extra.get("data");
            imageView.setImageBitmap(currentBitmap);
        }
    }

    /*
    * Click event handler for when clicking on take picture button
     */
    public void OnTakePictureClicked(View view)
    {
        DispatchCameraIntent();
    }

    /*
    * Click event handler, for when clicking on black filter button
     */
    public void OnBlackFilterClicked(View view)
    {
        Bitmap blackBitmap = grayScaleFilter(currentBitmap);
        imageView.setImageBitmap(blackBitmap);
    }

    /*
    * Transforms our colored bitmap to an grayscale filter
     */
    private Bitmap grayScaleFilter(Bitmap bitmapToFilter)
    {
        Bitmap grayBit = Bitmap.createBitmap(bitmapToFilter.getWidth(), bitmapToFilter.getHeight(), Bitmap.Config.RGB_565);

        Canvas can = new Canvas(grayBit);
        Paint paint = new Paint();
        float of = 1/3f;
        float[] matrix = new float[] {
                of, of, of, 0, 0,
                of, of, of, 0, 0,
                of, of, of, 0, 0,
                0, 0, 0, 1, 0
        };

        ColorMatrixColorFilter cmcf = new ColorMatrixColorFilter(matrix);
        paint.setColorFilter(cmcf);
        can.drawBitmap(bitmapToFilter, 0, 0, paint);

        return grayBit;
    }

    /*
    * Dispatches the camera intent.
     */
    private void DispatchCameraIntent()
    {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        try {
            startActivityForResult(intent, 1);
        }
        catch (ActivityNotFoundException exc)
        {
            Log.e("camera", "Could not start camera intent");
        }
    }
}