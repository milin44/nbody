package nbody.gui;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import javafx.util.Duration;
import nbody.model.Body;
import nbody.model.SolarSystem;

import java.util.List;

public class NBodyFxGui extends Application {
    private static final double GUI_RADIUS = 300;
    private static final double GUI_DIAMETER = GUI_RADIUS *2;


    private List<Body> bodies;
    private SolarSystem solarSystem;

    @Override
    public void start(Stage theStage) {
        createBodies();
        theStage.setTitle("NBody simulation");
        Group root = new Group();
        Scene theScene = new Scene(root);
        theStage.setScene(theScene);
        Canvas canvas = new Canvas(GUI_DIAMETER, GUI_DIAMETER);
        root.getChildren().add(canvas);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        final long timeStart = System.currentTimeMillis();

        KeyFrame kf = new KeyFrame(
                Duration.seconds(0.017),                // 60 FPS
                new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent ae) {
                        double elapsedTime = (System.currentTimeMillis() - timeStart);
                        updateFrame(gc, elapsedTime);
                    }
                });

        timeline.getKeyFrames().add(kf);
        timeline.play();
        theStage.show();
    }

    protected void updateFrame(GraphicsContext gc, double elapsedTime) {
        gc.clearRect(0, 0, GUI_DIAMETER, GUI_DIAMETER);
        for (Body body : bodies) {
            //body.update();
            gc.fillOval(model_x_to_pixels(body.location.x), model_y_to_pixels(body.location.y), 10, 10);
        }
    }

    private void createBodies() {
        this.solarSystem = new SolarSystem();
        this.bodies = solarSystem.createSolarSystem();
    }

    /**
     * Converts a model cartasian coordinates x coordinate meters to a horizontal position in the gui.
     * @param x
     * @return
     */
    private double model_x_to_pixels(double x) {
        return model_distance_to_pixel(x);
    }

    /**
     * Converts a model cartasian coordinates y coordinate meters to a vertical position in the gui.
     *
     * Javafx coordinates have origo top-left while cartesian coordinates have origo bottom-left.
     * @param y
     * @return
     */
    private double model_y_to_pixels(double y) {
        return model_distance_to_pixel(GUI_DIAMETER - y);
    }

    /**
     * Scales a distance in the model to number of pixels in gui.
     *
     * @param distance
     * @return
     */
    private double model_distance_to_pixel(double distance) {
        return distance / SolarSystem.SOLAR_SYSTEM_RADIUS * GUI_RADIUS;

    }

    public static void main(String[] args) {
        launch(args);
    }
}
