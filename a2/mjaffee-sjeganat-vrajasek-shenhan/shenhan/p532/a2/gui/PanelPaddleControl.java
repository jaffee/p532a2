package shenhan.p532.a2.gui;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import shenhan.p532.a2.controller.GameController;
import shenhan.p532.a2.actions.ChoosePaddleControl;
import shenhan.p532.a2.model.Paddle;

/**
 *
 * @author han
 */
public class PanelPaddleControl extends JPanel {

    public final static String ACTION_COMMAND_USE_KB = "kb";
    public final static String ACTION_COMMAND_USE_MOUSE = "mouse";
    private JRadioButton kbControl;
    private JRadioButton mouseControl;

    public PanelPaddleControl(GameController gameController, Paddle paddle) {
        super();

        ButtonGroup bg = new ButtonGroup();

        kbControl = new JRadioButton("use Keyboard");
        mouseControl = new JRadioButton("use Mouse");

        kbControl.setBounds(0, 0, 150, 20);
        mouseControl.setBounds(0, 20, 150, 20);

        bg.add(kbControl);
        bg.add(mouseControl);

        kbControl.setActionCommand(ACTION_COMMAND_USE_KB);
        mouseControl.setActionCommand(ACTION_COMMAND_USE_MOUSE);

        kbControl.addActionListener(new ChoosePaddleControl(gameController));
        mouseControl.addActionListener(new ChoosePaddleControl(gameController));

        this.add(kbControl);
        this.add(mouseControl);

        this.setBounds(460, 10, 150, 50);
        mouseControl.setSelected(true);
    }

    public void reset() {
        mouseControl.setSelected(true);
    }

    public void disableAll() {
        kbControl.setEnabled(false);
        mouseControl.setEnabled(false);
    }

    public void enableAll() {
        kbControl.setEnabled(true);
        mouseControl.setEnabled(true);
    }
}
