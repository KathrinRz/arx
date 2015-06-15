import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * Enthält alle diskreten Verteilungsfunkionen + jeweilig geforderte Parameter
 * @author Kathrin
 *
 */
public class DiscreteDistributionSelection extends Composite{
	
	public boolean eingabeOK=true;
	private Combo cmb2;
	private Label lblParam1;
	private Label lblParam2;
	private Label lblParam3;
	private Label lblDistribution;
	private Text txtParam1;
	private Text txtParam2;
	private Text txtParam3;

	/**
	 * Erzeugt Label + Textfeld für Verteilung; mit int typ wird für jedes Textfeld der richtige RegEx aufgerufen(int=0,double=1)
	 */
	
	public void setLabelText(Label lbl1,int lx1, int lx2, int lx3, int lx4,Text txt1,int tx1, int tx2, int tx3, int tx4, String lab1, String val1,int typ){
			lbl1.setBounds(lx1,lx2,lx3,lx4);
			lbl1.setText(lab1);
			txt1.setBounds(tx1,tx2,tx3,tx4);
	        txt1.setText(val1);
	        if(typ==0){
	        	txt1.addModifyListener(new ModifyListener(){
	        		public void modifyText(ModifyEvent e){
	        			if(RegEx.regExInt(txt1.getText())){
	        				eingabeOK=true;	        				
	        			}
	        			else {
	        				eingabeOK=false;
	        			}
	        		}
	        });
	 		}
	        if(typ==1){
	        	txt1.addModifyListener(new ModifyListener(){
	        		public void modifyText(ModifyEvent e){
	        			if(RegEx.regExDouble(txt1.getText())){
	        				eingabeOK=true;	        				
	        			}
	        			else {
	        				eingabeOK=false;
	        			}
	        		}
	        });
	 		}
	        
	        /**
	         * Wenn das Label eines Parameter der Verteilung leer ist, werden das entsprechende Label und Textfeld nicht angezeigt
	         * (bsp. "GeometricDistribution" braucht nur einen Parameter, "HypergeometricDistribution" braucht 3)
	         */
	  
	if(lab1==""){
		lbl1.setVisible(false);
		txt1.setVisible(false);
	}
	else{
		lbl1.setVisible(true);
		txt1.setVisible(true);
		
	}
}

	/**
	 * Rückgabe der Eingabewerte an aufrufende Klasse	 
	 * @return
	 */
	 
	 public String getText1(){
			return txtParam1.getText();
		}
	 public String getText2(){
			return txtParam2.getText();
		}
	 public String getText3(){
			return txtParam3.getText();
		}
	 /**
	  * Rückgabe ob Eingabe ok an aufrufende Klasse	 
	  * @return
	  */
	 public boolean getOK(){
		 return eingabeOK;
	 }
	
DiscreteDistributionSelection(Composite s1,int style){
	super(s1,style);
	lblDistribution=new Label (s1, SWT.NONE);
	lblDistribution.setText("Distribution");
	lblDistribution.setBounds(20, 100, 80, 20);
	
	/**
	 * Dropdownmenü für Auswahl Verteilungen wird erstellt
	 */
	
    cmb2=new Combo(s1, SWT.DROP_DOWN);
	cmb2.add("BinomialDistribution");
	cmb2.add("EnumeratedIntegerDistribution");
	cmb2.add("GeometricDistribution");
	cmb2.add("HypergeometricDistribution");
	cmb2.add("PascalDistribution");
	cmb2.add("PoissonDistribution");
	cmb2.add("UniformIntegerDistribution");
	cmb2.add("ZipfDistribution");
	cmb2.setBounds(20,120,200,20);
	lblParam1=new Label(s1, SWT.NONE);
	lblParam2=new Label(s1, SWT.NONE);
	lblParam3=new Label(s1, SWT.NONE);
	txtParam1=new Text(s1, SWT.BORDER);
    txtParam2=new Text(s1, SWT.BORDER);
    txtParam3=new Text(s1, SWT.BORDER);
    cmb2.select(-1);       
   
    /**
     * SelectionListener reagiert auf Auswahl und zeigt entsprechende Labels und Textfelder an
     */
    cmb2.addSelectionListener(new SelectionAdapter() {
	      public void widgetSelected(SelectionEvent e) {
		    int j=cmb2.getSelectionIndex();
		    if (j==0){
			    setLabelText(lblParam1,20,160,120,20,txtParam1,220,160,30,20,"Number of trials:","0",0);
    			setLabelText(lblParam2,20,190,120,20,txtParam2,220,190,30,20,"Probability of success:","0.0",1);
			    setLabelText(lblParam3,20,220,120,20,txtParam3,220,220,30,20,"","",5);
    			
		    }
		    else if (j==1){
			    setLabelText(lblParam1,20,160,200,20,txtParam1,220,160,30,20,"Probability mass function:","1.0",1);
    			setLabelText(lblParam2,20,190,200,20,txtParam2,220,190,30,20,"Probability mass function:","2.0",1);
			    setLabelText(lblParam3,20,220,120,20,txtParam3,220,220,30,20,"","",0);
    						      
		    }
		    else if (j==2){
			    setLabelText(lblParam1,20,160,120,20,txtParam1,220,160,30,20,"Probability of success:","50",0);
    			setLabelText(lblParam2,20,190,120,20,txtParam2,220,190,30,20,"","",5);
			    setLabelText(lblParam3,20,220,120,20,txtParam3,220,220,30,20,"","",5);
    				
		    }
    		else if (j==3){
			    setLabelText(lblParam1,20,160,120,20,txtParam1,220,160,30,20,"Population size:","500",0);
    			setLabelText(lblParam2,20,190,200,20,txtParam2,220,190,30,20,"Number of successes in population:","150",0);
			    setLabelText(lblParam3,20,220,120,20,txtParam3,220,220,30,20,"Sample size:","250",0);
    					      
		    }
		    else if (j==4){
			    setLabelText(lblParam1,20,160,120,20,txtParam1,220,160,30,20,"Number of successes:","0",0);
    			setLabelText(lblParam2,20,190,120,20,txtParam2,220,190,30,20,"Probability of success:","0.0",1);
			    setLabelText(lblParam3,20,220,120,20,txtParam3,220,220,30,20,"","",0);
    						      
		    }
		    else if (j==5){
			    setLabelText(lblParam1,20,160,120,20,txtParam1,220,160,30,20,"Mean:","0.0",1);
    			setLabelText(lblParam2,20,190,120,20,txtParam2,220,190,30,20,"Convergence:","1.0",1);
			    setLabelText(lblParam3,20,220,200,20,txtParam3,220,220,30,20,"Max Number of iterations:","50",0);
    						      
		    }
		    else if (j==6){
			    setLabelText(lblParam1,20,160,120,20,txtParam1,220,160,30,20,"Lower bounds:","10",0);
    			setLabelText(lblParam2,20,190,120,20,txtParam2,220,190,30,20,"Upper bounds:","50",0);
			    setLabelText(lblParam3,20,220,120,20,txtParam3,220,220,30,20,"","",0);
    					      
		    }
		    else if (j==7){
			    setLabelText(lblParam1,20,160,120,20,txtParam1,220,160,30,20,"Number of elements:","50",0);
    			setLabelText(lblParam2,20,190,120,20,txtParam2,220,190,30,20,"Exponent:","2",0);
			    setLabelText(lblParam3,20,220,120,20,txtParam3,220,220,30,20,"","",0);
    						      
		    }
		    return;
	      }
	    });
    
}

public void addModifyListener(ModifyListener modifyListener){
	eingabeOK=false;
}

}
