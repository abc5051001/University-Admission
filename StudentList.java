import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class StudentList {
    ArrayList<Student> sList;

    public StudentList() {
        this.sList = new ArrayList<Student>(0); // 0 for default
    }

    public StudentList(String filename) {
        this.sList = new ArrayList<Student>(0);
        try {
            Scanner in = new Scanner(new File(filename));
            while (in.hasNextLine()) { // read the file and while there's something in there
                String[] line = in.nextLine().split(",", 0); // split the line by comma and store to line
                sList.add(new Student(Integer.parseInt(line[0]), line[1], Integer.parseInt(line[2]))); // first, third
                                                                                                       // items are
                                                                                                       // int but stored
                                                                                                       // as string so
                                                                                                       // do parseint
            }
            in.close();
        } catch (FileNotFoundException e) { // exception when filenotfound
            System.out.println("file not found");
        }
    }

    public StudentList copy() {
        StudentList copy = new StudentList();
        for (int i = 0; i < sList.size(); i++) { // copy all the things in studentlist
            copy.add(sList.get(i));
        }
        return (copy);
    }

    public int size() {
        return (sList.size());
    }

    public Student get(int index) {
        if (index >= sList.size()) {
            throw new RuntimeException();
        } // when giving index is not in the range
        return (sList.get(index)); // else we try to get the index
    }

    public void set(int index, Student value) {
        if (index >= sList.size()) {
            throw new RuntimeException();
        } // again when giving index is not in the range we throw this exception
        sList.set(index, value); // else we set the student to the index
    }

    public int indexOf(Student value) {
        for (int i = 0; i < sList.size(); i++) {
            if (sList.get(i).equals(value) == true) {
                return (i);
            }
        } // loop to find the student and return the index
        return (-1); // if not then return -1
    }

    public void add(Student value) {
        if (value == null) {
            throw new RuntimeException();
        } // when student value is not valid
        sList.add(value);// else we add
    }

    public void insert(int index, Student value) {
        if (value == null || index >= sList.size()) {
            throw new RuntimeException();
        } // same as the previous ones
        sList.add(index, value); // else we add to the place we want to
    }

    public void remove(Student value) {
        if (sList.indexOf(value) == -1) {
            throw new RuntimeException();
        } // same
        sList.remove(value); // now we remove the given thing
    }

    public Student getHighestScoreStudent() {
        int highest = 0;
        int index = 0;
        if (sList.isEmpty()) {
            throw new RuntimeException();
        } // when no student then the exception happens
        for (int i = 0; i < sList.size(); i++) {
            if (sList.get(i).getSAT() > highest) {
                highest = sList.get(i).getSAT();
                index = i;
            }
        } // or else we loop and get the index of the student
        return (sList.get(index));
    }

    @Override
    public String toString() {
        String x = "{";
        for (int i = 0; i < sList.size(); i++) {
            if (i + 1 == sList.size()) {
                x += sList.get(i).toString();
            } else {
                x += sList.get(i).toString() + ", ";
            }
        }
        x += "}"; // format to the given thing
        return (x);
    }
}
