public class Student {
    private int id;
    private String name;
    private int SAT;

    public Student(int i, String n, int s) {
        this.id = i; // contructor
        this.name = n;
        this.SAT = s;
    }

    public int getId() {
        return (this.id); // getter for ID
    }

    public String getName() {
        return (this.name); // getter for Name
    }

    public int getSAT() {
        return (this.SAT); // getter for SAT
    }

    @Override
    public String toString() {
        String x;
        x = String.format("%d: %s", id, name);
        return (x); // overriding toString method with the format
    }

    public Student copy() {
        Student copy = new Student(this.id, this.name, this.SAT);
        return (copy); // copy the object of Student
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Student)) {
            return false; // if the given object isn't student then it's false
        }
        Student temp = (Student) other;
        if (temp.getId() == this.id && temp.getName().equals(this.name) && temp.getSAT() == this.SAT) {
            return (true); // compare all the parameters
        }
        return (false);
    }

}