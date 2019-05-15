package cdb;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Date;
import java.util.*;
import java.util.stream.Collectors;

import com.excilys.cdb.persistance.DAOFactory;
import com.excilys.cdb.model.ModelCompany;
import com.excilys.cdb.model.ModelComputer;

public class UTDatabase {

	private static final String ENTRIES_SQL = "entriesUT.sql";
	private static final String SCHEMA_SQL = "schema.sql";
	private static UTDatabase INSTANCE = null;

	private Map<Integer, ModelCompany> companies = new TreeMap<>();
	private Map<Integer, ModelComputer> computers = new TreeMap<>();

	private UTDatabase() {
		addCompanies();
		addComputers();

	}

	public static UTDatabase getInstance() throws ClassNotFoundException, SQLException {
		if (INSTANCE == null) {

			INSTANCE = new UTDatabase();
		}
		return INSTANCE;
	}

	private void addCompanies() {
		addCompany(1, "Apple Inc.");
		addCompany(2, "Thinking Machines");
		addCompany(3, "RCA");
		addCompany(4, "Netronics");
		addCompany(5, "Tandy Corporation");
		addCompany(6, "Commodore International");
		addCompany(7, "MOS Technology");
		addCompany(8, "Micro Instrumentation and Telemetry Systems");
		addCompany(9, "IMS Associates, Inc.");
		addCompany(10, "Digital Equipment Corporation");
	}

	private void addCompany(int id, String name) {
		final ModelCompany company = new ModelCompany(id, name);
		companies.put(id, company);
	}

	private void addComputers() {
		addComputer(1, "MacBook Pro 15.4 inch", null, null, 1);
		addComputer(2, "CM-2a", null, null, 2);
		addComputer(3, "CM-200", null, null, 2);
		addComputer(4, "CM-5e", null, null, 2);
		addComputer(5, "CM-5", Timestamp.valueOf("1991-1-1"+" 12:00:00"), null, 2);
		addComputer(6, "MacBook Pro", Timestamp.valueOf("2006-1-10"+" 12:00:00"), null, 1);
		addComputer(7, "Apple IIe", null, null, -1);
		addComputer(8, "Apple IIc", null, null, -1);
		addComputer(9, "Apple IIGS", null, null, -1);
		addComputer(10, "Apple IIc Plus", null, null, -1);
		addComputer(11, "Apple II Plus", null, null, -1);
		addComputer(12, "Apple III", Timestamp.valueOf("1980-5-1"+" 12:00:00"), Timestamp.valueOf("1984-4-1"+" 12:00:00"), 1);
		addComputer(13, "Apple Lisa", null, null, 1);
		addComputer(14, "CM-2", null, null, 2);
		addComputer(15, "Connection Machine", Timestamp.valueOf("1987-1-1"+" 12:00:00"), null, 2);
		addComputer(16, "Apple II", Timestamp.valueOf("1977-4-1"+" 12:00:00"), Timestamp.valueOf("1993-10-1"+" 12:00:00"), 1);
		addComputer(17, "Apple III Plus", Timestamp.valueOf("1983-12-1"+" 12:00:00"), Timestamp.valueOf("1984-4-1"+" 12:00:00"), 1);
		addComputer(18, "COSMAC ELF", null, null, 3);
		addComputer(19, "COSMAC VIP", Timestamp.valueOf("1977-1-1"+" 12:00:00"), null, 3);
		addComputer(20, "ELF II", Timestamp.valueOf("1977-1-1"+" 12:00:00"), null, 4);
	}

	private void addComputer(int id, String name, Timestamp introduced, Timestamp discontinued, int companyId) {

		final ModelComputer computer = new ModelComputer(id, name, introduced, discontinued, companyId);
		computers.put(id, computer);
	}

	private static void executeScript(String filename) throws SQLException, IOException {
		try (final Connection connection = DAOFactory.getInstance().getConnection();
				final Statement statement = connection.createStatement();
				final InputStream resourceAsStream = UTDatabase.class.getClassLoader().getResourceAsStream(filename);
				final Scanner scanner = new Scanner(resourceAsStream)) {

			StringBuilder sb = new StringBuilder();
			while (scanner.hasNextLine()) {
				final String nextLine = scanner.nextLine();
				sb.append(nextLine);
			}
			final StringTokenizer stringTokenizer = new StringTokenizer(sb.toString(), ";");

			while (stringTokenizer.hasMoreTokens()) {
				final String nextToken = stringTokenizer.nextToken();
				statement.execute(nextToken);
			}
		}
	}

	public ModelCompany findCompanyById(int id) {
		return Objects.nonNull(id) ? companies.get(id) : null;
	}

	public ModelComputer findComputerById(int id) {
		return computers.get(id);
	}

	public List<ModelComputer> findAllComputers() {
		return computers.values().stream().sorted(Comparator.comparingLong(ModelComputer::getId))
				.collect(Collectors.toList());
	}

	public List<ModelComputer> findAllComputers(int offset, int limit) {
		return findAllComputers().stream().skip(offset).limit(limit).collect(Collectors.toList());
	}

	public List<ModelCompany> findAllCompanies() {
		return companies.values().stream().sorted(Comparator.comparingLong(ModelCompany::getId))
				.collect(Collectors.toList());
	}

	public void reload() throws IOException, SQLException {
		executeScript(SCHEMA_SQL);
		executeScript(ENTRIES_SQL);
	}

	public List<ModelCompany> findAllCompanies(int offset, int limit) {
		return findAllCompanies().stream().skip(offset).limit(limit).collect(Collectors.toList());
	}

}