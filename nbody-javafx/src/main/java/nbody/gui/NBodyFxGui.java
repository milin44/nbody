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

/**
 * Gui representing the solar system using javafx.
 *
 * Note that javafx uses a coordinate system with origin top left.
 */
public class NBodyFxGui extends Application {
    public static final double GUI_RADIUS = 300;
    public static final double GUI_DIAMETER = GUI_RADIUS *2;
    public static final double TIME_SLICE = 30000;

    private SolarSystem solarSystem;
    private double elapsedTime = 0;

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

        KeyFrame kf = new KeyFrame(
                Duration.seconds(0.017),                // 60 FPS
                //Duration.seconds(0.5),                // 2 FPS
                new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent ae) {
                        updateFrame(gc);
                    }
                });

        timeline.getKeyFrames().add(kf);
        timeline.play();
        theStage.show();
    }

    protected void updateFrame(GraphicsContext gc) {
        gc.clearRect(0, 0, GUI_DIAMETER, GUI_DIAMETER);
        for (Body body : solarSystem.getBodies()) {
            //System.out.println("xm:" + body.location.x + ", ym:" + body.location.y);
            //System.out.println("xg:" + model_x_to_pixels(body.location.x) + ", yg:" + model_y_to_pixels(body.location.y));
            double circleRadius = 5;
            gc.fillOval(model_x_to_pixels(body.location.x) - circleRadius, model_y_to_pixels(body.location.y) - circleRadius, circleRadius * 2, circleRadius * 2);
        }
        solarSystem.update(TIME_SLICE);
        elapsedTime += TIME_SLICE;
    }

    protected void createBodies() {
        this.solarSystem = new SolarSystem();
    }

    /**
     * Converts a model x coordinate in cartasian coordinates to a horizontal position in the gui.
     * @param x
     * @return
     */
    protected double model_x_to_pixels(double x) {
        return model_distance_to_pixel(x);
    }

    /**
     * Converts a model a y coordinate in cartasian coordinates to a vertical position in the gui.
     *
     * Javafx coordinates have origin top-left while cartesian coordinates have origin bottom-left.
     * @param y
     * @return
     */
    protected double model_y_to_pixels(double y) {
        return GUI_DIAMETER - model_distance_to_pixel(y);
    }

    /**
     * Scales a distance in the model to number of pixels in gui.
     *
     * @param distance
     * @return
     */
    protected double model_distance_to_pixel(double distance) {
        return distance / SolarSystem.SOLAR_SYSTEM_RADIUS * GUI_RADIUS;

    }

    public static void main(String[] args) {
        launch(args);
    }
}
