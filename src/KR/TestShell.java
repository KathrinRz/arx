import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class TestShell {

	public static void main(String[] args) {
		new TestShell();
	}

	TestShell() {
	    Display d = new Display();
	    Shell s = new Shell(d);
	    s.setSize(500, 500);      // Shellgröße
	    s.setText("Shell zum Testen");  // Text in Fensterleiste
	    
	    Combo combo1 = new Combo (s, SWT.DROP_DOWN);
	    combo1.setBounds(20, 20, 250, 30);
	    combo1.add("ConfigGenerateRandomDataMasker");
	    combo1.add("ConfigGenerateRandomDecimalMasker");
	    combo1.add("ConfigGenerateRandomIntegerDecimalMasker");
	    combo1.add("ConfigGenerateRandomStringMasker");
	    combo1.add("ConfigConstantShiftDateMasker");
	    combo1.add("ConfigConstantShiftDecimalMasker");
	    combo1.add("ConfigReplaceInstMasker");
	    combo1.add("ConfigSplitAndReplaceStringMasker");
	    combo1.add("ConfigRandomShiftDateMasker");
	    combo1.add("ConfigRandomShiftDecimalMasker");
	    combo1.add("ConfigReplaceDictMasker");
	    combo1.add("ConfigMatchAndReplaceStringMasker");
	    combo1.select(0);
	    
	    Text text1 = new Text(s,SWT.NONE);
	    text1.setBounds(20, 200, 150, 20);
	    Text text2 = new Text(s,SWT.NONE);
	    text2.setBounds(20, 230, 150, 20);
	    Text text3 = new Text (s, SWT.NONE);
	    text3.setBounds(20, 260, 150, 20);
	    Text text4 = new Text (s, SWT.NONE);
	    text4.setBounds(20, 290, 150, 20);
	    Text text5 = new Text (s, SWT.NONE);
	    text5.setBounds(20, 320, 150, 20);
	    Text text6 = new Text (s, SWT.NONE);
	    text6.setBounds(20, 350, 150, 20);
	    
	    
	    
	    Button b1 = new Button (s, SWT.NONE);
	    b1.setBounds(70, 50, 150, 30);
	    b1.setText("Parameter eingeben");
	    b1.addSelectionListener(new SelectionAdapter(){
	    	public void widgetSelected(SelectionEvent e){
	    		int j = combo1.getSelectionIndex();
	    		
	    		if (j==0){
	    			final ConfigGenerateRandomDataMasker g1 = new ConfigGenerateRandomDataMasker (d, 50,100);
	    			MaskerData i = g1.Open(d);
	    			text1.setText(i.getZahl1());
	    			text2.setText(i.getZahl2());
	    			text3.setText(i.getZahl3());
	    			text4.setText(i.getZahl4());
	    			text5.setText(i.getZahl5());
	    			text6.setText(i.getZahl6());
	    		}
	    		if (j==1){
		    		final ConfigGenerateRandomDecimalMasker g1 = new ConfigGenerateRandomDecimalMasker (d, 50,100);
		    		MaskerData i = g1.Open(d);
		    		text1.setText(i.getZahl1());
		    		text2.setText(i.getZahl2());
		    		text3.setText(i.getZahl3());
		    		text4.setText(i.getZahl4());
		    		text5.setText(i.getZahl5());
		    		text6.setText(i.getZahl6());
		    		
		   		}
	    		if (j==2){
	    			final ConfigGenerateRandomIntegerDecimalMasker g1 = new ConfigGenerateRandomIntegerDecimalMasker (d, 50,100);
	    			MaskerData i = g1.Open(d);
	    			text1.setText(i.getZahl1());
	    			text2.setText(i.getZahl2());
	    			text3.setText(i.getZahl3());
	    			text4.setText(i.getZahl4());
	    			text5.setText(i.getZahl5());
	    			text6.setText(i.getZahl6());
	    		}
	    		
	    		if (j==3){
	    			final ConfigGenerateRandomStringMasker g1 = new ConfigGenerateRandomStringMasker (d, 50,100);
	    			MaskerData i = g1.Open(d);
	    			text1.setText(i.getZahl1());
	    			text2.setText(i.getZahl2());
	    			text3.setText(i.getZahl3());
	    			text4.setText(i.getZahl4());
	    			text5.setText(i.getZahl5());
	    			text6.setText(i.getZahl6());
	    		}
	    		
	    		if (j==4){
	    			final ConfigConstantShiftDateMasker g1 = new ConfigConstantShiftDateMasker (d, 50,100);
	    			MaskerData i = g1.Open(d);
	    			text1.setText(i.getZahl1());
	    			text2.setText(i.getZahl2());
	    			text3.setText(i.getZahl3());
	    			text4.setText(i.getZahl4());
	    			text5.setText(i.getZahl5());
	    			text6.setText(i.getZahl6());
	    		}
	    		if (j==5){
	    			final ConfigConstantShiftDecimalMasker g1 = new ConfigConstantShiftDecimalMasker (d, 50,100);
	    			MaskerData i = g1.Open(d);
	    			text1.setText(i.getZahl1());
	    			text2.setText(i.getZahl2());
	    			text3.setText(i.getZahl3());
	    			text4.setText(i.getZahl4());
	    			text5.setText(i.getZahl5());
	    			text6.setText(i.getZahl6());
	    		}
	    		if (j==6){
	    			final ConfigReplaceInstMasker g1 = new ConfigReplaceInstMasker (d, 50,100);
	    			MaskerData i = g1.Open(d);
	    			text1.setText(i.getZahl1());
	    			text2.setText(i.getZahl2());
	    			text3.setText(i.getZahl3());
	    			text4.setText(i.getZahl4());
	    			text5.setText(i.getZahl5());
	    			text6.setText(i.getZahl6());
	    		}
	    		if (j==7){
	    			final ConfigSplitAndReplaceStringMasker g1 = new ConfigSplitAndReplaceStringMasker (d, 50,100);
	    			MaskerData i = g1.Open(d);
	    			text1.setText(i.getZahl1());
	    			text2.setText(i.getZahl2());
	    			text3.setText(i.getZahl3());
	    			text4.setText(i.getZahl4());
	    			text5.setText(i.getZahl5());
	    			text6.setText(i.getZahl6());
	    		}
	    		if (j==8){
	    			final ConfigRandomShiftDateMasker g1 = new ConfigRandomShiftDateMasker (d, 50,100);
	    			MaskerData i = g1.Open(d);
	    			int n = i.getZahlN();
	    			text1.setText(i.getZahl1());
	    			text2.setText(i.getZahl2());
	    			text3.setText(i.getZahl3());
	    			text4.setText(i.getZahl4());
	    			text5.setText(i.getZahl5());
	    			text6.setText(i.getZahl6());
	    		}
	    		if (j==9){
	    			final ConfigRandomShiftDecimalMasker g1 = new ConfigRandomShiftDecimalMasker (d, 50,100);
	    			MaskerData i = g1.Open(d);
	    			text1.setText(i.getZahl1());
	    			text2.setText(i.getZahl2());
	    			text3.setText(i.getZahl3());
	    			text4.setText(i.getZahl4());
	    			text5.setText(i.getZahl5());
	    			text6.setText(i.getZahl6());
	    		}
	    		if (j==10){
	    			final ConfigReplaceDictMasker g1 = new ConfigReplaceDictMasker (d, 50,100);
	    			MaskerData i = g1.Open(d);
	    			text1.setText(i.getZahl1());
	    			text2.setText(i.getZahl2());
	    			text3.setText(i.getZahl3());
	    			text4.setText(i.getZahl4());
	    			text5.setText(i.getZahl5());
	    			text6.setText(i.getZahl6());
	    		}
	    		if (j==11){
	    			final ConfigMatchAndReplaceStringMasker g1 = new ConfigMatchAndReplaceStringMasker (d, 50,100);
	    			MaskerData i = g1.Open(d);
	    			text1.setText(i.getZahl1());
	    			text2.setText(i.getZahl2());
	    			text3.setText(i.getZahl3());
	    			text4.setText(i.getZahl4());
	    			text5.setText(i.getZahl5());
	    			text6.setText(i.getZahl6());
	    		}
	    		
	    	}
	    });
	    
	    
	    //final GIntegerDistribution g1 = new GIntegerDistribution(s,50,100);  
	    //final ConfigRandomIntegerDecimalMasker c1 = new ConfigRandomIntegerDecimalMasker(s,5);
	    //final ConfigRandomDecimalMasker c2 = new ConfigRandomDecimalMasker(s,10);
	    //final ConfigRandomShiftDecimalMasker c3 = new ConfigRandomShiftDecimalMasker (s);        
	    
	    s.open();
	    while (!s.isDisposed()) {
	      if (!d.readAndDispatch())
	        d.sleep();
	    }
	    d.dispose();
	  
}

}
	
