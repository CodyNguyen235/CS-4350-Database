import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.custom.ScrolledComposite;

import java.sql.SQLException;
import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

public class ViewCustomers
{

	protected Shell shell;
	protected TargutDatabase db;
	private Table table;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args)
	{
		try
		{
			ViewCustomers window = new ViewCustomers();
			window.open();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public void open() throws ClassNotFoundException, SQLException
	{
		db = new TargutDatabase();
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed())
		{
			if (!display.readAndDispatch())
			{
				display.sleep();
			}
		}
		
		
	}

	/**
	 * Create contents of the window.
	 * @throws SQLException 
	 */
	protected void createContents() throws SQLException
	{
		shell = new Shell();
		shell.setMinimumSize(new Point(1100, 1000));
		shell.setSize(810, 1000);
		shell.setText("Customers");
		
		
		table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(10, 10, 1054, 941);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		//Creates the columns
		TableColumn idColumn = new TableColumn(table, SWT.CENTER);
	    TableColumn ssnColumn = new TableColumn(table, SWT.CENTER);
	    TableColumn nameColumn= new TableColumn(table, SWT.CENTER);
	    TableColumn emailColumn = new TableColumn(table, SWT.CENTER);
	    TableColumn paymentColumn = new TableColumn(table, SWT.CENTER);
	    TableColumn shippingColumn = new TableColumn(table, SWT.CENTER);
	    TableColumn phoneColumn = new TableColumn(table, SWT.CENTER);
	    
	    //Names the columns in the header
	    idColumn.setText("ID");
	    ssnColumn.setText("SSN");
	    nameColumn.setText("Name");
	    emailColumn.setText("Email");
	    paymentColumn.setText("Payment Method");
	    shippingColumn.setText("Address");
	    phoneColumn.setText("Phone Number");
	    
	    //Sets the width of all the columns
	    idColumn.setWidth(40);
	    ssnColumn.setWidth(135);
	    nameColumn.setWidth(135);
	    emailColumn.setWidth(300);
	    paymentColumn.setWidth(135);
	    shippingColumn.setWidth(135);
	    phoneColumn.setWidth(135);
	    
	    table.setHeaderVisible(true);
		
	    String[][] customers = db.getCustomers();
	    int rows = customers.length;
	    
	    for (int i = 0; i < rows; i++)
	    {
	    	TableItem item = new TableItem(table, SWT.NONE);
	    	item.setText(customers[i]);
	    }

	    

	}
}
