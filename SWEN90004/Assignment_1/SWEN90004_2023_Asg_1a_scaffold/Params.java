/**
 * Parameters for the superheros simulator.
 *
 * @author ngeard@unimelb.edu.au
 */

import java.util.Random;
import java.lang.Math;

/**
 * class used for params
 */
class Params {

    static Random rnd = new Random();

    /**
     * number of superheros in the simulator
     */
    static final int NUM_SUPERHEROES = 4;

    /**
     * average duration that superheros spend discussing world affairs before and after meetings
     */
    static final int MEAN_DISCUSSION_TIME = 200;

    /**
     * average duration that superheros spend completing a mission
     */
    static final int MEAN_MISSION_TIME = 40;

    /**
     * average interval between Professor Z leaving and re-entering the Mansion
     */
    static final int MEAN_PROF_WAITING_TIME = 200;

    /**
     * duration between new missions being added
     */
    static final int MISSION_ADDITION_TIME = 100;

    /**
     * duration between completed missions being removed
     */
    static final int MISSION_REMOVAL_TIME = 100;

    /**
     * generate a random discussion duration
     * @return
     */
    static int getDiscussionTime() {
        return (int) Math.max(0.0, rnd.nextGaussian() *
                MEAN_DISCUSSION_TIME / 2 + MEAN_DISCUSSION_TIME);
    }

    /**
     * generate a random mission duration
     * @return
     */
    static int getMissionTime() {
        return (int) Math.max(0.0, rnd.nextGaussian() *
                MEAN_MISSION_TIME / 6 + MEAN_MISSION_TIME);
    }

    /**
     * generate a random interval for Professor Z to be away
     * @return
     */
    static int getProfWaitingTime() {
        return (int) Math.max(0.0, (rnd.nextGaussian() *
                MEAN_PROF_WAITING_TIME / 8) + MEAN_PROF_WAITING_TIME);
    }

}
