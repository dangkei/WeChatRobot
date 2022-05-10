package cn.dangkei.dao;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import cn.dangkei.bean.Message;
import cn.dangkei.db.DbAccess;
/*
 * Message表相关Dao
 * */
public class MessageDao {

	
	
	public void deleteBatch(List<Integer> ids) {
		DbAccess dbAccess = new DbAccess();
		SqlSession sqlSession = null;
		try {
		 sqlSession= dbAccess.getSqlSession();
		 sqlSession.delete("Message.deleteBatch",ids);
		 sqlSession.commit();
		} catch (IOException e) {
			// TODO Auto-generated catchMessage block
			e.printStackTrace();
		}finally{
			if(null!=sqlSession) {
				sqlSession.close();
			}
		}
	}

	/*
	 * 删除一条记录
	 * */
	public void deleteOne(int id) {
		DbAccess dbAccess = new DbAccess();
		SqlSession sqlSession = null;
		try {
		 sqlSession= dbAccess.getSqlSession();
		 sqlSession.delete("Message.deleteOne",id);
		 sqlSession.commit();
		} catch (IOException e) {
			// TODO Auto-generated catchMessage block
			e.printStackTrace();
		}finally{
			if(null!=sqlSession) {
				sqlSession.close();
			}
		}
	}
	
	
	/*
	 * 查询消息列表
	 * */
	public List<Message> queryMessageList(String command ,String description){
		DbAccess dbAccess = new DbAccess();
		SqlSession sqlSession = null;
		List<Message> messageList =  new ArrayList<Message>();
		try {
		 sqlSession= dbAccess.getSqlSession();
		 Message message = new Message();
		 message.setCommand(command);
		 message.setDescription(description);
		 messageList = sqlSession.selectList("Message.queryMessageList",message);
		} catch (IOException e) {
			// TODO Auto-generated catchMessage block
			e.printStackTrace();
		}finally{
			if(null!=sqlSession) {
				sqlSession.close();
			}
		}
		return messageList;
	}
	
	/*
	 * 传统数据库访问
	 * */
	private List<Message> queryMessageList1(String command ,String description) {	
		List<Message> messageList =  new ArrayList<Message>();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/WebChat","root","123456");
			StringBuilder sql = new StringBuilder("select ID,COMMAND,DESCRIPTION,CONTENT from MESSAGE where 1=1"); 
			List<String> params = new ArrayList<String>();
			if(null != command && !"".equals(command.trim())) {
				sql.append(" and COMMAND like '%' ? '%'");
				params.add(command);
			}
			if(null != description && !"".equals(description.trim())) {
				sql.append(" and DESCRIPTION like '%' ? '%'");
				params.add(description);
			}
			System.out.println(sql.toString());
			PreparedStatement  statement = conn.prepareStatement(sql.toString());
			for(int i=0;i<params.size();i++) {
				System.out.println(params.get(i));
				statement.setString(i+1, params.get(i));
			}
			
			ResultSet rs = statement.executeQuery();

			while(rs.next()) {
				Message message = new Message();
				messageList.add(message);
				message.setId(rs.getString("ID"));
				message.setCommand(rs.getString("COMMAND"));
				message.setDescription(rs.getString("DESCRIPTION"));
				message.setContent(rs.getString("CONTENT"));
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return messageList;
	}
	
}
