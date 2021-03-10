import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class Lab2 extends JFrame implements ActionListener {
	JButton open = new JButton("Next Program");
	JTextArea result = new JTextArea(20,40);
	JLabel errors = new JLabel();
	JScrollPane scroller = new JScrollPane();
	
	public Lab2() {
		setLayout(new java.awt.FlowLayout());
		setSize(500,430);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		add(open); open.addActionListener(this);
		scroller.getViewport().add(result);
		add(scroller);
		add(errors);
	}
	
	public void actionPerformed(ActionEvent evt) {
		result.setText("");	//clear TextArea for next program
		errors.setText("");
		processProgram();
	}
	
	public static void main(String[] args) {
		Lab2 display = new Lab2();
		display.setVisible(true);
	}
	
	String getFileName() {
		JFileChooser fc = new JFileChooser();
		int returnVal = fc.showOpenDialog(this);
		if (returnVal == JFileChooser.APPROVE_OPTION)
			return fc.getSelectedFile().getPath();
		else
			return null;
	}
	
/************************************************************************/
/* Put your implementation of the processProgram method here.           */
/* Use the getFileName method to allow the user to select a program.    */
/* Then simulate the execution of that program.                         */
/* You may add any other methods that you think are appropriate.        */
/* However, you should not change anything in the code that I have      */
/* written.                                                             */
/************************************************************************/

	public void processProgram()
	{
		String toProcess = getFileName();
		System.out.println("Got the file name");
		System.out.println(toProcess);
		System.out.println("printed toProcess");
		//To do: get instructions text from readmetxt. 
		result.setText(processTime(toProcess));

	}
	/**
	 * 
	 * @param instructions This is a line of code using the BASIC-style code givn. 
	 * @return This returns the thing to print. 
	 */
	public String processTime(String instructions){
		return "Test Code";
		//To do
	}


}