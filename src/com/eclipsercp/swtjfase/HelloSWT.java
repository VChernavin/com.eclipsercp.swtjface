package com.eclipsercp.swtjfase;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;;

public class HelloSWT {

	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);

		//mine code
		shell.setText("Hello");
		Label label = new Label (shell, SWT.CENTER);
		label.setText("Hello SWT");
		
	    label.pack();
	    
	    
	    
	   
	    
		shell.pack();
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}
}