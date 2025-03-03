public class HourlySalaryEmployee extends StaffMember {
    private int hoursWorked;
    private double rate;

    public HourlySalaryEmployee(int id, String name, String address, int hoursWorked, double rate) {
        super(id, name, address);
        this.hoursWorked = hoursWorked;
        this.rate = rate;
    }

    public int getHoursWorked() {
        return this.hoursWorked;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double hourlyRate) {
        this.rate = hourlyRate;
    }

    public void setHoursWorked(int hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    @Override
    public double pay() {
        return hoursWorked * rate;
    }

    @Override
    public String toString() {
        return "Hourly Employee - " + super.toString() + ", Hours Worked: " + hoursWorked + ", Rate: " + rate;
    }
}
