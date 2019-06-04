package cdb;

import static org.junit.Assert.assertEquals;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.excilys.cdb.WebUiObject.Page;
import com.excilys.cdb.exception.BaseVide;
import com.excilys.cdb.exception.RequeteSansResultatException;
import com.excilys.cdb.model.ModelComputer;
import com.excilys.cdb.persistance.DaoComputer;
import com.excilys.cdb.service.ServiceComputer;
import com.excilys.cdb.spring.AppConfig;

public class DaoComputerTest {
	DaoComputer daoComp;
	Page page;
	AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();

	@Before
	public void setUp() throws Exception {
		UTDatabase.getInstance().reload();
		page=Page.getInstance();
		ctx.register(AppConfig.class);
        ctx.refresh();
        this.daoComp = ctx.getBean(DaoComputer.class);

	}

	@Test
	public void selectTest() throws BaseVide {

		List<ModelComputer> listResultatObtenu;
		
		listResultatObtenu = daoComp.select(page);
		int tailleObtenu = listResultatObtenu.size();
		int tailleSouhaite = 2;
		assertEquals("test taille de liste d'ordis", tailleSouhaite, tailleObtenu);

	}

	@Test
	public void selectOneTest() throws RequeteSansResultatException {
		ModelComputer model = new ModelComputer();
		model.setId(0);
		model.setName("Test");
		model.setIntroduced(null);
		model.setDiscontinued(null);
		model.setCompany_id(null);

		int idAdded = daoComp.insert(model);

		ModelComputer modelTestObtenu = daoComp.selectOne(idAdded);
		model.setId(idAdded);
		assertEquals("test selectOne", model, modelTestObtenu);

		daoComp.delete(0);
	}

	@Test(expected = RequeteSansResultatException.class)
	public void selectOneTestException() throws RequeteSansResultatException {
		daoComp.selectOne(-1);

	}

}
