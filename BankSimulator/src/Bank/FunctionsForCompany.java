package Bank;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FunctionsForCompany {
	
	public static void showCompanyMenu() {
        JFrame companyFrame = createFrame("Company Menu");

        JPanel companyPanel = new JPanel(new GridLayout(3, 1));
        
        addButton(companyPanel, "Add Company", e -> showAddCompany());
        addButton(companyPanel, "Print Companies", e -> showPrintCompanies());
        addButton(companyPanel, "Change budget", e -> showChangeBudget());

        companyFrame.getContentPane().add(companyPanel, BorderLayout.CENTER);
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
        return frame;
    }
	
	private static void showAddCompany()
	{
		JFrame addCompanyFrame = createFrame("Add Company");

        JPanel addCompanyPanel = new JPanel(new GridLayout(4, 2));

        JTextField cuiField = new JTextField();
        JTextField budgetField = new JTextField();
        JTextField nameField = new JTextField();

        JButton submitButton = new JButton("Submit");

        addCompanyPanel.add(new JLabel("CUI:"));
        addCompanyPanel.add(cuiField);
        addCompanyPanel.add(new JLabel("Budget for wages:"));
        addCompanyPanel.add(budgetField);
        addCompanyPanel.add(new JLabel("Name of the company:"));
        addCompanyPanel.add(nameField);
        addCompanyPanel.add(submitButton);

        addCompanyFrame.getContentPane().add(addCompanyPanel, BorderLayout.CENTER); 
        
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int cui = Integer.parseInt(cuiField.getText());
                int budget = Integer.parseInt(budgetField.getText());
                String name= nameField.getText();
                
                addCompanyFrame.dispose();
                addCompanyFrame.dispose();
                
                new Company(cui, budget, name);
            }
        });
	}
	
	private static void showPrintCompanies()
	{
		String message = Company.getCompanies();
		JOptionPane.showMessageDialog(null, message);
	}
	
	private static void showChangeBudget()
	{
		JFrame addCompanyFrame = createFrame("Change budget");

        JPanel addCompanyPanel = new JPanel(new GridLayout(3, 2));

        JTextField nameField = new JTextField();
        JTextField budgetField = new JTextField();

        JButton submitButton = new JButton("Submit");

        addCompanyPanel.add(new JLabel("Name:"));
        addCompanyPanel.add(nameField);
        addCompanyPanel.add(new JLabel("New Budget for wages:"));
        addCompanyPanel.add(budgetField);
        addCompanyPanel.add(submitButton);

        addCompanyFrame.getContentPane().add(addCompanyPanel, BorderLayout.CENTER); 
        
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String name = nameField.getText();
                int budget = Integer.parseInt(budgetField.getText());
                Company company = Company.getObject(name);
                
                if(budget < 0)
                {
                	JOptionPane.showMessageDialog(null, "The budget must be greater then 0");
                }
                else if(company == null)
                {
                	JOptionPane.showMessageDialog(null, "The company doesnt exist");
                }
                else
                {
                	company.changeBudget(budget);
                }
                
                addCompanyFrame.dispose();
                addCompanyFrame.dispose();
            }
        });
	}
}
