import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
//import org.eclipse.swt.widgets.Shell;
//import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
//import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;


public class ConfigGenerateRandomIntegerDecimalMasker{
 
	private Text text1;
	private Label label1;
	private Combo combo2;
	private Label label2;
	private Label label3;
	private Label label4;
	private Label label5;
	private Label label6;
	private Text text2;
	private Text text3;
	private Text text4;
	private Text text5;
	private Text text6;
	private Label l2;
	private Shell s1;
	private Button b1;
	private MaskerData returnwert = new MaskerData();
	
	public boolean regExInt(String s){
		for(int i = 0; i<s.length();i++){
			if("0123456789".indexOf(s.substring(i,i+1))== -1) return false;
		}
		if(s.equals(""))return false;
		return true;
		}
	
	 public boolean regExDouble (String s){
			for(int i = 0; i<s.length();i++){
				if(".0123456789".indexOf(s.substring(i,i+1))== -1) return false;
			}
			int anz=0;
			for(int i =0;i<s.length();i++){
				if (s.substring(i,i+1).equals("."))
				anz++;
			}
			if (anz>1) return false;
			if((s.equals(".")))return false;
			if(s.equals(""))return false;
			return true;
	 }
	
	
	public void SetLabelText(Label l1,int lx1, int lx2, int lx3, int lx4,Text t1,int tx1, int tx2, int tx3, int tx4, String lab1, String val1,int typ){
		l1.setBounds(lx1,lx2,lx3,lx4);
		l1.setText(lab1);
		t1.setBounds(tx1,tx2,tx3,tx4);
        t1.setText(val1);
        if (typ == 0){
        t1.addModifyListener(new ModifyListener(){
        	public void modifyText(ModifyEvent e){
        		if (regExInt(t1.getText())){
        			b1.setEnabled(true);
        		}
        		else
        		{
        			b1.setEnabled(false);
        			
        		}
        	}
        });
        }
        if (typ == 1){
            t1.addModifyListener(new ModifyListener(){
            	public void modifyText(ModifyEvent e){
            		if (regExDouble(t1.getText())){
            			b1.setEnabled(true);
            		}
            		else
            		{
            			b1.setEnabled(false);
            			
            		}
            	}
            });
            }
        
        if (lab1=="")  {
        	l1.setVisible(false);
        	t1.setVisible(false);
        }
        else
        {
        	l1.setVisible(true);
        	t1.setVisible(true);
        }
	}
	
	
	public ConfigGenerateRandomIntegerDecimalMasker(Display d, int x, int y) {
		s1 = new Shell(d);
		s1.setSize(500,500);
		s1.setText("ConfigRandomIntegerDecimalMasker");
        
		b1= new Button(s1,SWT.NONE);
		b1.setBounds(400, 400, 50, 30);
		b1.setText("OK!");
		b1.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent e){
				returnwert.setZahl1(text1.getText());
				returnwert.setZahl2(text2.getText());
				returnwert.setZahl3(text3.getText());
				returnwert.setZahl4(text4.getText());
				returnwert.setZahl5(text5.getText());
				returnwert.setZahl6(text6.getText());
				s1.close();
			}
		});
		
		label1=new Label(s1, SWT.NONE);
		text1=new Text(s1, SWT.SINGLE);
		
		SetLabelText(label1,20,20,80,20,text1,110,20,30,20,"ShiftConstant:","0.0",1);
		
        l2=new Label (s1, SWT.NONE);
        l2.setText("Distribution");
        l2.setBounds(20, 60, 80, 20);
        combo2=new Combo(s1, SWT.DROP_DOWN);
		combo2.add("BinomialDistribution");
		combo2.add("EnumeratedIntegerDistribution");
		combo2.add("GeometricDistribution");
		combo2.add("HypergeometricDistribution");
		combo2.add("PascalDistribution");
		combo2.add("PoissonDistribution");
		combo2.add("UniformIntegerDistribution");
		combo2.add("ZipfDistribution");
		combo2.setBounds(20,80,200,20);
		label2=new Label(s1, SWT.NONE);
		label3=new Label(s1, SWT.NONE);
		label4=new Label(s1, SWT.NONE);
		label5=new Label(s1, SWT.NONE);
		label6=new Label(s1, SWT.NONE);
		text2=new Text(s1, SWT.SINGLE);
        text3=new Text(s1, SWT.SINGLE);
        text4=new Text(s1, SWT.SINGLE);
        text5=new Text(s1, SWT.SINGLE);
        text6=new Text(s1, SWT.SINGLE);
        combo2.select(-1);       
       
        combo2.addSelectionListener(new SelectionAdapter() {
		      public void widgetSelected(SelectionEvent e) {
			    int j=combo2.getSelectionIndex();
			    if (j==0){
				    SetLabelText(label2,20,120,120,20,text2,220,120,30,20,"Number of trials:","0",0);
        			SetLabelText(label3,20,150,120,20,text3,220,150,30,20,"Probability of success:","0.0",1);
				    SetLabelText(label4,20,180,120,20,text4,220,180,30,20,"","",0);
        			SetLabelText(label5,20,210,120,20,text5,220,210,30,20,"","",0);
			    }
			    else if (j==1){
				    SetLabelText(label2,20,120,200,20,text2,220,120,30,20,"Probability mass function:","1.0",1);
        			SetLabelText(label3,20,150,200,20,text3,220,150,30,20,"Probability mass function:","2.0",1);
				    SetLabelText(label4,20,180,120,20,text4,220,180,30,20,"","",0);
        			SetLabelText(label5,20,210,120,20,text5,220,210,30,20,"","",0);			      
			    }
			    else if (j==2){
				    SetLabelText(label2,20,120,120,20,text2,220,120,30,20,"Probability of success:","50",0);
        			SetLabelText(label3,20,150,120,20,text3,220,150,30,20,"","",0);
				    SetLabelText(label4,20,180,120,20,text4,220,180,30,20,"","",0);
        			SetLabelText(label5,20,210,120,20,text5,220,210,30,20,"","",0);	
			    }
        		else if (j==3){
				    SetLabelText(label2,20,120,120,20,text2,220,120,30,20,"Population size:","500",0);
        			SetLabelText(label3,20,150,200,20,text3,220,150,30,20,"Number of successes in population:","150",0);
				    SetLabelText(label4,20,180,120,20,text4,220,180,30,20,"Sample size:","250",0);
        			SetLabelText(label5,20,210,120,20,text5,220,210,30,20,"","",0);			      
			    }
			    else if (j==4){
				    SetLabelText(label2,20,120,120,20,text2,220,120,30,20,"Number of successes:","0",0);
        			SetLabelText(label3,20,150,120,20,text3,220,150,30,20,"Probability of success:","0.0",1);
				    SetLabelText(label4,20,180,120,20,text4,220,180,30,20,"","",0);
        			SetLabelText(label5,20,210,120,20,text5,220,210,30,20,"","",0);			      
			    }
			    else if (j==5){
				    SetLabelText(label2,20,120,120,20,text2,220,120,30,20,"Mean:","0.0",1);
        			SetLabelText(label3,20,150,120,20,text3,220,150,30,20,"Convergence:","1.0",1);
				    SetLabelText(label4,20,180,200,20,text4,220,180,30,20,"Max Number of iterations:","50",0);
        			SetLabelText(label5,20,210,120,20,text5,220,210,30,20,"","",0);			      
			    }
			    else if (j==6){
				    SetLabelText(label2,20,120,120,20,text2,220,120,30,20,"Lower bounds:","10",0);
        			SetLabelText(label3,20,150,120,20,text3,220,150,30,20,"Upper bounds:","50",0);
				    SetLabelText(label4,20,180,120,20,text4,220,180,30,20,"","",0);
        			SetLabelText(label5,20,210,120,20,text5,220,210,30,20,"","",0);			      
			    }
			    else if (j==7){
				    SetLabelText(label2,20,120,120,20,text2,220,120,30,20,"Number of elements:","50",0);
        			SetLabelText(label3,20,150,120,20,text3,220,150,30,20,"Exponent:","2",0);
				    SetLabelText(label4,20,180,120,20,text4,220,180,30,20,"","",0);
        			SetLabelText(label5,20,210,120,20,text5,220,210,30,20,"","",0);			      
			    }
			    return;
		      }
		    });
        
		
		

	}
	public MaskerData Open(Display d){
		s1.open();
			while (!s1.isDisposed()){
				if(!d.readAndDispatch())
					d.sleep();
			}
		return returnwert;
}
	


}
