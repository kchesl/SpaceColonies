// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Kirsten Chesley (kchesley888)
package spacecolonies;

import java.io.FileNotFoundException;
import java.io.File;
import java.util.Scanner;
import bsh.ParseException;

/**
 *
 * @author Kirsten Chesley (kchesley888)
 * @version (2020-Apr-20)
 */
public class ColonyReader {
    private Planet[] planets;
    private ArrayQueue<Person> queue;

    /**
     * constructor
     * 
     * @param applicantFileName
     *            applicate file name
     * @param planetFileName
     *            planet file name
     * @throws SpaceColonyDataException
     *             exception
     * @throws ParseException
     *             exception
     * @throws FileNotFoundException
     *             exception
     */
    public ColonyReader(String applicantFileName, String planetFileName)
        throws SpaceColonyDataException,
        ParseException,
        FileNotFoundException {
        planets = new Planet[ColonyCalculator.NUM_PLANETS + 1];
        queue = readQueueFile(applicantFileName);
        planets = readPlanetFile(planetFileName);
        // Instantiate a new SpaceWindow with the recently filled colony as its
        // parameter.
        @SuppressWarnings("unused")
        SpaceWindow sw = new SpaceWindow(new ColonyCalculator(queue, planets));
    }


    /**
     * reads teh file
     * 
     * @param fileName
     *            filename
     * @return the file
     * @throws ParseException
     *             exception
     * @throws SpaceColonyDataException
     *             exception
     * @throws FileNotFoundException
     *             exception
     */
    private Planet[] readPlanetFile(String fileName)
        throws ParseException,
        SpaceColonyDataException,
        FileNotFoundException {
        Planet[] temp = new Planet[ColonyCalculator.NUM_PLANETS + 1];
        Scanner file = new Scanner(new File(fileName));
        for (int i = 1; i < ColonyCalculator.NUM_PLANETS + 1; i++) {

            if (file.hasNextLine()) {
                String[] planets = file.nextLine().split(", *");

                if (planets.length != 5) {
                    file.close();
                    throw new ParseException();
                }

                if (!isInSkillRange(Integer.valueOf(planets[1]), Integer
                    .valueOf(planets[2]), Integer.valueOf(planets[3]))) {
                    file.close();
                    throw new SpaceColonyDataException(fileName);
                }
                temp[i] = new Planet(planets[0], Integer.valueOf(planets[1]),
                    Integer.valueOf(planets[2]), Integer.valueOf(planets[3]),
                    Integer.valueOf(planets[4]));
                if (file.hasNextLine()) {
                    file.nextLine();
                }
            }
        }
        if (planets.length < 3) {
            file.close();
            throw (new SpaceColonyDataException("Planet length invalid."));
        }
        file.close();
        return temp;
    }


    /**
     * reads teh file
     * 
     * @param fileName
     *            the file
     * @return the file
     * @throws SpaceColonyDataException
     *             exception
     * @throws FileNotFoundException
     *             exception
     * @throws ParseException
     *             exception
     */
    private ArrayQueue<Person> readQueueFile(String fileName)
        throws SpaceColonyDataException,
        FileNotFoundException,
        ParseException {
        Scanner file = new Scanner(new File(fileName));
        ArrayQueue<Person> temp = new ArrayQueue<Person>();
        while (file.hasNextLine()) {
            String[] skills;
            skills = file.nextLine().split(", *");
            for (int i = 0; i < skills.length; i++) {
                if (skills[i] == null || skills[i].equals("")) {
                    file.close();
                    throw new ParseException();
                }
            }

            if (!isInSkillRange(Integer.valueOf(skills[1]), Integer.valueOf(
                skills[2]), Integer.valueOf(skills[3]))) {
                file.close();
                throw (new SpaceColonyDataException(
                    "Error"));
            }
            try {
                temp.enqueue(new Person(skills[0], Integer.valueOf(skills[1]),
                    Integer.valueOf(skills[2]), Integer.valueOf(skills[3]),
                    skills[4]));
            }
            catch (ArrayIndexOutOfBoundsException e) {
                temp.enqueue(new Person(skills[0], Integer.valueOf(skills[1]),
                    Integer.valueOf(skills[2]), Integer.valueOf(skills[3]),
                    ""));
            }
        }
        file.close();
        return temp;
    }


    /**
     * sees if in skill range
     * 
     * @param num1
     *            first
     * @param num2
     *            second
     * @param num3
     *            third
     * @return if in skill range
     */
    public boolean isInSkillRange(int num1, int num2, int num3) {
        return (num1 >= 1 && num1 <= 5 && num2 >= 1 && num2 <= 5 && num3 >= 1
            && num3 <= 5);
    }
}
