
public class Payroll {
    public static final int INITIAL_MAXIMUM_SIZE = 1024;

    public Payroll() {
	    people = new Employee[INITIAL_MAXIMUM_SIZE];
        maximum_size = INITIAL_MAXIMUM_SIZE;
        current_size = 0;
    }
    
    public void add_employee(Employee newbie) {
	    if (current_size == maximum_size) {
            maximum_size *= 2;
            Employee[] new_people = new Employee[maximum_size];
            System.arraycopy(people, 0, new_people, 0, current_size);
            people = new_people;
        }
        people[current_size] = newbie;
        current_size += 1;
    }

    public void remove_employee(int i) throws EmployeeIndexException {
	    if (i < 0 || i >= current_size || people[i] == null) {
            throw new EmployeeIndexException();
        }
        people[i] = people[current_size - 1];
        people[current_size - 1] = null;
        current_size -= 1;
    }
    
    public int find_employee(String name) throws EmployeeNotFoundException {
	    for (int i = 0; i < current_size; i++) {
            if (people[i].name.equals(name)) {
                return i;
            }
        }
        throw new EmployeeNotFoundException();
    }

    public void add_payroll(Payroll source) {
        while (this.current_size + source.current_size > this.maximum_size) {
            this.maximum_size *= 2;
        }
        if (this.people.length < this.maximum_size) {
            Employee[] new_people = new Employee[this.maximum_size];
            System.arraycopy(this.people, 0, new_people, 0, this.current_size);
            this.people = new_people;
        }
        for (int i = 0; i < source.current_size; i++) {
            this.add_employee(source.people[i]);
        }
    }

    public void copy_payroll(Payroll source) {
	    this.current_size = source.current_size;
        this.maximum_size = source.maximum_size;
        this.people = new Employee[this.maximum_size];
        System.arraycopy(source.people, 0, this.people, 0, this.current_size);
    }

    public int size() {
        return current_size;
    }

    public void print() {
        for (int i = 0; i < current_size; i++) {
            if (people[i] != null) {
                System.out.println(people[i].name);
            }
        }
    }

    private Employee people[];    
    private int maximum_size;
    private int current_size;
}
