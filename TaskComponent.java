import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class TaskComponent extends JPanel implements ActionListener{
      private JCheckBox checkBox;
      private JTextPane taskField;  
      private JButton deleteButton;

      public JTextPane getTaskField() {
         return taskField;
      }

      // this panel is used to make updates to the task panel when deleting tasks
      private JPanel parentPanel;

      public TaskComponent(JPanel parentPanel){
         this.parentPanel = parentPanel;

         // task field
         taskField = new JTextPane();
         taskField.setPreferredSize(commonConstants.TASKFIELD_SIZE);
         taskField.setContentType("text/html");

         //check box
         checkBox = new JCheckBox();
         checkBox.setPreferredSize(commonConstants.CHECKBOX_SIZE);
         checkBox.addActionListener(this);

         //delete button
         deleteButton = new JButton("X");
         deleteButton.setPreferredSize(commonConstants.DELETE_BUTTON_SIZE);
         deleteButton.addActionListener(this);

         // add to this task component
         add(checkBox); 
         add(taskField);
         add(deleteButton);

      }

      public void actionPerformed(ActionEvent e){
         if(checkBox.isSelected()){
            //replaces all html tags to empty string to grab the main text
            String taskText = taskField.getText().replaceAll("<[^>]*>", "");

            // add strike through text
            taskField.setText("<html><s>" + taskText + "</s></html>");  
         }
         else if(!checkBox.isSelected()){
            String taskText = taskField.getText().replaceAll("<[^>]*>", "");

            taskField.setText(taskText);
         }

         if(e.getActionCommand().equalsIgnoreCase("X")){
            // delete this component from the parent panel
            parentPanel.remove(this);
            parentPanel.repaint();
            parentPanel.revalidate();
         }
      }

}
