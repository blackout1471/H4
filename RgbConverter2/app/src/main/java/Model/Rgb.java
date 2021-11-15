package Model;

public class Rgb {
    private char rValue;
    private char gValue;
    private char bValue;

    /*
    * Retrieve red value
     */
    public char getrValue() {
        return rValue;
    }

    /*
    * set red value
     */
    public void setrValue(char rValue) {
        this.rValue = rValue;
    }

    /*
    * get green value
     */
    public char getgValue() {
        return gValue;
    }

    /*
    * set green value
     */
    public void setgValue(char gValue) {
        this.gValue = gValue;
    }

    /*
    * get blue value
     */
    public char getbValue() {
        return bValue;
    }

    /*
    * set blue value
     */
    public void setbValue(char bValue) {
        this.bValue = bValue;
    }

    public Rgb(char r, char g, char b)
    {
        setrValue(r);
        setgValue(g);
        setbValue(b);
    }

    /*
    * Converts the rgb values to a single int
     */
    public int convertToInt()
    {
        int rgb = getrValue();
        rgb = (rgb << 8) + getgValue();
        rgb = (rgb << 8) + getbValue();
        return rgb;
    }
}
