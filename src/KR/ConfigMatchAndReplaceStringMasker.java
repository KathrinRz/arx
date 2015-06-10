import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Text;

public class ConfigMatchAndReplaceStringMasker {
	
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
	 public boolean regExBoolean (String s){
		 if(s.equals("true")||s.equals("false")) return true;
		 return false;
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
        if (typ == 4){
            t1.addModifyListener(new ModifyListener(){
            	public void modifyText(ModifyEvent e){
            		if (regExBoolean(t1.getText())){
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
	
	
	public ConfigMatchAndReplaceStringMasker (Display d, int x, int y){
		s1=new Shell(d);
		s1.setSize(500, 500);
		s1.setText("ConfigMatchAndReplaceStringMasker");
		
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
	
    SetLabelText(label1,20,20,120,20,text1,220,20,100,20,"RegExPattern:","^.{0,5}",5); //Regex: At least zero and up to 5 occurrences of any character, but only if it occurs at the beginning. 
    SetLabelText(label2,20,60,120,20,text2,220,60,100,20,"ReplacementString:","*",5);  //stars out the first 5 letters of the input
    SetLabelText(label3,20,100,120,20,text3,220,100,100,20,"ReplacingAllMatches:","true",4); //Replace all matches
    SetLabelText(label4,20,140,120,20,text4,220,140,100,20,"ReplacingAllCharacters:","false",4); //replace all characters in match
    
    
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


