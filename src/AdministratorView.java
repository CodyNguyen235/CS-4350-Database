import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Text;

import java.sql.SQLException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.wb.swt.SWTResourceManager;

public class AdministratorView
{

	protected Shell shell;
	private Text txtTargutAdministratorView;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args)
	{
		try
		{
			AdministratorView window = new AdministratorView();
			window.open();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open()
	{
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
	 */
	protected void createContents()
	{
		shell = new Shell();
		shell.setMinimumSize(new Point(700, 500));
		shell.setSize(422, 261);
		shell.setText("Administrator View");

		txtTargutAdministratorView = new Text(shell, SWT.BORDER | SWT.CENTER);
		txtTargutAdministratorView.setFont(SWTResourceManager.getFont("Segoe UI", 32, SWT.NORMAL));
		txtTargutAdministratorView.setEnabled(false);
		txtTargutAdministratorView.setEditable(false);
		txtTargutAdministratorView.setText("Targut Administrator View");
		txtTargutAdministratorView.setBounds(48, 32, 576, 85);

		Button btnAddItem = new Button(shell, SWT.NONE);
		btnAddItem.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				AddItem addItem = new AddItem();
				addItem.open();
			}
		});
		btnAddItem.setText("Add Item");
		btnAddItem.setBounds(48, 176, 150, 100);

		Button btnRemoveItem = new Button(shell, SWT.NONE);
		btnRemoveItem.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				ViewCatalogue remove = new ViewCatalogue();
				DoubleclickRemove doubleclick = new DoubleclickRemove();
				try
				{	
					doubleclick.open();
					remove.open(true, false);
				} catch (ClassNotFoundException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnRemoveItem.setText("Remove Item");
		btnRemoveItem.setBounds(260, 176, 150, 100);

		Button btnViewCustomers = new Button(shell, SWT.NONE);
		btnViewCustomers.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				ViewCustomers customers = new ViewCustomers();
				try
				{
					customers.open();
				} catch (ClassNotFoundException | SQLException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnViewCustomers.setText("View Customers");
		btnViewCustomers.setBounds(474, 176, 150, 100);
		
		Button btnViewOrders = new Button(shell, SWT.NONE);
		btnViewOrders.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) 
			{
				ViewOrders orders = new ViewOrders();
				try
				{
					orders.open();
				} catch (ClassNotFoundException | SQLException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnViewOrders.setText("View Orders");
		btnViewOrders.setBounds(260, 320, 150, 100);
		
		Button btnCustomerView = new Button(shell, SWT.NONE);
		btnCustomerView.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				CustomerView customer = new CustomerView();
				shell.dispose();
				customer.open();
			}
		});
		btnCustomerView.setText("Customer View");
		btnCustomerView.setBounds(474, 320, 150, 100);

	}

}
