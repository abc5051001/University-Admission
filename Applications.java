import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Applications {
    private int[] iarray;
    private int[][] i2d;

    public Applications(String filename) {
        try {
            Scanner in = new Scanner(new File(filename));
            int rowCount = 0;
            while (in.hasNextLine()) {
                in.nextLine();
                rowCount++;
            } // counting how many rows the thing has so how many students it has
            in = new Scanner(new File(filename)); // reset
            i2d = new int[rowCount][]; // i2d first 1d row will have students storage
            iarray = new int[rowCount]; // iarray is gonna have storage equals to the amount of students
            int z = 0;
            while (in.hasNextLine()) {
                String[] line = in.nextLine().split(",", 0);
                iarray[z] = Integer.parseInt(line[0]); // putting students to iarray
                ArrayList<Integer> colleges = new ArrayList<Integer>();
                for (int i = 1; i < line.length; i++) {
                    colleges.add(Integer.parseInt(line[i]));
                } // getting the colleges selections from the student
                int[] collegeArray = new int[colleges.size()];
                for (int i = 0; i < colleges.size(); i++) {
                    collegeArray[i] = colleges.get(i);
                } // putting the colleges arraylist to array
                i2d[z] = collegeArray; // then the 2d array take the colleges selections
                z++; // go on to next student
            }
            in.close();
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
        }
    }

    public boolean inorno(int studentId) {
        boolean x = false;
        for (int i = 0; i < iarray.length; i++) {
            if (iarray[i] == studentId) {
                x = true;
            }
        }
        return (x);
    } // see if a student applied

    public int size() {
        return (iarray.length);
    }

    public int getStudentId(int index) {
        if (index < iarray.length) {
            return (iarray[index]);
        }
        return (-1);
    } // get the index or -1 if not in there

    public int[] getStudentSelections(int studentId) {
        int ind = 0;
        for (int i = 0; i < iarray.length; i++) {
            if (iarray[i] == studentId) {
                ind = i;
            }
        }
        return (i2d[ind]); // get all the colleges from 2d array from i2d
    }

    public int[] getStudentsByUniversity(int universityId) {
        ArrayList<Integer> row = new ArrayList<Integer>(0);
        for (int i = 0; i < i2d.length; i++) {
            for (int j = 0; j < i2d[i].length; j++) {
                if (i2d[i][j] == universityId) {
                    row.add(i);
                }
            }
        } // get all students from i2d 1d array to add to the row
        int[] ids = new int[row.size()];
        for (int i = 0; i < row.size(); i++) {
            ids[i] = iarray[row.get(i)];
        } // have id[i] to get all the students from row
        return (ids);
    }

    public int getUniversityByStudentsRank(int studentId, int rank) {
        boolean contain = false;
        int ind = 0;
        for (int i = 0; i < iarray.length; i++) {
            if (iarray[i] == studentId) {
                contain = true;
                ind = i;
            }
        } // get the student index and check if the student is in the list or no
        if (contain == false || rank > i2d[ind].length) {
            throw new RuntimeException();
        } else {
            return (i2d[ind][rank - 1]);
        } // then check if the student has the college in his list for the given rank
          // if he has the rank then return that college
    }

    @Override
    public String toString() {
        String x = "{";
        for (int i = 0; i < i2d.length; i++) {
            x += iarray[i] + ": ";
            for (int j = 0; j < i2d[i].length; j++) {
                if (j + 1 == i2d[i].length && i + 1 < i2d.length) {
                    x += i2d[i][j] + ", ";
                } else if (j + 1 == i2d[i].length && i + 1 == i2d.length) {
                    x += i2d[i][j];
                } else {
                    x += i2d[i][j] + " ";
                }
            }
        }
        x += "}";
        return (x);
    } // format as needed
}