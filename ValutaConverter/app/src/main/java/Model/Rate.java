package Model;

public class Rate {
    private String name;
    private double value;

    /**
    * getter for name of rate.
     **/
    public String getName() {
        return name;
    }

    /**
    * setter for name of rate.
     **/
    private void setName(String name) {
        this.name = name;
    }

    /**
    * getter for the value of the rate.
     **/
    public double getValue() {
        return value;
    }

    /**
    * setter for the value of the rate
     **/
    private void setValue(double value) {
        this.value = value;
    }

    public Rate(String _name, double _value)
    {
        name = _name;
        value = _value;
    }
}
