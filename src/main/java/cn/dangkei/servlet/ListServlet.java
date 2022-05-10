package cn.dangkei.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.dangkei.service.QueryService;

/*
 * 后台维护，显示消息列表
 * */
@SuppressWarnings("serial")
public class ListServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("----------------list messsage------UTF-8------------- ");
		//设置编码
		req.setCharacterEncoding("UTF-8");
		//获取页面参数
		String command = req.getParameter("command");
		String description = req.getParameter("description");
		//向页面传值
		req.setAttribute("command", command);
		req.setAttribute("description", description);
		
		QueryService listService = new QueryService();
		req.setAttribute("messageList", listService.queryMessageList(command, description));	
		
		req.getRequestDispatcher("list.jsp").forward(req, resp);
	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(req, resp);
	}
}
