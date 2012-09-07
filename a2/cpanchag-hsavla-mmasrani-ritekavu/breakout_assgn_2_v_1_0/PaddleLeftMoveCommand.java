package breakout_assgn_2_v_1_0;

public class PaddleLeftMoveCommand implements Command {

	private double prevX;
	private Paddle paddle;

	PaddleLeftMoveCommand(Paddle paddle) {
		this.paddle = paddle;
	}

	public double getPrevX() {
		return prevX;
	}

	public void setPrevX(int prevX) {
		this.prevX = prevX;
	}

	public void execute() {
		prevX = paddle.getX();
		paddle.setX(paddle.getX() - Constants.PADDLE_PRECISION);
	}

	public void undo() {
		
	}
}
