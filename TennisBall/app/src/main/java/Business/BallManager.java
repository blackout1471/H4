package Business;

import Model.Vec;

public class BallManager {

    /**
     * Get the balls position
     * @return The vector representing position
     */
    public Vec getBall() {
        return ball;
    }

    /**
     * Set the balls position
     * @param ball Vector representing position
     */
    public void setBall(Vec ball) {
        this.ball = ball;
    }

    /**
     * Get the balls speed.
     */
    public float getSpeed() {
        return speed;
    }

    private final float speed;
    private Vec ball;

    public BallManager() {
        ball = new Vec(0, 0, 0);
        speed = 50f;
    }

    /**
     * Calculates the next position for the ball, given a force and deltatime.
     * @param force The vector force which will be added to the ball.
     * @param deltaTime The deltatime between last calculate
     * @return A new position for the ball
     */
    public Vec getBallsNextPos(Vec force, float deltaTime) {
        Vec newPos = new Vec(force);
        newPos.mult(speed);
        newPos.mult(deltaTime);
        newPos.add(getBall());

        return newPos;
    }

    /**
     * Moves the ball to the given position
     * @param newPos The new position for the ball
     */
    public void moveBall(Vec newPos) {
        setBall(newPos);
    }

    /**
     * Checks whether 2 rectangle collides with each other.
     * @param r1
     * @param r2
     * @return true if colliding, false otherwise.
     */
    public boolean isColliding(Vec r1, Vec r2) {
        return
            r2.getX() + r2.getZ() > r1.getX() &&
                r2.getY() + r2.getW() > r1.getY() &&
                r1.getX() + r1.getZ() > r2.getX() &&
                r1.getY() + r1.getW() > r2.getY();
    }
}
