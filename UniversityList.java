import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class UniversityList {
    ArrayList<University> ulist;

    public UniversityList() {
        this.ulist = new ArrayList<University>(0);
    }

    public int size() {
        return (ulist.size());
    }

    public University get(int index) {
        if (index >= ulist.size()) {
            throw new RuntimeException();
        }
        return (ulist.get(index));
    }// same as studentlist so far

    public UniversityList(String filename) {
        this.ulist = new ArrayList<University>(0);
        try {
            Scanner in = new Scanner(new File(filename));
            while (in.hasNextLine()) {
                String[] line = in.nextLine().split(",", 0); // when there's still something in there we split by comma
                                                             // and put to line
                this.insert(new University(Integer.parseInt(line[0]), line[1], Integer.parseInt(line[2]),
                        Integer.parseInt(line[3]))); // samething as studentlist but now we call insert so they are in
                                                     // order
            }
            in.close();
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
        }
    }

    public void set(int index, University value) {
        if (index >= ulist.size()) {
            throw new RuntimeException();
        }
        ulist.set(index, value); // setter
    }

    public int indexOf(University value) {
        for (int i = 0; i < ulist.size(); i++) {
            if (ulist.get(i).equals(value) == true) {
                return (i);
            }
        } // same thing to get index
        return (-1);
    }

    public int indexOf(int universityId) {
        for (int i = 0; i < ulist.size(); i++) {
            if (ulist.get(i).getId() == (universityId)) {
                return (i);
            }
        } // this is to get index id now
        return (-1);
    }

    public void insert(University value) {
        if (value == null) {
            throw new RuntimeException();
        } // when given unviersity is invalid
        if (ulist.size() == 0) {
            ulist.add(value); // when nothing is in there we add first
        } else {
            for (int i = 0; i < ulist.size(); i++) {
                if (ulist.get(i).getName().compareTo(value.getName()) > 0) {
                    ulist.add(i, value);
                    break;
                } // then we loop to figure out where the given university is supposed to be
                if (i + 1 == ulist.size()) {
                    ulist.add(value);
                    return; // if its not in front of the any university in the list then its at the end
                }
            }
        }
    }

    @Override
    public String toString() {
        String x = "{";
        for (int i = 0; i < ulist.size(); i++) {
            if (i + 1 == ulist.size()) {
                x += ulist.get(i).toString();
            } else {
                x += ulist.get(i).toString() + ", ";
            }
        }
        x += "}";
        return (x); // string format overriding tostring
    }
}