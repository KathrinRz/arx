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

	public class ConfigConstantShiftDateMasker {
		 
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
			 public boolean regExPeriod(String s){
					if(s.length()<15) return false;
					for(int i = 0;i<s.length();i++){
						if("-0123456789ymdh ".indexOf(s.substring(i,i+1))==-1)
							return false;
					}
					int anzY=0;
					for(int i =0;i<s.length();i++){
						if (s.substring(i,i+1).equals("y"))
						anzY++;
					}
					if (anzY>1) return false;
					
					int anzM=0;
					for(int i =0;i<s.length();i++){
						if (s.substring(i,i+1).equals("m"))
						anzM++;
					}
					if (anzM>1) return false;
					int anzD=0;
					for(int i =0;i<s.length();i++){
						if (s.substring(i,i+1).equals("d"))
						anzD++;
					}
					if (anzD>1) return false;
					
					int anzH=0;
					for(int i =0;i<s.length();i++){
						if (s.substring(i,i+1).equals("h"))
						anzH++;
					}
					if (anzH>1) return false;
					
					int py;
					int pm;
					int pd;
					int ph;
					py=Integer.valueOf(s.indexOf("y"));
					pm=Integer.valueOf(s.indexOf("m"));
					pd=Integer.valueOf(s.indexOf("d"));
					ph=Integer.valueOf(s.indexOf("h"));
					/*System.out.println("Das ist der Wert von py: " + py);
					System.out.println("Das ist der Wert von pm:" + pm);
					System.out.println("Das ist der Wert von pd: " + pd);
					System.out.println("Das ist der Wert von ph:" + ph);*/
					if ((py>pm)||(py>pd)||(py>ph))return false;
					if ((pm>pd)||(pm>ph))return false;
					if(pd>ph)return false;
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
		        
		        if (typ == 3){
		            t1.addModifyListener(new ModifyListener(){
		            	public void modifyText(ModifyEvent e){
		            		if (regExPeriod(t1.getText())){
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
			
			
			public ConfigConstantShiftDateMasker(Display d, int x, int y) {
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
				label2=new Label(s1, SWT.NONE);
				label3=new Label(s1, SWT.NONE);
				label4=new Label(s1, SWT.NONE);
				label5=new Label(s1, SWT.NONE);
				label6=new Label(s1, SWT.NONE);
				text1=new Text(s1, SWT.SINGLE);
				text2=new Text(s1, SWT.SINGLE);
		        text3=new Text(s1, SWT.SINGLE);
		        text4=new Text(s1, SWT.SINGLE);
		        text5=new Text(s1, SWT.SINGLE);
		        text6=new Text(s1, SWT.SINGLE);
				
		        SetLabelText(label1,20,20,120,20,text1,220,20,100,20,"ShiftPeriod:","05y 04m 03d 01h",3);
		        
		        
		       
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




	


