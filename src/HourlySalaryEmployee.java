class HourlySalaryEmployee extends StaffMember {
    // fields
    private int hourWorked;
    private double rate;

    // constructor
    public HourlySalaryEmployee (int id, String name, String address, int hourWorked, double rate){
        super(id, name, address);
        this.hourWorked = hourWorked;
        this.rate = rate;
    }
    public int getHourWorked(){
        return hourWorked;
    }
    public double getRate(){
        return rate;
    }
    public void setHourWorked(int hourWorked){
        this.hourWorked = hourWorked;
    }
    public void setRate(double rate){
        this.rate = rate;
    }

    // override abstract method in superclass
    @Override
    public double pay() {
        return hourWorked * rate;
    }

    @Override
    public String toString(){
        return super.toString() + "HourlyEmployee {hourWorked: " + hourWorked + ", rate: " + rate + "$}";
    }
}
