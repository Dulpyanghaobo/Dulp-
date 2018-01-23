package web.Servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.User;
import service.UserService;
import service.UserServiceImpl;
import utils.UUIDUtils;

/**
 * �û�ע���servlet
 */
public class RegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��������:
		//������������
	try{
		request.setCharacterEncoding("utf-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String nickname = request.getParameter("nickname");
		String email = request.getParameter("email");
		//��װ����
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setNickname(nickname);
		user.setEmail(email);
		user.setState(0);//0��ʾδ����1��ʾ����
		String code = UUIDUtils.getUUID()+UUIDUtils.getUUID();
		user.setCode(code);
		//����ҵ��㴦������
		UserService userService = new UserServiceImpl();
		userService.regist(user);
		//ҳ����ת
		request.setAttribute("msg","���Ѿ�ע��ɹ�!��ȥ���伤��!");
		request.getRequestDispatcher("/msg.jsp").forward(request,response);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("�������ݿ�ʧ��",e);
		}
		//ҳ����ת
 catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
