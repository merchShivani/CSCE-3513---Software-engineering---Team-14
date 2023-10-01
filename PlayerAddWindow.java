import java.awt.Color;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.event.KeyListener;

/*
=Stages of Player Add Window=
Stage 0 - Window is Closed
Stage 1 - Window is Open user has yet to enter Player ID
Stage 2 - User has entered Player ID
Stage 3 - ID was found and user prompted to hit enter to confirm
Stage 4 - User confirmed found ID - Jump to Stage 7
Stage 5 - ID was not found, user is prompted to enter new Code Name
Stage 6 - User hit enter to confirm new Code Name
Stage 7 - User Prompted to enter Equitment Number
Stage 8 - User Hit Enter on Equitment Prompt

Stage 3 and 4 will run and reset the window or Stage 5 and 6 will run both will not run in same cycle.
 */


public class PlayerAddWindow implements KeyListener
{
    // Java Swing Objects
    JFrame jFrame;
    JPanel jPanel;
    JButton playerIDButton;
    JTextField IDfield;
    JTextField codeNameField;
    JTextField equiptmentField;

    // Text for ID directions
    JLabel IDprompt;
    JLabel IDcontrols;
    JLabel equiptmentLabel;

    // Text for Code Name Directions
    JLabel codeNameFoundLabel;
    JLabel codeNameNeedLabel;
    JLabel codeNameControls;
    JLabel equiptmentControls;

    // Set Stage of the Window
    int stage;

    // Player ID String that will be converted into a Int
    String windowPlayerID;

    // Player Code Name
    String windowCodeName;

    // Equiptment Number
    String windowEquiptment;

    // Determines if the Window is open for the program
    boolean windowOpen = false;
    
    PlayerAddWindow()
    {
        windowPlayerID = "0";
        windowCodeName = "A";
        windowEquiptment = "0";

        stage = 0;

        // Jframe for new Window
        jFrame = new JFrame();
		jFrame.setSize(1080, 600);
        jFrame.setLocationRelativeTo(null);

        // First Text for Player Add Window
        IDprompt = new JLabel("Please Input Player's ID Number");
        IDprompt.setBounds(300, 0, 1080, 40);
        IDprompt.setFont(new Font("Georgia",Font.BOLD,30));
        IDprompt.setForeground(Color.white);
        jFrame.add(IDprompt);

        // Directions for ID Enter Field
        IDcontrols = new JLabel("Press 'Enter' to accept ID and 'Escape' to leave without making changes.");
        IDcontrols.setBounds(50, 50, 1080, 40);
        IDcontrols.setFont(new Font("Georgia",Font.BOLD,25));
        IDcontrols.setForeground(Color.white);
        jFrame.add(IDcontrols);

        // ID enter field
        IDfield = new JTextField();
        IDfield.setBounds(400,100,200,50);
        IDfield.setFont(new Font("Georgia",Font.BOLD,25));
        IDfield.addKeyListener(this);
        jFrame.add(IDfield);

        // ID was found message
        codeNameFoundLabel = new JLabel("ID found in database: Press 'Enter' to continue.");
        codeNameFoundLabel.setBounds(100, 150, 1080, 40);
        codeNameFoundLabel.setFont(new Font("Georgia",Font.BOLD,30));
        codeNameFoundLabel.setForeground(Color.white);
        jFrame.add(codeNameFoundLabel);
        codeNameFoundLabel.setVisible(false);

        // ID was not found, prompt for new Code Name
        codeNameNeedLabel = new JLabel("Please Input Player's Code Name");
        codeNameNeedLabel.setBounds(300, 150, 1080, 40);
        codeNameNeedLabel.setFont(new Font("Georgia",Font.BOLD,30));
        codeNameNeedLabel.setForeground(Color.white);
        jFrame.add(codeNameNeedLabel);
        codeNameNeedLabel.setVisible(false);

        // Directions for entering new Code Name
        codeNameControls = new JLabel("Press 'Enter' to accept code name and 'Escape' to leave without making changes.");
        codeNameControls.setBounds(50, 200, 1080, 40);
        codeNameControls.setFont(new Font("Georgia",Font.BOLD,25));
        codeNameControls.setForeground(Color.white);
        jFrame.add(codeNameControls);
        codeNameControls.setVisible(false);

        // Text field for Code Name
        codeNameField = new JTextField();
        codeNameField.setBounds(400,250,200,40);
        codeNameField.addKeyListener(this);
        codeNameField.setFont(new Font("Georgia",Font.BOLD,25));
        jFrame.add(codeNameField);
        codeNameField.setVisible(false);

        // Prompt for Equiptment
        equiptmentLabel = new JLabel("Please Input Player's Equiptment ID");
        equiptmentLabel.setBounds(300, 300, 1080, 40);
        equiptmentLabel.setFont(new Font("Georgia",Font.BOLD,30));
        equiptmentLabel.setForeground(Color.white);
        jFrame.add(equiptmentLabel);
        equiptmentLabel.setVisible(false);

        // Directions for entering new Equiptment ID
        equiptmentControls = new JLabel("Press 'Enter' to accept Equiptment ID");
        equiptmentControls.setBounds(300, 350, 1080, 40);
        equiptmentControls.setFont(new Font("Georgia",Font.BOLD,25));
        equiptmentControls.setForeground(Color.white);
        jFrame.add(equiptmentControls);
        equiptmentControls.setVisible(false);

        // Text field for Code Name
        equiptmentField = new JTextField();
        equiptmentField.setBounds(400,400,200,40);
        equiptmentField.addKeyListener(this);
        equiptmentField.setFont(new Font("Georgia",Font.BOLD,25));
        jFrame.add(equiptmentField);
        equiptmentField.setVisible(false);

        // Background color
        jPanel = new JPanel();
        jPanel.setBounds(10, 10, 30, 30);
        jPanel.setBackground(Color.blue);
        jFrame.add(jPanel);
    }

    @Override
    public void keyTyped(KeyEvent e) 
    {
       
    }

    @Override
    public void keyPressed(KeyEvent e) 
    {
       
    }

    // Keyboard Listner for Player Add Window, no repeat or hold key movements
    @Override
    public void keyReleased(KeyEvent e) 
    {
        // Escape will close the window and will result in no changes.
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE && (stage > 0 && stage < 7))
        {
            IDfield.setText("");
            codeNameField.setText("");
            IDfield.requestFocus();

            codeNameFoundLabel.setVisible(false);
            codeNameNeedLabel.setVisible(false);
            codeNameField.setVisible(false);
            codeNameControls.setVisible(false);
            stage = 0;
            windowOpen = false;
        }

        // User hits enter for Player ID
        if (e.getKeyCode() == KeyEvent.VK_ENTER && stage == 1)
        {
            windowPlayerID = IDfield.getText();

            // Check that the ID is an int, will not progress without correct type
            if (Integer.valueOf(windowPlayerID) instanceof Integer)
            {
                stage = 2;
            }
        }

        // Player ID was found
        if (e.getKeyCode() == KeyEvent.VK_ENTER && stage == 3)
        {
            stage = 4;
            equiptmentField.requestFocus();
            //codeNameFoundLabel.setVisible(false);
            //IDfield.setText("");
        }

        // Accepts CodeName and then closes window along with reseting the fields.
        if (e.getKeyCode() == KeyEvent.VK_ENTER && stage == 5)
        {
            windowCodeName = codeNameField.getText();
            stage = 6;
            equiptmentField.requestFocus();
        }

        if (e.getKeyCode() == KeyEvent.VK_ENTER && stage == 7)
        {
            windowEquiptment = equiptmentField.getText();
            stage = 8;

            //Clear Page for next cycle
            IDfield.setText("");
            codeNameField.setText("");
            equiptmentField.setText("");
            IDfield.requestFocus();

            codeNameNeedLabel.setVisible(false);
            codeNameFoundLabel.setVisible(false);
            codeNameField.setVisible(false);
            codeNameControls.setVisible(false);

            equiptmentControls.setVisible(false);
            equiptmentField.setVisible(false);
            equiptmentLabel.setVisible(false);
        }
    }

    // Checks the window is open and sets the visability for labels and text fields
    void update()
    {
        if (windowOpen == true)
        {
            jFrame.setVisible(true);
        }
        else
        {
            jFrame.setVisible(false);
        }

        if (stage == 3)
        {
            codeNameFoundLabel.setVisible(true);
        }

        if (stage == 5)
            {
            codeNameNeedLabel.setVisible(true);
            codeNameField.setVisible(true);
            codeNameControls.setVisible(true);
            codeNameField.requestFocus();
            }

        if (stage == 7)
        {
            equiptmentField.setVisible(true);
            equiptmentLabel.setVisible(true);
            equiptmentControls.setVisible(true);
            equiptmentField.requestFocus();
        }
    }
}
