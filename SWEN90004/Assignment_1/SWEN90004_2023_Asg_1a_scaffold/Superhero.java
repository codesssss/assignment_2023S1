/**
 * @author Xuhang Shi
 * @date 9/3/2023 2:55 下午
 * @description the thread of superhero
 */
public class Superhero extends Thread {
    private Mansion mansion;
    private Roster rosterNew;
    private Roster rosterComplete;
    private Mission mission;


    public Superhero(int i, Roster rosterNew, Roster rosterComplete, Mansion mansion) {
        this.setName("superhero " + i);
        this.mansion = mansion;
        this.rosterComplete = rosterComplete;
        this.rosterNew = rosterNew;
    }

    @Override
    public void run() {
        while (true) {
            waitForEnter();
            //professor is not in mansion
            this.mansion.enterMansion();
            try {
                Thread.sleep(Params.getDiscussionTime());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.mansion.enterSecretRoom();
            waitForMeeting();
            //meeting starts
            if (mission != null && mission.completed) {
                System.out.println(Thread.currentThread().getName() + " completes " + mission + ".");
                //hand on finished mission
                rosterComplete.addComplete(mission);
                System.out.println(Thread.currentThread().getName() + " releases " + mission + ".");
            }
            //get new mission from roster
            this.mission = rosterNew.getMission();
            waitForMission();
            System.out.println(Thread.currentThread().getName() + " acquires " + mission + ".");
            //leave and notify the professor
            this.mansion.exitSecretRoom();
            try {
                Thread.sleep(Params.getDiscussionTime());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //exit mansion
            waitForLeave();
            this.mansion.exitMansion();
            System.out.println(Thread.currentThread().getName() + " sets of to complete " + mission + ".");
            //execute mission
            try {
                Thread.sleep(Params.getMissionTime());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mission.setMissionCompleted();
        }
    }

    /**
     * wait for professor to leave to enter mansion
     */
    void waitForEnter() {
        synchronized (this.mansion) {
            while (mansion.flagProfessor) {
                try {
                    //wait till the professor leaves
                    this.mansion.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * wait for meeting start
     */
    void waitForMeeting() {
        synchronized (this.mansion) {
            while (!mansion.isMeeting) {
                try {
                    //wait for professor to start the meeting
                    this.mansion.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * wait for professor to leave mansion
     */
    void waitForLeave() {
        synchronized (this.mansion) {
            mansion.numOfHero--;
            while (mansion.flagProfessor) {
                try {
                    //wait for professor
                    this.mansion.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * if roster have no new mission, wait for mission
     */
    void waitForMission() {
        while (mission == null) {
            try {
                //sleep for mission
                sleep(50);
                this.mission = rosterNew.getMission();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
