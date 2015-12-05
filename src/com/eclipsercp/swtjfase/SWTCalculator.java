package com.eclipsercp.swtjfase;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;



public class SWTCalculator {
	public static void main(String[] args) {
		Display display = new Display ();
		/*final*/ Shell shell = new Shell (display);
		shell.setLayout(new FillLayout());
		
		
		/*final*/ TabFolder tabFolder = new TabFolder (shell, SWT.BORDER);
		
		//tabFolder.setLayout(new RowLayout());
		//Rectangle clientArea = shell.getClientArea ();
		//tabFolder.setLocation (clientArea.x+10, clientArea.y);
		TabItem itemCalculator = new TabItem (tabFolder, SWT.NONE);
		itemCalculator.setText ("Calculator" );
		
		Group calculatorGroup = new Group(tabFolder, SWT.NONE);
		calculatorGroup.setLayout(new GridLayout(3, false));
		/*Group  firstLineGroup =new Group (calculatorGroup, SWT.HORIZONTAL);
		firstLineGroup.setLayout(new FillLayout());*/
		GridData gridData = new GridData();
		gridData.horizontalAlignment = SWT.FILL;
		gridData.verticalAlignment = SWT.TOP;
		gridData.grabExcessHorizontalSpace = true;
		
		final Text firstNumber = new Text(calculatorGroup , SWT.BORDER );
		firstNumber.setLayoutData(gridData);
		
		final Combo combo = new Combo (calculatorGroup , SWT.READ_ONLY);
		combo.setItems (new String [] {"+", "-", "*","/"});
		combo.select(0);
		gridData = new GridData();
		gridData.horizontalAlignment = SWT.FILL;
		gridData.verticalAlignment = SWT.TOP;
		gridData.grabExcessHorizontalSpace = true;
		combo.setLayoutData(gridData);
		
		final Text secondNumber = new Text(calculatorGroup , SWT.BORDER );
		secondNumber.setLayoutData(gridData);
		
		
		
		/*Group  secondLineGroup =new Group (calculatorGroup, SWT.HORIZONTAL);
		secondLineGroup.setLayout(new FillLayout());*/
		
		final Button calculateOnTheFly =new Button(calculatorGroup, SWT.CHECK);
		calculateOnTheFly .setText("calculate on the fly");
		gridData = new GridData();
		gridData.horizontalSpan = 2;
		calculateOnTheFly.setLayoutData(gridData);
		final Button calculateButton = new Button(calculatorGroup, SWT.PUSH);
		calculateButton.setText("Calculate");
		gridData = new GridData();
		gridData.horizontalAlignment = SWT.FILL;
		gridData.grabExcessHorizontalSpace = true;
		gridData.verticalAlignment = SWT.FILL;
		gridData.grabExcessVerticalSpace = true;
		calculateButton.setLayoutData(gridData);
		
		
		/*Group thirdLineGroup =new Group(calculatorGroup, SWT.HORIZONTAL);
		thirdLineGroup.setLayout(new GridLayout(2, false));*/
		
		Label resultLabel= new Label(calculatorGroup,SWT.NONE);
		resultLabel.setText("Result:");
		final Text resultText =new Text(calculatorGroup,SWT.BORDER|SWT.READ_ONLY);
		/*GridData gridDataResult = new GridData();
		gridDataResult.horizontalAlignment = SWT.FILL;
		gridDataResult.grabExcessHorizontalSpace = true;
		
		resultText.setLayoutData(gridDataResult);*/
		gridData = new GridData();
		gridData.horizontalAlignment = SWT.FILL;
		gridData.grabExcessHorizontalSpace = true;
		/*gridData.verticalAlignment = SWT.FILL;
		gridData.grabExcessVerticalSpace = true;*/
		gridData.horizontalSpan = 2;
		resultText.setLayoutData(gridData);
		
		
		//resultText.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		itemCalculator.setControl(calculatorGroup);
		
				
		//itemCalculator.setControl(control);
		//FillLayout fillLayout = new FillLayout(SWT.VERTICAL);
		
		TabItem itemHistory = new TabItem (tabFolder, SWT.NONE);
		itemHistory.setText ("History");
		SashForm historySashForm = new SashForm(tabFolder, SWT.VERTICAL);
		final Text historyText =new Text(historySashForm,SWT.BORDER | SWT.MULTI |SWT.READ_ONLY);
		itemHistory.setControl(historySashForm);
		
		/*for (int i=0; i<6; i++) {
			TabItem item = new TabItem (tabFolder, SWT.NONE);
			item.setText ("TabItem " + i);
			Button button = new Button (tabFolder, SWT.PUSH);
			button.setText ("Page " + i);
			item.setControl (button);
		}*/
		
		VerifyListener verifyListener =new VerifyListener() {

	        @Override
	        public void verifyText(VerifyEvent e) {

	        	/*
	        	String string = e.text;
	        	System.out.println(string);
	            char[] chars = new char[string.length()];
	            string.getChars(0, chars.length, chars, 0);
	            for (int i = 0; i < chars.length; i++) {
	              if (!('0' <= chars[i] && chars[i] <= '9')) {
	                e.doit = false;
	                return;
	              }
	            }*/
	        	
	            Text text = (Text)e.getSource();

	            // get old text and create new text by using the VerifyEvent.text
	             String oldS = text.getText();
	            String newS = oldS.substring(0, e.start) + e.text + oldS.substring(e.end);

	            boolean isFloat = true;
	            try
	            {
	                Float.parseFloat(newS);
	            }
	            catch(NumberFormatException ex)
	            {
	                isFloat = false;
	            }

	            System.out.println("e.end "+e.end);
	            System.out.println("e.text "+e.text);
	            System.out.println("oldS "+oldS);
	            System.out.println("newS "+newS);
	            

	            if(!isFloat)
	                e.doit = false;
	            
	        }
	    };
	    firstNumber.addVerifyListener(verifyListener);
	    secondNumber.addVerifyListener(verifyListener);
	    
	    
	    ModifyListener modifyListener = new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent e) {
				// TODO Auto-generated method stub
				if(firstNumber.getText().isEmpty()){
					firstNumber.setText("0");
				}
				if(secondNumber.getText().isEmpty()){
					secondNumber.setText("0");
				}
				if(calculateOnTheFly.getSelection()){
	            	System.out.println(calculateOnTheFly.getSelection());
	            	calculateButton.notifyListeners(SWT.Selection, new Event());
	            }
				
			}
		};
	    
	    
	    firstNumber.addModifyListener(modifyListener);
	    secondNumber.addModifyListener(modifyListener);
	   
	    
	    
	    
	    
	   
	    		
	    		Listener calculateListener = new Listener() {
	        public void handleEvent(Event e) {
	            switch (e.type) {
	            case SWT.Selection:
	            	String firstNumberString = firstNumber.getText();
	            	String secondNumberString = secondNumber.getText();
	              if((!firstNumberString.equals(""))&&(!secondNumberString.equals(""))){
	            	  float firstNumberFloat=Float.parseFloat(firstNumberString);
	            	  float secondNumberFloat=Float.parseFloat(secondNumberString);
	            	  if (combo.getSelectionIndex()==3){
	            		  if(secondNumberFloat==0){
	            			  resultText.setText("division by zero");
	            			 
	            		  }else{
	            			  resultText.setText(String.valueOf(firstNumberFloat/secondNumberFloat));
	            		  
	            		  }
	            		  historyText.setText(historyText.getText()+firstNumberString+"/"+secondNumberString+"="+resultText.getText()+"\n");
	            	  }else{
	            		  int i=combo.getSelectionIndex();
	            		  switch (i){
	            		  case 0:
	            			  resultText.setText(String.valueOf(firstNumberFloat+secondNumberFloat));
	            			  historyText.setText(historyText.getText()+firstNumberString+"+"+secondNumberString+"="+resultText.getText()+"\n");
	            			  break;
	            		  case 1:
	            			  resultText.setText(String.valueOf(firstNumberFloat-secondNumberFloat));
	            			  historyText.setText(historyText.getText()+firstNumberString+"-"+secondNumberString+"="+resultText.getText()+"\n");
	            			  break;
	            		  case 2:
	            			  resultText.setText(String.valueOf(firstNumberFloat*secondNumberFloat));
	            			  historyText.setText(historyText.getText()+firstNumberString+"*"+secondNumberString+"="+resultText.getText()+"\n");
	            			  break;  
	            		  
	            		  }
	            	  }
	            	  
	              }else {
					resultText.setText("Fill the Numbers Fields");
				}
	            	
	              
	              break;
	            }
	        }
	    };
		
	    calculateButton.addListener(SWT.Selection,calculateListener);
	    
	    combo.addSelectionListener(new SelectionAdapter() {
	        @Override

	        public void widgetSelected(SelectionEvent e) {

	              //missing if statement        
	        	if(calculateOnTheFly.getSelection()) {
	        		
	        		calculateButton.notifyListeners(SWT.Selection, new Event());
	        	}
	            }
	        });
	    
	    calculateOnTheFly.addSelectionListener(new SelectionAdapter() {
	        @Override

	        public void widgetSelected(SelectionEvent e) {

	              //missing if statement        
	        	if(calculateOnTheFly.getSelection()) {
	        		
	        		calculateButton.setEnabled(false);
	        		calculateButton.notifyListeners(SWT.Selection, new Event());
	        	}else{
	        		
	        		calculateButton.setEnabled(true);
	        	}
	            }
	        });
	    
	    
		tabFolder.pack ();
		
		shell.pack();
		shell.setMinimumSize(shell.getSize());
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}

}
