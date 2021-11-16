package Model;

public class Rgb implements RgbAble {
    private char rValue;
    private char gValue;
    private char bValue;

    /*
    * Retrieve red value
     */
    public char getRValue() {
        return rValue;
    }

    /*
    * set red value
     */
    public void setRValue(char rValue) {
        this.rValue = rValue;
    }

    /*
    * get green value
     */
    public char getGValue() {
        return gValue;
    }

    /*
    * set green value
     */
    public void setGValue(char gValue) {
        this.gValue = gValue;
    }

    /*
    * get blue value
     */
    public char getBValue() {
        return bValue;
    }

    /*
    * set blue value
     */
    public void setBValue(char bValue) {
        this.bValue = bValue;
    }

    public Rgb(char r, char g, char b)
    {
        setRValue(r);
        setGValue(g);
        setBValue(b);
    }

    /*
    * Converts the rgb values to a single int
     */
    public int convertToInt()
    {
        int rgb = getRValue();
        rgb = (rgb << 8) + getGValue();
        rgb = (rgb << 8) + getBValue();
        return rgb;
    }
}
