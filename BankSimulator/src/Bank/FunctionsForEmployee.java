package Bank;

import java.awt.*;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class FunctionsForEmployee {

	public static void showEmployeeMenu() {
		JFrame employeeFrame = createFrame("Employee Menu");

        JPanel employeePanel = new JPanel(new GridLayout(5, 1));

        addButton(employeePanel, "Add Employee", e -> showAddEmployeeFrame());
        addButton(employeePanel, "Add Salary", e -> showAddSalaryFrame());
        addButton(employeePanel, "Print Employees", e -> showMessage("Employee List", Employee.getEmployees()));
        addButton(employeePanel, "Financial Statement", e -> showFinancialStatementFrame());
        addButton(employeePanel, "Withdrawal Money", e -> showWithdrawalFrame());
        
        employeeFrame.getContentPane().add(employeePanel, BorderLayout.CENTER);
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
	
	private static void showMessage(String title, String message) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
    }

    private static void showAddEmployeeFrame() {
    	
        JFrame addEmployeeFrame = createFrame("Add Employees");
        JPanel addEmployeePanel = new JPanel(new GridLayout(4, 2));
        
        JTextField nameField = new JTextField();
        JTextField companyField = new JTextField();
        JTextField ibanField = new JTextField();
        
        JButton submitButton = new JButton("Submit");
        
        addEmployeePanel.add(new JLabel("Name:"));
        addEmployeePanel.add(nameField);
        addEmployeePanel.add(new JLabel("Company:"));
        addEmployeePanel.add(companyField);
        addEmployeePanel.add(new JLabel("IBAN:"));
        addEmployeePanel.add(ibanField);
        addEmployeePanel.add(submitButton);
        
        addEmployeeFrame.getContentPane().add(addEmployeePanel, BorderLayout.CENTER);
        
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String name = nameField.getText();
                String company = companyField.getText();
                int iban = Integer.parseInt(ibanField.getText());
                
                if (Company.getObject(company) == null) {
                	showMessage("Error", "The company doesnt exist!");
                }
                else if (Employee.getObject(iban) != null) {
                    showMessage("Error", "The IBAN is already used!");
                }
                else {
                	showMessage("Correct", "The employee was added!");
                    new Employee(name, company, iban);
                }
                
                addEmployeeFrame.dispose();
                
          }});
        
    }

    private static void showAddSalaryFrame() {
        
    	JFrame addSalaryFrame = createFrame("Add Employees");
        JPanel addSalaryPanel = new JPanel(new GridLayout(4, 2));
        
        JTextField salaryField = new JTextField();
        JTextField ibanField = new JTextField();

        JButton submitButton = new JButton("Submit");

        addSalaryPanel.add(new JLabel("Salary:"));
        addSalaryPanel.add(salaryField);
        addSalaryPanel.add(new JLabel("IBAN of the employee:"));
        addSalaryPanel.add(ibanField);
        addSalaryPanel.add(submitButton);

        addSalaryFrame.getContentPane().add(addSalaryPanel, BorderLayout.CENTER);
        
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int salary = Integer.parseInt(salaryField.getText());
                int iban = Integer.parseInt(ibanField.getText());
                Employee employee = Employee.getObject(iban);
                Company c = Company.getObject(employee.getCompany());
                
                if(salary < 1)
                {
                	JOptionPane.showMessageDialog(null, "The salary should be greater then 0", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else if(!c.substarctSalaryFromBudget(salary))
                {
                	JOptionPane.showMessageDialog(null, "The company doesnt have the money to pay the salary!", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                	JOptionPane.showMessageDialog(null, "The salary was payed!", "Correct", JOptionPane.PLAIN_MESSAGE);
                	employee.addSalary(salary);
                }
                
                
                addSalaryFrame.dispose();
          }});
    }

    private static void showFinancialStatementFrame() {
    	JFrame financialStatementFrame = createFrame("Add Employees");
        JPanel financialStatementPanel = new JPanel(new GridLayout(4, 2));
        
        JTextField ibanField = new JTextField();

        JButton submitButton = new JButton("Submit");

        financialStatementPanel.add(new JLabel("IBAN of the employee:"));
        financialStatementPanel.add(ibanField);
        financialStatementPanel.add(submitButton);
        
        financialStatementFrame.getContentPane().add(financialStatementPanel, BorderLayout.CENTER);
        
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int iban = Integer.parseInt(ibanField.getText());
                Employee employee = Employee.getObject(iban);
                String message;
                
                if (employee == null)
                {
                	message = "The iban doesnt exist";
                }
                else
                {
                	message = employee.getFinancialStatement();
                	message += "Total: " + employee.getBankBalance() + " lei";
                }
                
                JOptionPane.showMessageDialog(null, message);
                
                financialStatementFrame.dispose();
                
          }});
    }

    private static void showWithdrawalFrame() {
    	JFrame withdrawalFrame = createFrame("Withdrawal");
        JPanel withdrawalPanel = new JPanel(new GridLayout(4, 2));
        
        JTextField ibanField = new JTextField();
        JTextField amountField = new JTextField();

        JButton submitButton = new JButton("Submit");

        withdrawalPanel.add(new JLabel("IBAN of the employee:"));
        withdrawalPanel.add(ibanField);
        withdrawalPanel.add(new JLabel("Amount:"));
        withdrawalPanel.add(amountField);
        withdrawalPanel.add(submitButton);

        withdrawalFrame.getContentPane().add(withdrawalPanel, BorderLayout.CENTER);
        
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int iban = Integer.parseInt(ibanField.getText());
                int amount = Integer.parseInt(ibanField.getText());
                Employee employee = Employee.getObject(iban);
                String message;
                
                if (employee == null)
                {
                	message = "The iban doesnt exist";
                }
                else if(amount < 1)
                {
                	message = "The amount should be greater then 0";
                }
                else if(employee.getBankBalance() - amount < 0)
                {
                	message = "Not enough money in the account";
                }
                else
                {
                	employee.withdrawalOfMoney(amount);
                	message = "The withdrawal was succesuful!";
                }
                
                JOptionPane.showMessageDialog(null, message);
                
                withdrawalFrame.dispose();
                
          }});
    }
}
