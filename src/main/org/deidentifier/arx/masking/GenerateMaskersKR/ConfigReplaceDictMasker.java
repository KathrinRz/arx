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
 * Erstellt Eingabefelder für Klasse ReplaceDictMasker
 * @author Kathrin
 *
 */

public class ConfigReplaceDictMasker{
	
	private boolean eingabeOK=true;
	private Composite root;
	private Label lblReplacementList;
	private Text txtReplacementList;
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
        		else
        		{
        			//btn1.setEnabled(false);
        			eingabeOK=true;
        			
        		}
        	}
        });
        }
        if (typ == 1){
            txt1.addModifyListener(new ModifyListener(){
            	public void modifyText(ModifyEvent e){
            		if (RegEx.regExDouble(txt1.getText())){
            			//btn1.setEnabled(true);
            			eingabeOK=true;
            		}
            		else
            		{
            			//btn1.setEnabled(false);
            			eingabeOK=true;
            			
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
	 * Methode, die ein Coposite erzeugt, in dem ein Textfeld erstellt wird
	 * @param s1
	 * @param x
	 * @param y
	 */
	
	public ConfigReplaceDictMasker (Shell s1, int x, int y){
		root = new Composite(s1,SWT.NONE);
		root.setBounds(x,y,500,500);
		
		btn1= new Button(root,SWT.NONE);
		btn1.setBounds(300, 400, 130, 30);
		btn1.setText("Eingabe testen");
		btn1.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent e){
				boolean istOK=eingabeOK;
				if(istOK==true){
					btn1.setText("OK!");
				}
				else{
					btn1.setText("Falsche Eingabe");
				}
			}
		});
		
	lblReplacementList=new Label(root, SWT.NONE);
	txtReplacementList=new Text(root, SWT.BORDER);
				
    setLabelText(lblReplacementList,20,20,120,20,txtReplacementList,220,20,100,80,"ReplacementList:","1,2,3,4",5);
    

    
	}  
	

}

