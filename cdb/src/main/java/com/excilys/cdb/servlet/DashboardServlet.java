package com.excilys.cdb.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.cdb.exception.BaseVide;
import com.excilys.cdb.exception.PasDePagesNegException;
import com.excilys.cdb.persistance.DaoCompany;
import com.excilys.cdb.service.ServiceComputer;

import javax.servlet.annotation.*;

/*@WebServlet(
		initParams={
	@WebInitParam(name="page", value="1"), 
	@WebInitParam(name="nbComputerByPage", value="10"),
	@WebInitParam(name="nbComputer", value="0")

		}
)*/


/**
 * Servlet implementation class DashboardServlet
 */
public class DashboardServlet extends HttpServlet {
	
	ServiceComputer serviceComputer = ServiceComputer.getInstance();
	String currentPage="1";
	String nbComputersByPage="10";
	String nbComputers="0";
	
	private static final long serialVersionUID = 1L;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DashboardServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.getServletContext().getRequestDispatcher("/WEB-INF/views/dashboard.jsp").forward( request, response );
		this.currentPage = (String)this.getServletContext().getAttribute("currentPage");
		currentPage = (request.getParameter("page") == null) ? "1" : request.getParameter("page");
		try {
			nbComputers=String.valueOf(serviceComputer.selectComputerCount());
		} catch (BaseVide | PasDePagesNegException e) {
			Logger logger = LoggerFactory.getLogger(DaoCompany.class);
			logger.error(e.getMessage(), e);
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
