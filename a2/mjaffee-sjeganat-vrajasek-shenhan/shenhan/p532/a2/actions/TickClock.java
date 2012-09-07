package shenhan.p532.a2.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import shenhan.p532.a2.model.Clock;

/**
 *
 * @author han
 */
public class TickClock implements ActionListener {

    private Clock clock;

    public TickClock(Clock clock) {
        this.clock = clock;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        int clockValue = clock.getClockValue();
        clock.setClockValue(clockValue + 1);
        int mins = clockValue / 60;
        int secs = clockValue % 60;

        String newClockString = addZeros(mins, 1) + ":" + addZeros(secs, 1);

        clock.setText(newClockString);
        clock.updateDisplay();
    }

    public String addZeros(int number, int numZero) {
        String numberString = new Integer(number).toString();

        for (int i = 1; i <= numZero; i++) {
            if ((number < 10 * numZero)) {
                numberString = "0" + numberString;

            }
        }
        return numberString;
    }
}
