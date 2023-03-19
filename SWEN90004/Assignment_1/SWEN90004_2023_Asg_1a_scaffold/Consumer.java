/**
 * Consumes completed missions from a roster.
 *
 * @author ngeard@unimelb.edu.au
 */

public class Consumer extends Thread {

    /**
     * the roster from which completed missions are removed
     */
    private Roster roster;

    /**
     * creates a new consumer for the given roster
     *
     * @param newAgenda
     */
    Consumer(Roster newAgenda) {
        this.roster = newAgenda;
    }

    /**
     * repeatedly collect completed missions from the roster
     */
    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                // remove a mission that is complete
                roster.removeComplete();
                // let some time pass before the next mission is removed
                sleep(Params.MISSION_REMOVAL_TIME);
            } catch (InterruptedException e) {
                this.interrupt();
            }
        }
    }
}
