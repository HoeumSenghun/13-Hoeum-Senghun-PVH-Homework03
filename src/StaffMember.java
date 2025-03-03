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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

//    public void setSalary(double salary) {
//
//    }

    public void setHoursWorked(double hoursWorked) {

    }

//    public Object getSalary() {
//
//        return null;
//    }

}