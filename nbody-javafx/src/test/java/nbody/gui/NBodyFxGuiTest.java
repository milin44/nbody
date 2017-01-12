package nbody.gui;

import nbody.model.Body;
import nbody.model.SolarSystem;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import static nbody.gui.NBodyFxGui.GUI_DIAMETER;

public class NBodyFxGuiTest {
    private static final double GUI_WIDTH_IN_PIXELS = GUI_DIAMETER;
    private static final double GUI_HEIGHT_IN_PIXELS = GUI_DIAMETER;
    private static  final double MODEL_MAX_WIDTH_IN_METERS = SolarSystem.SOLAR_SYSTEM_RADIUS * 2;
    private NBodyFxGui gui;

    @Before
    public void setUp() {
        gui = new NBodyFxGui();
    }

    @Test
    public void model_distance_to_pixel_test() {
        assertEquals(GUI_WIDTH_IN_PIXELS, gui.model_distance_to_pixel(MODEL_MAX_WIDTH_IN_METERS),  0.01);
    }

    @Test
    public void model_x_to_pixels_test_min_model_x() {
        assertEquals(0, gui.model_x_to_pixels(0), 0.01);
    }

    @Test
    public void model_x_to_pixels_test_max_model_x() {
        assertEquals(GUI_WIDTH_IN_PIXELS, gui.model_x_to_pixels(MODEL_MAX_WIDTH_IN_METERS), 0.01);
    }

    @Test
    public void model_y_to_pixels_test_min_model_y() {
        assertEquals(GUI_HEIGHT_IN_PIXELS, gui.model_y_to_pixels(0), 0.01);
    }

    @Test
    public void model_y_to_pixels_test_max_model_y() {
        assertEquals(0, gui.model_y_to_pixels(MODEL_MAX_WIDTH_IN_METERS), 0.01);
    }

    @Test
    public void testIter() {
        SolarSystem solarSystem = new SolarSystem();

        double time = 0;
        for (int i = 0; i < 100; i++) {
            Body earth = solarSystem.getBody(SolarSystem.EARTH_NAME).get();
            System.out.println(earth);
            System.out.println("nr: " + i + ", time: " + time + ", xpixels: " + gui.model_x_to_pixels(earth.location.x));
            System.out.println("------------------------------------------------------------------------------");

            /*

            Body sun = solarSystem.getBody(SolarSystem.SUN_NAME).get();
            System.out.println(sun);
            System.out.println("nr: " + i + ", time: " + time + ", xpixels: " + gui.model_x_to_pixels(sun.location.x));
            System.out.println("#################################################################################3");
            */

            time += solarSystem.update(30000);
        }




/*
        solarSystem.timeMultiplier = 200;
        double time = 0;
        for (int i = 0; i < 6; i++) {
            Body earth = solarSystem.getBody(SolarSystem.EARTH_NAME).get();
            System.out.println(earth);
            System.out.println("nr: " + i + ", time: " + time + ", xpixels: " + gui.model_x_to_pixels(earth.location.x));
            System.out.println("------------------------------------------------------------------------------");
            time += solarSystem.updateVelocityAndLocation();
        }
*/

    }

}
