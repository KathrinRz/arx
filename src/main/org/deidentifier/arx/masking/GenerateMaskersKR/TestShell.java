import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

/**
 * Erzeugt Shell, in der einzelne Maskerklassen aufgerufen werden können
 * @author Kathrin
 *
 */

public class TestShell {
	private Button btn1;

	
	public static void main(String[] args) {
		new TestShell();
	}

	TestShell() {
	    Display d = new Display();
	    Shell s = new Shell(d);
	    s.setSize(1000, 700);      // Shellgröße
	    s.setText("Shell zum Testen");  // Text in Fensterleiste
	    
	    /**
	     * Dropdownmenü, um zwischen verschiedenen Klassen zu wählen
	     */
	    
	    Combo cmb1 = new Combo (s, SWT.DROP_DOWN);
	    cmb1.setBounds(20, 20, 250, 30);
	    cmb1.add("ConfigConstantShiftDateMasker");
	    cmb1.add("ConfigConstantShiftDecimalMasker");
	    cmb1.add("ConfigGenerateRandomDataMasker");
	    cmb1.add("ConfigGenerateRandomDecimalMasker");
	    cmb1.add("ConfigGenerateRandomIntegerDecimalMasker");
	    cmb1.add("ConfigGenerateRandomStringMasker");
	    cmb1.add("ConfigMatchAndReplaceStringMasker");
	    cmb1.add("ConfigRandomShiftDateMasker");
	    cmb1.add("ConfigRandomShiftDecimalMasker");
	    cmb1.add("ConfigReplaceDictMasker");
	    cmb1.add("ConfigReplaceInstMasker");
	    cmb1.add("ConfigSplitAndReplaceStringMasker");
	    cmb1.select(0);
	    
	    /**
	     * Erzeugt Button + SelectionListener, mit dem man ausgewählte Klasse aufruft
	     */
	    
	    btn1 = new Button (s, SWT.NONE);
	    btn1.setBounds(70, 50, 150, 30);
	    btn1.setText("Parameter eingeben");
	    btn1.addSelectionListener(new SelectionAdapter(){
	    	public void widgetSelected(SelectionEvent e){
	    		int j = cmb1.getSelectionIndex();
	    		if (j==0){
	    			ConfigConstantShiftDateMasker g1 = new ConfigConstantShiftDateMasker (s, 400,0); 
	    		}
	    				    		
	    		if (j==1){
	    			ConfigConstantShiftDecimalMasker g1 = new ConfigConstantShiftDecimalMasker (s, 400,0);
	    		}
	    		
	    		if (j==2){
	    			ConfigGenerateRandomDataMasker g1 = new ConfigGenerateRandomDataMasker (s, 400,0);
	    		}
	    		
	    		if (j==3){
		    		ConfigGenerateRandomDecimalMasker g1 = new ConfigGenerateRandomDecimalMasker (s, 400,0);
		   		}
	    		
	    		if (j==4){
	    			ConfigGenerateRandomIntegerDecimalMasker g1 = new ConfigGenerateRandomIntegerDecimalMasker (s, 400,0);
	    		}
	    		
	    		
	    		if (j==5){
	    			ConfigGenerateRandomStringMasker g1 = new ConfigGenerateRandomStringMasker (s, 400,0);
	    		}
	    		
	    		if (j==6){
	    			ConfigMatchAndReplaceStringMasker g1 = new ConfigMatchAndReplaceStringMasker (s, 400,0);
	    		}
	    		
	    		if (j==7){
	    			ConfigRandomShiftDateMasker g1 = new ConfigRandomShiftDateMasker (s, 400,0);
	    		}
	    		
	    		if (j==8){
	    			ConfigRandomShiftDecimalMasker g1 = new ConfigRandomShiftDecimalMasker (s, 400,0);
	    		}

	    		
	    		if (j==9){
	    			ConfigReplaceDictMasker g1 = new ConfigReplaceDictMasker (s, 400,0);
	    		}
	    		
	    		
	    		if (j==10){
	    			ConfigReplaceInstMasker g1 = new ConfigReplaceInstMasker (s, 400,0);
	    		}
	    		
	    		if (j==11){
	    			ConfigSplitAndReplaceStringMasker g1 = new ConfigSplitAndReplaceStringMasker (s, 400,0);
	    		}
	    		
	    		
	    		
	    		
	    	}
	    });

	    s.open();
	    while (!s.isDisposed()) {
	      if (!d.readAndDispatch())
	        d.sleep();
	    }
	    d.dispose();
	  
}

}
	
