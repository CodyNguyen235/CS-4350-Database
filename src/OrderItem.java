import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Label;

import java.sql.SQLException;

import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class OrderItem
{

	protected Shell shell;
	private Text addressInput;
	private TableItem[] selection;
	private TargutDatabase db;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args)
	{
		try
		{
			OrderItem window = new OrderItem();
			window.open(null, null);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open(TableItem[] selection, TargutDatabase db)
	{
		this.db = db;
		this.selection = selection;
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
		shell.setMinimumSize(new Point(450, 200));
		shell.setSize(63, 10);
		shell.setText("SWT Application");
		
		Label lblWhereToShip = new Label(shell, SWT.NONE);
		lblWhereToShip.setFont(SWTResourceManager.getFont("Segoe UI", 20, SWT.NORMAL));
		lblWhereToShip.setBounds(106, 11, 215, 37);
		lblWhereToShip.setText("Where to ship to?");
		
		addressInput = new Text(shell, SWT.BORDER);
		addressInput.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		addressInput.setBounds(10, 54, 414, 44);
		
		Button btnOk = new Button(shell, SWT.NONE);
		btnOk.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) 
			{
				try
				{
					db.orderItem(selection, addressInput.getText());
				} catch (SQLException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				shell.dispose();
			}
		});
		btnOk.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		btnOk.setBounds(171, 104, 88, 47);
		btnOk.setText("OK");

	}
}
