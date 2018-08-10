package com.wbh.model;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.wbh.dao.CartRecordDao;
import com.wbh.mvc.model.DispatchModel;
import com.wbh.mvc.model.ModelSupport;
import com.wbh.pojo.CartRecord;
import com.wbh.pojo.User;

public class OfflineModel extends DispatchModel{
	private CartRecordDao cartRecordDao;
	public OfflineModel(){
		cartRecordDao=new CartRecordDao();
	}
	@Override
	public String execute() {
		HttpServletRequest request=ModelSupport.getRequest();
		HttpSession session=request.getSession();
		//������ֱ��Ų�õļ������еķ������û����û�ע����Ҳ�����ﳵ����д�����ݿ�
		User loginUser=(User)session.getAttribute("loginUser");
		if(loginUser!=null){
			cartRecordDao.deleteAllCartInfoByUserId(loginUser.getUserId());
			List<CartRecord> cartRecordList=(List<CartRecord>)session.getAttribute("userCartRecordList");
			for(int i=0;i<cartRecordList.size();i++){
				cartRecordDao.insertCartRecord(cartRecordList);
			}
		}
		request.getSession().removeAttribute("loginUser");
		return "success";
	}
}
