package com.beerbuddy.core.configuration;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.junit.Test;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PersistentDatabaseConnectionTest {

	static Connection conn = null;
	static Statement stmt = null;
	static ResultSet rs = null;

	@BeforeClass
	public static void setUp() throws Exception {
		//conn = DriverManager.getConnection("jdbc:mysql://localhost/cool", "root", "root");
		conn = DriverManager.getConnection("jdbc:mysql://74.132.115.143:3306/cool", "dbadmin","root");

		stmt = conn.createStatement();

	}

	@Test
	public void A_canConnect() throws SQLException {
		boolean b = conn.isValid(5);
		assertThat(b, is(equalTo(true)));
	}

	@Test
	public void B_createTable() throws SQLException {
		String create = "CREATE TABLE cool.foo_beer( "
				+ "idfoo_beer INT NOT NULL,"
				+ "foo_type VARCHAR(45) NULL DEFAULT 'cool',"
				+ "foo_beer_name VARCHAR(45) NULL DEFAULT 'default',"
				+ "PRIMARY KEY (idfoo_beer));";

		stmt.executeUpdate(create);
	}

	@Test
	public void C_insert() throws SQLException {
		String insert1 = "INSERT INTO cool.foo_beer(idfoo_beer, foo_type, foo_beer_name)"
				+ " VALUES (1, 'type1', 'FOO');";
		String insert2 = "INSERT INTO cool.foo_beer(idfoo_beer, foo_type, foo_beer_name)"
				+ " VALUES (2, 'type2', 'FOO_BAR');";
		String insert3 = "INSERT INTO cool.foo_beer(idfoo_beer, foo_type, foo_beer_name)"
				+ " VALUES (3, 'type4', 'BLAH');";
		String insert4 = "INSERT INTO cool.foo_beer(idfoo_beer, foo_type, foo_beer_name)"
				+ " VALUES (4, 'type1', 'WOO');";
		String insert5 = "INSERT INTO cool.foo_beer(idfoo_beer, foo_type, foo_beer_name)"
				+ " VALUES (5, 'type4', 'RED');";
		String insert6 = "INSERT INTO cool.foo_beer(idfoo_beer, foo_type, foo_beer_name)"
				+ " VALUES (6, 'type1', 'PINK');";

		stmt.executeUpdate(insert1);
		stmt.executeUpdate(insert2);
		stmt.executeUpdate(insert3);
		stmt.executeUpdate(insert4);
		stmt.executeUpdate(insert5);
		stmt.executeUpdate(insert6);

	}

	@Test
	public void D_query() throws SQLException {

		String query = "SELECT idfoo_beer, foo_type, foo_beer_name FROM foo_beer WHERE foo_beer_name = 'PINK';";
		rs = stmt.executeQuery(query);

		rs.next();
		int id = rs.getInt("idfoo_beer");
		String type = rs.getString("foo_type");
		String name = rs.getString("foo_beer_name");

		assertThat(id, is(equalTo(6)));
		assertThat(type, is(equalTo("type1")));
		assertThat(name, is(equalTo("PINK")));

	}

	@Test
	public void E_dropTable() throws SQLException {
		String drop = "DROP TABLE foo_beer";
		stmt.executeUpdate(drop);

	}

	@AfterClass
	public static void cleanUp() throws SQLException {
		rs.close();
		stmt.close();
		conn.close();

	}

}