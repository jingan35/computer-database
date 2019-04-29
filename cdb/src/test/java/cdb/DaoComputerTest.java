package cdb;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import com.excilys.cdb.exception.BaseVide;
import com.excilys.cdb.model.ModelComputer;
import com.excilys.cdb.persistance.DaoComputer;

public class DaoComputerTest {
	DaoComputer daoComp = DaoComputer.getInstance();
	
	@Test
	public void selectTest() {
		try {
		ArrayList<ModelComputer> listResultatObtenu;
		
			listResultatObtenu = daoComp.select(1, 1);
			int tailleObtenu = listResultatObtenu.size();
			int tailleSouhaite =1;
			assertEquals("taille de liste d'ordis", tailleSouhaite, tailleObtenu);
		} 
		
		catch (BaseVide e) {
			// TODO Auto-generated catch block
		System.out.println(e.getMessage());
		}
	}
	
	@Test (expected = BaseVide.class)
	public void selectTestException() throws BaseVide{
		ArrayList<ModelComputer> listResultatObtenu = daoComp.select(0, 0);
		int tailleObtenu = listResultatObtenu.size();
		int tailleSouhaite =1;
		assertEquals("taille de liste d'ordis", tailleSouhaite, tailleObtenu);
	}

}
