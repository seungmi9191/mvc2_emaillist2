package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaex.vo.EmailVO;

public class EmaillistDao {
	
	public void insert(EmailVO vo) {
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		//ResultSet rs = null;
		int count;

		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			
			// 3. SQL문 준비 / 바인딩 / 실행
			String query ="insert into emaillist values( seq_emaillist_no.nextval, ?, ?, ?)";
			pstmt = conn.prepareStatement(query);
			
			//쿼리문의 ?를 문자열로 변경해주기
			pstmt.setString(1, vo.getLastName()); //(����, �ۿ��� �޾ƿ��� ��)
			pstmt.setString(2, vo.getFirstName());
			pstmt.setString(3, vo.getEmail());
		    
			//db날려주기 - 성공 1, 실패0
			count = pstmt.executeUpdate();
			
			// 4.결과처리
			System.out.println(count + "건 등록");

		} catch (ClassNotFoundException e) {
		    System.out.println("error:드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
		    System.out.println("error:" + e);
		} finally {
		   
			 // 5. 자원정리
		    try {
		       // if (rs != null) {
		        //    rs.close();
		       // }                
		        if (pstmt != null) {
		            pstmt.close();
		        }
		        if (conn != null) {
		            conn.close();
		        }
		    } catch (SQLException e) {
		        System.out.println("error:" + e);
		    }

		}		
	}
	
	public List<EmailVO> getList() {
		
		// 0. import java.sql.*;
				Connection conn = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;

				List<EmailVO> list = new ArrayList<EmailVO>();
				
				try {
					// 1. JDBC 드라이버 (Oracle) 로딩
					Class.forName("oracle.jdbc.driver.OracleDriver");
					
					 // 2. Connection 얻어오기
					String url = "jdbc:oracle:thin:@localhost:1521:xe";
					conn = DriverManager.getConnection(url, "webdb", "webdb");
					
					  // 3. SQL문 준비 / 바인딩 / 실행
					String query ="select no, last_name, first_name, email "
								 + "from emaillist "
							     + "order by no desc ";
					pstmt = conn.prepareStatement(query);
					
					rs = pstmt.executeQuery();
				    
					//db날려주기 - 성공 1, 실패0
					while(rs.next()) {
						int no = rs.getInt("no");
						String lastName = rs.getString("last_name");
						String firstName = rs.getString("first_name");
						String email = rs.getString("email");
						
						EmailVO vo = new EmailVO(no, lastName, firstName, email);
						list.add(vo);
					}
					
					// 4.결과처리
					

				} catch (ClassNotFoundException e) {
				    System.out.println("error: ����̹� �ε� ���� - " + e);
				} catch (SQLException e) {
				    System.out.println("error:" + e);
				} finally {
				   
					// 5. 자원정리
				    try {
				       if (rs != null) {
				           rs.close();
				       }                
				        if (pstmt != null) {
				            pstmt.close();
				        }
				        if (conn != null) {
				            conn.close();
				        }
				    } catch (SQLException e) {
				        System.out.println("error:" + e);
				    }

     		}
				return list;
		
	}
}
