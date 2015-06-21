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
 * Erstellt Eingabefelder für Klasse ConstantShiftDecimalMasker
 * 
 * @author Kathrin
 *
 */

public class ConfigConstantShiftDecimalMasker {

	private boolean okShiftDistance = false;
	private Button btn1;
	private Group group;
	protected Label lblShiftDistance;
	protected Text txtShiftDistance;

	/**
	 * Methode, die ein Coposite erzeugt, in dem ein Eigabefeld für
	 * ShiftDistance erstellt wird
	 * 
	 * @param s1
	 * @param x
	 * @param y
	 */
	public ConfigConstantShiftDecimalMasker(Shell s,int x, int y) {
		group = new Group(s, SWT.SHADOW_IN | SWT.H_SCROLL | SWT.V_SCROLL);

		group.setText("ConfigConstantShiftDecimalMasker");
		GridLayout gridLayout = new GridLayout(2, true);
		group.setLayout(gridLayout);
		GridData gridData = new GridData(GridData.FILL, GridData.FILL, true,
				true);
		// gridData.horizontalSpan=5; //Bei 2 werden beide Columns mit dieser
		// Composite ausgefüllt, nächste rutscht in nächste Reihe
		group.setLayoutData(gridData);

		lblShiftDistance = new Label(group, SWT.NONE);
		txtShiftDistance = new Text(group, SWT.BORDER);

		setLabelText(lblShiftDistance, txtShiftDistance, "ShiftDistance:",
				"0.0", 1);

		txtShiftDistance.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				checkShiftDistance();
				checkOK();
			}
		});

		btn1 = new Button(group, SWT.NONE);
		btn1.setText("OK");
		gridData = new GridData(GridData.FILL, GridData.END, true, true);
		gridData.horizontalSpan = 2;
		btn1.setLayoutData(gridData);
	}

	private void checkOK() {
		if (okShiftDistance) {
			btn1.setText("OK");
			btn1.setEnabled(true);
		} else {
			btn1.setText("Not OK");
			btn1.setEnabled(false);
		}
	}

	private void checkShiftDistance() {
		okShiftDistance = RegEx.regExDouble(txtShiftDistance.getText());
		if(okShiftDistance){
			txtShiftDistance.setForeground(txtShiftDistance.getDisplay().getSystemColor(SWT.COLOR_BLACK));
		}else{
			txtShiftDistance.setForeground(txtShiftDistance.getDisplay().getSystemColor(SWT.COLOR_RED));
		}
	}

	/**
	 * Methode, die Labels + Textfelder für verschiedene Parameter erstellt. Mit
	 * int typ wird angegeben, ob Int, Double,Date,Period oder ein String
	 * erwartet wird => Aufruf RegEx
	 * 
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
