package Bank;

import java.util.ArrayList;
import java.util.List;

public class Company {
	private static List<Company> allCompanies = new ArrayList<>();
	private int cui, budget;
	String name;

	public Company(int cui, int budget, String name) {
		this.cui = cui;
		this.budget = budget;
		this.name = name;
		allCompanies.add(this);
	}

	public int getBudget() {
		return budget;
	}

	public String getName() {
		return name;
	}

	public int getCui() {
		return cui;
	}
	
	public static String getCompanies() {

		String message = "List of Companies:\n";
		for (Company company : allCompanies) {
			message += "Name: " + company.getName() + ", Budget for Wages: " + company.getBudget() + " lei, CUI: "
					+ company.getCui() +'\n';
		}
		
		return message;
	}

	public static Company getObject(String name) {
		for (Company c : allCompanies) {
			if (c.getName().equals(name)) {
				return c;
			}
		}

		return null;
	}

	public void changeBudget(int amount) {
		
		this.budget = amount;
	}

	public boolean substarctSalaryFromBudget(int amount) {
		if (this.budget - amount < 0) {
			return false;
		} else {
			this.budget -= amount;
			return true;
		}
	}
}
