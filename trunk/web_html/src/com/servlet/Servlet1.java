package com.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Writer;
import java.nio.Buffer;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Servlet1
 */
public class Servlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Servlet1() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");

		System.out.println("--- 进入servlet1");

		System.out.println("path--" + request.getContextPath() + "---type-"
				+ request.getContentType() + "--contentlength---"
				+ request.getContentLength());

		Enumeration<String> aa = request.getParameterNames();

		StringBuffer sb = new StringBuffer();
		while (aa.hasMoreElements()) {
			String name = aa.nextElement().toString();
			sb.append("--name-" + name + "\t--value-"
					+ request.getParameter(name));
		}
		System.out.println(sb.toString());
		Writer w = response.getWriter();
		w.write(sb.toString() + "over---------------");
		w.close();
	}
}
