
import java.io.FileNotFoundException;
import bsh.ParseException;


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
        
        

