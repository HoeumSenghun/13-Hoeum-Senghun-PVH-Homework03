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
        displayMenu(); // Display menu

        // Sample data
        employees.add(new Volunteer(1, "Tina", "PP", 0));
        employees.add(new HourlySalaryEmployee(3, "Sokha", "BTB", 60, 10.0));
        employees.add(new Volunteer(4, "Lee", "SR", 0));
        employees.add(new HourlySalaryEmployee(6, "Ka", "PV", 50, 10.0));
        employees.add(new SalariedEmployee(5, "Fy", "KT", 300.0, 20.0));
        employees.add(new SalariedEmployee(2, "Dana", "KPS", 300.0, 10.0));

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
        Table table = new Table(1, BorderStyle.UNICODE_BOX, ShownBorders.SURROUND_HEADER_AND_COLUMNS);
        table.addCell("Employee Management System", new CellStyle(CellStyle.HorizontalAlign.center));

        // display menu
        table.setColumnWidth(0, 30, 35);
        table.addCell("1.Insert Employee");
        table.addCell("2.Display Employee");
        table.addCell("3.Update Employee");
        table.addCell("4.Remove Employee");
        table.addCell("5.Exit");

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
                break;
            }

            // id increase
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
                    System.out.print("Enter Salary: ");
                    double salary = scanner.nextDouble();
                    employees.add(new Volunteer(newId, name, address, salary));
                    System.out.println("Volunteer added successfully!");
                    break;
                case 2: // Salaried
                    System.out.print("Enter Salary: ");
                    salary = scanner.nextDouble();
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
        Table table = new Table(9, BorderStyle.UNICODE_BOX, ShownBorders.ALL);
        CellStyle style = new CellStyle(CellStyle.HorizontalAlign.center);

        table.setColumnWidth(0, 20, 30); // Type
        table.setColumnWidth(1, 5, 10);  // ID
        table.setColumnWidth(2, 15, 20); // Name
        table.setColumnWidth(3, 15, 20); // Address
        table.setColumnWidth(4, 10, 15); // Salary
        table.setColumnWidth(5, 10, 15); // Bonus
        table.setColumnWidth(6, 10, 15); // Hours
        table.setColumnWidth(7, 10, 15); // Rate
        table.setColumnWidth(8, 10, 15); // Pay

        table.addCell("Type", style);
        table.addCell("ID", style);
        table.addCell("Name", style);
        table.addCell("Address", style);
        table.addCell("Salary", style);
        table.addCell("Bonus", style);
        table.addCell("Hours", style);
        table.addCell("Rate", style);
        table.addCell("Pay", style);

        // Add employee rows
        for (StaffMember employee : employees) {
            String type = employee.getClass().getSimpleName();
            String salary = "---";
            String bonus = "---";
            String hours = "---";
            String rate = "---";
            double pay = employee.pay();

            if (employee instanceof SalariedEmployee) {
                SalariedEmployee salaried = (SalariedEmployee) employee;
                salary = String.format("$%.2f", salaried.getSalary());
                bonus = String.format("$%.2f", salaried.getBonus());
            } else if (employee instanceof HourlySalaryEmployee) {
                HourlySalaryEmployee hourly = (HourlySalaryEmployee) employee;
                hours = String.valueOf(hourly.getHoursWorked());
                rate = String.format("$%.2f", hourly.getRate());
            } else if (employee instanceof Volunteer) {
                salary = String.format("$%.2f", ((Volunteer) employee).getSalary());
            }

            table.addCell(type, style);
            table.addCell(String.valueOf(employee.id), style);
            table.addCell(employee.name, style);
            table.addCell(employee.address, style);
            table.addCell(salary, style);
            table.addCell(bonus, style);
            table.addCell(hours, style);
            table.addCell(rate, style);
            table.addCell(String.format("$%.2f", pay), style);
        }
        System.out.println(table.render());
    }

    private static void updateEmployee() {
        System.out.print("Enter Employee ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Optional<StaffMember> employeeOpt = employees.stream()
                .filter(e -> e.id == id)
                .findFirst();

        if (employeeOpt.isPresent()) {
            StaffMember employee = employeeOpt.get();

            boolean exit = false;
            while (!exit) {
                // display current employee details
                System.out.println("\nCurrent Employee Details:");
                displaySingleEmployee(employee);

                System.out.println("\nChoose a field to update:");
                System.out.println("1. Name");
                System.out.println("2. Address");

                if (employee instanceof Volunteer) {
                    System.out.println("3. Salary");
                } else if (employee instanceof SalariedEmployee) {
                    System.out.println("3. Salary");
                    System.out.println("4. Bonus");
                } else if (employee instanceof HourlySalaryEmployee) {
                    System.out.println("3. Hourly Rate");
                    System.out.println("4. Hours Worked");
                }

                System.out.println("5. Exit");
                System.out.print("Choose: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        System.out.print("Enter new Name: ");
                        employee.setName(scanner.nextLine());
                        break;
                    case 2:
                        System.out.print("Enter new Address: ");
                        employee.setAddress(scanner.nextLine());
                        break;
                    case 3:
                        if (employee instanceof Volunteer) {
                            System.out.print("Enter new Salary: ");
                            ((Volunteer) employee).setSalary(scanner.nextDouble());
                        } else if (employee instanceof SalariedEmployee) {
                            System.out.print("Enter new Salary: ");
                            ((SalariedEmployee) employee).setSalary(scanner.nextDouble());
                        } else if (employee instanceof HourlySalaryEmployee) {
                            System.out.print("Enter new Hourly Rate: ");
                            ((HourlySalaryEmployee) employee).setRate(scanner.nextDouble());
                        }
                        scanner.nextLine();
                        break;
                    case 4:
                        if (employee instanceof SalariedEmployee) {
                            System.out.print("Enter new Bonus: ");
                            ((SalariedEmployee) employee).setBonus(scanner.nextDouble());
                        } else if (employee instanceof HourlySalaryEmployee) {
                            System.out.print("Enter new Hours Worked: ");
                            ((HourlySalaryEmployee) employee).setHoursWorked(scanner.nextInt());
                        }
                        scanner.nextLine();
                        break;
                    case 5:
                        exit = true;
                        System.out.println("Exiting update menu.");
                        break;
                    default:
                        System.out.println("Invalid choice! Please try again.");
                }

                // Show update table after updated
                System.out.println("\nUpdated Employee Details:");
                displaySingleEmployee(employee);
            }
        } else {
            System.out.println("Employee not found!");
        }
    }

    private static void displaySingleEmployee(StaffMember employee) {
        Table table = new Table(9, BorderStyle.UNICODE_BOX, ShownBorders.ALL);
        CellStyle style = new CellStyle(CellStyle.HorizontalAlign.center);

        table.setColumnWidth(0, 20, 30);
        table.setColumnWidth(1, 5, 10);
        table.setColumnWidth(2, 15, 20);
        table.setColumnWidth(3, 15, 20);
        table.setColumnWidth(4, 10, 15);
        table.setColumnWidth(5, 10, 15);
        table.setColumnWidth(6, 10, 15);
        table.setColumnWidth(7, 10, 15);
        table.setColumnWidth(8, 10, 15);

        table.addCell("Type", style);
        table.addCell("ID", style);
        table.addCell("Name", style);
        table.addCell("Address", style);
        table.addCell("Salary", style);
        table.addCell("Bonus", style);
        table.addCell("Hours", style);
        table.addCell("Rate", style);
        table.addCell("Pay", style);

        String type = employee.getClass().getSimpleName();
        String salary = "---", bonus = "---", hours = "---", rate = "---";
        double pay = employee.pay();

        if (employee instanceof SalariedEmployee) {
            salary = String.format("$%.2f", ((SalariedEmployee) employee).getSalary());
            bonus = String.format("$%.2f", ((SalariedEmployee) employee).getBonus());
        } else if (employee instanceof HourlySalaryEmployee) {
            hours = String.valueOf(((HourlySalaryEmployee) employee).getHoursWorked());
            rate = String.format("$%.2f", ((HourlySalaryEmployee) employee).getRate());
        } else if (employee instanceof Volunteer) {
            salary = String.format("$%.2f", ((Volunteer) employee).getSalary());
        }

        table.addCell(type, style);
        table.addCell(String.valueOf(employee.id), style);
        table.addCell(employee.name, style);
        table.addCell(employee.address, style);
        table.addCell(salary, style);
        table.addCell(bonus, style);
        table.addCell(hours, style);
        table.addCell(rate, style);
        table.addCell(String.format("$%.2f", pay), style);

        System.out.println(table.render());
    }

    private static void removeEmployee() {
        System.out.print("Enter Employee ID to remove: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        employees.removeIf(e -> e.id == id);
        System.out.println("Employee removed!");
    }
}