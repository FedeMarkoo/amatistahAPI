package com.amatistah.servlet.actions;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletResponse;

import com.amatistah.servlet.supers.Request;
import com.amatistah.servlet.supers.Servlet;

/**
 * Servlet implementation class Main
 */
@WebServlet("/ama.tista")
public class Amatista extends Servlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Amatista() {
    	super("/pages/ama.tista.jsp");
    }

	@Override
	public void process(Request request, HttpServletResponse response) {
		//request.getSession().setAttribute("instagramPosts", Instagram.getInstagramPost(-1));
	}
}
