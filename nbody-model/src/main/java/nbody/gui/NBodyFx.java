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
import nbody.model.Vector2D;

import java.util.ArrayList;
import java.util.List;


public class NBodyFx extends Application {
    private static final double HEIGHT = 512;
    private static final double WIDTH = 512;
    private List<Body> bodies;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage theStage) {
        createBodies();
        theStage.setTitle("NBody simulation");
        Group root = new Group();
        Scene theScene = new Scene(root);
        theStage.setScene(theScene);
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
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
        gc.clearRect(0, 0, WIDTH, HEIGHT);
        for (Body body : bodies) {
            body.update();
            gc.fillOval(cartesian_x_to_model(body.location.x), cartersian_y_to_model(body.location.y), 50, 50);
        }
    }

    private double cartesian_x_to_model(double x) {
        return x;
    }

    private double cartersian_y_to_model(double y) {
        return HEIGHT - y;
    }

    private void createBodies() {
        this.bodies = new ArrayList<>();
        bodies.add(createBody());
    }

    private Body createBody() {
        Vector2D location = new Vector2D(WIDTH/2, HEIGHT/2);
        Vector2D velocity = new Vector2D(2.0, 3.3);
        Body body = new Body(location, velocity, 10, 10);
        return body;
    }
}
