public class Volunteer extends StaffMember {
    private double salary;

    public Volunteer(int id, String name, String address, double salary) {
        super(id, name, address);
        this.salary = salary; // Missing assignment fixed
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public double pay() {
        return salary; // Volunteers are not paid
    }

    @Override
    public String toString() {
        return "Volunteer - " + super.toString();
    }
}
