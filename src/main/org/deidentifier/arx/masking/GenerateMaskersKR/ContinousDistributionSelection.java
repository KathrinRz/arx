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
 * Enthält alle kontinuierlichen Verteilungsfunkionen + jeweilig geforderte Parameter
 * @author Kathrin
 *
 */

public class ContinousDistributionSelection extends Composite {
	
	public boolean eingabeOK=true;
	private Combo cmb3;
	private Label lblDistribution;
	private Label lblParam1;
	private Label lblParam2;
	private Label lblParam3;
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
	        			if(RegEx.regExInt(txt1.getText())) {
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
	         * (bsp. "ConstantRealDistribution" braucht nur einen Parameter, "BetaDistribution" braucht 3)
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
 * Rückgabe, ob Eingabe ok an aufrufende Klasse
 * @return
 */

public boolean getOK(){
	return eingabeOK;
}


ContinousDistributionSelection(Composite s1,int style){
	super(s1,style);
	lblDistribution=new Label(s1,SWT.NONE);
	lblDistribution.setText("Distribution:");
	lblDistribution.setBounds(20, 60, 80, 20);
	/**
	 * Dropdownmenü zur Auswahl der verschiedenen Verteilungen wird erstellt
	 */
    cmb3=new Combo(s1, SWT.DROP_DOWN);
	cmb3.add("BetaDistribution");
	cmb3.add("CauchyDistribution");
	cmb3.add("ChiSquaredDistribution");
	cmb3.add("ConstantRealDistribution");
	cmb3.add("EnumeratedRealDistribution");
	cmb3.add("FDistribution");
	cmb3.add("GammaDistribution");
	cmb3.add("GumbelDistribution");
	cmb3.add("LaplaceDistribution");
	cmb3.add("LevyDistribution");
	cmb3.add("LogisticDistribution");
	cmb3.add("LogNormalDistribution");
	cmb3.add("NakagamiDistribution");
	cmb3.add("NormalDistribution");
	cmb3.add("ParetoDistribution");
	cmb3.add("TDistribution");
	cmb3.add("TriangularDistribution");
	cmb3.add("UniformRealDistribution");
	cmb3.add("WeilbullDistribution");
	cmb3.setBounds(20,80,200,20);
	lblParam1=new Label(s1, SWT.NONE);
	lblParam2=new Label(s1, SWT.NONE);
	lblParam3=new Label(s1, SWT.NONE);
	txtParam1=new Text(s1, SWT.BORDER);
    txtParam2=new Text(s1, SWT.BORDER);
    txtParam3=new Text(s1, SWT.BORDER);
    cmb3.select(-1);   
    
   /**
    * SelectionListener reagier auf Auswahl und zeigt entsprechende Labels und Textfelder an
    */
    
    this.cmb3.addSelectionListener(new SelectionAdapter() {
	      public void widgetSelected(SelectionEvent e) {
		    int j=cmb3.getSelectionIndex();
		    if (j==0){
			    setLabelText(lblParam1,20,120,120,20,txtParam1,220,120,30,20,"Alpha:","0",0);
    			setLabelText(lblParam2,20,150,120,20,txtParam2,220,150,30,20,"Beta:","0.0",1);
			    setLabelText(lblParam3,20,180,120,20,txtParam3,220,180,30,20,"InverseCumAccuracy:","0.0",1);
   		    }
		    else if (j==1){
			    setLabelText(lblParam1,20,120,120,20,txtParam1,220,120,30,20,"Median:","1.0",1);
    			setLabelText(lblParam2,20,150,120,20,txtParam2,220,150,30,20,"Scale:","1.0",1);
			    setLabelText(lblParam3,20,180,120,20,txtParam3,220,180,30,20,"InverseCumAccuracy:","0.0",1);		      
		    }
		    else if (j==2){
			    setLabelText(lblParam1,20,120,120,20,txtParam1,220,120,30,20,"DegreesOfFreedom:","5",0);
    			setLabelText(lblParam2,20,150,120,20,txtParam2,220,150,30,20,"InverseCumAccuracy:","0.0",1);
			    setLabelText(lblParam3,20,180,120,20,txtParam3,220,180,30,20,"","",0);	
		    }
    		else if (j==3){
			    setLabelText(lblParam1,20,120,120,20,txtParam1,220,120,30,20,"Value:","0",0);
    			setLabelText(lblParam2,20,150,200,20,txtParam2,220,150,30,20,"","",0);
			    setLabelText(lblParam3,20,180,120,20,txtParam3,220,180,30,20,"","",0);			      
		    }
		    else if (j==4){
			    setLabelText(lblParam1,20,120,120,20,txtParam1,220,120,30,20,"Singletons:","10",0);
    			setLabelText(lblParam2,20,150,120,20,txtParam2,220,150,30,20,"Probabilities:","0.5",1);
			    setLabelText(lblParam3,20,180,120,20,txtParam3,220,180,30,20,"","",0);		      
		    }
		    else if (j==5){
			    setLabelText(lblParam1,20,120,120,20,txtParam1,220,120,30,20,"Mean:","0.0",1);
    			setLabelText(lblParam2,20,150,120,20,txtParam2,220,150,30,20,"InverseCumAccuracy:","1.0",1);
			    setLabelText(lblParam3,20,180,120,20,txtParam3,220,180,30,20,"","",0);			      
		    }
		    else if (j==6){
			    setLabelText(lblParam1,20,120,120,20,txtParam1,220,120,30,20,"NumeratorDegreesOfFreedom:","10",0);
    			setLabelText(lblParam2,20,150,120,20,txtParam2,220,150,30,20,"DenominatorDegreesOfFreedom:","50",0);
			    setLabelText(lblParam3,20,180,120,20,txtParam3,220,180,30,20,"InverseCumAccuracy:","0",0);			      
		    }
		    else if (j==7){
			    setLabelText(lblParam1,20,120,120,20,txtParam1,220,120,30,20,"Shape:","50",0);
    			setLabelText(lblParam2,20,150,120,20,txtParam2,220,150,30,20,"Scale:","2",0);
			    setLabelText(lblParam3,20,180,120,20,txtParam3,220,180,30,20,"InverseCumAccuracy:","0",0);		      
		    }
		    if (j==8){
			    setLabelText(lblParam1,20,120,120,20,txtParam1,220,120,30,20,"Mu:","0",0);
    			setLabelText(lblParam2,20,150,120,20,txtParam2,220,150,30,20,"Beta:","0",0);
			    setLabelText(lblParam3,20,180,120,20,txtParam3,220,180,30,20,"","",0);
		    }
		    else if (j==9){
			    setLabelText(lblParam1,20,120,120,20,txtParam1,220,120,30,20,"Mu:","1.0",1);
    			setLabelText(lblParam2,20,150,120,20,txtParam2,220,150,30,20,"Beta:","1.0",1);
			    setLabelText(lblParam3,20,180,120,20,txtParam3,220,180,30,20,"","",0);		      
		    }
		    else if (j==10){
			    setLabelText(lblParam1,20,120,120,20,txtParam1,220,120,30,20,"Mu:","5",0);
    			setLabelText(lblParam2,20,150,120,20,txtParam2,220,150,30,20,"C:","0.0",1);
			    setLabelText(lblParam3,20,180,120,20,txtParam3,220,180,30,20,"","",0);	
		    }
    		else if (j==11){
			    setLabelText(lblParam1,20,120,120,20,txtParam1,220,120,30,20,"Mu:","0",0);
    			setLabelText(lblParam2,20,150,200,20,txtParam2,220,150,30,20,"S:","10",0);
			    setLabelText(lblParam3,20,180,120,20,txtParam3,220,180,30,20,"","",0);		      
		    }
		    else if (j==12){
			    setLabelText(lblParam1,20,120,120,20,txtParam1,220,120,30,20,"Scale:","1.0",1);
    			setLabelText(lblParam2,20,150,120,20,txtParam2,220,150,30,20,"Shape:","0.5",1);
			    setLabelText(lblParam3,20,180,120,20,txtParam3,220,180,30,20,"InverseCumAccuracy:","0.0",1);	      
		    }
		    else if (j==13){
			    setLabelText(lblParam1,20,120,120,20,txtParam1,220,120,30,20,"Mu:","0.0",1);
    			setLabelText(lblParam2,20,150,120,20,txtParam2,220,150,30,20,"Omega:","1.0",1);
			    setLabelText(lblParam3,20,180,120,20,txtParam3,220,180,30,20,"InverseCumAccuracy:","0.0",1);		      
		    }
		    else if (j==14){
			    setLabelText(lblParam1,20,120,120,20,txtParam1,220,120,30,20,"Mean:","0",0);
    			setLabelText(lblParam2,20,150,120,20,txtParam2,220,150,30,20,"Sd:","1",0);
			    setLabelText(lblParam3,20,180,120,20,txtParam3,220,180,30,20,"InverseCumAccuracy:","0",0);			      
		    }
		    else if (j==15){
			    setLabelText(lblParam1,20,120,120,20,txtParam1,220,120,30,20,"Scale:","1",0);
    			setLabelText(lblParam2,20,150,120,20,txtParam2,220,150,30,20,"Shape:","1",0);
			    setLabelText(lblParam3,20,180,120,20,txtParam3,220,180,30,20,"InverseCumAccuracy:","0",0);		      
		    }
			else if (j==16){
			    setLabelText(lblParam1,20,120,120,20,txtParam1,220,120,30,20,"DegreesOfFreedom:","0",0);
    			setLabelText(lblParam2,20,150,200,20,txtParam2,220,150,30,20,"InverseCumAccuracy:","0.0",1);
			    setLabelText(lblParam3,20,180,120,20,txtParam3,220,180,30,20,"","",0);		      
		    }
		    else if (j==17){
			    setLabelText(lblParam1,20,120,120,20,txtParam1,220,120,30,20,"Lower limit:","10",0);
    			setLabelText(lblParam2,20,150,120,20,txtParam2,220,150,30,20,"Upper limit:","0.5",1);
			    setLabelText(lblParam3,20,180,120,20,txtParam3,220,180,30,20,"Mode:","0",0);			      
		    }
		    else if (j==18){
			    setLabelText(lblParam1,20,120,120,20,txtParam1,220,120,30,20,"Lower bounds:","0.0",1);
    			setLabelText(lblParam2,20,150,120,20,txtParam2,220,150,30,20,"Upper bounds:","1.0",1);
			    setLabelText(lblParam3,20,180,120,20,txtParam3,220,180,30,20,"","",0);		      
		    }
		    else if (j==19){
			    setLabelText(lblParam1,20,120,120,20,txtParam1,220,120,30,20,"Shape:","0",0);
    			setLabelText(lblParam2,20,150,120,20,txtParam2,220,150,30,20,"Scale:","0",0);
			    setLabelText(lblParam3,20,180,120,20,txtParam3,220,180,30,20,"InverseCumAccuracy:","0",0);		      
		    }
		    return;
	      }
	    });	
}


public void addModifyListener(ModifyListener modifyListener){
	
}
}

