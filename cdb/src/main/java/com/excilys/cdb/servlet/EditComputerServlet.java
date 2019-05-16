package com.excilys.cdb.servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.cdb.exception.BaseVide;
import com.excilys.cdb.exception.InvalidDateOrderException;
import com.excilys.cdb.exception.NotAIntegerException;
import com.excilys.cdb.exception.PasLeBonFormatTimestamp;
import com.excilys.cdb.exception.RequeteSansResultatException;
import com.excilys.cdb.exception.RequiredElementException;
import com.excilys.cdb.exception.TimestampDiscotinuedInferiorToTimestampIntroduced;
import com.excilys.cdb.mapper.DtoCompany;
import com.excilys.cdb.mapper.DtoComputer;
import com.excilys.cdb.service.ServiceCompany;
import com.excilys.cdb.service.ServiceComputer;
import com.excilys.cdb.validator.Validator;

/**
 * Servlet implementation class EditComputer
 */
public class EditComputerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ServiceComputer serviceComputer = ServiceComputer.getInstance();
    private DtoComputer currentComputer;   
    ServiceCompany seviceCompany = ServiceCompany.getInstance();
	ArrayList<DtoCompany> companiesList = new ArrayList<DtoCompany>();
	Validator validator = Validator.getInstance();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditComputerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("id"));
		request.setAttribute("id", id);
		try {
			currentComputer=serviceComputer.computerDetails(id);
			String name=currentComputer.getName();
			String introduced=currentComputer.getIntroduced()==null?null:currentComputer.getIntroduced().split(" ")[0];
			String discontinued=currentComputer.getDiscontinued()==null?null:currentComputer.getDiscontinued().split(" ")[0];
			String companyName=currentComputer.getCompanyName()==null?null:currentComputer.getCompanyName();
			String companyId=currentComputer.getCompanyId()==null?null:currentComputer.getCompanyId();
			request.setAttribute("name", name);
			request.setAttribute("introduced", introduced);
			request.setAttribute("discontinued", discontinued);
			request.setAttribute("company_name", companyName);
			request.setAttribute("companyId", companyId);
			companiesList=ServiceCompany.getInstance().selectAllCompanies();
			request.setAttribute("companiesList", companiesList);
			
		} catch (RequeteSansResultatException | BaseVide e) {
			// TODO Auto-generated catch block
			Logger logger = LoggerFactory.getLogger(DashboardServlet.class);
			logger.error(e.getMessage(), e);
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/views/editComputer.jsp").forward( request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		DtoComputer dtoComputer= new DtoComputer(request.getParameter("id"),request.getParameter("UpdateComputerName"),request.getParameter("UpdateIntroduced"),request.getParameter("UpdateDiscontinued"),
				request.getParameter("UpdateCompanyId"));
		try {
			validator.validateComputerDto(dtoComputer);
			serviceComputer.updateComputer(dtoComputer);
		} catch (PasLeBonFormatTimestamp | NotAIntegerException | TimestampDiscotinuedInferiorToTimestampIntroduced | RequiredElementException | InvalidDateOrderException e) {
			// TODO Auto-generated catch block
			Logger logger = LoggerFactory.getLogger(DashboardServlet.class);
			logger.error(e.getMessage(), e);
		}
		//doGet(request, response);
		response.sendRedirect(request.getContextPath()+"/dashboard");	}

}
