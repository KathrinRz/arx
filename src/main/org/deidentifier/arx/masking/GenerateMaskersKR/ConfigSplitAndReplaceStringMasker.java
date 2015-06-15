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
 * Erstellt Eingabefelder für Klasse SplitAndReplaceStringMasker
 * @author Kathrin
 *
 */
public class ConfigSplitAndReplaceStringMasker {
	
	private boolean eingabeOK=true;
	private Composite root;
	private Label lblSplitAtOccurrenceOf;
	private Label lblReplacementString;
	private Label lblReplaceGroup;
	private Label lblReplaceEachChar;
	private Text txtSplitAtOccurrenceOf;
	private Text txtReplacementString;
	private Text txtReplaceGroup;
	private Button btn1;
	private Button btnReplaceEchChar;
	
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
        if (typ == 1){
            txt1.addModifyListener(new ModifyListener(){
            	public void modifyText(ModifyEvent e){
            		if (RegEx.regExDouble(txt1.getText())){
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
		 * Methode, die ein Coposite erzeugt, in dem drei Textfelder und eine Checkbox erstellt werden
		 * @param s1
		 * @param x
		 * @param y
		 */
	
	public ConfigSplitAndReplaceStringMasker (Shell s1, int x, int y){
		root = new Composite(s1,SWT.NONE);
		root.setBounds(x,y,500,500);
		btn1= new Button(root,SWT.NONE);
		btn1.setBounds(300, 400, 130, 30);
		btn1.setText("Eingabe testen");
		btn1.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent e){
				boolean istOK = eingabeOK;
				if(istOK==true)	{
					btn1.setText("OK");
				}
				else {
					btn1.setText("Falsche Eingabe");
				}
			}
		});
		
	lblSplitAtOccurrenceOf=new Label(root, SWT.NONE);
	lblReplacementString=new Label(root, SWT.NONE);
	lblReplaceGroup=new Label(root, SWT.NONE);
	lblReplaceEachChar=new Label(root, SWT.NONE);
	txtSplitAtOccurrenceOf=new Text(root, SWT.BORDER);
	txtReplacementString=new Text(root, SWT.BORDER);
	txtReplaceGroup=new Text(root, SWT.BORDER);
	
    setLabelText(lblSplitAtOccurrenceOf,20,20,200,20,txtSplitAtOccurrenceOf,220,20,100,20,"Split at occurrences of:","@",5);
    setLabelText(lblReplacementString,20,50,200,20,txtReplacementString,220,50,100,20,"Replacement String:","*",5);
    setLabelText(lblReplaceGroup,20,80,200,20,txtReplaceGroup,220,80,100,20,"Replace group with index:","0",0);
     
    lblReplaceEachChar.setBounds(20,110,200,20);  				//wenn true -> jeder Buchstabe wird einzeln ersetzt
    lblReplaceEachChar.setText("Replace each character?");		// wenn false -> ganze Gruppe wird ersetzt
    btnReplaceEchChar = new Button(root,SWT.CHECK);
    btnReplaceEchChar.setBounds(220,110,100,20);
    
	} 
	
}
