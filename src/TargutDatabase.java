import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

import org.eclipse.swt.widgets.TableItem;

public class TargutDatabase
{

	static final String JDBC_DRIVER = "net.ucanaccess.jdbc.UcanaccessDriver";
	static final String targut = "Targut.accdb";
	static final String URL = "jdbc:ucanaccess://" + targut;

	// edit below url to the location of the db. this assumes it is in the same src
	// folder
	static final String DB_URL = "jdbc:ucanaccess://Targut.accdb;memory=true";

	Connection connection;
	Statement statement;
	ResultSet resultSet;

	TargutDatabase() throws ClassNotFoundException, SQLException
	{
		connection = null;
		statement = null;
		resultSet = null;

		Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
		connection = DriverManager.getConnection(DB_URL);
		statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
	}

	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException
	{
		TargutDatabase db = new TargutDatabase();

		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
		try
		{

			connection = DriverManager.getConnection(DB_URL);

			statement = connection.createStatement();

			// db.insertItem(connection); // THIS IS A TEST TO INSERT AN ITEM

			// db.insertCustomer(connection); // THIS IS A TEST TO INSERT A CUSTOMER

			// db.insertDepartment(connection); // THIS IS A TEST TO INSERT A DEPARTMENT

			resultSet = statement.executeQuery("SELECT * FROM ITEM");

			// ----------------------- FORMAT THIS TO FIT THE UI:
			// ---------------------------------------------------------
			System.out.println("ID\tItem ID\t    Item Name\tExp Date\tPrice\tStock\tOn Sale\t");
			System.out.println("===\t========\t==================\t============\t=========\t=======\t=======");

			// this prints to console, configure it to print to the UI
			while (resultSet.next())
			{
				System.out.println(resultSet.getInt(1) + "\t" + resultSet.getString(2) + "\t" + resultSet.getString(3) + "\t" + resultSet.getString(4)
						+ "\t" + resultSet.getString(5) + "\t" + resultSet.getString(6) + "\t" + resultSet.getString(7));

			}

			resultSet = statement.executeQuery("SELECT * FROM CUSTOMER");

			// ----------------------- FORMAT THIS TO FIT THE UI:
			// ---------------------------------------------------------
			System.out.println("ID\tCustomer ID\t Name\t Email\t Payment Method \tShipping Info\tConfirmation\tPhone\t");
			System.out.println("===\t========\t==================\t============\t=========\t=======\t=========\t===========");

			// this prints to console, configure it to print to the UI
			while (resultSet.next())
			{
				System.out.println(resultSet.getInt(1) + "\t" + resultSet.getString(2) + "\t" + resultSet.getString(3) + "\t" + resultSet.getString(4)
						+ "\t" + resultSet.getString(5) + "\t" + resultSet.getString(6) + "\t" + resultSet.getString(7) + "\t"
						+ resultSet.getString(8));

			}

			resultSet = statement.executeQuery("SELECT * FROM DEPARTMENT");

			// ----------------------- FORMAT THIS TO FIT THE UI:
			// ---------------------------------------------------------
			System.out.println("ID\t Department ID\t Department Name\t");
			System.out.println("===\t=========\t==================\t====");

			// this prints to console, configure it to print to the UI
			while (resultSet.next())
			{
				System.out.println(resultSet.getInt(1) + "\t" + resultSet.getString(2) + "\t" + resultSet.getString(3));

			}
		} catch (SQLException sqlex)
		{
			sqlex.printStackTrace();
		} finally
		{

			try
			{
				if (null != connection)
				{

					resultSet.close();
					statement.close();

					connection.close();
				}
			} catch (SQLException sqlex)
			{
				sqlex.printStackTrace();
			}
		}
	}

	String[][] getCatalogue() throws SQLException
	{
		String[][] catalogue = null;
		try
		{
			resultSet = statement.executeQuery("SELECT * FROM ITEM");

			int size = 0;

			if (resultSet != null)
			{
				resultSet.last(); // moves cursor to the last row
				size = resultSet.getRow(); // get row id
			}
			resultSet.beforeFirst();

			catalogue = new String[size][6];
			int row = 0;
			while (resultSet.next())
			{

				catalogue[row][0] = resultSet.getString(5);
				catalogue[row][1] = resultSet.getString(1);
				catalogue[row][2] = resultSet.getString(2);
				catalogue[row][3] = resultSet.getString(3);
				catalogue[row][4] = "$" + String.valueOf((Math.floor(Double.parseDouble(resultSet.getString(4)) * 100) / 100)); // truncate to 2
																																// decimal places
				catalogue[row][5] = resultSet.getString(6);
				row++;
			}
		} finally
		{

			try
			{
				if (null != connection)
				{

					resultSet.close();
					statement.close();

					connection.close();
				}
			} catch (SQLException sqlex)
			{
				sqlex.printStackTrace();
			}
		}

		return catalogue;
	}

	String[][] getCustomers() throws SQLException
	{
		
		String[][] customers = null;

		try
		{
			resultSet = statement.executeQuery("SELECT * FROM Customer");
	
			int size = 0;
	
			if (resultSet != null)
			{
				resultSet.last(); // moves cursor to the last row
				size = resultSet.getRow(); // get row id 'v
			}
			resultSet.beforeFirst();
	
			customers = new String[size][7];
			int row = 0;
			while (resultSet.next())
			{
	
				customers[row][0] = resultSet.getString(7);
				customers[row][1] = resultSet.getString(1);
				customers[row][2] = resultSet.getString(2);
				customers[row][3] = resultSet.getString(3);
				customers[row][4] = resultSet.getString(4);
				customers[row][5] = resultSet.getString(5);
				customers[row][6] = resultSet.getString(6);
				row++;
			}
		}finally
		{

			try
			{
				if (null != connection)
				{

					resultSet.close();
					statement.close();

					connection.close();
				}
			} catch (SQLException sqlex)
			{
				sqlex.printStackTrace();
			}
		}
		

		return customers;
	}
	
	String[][] getOrders() throws SQLException
	{
		
		String[][] orders = null;

		try
		{
			resultSet = statement.executeQuery("SELECT * FROM Order");
	
			int size = 0;
	
			if (resultSet != null)
			{
				resultSet.last(); // moves cursor to the last row
				size = resultSet.getRow(); // get row id 'v
			}
			resultSet.beforeFirst();
	
			orders = new String[size][6];
			int row = 0;
			while (resultSet.next())
			{
	
				orders[row][0] = resultSet.getString(6);
				orders[row][1] = resultSet.getString(1);
				orders[row][2] = "$" + String.valueOf((Math.floor(Double.parseDouble(resultSet.getString(2)) * 100) / 100)); //truncate to 2 decimal places
				orders[row][3] = resultSet.getString(3);
				orders[row][4] = resultSet.getString(4);
				orders[row][5] = resultSet.getString(5).substring(0,10);
				row++;
			}
		}finally
		{

			try
			{
				if (null != connection)
				{

					resultSet.close();
					statement.close();

					connection.close();
				}
			} catch (SQLException sqlex)
			{
				sqlex.printStackTrace();
			}
		}
		

		return orders;
	}
	
	String[][] getItemsByDepartment(String departmentString, String order, boolean onSale) throws SQLException
	{
		String statementString;
       
		statementString = "SELECT * \nFROM Item \nWHERE Department = '" + departmentString + "' AND [On Sale] = " + onSale + "\nORDER BY Price " + order;
        
		String[][] catalogue = null;
		try
		{
			resultSet = statement.executeQuery(statementString);

			int size = 0;

			if (resultSet != null)
			{
				resultSet.last(); // moves cursor to the last row
				size = resultSet.getRow(); // get row id
			}
			resultSet.beforeFirst();

			catalogue = new String[size][6];
			int row = 0;
			while (resultSet.next())
			{

				catalogue[row][0] = resultSet.getString(5);
				catalogue[row][1] = resultSet.getString(1);
				catalogue[row][2] = resultSet.getString(2);
				catalogue[row][3] = resultSet.getString(3);
				catalogue[row][4] = "$" + String.valueOf((Math.floor(Double.parseDouble(resultSet.getString(4)) * 100) / 100)); // truncate to 2
																																// decimal places
				catalogue[row][5] = resultSet.getString(6);
				row++;
			}
		} finally
		{

			try
			{
				if (null != connection)
				{

					resultSet.close();
					statement.close();

					connection.close();
				}
			} catch (SQLException sqlex)
			{
				sqlex.printStackTrace();
			}
		}

		return catalogue;
	}
	
	String[][] getItemsByName(String name, String order, boolean onSale) throws SQLException
	{
		String statementString;
       
		statementString = "SELECT * \nFROM Item \nWHERE [Item Name] LIKE '%" + name + "%' AND [On Sale] = " + onSale + "\nORDER BY Price " + order + ";";
        
		String[][] catalogue = null;
		try
		{
			resultSet = statement.executeQuery(statementString);

			int size = 0;

			if (resultSet != null)
			{
				resultSet.last(); // moves cursor to the last row
				size = resultSet.getRow(); // get row id
			}
			resultSet.beforeFirst();

			catalogue = new String[size][6];
			int row = 0;
			while (resultSet.next())
			{

				catalogue[row][0] = resultSet.getString(5);
				catalogue[row][1] = resultSet.getString(1);
				catalogue[row][2] = resultSet.getString(2);
				catalogue[row][3] = resultSet.getString(3);
				catalogue[row][4] = "$" + String.valueOf((Math.floor(Double.parseDouble(resultSet.getString(4)) * 100) / 100)); // truncate to 2
																																// decimal places
				catalogue[row][5] = resultSet.getString(6);
				row++;
			}
		} finally
		{

			try
			{
				if (null != connection)
				{

					resultSet.close();
					statement.close();

					connection.close();
				}
			} catch (SQLException sqlex)
			{
				sqlex.printStackTrace();
			}
		}

		return catalogue;
	}
	
	String[][] getItemsById(String id, String order, boolean onSale) throws SQLException
	{
		String statementString;
       
		statementString = "SELECT * \nFROM Item \nWHERE Id = " + " '" + id + "' AND [On Sale] = " + onSale + "\nORDER BY Price " + order + ";";
        
		String[][] catalogue = null;
		try
		{
			resultSet = statement.executeQuery(statementString);

			int size = 0;

			if (resultSet != null)
			{
				resultSet.last(); // moves cursor to the last row
				size = resultSet.getRow(); // get row id
			}
			resultSet.beforeFirst();

			catalogue = new String[size][6];
			int row = 0;
			while (resultSet.next())
			{

				catalogue[row][0] = resultSet.getString(5);
				catalogue[row][1] = resultSet.getString(1);
				catalogue[row][2] = resultSet.getString(2);
				catalogue[row][3] = resultSet.getString(3);
				catalogue[row][4] = "$" + String.valueOf((Math.floor(Double.parseDouble(resultSet.getString(4)) * 100) / 100)); // truncate to 2
																																// decimal places
				catalogue[row][5] = resultSet.getString(6);
				row++;
			}
		} finally
		{

			try
			{
				if (null != connection)
				{

					resultSet.close();
					statement.close();

					connection.close();
				}
			} catch (SQLException sqlex)
			{
				sqlex.printStackTrace();
			}
		}

		return catalogue;
	}
	
	


	// -------------------------------------INSERTION
	// METHODS--------------------------------------------------------
	void insertCustomer(Connection conn) throws ClassNotFoundException, SQLException, IOException
	{

		int custID = 99; // have the user able to enter this value
		String name = "'abc'"; // !!!!!!!!!! make sure any user entered string values have single quotes
								// attached to both ends. ie) abc would become 'abc'
		String email = "'abc'"; // have the user able to enter this value
		String paymentMethod = "'abc'"; // have the user able to enter this value
		String shipInfo = "'abc'"; // have the user able to enter this value
		boolean confirmation = true; // have the user able to enter this value
		int phone = 1234567; // have the user able to enter this value

		String stmt = "INSERT INTO Customer([Customer ID],[Cust Name],Email,[Payment Method],[Shipping Info],Confirmation,[Phone Number]) VALUES("
				+ custID + "," + name + "," + email + "," + paymentMethod + "," + shipInfo + "," + confirmation + "," + phone + ")";

		Statement s = conn.createStatement();

		s.executeUpdate(stmt);
		
		try
		{
			if (null != connection)
			{

				resultSet.close();
				statement.close();

				connection.close();
			}
		} catch (SQLException sqlex)
		{
			sqlex.printStackTrace();
		}

	}

	/** Not needed anymore
	void insertDepartment(Connection conn) throws ClassNotFoundException, SQLException
	{
		int deptID = 0; // have the user able to enter this value
		String deptName = "'abc'"; // have the user able to enter this value

		String stmt = "INSERT INTO Department([Dept ID],[Dept Name]) VALUES(" + deptID + "," + deptName + ")";

		Statement s = conn.createStatement();

		s.executeUpdate(stmt);

	}
	*/

	void insertItem(String itemId, String department, String itemName, double price, boolean onSale) throws ClassNotFoundException, SQLException
	{

		//String stmt = "INSERT INTO Item " + "VALUES(" + 2000 + itemId + "," + department + "," + itemName + "," + price + "," + onSale + ")";
		String stmt = "INSERT INTO Item ([Item ID], Department, [Item Name], Price, [On Sale]) " + "VALUES (\""+ itemId + "\",\"" + department + "\",\"" + itemName + "\",\"" + price + "\",\"" + onSale + "\")";

		//System.out.println(stmt);
		Statement s = connection.createStatement();

		s.executeUpdate(stmt);
		
		try
		{
			if (null != connection)
			{
				statement.close();
				connection.close();
			}
		} catch (SQLException sqlex)
		{
			sqlex.printStackTrace();
		}

	}

	/*
	// -------------------------------------------------MODIFICATION
	// METHODS----------------------------------------------
	void modifyTable(Connection conn) throws ClassNotFoundException, SQLException
	{
		String tableToUpdate = "Customer"; // these are test cases, update them to
		String columnToUpdate = "Shipping Info";
		String condition = "[Name]";
		String conditionValue = "'Cody'";
		String newValue = "'abcdefg'"; // need to be able to enter either int, string, double, or boolean

		String stmt = "UPDATE " + tableToUpdate + "\n" + "SET " + columnToUpdate + " = " + newValue + " WHERE " + condition + " = " + conditionValue;

		Statement s = conn.createStatement();

		s.executeUpdate(stmt);

	}
	*/
	
	void removeItem(TableItem[] selection) throws SQLException 
	{
		
		String stmt = "DELETE FROM item WHERE ID=\"" + selection[0].getText(0) + "\"";
		System.out.println(stmt);
		
		Statement s = connection.createStatement();
		
		s.executeUpdate(stmt);
		
		try
		{
			if (null != connection)
			{
				statement.close();
				connection.close();
			}
		} catch (SQLException sqlex)
		{
			sqlex.printStackTrace();
		}
	}
	
	void orderItem(TableItem[] selection, String address) throws SQLException 
	{
		Random random = new Random();
		double cost = Double.valueOf(selection[0].getText(4).substring(1,selection[0].getText(4).length()));
		String itemId = selection[0].getText(1);
		String date = java.time.LocalDate.now().toString();
		String orderId = "US-2019-" + (random.nextInt(900000) + 50000);
		
		String stmt = "INSERT INTO Order ([Order ID], [Total Cost], [Product ID], [Shipping Information], Date) " + "VALUES (\""+ orderId + "\",\"" + cost + "\",\"" + itemId + "\",\"" + address + "\",\"" + date + "\")";
		//System.out.println(stmt);
		
		Statement s = connection.createStatement();
		
		s.executeUpdate(stmt);
		
		try
		{
			if (null != connection)
			{
				statement.close();
				connection.close();
			}
		} catch (SQLException sqlex)
		{
			sqlex.printStackTrace();
		}
	}
	
	//--------------------------------------- QUERY METHODS ----------------------------------------
    void showAllItemsInDept(String departmentString, String organize, boolean onSale) throws ClassNotFoundException, SQLException {
        String statement;
        String department = departmentString; //Get this via user input //should be the department name
        String order = organize; //get this via user input; //should be "ASC" for ascending, etc
        Statement s = connection.createStatement();

        statement = "SELECT * \nFROM Item \nWHERE Department = " + " '" + departmentString + "' AND On_Sale = " + onSale + "\nORDER BY Price " + order;
        s.executeQuery(statement);

    }

    void showAllItemInDeptSale(Connection conn) throws ClassNotFoundException, SQLException {
        String statement;
        String departmentString = ""; //Get this via user input
        String Order = ""; //get this via user input;
        boolean sale = true; //
        Statement s = conn.createStatement();

        statement = "SELECT * \nFROM Item \nWHERE Department = '" + departmentString + "'" + "AND On_Sale = " + sale
                + "\nORDER BY Price " + Order + ";";
        s.executeQuery(statement);

    }
    void itemNameOnly(Connection conn, String order, boolean onSale) throws ClassNotFoundException, SQLException {
        String statement;
        String Item_Name = ""; //Get this via user input
         //
         //
        Statement s = conn.createStatement();

        statement = "SELECT * \nFROM Item \nWHERE Item_Name = " + " '" + Item_Name + "' AND On_Sale = " + onSale + "\nORDER BY Price " + order + ";";
        s.executeQuery(statement);

    }
    void itemIDOnly(Connection conn) throws ClassNotFoundException, SQLException {
        String statement;
        int ID = 0; //Get this via user input
         //
         //
        Statement s = conn.createStatement();

        statement = "SELECT * \nFROM Item \nWHERE ID = " + " '" + ID + "'" + ";";
        s.executeQuery(statement);

    }


}
