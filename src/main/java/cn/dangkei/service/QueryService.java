/**
 * 
 */
package cn.dangkei.service;

import java.util.List;

import cn.dangkei.bean.Message;
import cn.dangkei.dao.MessageDao;
import cn.dangkei.util.Iconst;

/**
 * @author Administrator
 * 所有查询相关的功能
 *
 */
public class QueryService {
	
	/*
	 * 查询消息列表
	 * */
	public List<Message> queryMessageList(String command,String description){
		MessageDao messageDao = new MessageDao();
		return messageDao.queryMessageList(command, description);
	}
	
	/*
	 * 根据指令查询消息结果
	 * */
	
	public String queryByCommand(String command) {
		MessageDao messageDao = new MessageDao();
		List<Message> messageList = messageDao.queryMessageList(command, null);
		if(messageList.size()>0)return messageList.get(0).getContent();
		return Iconst.NO_MATCHING_CONTENT;
	}
	
	
}
