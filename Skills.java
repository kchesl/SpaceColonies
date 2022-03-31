// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Kirsten Chesley (kchesley888)
package spacecolonies;

/**
 *
 * @author Kirsten Chesley (kchesley888)
 * @version (2020-Apr-20)
 */
public class Skills {

    private int agriculture;
    private int medicine;
    private int technology;

    /**
     * constructor for the class
     * 
     * @param ag
     *            the agriculture
     * @param med
     *            the medicine
     * @param tech
     *            the technology
     */
    public Skills(int ag, int med, int tech) {
        agriculture = ag;
        medicine = med;
        technology = tech;
    }


    /**
     * gets the agriculture
     * 
     * @return the agriculture
     */
    public int getAgriculture() {
        return agriculture;
    }


    /**
     * gets the medicine
     * 
     * @return the medicine
     */
    public int getMedicine() {
        return medicine;
    }


    /**
     * gets the technology
     * 
     * @return technology
     */
    public int getTechnology() {
        return technology;
    }


    /**
     * checks the skill levels
     * 
     * @param other
     *            the others skills
     * @return if the skills are lower
     */
    public boolean isBelow(Skills other) {
        return ((agriculture <= other.agriculture)
            && (medicine <= other.medicine)
            && (technology <= other.technology));
    }


    /**
     * turns it into a string
     * 
     * @return returns a string
     */
    public String toString() {

        StringBuilder build = new StringBuilder();
        build.append("A:");
        build.append(agriculture);
        build.append(" M:");
        build.append(medicine);
        build.append(" T:");
        build.append(technology);
        return build.toString();
    }


    /**
     * checks if they are equal
     * 
     * @param obj
     *            the other skills
     * @return whether they are equal or not
     */
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (this == null || obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        return (agriculture == ((Skills)obj).getAgriculture()
            && medicine == ((Skills)obj).getMedicine()
            && technology == ((Skills)obj).getTechnology());
    }
}
