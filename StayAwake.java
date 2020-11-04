import java.util.Scanner;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.lang.reflect.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalTime; 
import static java.time.temporal.ChronoUnit.MINUTES;

public final class StayAwake extends JFrame implements ActionListener
{
    String[] endTimes = { "3:00pm", "3:30pm", "4:00pm", "4:30pm", "5:00pm", "5:30pm", "6:00pm", "6:30pm" };
    JComboBox dropdown = new JComboBox(endTimes);
    JLabel lbl = new JLabel("Select End Time: ");
    JButton btn = new JButton("OK");
    JButton stopBtn = new JButton("End Now");
    int min = 0;
    
    public static void main(String[] args) throws AWTException
    {
        StayAwake stayAwake = new StayAwake();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int)screenSize.getWidth()/2;
        int height = (int)screenSize.getHeight()/2;
        stayAwake.setLocation(width, height);

        stayAwake.setVisible(true);
        stayAwake.run();
    }

    public void run() throws AWTException {
        setLayout(new FlowLayout());
        setSize(200, 100);
        setTitle("Stay Awake");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        btn.addActionListener(this);

        add(lbl);
        add(dropdown);
        add(btn);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btn) {
            LocalTime endTime = getEndTime();
            LocalTime now = LocalTime.now();

            min = (int)MINUTES.between(now, endTime);
            lbl.setText(Integer.toString(min));

            dropdown.setVisible(false);
            remove(dropdown);

            btn.setVisible(false);
            remove(btn);

            stopBtn.addActionListener(this);
            add(stopBtn);

//           repaint();
//           revalidate();

//           try {
//               Robot robot = new Robot();
//
//               for(int i = 0; i < min; i++){
//                   robot.mouseWheel(0);
//                   robot.delay(60000);
//                   //Thread.sleep(60000);
//               }
//           } catch(Exception AWTException){
//           }
//
        } 
        if (e.getSource() == stopBtn) {
            System.exit(0);
        }

    }

    private LocalTime getEndTime(){
        String endTime = dropdown.getSelectedItem().toString();
        switch(endTime) {
            case "3:00pm":
                    return LocalTime.parse("15:00:00");
            case "3:30pm":
                    return LocalTime.parse("15:30:00");
            case "4:00pm":
                    return LocalTime.parse("14:00:00");
            case "4:30pm":
                    return LocalTime.parse("14:30:00");
            case "5:00pm":
                    return LocalTime.parse("17:00:00");
            case "5:30pm":
                    return LocalTime.parse("17:30:00");
            case "6:00pm":
                    return LocalTime.parse("18:00:00");
            case "6:30pm":
                    return LocalTime.parse("18:30:00");
            default:
                    return LocalTime.parse("00:00:00");
        }
    }
}
