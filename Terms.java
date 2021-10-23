public class Terms {
    public double coeff;
    public int power;

    public Terms(double coeff, int power){
        this.coeff = coeff;
        this.power = power;
    }

    public double getCoeff() {
        return this.coeff;
    }

    public int getPower() {
        return this.power;
    }

    public void setCoeff(double coeff) {
        this.coeff = coeff;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public String toString() {
        return coeff + "x" + "^" + power;
    }

    //Method to compare the power terms with another term that a user choses
    public int compareTo(Terms other) {
        //if the power is more than other than return the power if the method returns -1 the other has a greater power
        if(this.power < other.power)
            return this.power;
        else
            return -1;
    }
}