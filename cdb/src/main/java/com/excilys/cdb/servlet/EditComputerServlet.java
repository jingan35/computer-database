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
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
import com.excilys.cdb.spring.AppConfig;
import com.excilys.cdb.spring.AppContext;
import com.excilys.cdb.validator.Validator;

/**
 * Servlet implementation class EditComputer
 */
@Controller
public class EditComputerServlet {
	private static final long serialVersionUID = 1L;
	ServiceComputer serviceComputer;
	private DtoComputer currentComputer;
	ServiceCompany serviceCompany;
	ArrayList<DtoCompany> companiesList = new ArrayList<DtoCompany>();
	Validator validator;
	AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
	AppContext appContext;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditComputerServlet() {
		super();
		this.appContext = AppContext.getInstance();
		this.serviceComputer = this.appContext.getServiceComputer();
		this.serviceCompany = this.appContext.getServiceCompany();
		this.validator = this.appContext.getValidator();
	}


	@GetMapping("/editComputer")
	protected ModelAndView doGet(@RequestParam(name = "id") String id1) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(id1);
		
		ModelAndView modelAndView= new ModelAndView("editComputer", "dtoComputer", new DtoComputer());
		
		
		try {
			currentComputer = serviceComputer.computerDetails(id);
			String name = currentComputer.getName();
			String introduced = currentComputer.getIntroduced() == null ? null
					: currentComputer.getIntroduced().split(" ")[0];
			String discontinued = currentComputer.getDiscontinued() == null ? null
					: currentComputer.getDiscontinued().split(" ")[0];
			String companyName = currentComputer.getCompanyName() == null ? null : currentComputer.getCompanyName();
			String companyId = currentComputer.getCompany_id() == null ? null : currentComputer.getCompany_id();
			
			companiesList = serviceCompany.selectAllCompanies();
			
			DtoComputer dtoComputer=new DtoComputer(id1,name,introduced,discontinued,companyId);
			dtoComputer.setCompanyName(companyName);
			modelAndView= new ModelAndView("editComputer", "dtoComputer", dtoComputer);
			modelAndView.addObject("company_id", companyId);
			modelAndView.addObject("companyName", companyName==null?"":companyName);
			modelAndView.addObject("companiesList", companiesList);
			
			return modelAndView;
		} catch (RequeteSansResultatException | BaseVide e) {
			// TODO Auto-generated catch block
			Logger logger = LoggerFactory.getLogger(DashboardServlet.class);
			logger.error(e.getMessage(), e);
		}
		modelAndView.addObject("id", id);
		return modelAndView;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@PostMapping("/editComputer")
	protected String doPost(@Validated @ModelAttribute("dtoComputer")DtoComputer dtoComputer) throws ServletException, IOException {
		try {
			validator.validateComputerDto(dtoComputer);
			serviceComputer.updateComputer(dtoComputer);
		} catch (PasLeBonFormatTimestamp | NotAIntegerException | TimestampDiscotinuedInferiorToTimestampIntroduced
				| RequiredElementException | InvalidDateOrderException e) {
			// TODO Auto-generated catch block
			Logger logger = LoggerFactory.getLogger(DashboardServlet.class);
			logger.error(e.getMessage(), e);
		}
		// doGet(request, response);
		return "redirect:/dashboard";
	}

}
