import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.ToolBar;

import java.sql.SQLException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Combo;

public class SearchItem
{

	protected Shell shell;
	boolean name, id, department;
	private Text nameText;
	private Text idText;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args)
	{
		try
		{
			SearchItem window = new SearchItem();
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
		name = false;
		department = false;
		id = false;
				
		
		
		shell = new Shell();
		shell.setMinimumSize(new Point(500, 500));
		shell.setSize(450, 300);
		shell.setText("SWT Application");
		
		Label lblSearchBy = new Label(shell, SWT.NONE);
		lblSearchBy.setFont(SWTResourceManager.getFont("Segoe UI", 30, SWT.NORMAL));
		lblSearchBy.setBounds(158, 10, 186, 60);
		lblSearchBy.setText("Search By:");
		
		Button btnItemName = new Button(shell, SWT.RADIO);
		btnItemName.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		btnItemName.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) 
			{
				name = true;
				id = false;
				department = false;
			}
		});
		btnItemName.setBounds(47, 54, 116, 34);
		btnItemName.setText("Item Name");
		
		Button btnId = new Button(shell, SWT.RADIO);
		btnId.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		btnId.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) 
			{
				id = true;
				name = false;
				department = false;
			}
		});
		btnId.setBounds(47, 141, 116, 34);
		btnId.setText("ID");
		
		Button btnDepartment = new Button(shell, SWT.RADIO);
		btnDepartment.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		btnDepartment.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) 
			{
				department = true;
				name = false;
				id = false;
			}
		});
		btnDepartment.setBounds(47, 228, 116, 34);
		btnDepartment.setText("Department");
		
		
		
		nameText = new Text(shell, SWT.BORDER);
		nameText.setBounds(57, 94, 381, 41);
		
		idText = new Text(shell, SWT.BORDER);
		idText.setBounds(57, 181, 381, 41);
		
		Button btnForSale = new Button(shell, SWT.CHECK);
		btnForSale.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		btnForSale.setBounds(47, 315, 116, 60);
		btnForSale.setText("For Sale");
		
		Button btnOrder = new Button(shell, SWT.CHECK);
		btnOrder.setText("Ascending Order By Price");
		btnOrder.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		btnOrder.setBounds(169, 315, 269, 60);
		
		Combo departmentText = new Combo(shell, SWT.NONE);
		departmentText.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		departmentText.setBounds(57, 271, 381, 38);
		departmentText.add("Office Supplies");
		departmentText.add("Furniture");
		departmentText.add("Technology");
		
		Button btnSearch = new Button(shell, SWT.NONE);
		btnSearch.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) 
			{
				if (name)
				{
					boolean onSale = btnForSale.getSelection();
					String order = null;
					if (btnOrder.getSelection())
					{
						order = "ASC";
					}
					else
					{
						order = "DESC";
					}
					ViewCatalogueSearch catalogue = new ViewCatalogueSearch();
					try
					{
						catalogue.open(true, false, false, nameText.getText(), order, onSale);
					} catch (ClassNotFoundException | SQLException e1)
					{
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					shell.dispose();
				}
				else if (id)
				{
					boolean onSale = btnForSale.getSelection();
					String order = null;
					if (btnOrder.getSelection())
					{
						order = "ASC";
					}
					else
					{
						order = "DESC";
					}
					ViewCatalogueSearch catalogue = new ViewCatalogueSearch();
					try
					{
						catalogue.open(false, true, false, idText.getText(), order, onSale);
					} catch (ClassNotFoundException | SQLException e1)
					{
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					shell.dispose();
				}
				else if (department)
				{
					boolean onSale = btnForSale.getSelection();
					String order = null;
					if (btnOrder.getSelection())
					{
						order = "ASC";
					}
					else
					{
						order = "DESC";
					}
					ViewCatalogueSearch catalogue = new ViewCatalogueSearch();
					try
					{
						catalogue.open(false, false, true, departmentText.getText(), order, onSale);
					} catch (ClassNotFoundException | SQLException e1)
					{
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					shell.dispose();
				}
			}
		});
		btnSearch.setFont(SWTResourceManager.getFont("Segoe UI", 20, SWT.NORMAL));
		btnSearch.setBounds(324, 391, 150, 60);
		btnSearch.setText("Search");
		
		

	}
}
