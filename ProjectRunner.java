// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Kirsten Chesley (kchesley888)
package spacecolonies;

import java.io.FileNotFoundException;
import bsh.ParseException;

/**
 *
 * @author Kirsten Chesley (kchesley888)
 * @version (2020-Apr-20)
 */
public class ProjectRunner {

    /**
     * constructor
     */
    public ProjectRunner() {
        //left empty
    }
    
    /**
     * the method
     * @param args input
     * @throws ParseException exception
     * @throws SpaceColonyDataException exception
     * @throws FileNotFoundException exception
     * @throws bsh.ParseException 
     */
    @SuppressWarnings("unused")
    public static void main(String[] args) 
        throws ParseException, SpaceColonyDataException,
        FileNotFoundException{
        if (args.length == 2) {
            ColonyReader c = new ColonyReader(args[0], args[1]);
        }
        else {
            ColonyReader c = new ColonyReader("input.txt", "planets.txt");
        }
    }
}
        
        

