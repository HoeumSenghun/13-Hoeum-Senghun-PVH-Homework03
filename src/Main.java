import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    private static List<StaffMember> employees = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        displayMenu(); // Display the menu table

        // Predefined data
        employees.add(new Volunteer(1, "Tina", "PP"));
        employees.add(new HourlySalaryEmployee(3, "Sokha", "BTB", 60, 10.0));
        employees.add(new SalariedEmployee(7, "Nang", "KP", 900.0, 10.0));

        while (true) {
            System.out.print("\nChoose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    insertEmployee();
                    break;
                case 2:
                    displayEmployees();
                    break;
                case 3:
                    updateEmployee();
                    break;
                case 4:
                    removeEmployee();
                    break;
                case 5:
                    System.exit(0);
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    private static void displayMenu() {
        // Create a table with borders
        Table table = new Table(1, BorderStyle.UNICODE_BOX, ShownBorders.ALL);
        CellStyle centerStyle = new CellStyle(CellStyle.HorizontalAlign.center);

        // Add header
        table.addCell("EMS", new CellStyle(CellStyle.HorizontalAlign.center));

        // Add menu items
        table.addCell("1.Insert Employee");
        table.addCell("2.Display Employee");
        table.addCell("3.Update Employee");
        table.addCell("4.Remove Employee");
        table.addCell("5.Exit");

        // Render the table
        System.out.println(table.render());
    }

    private static void insertEmployee() {
        while (true) {
            System.out.println("\n1. Volunteer");
            System.out.println("2. Salaried");
            System.out.println("3. Hourly");
            System.out.println("4. Back");
            System.out.print("Choose an option: ");
            int type = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (type == 4) {
                break; // Go back to the main menu
            }

            // Auto-generate ID
            int newId = employees.isEmpty() ? 1 : employees.stream()
                    .mapToInt(e -> e.id)
                    .max()
                    .orElse(0) + 1;

            System.out.print("Enter Name: ");
            String name = scanner.nextLine();
            System.out.print("Enter Address: ");
            String address = scanner.nextLine();

            switch (type) {
                case 1: // Volunteer
                    employees.add(new Volunteer(newId, name, address));
                    System.out.println("Volunteer added successfully!");
                    break;
                case 2: // Salaried
                    System.out.print("Enter Salary: ");
                    double salary = scanner.nextDouble();
                    System.out.print("Enter Bonus: ");
                    double bonus = scanner.nextDouble();
                    employees.add(new SalariedEmployee(newId, name, address, salary, bonus));
                    System.out.println("Salaried Employee added successfully!");
                    break;
                case 3: // Hourly
                    System.out.print("Enter Hours Worked: ");
                    int hours = scanner.nextInt();
                    System.out.print("Enter Rate: ");
                    double rate = scanner.nextDouble();
                    employees.add(new HourlySalaryEmployee(newId, name, address, hours, rate));
                    System.out.println("Hourly Employee added successfully!");
                    break;
                default:
                    System.out.println("Invalid option!");
            }
        }
    }

    private static void displayEmployees() {
        // Create a table with borders
        Table table = new Table(9, BorderStyle.UNICODE_BOX, ShownBorders.ALL);

        // Add header
        table.addCell("Type", new CellStyle(CellStyle.HorizontalAlign.center));
        table.addCell("ID", new CellStyle(CellStyle.HorizontalAlign.center));
        table.addCell("Name", new CellStyle(CellStyle.HorizontalAlign.center));
        table.addCell("Address", new CellStyle(CellStyle.HorizontalAlign.center));
        table.addCell("Salary", new CellStyle(CellStyle.HorizontalAlign.center));
        table.addCell("Bonus", new CellStyle(CellStyle.HorizontalAlign.center));
        table.addCell("Hour", new CellStyle(CellStyle.HorizontalAlign.center));
        table.addCell("Rate", new CellStyle(CellStyle.HorizontalAlign.center));
        table.addCell("Pay", new CellStyle(CellStyle.HorizontalAlign.center));

        // Add employee rows
        for (StaffMember employee : employees) {
            String type = employee.getClass().getSimpleName(); // Get employee type (e.g., Volunteer, SalariedEmployee)
            String salary = "---";
            String bonus = "---";
            String hour = "---";
            String rate = "---";
            double pay = employee.pay(); // Calculate payment using the pay() method

            // Add details based on employee type
            if (employee instanceof SalariedEmployee) {
                SalariedEmployee salaried = (SalariedEmployee) employee;
                salary = String.format("$%.2f", salaried.getSalary());
                bonus = String.format("$%.2f", salaried.getBonus());
            } else if (employee instanceof HourlySalaryEmployee) {
                HourlySalaryEmployee hourly = (HourlySalaryEmployee) employee;
                hour = String.valueOf(hourly.getHoursWorked());
                rate = String.format("$%.2f", hourly.getRate());
            } else if (employee instanceof Volunteer) {
                salary = "---";
                bonus = "---";
            }

            // Add row to the table
            table.addCell(type);
            table.addCell(String.valueOf(employee.id));
            table.addCell(employee.name);
            table.addCell(employee.address);
            table.addCell(salary);
            table.addCell(bonus);
            table.addCell(hour);
            table.addCell(rate);
            table.addCell(String.format("$%.2f", pay));
        }

        // Render the table
        System.out.println(table.render());
    }

    private static void updateEmployee() {
        System.out.print("Enter Employee ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Optional<StaffMember> employeeOpt = employees.stream()
                .filter(e -> e.id == id)
                .findFirst();

        if (employeeOpt.isPresent()) {
            StaffMember employee = employeeOpt.get();
            System.out.print("Enter new Name: ");
            String name = scanner.nextLine();
            System.out.print("Enter new Address: ");
            String address = scanner.nextLine();
            employee.name = name;
            employee.address = address;
            System.out.println("Employee updated!");
        } else {
            System.out.println("Employee not found!");
        }
    }

    private static void removeEmployee() {
        System.out.print("Enter Employee ID to remove: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        employees.removeIf(e -> e.id == id); // Java 8 removeIf
        System.out.println("Employee removed!");
    }
}