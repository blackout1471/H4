package View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

/**
 * Custom view for the ball, to control the drawing.
 */
public class BallView extends View {

    public float x;
    public float y;
    private final int r;
    private final Paint mPaint;

    public BallView(Context context, float x, float y, int r) {
        super(context);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setTextSize(12);
        this.x = x;
        this.y = y;
        this.r = r;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(x, y, r, mPaint);
    }
}
