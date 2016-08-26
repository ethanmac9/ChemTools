//A.P. Chem. Chemical Formula Parsing Tools V0.1.5.2
//Code by Ethan MacDonald and Chuck8521
//Currently the main functionality and the atomic mass display works, and the additional displays are yet to be implemented. 

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
import javax.swing.JList;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;

public class ChemTools extends JFrame {
	
	//Initialize the map of elements and their amus
	private static final Map<String, Double> amu;
    	static {
        Map<String, Double> mass = new HashMap<String, Double>();
        mass.put("H", 1.008);
        mass.put("He", 4.003); //End period 1
        mass.put("Li", 6.941);
        mass.put("Be", 9.012);
        mass.put("B", 10.81);
        mass.put("C", 12.01);
        mass.put("N", 14.01);
        mass.put("O", 16.0);
        mass.put("F", 19.0);
        mass.put("Ne", 20.18); //End period 2
        mass.put("Na", 22.99);
        mass.put("Mg", 24.31);
        mass.put("Al", 26.98);
        mass.put("Si", 28.09);
        mass.put("P", 30.97);
        mass.put("S", 32.07);
        mass.put("Cl", 35.45);
        mass.put("Ar", 39.95); //End period 3
        mass.put("K", 39.1);
        mass.put("Ca", 40.08);
        mass.put("Sc", 44.96);
        mass.put("Ti", 47.88);
        mass.put("V", 50.94);
        mass.put("Cr", 52.0);
        mass.put("Mn", 54.94);
        mass.put("Fe", 55.85);
        mass.put("Co", 58.93);
        mass.put("Ni", 58.69);
        mass.put("Cu", 63.55);
        mass.put("Zn", 65.38);
        mass.put("Ga", 69.72);
        mass.put("Ge", 72.59);
        mass.put("As", 74.92);
        mass.put("Se", 78.96);
        mass.put("Br", 79.9);
        mass.put("Kr", 83.8); //End period 4
        mass.put("Rb", 85.468);
        mass.put("Sr", 87.62);
        mass.put("Y", 88.906);
        mass.put("Zr", 91.224);
        mass.put("Nb", 92.906);
        mass.put("Mo", 95.96);
        mass.put("Te", 98.0); ////Artificial  
        mass.put("Ru", 101.07);
        mass.put("Rh", 102.91);
        mass.put("Pd", 106.42);
        mass.put("Ag", 107.87);
        mass.put("Cd", 112.41);
        mass.put("In", 114.82);
        mass.put("Sn", 118.71);
        mass.put("Sb", 121.76);
        mass.put("Te", 127.60);
        mass.put("I", 126.90);
        mass.put("Xe", 131.29); //End period 5
        mass.put("Cs", 131.91);
        mass.put("Ba", 137.33);
	        mass.put("La", 138.91); 
	        mass.put("Ce", 140.12);
	        mass.put("Pr", 140.91);
	        mass.put("Nd", 144.24);
	        mass.put("Pm", 145.0); ////Artificial  
	        mass.put("Sm", 150.36);
	        mass.put("Eu", 151.96);
	        mass.put("Gd", 157.25);
	        mass.put("Tb", 158.93);
	        mass.put("Dy", 162.50);
	        mass.put("Ho", 164.93);
	        mass.put("Er", 167.26);
	        mass.put("Tm", 168.93);
	        mass.put("Yb", 173.05);
	        mass.put("Lu", 174.97); //End lanthanides
        mass.put("Hf", 178.49);
        mass.put("Ta", 180.95);
        mass.put("W", 183.84);
        mass.put("Re", 186.21);
        mass.put("Os", 190.23);
        mass.put("Ir", 192.22);
        mass.put("Pt", 195.08);
        mass.put("Au", 196.97);
        mass.put("Hg", 200.59);
        mass.put("Tl", 204.38);
        mass.put("Pb", 207.2);
        mass.put("Bi", 208.98);
        mass.put("Po", 209.0); //End stable elements 
        mass.put("At", 210.0);
        mass.put("Rn", 222.0); //End period 6
        mass.put("Fr", 223.0);
        mass.put("Ra", 226.0);
        	mass.put("Ac", 227.0);
        	mass.put("Th", 232.04);
        	mass.put("Pa", 231.04);
        	mass.put("U", 238.03); //End naturally occurring elements
        	mass.put("Np", 237.0); 
        	mass.put("Pu", 244.0);
        	mass.put("Am", 243.0);
        	mass.put("Cm", 247.0);
        	mass.put("Bk", 247.0);
        	mass.put("Cf", 251.0);
        	mass.put("Es", 252.0);
        	mass.put("Fm", 257.0);
        	mass.put("Md", 258.0);
        	mass.put("No", 259.0);
        	mass.put("Lr", 262.0); //End Actinides 
    	mass.put("Rf", 267.0);
    	mass.put("Db", 268.0);
    	mass.put("Sg", 271.0);
    	mass.put("Bh", 272.0);
    	mass.put("Hs", 277.0);
    	mass.put("Mt", 276.0);
    	mass.put("Ds", 281.0);
    	mass.put("Rg", 280.0);
    	mass.put("Cn", 285.0);
    	//mass.put("Uut", ?); //Theoretical 
    	mass.put("Fl", 287.0);
    	//mass.put("Uup", ?); //Theoretical 
    	mass.put("Lv", 291.0);
    	//mass.put("Uus", ?); //Theoretical 
    	//mass.put("Uuo", ?); //Theoretical //End period 7
        amu = Collections.unmodifiableMap(mass);
	}
	
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

        		//Here is your new list Chuck///////////////////////////////////////////////////////////////////////////////////////////////////
        		//You can add items to the list with percentList.add(STRING);
        		//the list is automatically cleared every time another formula is entered. 
        		final List percentList = new List();
                percentList.setFont(new Font("Century Gothic", Font.PLAIN, 24));
                percentList.setBackground(Color.WHITE);
                percentList.setBounds(146, 193, 128, 262);
        		
        		//Handles the list which contains the kind and amount of atoms
        		final List list = new List();
                list.setFont(new Font("Century Gothic", Font.PLAIN, 24));
                list.setBackground(Color.WHITE);
                list.setBounds(12, 193, 128, 262);  
                ArrayList<String[]> splitElements = new ArrayList<>();
                
                //Uses the splitElement method to break the blocks of e.g. "C6" in arrays of e.g. {"C","6"}
        		for(int j=0; j<elements.size(); j++){
        			String[] split = splitElement(elements.get(j));
        			splitElements.add(split);	
        		}
        		
        		//Uses the explode method to create one array list which contains all the individual atoms in the inputed formula
        		//represented by their symbol. e.g. <{"H","2"},{"O","1"}> becomes <"H","H","O">
        		ArrayList<String> atoms = explode(splitElements);
        		double totalMass = 0.0;
        		
        		//The stage is set for generating output thanks to Ethan's general purpose explode method
        		for(int x = 0;  x< atoms.size(); x++){
        			String test = atoms.get(x);
        			if(test.equals("")){
        				//The index we're searching contained a duplicate of a previous element - we don't need to do anything
        				continue;
        			}
        			int number = 1; //There is at least 1 of this atom - each time we find another, we increase this
        			for(int j = x + 1; j < atoms.size(); j++){
        				String test2 = atoms.get(j);
        				if(test.equals(test2)){
        					number++;
        					atoms.set(j, "");//So this index is not analyzed when the outer for loop comes to it
        				}
        			}
        			//Adds the formatted output to the JList
        			list.add(test + ": " + number);
        			
        			if(amu.containsKey(test)){
        				double mass = amu.get(test) * number;
        				//TODO - Add code to generate % of each element by mass in compound, if desired
        				//This sounds like a good idea
        				totalMass += mass;
        			} else {
        				System.out.println("There is no such element as: " + test + ". Please check your input for errors.");
        			}
        		}
        		//Formats the output so it can be added to the JTextField display
        		String totalMassString = Double.toString(totalMass);
        		String amuOutput = totalMassString + "amu";
        		
        		//Adds the Lists to the screen
        		contentPane.add(list);
        		contentPane.add(percentList);
               
        		//Mass text field setup
                txtamu = new JTextField();
                txtamu.setText(amuOutput);
                txtamu.setBounds(333, 205, 116, 22);
                contentPane.add(txtamu);
                txtamu.setColumns(10);
                
                //Num of unique atoms setup
                textField = new JTextField();
                textField.setText("0");
                textField.setBounds(333, 265, 116, 22);
                contentPane.add(textField);
                textField.setColumns(10);
                
                //Num of atoms setup
                textField_1 = new JTextField();
                textField_1.setText("0");
                textField_1.setBounds(333, 325, 116, 22);
                contentPane.add(textField_1);
                textField_1.setColumns(10);
                contentPane.setVisible(true);
                
                //Resets the list the when main text field is clicked. For some reason it doesn't update if this isn't done 
                txtEnterChemicalFormula.addMouseListener(new MouseAdapter(){
                    @Override
                    public void mouseClicked(MouseEvent e){
                       txtEnterChemicalFormula.setText("");
                       contentPane.remove(list);
                       contentPane.remove(percentList);
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
        lblUniqueElements.setFont(new Font("Century Gothic", Font.PLAIN, 17));
        lblUniqueElements.setBounds(320, 240, 150, 30);
        contentPane.add(lblUniqueElements);
		
        JLabel lblAtomicMass = new JLabel("Atomic Mass");
        lblAtomicMass.setFont(new Font("Century Gothic", Font.PLAIN, 17));
        lblAtomicMass.setBounds(347, 179, 108, 30);
        contentPane.add(lblAtomicMass);
        
        JLabel lblNumberOfAtoms = new JLabel("Number of Atoms");
        lblNumberOfAtoms.setFont(new Font("Century Gothic", Font.PLAIN, 17));
        lblNumberOfAtoms.setBounds(310, 300, 160, 30);
        contentPane.add(lblNumberOfAtoms);
        
        JLabel lblAtomsAmount = new JLabel("Composition");
        lblAtomsAmount.setFont(new Font("Century Gothic", Font.PLAIN, 17));
        lblAtomsAmount.setBounds(12, 158, 104, 30);
        contentPane.add(lblAtomsAmount);
        
        JLabel lblByMass = new JLabel("% by Mass");
        lblByMass.setFont(new Font("Century Gothic", Font.PLAIN, 17));
        lblByMass.setBounds(146, 157, 92, 30);
        contentPane.add(lblByMass);
        
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
