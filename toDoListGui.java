import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class toDoListGui extends JFrame implements ActionListener {

   // taskPanel will act as the container for the taskComponentPanel
   // taskComponentPanel will store all the taskComponents
   private JPanel taskPanel, taskComponentPanel;

   public toDoListGui() {
      super("TASKIFY");
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      setPreferredSize(commonConstants.GUI_SIZE);
      pack();
      setLocationRelativeTo(null);
      setResizable(false);
      setLayout(null);

      addGuiComponents();
   }

   private void addGuiComponents(){
      // banner text
      JLabel bannerLabel = new JLabel("To-Do's");
      bannerLabel.setFont(createFont("resources/LEMONMilk-Light.otf", 36f));
         bannerLabel.setBounds(
            (commonConstants.GUI_SIZE.width - bannerLabel.getPreferredSize().width)/2,
            15,
            commonConstants.BANNER_SIZE.width,
            commonConstants.BANNER_SIZE.height
         );


         // taskpanel
         taskPanel = new JPanel();

         // taskcomponentpanel
         taskComponentPanel = new JPanel();
         taskComponentPanel.setLayout(new BoxLayout(taskComponentPanel, BoxLayout.Y_AXIS));
         taskPanel.add(taskComponentPanel);

         // add scrolling to the task panel
         JScrollPane scrollPane = new JScrollPane(taskPanel);
         scrollPane.setBounds(8, 70, commonConstants.TASKPANEL_SIZE.width, commonConstants.TASKPANEL_SIZE.height);
         scrollPane.setBorder(BorderFactory.createLoweredBevelBorder());
         scrollPane.setMaximumSize(commonConstants.TASKPANEL_SIZE);
         scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
         scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

         // changing the scrolling speed
         JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
         verticalScrollBar.setUnitIncrement(20);

         // add task button
         JButton addTaskButton = new JButton("Add Text");
         addTaskButton.setFont(createFont("resources/LEMONMilk-Light.otf", 26f));
         addTaskButton.setBounds(-5, commonConstants.GUI_SIZE.height - 88,
                                 commonConstants.ADDTASK_BUTTON_SIZE.width, commonConstants.ADDTASK_BUTTON_SIZE.height);
         addTaskButton.addActionListener(this);

         // add to a frame
         this.getContentPane().add(bannerLabel);
         this.getContentPane().add(scrollPane);
         this.getContentPane().add(addTaskButton);

   }

   private Font createFont(String resource, float size){
      //get font file path
      String filePath = getClass().getClassLoader().getResource(resource).getPath();

      // check to see if the path contain folder with spaces in them
      if(filePath.contains("%20")){
         filePath = getClass().getClassLoader().getResource(resource).getPath()
         .replaceAll("%20", " ");

      }
      // create font
      try{
         File customFontFile = new File(filePath);
         Font customFont = Font.createFont(Font.TRUETYPE_FONT, customFontFile).deriveFont(size);
         return customFont; 
      }catch(Exception e){
         System.out.println("Error" + e);
      }
      return null;
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      // Handle the action event
      String command = e.getActionCommand();
      if (command.equalsIgnoreCase("Add Text")) {
      // create a taskcomponent
      TaskComponent taskComponent = new TaskComponent(taskComponentPanel);
      taskComponentPanel.add(taskComponent);

      // make the task field request focus after creation
         taskComponent.getTaskField().requestFocus();
         repaint();
         revalidate();


      }
   }
}