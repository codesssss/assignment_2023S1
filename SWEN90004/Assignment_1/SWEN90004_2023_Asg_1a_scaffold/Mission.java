/**
 * A mission, with a unique id, to be assigned to a superhero for completion
 *
 * @author ngeard@unimelb.edu.au
 */


public class Mission {
    /**
     * @id
     */
    private int id;

    /**
     * the next ID to be allocated
     */
    private static int nextId = 1;

    /**
     * a flag indicating whether the mission has been completed
     */
    boolean completed;


    /** create a new mission with a given identifier
     *
     * @param id id of mission
     */
    Mission(int id) {
        this.id = id;
        this.completed = false;
    }

    /** get a new Mission instance with a unique identifier
     *
     * @return
     */
    public static Mission getNewMission() {
        return new Mission(nextId++);
    }

    /** produce an identifying string for the mission
     *
     * @return
     */
    @Override
    public String toString() {
        return "Mission " + id;
    }

    /**
     * set the mission state to completed
     */
    public void setMissionCompleted(){
        completed=true;
    }
}