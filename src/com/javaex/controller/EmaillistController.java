package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.EmaillistDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.EmailVO;

@WebServlet("/el")
public class EmaillistController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//도달할 주소 localhost:8088/emaillist2/EmaillistController -> 줄여서 /el -> /el?a=form
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("얘는 doGet의 controller");
		
		//파라미터로 a를 받아옴
		String actionName=request.getParameter("a");
		System.out.println(actionName);
		
		//원래는 actionName.equals("form")으로 쓸 수 있는데 혹시나 a값을 넘겨주지 않으면 equals를 확인할 수 없어서 오류남
		if("form".equals(actionName)) {
			//form
			WebUtil.forward(request, response, "/WEB-INF/form.jsp");
			
			/*WebUtil util = new WebUtil();
			util.forward(request, response, path);*/
			
			//RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/form.jsp"); //��û ������
			//rd.forward(request, response); //요청 보낼거
			
		///el?a=insert
		} else if("insert".equals(actionName)) {
			//insert
			String lastName=request.getParameter("ln");
			String firstName=request.getParameter("fn");
			String email=request.getParameter("email");
			
			EmailVO vo = new EmailVO(lastName, firstName, email);
			EmaillistDao dao = new EmaillistDao();
			dao.insert(vo);
			
			//list redirect = 사용자가 다시 주소치고 들어가야하는거니 url형식
			WebUtil.redirect(request, response, "/emaillist2/el");
			/*response.sendRedirect("/emaillist2/el"); //리다이렉트는 변하는게 없는 주소이기때문에 변경 노노
			System.out.println("얘는 insert의 list");*/
					
		} else {
			//list
			EmaillistDao dao = new EmaillistDao();
			List<EmailVO> list = dao.getList();
			
			request.setAttribute("list", list); //("list를 꺼낼 때 이름" : 리스트)
			WebUtil.forward(request, response, "/WEB-INF/list.jsp");
			/*RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/list.jsp");
			rd.forward(request, response);*/
			System.out.println("얘는 list의 list");
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
