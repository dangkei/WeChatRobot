/**
 * 
 */
package cn.dangkei.db;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * @author Administrator
 * 数据库访问类
 */
public class DbAccess {
	public SqlSession getSqlSession() throws IOException {
		Reader reader = Resources.getResourceAsReader("cn/dangkei/config/configuration.xml");
		SqlSessionFactory f = new SqlSessionFactoryBuilder().build(reader);
		return f.openSession();
	}
}
