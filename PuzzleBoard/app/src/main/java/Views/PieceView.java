package Views;

import android.content.Context;

public class PieceView extends androidx.appcompat.widget.AppCompatButton {

    public int getxPos() {
        return xPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    private int xPos;
    private int yPos;

    public PieceView(Context context, int _xPos, int _yPos) {
        super(context);
        setxPos(_xPos);
        setyPos(_yPos);
    }
}
