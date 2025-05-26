package model;

/**
 * Represents a screen printer for printing event log to the console.
 */
public class ConsolePrinter {

    // EFFECTS: print all events logged since the application started
    public static void printLog() {
        System.out.println("Events logged since application started:");
        for (Event next : EventLog.getInstance()) {
            System.out.println(next.toString());
        }
    }

}
