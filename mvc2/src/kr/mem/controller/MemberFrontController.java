package kr.mem.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.mem.model.MemberDAO;
import kr.mem.model.MemberVO;
import kr.mem.pojo.Controller;
import kr.mem.pojo.MemberDeleteController;
import kr.mem.pojo.MemberInsertController;
import kr.mem.pojo.MemberInsertFormController;
import kr.mem.pojo.MemberListController;
public class MemberFrontController extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  request.setCharacterEncoding("euc-kr");
		  String reqUrl=request.getRequestURI();
		  String ctxPath=request.getContextPath();
		  String command=reqUrl.substring(ctxPath.length());

		  Controller controller=null;
		  String nextView=null;
		  HandlerMapping mappings=new HandlerMapping();
		  controller=mappings.getController(command);
		  nextView=controller.requestHandle(request, response);
	  
		  //View 페이지로 연동하는 부분
		  if(nextView!=null) {
			  if(nextView.indexOf("redirect:")!=-1) {
				  String[] sp=nextView.split(":"); //  sp[0]:sp[1]
				  response.sendRedirect(sp[1]); // :O
			  }else {
				  RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/views/"+nextView);
				  rd.forward(request, response);
			  }
		  }
	}
}