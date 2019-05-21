package com.excilys.cdb.servlet;

import java.io.IOException;

import com.excilys.cdb.WebUiObject.OrderByEnum;
import com.excilys.cdb.WebUiObject.Page;
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
	Page pageData = Page.getInstance();
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		pageData.setCurrentPage((request.getParameter("page") == null||request.getParameter("page") == "") ? "1" : request.getParameter("page"));
		pageData.setSearched(request.getParameter("search"));
		boolean error = false;

		String orderBy = request.getParameter("orderBy");
		if (orderBy != null&&!orderBy.equals("")) {
			switch (orderBy) {
			case "computer":
				pageData.setIsOrdered(true);
				pageData.setOrderBy(OrderByEnum.NAME);
				break;
			case "computerDesc":
				pageData.setIsOrdered(true);
				pageData.setOrderBy(OrderByEnum.NAME_DESC);
				break;
			case "introduced":
				pageData.setIsOrdered(true);
				pageData.setOrderBy(OrderByEnum.INTRODUCED);
				break;
			case "introducedDesc":
				pageData.setIsOrdered(true);
				pageData.setOrderBy(OrderByEnum.INTRODUCED_DESC);
				break;
			case "discontinued":
				pageData.setIsOrdered(true);
				pageData.setOrderBy(OrderByEnum.DISCONTINUED);
				break;
			case "discontinuedDesc":
				pageData.setIsOrdered(true);
				pageData.setOrderBy(OrderByEnum.DISCONTINUED_DESC);
				break;
			case "company":
				pageData.setIsOrdered(true);
				pageData.setOrderBy(OrderByEnum.COMPANY_NAME);
				break;
			case "companyDesc":
				pageData.setIsOrdered(true);
				pageData.setOrderBy(OrderByEnum.COMPANY_NAME_DESC);
				break;
			default:
				System.out.println(orderBy+"-ff-");
				error=true;
				break;
			}
		}

		request.setAttribute("pageData.getCurrentPage()", pageData.getCurrentPage());
		request.setAttribute("search", pageData.getSearched());
		if (request.getParameter("size") != null)
			pageData.setNbComputersByPage(request.getParameter("size"));
		request.setAttribute("size", pageData.getNbComputersByPage());

		try {
			pageData.setNbComputers((String.valueOf(serviceComputer.selectComputerCount(pageData.getSearched()))));
			request.setAttribute("nbComputers", pageData.getNbComputers());
			int resteDernierePage = Integer.parseInt(pageData.getNbComputers())
					% Integer.parseInt(pageData.getNbComputersByPage());
			int lastPage = (resteDernierePage == 0)
					? (Integer.parseInt(pageData.getNbComputers()) / Integer.parseInt(pageData.getNbComputersByPage()))
					: ((Integer.parseInt(pageData.getNbComputers()) / Integer.parseInt(pageData.getNbComputersByPage()))
							+ 1);
			if (lastPage < Integer.parseInt(pageData.getCurrentPage())) {
				pageData.setCurrentPage("1");
			}
			computersList = (pageData.getSearched() == null) ? (serviceComputer.selectComputer(pageData))
					: (serviceComputer.selectComputerSearch(pageData));
			request.setAttribute("computersList", computersList);
			request.setAttribute("pagination", getPagination(Integer.parseInt(pageData.getCurrentPage()), lastPage));
			request.setAttribute("lastPage", lastPage);
			int firstArrow = ((Integer.parseInt(pageData.getCurrentPage()) - 5) >= 1)
					? (Integer.parseInt(pageData.getCurrentPage()) - 5)
					: 1;
			int secondArrow = ((Integer.parseInt(pageData.getCurrentPage()) + 5) <= lastPage)
					? (Integer.parseInt(pageData.getCurrentPage()) + 5)
					: lastPage;
			request.setAttribute("firstArrow", firstArrow);
			request.setAttribute("secondArrow", secondArrow);

			ArrayList<Integer> availablePages = new ArrayList<Integer>();
			if (Integer.parseInt(pageData.getCurrentPage()) == (lastPage - 1)
					|| Integer.parseInt(pageData.getCurrentPage()) == (lastPage)) {
				for (int i = pageData.getNbPages() - 4; i <= pageData.getNbPages(); i++) {
					if (i > 0 && i <= pageData.getNbPages())
						availablePages.add(i);
				}
			} else {
				for (int i = Integer.parseInt(pageData.getCurrentPage()) - 2; i <= Integer
						.parseInt(pageData.getCurrentPage()) + 2; i++) {
					if (i > 0 && i <= pageData.getNbPages())
						availablePages.add(i);
				}
				if (availablePages.size() == 3 && lastPage >= 4) {
					availablePages.add(4);
				}
				if (availablePages.size() == 4 && lastPage >= 5) {
					availablePages.add(5);
				}
			}
			pageData.setAvailablePages(availablePages);
			request.setAttribute("availablePages", availablePages);

		} catch (BaseVide | PasDePagesNegException e) {
			Logger logger = LoggerFactory.getLogger(DashboardServlet.class);
			logger.error(e.getMessage(), e);
		}
		if(error==false)
			this.getServletContext().getRequestDispatcher("/WEB-INF/views/dashboard.jsp").forward(request, response);
		else
			response.sendError(404);
	}

	protected int getPagination(int currentPage, int lastPage) {
		return Math.max(3, Math.min(lastPage - 2, currentPage));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println(request.getParameter("selection"));
		serviceComputer.deleteComputer(request.getParameter("selection"));
		doGet(request, response);
	}

}
