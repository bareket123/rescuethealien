import javax.swing.*;

public class GameMain extends JFrame {
    public static void main(String[] args) {
      GameMain gameMain = new GameMain();


    }








    private static void optionPaneExample() {
        JFrame startButton = new JFrame();
        JFrame instruction = new JFrame();
        startButton.setLocationRelativeTo(null);
        instruction.setLocationRelativeTo(null);
        JOptionPane.showMessageDialog(startButton, "Hello, Welcome to 'ESCAPE'.");
        JOptionPane.showMessageDialog(instruction, "The rules are simple: " +
                "*You can not get out of the frame" +
                "\n *You must avoid the enemies" +
                "\n* in order to win the game you need to escape through the exit hole");
    }
    public GameMain(){
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setTitle("Rescue player");
        this.setResizable(false);
        FirstLevel firstLevel= new FirstLevel();
        firstLevel.setBounds(0,0,Constants.WINDOW_WIDTH,Constants.WINDOW_HEIGHT);
        this.add(firstLevel);
        PlayerMovement playerMovement = new PlayerMovement(firstLevel.getPlayer());
        this.addKeyListener(playerMovement);
        //SecondLevel secondLevel = new SecondLevel();
       // PlayerMovement playerMovement2=new PlayerMovement(secondLevel.getPlayer());
        //this.addKeyListener(playerMovement2);




    }
}