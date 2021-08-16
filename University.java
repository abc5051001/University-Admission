public class University {
    private int id;
    private String name;
    private int numOfPositions;
    private int minSAT;
    private StudentList slist;

    public University(int i, String n, int nop, int m) {
        this.id = i;
        this.name = n;
        this.numOfPositions = nop;
        this.minSAT = m;
        this.slist = new StudentList();
    } // constructor to set the values

    public int getId() {
        return (id);
    }

    public String getName() {
        return (name);
    }

    public int getNumOfPositions() {
        return (numOfPositions);
    }

    public int getMinSAT() {
        return (minSAT);
    } // getters

    public void activateMinSAT(boolean value) {
        if (!value) {
            minSAT = -1 * (Math.abs(minSAT));
        }
        if (value) {
            minSAT = Math.abs(minSAT);
        }
    } // when activated, we need to check the minsat required else we set to negative
      // so any value of SAT score will be higher than min

    public boolean admit(Student value) {
        if (value.getSAT() >= this.getMinSAT() && slist.size() < this.getNumOfPositions()) {
            slist.add(value);
            return (true);
        } // if the student's SAT is higher than the minSAT and positions are not full
          // then we add
        return (false);

    }

    public StudentList getAdmittedStudents() {
        return (slist);
    }

    public int lastAdmittedStudentSAT() {
        int last = 0;
        if (slist.size() != 0) {
            last = slist.get(0).getSAT();
            for (int i = 0; i < slist.size(); i++) {
                if (slist.get(i).getSAT() < last) {
                    last = slist.get(i).getSAT();
                }
            } // check in the admitted students list and see the lowest SAT
        }
        return (last);
    }

    public void resetAdmissions() {
        slist = new StudentList();
    }

    @Override
    public String toString() {
        String x;
        x = String.format("%d: %s", id, name);
        return (x);
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof University)) {
            return false;
        } // not university then its wrong
        University temp = (University) other;
        if (temp.getId() == this.id && temp.getName().equals(this.name)) {
            return (true);
        } else {
            return (false);
        }
        // then check if everything is the same
    }
}