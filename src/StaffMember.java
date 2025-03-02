public abstract class StaffMember {
    protected int id;
    protected String name;
    protected String address;

    public StaffMember(int id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public abstract double pay();

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Address: " + address;
    }
}