package breakout_assgn_2_v_1_0;

public class PaddleRightMoveCommand implements Command {

	private double prevX;
	private Paddle paddle;

	PaddleRightMoveCommand(Paddle paddle) {
		this.paddle = paddle;
	}

	public double getPrevX() {
		return prevX;
	}

	public void setPrevX(double prevX) {
		this.prevX = prevX;
	}

	public void execute() {
		prevX = paddle.getX();
		paddle.setX(paddle.getX() + Constants.PADDLE_PRECISION);
	}

	public void undo() {
		
	}

}