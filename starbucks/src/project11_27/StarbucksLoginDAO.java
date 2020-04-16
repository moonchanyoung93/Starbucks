package project11_27;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

public class StarbucksLoginDAO {

//	public String searchPassword (String userid, String phone, String sex) {
//		String password= null;
//		Connection conn=null;
//		PreparedStatement pstmt=null;
//		ResultSet rs=null;
//		try {
//			conn=DB.dbConn();
//			String sql=" select password from starbuckslogin where userid=? and phone=? and sex=? ";
//			pstmt=conn.prepareStatement(sql);
//			pstmt.setString(1, userid);
//			pstmt.setString(2, phone);
//			pstmt.setString(3, sex);
//			rs=pstmt.executeQuery();
//			if(rs.next()) {
//				password=rs.getString("password");
//			}
//			System.out.println(password);
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if(rs!=null) rs.close();
//			} catch (Exception e2) {
//				e2.printStackTrace();
//			} try {
//				if(pstmt!=null) pstmt.close();
//			} catch (Exception e2) {
//				e2.printStackTrace();
//			} try {
//				if(conn!=null) conn.close();
//			} catch (Exception e2) {
//				e2.printStackTrace();
//			}
//		}//finally
//		return password; //백터 리턴
//	}//end
	
	public StarbucksLoginDTO searchPassword (String userid, String phone, String sex) {
		StarbucksLoginDTO dto=null;
//		boolean a= true;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=DB.dbConn();
			String sql="select * from starbuckslogin where userid=? and phone=? and sex=? ";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			pstmt.setString(2, phone);
			pstmt.setString(3, sex);
			rs=pstmt.executeQuery();
			System.out.println(rs);
			if(rs.next()) {
				String password=rs.getString("password");
				dto=new StarbucksLoginDTO(userid, password, phone, sex);
			}
			System.out.println(rs.next());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			} try {
				if(pstmt!=null) pstmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			} try {
				if(conn!=null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}//finally
		return dto; //백터 리턴
	}//end
	
	
	public Vector searchStarbucksLogin(String userid, String phone, String sex) {
		Vector items=new Vector();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			StarbucksEmpDAO dao=new StarbucksEmpDAO();
			conn=dao.dbConn();
			String sql="select name, userid, password, age, phone, location, sex from starbuckslogin where userid=? and phone=? and sex=? ";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			pstmt.setString(2, phone);
			pstmt.setString(3, sex);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				Vector row=new Vector();
				row.add(rs.getString("name"));
				row.add(rs.getString("userid"));
				row.add(rs.getString("password"));
				row.add(rs.getInt("age"));
				row.add(rs.getString("phone"));
				row.add(rs.getString("location"));
				row.add(rs.getString("sex"));

				items.add(row);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				if(pstmt!=null) pstmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				if(conn!=null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}//finally
		return items;
	}




}
