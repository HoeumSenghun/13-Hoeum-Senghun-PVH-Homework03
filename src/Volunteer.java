class Volunteer extends StaffMember {
    // field
    protected double salary = 0; // Volunteer ot ban luy te

    // constructor
    public Volunteer(int id, String name, String address) {
        super(id, name, address);
    }

    // override to abstract method in superclass
    @Override
    public double pay() {
        return salary; // call parent class method : no money
    }

    @Override
    public String toString() {
        return super.toString() + "Volunteer{" + " salary: " + salary + "$}";
    }
}
