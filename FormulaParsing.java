//A.P. Chem. Chemical Formula Parsing Tools V0.1.5
//Code by Ethan MacDonald and Charles Kulick
//Currently the main functionality works, and the additional displays are yet to be implemented. 

package chemTools;
import java.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JLabel;
import java.awt.List;
import java.awt.Color;

public class ChemTools extends JFrame {
	private JPanel contentPane;
	private JTextField txtamu;
	private JTextField textField;
	private JTextField textField_1;
	public static void main(String[] args) {
		ChemTools frame = new ChemTools();
		frame.setVisible(true);
	}
	public ChemTools() {
		
		//Main Jframe setup
		setTitle("A.P. Chem. Molecule Parsing Tool");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
        
        //Input text field setup
        final JTextField txtEnterChemicalFormula = new JTextField("Enter chemical formula here...");
        txtEnterChemicalFormula.setHorizontalAlignment(SwingConstants.LEFT);
        txtEnterChemicalFormula.setFont(new Font("Century Gothic", Font.PLAIN, 25));
        txtEnterChemicalFormula.setBounds(12, 13, 458, 48);
        txtEnterChemicalFormula.setAlignmentX(Component.LEFT_ALIGNMENT);
        txtEnterChemicalFormula.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){ //removes the default text in the main text field setup
                txtEnterChemicalFormula.setText("");
                
            }
        });
        contentPane.setLayout(null);
        contentPane.add(txtEnterChemicalFormula);
        
        //Sets up the parse button. All the programs processing happens when the the button is pressed, and
        //therefore almost all code related to the processing is contained in the button's action listener.
        JButton btnNewButton = new JButton("Parse Formula");
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnNewButton.setBounds(12, 74, 160, 48);
        //btnNewButton.setSelected(true);
        contentPane.add(btnNewButton);
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		//Gets the user's input
        		String inputString = txtEnterChemicalFormula.getText();
        		
        		//Parses the formula into blocks of element and number. Single atoms are not yet given a 1
        		Scanner sc = new Scanner(System.in);
        		ArrayList<String> elements = new ArrayList<>();
        		String formula = inputString;
        		String s = "";
        		
        		//Unfathomable witchcraft 
        		for (int i=0; i<formula.length(); i++) {
        		    if (Character.isUpperCase(formula.charAt(i))) {
        		        if (!s.isEmpty()) {
        		            elements.add(s);
        		        }
        		        s = "" + formula.charAt(i);
        		    } else {
        		        s += formula.charAt(i);
        		    }
        		}
        		elements.add(s);   

        		//Handles the list which contains the kind and amount of atoms
        		final List list = new List();
                list.setFont(new Font("Century Gothic", Font.PLAIN, 25));
                list.setBackground(Color.WHITE);
                list.setBounds(12, 193, 145, 262);             
                ArrayList<String[]> splitElements = new ArrayList<>();
                
                //Uses the splitElement method to break the blocks of e.g. "C6" in arrays of e.g. {"C","6"}
        		for(int j=0; j<elements.size(); j++){
        			String[] split = splitElement(elements.get(j));
        			splitElements.add(split);	
        		}
        		
        		//Uses the explode method to create one array list which contains all the individual atoms in the inputed formula
        		//represented by their symbol. e.g. <{"H","2"},{"O","1"}> becomes <"H","H","O">
        		ArrayList<String> atoms = explode(splitElements); 
        		
        		//This is where Chuck invoked the black magic of 23 to format the output from the trash I gave him. 
        		for(int x = 0;  x< atoms.size(); x++){
        			String test = atoms.get(x);
        			if(test.equals("")){
        				continue;
        			}
        			int number = 1;
        			for(int j = x + 1; j < atoms.size(); j++){
        				String test2 = atoms.get(j);
        				if(test.equals(test2)){
        					number++;
        					atoms.set(j, "");
        					//j--;
        				}
        			}
        			//Adds the formatted output to the JList
        			list.add(test + ": " + number);
        		}
        		//Adds the JList to the screen
        		contentPane.add(list);
                
        		//Mass text field setup
                txtamu = new JTextField();
                txtamu.setText("0.0000amu");
                txtamu.setBounds(248, 222, 116, 22);
                contentPane.add(txtamu);
                txtamu.setColumns(10);
                
                //Num of unique atoms setup
                textField = new JTextField();
                textField.setText("0");
                textField.setBounds(248, 286, 116, 22);
                contentPane.add(textField);
                textField.setColumns(10);
                
                //Num of atoms setup
                textField_1 = new JTextField();
                textField_1.setText("0");
                textField_1.setBounds(248, 352, 116, 22);
                contentPane.add(textField_1);
                textField_1.setColumns(10);
                contentPane.setVisible(true);
                
                //Resets the list the when main text field is clicked. For some reason it doesn't update if this isn't done 
                txtEnterChemicalFormula.addMouseListener(new MouseAdapter(){
                    @Override
                    public void mouseClicked(MouseEvent e){
                        txtEnterChemicalFormula.setText("");
                        contentPane.remove(list);
                    }
                });
        	}
        });
        //Labels for the GUI//
        
        JLabel lblResults = new JLabel("R E S U L T S");
        lblResults.setFont(new Font("Century Gothic", Font.PLAIN, 19));
        lblResults.setHorizontalAlignment(SwingConstants.CENTER);
        lblResults.setBounds(12, 122, 458, 22);
        contentPane.add(lblResults);
        
        JLabel label = new JLabel("___________________________________________________________________");
        label.setBounds(12, 128, 470, 16);
        contentPane.add(label);
        
        JLabel lblUniqueElements = new JLabel("Unique Elements");
        lblUniqueElements.setFont(new Font("Century Gothic", Font.PLAIN, 19));
        lblUniqueElements.setBounds(248, 257, 150, 30);
        contentPane.add(lblUniqueElements);
		
        JLabel lblAtomicMass = new JLabel("Atomic Mass");
        lblAtomicMass.setFont(new Font("Century Gothic", Font.PLAIN, 19));
        lblAtomicMass.setBounds(248, 193, 115, 30);
        contentPane.add(lblAtomicMass);
        
        JLabel lblNumberOfAtoms = new JLabel("Number of Atoms");
        lblNumberOfAtoms.setFont(new Font("Century Gothic", Font.PLAIN, 19));
        lblNumberOfAtoms.setBounds(248, 321, 160, 30);
        contentPane.add(lblNumberOfAtoms);
        
        JLabel lblAtomsAmount = new JLabel("Atoms & Amount");
        lblAtomsAmount.setFont(new Font("Century Gothic", Font.PLAIN, 19));
        lblAtomsAmount.setBounds(12, 157, 160, 30);
        contentPane.add(lblAtomsAmount);
	}
	
	//takes an element chunk e.g."C6" and splits it so it can be added to the list display
	public static String[] splitElement(String e){
		if(e.length()>1){
			String[] split = e.split("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)");
			return split;
		}
		else{
			String[] split = {e,"1"};
			return split;
		}
	}
	//takes an arraylist that contains arrays of the element chunks created by splitElement 
	public static ArrayList<String> explode(ArrayList<String[]> splitElements){	
		ArrayList<String> atoms = new ArrayList<>();
		for(int i=0; i<splitElements.size(); i++){
			String timesString = splitElements.get(i)[1];
			int times = Integer.parseInt(timesString);
			for(int j=0; j<times; j++){
				String element = splitElements.get(i)[0];
				atoms.add(element);
			}
		}
		return atoms;
	}	
}
