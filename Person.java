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
public class Person {
    private String name;
    private Skills skills;
    private String planetPreference;

    /**
     * the constructor
     * 
     * @param name
     *            name
     * @param agri
     *            agriculture
     * @param medi
     *            medicine
     * @param tech
     *            technology
     * @param planet
     *            planet
     */
    public Person(String name, int agri, int medi, int tech, String planet) {
        skills = new Skills(agri, medi, tech);
        this.name = name;
        planetPreference = planet;
    }


    /**
     * gets the skills
     * 
     * @return returns skill
     */
    public Skills getSkills() {
        return skills;
    }


    /**
     * gets the name
     * 
     * @return returns the name
     */
    public String getName() {
        return name;
    }


    /**
     * gets the planet
     * 
     * @return returns the planet
     */
    public String getPlanetName() {
        return planetPreference;
    }


    /**
     * sets to a string
     * 
     * @return returns a string
     */
    public String toString() {
        StringBuilder build = new StringBuilder();

        if (planetPreference.length() <= 0) {
            build.append("No-Planet ");
        }
        build.append(name);
        build.append(" A:");
        build.append(skills.getAgriculture());
        build.append(" M:");
        build.append(skills.getMedicine());
        build.append(" T:");
        build.append(skills.getTechnology());
        if (planetPreference.length() > 0) {
            build.append(" Wants: ");
            build.append(planetPreference);
        }
        return build.toString();
    }


    /**
     * checks if they are equal
     * 
     * @param obj
     *            the other object
     * @return if they are equal
     */
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        return name.equals(((Person)obj).getName()) && skills.equals(
            ((Person)obj).getSkills()) && planetPreference.equals(((Person)obj)
                .getPlanetName());
    }

}
