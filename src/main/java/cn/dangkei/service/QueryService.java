/**
 * 
 */
package cn.dangkei.service;

import java.util.List;
import java.util.Random;

import cn.dangkei.bean.Command;
import cn.dangkei.bean.CommandContent;
import cn.dangkei.bean.Message;
import cn.dangkei.dao.CommandDao;
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
		//MessageDao messageDao = new MessageDao();
		CommandDao commandDao = new CommandDao();
		//List<Message> messageList; 
		List<Command> commandList; 
		if(Iconst.HELP_COMMAND.equals(command)) {
			//messageList = messageDao.queryMessageList(null, null);
			commandList = commandDao.queryCommandList(null, null);
			StringBuilder result = new StringBuilder();
			for(int i =0; i<commandList.size(); i++) {
				if(i!=0) result.append("<br/>"); 
				result.append("回复【"+commandList.get(i).getName()+"】可以查看"+commandList.get(i).getDescription());
			}
			
			return result.toString();
		}
		//messageList = messageDao.queryMessageList(command, null);
		commandList = commandDao.queryCommandList(command, null);
		//if(messageList.size()>0)return messageList.get(0).getContent();
		
		if(commandList.size()>0) {
			List<CommandContent> contentList = commandList.get(0).getContentList();
			return contentList.get(new Random().nextInt(contentList.size())).getContent();
		}
						
		return Iconst.NO_MATCHING_CONTENT;
	}
	
	
}
