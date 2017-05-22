package nbody.model;

public class FPSCounter {
    private long startTime = 0;
    private long frameCount = 0;
    private long fps;

    public long countFrame() {
        if (startTime == 0) {
            startTime = System.currentTimeMillis();
        }

        frameCount++;
        long currentTime = System.currentTimeMillis();
        if (currentTime - startTime >= 1000) {
            fps = Math.round(frameCount / ((currentTime - startTime)/1000.0));
            startTime = currentTime;
            frameCount = 0;
        }
        return fps;
    }
}
