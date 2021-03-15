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
		ArrayList<ArrayList<String>> allInstructionLists =  new ArrayList<ArrayList<String>>(); 
		//To Do: Fill in allInstructionLists with the ones givemn by the program
		String path = getFileName();


		System.out.println("The file path is " + path);
		//Reading the file. 
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(path));
			String line = reader.readLine();
			while (line != null)
			{
				//I named the temporary arraylist meowmeowmeowhellokittens because i was bored.
				ArrayList<String> meowmeowmeowhellokittens = new ArrayList<>(Arrays.asList(line.split(" ")));
				allInstructionLists.add(meowmeowmeowhellokittens);
				System.out.println(meowmeowmeowhellokittens);

				//REads next line
				line = reader.readLine();
			}
		}
		catch (Exception e)
		{
			System.out.println("yeah your file dont work");
		}
		int index = 1;
		while (index != -1)
		{
			index = processTime(allInstructionLists.get(index - 1), index);
		}


	}


	
	/** 
	 * @param variableName
	 * @return String
	 */
	//Getters and setters from the hashVars dictionary. 
	public String getVariable(String variableName)
	{	
		try {
			return String.valueOf(Double.parseDouble(hashVars.get(variableName)));
		}
		catch (Exception e)
		{
			//TO DO : set to -1
			return null;
		}
	}
	
	/** 
	 * @param variableName
	 * @param value
	 */
	//Getters and setters from the hashVars dictionary. 
	public void setVariable(String variableName, String value)
	{
		try {
			hashVars.put(variableName, value);
		}
		catch (Exception e)
		{
			invalidStatement();
		}
	}

	/**
	 * This is the main function in order to process a single command given an arraylist of words compromising a command. 
	 * @param instructions This is a line of code split into different words, and each word is an element in an arraylist
	 * @param currentIndex The current index the code
	 * @return This returns the next index to go to. In the case of END, it returns -1, which will terminate the program in another function
	 */
	public int processTime(ArrayList<String> instructions, int currentIndex){
		//Just in case haah
		try {

			//Possible end statement
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
				result.setText(result.getText() + "\n" +  getVariable(instructions.get(1)));
				return currentIndex + 1;
			}
			
			else if (instructions.get(0).equals("IF"))
			{
				boolean isTrue = false;
				//Is there even an if/is/then statement? If not then we return invalid statement
				if ((!instructions.get(0).equals("IF")) ||(!instructions.get(2).equals("IS")) || (!instructions.get(4).equals("THEN")))
				{
					return invalidStatement();
				}

				//Does the variable put forth in variable exist???
				 if (getVariable(instructions.get(1)) != null)
				 {
					//if variable exists, Does the variable put forth in value exist???
					if ((getVariable(instructions.get(3)) != null))
					{
						//if variable and value exist, Is variable = value???
						if (Double.parseDouble(getVariable(instructions.get(1))) == Double.parseDouble(instructions.get(3)))
						{
							isTrue = true;
						}
					}
					//If variable exists but value doesn't exist, is value a double?
					try {
						double toCompare = Double.parseDouble(instructions.get(3));
						if (Double.parseDouble(getVariable(instructions.get(1))) == toCompare)
						{
							isTrue = true;
						}
					}
					catch (Exception e) //This is an invalid statement since value is neither a variable nor a double. 
					{
						return invalidStatement();
					}
				 }
				 //The variable does not exist.
				 else
				 {
					return invalidStatement();
				 }
				
				
				if (isTrue)
				{
					ArrayList<String> tempInstructions = new ArrayList<String>();

					for (int i = 5; i < instructions.size(); i++)
					{
						tempInstructions.add(instructions.get(i));
					}
					return simpleStatementGetter(tempInstructions,currentIndex);
				}
				//Assuming the if/is statement is not true, we will just go to the next index. 
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
		} catch (Exception e)
		{
			return invalidStatement();
		}
		
	}







	/**
	 * This is basically the same as process time, except just for the if conditionals' simple statement. I'm not sure why I need this and can't just use the processTime but it bugged out.
	 * @param instructions A simple statement in 
	 * @param currentIndex returns the current index. 
	 * @return
	 */
	public int simpleStatementGetter(ArrayList<String> instructions, int currentIndex)
	{
		if (instructions.get(0).equals("GOTO"))
		{
			return Integer.parseInt(instructions.get(1));
		}
		else if (instructions.get(0).equals("PRINT"))
		{
			result.setText(result.getText() + "\n" +  hashVars.get(instructions.get(1)));
			return currentIndex + 1;
		}
		else if (instructions.get(1).equals("=")){
			hashVars.put(instructions.get(0),instructions.get(2));
			return currentIndex + 1;
		}
		else {
			return invalidStatement();
		}
	}


	/**
	 * This adds to the error log and calls for an END program. 
	 * @return -1 (aka what we're checking for to end the program)
	 */
	public int invalidStatement()
	{
		//TO DO : Set text to invalid input
		errors.setText("There was an invalid statement inside your file.");
		return -1;
	}
		

}