package Views;

import android.content.Context;
import android.graphics.Color;

public class PieceView extends androidx.appcompat.widget.AppCompatButton {

    /**
     * Retrieve x position from the piece view
     * @return
     */
    public int getxPos() {
        return xPos;
    }

    /**
     * Set x pos for the piece view
     * @param xPos
     */
    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    /**
     * Get the y pos from the piece view
     * @return
     */
    public int getyPos() {
        return yPos;
    }

    /**
     * Set the y pos in the piece view.
     * @param yPos
     */
    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    private int xPos;
    private int yPos;

    public PieceView(Context context, int _xPos, int _yPos) {
        super(context);
        setxPos(_xPos);
        setyPos(_yPos);

        setBackgroundToDefault();
    }

    /**
     * Set the background to the default color
     */
    public void setBackgroundToDefault() {
        setBackgroundColor(Color.BLUE);
    }

    /**
     * Set the background to a red invalid color
     */
    public void setBackgroundToInvalid() {
        setBackgroundColor(Color.RED);
    }

    /**
     * Set background to valid color: green
     */
    public void setBackgroundToValid() {
        setBackgroundColor(Color.GREEN);
    }
}
