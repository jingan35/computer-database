package com.excilys.cdb.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.cdb.exception.BaseVide;
import com.excilys.cdb.exception.PasDePagesNegException;
import com.excilys.cdb.mapper.DtoComputer;
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
	String searched=null;
	ArrayList<DtoComputer> computersList = new ArrayList<DtoComputer>();
	
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
		currentPage = (request.getParameter("page") == null) ? "1" : request.getParameter("page");
		searched = (request.getParameter("search") == null) ? null : request.getParameter("search");
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("search", searched);
		
		try {
			nbComputers=(searched==null)?(String.valueOf(serviceComputer.selectComputerCount(null))):(String.valueOf(serviceComputer.selectComputerCount(searched)));
			request.setAttribute("nbComputers", nbComputers);
			computersList= (searched==null)?(serviceComputer.selectComputer(Integer.parseInt(nbComputersByPage), Integer.parseInt(currentPage)))
					:(serviceComputer.selectComputerSearch(Integer.parseInt(nbComputersByPage), Integer.parseInt(currentPage),searched));
			request.setAttribute("computersList", computersList);
			int resteDernierePage = Integer.parseInt(nbComputers)%Integer.parseInt(nbComputersByPage);
			int lastPage =(resteDernierePage==0)? (Integer.parseInt(nbComputers)/Integer.parseInt(nbComputersByPage)):((Integer.parseInt(nbComputers)/Integer.parseInt(nbComputersByPage))+1);
			request.setAttribute("pagination", getPagination(Integer.parseInt(currentPage),lastPage));
			int firstArrow= ((Integer.parseInt(currentPage)-5)>=1)?(Integer.parseInt(currentPage)-5):1;
			int secondArrow= ((Integer.parseInt(currentPage)+5)<=lastPage)?(Integer.parseInt(currentPage)+5):lastPage;
			request.setAttribute("firstArrow", firstArrow);
			request.setAttribute("secondArrow", secondArrow);
			
		} catch (BaseVide | PasDePagesNegException e) {
			Logger logger = LoggerFactory.getLogger(DaoCompany.class);
			logger.error(e.getMessage(), e);
		}
		 
		this.getServletContext().getRequestDispatcher("/WEB-INF/views/dashboard.jsp").forward( request, response );
	}

	protected int getPagination(int currentPage,int lastPage) {
		return Math.max(3, Math.min(lastPage-2, currentPage));
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
