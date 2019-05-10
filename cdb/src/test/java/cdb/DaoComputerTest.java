package cdb;

import static org.junit.Assert.assertEquals;

import java.sql.Timestamp;
import java.util.ArrayList;

import org.junit.Test;

import com.excilys.cdb.exception.BaseVide;
import com.excilys.cdb.exception.RequeteSansResultatException;
import com.excilys.cdb.model.ModelComputer;
import com.excilys.cdb.persistance.DaoComputer;

public class DaoComputerTest {
	DaoComputer daoComp = DaoComputer.getInstance();
	
	@Test
	public void selectTest() throws BaseVide {
		
		ArrayList<ModelComputer> listResultatObtenu;
		
			listResultatObtenu = daoComp.select(2, 1,null);
			int tailleObtenu = listResultatObtenu.size();
			int tailleSouhaite =2;
			assertEquals("test taille de liste d'ordis", tailleSouhaite, tailleObtenu);
		
		
	}
	
	@Test
	public void selectOneTest() throws RequeteSansResultatException {
		ModelComputer model= new ModelComputer();
		model.setId(0);
		model.setName("Test");
		model.setIntroduced(null);
		model.setDiscontinued(null);
		model.setCompanyId(null);
		
		int idAdded=daoComp.insert(model);
		
		ModelComputer modelTestObtenu= daoComp.selectOne(idAdded);
		model.setId(idAdded);
		assertEquals("test selectOne", model, modelTestObtenu);
		
		daoComp.delete(0);
	}
	
	@Test (expected = RequeteSansResultatException.class)
	public void selectOneTestException() throws RequeteSansResultatException {
		daoComp.selectOne(-1);
		
	}
	
	
	

}
