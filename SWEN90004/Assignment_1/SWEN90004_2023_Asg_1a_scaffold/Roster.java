import java.util.ArrayList;

/**
 * @author Xuhang Shi
 * @date 9/3/2023 2:44 下午
 * @description class for store the new missions and completed missions
 */
public class Roster {
    private volatile static ArrayList<Mission> newMission;
    private volatile static ArrayList<Mission> completedMission;
    private String name;

    /**
     * add new mission to newMission
     * @param mission the mission generated bt producer
     */
    synchronized void addNew(Mission mission) {
        newMission.add(mission);
        System.out.println(mission + " added to New Roster.");
    }

    Roster(String name) {
        this.name = name;
        newMission = new ArrayList<>();
        completedMission = new ArrayList<>();
    }

    /**
     * remove the completed mission from complete roster
     */
    synchronized void removeComplete() {
        for (Mission mission : completedMission) {
            System.out.println(mission + " removed from Complete Roster.");
        }
        completedMission.clear();
    }

    /**
     * add completed mission to complete roster
     * @param mission
     */
    synchronized void addComplete(Mission mission) {
        completedMission.add(mission);
        System.out.println(mission + " added to Complete Roster.");
    }

    /**
     * get mission for heroes
     * @return mission
     */
    synchronized Mission getMission() {
        if (newMission != null && newMission.size() != 0) {
            Mission temp = newMission.get(0);
            newMission.remove(0);
            return temp;
        }
        return null;
    }
}
