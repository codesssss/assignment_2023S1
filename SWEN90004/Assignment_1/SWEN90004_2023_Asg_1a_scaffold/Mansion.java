/**
 * @author Xuhang Shi
 * @date 9/3/2023 2:56 下午
 */
public class Mansion {
    /**
     * flag for professor is in mansion or not
     */
    volatile boolean flagProfessor;

    /**
     * name of mansion
     */
    String name;

    /**
     * amount of hero in mansion
     */
    volatile int numOfHero;

    /**
     * amount of hero in the secret room
     */
    volatile int heroInSecret;

    /**
     * flag for meeting is start or not
     */
    volatile boolean isMeeting;

    Mansion(String name, Roster rosterNew, Roster rosterComplete) {
        flagProfessor = false;
        numOfHero = 0;
        heroInSecret = 0;
        isMeeting=false;
        this.name = name;
    }

    /**
     * heroes enter mansion
     */
    synchronized void enterMansion() {
        numOfHero++;
        System.out.println(Thread.currentThread().getName() + " enters Mansion.");
    }

    /**
     * heroes exit mansion
     */
    synchronized void exitMansion() {
//        numOfHero--;
        System.out.println(Thread.currentThread().getName() + " exits Mansion.");
    }

    /**
     * professor Z exit the mansion
     */
    synchronized void exitProfMansion() {
        flagProfessor = false;
        //notify heroes want to enter or exit the mansion
        this.notifyAll();
        System.out.println(Thread.currentThread().getName() + " exits Mansion.");
    }

    /**
     * professor Z enter the mansion
     */
    synchronized void enterProfMansion() {
        flagProfessor = true;
        //notify heroes want to enter or exit the mansion
        //notifyAll();
        System.out.println(Thread.currentThread().getName() + " enters Mansion.");
    }

    /**
     * heroes enter the secret room
     */
    public synchronized void enterSecretRoom() {
        heroInSecret++;
        //notify the professor heroes are in the secret room
        this.notifyAll();
        System.out.println(Thread.currentThread().getName() + " enters the Secret Room.");
    }

    /**
     * heroes exit the secret room
     */
    public synchronized void exitSecretRoom() {
        heroInSecret--;
        this.notifyAll();
        System.out.println(Thread.currentThread().getName() + " leaves the Secret Room.");
    }

    /**
     * setter for isMeeting
     * @param isMeeting isMeeting
     */
    synchronized void setIsMeeting(Boolean isMeeting){
        this.isMeeting=isMeeting;
    }

}
