/**
 * @author Xuhang Shi
 * @date 9/3/2023 2:56 下午
 * @description the thread of professor Z
 */
public class ProfessorZ extends Thread {

    private Mansion mansion;

    @Override
    public void run() {
        while (true) {
            try {
                sleep(Params.getProfWaitingTime());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //enter mansion and notify heroes
            mansion.enterProfMansion();
            waitForMeeting();
            mansion.setIsMeeting(true);
            //notify heroes that meeting begins
            notifyMeeting();
            System.out.println("Meeting Begins!");
            waitForEndMeeting();
            mansion.setIsMeeting(false);
            //meeting ends
            System.out.println("Meeting Ends!");
            try {
                Thread.sleep(Params.getProfWaitingTime());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //exit mansion and notify heroes
            mansion.exitProfMansion();
        }
    }


    public ProfessorZ(Mansion mansion) {
        this.mansion = mansion;
        this.setName("Professor Z");
    }

    /**
     * wait till ready for meeting
     */
    void waitForMeeting() {
        synchronized (this.mansion) {
            while (mansion.heroInSecret != mansion.numOfHero) {
                try {
                    //wait till all heroes entered the secret room
                    this.mansion.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * wait till ready for end meeting
     */
    void waitForEndMeeting() {
        synchronized (this.mansion) {
            while (mansion.heroInSecret != 0) {
                try {
                    //wait till all heroes leaves room
                    this.mansion.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * inform heroes the meeting is start
     */
    void notifyMeeting() {
        synchronized (this.mansion) {
            this.mansion.notifyAll();
        }
    }


}
