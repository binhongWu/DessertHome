package com.wbh.listener;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.wbh.dao.CartRecordDao;
import com.wbh.mvc.model.ModelSupport;
import com.wbh.pojo.CartRecord;
import com.wbh.pojo.User;

public class SessionListener implements HttpSessionListener{
	private CartRecordDao cartRecordDao;
	
	public SessionListener(){
		cartRecordDao=new CartRecordDao();
	}
	public void sessionCreated(HttpSessionEvent se) {
		// TODO Auto-generated method stub
		System.out.println("session被创建了");
	}

	public void sessionDestroyed(HttpSessionEvent se) {
		// TODO Auto-generated method stub
		System.out.println("session被销毁了");
		
		HttpSession session=se.getSession();
		User loginUser=(User)session.getAttribute("loginUser");
		if(loginUser!=null){
			cartRecordDao.deleteAllCartInfoByUserId(loginUser.getUserId());
			List<CartRecord> cartRecordList=(List<CartRecord>)session.getAttribute("userCartRecordList");
			for(int i=0;i<cartRecordList.size();i++){
				cartRecordDao.insertCartRecord(cartRecordList);
			}
		}
	}

}
