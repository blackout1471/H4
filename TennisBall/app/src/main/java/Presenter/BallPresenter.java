package Presenter;

import android.util.Log;

import androidx.constraintlayout.motion.widget.Debug;

import Business.BallManager;
import Model.Vec;

public class BallPresenter {

    private final BallManager manager;
    private final View view;

    private final Vec screenRect;

    public BallPresenter(View _view, Vec screen) {
        manager = new BallManager();
        view = _view;
        screenRect = screen;
    }

    /**
     * Called when the ball is created and will tell the view to draw the ball at it's position
     * @param pos The balls position
     */
    public void onBallCreate(Vec pos) {
        manager.setBall(pos);
        view.ballMoved(manager.getBall());
    }

    /**
     * Called when the ball has to update it's position
     */
    public void onBallUpdate(Vec force, float deltaTime) {
        Vec ballNewPos = manager.getBallsNextPos(force, deltaTime);
        ballNewPos.setW(50);
        ballNewPos.setZ(50);

        if (!manager.isColliding(ballNewPos, screenRect))
            return;

        manager.moveBall(ballNewPos);
        view.ballMoved(manager.getBall());
    }

    public interface View {
        void ballMoved(Vec position);
    }
}
