
import java.awt.Color;
import CS2114.Button;
import CS2114.CircleShape;
import CS2114.Shape;
import CS2114.TextShape;
import CS2114.Window;
import CS2114.WindowSide;
import list.AList;

/**
 *
 * @author Kirsten Chesley (kchesley888)
 * @version (2020-Apr-20)
 */
public class SpaceWindow {
    private Window window;
    private ColonyCalculator colonyCalculator;
    private Button accept;
    private Button reject;
    public static final int QUEUE_STARTX = 100;

    public static final int QUEUE_STARTY = 150;

    public static final int CIRCLE_SIZE = 10;

    private TextShape personInfo;
    private TextShape planet1;
    private TextShape planet2;
    private TextShape planet3;
    int circles = ColonyCalculator.NUM_PLANETS;
    public static final int SHAPE_SIZE = 50;
    public static final int PLSX = 100;
    public static final int PLSY = 150;

    public SpaceWindow(ColonyCalculator c) throws SpaceColonyDataException {
        colonyCalculator = c;
        if (colonyCalculator == null) {
            throw new SpaceColonyDataException("Error");
        }
        window = new Window("Space Colony Placement");
        accept = new Button("ACCEPT");
        reject = new Button("REJECT");
        accept.onClick(this, "clickedAccept");
        reject.onClick(this, "clickedReject");
        window.addButton(accept, WindowSide.SOUTH);
        window.addButton(reject, WindowSide.SOUTH);
        if (c.getQueue().isEmpty()) {
            personInfo = new TextShape(QUEUE_STARTX, QUEUE_STARTY - CIRCLE_SIZE,
                " ");
        }
        else {
            personInfo = new TextShape(QUEUE_STARTX, QUEUE_STARTY - CIRCLE_SIZE,
                c.getQueue().getFront().toString());
        }
        Planet pl1 = c.planetByNumber(1);
        Planet p2 = c.planetByNumber(2);
        Planet p3 = c.planetByNumber(3);

        planet1 = new TextShape(PLSX, PLSY, pl1.getName() + ", 0/10");
        if (p2 != null) {
            planet2 = new TextShape(PLSX + SHAPE_SIZE * 3 / 2, PLSY, p2
                .getName() + ", 0/10");
        }
        if (p3 != null) {
            planet3 = new TextShape(PLSX + SHAPE_SIZE * 3, PLSY, p3.getName()
                + ", 0/10");
        }

        window.addShape(planet1);
        window.addShape(planet2);
        window.addShape(planet3);
        window.addShape(personInfo);
        update();
    }


    /**
     * updates teh window whenever there is a change
     */
    public void update() {
        if (colonyCalculator.getQueue().isEmpty()) {
            window.removeAllShapes();
            window.removeButton(accept, WindowSide.SOUTH);
            window.removeButton(reject, WindowSide.SOUTH);
            TextShape finis = new TextShape(window.getWidth() / 3, window
                .getHeight() / 3, "All applicants processed! Good work!");
            window.addShape(finis);
        }
        else {
            window.removeAllShapes();
            personInfo.remove();
            personInfo = new TextShape(QUEUE_STARTX, QUEUE_STARTY - CIRCLE_SIZE
                * 4 / 2, colonyCalculator.getQueue().getFront().toString());
            Planet p1 = colonyCalculator.planetByNumber(1);
            Planet p2 = colonyCalculator.planetByNumber(2);
            Planet p3 = colonyCalculator.planetByNumber(3);

            planet1.setText(p1.getName() + ", " + p1.getPopulationSize() + "/"
                + p1.getCapacity());
            TextShape perma1 = new TextShape(PLSX, PLSY + 20, p1.getSkills()
                .toString());
            if (p2 != null) {
                planet2.setText(p2.getName() + ", " + p2.getPopulationSize()
                    + "/" + p2.getCapacity());
                TextShape perma2 = new TextShape(PLSX + SHAPE_SIZE * 3 / 2, PLSY
                    + 20, p2.getSkills().toString());

                window.addShape(perma2);
            }
            if (p3 != null) {
                planet3.setText(p3.getName() + ", " + p3.getPopulationSize()
                    + "/" + p3.getCapacity());

                TextShape perma3 = new TextShape(PLSX + SHAPE_SIZE * 3, PLSY
                    + 20, p3.getSkills().toString());
                window.addShape(perma3);
            }
            window.addShape(planet1);
            window.addShape(planet2);
            window.addShape(planet3);
            window.addShape(perma1);
            window.addShape(personInfo);
            for (int i = 1; i < ColonyCalculator.NUM_PLANETS + 1; i++) {
                Shape planet = new Shape(PLSX + SHAPE_SIZE * 3 / 2 * (i - 1),
                    PLSY - SHAPE_SIZE, SHAPE_SIZE, SHAPE_SIZE);
                Shape fill = planet;
                Planet current = colonyCalculator.planetByNumber(i);
                if (current != null) {
                    int ss = SHAPE_SIZE * current.getAvailability() / current
                        .getCapacity();
                    fill = new Shape(PLSX + SHAPE_SIZE * 3 / 2 * (i - 1), PLSY
                        - SHAPE_SIZE, SHAPE_SIZE, ss);
                    window.moveToFront(fill);
                }

                planet.setForegroundColor(Color.BLACK);
                fill.setForegroundColor(Color.BLACK);
                switch (i) {
                    case 1:
                        planet.setBackgroundColor(Color.GREEN);
                        fill.setBackgroundColor(Color.CYAN);
                        break;
                    case 2:
                        planet.setBackgroundColor(Color.RED);
                        fill.setBackgroundColor(Color.PINK);
                        break;
                    case 3:
                        planet.setBackgroundColor(Color.BLUE);
                        fill.setBackgroundColor(Color.YELLOW);
                        break;
                }
                window.addShape(planet);
                window.addShape(fill);
                window.moveToBack(planet);
            }
            ArrayQueue<Person> temp = null;
            if (!colonyCalculator.getQueue().isEmpty()) {
                temp = colonyCalculator.getQueue();
            }
            Object[] copy = temp.toArray();
            for (int i = 0; i < temp.getSize(); i++) {
                CircleShape shape = new CircleShape(QUEUE_STARTX + CIRCLE_SIZE
                    * i, QUEUE_STARTY, CIRCLE_SIZE);
                String plan = ((Person)copy[i]).getPlanetName();
                int personPlanet = colonyCalculator.getPlanetIndex(plan);
                shape.setBackgroundColor(Color.BLACK);
                switch (personPlanet) {
                    case 1:
                        shape.setForegroundColor(Color.GREEN);
                        break;
                    case 2:
                        shape.setForegroundColor(Color.CYAN);
                        break;
                    case 3:
                        shape.setForegroundColor(Color.MAGENTA);
                        break;

                }
                window.addShape(shape);
            }
        }

    }


    /**
     * accepts
     * 
     * @param button
     *            accept button
     */
    public void clickedAccept(Button button) {
        boolean accepted = colonyCalculator.accept();
        if (accepted == true) {
            update();
        }
        else {
            button.disable();
        }

    }


    /**
     * rejects
     * 
     * @param button
     *            reject button
     */
    public void clickedReject(Button button) {
        colonyCalculator.reject();
        update();
        accept.enable();
    }

}
