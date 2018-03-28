package controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import jdk.nashorn.api.scripting.JSObject;
import net.sf.json.JSONObject;
import service.SearchPic;
import sun.misc.BASE64Decoder;

/**
 * Servlet implementation class Search
 */
@WebServlet("/Search")
@MultipartConfig
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Search() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		//System.out.println(request.getParameter("img"));
		BASE64Decoder decoder = new BASE64Decoder();
		//System.out.println(request.getParameter("img"));
		String img = request.getParameter("img");
		HttpSession session = request.getSession();
		//img.replace("data:image/png;base64,", "");
		if(img!=null) {
			
		
		img = img.substring(22);
		img = img.replace(" ", "+");
		//System.out.println(img);
		byte[] bytes = decoder.decodeBuffer(img);
		/*for (int i = 0; i < bytes.length; ++i) {
			if (bytes[i] < 0) {// 调整异常数据
			bytes[i] += 256;
			}
		}
		
		for (byte b : bytes) {
			System.err.print(b);
		}*/
		//bytes[i]
		//System.out.println(bytes.length);
		
		//Image
		
		SearchPic uploadPic = new SearchPic();
		//System.out.println(uploadPic.search(picPath));
		
		//byte[] bytes2 = uploadPic.getBytesFromFile(new File("C:\\Users\\Lenovo\\Desktop\\lgx\\1.jpg"));
		//uploadPic.base64StringToImage(bytes);
		
		/*String str = uploadPic.search(bytes);
		System.out.println(str);	*/
		Double conf = uploadPic.search(bytes);
		System.out.println(conf);
		//JSONObject.fromObject(new String(conf));
		//request.setAttribute("status",conf);
		response.getWriter().write(String.valueOf(conf));
		//request.getRequestDispatcher("front.jsp").forward(request, response);
		/*session.setAttribute("status", conf);
		response.sendRedirect("front.jsp");*/
		
		
		/*Part part = request.getPart("pic");
		String name = part.getHeader("content-disposition");
		System.out.println(name);
		String root = request.getServletContext().getRealPath("/upload");
		System.out.println(root);
		if(name.lastIndexOf(".") != -1) {
			String str = name.substring(name.lastIndexOf("."), name.length()-1);
			System.out.println(str);
			String filename	= root+"\\"+UUID.randomUUID().toString()+str;
			System.out.println("文件名为"+"upload"+filename.substring(filename.lastIndexOf("\\"),filename.length()));
			//File file = new File(filename);
			part.write(filename);
			String picAddress = "upload"+filename.substring(filename.lastIndexOf("\\"),filename.length());
			System.out.println(picAddress);
			
			SearchPic uploadPic = new SearchPic();
			//System.out.println(uploadPic.search(picPath));
			request.setAttribute("status",uploadPic.search(filename));
			request.getRequestDispatcher("status.jsp").forward(request, response);
		}*/
		/*String kk = request.getParameter("pic");
		System.out.println(kk);*/
			
		
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
