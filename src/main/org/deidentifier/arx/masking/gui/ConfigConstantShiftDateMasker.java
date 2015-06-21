import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * Erstellt Eingabefelder für Klasse ConstantShiftDateMasker
 * 
 * @author Kathrin
 *
 */
public class ConfigConstantShiftDateMasker {

	private boolean okShiftPeriod = false;
	private Button btn1;
	private Group group;
	private Label lblShiftPeriod;
	private Text txtShiftPeriod;

	/**
	 * Methode, die ein Coposite erzeugt, in dem ein Eigabefeld für ShiftPeriod
	 * erstellt wird
	 * 
	 * @param s1
	 * @param x
	 * @param y
	 */
	public ConfigConstantShiftDateMasker(Shell s,int x, int y) {
		group = new Group(s, SWT.SHADOW_IN | SWT.H_SCROLL | SWT.V_SCROLL);
		group.setText("ConfigConstantShiftDateMasker");
		GridLayout gridLayout = new GridLayout(2, true);
		group.setLayout(gridLayout);
		GridData gridData = new GridData(SWT.H_SCROLL | SWT.V_SCROLL);
		gridData.horizontalAlignment = SWT.FILL;
		gridData.verticalAlignment = SWT.FILL;
		gridData.horizontalSpan = 1;
		group.setLayoutData(gridData);
		group.computeSize(SWT.MAX, SWT.MAX);

		lblShiftPeriod = new Label(group, SWT.NONE);
		txtShiftPeriod = new Text(group, SWT.BORDER);

		setLabelText(lblShiftPeriod, txtShiftPeriod, "ShiftPeriod:",
				"05y 04m 03d 01h", 3);

		txtShiftPeriod.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				checkShiftPeriod();
				checkOK();
			}
		});

		btn1 = new Button(group, SWT.PUSH);
		btn1.setText("OK");
		gridData = new GridData(GridData.FILL, GridData.END, true, true);
		gridData.horizontalSpan = 2;
		btn1.setLayoutData(gridData);
		checkShiftPeriod();

	}

	private void checkOK() {
		if (okShiftPeriod) {
			btn1.setText("OK");
			btn1.setEnabled(true);
		} else {
			btn1.setText("Not OK");
			btn1.setEnabled(false);
		}
	}

	private void checkShiftPeriod() {
		okShiftPeriod = RegEx.regExPeriod(txtShiftPeriod.getText());
		if(okShiftPeriod){
			txtShiftPeriod.setForeground(txtShiftPeriod.getDisplay().getSystemColor(SWT.COLOR_BLACK));
		}else{
			txtShiftPeriod.setForeground(txtShiftPeriod.getDisplay().getSystemColor(SWT.COLOR_RED));
		}
	}

	/**
	 * Methode, die Labels + Textfelder für verschiedene Parameter erstellt. Mit
	 * int typ wird angegeben, ob Int, Double,Date,Period oder ein String
	 * erwartet wird => Aufruf RegEx
	 * 
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

	public void setLabelText(Label lbl1, final Text txt1, String lab1,
			String val1, int typ) {
		GridData gridData = new GridData();
		lbl1.setLayoutData(gridData);
		lbl1.setText(lab1);
		gridData = new GridData();
		gridData.grabExcessHorizontalSpace = true;
		gridData.horizontalAlignment = GridData.FILL;
		txt1.setLayoutData(gridData);
		txt1.setText(val1);
		/**
		 * Wenn das Label eines Parameter der Verteilung leer ist, werden das
		 * entsprechende Label und Textfeld nicht angezeigt
		 * 
		 */

		if (lab1 == "") {
			lbl1.setVisible(false);
			txt1.setVisible(false);
		} else {
			lbl1.setVisible(true);
			txt1.setVisible(true);
		}
	}
}
