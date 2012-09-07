package breakout_assgn_2_v_1_0;

public class BallMoveCommand implements Command {

	private double prevX, prevY;
	private Ball ball;

	BallMoveCommand(Ball ball) {
		this.ball = ball;
	}

	public double getPrevX() {
		return prevX;
	}

	public void setPrevX(double prevX) {
		this.prevX = prevX;
	}

	public double getPrevY() {
		return prevY;
	}

	public void setPrevY(double prevY) {
		this.prevY = prevY;
	}

	public void execute() {
		prevX = ball.getX();
		prevY = ball.getY();
		ball.setX(ball.getX() + ball.getVelocityX());
		ball.setY(ball.getY() + ball.getVelocityY());
	}

	public void undo() {
		ball.setX(prevX);
		ball.setY(prevY);
	}
}