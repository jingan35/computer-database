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
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

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
import com.excilys.cdb.spring.AppConfig;
import com.excilys.cdb.spring.AppContext;
import com.excilys.cdb.validator.Validator;;

/**
 * Servlet implementation class AddComputerServlet
 */
@Controller
public class AddComputerServlet {
	
	ServiceCompany serviceCompany;
	ArrayList<DtoCompany> companiesList = new ArrayList<DtoCompany>();
	Validator validator ;
	ServiceComputer serviceComputer ;
	AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
	AppContext appContext;
   
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
    public AddComputerServlet() {
        super();
        this.appContext=AppContext.getInstance();
	    this.serviceComputer = this.appContext.getServiceComputer();
        this.serviceCompany =this.appContext.getServiceCompany();
        this.validator= this.appContext.getValidator();
    }
    
    @GetMapping("/addComputer")
    public ModelAndView showForm() {
        return new ModelAndView("addComputer", "dtoComputer", new DtoComputer());
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
   /* @GetMapping("/addComputer")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			companiesList=serviceCompany.selectAllCompanies();
			request.setAttribute("companiesList", companiesList);
			
		} catch (BaseVide e) {
			// TODO Auto-generated catch block
			Logger logger = LoggerFactory.getLogger(AddComputerServlet.class);
			logger.error(e.getMessage(), e);
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/views/addComputer.jsp").forward( request, response );
	}*/

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    /*@PostMapping("/addComputer")
	protected void doPost(HttpServletRequest request, HttpServletResponse response,@RequestBody DtoComputer dtoComputer) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/*DtoComputer dtoComputer= new DtoComputer("0",request.getParameter("computerName"),request.getParameter("introduced"),request.getParameter("discontinued"),
				request.getParameter("companyId"));*/
		/*System.out.println(request.getParameter("introduced"));
		try {
			validator.validateComputerDto(dtoComputer);
			serviceComputer.insertComputer(dtoComputer);
			
		} catch (NotAIntegerException | RequiredElementException | PasLeBonFormatTimestamp | TimestampDiscotinuedInferiorToTimestampIntroduced | InvalidDateOrderException e) {
			// TODO Auto-generated catch block
			Logger logger = LoggerFactory.getLogger(AddComputerServlet.class);
			logger.error(e.getMessage(), e);
		}
		
		doGet(request, response);
	}*/
    
    @PostMapping("/addComputer")
	protected String doPost(@Validated @ModelAttribute("dtoComputer")DtoComputer dtoComputer) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/*DtoComputer dtoComputer= new DtoComputer("0",request.getParameter("computerName"),request.getParameter("introduced"),request.getParameter("discontinued"),
				request.getParameter("companyId"));
		System.out.println(request.getParameter("introduced"));*/
		try {
			validator.validateComputerDto(dtoComputer);
			serviceComputer.insertComputer(dtoComputer);
			
		} catch (NotAIntegerException | RequiredElementException | PasLeBonFormatTimestamp | TimestampDiscotinuedInferiorToTimestampIntroduced | InvalidDateOrderException e) {
			// TODO Auto-generated catch block
			Logger logger = LoggerFactory.getLogger(AddComputerServlet.class);
			logger.error(e.getMessage(), e);
		}
		
		return "redirect:/addComputer";
	}

}
