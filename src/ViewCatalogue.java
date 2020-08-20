import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.custom.ScrolledComposite;

import java.sql.SQLException;
import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

public class ViewCatalogue
{

	protected Shell shell;
	protected TargutDatabase db;
	private Table table;
	private Boolean remove, order;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args)
	{
		try
		{
			ViewCatalogue window = new ViewCatalogue();
			window.open(false, false);
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
	public void open(boolean remove, boolean order) throws ClassNotFoundException, SQLException
	{
		this.remove = remove;
		this.order = order;
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
		shell.setMinimumSize(new Point(950, 1000));
		shell.setSize(810, 1000);
		shell.setText("Catalogue");
		
		
		table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		
		
		
		table.setBounds(10, 10, 901, 941);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		//Creates the columns
		TableColumn idColumn = new TableColumn(table, SWT.CENTER);
	    TableColumn itemIdColumn = new TableColumn(table, SWT.CENTER);
	    TableColumn departmentColumn= new TableColumn(table, SWT.CENTER);
	    TableColumn itemNameColumn = new TableColumn(table, SWT.CENTER);
	    TableColumn priceColumn = new TableColumn(table, SWT.CENTER);
	    TableColumn onSaleColumn = new TableColumn(table, SWT.CENTER);
	    
	    //Names the columns in the header
	    idColumn.setText("ID ");
	    itemIdColumn.setText("Item ID");
	    departmentColumn.setText("Department");
	    itemNameColumn.setText("Item Name");
	    priceColumn.setText("Price");
	    onSaleColumn.setText("On Sale");
	    
	    //Sets the width of all the columns
	    idColumn.setWidth(40);
	    itemIdColumn.setWidth(135);
	    departmentColumn.setWidth(135);
	    itemNameColumn.setWidth(300);
	    priceColumn.setWidth(135);
	    onSaleColumn.setWidth(135);
	    
	    table.setHeaderVisible(true);
		
	    String[][] catalogue = db.getCatalogue();
	    int rows = catalogue.length;
	    
	    for (int i = 0; i < rows; i++)
	    {
	    	TableItem item = new TableItem(table, 0);
	    	item.setText(catalogue[i]);
	    }
	    
	    //Listener for if in remove mode
  		table.addMouseListener(new MouseAdapter() {
  			@Override
  			public void mouseDoubleClick(MouseEvent e) 
  			{
  				if (remove)
  				{
  					try
					{
						db = new TargutDatabase();
					} catch (ClassNotFoundException e2)
					{
						// TODO Auto-generated catch block
						e2.printStackTrace();
					} catch (SQLException e2)
					{
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
  					
  			        TableItem[] selection = table.getSelection();
  			        
  			        try
					{
						db.removeItem(selection);
					} catch (SQLException e1)
					{
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
  			        
  			        RemoveCompleted complete = new RemoveCompleted();
  			        complete.open();
  			        shell.dispose();
  			        
  				}
  				else if(order)
  				{
  					try
					{
						db = new TargutDatabase();
					} catch (ClassNotFoundException e2)
					{
						// TODO Auto-generated catch block
						e2.printStackTrace();
					} catch (SQLException e2)
					{
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
  					
  			        TableItem[] selection = table.getSelection();
  			        if(selection[0].getText(5).toUpperCase().equals("TRUE"))
  			        {
  			        	OrderItem orderItem = new OrderItem();
  	  			        orderItem.open(selection, db);
	  	  			    OrderCompleted complete = new OrderCompleted();
	  			        complete.open();
	  			        shell.dispose();
  			        }
  			        else
  			        {
  			        	NotForSale notForSale = new NotForSale();
  			        	notForSale.open();
  			        }
  			        
  			        
  			        
  			        
  			        
  				}
  			}
  		});

	}
}
