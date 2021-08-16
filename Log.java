import java.util.ArrayList;

public class Log {
    private ArrayList<String> logg;
    private String[] ans = new String[0];

    public Log() {
        this.logg = new ArrayList<String>();
    }

    public Log copy() {
        Log copy = new Log();
        for (int i = 0; i < logg.size(); i++) {
            copy.record(logg.get(i));
        }
        return (copy); // copy the log
    }

    public String[] read() {
        if (logg.isEmpty()) {
            return (ans);
        }
        ans = new String[logg.size()];
        for (int i = 0; i < logg.size(); i++) {
            ans[i] = logg.get(i);
        }
        return (ans); // read is to read the things in log and return in String array
    }

    public void record(String msg) {
        logg.add(msg); // record given msg to logg the array
    }
}