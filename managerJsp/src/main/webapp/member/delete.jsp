<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="manager.*"%>
<%
	MemberService memberService = new MemberService(new MemberDao());
	int num = Integer.parseInt(request.getParameter("num"));
	if(memberService.remove(num)) {
		response.sendRedirect(request.getContextPath() + "/member/list.jsp");
	} else {
		response.sendRedirect(request.getContextPath() + "/member/detail.jsp?num=" + num);
	};
%>        