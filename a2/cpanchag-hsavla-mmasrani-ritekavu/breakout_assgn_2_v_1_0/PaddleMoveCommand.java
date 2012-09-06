package breakout_assgn_2_v_1_0;

public class PaddleMoveCommand implements Command {

	private int prevX;
	private Paddle paddle;

	PaddleMoveCommand(Paddle paddle) {
		this.paddle = paddle;
	}

	public int getPrevX() {
		return prevX;
	}

	public void setPrevX(int prevX) {
		this.prevX = prevX;
	}

	public void execute() {
		System.out.println("\nExecuted: "+paddle.getX());
		setPrevX(paddle.getX());
		
	}

	public void undo() {
		
		paddle.setX(prevX);
	}
}
