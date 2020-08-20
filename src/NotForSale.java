import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class NotForSale
{

	protected Shell shell;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args)
	{
		try
		{
			NotForSale window = new NotForSale();
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
		shell.setMinimumSize(new Point(200, 300));
		shell.setSize(450, 300);
		shell.setText("SWT Application");
		
		Label lblItemNotFor = new Label(shell, SWT.WRAP);
		lblItemNotFor.setAlignment(SWT.CENTER);
		lblItemNotFor.setFont(SWTResourceManager.getFont("Segoe UI", 30, SWT.NORMAL));
		lblItemNotFor.setBounds(51, 10, 326, 114);
		lblItemNotFor.setText("Item Not For Sale. Please Try Again.");
		
		Button btnOk = new Button(shell, SWT.NONE);
		btnOk.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) 
			{
				shell.dispose();
			}
		});
		btnOk.setFont(SWTResourceManager.getFont("Segoe UI", 30, SWT.NORMAL));
		btnOk.setBounds(161, 145, 115, 64);
		btnOk.setText("OK");

	}

}
