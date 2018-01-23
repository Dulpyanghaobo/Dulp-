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

/**
 * �û������ʼ�Servlet
 */
public class ActiveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public ActiveServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//���ܼ�����
	try {
		String code = request.getParameter("code");
		UserService userService = new UserServiceImpl();
		User user = userService.findByCode(code);
		if(user != null) {
			//�Ѿ���ѯ�����û�,�޸ĸ��û�
			user.setState(1);
			user.setCode(null);
			userService.update(user);
			request.setAttribute("msg","���Ѿ�����ɹ���ȥ��¼");
		}else {
			//���ݼ�����û�в�ѯ�����û�
			request.setAttribute("msg","���ļ���������!�����¼���!");
		}
		request.getRequestDispatcher("/msg.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		//���ݼ������ѯ�û�
		//�Ѿ���ѯ��,�޸��û�״̬
	
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
