public class Volunteer extends StaffMember {
    public Volunteer(int id, String name, String address) {
        super(id, name, address);
    }

    @Override
    public double pay() {
        return 0.0; // Volunteers are not paid
    }

    @Override
    public String toString() {
        return "Volunteer - " + super.toString();
    }
}