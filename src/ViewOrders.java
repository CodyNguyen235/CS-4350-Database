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

public class ViewOrders
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
			ViewOrders window = new ViewOrders();
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
		shell.setText("orders");
		
		
		table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(10, 10, 1054, 941);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		//Creates the columns
		TableColumn idColumn = new TableColumn(table, SWT.CENTER);
	    TableColumn orderIdColumn = new TableColumn(table, SWT.CENTER);
	    TableColumn totalCostColumn= new TableColumn(table, SWT.CENTER);
	    TableColumn productIdColumn = new TableColumn(table, SWT.CENTER);
	    TableColumn shippingInfoColumn = new TableColumn(table, SWT.CENTER);
	    TableColumn dateColumn = new TableColumn(table, SWT.CENTER);
	    
	    //Names the columns in the header
	    idColumn.setText("ID");
	    orderIdColumn.setText("Order ID");
	    totalCostColumn.setText("Total Cost");
	    productIdColumn.setText("Product ID");
	    shippingInfoColumn.setText("Shipping Information");
	    dateColumn.setText("Date");
	    
	    //Sets the width of all the columns
	    idColumn.setWidth(40);
	    orderIdColumn.setWidth(135);
	    totalCostColumn.setWidth(135);
	    productIdColumn.setWidth(300);
	    shippingInfoColumn.setWidth(135);
	    dateColumn.setWidth(135);
	    
	    table.setHeaderVisible(true);
		
	    String[][] orders = db.getOrders();
	    int rows = orders.length;
	    
	    for (int i = 0; i < rows; i++)
	    {
	    	TableItem item = new TableItem(table, SWT.NONE);
	    	item.setText(orders[i]);
	    }

	    

	}
}
