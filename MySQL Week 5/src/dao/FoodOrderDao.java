package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.FoodOrder;

public class FoodOrderDao {
	
	private Connection connection;
	private MemberDao memberDao;
	private final String GET_ORDER_QUERY = "SELECT * FROM food_orders";
	private final String GET_FOOD_ORDERS_BY_ID_QUERY = "SELECT * FROM food_orders WHERE id = ?";
	private final String CREATE_NEW_FOOD_ORDER_QUERY = "INSERT INTO food_orders(food_name, food_type) VALUES(?,?)";
	private final String DELETE_FOOD_ORDER_BY_ID_QUERY = "DELETE FROM food_orders WHERE id = ?";
	
	public FoodOrderDao() {
		connection = DBConnection.getConnection();
		memberDao = new MemberDao();
		
	}
	
	public List<FoodOrder> getFoodOrder() throws SQLException {
		ResultSet rs = connection.prepareStatement(GET_ORDER_QUERY).executeQuery();
		List<FoodOrder> orders = new ArrayList<FoodOrder>();
		
		while (rs.next()) {
			orders.add(populateFoodOrder(rs.getInt(1), rs.getString(2), rs.getString(3)));
		}
		return orders;
	}
	
	public FoodOrder getFoodOrderById(int id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(GET_FOOD_ORDERS_BY_ID_QUERY);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		rs.next();
		return populateFoodOrder(rs.getInt(1), rs.getString(2), rs.getString(3));
	}
	
	public void createFoodOrder(String foodName, String foodType) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(CREATE_NEW_FOOD_ORDER_QUERY);
		ps.setString(1, foodName);
		ps.setString(2, foodType);
		ps.executeUpdate();
	}
	
	public void deleteFoodOrderById(int id) throws SQLException {
		memberDao.deleteMembersByFoodOrderId(id);
		PreparedStatement ps = connection.prepareStatement(DELETE_FOOD_ORDER_BY_ID_QUERY);
		ps.setInt(1, id);
		ps.executeUpdate();
	}
	
	private FoodOrder populateFoodOrder(int foodOrderId, String foodName, String foodType) throws SQLException {
		return new FoodOrder(foodOrderId, foodName, foodType, memberDao.getMembersByFoodOrder(foodOrderId));
		
	}

}
