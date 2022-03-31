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
public class Planet implements Comparable<Planet> {
    private String name;
    private Skills minSkills;
    private Person[] population;
    private int populationSize;
    private final int capacity;

    /**
     * constructor
     * 
     * @param planetName
     *            planet name
     * @param planetAgri
     *            planet agriculture
     * @param planetMedi
     *            planet medicine
     * @param planetTech
     *            planet technology
     * @param planetCap
     *            planet capacity
     */
    public Planet(
        String planetName,
        int planetAgri,
        int planetMedi,
        int planetTech,
        int planetCap) {
        name = planetName;
        minSkills = new Skills(planetAgri, planetMedi, planetTech);
        capacity = planetCap;

        population = new Person[capacity];
        populationSize = 0;
    }


    /**
     * sets the name
     * 
     * @param nam
     *            the name
     */
    public void setName(String nam) {
        name = nam;
    }


    /**
     * gets the name
     * 
     * @return the name
     */
    public String getName() {
        return name;
    }


    /**
     * gets the skills
     * 
     * @return skills
     */
    public Skills getSkills() {
        return minSkills;
    }


    /**
     * gets the population
     * 
     * @return the population
     */
    public Person[] getPopulation() {
        return population;
    }


    /**
     * gets the population size
     * 
     * @return the population size
     */
    public int getPopulationSize() {
        return populationSize;
    }


    /**
     * returns the capacity
     * 
     * @return the capacity
     */
    public int getCapacity() {
        return capacity;
    }


    /**
     * returns the availability
     * 
     * @return the availability
     */
    public int getAvailability() {
        return (capacity - populationSize);
    }


    /**
     * checks if it is full
     * 
     * @return if full
     */
    public boolean isFull() {
        return capacity == populationSize;
    }


    /**
     * adds a person
     * 
     * @param person
     *            the person
     * @return if added
     */
    public boolean addPerson(Person person) {
        if (!isFull() && isQualified(person)) {
            population[populationSize] = person;
            populationSize++;
            return true;
        }
        return false;
    }


    /**
     * checks if it is qualified
     * 
     * @param person
     *            the person
     * @return if are qualified
     */
    public boolean isQualified(Person person) {
        return minSkills.isBelow(person.getSkills());
    }


    /**
     * turns to a string
     * 
     * @return returns a string
     */
    public String toString() {
        StringBuilder build = new StringBuilder();
        build.append(name + ", population " + populationSize + " (cap: "
            + capacity + "), Requires: A >= " + minSkills.getAgriculture()
            + ", M >= " + minSkills.getMedicine() + ", T >= " + minSkills
                .getTechnology());
        return build.toString();
    }


    /**
     * compares the planets
     * 
     * @param other
     *            the other planet
     * @return if available
     */
    public int compareTo(Planet other) {
        if (getAvailability() < other.getAvailability()) {
            return -1;
        }
        if (getAvailability() == other.getAvailability()) {
            return 0;
        }
        return 1;
    }


    /**
     * checks if equals
     * 
     * @param obj
     *            the object
     * @return returns if equals
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
        return (name.equals(((Planet)obj).getName()) && minSkills.equals(
            ((Planet)obj).getSkills()) && getPopulationSize() == ((Planet)obj)
                .getPopulationSize());
    }
}
