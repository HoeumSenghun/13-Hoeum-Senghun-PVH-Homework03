abstract class StaffMember {
    // fields
    protected int id;
    protected String name;
    protected String address;

    // constructor
    public StaffMember (int id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    // abstract method
    public abstract double pay();

    // method toString
    @Override
    public String toString() {
        return "StaffMember {id: " + id + ", name: " + name + ", address: " + address + "}";
    }
}
