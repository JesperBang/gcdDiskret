package gcdDiskret;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.JTextPane;
import javax.swing.JRadioButton;

public class gcdFrame{
	
	JFrame frame;
	private JLabel lblResults, lblGcd;
	private int temp1, temp2, step, ss = 0;
	private String inp1, inp2, si1, si2, t1, t2;
	private JTextField textField, textField_1, textField_2;
	private ArrayList<Integer> rlist = new ArrayList<Integer>();
	private ArrayList<Integer> qlist = new ArrayList<Integer>();
	private ArrayList<Integer> slist = new ArrayList<Integer>();
	private ArrayList<Integer> tlist = new ArrayList<Integer>();
	private final JRadioButton rdbtnIncludeException = new JRadioButton("Include condition");
	
	private JTextPane textPane, textPane_1, textPane_2, textPane_3, textPane_4, textPane_5, textPane_6, textPane_7, textPane_8, textPane_9, textPane_10,
	textPane_11, textPane_12, textPane_13, textPane_14, textPane_15, textPane_16, textPane_17, textPane_18, textPane_19, textPane_20, textPane_21,
	textPane_22, textPane_23, textPane_24, textPane_25, textPane_26, textPane_27, textPane_28, textPane_29, textPane_30, textPane_31, textPane_32,
	textPane_33, textPane_34, textPane_35, textPane_36, textPane_37;
	private JLabel lblAdditionalResults;
	private JLabel lblNewLabel_1;
	private JLabel label_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					gcdFrame window = new gcdFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the application.
	 */
	public gcdFrame() {
		initialize();
	}
	
	/**
	 * Methods
	 */
	public void change(){
		temp1 = Integer.parseInt(inp1);
		temp2 = Integer.parseInt(inp2);
		
		rlist.clear(); qlist.clear(); slist.clear(); tlist.clear();
		
		if(temp1 < temp2){
			System.out.println("bck");
			//Initial values for backwards	
			rlist.add(temp2); 
			rlist.add(temp1);
			
			slist.add(0); 
			slist.add(1);
			tlist.add(1); 
			tlist.add(0);
			ss = 1;
			
			calc();
		}else{
			System.out.println("forw");
			//Initial values for forwards
			rlist.add(temp1); 
			rlist.add(temp2);
			
			slist.add(1); 
			slist.add(0);
			tlist.add(0); 
			tlist.add(1);
			ss = 0;
			
			calc();
		}
	}
	
	/**
	 * Method for calculating 
	 */
	public  void calc(){
		
		step = 2; 
		
		while(true){
			if(rlist.get(step-1) != 0){
				rlist.add(rlist.get(step-2)%rlist.get(step-1));
				qlist.add(rlist.get(step-2)/rlist.get(step-1));
				slist.add(slist.get(step-2)-(qlist.get(step-2)*slist.get(step-1)));
				tlist.add(tlist.get(step-2)-(qlist.get(step-2)*tlist.get(step-1)));
			} else{
				slist.remove(slist.size()-1);
				tlist.remove(tlist.size()-1);
				break;
			}
			
			step++;
		}
		
		
		System.out.println(qlist);
		System.out.println(rlist);
		System.out.println(slist);
		System.out.println(tlist);
		paint();
		
		//Result printing
		if(ss == 1){
			System.out.println("true");
			si2 = Integer.toString(rlist.get(1));
			si1 = Integer.toString(rlist.get(0));
			t1  = Integer.toString(rlist.get(1));
			t2  = Integer.toString(rlist.get(0));
		}else if(ss == 0){
			System.out.println("false");
			si1 = Integer.toString(rlist.get(1));
			si2 = Integer.toString(rlist.get(0));
			t2  = Integer.toString(rlist.get(1));
			t1  = Integer.toString(rlist.get(0));
		}
		System.out.println(Integer.toString(rlist.get(1)));
		lblGcd.setText("<html>gcd("+t1+","+t2+")= <font color='blue'>"+
		Integer.toString(rlist.get(rlist.size()-2))+"</font> = <font color='red'>"+Integer.toString(slist.get(slist.size()-1))+"</font> * "+
		si2+" + <font color='green'>"+Integer.toString(tlist.get(tlist.size()-1))+"</font> * "+
		si1+"</html>");
		
		//Additional result printing
		lblNewLabel_1.setText("<html>s = <font color='red'>"+Integer.toString(slist.get(slist.size()-1))+"</font>, t = <font color='green'>"+Integer.toString(tlist.get(tlist.size()-1))+"</font></html>");
		
		if(rdbtnIncludeException.isSelected()){
			double db1 = Integer.parseInt(textField_2.getText())/(double) rlist.get(rlist.size()-2);
			System.out.println((double) rlist.get(rlist.size()-2)+"/"+Integer.parseInt(textField_2.getText())+" = "+db1);
			if ((db1 == Math.floor(db1)) && !Double.isInfinite(db1)) {
				label_1.setText("<html>gcd("+t1+","+t2+")= <font color='blue'>"+
				Integer.toString(rlist.get(rlist.size()-2))+"</font> | "+textField_2.getText()+"<font color='green'> True </font></html>"); 
			}else{
				label_1.setText("<html>gcd("+t1+","+t2+")= <font color='blue'>"+
						Integer.toString(rlist.get(rlist.size()-2))+"</font> | "+textField_2.getText()+"<font color='red'> False </font></html>");
			}
		}else{
			label_1.setText("");
		}
		if(ss == 1){
			qlist.add(0, 0);
			rlist.add(0, temp1);
			slist.add(0, 1);
			tlist.add(0, 0);
			paint(); //remove for normal
		}
	}
	
	public void cleanup(){	
		//Dirty and incorrect way to do cleanup
		try{
			//Rlist
			textPane.setText(null);
			textPane_1.setText(null);
			textPane_10.setText(null);
			textPane_11.setText(null);
			textPane_12.setText(null);
			textPane_13.setText(null);
			textPane_14.setText(null);
			textPane_15.setText(null);
			textPane_16.setText(null);
			textPane_17.setText(null);
		}catch (IndexOutOfBoundsException e) {
			//do nothing
		}
		try{
			//Qlist
			textPane_2.setText(null);
			textPane_3.setText(null);
			textPane_4.setText(null);
			textPane_5.setText(null);
			textPane_6.setText(null);
			textPane_7.setText(null);
			textPane_8.setText(null);
			textPane_9.setText(null);
		}catch (IndexOutOfBoundsException e) {
			//do nothing
		}
		try{
			//Slist
			textPane_18.setText(null);
			textPane_19.setText(null);
			textPane_20.setText(null);
			textPane_21.setText(null);
			textPane_22.setText(null);
			textPane_23.setText(null);
			textPane_24.setText(null);
			textPane_25.setText(null);
			textPane_26.setText(null);
		}catch (IndexOutOfBoundsException e) {
			//do nothing
		}
		try{
			//Tlist
			textPane_28.setText(null);
			textPane_29.setText(null);
			textPane_30.setText(null);
			textPane_31.setText(null);
			textPane_32.setText(null);
			textPane_33.setText(null);
			textPane_34.setText(null);
			textPane_35.setText(null);
			textPane_36.setText(null);
			textPane_37.setText(null);
		}catch (IndexOutOfBoundsException e) {
			//do nothing
		}
	}
	
	public void paint(){
		//Drawing results on image
		try{
			//Rlist
			textPane.setText(Integer.toString(rlist.get(0)));
			textPane_1.setText(Integer.toString(rlist.get(1)));
			textPane_10.setText(Integer.toString(rlist.get(2)));
			textPane_11.setText(Integer.toString(rlist.get(3)));
			textPane_12.setText(Integer.toString(rlist.get(4)));
			textPane_13.setText(Integer.toString(rlist.get(5)));
			textPane_14.setText(Integer.toString(rlist.get(6)));
			textPane_15.setText(Integer.toString(rlist.get(7)));
			textPane_16.setText(Integer.toString(rlist.get(8)));
			textPane_17.setText(Integer.toString(rlist.get(9)));
		}catch (IndexOutOfBoundsException e) {
			//do nothing
		}
		try{
			//Qlist
			textPane_2.setText(Integer.toString(qlist.get(0)));
			textPane_3.setText(Integer.toString(qlist.get(1)));
			textPane_4.setText(Integer.toString(qlist.get(2)));
			textPane_5.setText(Integer.toString(qlist.get(3)));
			textPane_6.setText(Integer.toString(qlist.get(4)));
			textPane_7.setText(Integer.toString(qlist.get(5)));
			textPane_8.setText(Integer.toString(qlist.get(6)));
			textPane_9.setText(Integer.toString(qlist.get(7)));
		}catch (IndexOutOfBoundsException e) {
			//do nothing
		}
		try{
			//Slist
			textPane_18.setText(Integer.toString(slist.get(0)));
			textPane_19.setText(Integer.toString(slist.get(1)));
			textPane_20.setText(Integer.toString(slist.get(2)));
			textPane_21.setText(Integer.toString(slist.get(3)));
			textPane_22.setText(Integer.toString(slist.get(4)));
			textPane_23.setText(Integer.toString(slist.get(5)));
			textPane_24.setText(Integer.toString(slist.get(6)));
			textPane_25.setText(Integer.toString(slist.get(7)));
			textPane_26.setText(Integer.toString(slist.get(8)));
		}catch (IndexOutOfBoundsException e) {
			//do nothing
		}
		try{
			//Tlist
			textPane_28.setText(Integer.toString(tlist.get(0)));
			textPane_29.setText(Integer.toString(tlist.get(1)));
			textPane_30.setText(Integer.toString(tlist.get(2)));
			textPane_31.setText(Integer.toString(tlist.get(3)));
			textPane_32.setText(Integer.toString(tlist.get(4)));
			textPane_33.setText(Integer.toString(tlist.get(5)));
			textPane_34.setText(Integer.toString(tlist.get(6)));
			textPane_35.setText(Integer.toString(tlist.get(7)));
			textPane_36.setText(Integer.toString(tlist.get(8)));
			textPane_37.setText(Integer.toString(tlist.get(9)));
		}catch (IndexOutOfBoundsException e) {
			//do nothing
		}
	}
	
	/**
	 *Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setTitle("v0.1Release - gcd Breaker");
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(gcdFrame.class.getResource("/resources/discrete.gif")));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("");
		label.setBounds(0, 0, 188, 269);
		label.setIcon(new ImageIcon(gcdFrame.class.getResource("/resources/sfd.png")));
		frame.getContentPane().add(label);
		
		JLabel lblNewLabel = new JLabel("gcd(           ,           )");
		lblNewLabel.setBounds(198, 11, 171, 14);
		frame.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(224, 8, 30, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(261, 8, 30, 20);
		frame.getContentPane().add(textField_1);
		
		JButton btnBreakIt = new JButton("Break it!");
		btnBreakIt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textField.getText().length() > 0 && textField_1.getText().length() > 0){
					cleanup();
					inp1 = textField.getText();
					inp2 = textField_1.getText();
					change();
				}
			}
		});
		btnBreakIt.setBounds(326, 7, 89, 23);
		frame.getContentPane().add(btnBreakIt);
		
		textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setBounds(82, 34, 30, 20);
		frame.getContentPane().add(textPane);
		
		textPane_1 = new JTextPane();
		textPane_1.setEditable(false);
		textPane_1.setBounds(82, 59, 30, 20);
		frame.getContentPane().add(textPane_1);
		
		textPane_2 = new JTextPane();
		textPane_2.setEditable(false);
		textPane_2.setBounds(44, 85, 30, 20);
		frame.getContentPane().add(textPane_2);
		
		textPane_3 = new JTextPane();
		textPane_3.setEditable(false);
		textPane_3.setBounds(44, 110, 30, 20);
		frame.getContentPane().add(textPane_3);
		
		textPane_4 = new JTextPane();
		textPane_4.setEditable(false);
		textPane_4.setBounds(44, 135, 30, 20);
		frame.getContentPane().add(textPane_4);
		
		textPane_5 = new JTextPane();
		textPane_5.setEditable(false);
		textPane_5.setBounds(44, 158, 30, 20);
		frame.getContentPane().add(textPane_5);
		
		textPane_6 = new JTextPane();
		textPane_6.setEditable(false);
		textPane_6.setBounds(44, 182, 30, 20);
		frame.getContentPane().add(textPane_6);
		
		textPane_7 = new JTextPane();
		textPane_7.setEditable(false);
		textPane_7.setBounds(44, 206, 30, 20);
		frame.getContentPane().add(textPane_7);
		
		textPane_8 = new JTextPane();
		textPane_8.setEditable(false);
		textPane_8.setBounds(44, 228, 30, 20);
		frame.getContentPane().add(textPane_8);
		
		textPane_9 = new JTextPane();
		textPane_9.setEditable(false);
		textPane_9.setBounds(44, 250, 30, 20);
		frame.getContentPane().add(textPane_9);
		
		textPane_10 = new JTextPane();
		textPane_10.setEditable(false);
		textPane_10.setBounds(82, 85, 30, 20);
		frame.getContentPane().add(textPane_10);
		
		textPane_11 = new JTextPane();
		textPane_11.setEditable(false);
		textPane_11.setBounds(82, 110, 30, 20);
		frame.getContentPane().add(textPane_11);
		
		textPane_12 = new JTextPane();
		textPane_12.setEditable(false);
		textPane_12.setBounds(82, 135, 30, 20);
		frame.getContentPane().add(textPane_12);
		
		textPane_13 = new JTextPane();
		textPane_13.setEditable(false);
		textPane_13.setBounds(82, 158, 30, 20);
		frame.getContentPane().add(textPane_13);
		
		textPane_14 = new JTextPane();
		textPane_14.setEditable(false);
		textPane_14.setBounds(82, 182, 30, 20);
		frame.getContentPane().add(textPane_14);
		
		textPane_15 = new JTextPane();
		textPane_15.setEditable(false);
		textPane_15.setBounds(82, 206, 30, 20);
		frame.getContentPane().add(textPane_15);
		
		textPane_16 = new JTextPane();
		textPane_16.setEditable(false);
		textPane_16.setBounds(82, 228, 30, 20);
		frame.getContentPane().add(textPane_16);
		
		textPane_17 = new JTextPane();
		textPane_17.setEditable(false);
		textPane_17.setBounds(82, 249, 30, 20);
		frame.getContentPane().add(textPane_17);
		
		textPane_18 = new JTextPane();
		textPane_18.setEditable(false);
		textPane_18.setBounds(121, 34, 30, 20);
		frame.getContentPane().add(textPane_18);
		
		textPane_19 = new JTextPane();
		textPane_19.setEditable(false);
		textPane_19.setBounds(121, 59, 30, 20);
		frame.getContentPane().add(textPane_19);
		
		textPane_20 = new JTextPane();
		textPane_20.setEditable(false);
		textPane_20.setBounds(121, 85, 30, 20);
		frame.getContentPane().add(textPane_20);
		
		textPane_21 = new JTextPane();
		textPane_21.setEditable(false);
		textPane_21.setBounds(121, 110, 30, 20);
		frame.getContentPane().add(textPane_21);
		
		textPane_22 = new JTextPane();
		textPane_22.setEditable(false);
		textPane_22.setBounds(121, 135, 30, 20);
		frame.getContentPane().add(textPane_22);
		
		textPane_23 = new JTextPane();
		textPane_23.setEditable(false);
		textPane_23.setBounds(121, 158, 30, 20);
		frame.getContentPane().add(textPane_23);
		
		textPane_24 = new JTextPane();
		textPane_24.setEditable(false);
		textPane_24.setBounds(121, 182, 30, 20);
		frame.getContentPane().add(textPane_24);
		
		textPane_25 = new JTextPane();
		textPane_25.setEditable(false);
		textPane_25.setBounds(121, 206, 30, 20);
		frame.getContentPane().add(textPane_25);
		
		textPane_26 = new JTextPane();
		textPane_26.setEditable(false);
		textPane_26.setBounds(121, 228, 30, 20);
		frame.getContentPane().add(textPane_26);
		
		textPane_27 = new JTextPane();
		textPane_27.setEditable(false);
		textPane_27.setBounds(121, 249, 30, 20);
		frame.getContentPane().add(textPane_27);
		
		textPane_28 = new JTextPane();
		textPane_28.setEditable(false);
		textPane_28.setBounds(158, 34, 30, 20);
		frame.getContentPane().add(textPane_28);
		
		textPane_29 = new JTextPane();
		textPane_29.setEditable(false);
		textPane_29.setBounds(158, 59, 30, 20);
		frame.getContentPane().add(textPane_29);
		
		textPane_30 = new JTextPane();
		textPane_30.setEditable(false);
		textPane_30.setBounds(158, 85, 30, 20);
		frame.getContentPane().add(textPane_30);
		
		textPane_31 = new JTextPane();
		textPane_31.setEditable(false);
		textPane_31.setBounds(158, 110, 30, 20);
		frame.getContentPane().add(textPane_31);
		
		textPane_32 = new JTextPane();
		textPane_32.setEditable(false);
		textPane_32.setBounds(158, 135, 30, 20);
		frame.getContentPane().add(textPane_32);
		
		textPane_33 = new JTextPane();
		textPane_33.setEditable(false);
		textPane_33.setBounds(158, 158, 30, 20);
		frame.getContentPane().add(textPane_33);
		
		textPane_34 = new JTextPane();
		textPane_34.setEditable(false);
		textPane_34.setBounds(158, 182, 30, 20);
		frame.getContentPane().add(textPane_34);
		
		textPane_35 = new JTextPane();
		textPane_35.setEditable(false);
		textPane_35.setBounds(158, 206, 30, 20);
		frame.getContentPane().add(textPane_35);
		
		textPane_36 = new JTextPane();
		textPane_36.setEditable(false);
		textPane_36.setBounds(158, 228, 30, 20);
		frame.getContentPane().add(textPane_36);
		
		textPane_37 = new JTextPane();
		textPane_37.setEditable(false);
		textPane_37.setBounds(158, 250, 30, 20);
		frame.getContentPane().add(textPane_37);
		
		lblResults = new JLabel("Results:");
		lblResults.setBounds(198, 128, 46, 14);
		frame.getContentPane().add(lblResults);
		
		lblGcd = new JLabel("");
		lblGcd.setBounds(198, 141, 236, 14);
		frame.getContentPane().add(lblGcd);
		rdbtnIncludeException.setBounds(194, 35, 155, 20);
		frame.getContentPane().add(rdbtnIncludeException);
		
		textField_2 = new JTextField();
		textField_2.setBounds(261, 62, 39, 20);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblCondition = new JLabel("Condition");
		lblCondition.setBounds(198, 65, 77, 14);
		frame.getContentPane().add(lblCondition);
		
		lblAdditionalResults = new JLabel("Additional results:");
		lblAdditionalResults.setBounds(198, 188, 122, 14);
		frame.getContentPane().add(lblAdditionalResults);
		
		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(198, 206, 93, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		label_1 = new JLabel("");
		label_1.setBounds(198, 234, 236, 14);
		frame.getContentPane().add(label_1);
		
		JLabel lblByJesperBang = new JLabel("<html><B>by</B><I> Jesper Bang - DTU s144211</I></html>");
		lblByJesperBang.setBounds(261, 250, 183, 18);
		frame.getContentPane().add(lblByJesperBang);
	}
}