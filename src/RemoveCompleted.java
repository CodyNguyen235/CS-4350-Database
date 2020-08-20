

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class RemoveCompleted
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
			RemoveCompleted window = new RemoveCompleted();
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
		shell.setMinimumSize(new Point(450, 300));
		shell.setSize(142, 118);
		shell.setText("Remove Completed!");
		
		Label lblRemoveCompleted = new Label(shell, SWT.NONE);
		lblRemoveCompleted.setAlignment(SWT.CENTER);
		lblRemoveCompleted.setFont(SWTResourceManager.getFont("Segoe UI", 32, SWT.NORMAL));
		lblRemoveCompleted.setBounds(10, 43, 414, 71);
		lblRemoveCompleted.setText("Remove Completed!");
		
		Button btnOk = new Button(shell, SWT.NONE);
		btnOk.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.dispose();
			}
		});
		btnOk.setFont(SWTResourceManager.getFont("Segoe UI", 32, SWT.NORMAL));
		btnOk.setBounds(160, 164, 93, 63);
		btnOk.setText("OK");

	}

}
