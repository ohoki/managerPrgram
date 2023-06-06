package manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberDao {
	private JdbcConnectionUtil util;
	
	public MemberDao() {
		util = JdbcConnectionUtil.getInstance();
	}
	
	//C
	public int insertMember(MemberVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			conn = util.getConnection();
			StringBuffer query = new StringBuffer();
			query.append("insert into member (num,member_id,member_pw,nickname,regdate) ");
			query.append("values(member_seq.NEXTVAL, ?, ?, ?, sysdate)");
			
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setString(1, vo.getMemberId());
			pstmt.setString(2, vo.getMemberPw());
			pstmt.setString(3, vo.getNickName());
			
			result = pstmt.executeUpdate();
			System.out.println(result + "행이 삽입되었습니다.");
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			util.close(pstmt);
			util.close(conn);
		}
		return result;
	}
	
	//R
	public MemberVo selectMember(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberVo result = null;
		
		try {
			conn = util.getConnection();
			pstmt = conn.prepareStatement("select * from member where num = ?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = new MemberVo(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4)
						);
				result.setRegdate(rs.getDate("regdate"));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			util.close(rs);
			util.close(pstmt);
			util.close(conn);
		}
		return result;
	}
		
	public List<MemberVo> selectMemberAll() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MemberVo> result = new ArrayList<>();
		
		try {
			conn = util.getConnection();
			pstmt = conn.prepareStatement("select * from member");
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				MemberVo vo = new MemberVo(
						rs.getInt(1),
						rs.getString(2),
						rs.getString("member_pw"),
						rs.getString(4)
						);
				vo.setRegdate(rs.getDate("regdate"));
				result.add(vo);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			util.close(rs);
			util.close(pstmt);
			util.close(conn);
		}
		return result;
	}
	
	//U
	public int updateMember(MemberVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			conn = util.getConnection();
			StringBuffer query = new StringBuffer();
			query.append("update member set member_pw = ?, nickname = ? ");
			query.append("where num = ?");
			
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setString(1, vo.getMemberPw());
			pstmt.setString(2, vo.getNickName());
			pstmt.setInt(3, vo.getNum());
			
			result = pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			util.close(pstmt);
			util.close(conn);
		}
		return result;
	}
	
	//D
	public int deleteMember(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			conn = util.getConnection();
			StringBuffer query = new StringBuffer();
			query.append("delete member where num = ?");
			
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setInt(1, num);
			
			result = pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			util.close(pstmt);
			util.close(conn);
		}
		return result;
	}
}
