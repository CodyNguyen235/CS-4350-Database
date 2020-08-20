import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Label;

import java.sql.SQLException;

import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class AddItem
{

	protected Shell shell;
	private Text itemIdText;
	private Text itemNameText;
	private Text priceText;
	protected TargutDatabase db;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args)
	{
		try
		{
			AddItem window = new AddItem();
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
		shell.setMinimumSize(new Point(600, 500));
		shell.setSize(450, 300);
		shell.setText("SWT Application");
		
		Label lblAddItem = new Label(shell, SWT.NONE);
		lblAddItem.setFont(SWTResourceManager.getFont("Segoe UI", 32, SWT.NORMAL));
		lblAddItem.setBounds(183, 10, 185, 72);
		lblAddItem.setText("Add Item");
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		lblNewLabel.setBounds(10, 76, 78, 30);
		lblNewLabel.setText("Item ID\r\n");
		
		itemIdText = new Text(shell, SWT.BORDER);
		itemIdText.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		itemIdText.setBounds(10, 112, 564, 36);
		
		Label lblDepartment = new Label(shell, SWT.NONE);
		lblDepartment.setText("Department");
		lblDepartment.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		lblDepartment.setBounds(10, 154, 128, 30);
		
		Combo departmentText = new Combo(shell, SWT.NONE);
		departmentText.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		departmentText.setBounds(10, 190, 564, 38);
		departmentText.add("Office Supplies");
		departmentText.add("Furniture");
		departmentText.add("Technology");
		
		Label lblItemName = new Label(shell, SWT.NONE);
		lblItemName.setText("Item Name");
		lblItemName.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		lblItemName.setBounds(10, 234, 128, 30);
		
		itemNameText = new Text(shell, SWT.BORDER);
		itemNameText.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		itemNameText.setBounds(10, 270, 564, 36);
		
		Label lblPrice = new Label(shell, SWT.NONE);
		lblPrice.setText("Price\r\n");
		lblPrice.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		lblPrice.setBounds(10, 315, 128, 30);
		
		priceText = new Text(shell, SWT.BORDER);
		priceText.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		priceText.setBounds(10, 351, 564, 36);
		
		Button forSaleButton = new Button(shell, SWT.CHECK);
		forSaleButton.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		forSaleButton.setBounds(10, 393, 116, 34);
		forSaleButton.setText("For Sale");
		
		Button finishBtn = new Button(shell, SWT.NONE);
		finishBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) 
			{
				try
				{
					db = new TargutDatabase();
				} catch (ClassNotFoundException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				try
				{
					db.insertItem(itemIdText.getText(), departmentText.getText(), itemNameText.getText(), Double.valueOf(priceText.getText()), forSaleButton.getSelection());
				} catch (NumberFormatException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				shell.dispose();
				
			}
		});
		finishBtn.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		finishBtn.setBounds(420, 402, 154, 49);
		finishBtn.setText("Finish");

	}
}
