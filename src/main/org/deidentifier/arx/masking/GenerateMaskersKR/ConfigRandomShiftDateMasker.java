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
 * Erstellt Eingabefelder für Klasse GenerateRandomShiftDateMasker
 * @author Kathrin
 *
 */
public class ConfigRandomShiftDateMasker {
	
	private boolean eingabeOK=true;
	private Composite root;
	private DiscreteDistributionSelection disc1;
	private Label lblShiftConstant;
	private Label lblPeriod;
	private Text txtShiftConstant;
	private Text txtPeriod;
	private Button btn1;

	/**
	 * Methode, die Labels + Textfelder für verschiedene Parameter erstellt. 
	 * Mit int typ wird angegeben, ob Int, Double,Date,Period oder ein String erwartet wird => Aufruf RegEx
	 * @param lbl1
	 * @param lx1
	 * @param lx2
	 * @param lx3
	 * @param lx4
	 * @param txt1
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
        if (typ == 0){
        txt1.addModifyListener(new ModifyListener(){
        	public void modifyText(ModifyEvent e){
        		if (RegEx.regExInt(txt1.getText())){
        			//btn1.setEnabled(true);
        			eingabeOK=true;
        		}
        		else {
        			//btn1.setEnabled(false);
        			eingabeOK=false;
           		}
        	}
        });
        }
        if (typ == 3){
            txt1.addModifyListener(new ModifyListener(){
            	public void modifyText(ModifyEvent e){
            		if (RegEx.regExPeriod(txt1.getText())){
            			//btn1.setEnabled(true);
            			eingabeOK=true;
            		}
            		else
            		{
            			//btn1.setEnabled(false);
            			eingabeOK=false;
            			
            		}
            	}
            });
            }
        
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
		 * Methode, die ein Coposite erzeugt, in dem zwei Eigabefelder und eine neue Composite für eine Verteilung erstellt werden
		 * @param s1
		 * @param x
		 * @param y
		 */
	
	public ConfigRandomShiftDateMasker (Shell s1, int x, int y){
		root = new Composite(s1,SWT.NONE);
		root.setBounds(x,y,500,500);
		btn1= new Button(root,SWT.NONE);
		btn1.setBounds(300, 400, 130, 30);
		btn1.setText("Eingabe testen");
		btn1.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent e){
				boolean istOK=disc1.getOK() && eingabeOK;
				if(istOK==true){
					btn1.setText("OK!");
				}
				else{
					btn1.setText("Falsche Eingabe");
				}
			}
		});
		
	lblShiftConstant=new Label(root, SWT.NONE);
	lblPeriod=new Label(root, SWT.NONE);
	txtShiftConstant=new Text(root, SWT.BORDER);
	txtPeriod=new Text(root, SWT.BORDER);
	
    setLabelText(lblShiftConstant,20,20,120,20,txtShiftConstant,220,20,100,20,"ShiftConstant:","0",0);
    setLabelText(lblPeriod,20,60,120,20,txtPeriod,220,60,100,20,"Period:","05y 04m 03d 01h",3);
    disc1= new DiscreteDistributionSelection(root,0);
    disc1.addModifyListener(new ModifyListener(){
    	public void modifyText(ModifyEvent e){
				btn1.setEnabled(disc1.getOK());
    	}
    });
    
	} 
}
