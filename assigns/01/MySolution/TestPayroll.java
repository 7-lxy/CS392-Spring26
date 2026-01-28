
public class TestPayroll {
    
    public static void main(String[] args) {
        /*
        int size()
        void print()
        void add_employee(Employee newbie)
        void remove_employee(int i)
        int find_employee(String target_name)
        void add_payroll(Payroll source)
        void copy_payroll(Payroll source)
        */

        Payroll p1 = new Payroll();
        Payroll p2 = new Payroll();

        Employee e1 = new Employee("Alice", 30, 50000);
        Employee e2 = new Employee("Bob", 35, 60000);
        Employee e3 = new Employee("Charlie", 28, 55000);
        Employee e4 = new Employee("Diana", 40, 70000);

        // add_employee() tests
        p1.add_employee(e1);
        p1.add_employee(e2);
        p1.add_employee(e3);

        p2.add_employee(e4);

        System.out.println("p1 size: " + p1.size()); // Expected: 3
        System.out.println("p2 size: " + p2.size()); // Expected: 1
        p1.print(); // Expected: Alice, Bob, Charlie
        p2.print(); // Expected: Diana

        System.out.println();
        
        // remove_employee() tests
        try {
            p1.remove_employee(0); // Removes Alice
            p2.remove_employee(0); // Removes Diana
            p2.remove_employee(0); // Expected: EmployeeIndexException
        }
        catch (EmployeeIndexException e) {
            System.out.println("EmployeeIndexException");
        }
        
        System.out.println();

        System.out.println("p1 size after removal: " + p1.size()); // Expected: 2
        System.out.println("p2 size after removal: " + p2.size()); // Expected: 0
        p1.print(); // Expected: Bob, Charlie
        p2.print(); // Expected: 

        System.out.println();

        // find_employee() tests
        try {
            System.out.println(p1.find_employee("Bob")); // Expected: 0
            System.out.println(p1.find_employee("Alice")); // Expected: EmployeeNotFoundException
        }
        catch (EmployeeNotFoundException e) {
            System.out.println("EmployeeNotFoundException");
        }
        
        System.out.println();

        // add_payroll() tests
        Payroll p3 = new Payroll();
        p3.add_employee(new Employee("Eve", 32, 62000));
        p3.add_employee(new Employee("Frank", 29, 58000));
        p1.add_payroll(p3); // p1 now has Bob, Charlie, Eve, Frank

        // copy_payroll() tests
        p2.copy_payroll(p1); // p2 now has Bob, Charlie, Eve, Frank
        try {
            p2.remove_employee(0);
        }
        catch (EmployeeIndexException e) {
            System.out.println("EmployeeIndexException");
        }
        System.out.println("p1 size after add_payroll: " + p1.size()); // Expected: 4
        System.out.println("p2 size after copy_payroll and removal: " + p2.size()); // Expected: 3
        p1.print(); // Expected: Bob, Charlie, Eve, Frank
        System.out.println();
        p2.print(); // Expected: Bob, Eve, Frank
        System.out.println();

        // resize test
        Payroll p4 = new Payroll();
        for (int i = 0; i < 15; i++) {
            p4.add_employee(new Employee("Emp" + i, 20, 40000));
        }
        System.out.println();
        System.out.println("p4 size after adding 15 employees: " + p4.size()); // Expected: 15
        p4.print(); // Expected: Emp0 to Emp14
    }
}
