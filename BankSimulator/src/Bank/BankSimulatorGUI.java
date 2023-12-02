package Bank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class BankSimulatorGUI {

    public static void createAndShowGUI() {
        JFrame frame = createFrame("Bank Simulator");
        
        JPanel panel = new JPanel(new GridLayout(2, 1));

        addButton(panel, "Functions for Employees", e -> FunctionsForEmployee.showEmployeeMenu());
        addButton(panel, "Functions for Companies", e -> FunctionsForCompany.showCompanyMenu());
        
        frame.getContentPane().add(panel, BorderLayout.CENTER);
    }
    
    private static void addButton(JPanel panel, String message, ActionListener listener)
	{
		JButton button = new JButton(message);
		button.addActionListener(listener);
		panel.add(button);
	}
    
    private static JFrame createFrame(String title) {
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
        return frame;
    }
}
