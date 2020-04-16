package project11_27;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.Vector;

public class StarbucksEmpDAO {
	
	//connection
	public Connection dbConn() {
		Connection conn=null;
		try {
			FileInputStream fis=new FileInputStream("d:\\db.prop");
			Properties prop=new Properties();
			prop.load(fis);
			
			String url=prop.getProperty("url");
			String id=prop.getProperty("id");
			String password=prop.getProperty("password");
			conn=DriverManager.getConnection(url, id, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	//목록
	public Vector listStarbucksEmp() {
		Vector items=new Vector();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			conn=dbConn();
			String sql=" select eno, position, ename, sal, phone, location, sex from starbucksemp order by location ";
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				Vector row=new Vector();
				int eno=rs.getInt("eno");
				String position=rs.getString("position");
				String ename=rs.getString("ename");
				int sal=rs.getInt("sal");
				String phone=rs.getString("phone");
				String location =rs.getString("location");
				String sex=rs.getString("sex");
				
				row.add(eno);
				row.add(position);
				row.add(ename);
				row.add(sal);
				row.add(phone);
				row.add(location);
				row.add(sex);
				
				items.add(row);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null)rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				if(pstmt!=null)pstmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				if(conn!=null)conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return items;
	}//end list
	
	
	public int insertStarbucksEmp(StarbucksEmpDTO dto) {
		int result=0;
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			conn=dbConn();
			String sql=" insert into starbucksemp(eno, position, ename, sal, phone, location, sex) values(?,?,?,?,?,?,?)"; 
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getEno());
			pstmt.setString(2, dto.getPosition());
			pstmt.setString(3, dto.getEname());
			pstmt.setInt(4, dto.getSal());
			pstmt.setString(5, dto.getPhone());
			pstmt.setString(6, dto.getLocation());
			pstmt.setString(7, dto.getSex());
			result=pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt!=null)pstmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				if(conn!=null)conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}//end insert
	
	//수정
	public int updateStarbucksEmp(StarbucksEmpDTO dto) {
		int result=0;
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			conn=dbConn();
			String sql=" update starbucksemp set eno =?, position=?, sal=?, phone=?, location=?, sex=? where ename=? ";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getEno());
			pstmt.setString(2, dto.getPosition());
			pstmt.setInt(3, dto.getSal());
			pstmt.setString(4, dto.getPhone());
			pstmt.setString(5, dto.getLocation());
			pstmt.setString(6, dto.getSex());
			pstmt.setString(7, dto.getEname());
			result=pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt!=null)pstmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				if(conn!=null)conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}//end update
	
	//삭제
	public int deleteStarbucksEmp(String ename) {
		int result=0;
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			conn=dbConn();
			String sql="delete from starbucksemp where ename =? ";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, ename);
			result=pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt!=null)pstmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				if(conn!=null)conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}
	
	//사람이름으로찾기
	public Vector searchStarbucksEmp_n(String ename) {
		Vector items=new Vector();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=dbConn();
			String sql=" select eno, position, ename, sal, phone, location, sex from starbucksemp where ename=? ";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, ename);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				Vector row=new Vector();
				row.add(rs.getInt("eno"));
				row.add(rs.getString("position"));
				row.add(rs.getString("ename"));
				row.add(rs.getInt("sal"));
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
	
	//지점별로 찾기
	public Vector searchStarbucksEmp_l(String location) {
		Vector items=new Vector();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=dbConn();
			String sql=" select eno, position, ename, sal, phone, location, sex from starbucksemp where location=? ";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, location);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				Vector row=new Vector();
				row.add(rs.getInt("eno"));
				row.add(rs.getString("position"));
				row.add(rs.getString("ename"));
				row.add(rs.getInt("sal"));
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
	//보기
	public StarbucksEmpDTO viewStarbucksEmp (String ename) {
		StarbucksEmpDTO dto=null;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=dbConn();
			String sql=" select eno, position, ename, sal, phone, location, sex from starbucksemp where ename=? ";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, ename);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				int eno=rs.getInt("eno");
				String position=rs.getString("position");
				int sal=rs.getInt("sal");
				String phone=rs.getString("phone");
				String location= rs.getString("location");
				String sex=rs.getString("sex");

				dto=new StarbucksEmpDTO(eno, sal, position, ename, phone, location, sex);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null)rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				if(pstmt!=null)pstmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				if(conn!=null)conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return dto;
	}
	
	//직책으로 찾기
	public Vector searchStarbucksEmp_P(String position) {
		Vector items=new Vector();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=dbConn();
			String sql=" select eno, position, ename, sal, phone, location, sex from starbucksemp where position=? ";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, position);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				Vector row=new Vector();
				row.add(rs.getInt("eno"));
				row.add(rs.getString("position"));
				row.add(rs.getString("ename"));
				row.add(rs.getInt("sal"));
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
	public StarbucksLoginDTO searchPassword1 (String userid, String phone, String sex) {
		StarbucksLoginDTO dto =new StarbucksLoginDTO();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=dbConn();
			String sql="select password from starbuckslogin where userid=? and phone=? and sex=? ";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			pstmt.setString(2, phone);
			pstmt.setString(3, sex);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				userid=rs.getString("userid");
				phone=rs.getString("phone");
				sex=rs.getString("sex");
				String password=rs.getString("password");
				 dto=new StarbucksLoginDTO(userid, password, phone, sex);
						 }
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
}
