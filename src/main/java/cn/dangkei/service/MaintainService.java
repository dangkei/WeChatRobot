/**
 * 
 */
package cn.dangkei.service;

import java.util.ArrayList;
import java.util.List;

import cn.dangkei.dao.MessageDao;

/**
 * @author Administrator
 * 消息维护Service
 *
 */
public class MaintainService {

	/*
	 * 批量删除
	 * */
	public void deleteBatch(String[] ids) {
		MessageDao messageDao = new MessageDao();
		List<Integer> idList = new ArrayList<Integer>();
		for(String id:ids) {
			idList.add(Integer.valueOf(id));
		}
		messageDao.deleteBatch(idList);
	}
	
	/*
	 * 单条删除
	 * */
	public void deleteOne(String id) {
		if(null!=id && !"".equals(id.trim())) {
			MessageDao messageDao = new MessageDao();
			messageDao.deleteOne(Integer.valueOf(id));
		}

	}
}
