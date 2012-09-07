package shenhan.p532.a2.gui;

import javax.swing.JButton;
import shenhan.p532.a2.controller.GameController;
import shenhan.p532.a2.actions.PressUndo;

/**
 *
 * @author han
 */
public class ButtonUndo extends JButton {

    public ButtonUndo(GameController gameController) {
        super();
        this.setText("Undo");
        this.setBounds(10, 10, 80, 20);
        this.addActionListener(new PressUndo(gameController));
    }
}
