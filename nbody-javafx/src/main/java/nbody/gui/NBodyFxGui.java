package nbody.gui;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import nbody.model.*;

/**
 * Gui representing the solar system using javafx.
 * <p>
 * Note that javafx uses a coordinate system with origin top left.
 */
public class NBodyFxGui extends Application {
    /**
     * Number of seconds to update the model with for each iteration
     */
    public static final double TIME_SLICE = 60000/4;

    public static final int BOTTOM_AREA_HEIGHT = 100;
    //public static final double SCALE = 3e8;
    public static final double SCALE = 5e8;

    private static final int SEC_IN_MINUTE = 60;
    private static final int SEC_IN_HOUR = SEC_IN_MINUTE * 60;
    private static final int SEC_IN_DAY = SEC_IN_HOUR * 24;
    private static final int SEC_IN_YEAR = 31556926;

    private SolarSystem solarSystem;
    private long elapsedTime = 0;
    private double canvasWidth = 0;
    private double canvasHeight = 0;
    private Vector2D dragPosStart;
    private Label timeLabel;

    private final CoordinatesTransformer transformer = new CoordinatesTransformer();

    @Override
    public void start(Stage stage) {
        createBodies();
        transformer.setScale(SCALE);
        GraphicsContext gc = createGui(stage);
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        KeyFrame kf = new KeyFrame(
                Duration.seconds(1/240.0),           // 240 FPS
                new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent ae) {
                        updateFrame(gc);
                    }
                });
        timeline.getKeyFrames().add(kf);
        timeline.play();
        stage.show();
    }

    protected void updateFrame(GraphicsContext gc) {
        this.canvasWidth = gc.getCanvas().getWidth();
        this.canvasHeight = gc.getCanvas().getHeight();
        gc.clearRect(0, 0, canvasWidth, canvasHeight);

        for (Body body : solarSystem.getBodies()) {
            //System.out.println("xm:" + body.location.x + ", ym:" + body.location.y);
            //System.out.println("xg:" + model_x_to_pixels(body.location.x) + ", yg:" + model_y_to_pixels(body.location.y));
            double circleRadius = 2;
            /*
            if (body.name.equals(CelestialBody.MOON.celestialName)) {
                gc.setFill(Color.RED);
                circleRadius = 2;
            } else {
                gc.setFill(Color.BLACK);
            }
            */
            gc.fillOval(transformer.modelToOtherX(body.location.x) - circleRadius, transformer.modelToOtherY(body.location.y) - circleRadius, circleRadius * 2, circleRadius * 2);
        }

        //if (elapsedTime == 0) {
            solarSystem.update(TIME_SLICE);
            elapsedTime += TIME_SLICE;
        //}

        timeLabel.setText(getElapsedTimeAsString(this.elapsedTime));
    }

    protected void createBodies() {
        this.solarSystem = new SolarSystem();
    }

    private GraphicsContext createGui(Stage stage) {
        BorderPane border = new BorderPane();
        createTimeLabel();
        HBox hbox = createHBox();
        border.setBottom(hbox);
        Canvas canvas = createCanvas();
        border.setCenter(canvas);
        stage.setTitle("NBody simulation");
        Scene scene = new Scene(border);
        stage.setScene(scene);
        stage.setMaximized(true);

        // Bind canvas size to stack pane size.
        canvas.widthProperty().bind(stage.widthProperty());
        canvas.heightProperty().bind(stage.heightProperty().subtract(BOTTOM_AREA_HEIGHT));
        return canvas.getGraphicsContext2D();
    }

    private Canvas createCanvas() {
        Canvas canvas = new ResizableCanvas();
        canvas.setOnDragDetected((event) -> this.dragPosStart = new Vector2D(event.getX(), event.getY()));

        canvas.setOnMouseDragged((event) -> {
            if (this.dragPosStart != null) {
                Vector2D dragPosCurrent = new Vector2D(event.getX(), event.getY());
                dragPosCurrent.sub(this.dragPosStart);
                dragPosStart = new Vector2D(event.getX(), event.getY());
                transformer.setOriginXForOther(transformer.getOriginXForOther() + dragPosCurrent.x);
                transformer.setOriginYForOther(transformer.getOriginYForOther() + dragPosCurrent.y);
            }
        });

        canvas.setOnMouseReleased((event) -> this.dragPosStart = null);

        canvas.setOnScroll((event) -> {
            if (event.getDeltaY() > 0) {
                transformer.setScale(transformer.getScale() * 0.9);
            } else {
                transformer.setScale(transformer.getScale() * 1.1);
            }
        });

        return canvas;
    }

    /**
     * Creates an HBox with two buttons for the top region
     */
    private HBox createHBox() {
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(10);   // Gap between nodes
        hbox.setStyle("-fx-background-color: #336699;");
        hbox.setFillHeight(true);
        hbox.getChildren().addAll(this.timeLabel);
        return hbox;
    }

    private void createTimeLabel() {
        timeLabel = new Label();
        timeLabel.setPrefSize(500, 20);
    }

    /*
    private Button addZoomOutBtn() {
        Button btn = new Button("Zoom out");
        btn.setPrefSize(100, 20);
        btn.setOnAction((event) -> {

        });
        return btn;
    }
    */

    private String getElapsedTimeAsString(long elapsedSeconds) {
        long years = elapsedSeconds / SEC_IN_YEAR;
        long days = (elapsedSeconds % SEC_IN_YEAR) / SEC_IN_DAY;
        long hours = ( (elapsedSeconds % SEC_IN_YEAR) % SEC_IN_DAY) / SEC_IN_HOUR;
        long minutes = ( ((elapsedSeconds % SEC_IN_YEAR) % SEC_IN_DAY) % SEC_IN_HOUR) / SEC_IN_MINUTE;
        long seconds = ( ((elapsedSeconds % SEC_IN_YEAR) % SEC_IN_DAY) % SEC_IN_HOUR) % SEC_IN_MINUTE;
        return String.format("Years:%08d, Days:%03d, Hours:%02d, Minutes:%02d, Seconds:%02d", years, days, hours, minutes, seconds);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
