class SalariedEmployee extends StaffMember {
    // fields
    private double salary;
    private double bonus;

    // constructor
    public SalariedEmployee ( int id, String name, String address, double salary, double bonus){
        super(id, name, address);
        this.salary = salary;
        this.bonus = bonus;
    }

    // override to abstract method in superclass
    @Override
    public double pay() {
        return salary + bonus;
    }

    @Override
    public String toString() {
        return super.toString() + "\nEmployee Salary: " + salary + "\nBonus: " + bonus;
    }
}
