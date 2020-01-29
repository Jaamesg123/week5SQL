package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Member;

public class MemberDao {
	
		private Connection connection;
		private final String GET_MEMBERS_BY_FOOD_ORDERS_ID_QUERY = "SELECT * FROM members WHERE food_id = ?";
		private final String DELETE_MEMBERS_BY_TEAM_ID_QUERY = "DELETE FROM members WHERE food_id = ?";
		private final String CREATE_NEW_MEMBER_QUERY = "INSERT INTO members(first_name, last_name, food_id) VALUES(?,?,?)";
		private final String DELETE_MEMBER_BY_ID_QUERY = "DELETE FROM members WHERE id = ?";
		
		public MemberDao() {
			connection = DBConnection.getConnection();
			
		}
	

	public List<Member> getMembersByFoodOrder(int foodOrderId) throws SQLException {
		PreparedStatement ps = connection.prepareCall(GET_MEMBERS_BY_FOOD_ORDERS_ID_QUERY);
		ps.setInt(1, foodOrderId);
		ResultSet rs = ps.executeQuery();
		List<Member> members = new ArrayList<Member>();
		
		while (rs.next()) {
			members.add(new Member(rs.getInt(1), rs.getString(2), rs.getString(3)));
		}
		
		return members;
	}
	
	public void createNewMember(String firstName, String lastName, int foodId) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(CREATE_NEW_MEMBER_QUERY);
		ps.setString(1, firstName);
		ps.setString(2, lastName);
		ps.setInt(3, foodId);
		ps.executeUpdate();
	}
	
	public void deleteMember(int id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(DELETE_MEMBER_BY_ID_QUERY);
		ps.setInt(1, id);
		ps.executeUpdate();
	}
	
	public void deleteMembersByFoodOrderId(int foodOrderId) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(DELETE_MEMBERS_BY_TEAM_ID_QUERY);
		ps.setInt(1, foodOrderId);
		ps.executeUpdate();
	}

}
