package Bank;

import java.util.ArrayList;
import java.util.List;

public class Employee {
	private static List<Employee> allEmployees = new ArrayList<>();
	private int bankBalance, iban;
	private String name, company, financialStatement;

	public Employee(String name, String company, int iban) {
		this.bankBalance = 0;
		this.name = name;
		this.company = company;
		this.iban = iban;
		this.financialStatement = "+ Bank Balance at start of: " + bankBalance + "\n";
		allEmployees.add(this);
	}

	public int getBankBalance() {
		return bankBalance;
	}

	public String getName() {
		return name;
	}

	public String getCompany() {
		return company;
	}

	public int getIban() {
		return iban;
	}

	public String getFinancialStatement() {
		return financialStatement;
	}

	public static Employee getObject(int iban) {
		for (Employee e : allEmployees) {
			if (e.getIban() == iban) {
				return e;
			}
		}

		return null;
	}

	public static String getEmployees() {

		String message = "List of Employees \n";
		for (Employee employee : allEmployees) {
			message += "Name: " + employee.getName() + ", Bank Balance: " + employee.getBankBalance()
					+ " lei, Company: " + employee.getCompany() +'\n';
		}
		
		return message;
	}

	public void addSalary(int amount) {
		this.bankBalance += amount;
		this.financialStatement += "+ Salary from " + this.company + " with the value of: " + amount + "\n";
	}

	public void withdrawalOfMoney(int amount) {
		bankBalance -= amount;
		this.financialStatement += "- Withdrawal with the value of: " + amount + "\n";
	}
}
