import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class Lab2 extends JFrame implements ActionListener {
	JButton open = new JButton("Next Program");
	JTextArea result = new JTextArea(20,40);
	JLabel errors = new JLabel();
	JScrollPane scroller = new JScrollPane();

	//This is a HashTable Dictionary with all of the variables. (Added by me :D)
	Hashtable<String, String> hashVars = new Hashtable<String, String>();


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

		/*File readFile = new File(toProcess);

		Scanner sc = new Scanner(readFile);
		while (sc.hasNextLine())
		{
			System.out.println(sc.nextLine());
		}
		*/
		System.out.println(toProcess);
		System.out.println("printed toProcess");
		//To do: get instructions text from readmetxt. 
		/*
		for line in readmetext 
		*/
		

	}



	public String getVariable(String variableName)
	{
		return "hi";
	}
	public void setVariable(String variableName, String value)
	{
		
	}

	/**
	 * 
	 * @param instructions This is a line of code split into different words, and each word is an element in an arraylist
	 * @param currentIndex The current index the code
	 * @return This returns the next index to go to. In the case of END, it returns -1, which will terminate the program in another function
	 */
	public int processTime(ArrayList<String> instructions, int currentIndex){

		if (instructions.get(0).equals("END"))
		{
			return -1;
		}
		else if (instructions.get(0).equals("GOTO"))
		{
			return Integer.parseInt(instructions.get(1));
		}
		else if (instructions.get(0).equals("PRINT"))
		{
			result.setText(result.getText() + hashVars.get(instructions.get(1)));
			return currentIndex + 1;
		}
		
		else if (instructions.get(0).equals("IF"))
		{
			boolean isTrue = false;
			//TO DO. need to do a lot of exceptions and stuff 
			
			result.setText(result.getText() + hashVars.get(instructions.get(0)));
			
			
			if (isTrue)
			{
				ArrayList<String> tempInstructions = new ArrayList<String>();

				for (int i = 5; i < instructions.size(); i++)
				{
					tempInstructions.add(instructions.get(i));
				}
				return simpleStatementGetter(tempInstructions,currentIndex);
			}
			else {
				return currentIndex + 1;
			}
			
		}
		else if (instructions.get(1).equals("=")){
			hashVars.put(instructions.get(0),instructions.get(2));
			return currentIndex + 1;
		}
		else {
			//SET TO INVALID INDEX
			return -1;
		}
		
	}
	public int simpleStatementGetter(ArrayList<String> instructions, int currentIndex)
	{
		if (instructions.get(0).equals("GOTO"))
		{
			return Integer.parseInt(instructions.get(1));
		}
		else if (instructions.get(0).equals("PRINT"))
		{
			result.setText(result.getText() + hashVars.get(instructions.get(1)));
			return currentIndex + 1;
		}
		else if (instructions.get(1).equals("=")){
			hashVars.put(instructions.get(0),instructions.get(2));
			return currentIndex + 1;
		}
		else {
			//SET TEXT TO INVALID INPUT
			return -1;
		}
	}

}