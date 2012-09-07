package shenhan.p532.a2.actions;

import acm.graphics.GLabel;
import acm.program.GraphicsProgram;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author han
 */
public class RemoveFlashInfo implements ActionListener {

    private GraphicsProgram program;
    private GLabel flash;
    
    public RemoveFlashInfo(GraphicsProgram program, GLabel flash) {
        this.program = program;
        this.flash = flash;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        program.remove(flash);
    }
}
