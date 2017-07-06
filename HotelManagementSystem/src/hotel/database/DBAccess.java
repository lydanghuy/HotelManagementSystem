package hotel.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.TimeUnit;
import hotel.Management;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DBAccess {

		private static Connection conn = null;
		private static ResultSet rs = null;
		private static PreparedStatement pstmt = null;		
		
		public DBAccess() throws SQLException{
			try{
				//get Connection to Database 
				conn = DBUtil.getConnection();
		}	catch (SQLException e){
				DBUtil.showErrorMessage(e);
			}
		}
		
		public static List<Integer> room_list ;
		
		public static List<String> checkreservation(int RID) throws SQLException{
			List<String> checklist = new ArrayList<String>();
			
	
			String sql= "select m.room_type, m.bed_type, g.first_name, r.check_in, r.check_out, r.adults, r.children, b.total_fee"
					 + " from guest g, reservation r, room m, bill b where r.room_id = m.room_id and g.guest_id = r.guest_id "
					 + "and r.reservation_id = ? and b.reservation_id = r.reservation_id";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, RID);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				checklist.add(rs.getString(1)); //room type
				checklist.add(rs.getString(2)); //bed type
				checklist.add(rs.getString(3)); // first name
				checklist.add(rs.getString(4)); // arrival date
				checklist.add(rs.getString(5)); // departure date
				checklist.add(rs.getString(6)); // adults
				checklist.add(rs.getString(7)); //children
				checklist.add(rs.getString(8)); //total_fee
			}
			return checklist;
			
		}
		public static List<String> showReservationandBill(int guest_id, int room_number, String arrival_date) throws SQLException{
			List<String> checklist = new ArrayList<String>();
			String sql = "select g.first_name, b.bill_id, b.total_fee, r.reservation_id from bill b, reservation r, guest g "
					+ "where r.guest_id = ? and g.guest_id = r.guest_id and r.room_id = ? and r.check_in = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, guest_id);
			pstmt.setInt(2, room_number);
			pstmt.setString(3, arrival_date);
			rs = pstmt.executeQuery();
			while(rs.next()){
				checklist.add(rs.getString(1)); //first_name
				checklist.add(rs.getString(2)); //bill id
				checklist.add(rs.getString(3)); //  total fee
				checklist.add(rs.getString(4)); // reservation ID
			}
			return checklist;
		}
		public static ObservableList<Management> getAllInfo() throws SQLException {
			
				String sql = "select g.guest_id, g.last_name, g.age, g.email, g.country, r.room_id, m.room_type, m.bed_type, "
						+ "r.check_in, r.check_out, r.adults, r.children from guest g, reservation r, room m "
						+ "where g.guest_id = r.guest_id and r.room_id = m.room_id";
			
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				ObservableList<Management> list = FXCollections.observableArrayList();
				
				while(rs.next()){
					int guestid = rs.getInt(1);
					String name = rs.getString(2);
		            int age = rs.getInt(3);
		            String email = rs.getString(4);
		            String country = rs.getString(5);
		            int roomnumber = rs.getInt(6);
		            String roomtype = rs.getString(7);
		            String bedtype = rs.getString(8);
		            String checkin = rs.getString(9);
		            String checkout = rs.getString(10);
		            int adults = rs.getInt(11);
		            int children = rs.getInt(12);
		            list.add(new Management(guestid,name, age,email,country,roomnumber, roomtype, bedtype, checkin, checkout, adults, children));
		   
				}
				
				return list;
		}
		
		public static void delete_rows(int RID) throws SQLException{
			
			String dml = "delete from bill where bill_id = ?"; //delete in bill table
			pstmt = conn.prepareStatement(dml);
			pstmt.setInt(1, RID);
			pstmt.execute();
			
			String dml2 = "delete from reservation where reservation_id = ?"; //delete in reservation table
			pstmt = conn.prepareStatement(dml2);
			pstmt.setInt(1, RID);
			pstmt.execute();
		
			
		}
		
		public static int checkIdentity(int RID, int room_no, String last_name) throws SQLException{
			
			String sql = "select r.reservation_id, r.room_id, g.last_name from reservation r, guest g where r.guest_id = r.guest_id ";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			int check = 0;
			while(rs.next()){
				if(rs.getInt(1) == RID && rs.getInt(2) == room_no && rs.getString(3).equals(last_name)){
					check = 1;
					break;
				}
			}
			if(check == 1) return 1; // Match Identity
			else return 0; // Not match
		}
		public static int checkReservationID(int RID) throws SQLException{
			
			String sql = "select reservation_id from reservation";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			int check = 0;
			while(rs.next()){
				if(rs.getInt(1) == RID) {
					check = 1;
					break;
				}
			}
			if(check == 0) return 1; //RID is accepted
			else return 0; //RID is duplicated, need to change
			
			
			
		}
		public static int insert_rows(String first_name, String last_name, int age, String country, String email, int RID,
				int room_no, String arrival, String departure, String adults, String children, double total_fee) throws SQLException{
			
			int already = 0;// check if guest is already in database or not.
			int result1 = 0, result2, result3, guest_id = 0;
			
			
			String compare_data= "select * from guest";
			pstmt = conn.prepareStatement(compare_data);
			rs = pstmt.executeQuery();
			while(rs.next()){
				if(rs.getString(2).equals(first_name) && rs.getString(3).equals(last_name) && rs.getInt(4) == age &&
						rs.getString(5).equals(country) && rs.getString(6).equals(email)){
					already = 1; result1 = 1; //guest info already in database
					break;
				}
			}
			
			if(already == 0){
			
				String sql = "insert into guest (first_name, last_name, age, country, email) values (?,?,?,?,?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, first_name);
				pstmt.setString(2, last_name);
				pstmt.setInt(3, age);
				pstmt.setString(4, country);
				pstmt.setString(5, email);
				result1 = pstmt.executeUpdate();//= 1 -> insert successful
			}
			
			String sql2 = "select guest_id from guest where first_name = ? and last_name = ? and age = ? "
						   + "and country = ? and email = ?"; //choose only 1 guest_id to insert to reservation table
			pstmt = conn.prepareStatement(sql2);
			pstmt.setString(1, first_name);
			pstmt.setString(2, last_name);
			pstmt.setInt(3, age);
			pstmt.setString(4, country);
			pstmt.setString(5, email);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				guest_id = rs.getInt(1);
			}
			
			String insert_reservation = "insert into reservation values (?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(insert_reservation);
			pstmt.setInt(1, RID);
			pstmt.setInt(2, guest_id);
			pstmt.setInt(3, room_no);
			pstmt.setString(4, arrival);
			pstmt.setString(5, departure);
			pstmt.setString(6, adults);
			pstmt.setString(7, children);
			result2 = pstmt.executeUpdate();//= 1 -> insert successful
			
			String insert_bill = "insert into bill values(?,?,?,?)";
			pstmt = conn.prepareStatement(insert_bill);
			pstmt.setInt(1, RID); //bill_id = reservation_id
			pstmt.setInt(2, guest_id);
			pstmt.setInt(3, RID);
			pstmt.setDouble(4, total_fee);
			result3 = pstmt.executeUpdate();//= 1 -> insert successful
			
		
			if(result1 == 1 && result2 == 1 && result3 ==1) return 1; //insert successfully 3 tables
			else return 0;

			
		}
		
		
		//search available rooms
		public static int search_Available_Room(String room_type, String bed_type, LocalDate input_arrival, LocalDate input_departure) throws SQLException{
			
			/* In hotel database, room order:
			STANDARD ROOMS: 101->115 -> (Single:101-105 Twins:106-110, Double:111-115)
			DELUXE ROOMS: 201->215   -> 		same
			SUITE ROOMS: 301->315    -> 		same         */
			int small = 0,big = 0; // range of room need to take, initialize them = 0
			int no_of_rows ;
			
			
			if(room_type == "Standard"){
				if(bed_type == "Single") {
					small = 100; big = 106;
				}
				else if(bed_type == "Twins"){
					small = 105; big = 111;
				}
				else if(bed_type == "Double"){
					small = 110; big = 116;
				}
					
			}
			else if(room_type == "Deluxe"){
				if(bed_type == "Single") {
					small = 200; big = 206;
				}
				else if(bed_type == "Twins"){
					small = 205; big = 211;
				}
				else if(bed_type == "Double"){
					small = 210; big = 216;
				}
			}
			else{
				if(bed_type == "Single") {
					small = 300; big = 306;
				}
				else if(bed_type == "Twins"){
					small = 305; big = 311;
				}
				else if(bed_type == "Double"){
					small = 310; big = 316;
				}
			}
			//Convert LocalDate to String
			String arrival_date = input_arrival.toString();
			String departure_date = input_departure.toString();
			
			
			String sql ="select room_id from room r where not exists (select * from reservation where room_id = r.room_id "
					+ "and check_in <= ? and check_out >= ?) and room_id < ? and room_id > ?";
	
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, departure_date);
			pstmt.setString(2, arrival_date);
			pstmt.setInt(3, big);
			pstmt.setInt(4, small);
			rs = pstmt.executeQuery();
			
			room_list = new ArrayList<Integer>();
			 
			while(rs.next()){
				room_list.add(rs.getInt(1));
			} 
			
			rs.last();
			no_of_rows = rs.getRow();
			
			if(no_of_rows > 0) return 1; //there are available rooms (at least 1)
			else return 0;               //no available rooms
			
			
			
		}
		
}

	


