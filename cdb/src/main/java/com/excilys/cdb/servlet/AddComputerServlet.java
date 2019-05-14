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
import com.excilys.cdb.exception.InvalidDateOrderException;
import com.excilys.cdb.exception.NotAIntegerException;
import com.excilys.cdb.exception.PasLeBonFormatTimestamp;
import com.excilys.cdb.exception.RequiredElementException;
import com.excilys.cdb.exception.TimestampDiscotinuedInferiorToTimestampIntroduced;
import com.excilys.cdb.mapper.DtoCompany;
import com.excilys.cdb.mapper.DtoComputer;
import com.excilys.cdb.persistance.DaoCompany;
import com.excilys.cdb.service.ServiceCompany;
import com.excilys.cdb.service.ServiceComputer;
import com.excilys.cdb.validator.Validator;;

/**
 * Servlet implementation class AddComputerServlet
 */
public class AddComputerServlet extends HttpServlet {
	
	ServiceCompany seviceCompany = ServiceCompany.getInstance();
	ArrayList<DtoCompany> companiesList = new ArrayList<DtoCompany>();
	Validator validator = Validator.getInstance();
	ServiceComputer serviceComputer = ServiceComputer.getInstance();
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddComputerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			companiesList=ServiceCompany.getInstance().selectAllCompanies();
			request.setAttribute("companiesList", companiesList);
			
		} catch (BaseVide e) {
			// TODO Auto-generated catch block
			Logger logger = LoggerFactory.getLogger(AddComputerServlet.class);
			logger.error(e.getMessage(), e);
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/views/addComputer.jsp").forward( request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println(request.getParameter("discontinued"));
		DtoComputer dtoComputer= new DtoComputer("0",request.getParameter("computerName"),request.getParameter("introduced"),request.getParameter("discontinued"),
				request.getParameter("companyId"));
		try {
			validator.validateComputerDto(dtoComputer);
			serviceComputer.insertComputer(dtoComputer);
			
		} catch (NotAIntegerException | RequiredElementException | PasLeBonFormatTimestamp | TimestampDiscotinuedInferiorToTimestampIntroduced | InvalidDateOrderException e) {
			// TODO Auto-generated catch block
			Logger logger = LoggerFactory.getLogger(AddComputerServlet.class);
			logger.error(e.getMessage(), e);
		}
		
		doGet(request, response);
	}

}
