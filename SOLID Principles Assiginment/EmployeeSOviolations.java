// now the employee class has only one function to do, which is to hold the employee details
class Employee {
    private String name;
    private double salary;

    Employee(String n, double s) {
        name = n;
        salary = s;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }
}

// According to the SRP, separate class for printing payslip
// so that each class only has one function to do
class PrintingPlaySlip {
    // function for printing payslip
    public void printPS(Employee employee) {
        System.out.println("employee: " + employee.getName() + ", salary: $" + employee.getSalary());
    }
}

// According to the OCP, created an interface for storing employee
// so that we can have multiple storage methods
// new storage methods can be added without modifying existing classes
// using this interface
interface StoringEmployee {
    void store(Employee employee);
}

// class for saving to a database which impliments the above interface
class intoDatabase implements StoringEmployee {
    public void store(Employee employee) {
        // some code to save employee to a database
        System.out.println("saving employee to database...");
    }
}

// class for saving to a file system
class intoFileSystem implements StoringEmployee {
    public void store(Employee employee) {
        // some code to save employee to a file into the system
        System.out.println("saving employee to a file in the system...");
    }
}

public class EmployeeSOviolations {
    public static void main(String[] args) {
        Employee test = new Employee("Karthikeya Bh", 100000);

        // creating a class for printing payslip
        PrintingPlaySlip playSlipPrinter = new PrintingPlaySlip();
        // using the print function of the class to print the payslip
        playSlipPrinter.printPS(test);

        // creating a class for storing employee into database
        StoringEmployee storage = new intoDatabase();
        storage.store(test);

        // creating a class for storing employee into file system
        StoringEmployee intoFileSystem = new intoFileSystem();
        intoFileSystem.store(test);
    }
}