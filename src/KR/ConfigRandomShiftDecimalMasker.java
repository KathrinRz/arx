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


public class ConfigRandomShiftDecimalMasker {
		
		private Text text1;
		private Label label1;
		private Combo combo3;
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
		public ConfigRandomShiftDecimalMasker(Display d, int x, int y){
			s1 = new Shell(d);
			s1.setSize(500, 500);
			s1.setText("ConfigRandomDecimalMasker");
			
			b1 = new Button(s1,SWT.NONE);
			b1.setBounds(400,400,50,30);
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
			label2=new Label(s1,SWT.NONE);
			label2.setText("Distribution:");
			label2.setBounds(20, 60, 80, 20);
	        combo3=new Combo(s1, SWT.DROP_DOWN);
			combo3.add("BetaDistribution");
			combo3.add("CauchyDistribution");
			combo3.add("ChiSquaredDistribution");
			combo3.add("ConstantRealDistribution");
			combo3.add("EnumeratedRealDistribution");
			combo3.add("FDistribution");
			combo3.add("GammaDistribution");
			combo3.add("GumbelDistribution");
			combo3.add("LaplaceDistribution");
			combo3.add("LevyDistribution");
			combo3.add("LogisticDistribution");
			combo3.add("LogNormalDistribution");
			combo3.add("NakagamiDistribution");
			combo3.add("NormalDistribution");
			combo3.add("ParetoDistribution");
			combo3.add("TDistribution");
			combo3.add("TriangularDistribution");
			combo3.add("UniformRealDistribution");
			combo3.add("WeilbullDistribution");
			combo3.setBounds(20,80,200,20);
			label2=new Label(s1, SWT.NONE);
			label3=new Label(s1, SWT.NONE);
			label4=new Label(s1, SWT.NONE);
			label5=new Label(s1, SWT.NONE);
			text2=new Text(s1, SWT.SINGLE);
	        text3=new Text(s1, SWT.SINGLE);
	        text4=new Text(s1, SWT.SINGLE);
	        text5=new Text(s1, SWT.SINGLE);
	        text6=new Text(s1, SWT.SINGLE);
	        combo3.select(-1);      
	       
	        this.combo3.addSelectionListener(new SelectionAdapter() {
			      public void widgetSelected(SelectionEvent e) {
				    int j=combo3.getSelectionIndex();
				    if (j==0){
					    SetLabelText(label2,20,120,120,20,text2,220,120,30,20,"Alpha:","0",0);
	        			SetLabelText(label3,20,150,120,20,text3,220,150,30,20,"Beta:","0.0",1);
					    SetLabelText(label4,20,180,120,20,text4,220,180,30,20,"InverseCumAccuracy:","0.0",1);
	        			SetLabelText(label5,20,210,120,20,text5,220,210,30,20,"","",0);
				    }
				    else if (j==1){
					    SetLabelText(label2,20,120,120,20,text2,220,120,30,20,"Median:","1.0",1);
	        			SetLabelText(label3,20,150,120,20,text3,220,150,30,20,"Scale:","1.0",1);
					    SetLabelText(label4,20,180,120,20,text4,220,180,30,20,"InverseCumAccuracy:","0.0",1);
	        			SetLabelText(label5,20,210,120,20,text5,220,210,30,20,"","",0);			      
				    }
				    else if (j==2){
					    SetLabelText(label2,20,120,120,20,text2,220,120,30,20,"DegreesOfFreedom:","5",0);
	        			SetLabelText(label3,20,150,120,20,text3,220,150,30,20,"InverseCumAccuracy:","0.0",1);
					    SetLabelText(label4,20,180,120,20,text4,220,180,30,20,"","",0);
	        			SetLabelText(label5,20,210,120,20,text5,220,210,30,20,"","",0);	
				    }
	        		else if (j==3){
					    SetLabelText(label2,20,120,120,20,text2,220,120,30,20,"Value:","0",0);
	        			SetLabelText(label3,20,150,200,20,text3,220,150,30,20,"","",0);
					    SetLabelText(label4,20,180,120,20,text4,220,180,30,20,"","",0);
	        			SetLabelText(label5,20,210,120,20,text5,220,210,30,20,"","",0);			      
				    }
				    else if (j==4){
					    SetLabelText(label2,20,120,120,20,text2,220,120,30,20,"Singletons:","10",0);
	        			SetLabelText(label3,20,150,120,20,text3,220,150,30,20,"Probabilities:","0.5",1);
					    SetLabelText(label4,20,180,120,20,text4,220,180,30,20,"","",0);
	        			SetLabelText(label5,20,210,120,20,text5,220,210,30,20,"","",0);			      
				    }
				    else if (j==5){
					    SetLabelText(label2,20,120,120,20,text2,220,120,30,20,"Mean:","0.0",1);
	        			SetLabelText(label3,20,150,120,20,text3,220,150,30,20,"InverseCumAccuracy:","1.0",1);
					    SetLabelText(label4,20,180,120,20,text4,220,180,30,20,"","",0);
	        			SetLabelText(label5,20,210,120,20,text5,220,210,30,20,"","",0);			      
				    }
				    else if (j==6){
					    SetLabelText(label2,20,120,120,20,text2,220,120,30,20,"NumeratorDegreesOfFreedom:","10",0);
	        			SetLabelText(label3,20,150,120,20,text3,220,150,30,20,"DenominatorDegreesOfFreedom:","50",0);
					    SetLabelText(label4,20,180,120,20,text4,220,180,30,20,"InverseCumAccuracy:","0",0);
	        			SetLabelText(label5,20,210,120,20,text5,220,210,30,20,"","",0);			      
				    }
				    else if (j==7){
					    SetLabelText(label2,20,120,120,20,text2,220,120,30,20,"Shape:","50",0);
	        			SetLabelText(label3,20,150,120,20,text3,220,150,30,20,"Scale:","2",0);
					    SetLabelText(label4,20,180,120,20,text4,220,180,30,20,"InverseCumAccuracy:","0",0);
	        			SetLabelText(label5,20,210,120,20,text5,220,210,30,20,"","",0);			      
				    }
				    if (j==8){
					    SetLabelText(label2,20,120,120,20,text2,220,120,30,20,"Mu:","0",0);
	        			SetLabelText(label3,20,150,120,20,text3,220,150,30,20,"Beta:","0",0);
					    SetLabelText(label4,20,180,120,20,text4,220,180,30,20,"","",0);
	        			SetLabelText(label5,20,210,120,20,text5,220,210,30,20,"","",0);
				    }
				    else if (j==9){
					    SetLabelText(label2,20,120,120,20,text2,220,120,30,20,"Mu:","1.0",1);
	        			SetLabelText(label3,20,150,120,20,text3,220,150,30,20,"Beta:","1.0",1);
					    SetLabelText(label4,20,180,120,20,text4,220,180,30,20,"","",0);
	        			SetLabelText(label5,20,210,120,20,text5,220,120,30,20,"","",0);			      
				    }
				    else if (j==10){
					    SetLabelText(label2,20,120,120,20,text2,220,120,30,20,"Mu:","5",0);
	        			SetLabelText(label3,20,150,120,20,text3,220,150,30,20,"C:","0.0",1);
					    SetLabelText(label4,20,180,120,20,text4,220,180,30,20,"","",0);
	        			SetLabelText(label5,20,210,120,20,text5,220,210,30,20,"","",0);	
				    }
	        		else if (j==11){
					    SetLabelText(label2,20,120,120,20,text2,220,120,30,20,"Mu:","0",0);
	        			SetLabelText(label3,20,150,200,20,text3,220,150,30,20,"S:","10",0);
					    SetLabelText(label4,20,180,120,20,text4,220,180,30,20,"","",0);
	        			SetLabelText(label5,20,210,120,20,text5,220,210,30,20,"","",0);			      
				    }
				    else if (j==12){
					    SetLabelText(label2,20,120,120,20,text2,220,120,30,20,"Scale:","1.0",1);
	        			SetLabelText(label3,20,150,120,20,text3,220,150,30,20,"Shape:","0.5",1);
					    SetLabelText(label4,20,180,120,20,text4,220,180,30,20,"InverseCumAccuracy:","0.0",1);
	        			SetLabelText(label5,20,210,120,20,text5,220,210,30,20,"","",0);			      
				    }
				    else if (j==13){
					    SetLabelText(label2,20,120,120,20,text2,220,120,30,20,"Mu:","0.0",1);
	        			SetLabelText(label3,20,150,120,20,text3,220,150,30,20,"Omega:","1.0",1);
					    SetLabelText(label4,20,180,120,20,text4,220,180,30,20,"InverseCumAccuracy:","0.0",1);
	        			SetLabelText(label5,20,210,120,20,text5,220,210,30,20,"","",0);			      
				    }
				    else if (j==14){
					    SetLabelText(label2,20,120,120,20,text2,220,120,30,20,"Mean:","0",0);
	        			SetLabelText(label3,20,150,120,20,text3,220,150,30,20,"Sd:","1",0);
					    SetLabelText(label4,20,180,120,20,text4,220,180,30,20,"InverseCumAccuracy:","0",0);
	        			SetLabelText(label5,20,210,120,20,text5,220,210,30,20,"","",0);			      
				    }
				    else if (j==15){
					    SetLabelText(label2,20,120,120,20,text2,220,120,30,20,"Scale:","1",0);
	        			SetLabelText(label3,20,150,120,20,text3,220,150,30,20,"Shape:","1",0);
					    SetLabelText(label4,20,180,120,20,text4,220,180,30,20,"InverseCumAccuracy:","0",0);
	        			SetLabelText(label5,20,210,120,20,text5,220,210,30,20,"","",0);			      
				    }
					else if (j==16){
					    SetLabelText(label2,20,120,120,20,text2,220,120,30,20,"DegreesOfFreedom:","0",0);
	        			SetLabelText(label3,20,150,200,20,text3,220,150,30,20,"InverseCumAccuracy:","0.0",1);
					    SetLabelText(label4,20,180,120,20,text4,220,180,30,20,"","",0);
	        			SetLabelText(label5,20,210,120,20,text5,220,210,30,20,"","",0);			      
				    }
				    else if (j==17){
					    SetLabelText(label2,20,120,120,20,text2,220,120,30,20,"Lower limit:","10",0);
	        			SetLabelText(label3,20,150,120,20,text3,220,150,30,20,"Upper limit:","0.5",1);
					    SetLabelText(label4,20,180,120,20,text4,220,180,30,20,"Mode:","0",0);
	        			SetLabelText(label5,20,210,120,20,text5,220,210,30,20,"","",0);			      
				    }
				    else if (j==18){
					    SetLabelText(label2,20,120,120,20,text2,220,120,30,20,"Lower bounds:","0.0",1);
	        			SetLabelText(label3,20,150,120,20,text3,220,150,30,20,"Upper bounds:","1.0",1);
					    SetLabelText(label4,20,180,120,20,text4,220,180,30,20,"","",0);
	        			SetLabelText(label5,20,210,120,20,text5,220,210,30,20,"","",0);			      
				    }
				    else if (j==19){
					    SetLabelText(label2,20,120,120,20,text2,220,120,30,20,"Shape:","0",0);
	        			SetLabelText(label3,20,150,120,20,text3,220,150,30,20,"Scale:","0",0);
					    SetLabelText(label4,20,180,120,20,text4,220,180,30,20,"InverseCumAccuracy:","0",0);
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
