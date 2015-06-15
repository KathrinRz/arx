	import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * Erstellt Eingabefelder f�r Klasse ConstantShiftDateMasker
 * @author Kathrin
 *
 */
	public class ConfigConstantShiftDateMasker{
			
			
			
			private boolean eingabeOK=true;
			private Button btn1;
			private Composite root;
			private Label lblShiftPeriod;
			private Text txtShiftPeriod;
			
			/**
			 * Methode, die Labels + Textfelder f�r verschiedene Parameter erstellt. 
			 * Mit int typ wird angegeben, ob Int, Double,Date,Period oder ein String erwartet wird => Aufruf RegEx
			 * @param l1
			 * @param lx1
			 * @param lx2
			 * @param lx3
			 * @param lx4
			 * @param t1
			 * @param tx1
			 * @param tx2
			 * @param tx3
			 * @param tx4
			 * @param lab1
			 * @param val1
			 * @param typ
			 */
			
			public void setLabelText(Label lbl1,int lx1, int lx2, int lx3, int lx4,Text txt1,int tx1, int tx2, int tx3, int tx4, String lab1, String val1,int typ){
				lbl1.setBounds(lx1,lx2,lx3,lx4);
				lbl1.setText(lab1);
				txt1.setBounds(tx1,tx2,tx3,tx4);
		        txt1.setText(val1);
		        if(typ ==3){
		        txt1.addModifyListener(new ModifyListener(){
		            	public void modifyText(ModifyEvent e){
		            		if (RegEx.regExPeriod(txt1.getText())){
		            			//b1.setEnabled(true);
		            			eingabeOK=true;
		            		}
		            		else
		            		{
		            			//b1.setEnabled(false);
		            			eingabeOK=false;
		            		}
		            	}
		            });
		            
		        }
		        /**
		         * Wenn das Label eines Parameter der Verteilung leer ist, werden das entsprechende Label und Textfeld nicht angezeigt
		         * 
		         */
		        
		        if (lab1=="")  {
		        	lbl1.setVisible(false);
		        	txt1.setVisible(false);
		        }
		        else
		        {
		        	lbl1.setVisible(true);
		        	txt1.setVisible(true);
		        }
			}
			
			/**
			 * Methode, die ein Coposite erzeugt, in dem ein Eigabefeld f�r ShiftPeriod erstellt wird
			 * @param s1
			 * @param x
			 * @param y
			 */
			public ConfigConstantShiftDateMasker(Shell s1, int x, int y) {
				root = new Composite(s1,SWT.NONE);
				root.setBounds(x,y,500,500);
				btn1= new Button(root,SWT.NONE);
				btn1.setBounds(300, 400, 130, 30);
				btn1.setText("Eingabe testen");
				btn1.addSelectionListener(new SelectionAdapter(){
					public void widgetSelected(SelectionEvent e){
						boolean istOK=eingabeOK;
						if (istOK==true){
							btn1.setText("OK");
						}
						else btn1.setText("Falsche Eingabe");
					}
				});
				
				lblShiftPeriod=new Label(root, SWT.NONE);
				txtShiftPeriod=new Text(root, SWT.BORDER);				
		        
				setLabelText(lblShiftPeriod,20,20,120,20,txtShiftPeriod,220,20,100,20,"ShiftPeriod:","05y 04m 03d 01h",3);		        		       
			}
}




	


