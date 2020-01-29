package entity;

import java.util.List;

public class FoodOrder {

		private int foodOrderId;
		private String foodName;
		private String foodType;
		private List<Member> members;
		
		public FoodOrder(int foodOrderId, String foodName, String foodType, List<Member> members) {
			this.setFoodOrderId(foodOrderId);
			this.setFoodName(foodName);
			this.setFoodType(foodType);
			this.setMembers(members);
		}


		public int getFoodOrderId() {
			return foodOrderId;
		}


		public void setFoodOrderId(int foodOrderId) {
			this.foodOrderId = foodOrderId;
		}


		public String getFoodName() {
			return foodName;
		}


		public void setFoodName(String foodName) {
			this.foodName = foodName;
		}


		public String getFoodType() {
			return foodType;
		}


		public void setFoodType(String foodType) {
			this.foodType = foodType;
		}


		public List<Member> getMembers() {
			return members;
		}


		public void setMembers(List<Member> members) {
			this.members = members;
		}


		
}
