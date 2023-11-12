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
Stage 2 - User hit Enter, ID was not found, user is prompted to enter new Code Name
Stage 3 - Enter Equitment Number
Stage 4 - Add Player to Game in Model and clean Player Add Window
 */


public class PlayerAddWindow implements KeyListener
{
    // Java Swing Objects
    JFrame jFrame;
    JPanel jPanel;
    JButton playerIDButton;
    JTextField  IDfield,
                codeNameField,
                equipmentField;
    // Text for ID directions
    JLabel  IDprompt,
            IDcontrols,
            equipmentLabel;
    // Text for Code Name Directions
    JLabel  codeNameFoundLabel, 
            codeNameNeedLabel, 
            codeNameControls,
            equipmentControls;

    // Set Stage of the Window
    int stage;

    // ID Found
    boolean idFound = false;

    // Player ID String that will be converted into a Int
    String windowPlayerID;

    // Player Code Name
    String windowCodeName;
    String codeNameIn;

    // Equipment Number
    String windowEquipment;

    // Equiptment Pause
    boolean keyPause = false;

    // Determines if the Window is open for the program
    boolean windowOpen = false;

    int yPosIterator;
    int yOffsetShift = 50;
    int xOffsetControl = 100;
    int xOffsetLabel = 300;
    String controlsString = "'Enter': accept value ; 'Escape': leave without saving";
    
    PlayerAddWindow()
    {
        windowPlayerID = "-1";
        windowCodeName = "";
        windowEquipment = "-1";
        
        yPosIterator = 0;

        stage = 0;

        // Jframe for new Window
        jFrame = new JFrame();
		jFrame.setSize(1080, 600);
        jFrame.setLocationRelativeTo(null);

        // Player Add Window, Step 1: ID
        IDcontrols = labelRender(xOffsetControl, yPosIterator, true, controlsString);
        IDprompt = labelRender( xOffsetLabel, yPosIterator, true,
            "Please Input Player's ID Number"); 
        IDfield = fieldRender(400, yPosIterator, true); // ID # enter field

        // ID was found message
        codeNameFoundLabel = labelRender(xOffsetLabel, yPosIterator, false,
            "ID found in database.");

        // ID not found message, Prompt for Code Name
        codeNameNeedLabel = labelRender(xOffsetLabel, yPosIterator, false,
            "Please Input Player's Code Name");
        codeNameControls = labelRender(xOffsetControl, yPosIterator, false, controlsString);
        codeNameField = fieldRender(400, yPosIterator, false); // Code name enter field

        // Prompt for Equipment
        equipmentLabel = labelRender(xOffsetLabel, yPosIterator, false,
            "Please Input Player's Equipment ID");
        equipmentControls = labelRender(xOffsetControl, yPosIterator, false, controlsString);
        equipmentField = fieldRender(400, yPosIterator, false); // Equipment # enter field

        // Background color
        jPanel = new JPanel();
        jPanel.setBounds(10, 10, 30, 30);
        jPanel.setBackground(Color.blue);
        jFrame.add(jPanel);
    }

    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyPressed(KeyEvent e) {}
    // Keyboard Listner for Player Add Window, no repeat or hold key movements
    @Override
    public void keyReleased(KeyEvent e) 
    {
        // Escape will close the window and will result in no changes.
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE && (stage > 0))
        {
            //Clear Page for next cycle
            IDfield.setText("");
            codeNameField.setText("");
            equipmentField.setText("");
            IDfield.requestFocus();

            codeNameNeedLabel.setVisible(false);
            codeNameFoundLabel.setVisible(false);
            codeNameField.setVisible(false);
            codeNameControls.setVisible(false);

            equipmentControls.setVisible(false);
            equipmentField.setVisible(false);
            equipmentLabel.setVisible(false);

            keyPause = false;
            idFound = false;

            yOffsetShift = 0;
            stage = 0;
            windowOpen = false;
        }

        // User hits enter for Player ID Stage 1
        if (e.getKeyCode() == KeyEvent.VK_ENTER && stage == 1)
        {
            windowPlayerID = IDfield.getText();

            // Check that the ID is an int, will not progress without correct type
            if (Integer.valueOf(windowPlayerID) instanceof Integer)
            {
                // Check if the name is in the database
                codeNameIn = "";
                codeNameIn = SupabaseOperations.read("id", windowPlayerID).getCodeName();

                // Codename is found in Database Skip Entering New ID
                if (codeNameIn.length() > 0) 
                {
                    idFound = true;
                    windowCodeName = codeNameIn;
                    stage = 3;
                }
                // ID not found in database
                else
                {
                    stage = 2;
                }
            }
        }

        // Accepts CodeName and then closes window along with reseting the fields.
        if (e.getKeyCode() == KeyEvent.VK_ENTER && stage == 2)
        {
            windowCodeName = codeNameField.getText();
            if(windowCodeName.length() > 0) {
                stage = 3;
            }
        }

        // Equitment ID Prompt
        if (e.getKeyCode() == KeyEvent.VK_ENTER && stage == 3 && keyPause == true)
        {
            windowEquipment = equipmentField.getText();
            if (Integer.valueOf(windowEquipment) > 0)
            {
            stage = 4;
            IDfield.setText("");
            codeNameField.setText("");
            equipmentField.setText("");
            IDfield.requestFocus();

            codeNameNeedLabel.setVisible(false);
            codeNameFoundLabel.setVisible(false);
            codeNameField.setVisible(false);
            codeNameControls.setVisible(false);

            equipmentControls.setVisible(false);
            equipmentField.setVisible(false);
            equipmentLabel.setVisible(false);

            keyPause = false;
            idFound = false;

            yOffsetShift = 0;
            }
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

        if (idFound)
        {
            codeNameFoundLabel.setVisible(true);
        }


        if (stage == 2)
        {
            codeNameNeedLabel.setVisible(true);
            codeNameField.setVisible(true);
            codeNameControls.setVisible(true);
            codeNameField.requestFocus();
        }

        if (stage == 3)
        {
            equipmentField.setVisible(true);
            equipmentLabel.setVisible(true);
            equipmentControls.setVisible(true);
            equipmentField.requestFocus();
            keyPause = true;
        }
    }
    public JLabel labelRender(int x, int y, boolean visible, String displayString) 
    {
        JLabel label = new JLabel(displayString);
        label.setBounds(x, y, 1080, 40);
        label.setFont(new Font("Georgia",Font.BOLD,30));
        label.setForeground(Color.white);
        jFrame.add(label);
        if(visible == false){label.setVisible(false);}
        yPosIterator += yOffsetShift;
        return label;
    }
    public JTextField fieldRender(int x, int y, boolean visible) 
    {
        JTextField field = new JTextField();
        field.setBounds(x,y,200,50);
        field.setFont(new Font("Georgia",Font.BOLD,25));
        field.addKeyListener(this);
        jFrame.add(field);
        if(visible == false) {field.setVisible(false);}
        yPosIterator += yOffsetShift;
        return field;
    }
}
