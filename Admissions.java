public class Admissions {
    private StudentList slist;
    private UniversityList ulist;
    private Applications app;
    private Log log;
    StudentList s1;

    public Admissions(StudentList s, UniversityList u, Applications a) {
        this.slist = s;
        this.ulist = u;
        this.app = a;
        this.log = new Log();
        this.s1 = new StudentList();
    } // constructor

    public void matching(boolean minSATactivated) {
        while (slist.size() > 0) {
            Student bb = slist.getHighestScoreStudent(); // get the highestscorestudent from the list
            if (app.inorno(bb.getId())) { // check if the highestscorestudent applied
                int[] studentsList = app.getStudentSelections(bb.getId()); // get the student applied colleges
                for (int j = 0; j < studentsList.length; j++) {
                    ulist.get(ulist.indexOf(studentsList[j])).activateMinSAT(minSATactivated); // get the university and
                                                                                               // use activateminSAT to
                                                                                               // turn it on or off
                    if (ulist.get(ulist.indexOf(studentsList[j])).admit(bb)) { // if the student can be admitted the
                                                                               // college
                        s1.add(bb); // have this studentlist to record it
                        log.record("THIS PERSON GOT ADMITTED WITH " + bb.getSAT() + ":" + bb.getId()
                                + ulist.get(ulist.indexOf(studentsList[j])).getName());
                        break;
                    } else {
                        log.record("THIS PERSON FAILED WITH " + bb.getSAT() + ":" + bb.getId());
                    }
                }
            }
            slist.remove(bb); // to prevent of getting the student over and over again i remove it. its not
                              // needed if the tester didn't require us to return the list in SAT score order.
        }
    }

    public StudentList studentsWithoutApplication() {
        StudentList gg = new StudentList();
        for (int i = 0; i < slist.size(); i++) {
            if (!app.inorno(slist.get(i).getId())) {
                gg.add(slist.get(i));
            }
        }
        return (gg); // get a new studentlist and if the student is not in application then
        // the student hasnt applied
    }

    public StudentList studentsWithApplication() {
        StudentList gg = new StudentList();
        for (int i = 0; i < slist.size(); i++) {
            if (app.inorno(slist.get(i).getId())) {
                gg.add(slist.get(i));
            }
        }
        return (gg); // opposite of the last one
    }

    public int numOfMatchings() {
        if (s1.size() > 0) {
            return (s1.size());
        } // return the size of students matched or 0 if none
        return (0);
    }

    public int numOfUnfilledPositions() {
        int count = 0;
        for (int i = 0; i < ulist.size(); i++) {
            count += ulist.get(i).getNumOfPositions();
        }
        count -= numOfMatchings();
        return (count); // get all the numofpositions add up and subsctract by the students matched, the
                        // result is the unfilled
    }
}