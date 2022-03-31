
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Kirsten Chesley (kchesley888)
 * @version (2020-Apr-20)
 */
public class ColonyCalculator {
    /**
     * the number of planets
     */
    public static final int NUM_PLANETS = 3;
    /**
     * the minimum skill level
     */
    public static final int MIN_SKILL_LEVEL = 1;
    /**
     * the maximum skill level
     */
    public static final int MAX_SKILL_LEVEL = 5;
    private ArrayQueue<Person> applicantQueue;
    private ArrayList<Person> rejectBus;
    private static Planet[] planets = new Planet[NUM_PLANETS + 1];

    /**
     * constructor
     * 
     * @param person
     *            person
     * @param planet
     *            planet
     */
    public ColonyCalculator(ArrayQueue<Person> person, Planet[] planet) {
        if (person == null) {
            throw new IllegalArgumentException();

        }
        applicantQueue = person;
        planets = planet;
        rejectBus = new ArrayList<Person>();
    }


    /**
     * gets the queue
     * 
     * @return the applicant queue
     */
    public ArrayQueue<Person> getQueue() {
        return applicantQueue;
    }


    /**
     * gets planets
     * 
     * @return planets
     */
    public static Planet[] getPlanets() {
        return planets;
    }


    /**
     * gets the planet for the person
     * 
     * @param nextPerson
     *            person
     * @return the planet
     */
    public Planet getPlanetForPerson(Person nextPerson) {
        if (nextPerson == null || applicantQueue.isEmpty()) {
            return null;
        }
        String pl = nextPerson.getPlanetName();
        try {
            if (pl.length() > 0) {
                char a = pl.charAt(pl.length() - 1);
                if ((a == '1' || a == '2' || a == '3')) {
                    int x = a;
                    if (x <= NUM_PLANETS && planetByNumber(x).isQualified(
                        nextPerson) && !planetByNumber(x).isFull()) {
                        return planets[x];
                    }
                }
            }
        }
        catch (NumberFormatException e) {
            //left empty
        }
        ArrayList<Planet> cop = new ArrayList<Planet>();
        for (int i = 0; i < 3; i++) {
            if (planets[i] != null) {
                cop.add(planets[i]);
            }
        }
        Collections.sort(cop);
        cop.add(0, null);
        boolean qualified = false;
        Planet highest = cop.get(1);
        for (int i = 0; i < NUM_PLANETS; i++) {
            Planet c = cop.get(i);
            if (c != null && c.isQualified(nextPerson) && !c.isFull() && c
                .compareTo(highest) >= 0) {
                qualified = true;
                highest = c;
            }
        }
        if (qualified) {
            return highest;
        }
        else {
            return null;
        }
    }


    /**
     * checks if they are accepted
     * 
     * @return if they are accepted
     */
    public boolean accept() {
        if (!applicantQueue.isEmpty()) {
            Person person = applicantQueue.getFront();
            Planet planet = getPlanetForPerson(person);
            if (planet == null) {
                return false;
            }
            planet.addPerson(person);
            applicantQueue.dequeue();
            return true;
        }
        return false;
    }


    /**
     * rejects the person
     */
    public void reject() {
        if (!applicantQueue.isEmpty()) {
            Person person = applicantQueue.getFront();
            applicantQueue.dequeue();
            rejectBus.add(person);
        }
    }


    /**
     * gets the planet by number
     * 
     * @param num
     *            the planet number
     * @return the planet
     */
    public Planet planetByNumber(int num) {
        return (num >= 1 && num <= 3) ? planets[num] : null;
    }


    /**
     * gets the index of the planet
     * 
     * @param index
     *            the index
     * @return the index
     */
    public int getPlanetIndex(String index) {
        for (int i = 0; i < planets.length; i++) {
            if (planets[i] != null && planets[i].getName().equals(index)) {
                return i;
            }
        }
        return 0;
    }
}
