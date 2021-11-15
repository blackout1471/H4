package Model;

public class Rgb {
    private char rValue;
    private char gValue;
    private char bValue;

    public char getrValue() {
        return rValue;
    }

    public void setrValue(char rValue) {
        this.rValue = rValue;
    }

    public char getgValue() {
        return gValue;
    }

    public void setgValue(char gValue) {
        this.gValue = gValue;
    }

    public char getbValue() {
        return bValue;
    }

    public void setbValue(char bValue) {
        this.bValue = bValue;
    }

    public Rgb(char r, char g, char b)
    {
        setrValue(r);
        setgValue(g);
        setbValue(b);
    }

    public int convertToInt()
    {
        int rgb = getrValue();
        rgb = (rgb << 8) + getgValue();
        rgb = (rgb << 8) + getbValue();
        return rgb;
    }


}
