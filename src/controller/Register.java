package controller;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import service.RegisterPic;
import service.SearchPic;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
@MultipartConfig
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		RegisterPic registerPic = new RegisterPic();
		String userName = request.getParameter("name");
		
		String root = request.getServletContext().getRealPath("/upload");
		Part part1 = request.getPart("pic1");
		Part part2 = request.getPart("pic2");
		Part part3 = request.getPart("pic3");
		String name1 = part1.getHeader("content-disposition");
		String name2 = part2.getHeader("content-disposition");
		String name3 = part3.getHeader("content-disposition");
		
		if(name1.lastIndexOf(".") != -1&name2.lastIndexOf(".") != -1&name3.lastIndexOf(".") != -1) {
			String str1 = name1.substring(name1.lastIndexOf("."), name1.length()-1);
			String str2 = name2.substring(name2.lastIndexOf("."), name2.length()-1);
			String str3 = name3.substring(name3.lastIndexOf("."), name3.length()-1);
			String filename1	= root+"\\"+UUID.randomUUID().toString()+str1;
			String filename2	= root+"\\"+UUID.randomUUID().toString()+str2;
			String filename3	= root+"\\"+UUID.randomUUID().toString()+str3;
			part1.write(filename1);
			part2.write(filename2);
			part3.write(filename3);
			
			
			if(registerPic.UploadPic(filename1,filename2,filename3,userName))
				request.setAttribute("status", "×¢²á³É¹¦!");
			else
				request.setAttribute("status", "×¢²áÊ§°Ü!");
			request.getRequestDispatcher("registerStatus.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
