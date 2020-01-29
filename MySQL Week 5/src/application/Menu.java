package application;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import dao.FoodOrderDao;
import dao.MemberDao;
import entity.FoodOrder;
import entity.Member;


public class Menu {
	
		private FoodOrderDao foodOrderDao = new FoodOrderDao();
		private MemberDao memberDao = new MemberDao();
		private Scanner scanner = new Scanner(System.in);
		private List<String> options = Arrays.asList("Display Food Orders", "Display a Food Order", "Create a Food Order", "Delete a Food Order",
														"Create a Member", "Delete a Member");

		public void start() {
			String select = "";
			
			do {
				printMenu();
				select = scanner.nextLine();
				
				
				try { 
					if (select.equals("1")) {
						displayFoodOrders();
					} else if (select.equals("2")) {
						displayFoodOrder();
					} else if (select.equals("3")) {
						createFoodOrder();
					} else if (select.equals("4")) {
						deleteFoodOrder();
					} else if (select.equals("5")) {
						createMember();
					} else if (select.equals("6")) {
						deleteMember();
					}
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
					
				System.out.println("Press enter to continue..");
				scanner.nextLine();
			} while (! select.equals("-1"));
		}
		
		private void printMenu() {
			System.out.println("Select and Option: \n ---------------------");
			for (int i = 0; i < options.size(); i++)
				System.out.println(i + 1 + ".) " + options.get(i));
		}
		
		private void displayFoodOrders() throws SQLException {
			List<FoodOrder> orders = foodOrderDao.getFoodOrder();
			for (FoodOrder order : orders) {
				System.out.println(order.getFoodOrderId() + ": " + order.getFoodName() + "  Food Type: " + order.getFoodType());
			}
		}
		
		private void displayFoodOrder() throws SQLException {
			System.out.println("Enter Food Order ID: ");
			int foodId = Integer.parseInt(scanner.nextLine());
			FoodOrder order = foodOrderDao.getFoodOrderById(foodId);
			System.out.println(order.getFoodOrderId() + ": " + order.getFoodName() + "  Food Type: " + order.getFoodType());
			for (Member member : order.getMembers()) {
				System.out.println("\tMemberID: " + member.getMemberId() + "  Name: " + member.getFirstName() + " " + member.getLastName());
			}
		}
		
		private void createFoodOrder() throws SQLException {
			System.out.println("Enter new food order: ");
			String foodName = scanner.nextLine();
			System.out.println("Enter food type: ");
			String foodType = scanner.nextLine();
			foodOrderDao.createFoodOrder(foodName, foodType);
		}
		
		private void createMember() throws SQLException {
			System.out.println("Enter new first name of new member: ");
			String firstName = scanner.nextLine();
			System.out.println("Enter last name of new mamber: ");
			String lastName = scanner.nextLine();
			System.out.println("Enter new ID for member: ");
			int foodId = Integer.parseInt(scanner.nextLine());
			memberDao.createNewMember(firstName, lastName, foodId);
			
		}
		
		private void deleteFoodOrder() throws SQLException {
			System.out.println("Enter food order ID to delete: ");
			int id = Integer.parseInt(scanner.nextLine());
			foodOrderDao.deleteFoodOrderById(id);
		}
		
		private void deleteMember() throws SQLException {
			System.out.println("Enter member ID that you would like to delete: ");
			int id = Integer.parseInt(scanner.nextLine());
			System.out.println("The member was deleted... ");
			memberDao.deleteMember(id);
		}
}
