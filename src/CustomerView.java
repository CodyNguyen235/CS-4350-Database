import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import java.sql.SQLException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.wb.swt.SWTResourceManager;

public class CustomerView
{

	protected Shell shell;
	private Text txtTargutDatabaseSystem;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args)
	{
		try
		{
			CustomerView window = new CustomerView();

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
		shell.setSize(558, 316);
		shell.setText("Customer View");

		txtTargutDatabaseSystem = new Text(shell, SWT.BORDER | SWT.CENTER);
		txtTargutDatabaseSystem.setFont(SWTResourceManager.getFont("Segoe UI", 32, SWT.NORMAL));
		txtTargutDatabaseSystem.setEnabled(false);
		txtTargutDatabaseSystem.setEditable(false);
		txtTargutDatabaseSystem.setText("Targut Online System");
		txtTargutDatabaseSystem.setBounds(73, 34, 546, 77);

		Button btnPlaceAnOrder = new Button(shell, SWT.NONE);
		btnPlaceAnOrder.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				DoubleclickOrder doubleclick = new DoubleclickOrder();
				doubleclick.open();
				ViewCatalogue catalogue = new ViewCatalogue();
				try
				{
					catalogue.open(false, true);
				} catch (ClassNotFoundException | SQLException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnPlaceAnOrder.setText("Place an order");
		btnPlaceAnOrder.setBounds(277, 138, 150, 100);

		Button btnAdministratorMenu = new Button(shell, SWT.NONE);
		btnAdministratorMenu.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				AdministratorView administrator = new AdministratorView();
				shell.dispose();
				administrator.open();
				
			}
		});
		btnAdministratorMenu.setText("Administrator Menu");
		btnAdministratorMenu.setBounds(469, 271, 150, 100);

		Button btnViewCatalogue = new Button(shell, SWT.NONE);
		btnViewCatalogue.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				ViewCatalogue catalogue = new ViewCatalogue();
				try
				{
					catalogue.open(false, false);
				} catch (Exception e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnViewCatalogue.setText("View Catalogue");
		btnViewCatalogue.setBounds(73, 138, 150, 100);

		Button btnSearchItems = new Button(shell, SWT.NONE);
		btnSearchItems.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				SearchItem search = new SearchItem();
				search.open();
			}
		});
		btnSearchItems.setText("Search Items");
		btnSearchItems.setBounds(469, 138, 150, 100);

	}
}
