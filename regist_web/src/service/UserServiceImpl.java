package service;


import java.sql.SQLException;

import dao.UserDao;
import dao.UserDaoImpl;
import domain.User;
import utils.MailUtils;

public class UserServiceImpl implements UserService{

	@Override
	//ҵ���ʵ���û�ע��ķ���
	public void regist(User user) throws Exception {
		//�����ݴ��뵽���ݿ�
		UserDao userDao = new UserDaoImpl();
		userDao.regist(user);
		//ͬʱ����һ���ʼ�
		MailUtils.sendMail(user.getEmail(),user.getCode());
	}

	@Override
	//���ݼ������ѯ�û��ķ���
	public User findByCode(String code) throws SQLException{
		UserDao userDao = new UserDaoImpl();
		
			return userDao.findByCode(code);
		
	}

	@Override
	//ҵ����޸�ҵ��ķ���
	public void update(User user) throws Exception {
		UserDao userDao = new UserDaoImpl();
		userDao.update(user);
	}

}
