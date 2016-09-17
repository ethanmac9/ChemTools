//A.P. Chem. Chemical Formula Parsing Tools V0.3
//Code by Ethan MacDonald and Chuck8521
//Currently the main functionality, the atomic mass display, and the number of atoms display works, and the 
//additional displays are yet to be implemented

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
import java.text.DecimalFormat;

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
        		
        		//Parses the formula into blocks of element and number. Single atoms are given a 1
        		//Scanner sc = new Scanner(System.in); Possible resource leak - I don't think this is used
        		ArrayList<String> elements = new ArrayList<>();
        		String formula = inputString;
        		String s = "";
        		
        		//Completely fathomable-ish witchcraft 
        		for (int i=0; i<formula.length(); i++) {
        			if(formula.charAt(i) == ')'){
        				//Random parenthesis, possibly a typo. Just ignore it
        				continue;
        			}
        		    if (Character.isUpperCase(formula.charAt(i))) {
        		        if (!s.isEmpty() && isParsable(Character.toString(s.charAt(s.length() - 1)))) { 
        		            elements.add(s);
        		        } else if (!s.isEmpty()){
        		        	elements.add(s + "1");
        		        }
        		        s = "" + formula.charAt(i);
        		    } else if(formula.charAt(i) == '('){
        		    	
        		    	//Check for the user being an idiot (or a developer)
        		    	if(formula.charAt(i + 1) == ')'){
        		    		//() was entered, just skip it.
        		    		i += 1;
        		    	}
        		    	
        		    	//We have a polyatomic ion. First, finalize the previous element
        		    	if (!s.isEmpty() && isParsable(Character.toString(s.charAt(s.length() - 1)))) { 
        		            elements.add(s);
        		        } else if (!s.isEmpty()){
        		        	elements.add(s + "1");
        		        }
        		        s = "";
        		    	
        		        //Now, start analyzing the polyatomic part
        		    	String ion = "";
        		    	String ionNum = "";
        		    	boolean postIon = false;
        		    	for(int x = i + 1; x < formula.length(); x++){
        		    		if(formula.charAt(x) == ')'){
        		    			//The ion has ended. Check if there's a number after it.
        		    			postIon = true;
        		    		} else if(postIon){
        		    			if(isParsable(String.valueOf(formula.charAt(x)))){
        		    				//We have a number. Add it.
        		    				ionNum += formula.charAt(x);
        		    			} else {
        		    				//The ion has been completely parsed. The loop is over.
        		    				break;
        		    			}
        		    		} else {
        		    			ion += formula.charAt(x);
        		    		}
        		    	}
        		    	
        		    	if(ionNum.equals("")){
        		    		//There is only one ion.
        		    		ionNum = "1";
        		    	}

        		    	
        		    	//We have the information we need. Distribute the ionNum over all nums and then pass values to the array
        		    	String toBeAdded = "";
        		    	String tempNumber = "";
        		    	for(int x = 0; x < ion.length(); x++){
        		    		if(ion.charAt(x) == '('){
        		    			//Ha ha. Nested parenthesis. Very funny, yet chemically incorrect. Let's ignore this
        		    			continue;        		    			
        		    		}
        		    		if(Character.isUpperCase(ion.charAt(x))){
        		    			if (!toBeAdded.isEmpty() && !tempNumber.equals("")) { 
        		    				//Has a number after it. Multiply whatever it is by the ionNum
        		    				int actualNumber = Integer.parseInt(tempNumber) * Integer.parseInt(ionNum);
                		            elements.add(toBeAdded + actualNumber);
                		        } else if (!toBeAdded.isEmpty()){
                		        	//No number after it, so ionNum * 1 still equals ionNum
                		        	elements.add(toBeAdded + ionNum);
                		        }
                		        toBeAdded = "" + ion.charAt(x);
                		        tempNumber = "";
        		    		} else if (isParsable(String.valueOf(ion.charAt(x)))){
        		    			//There is a number after the atom. Keep it so it can be multiplied by the ionNum
        		    			tempNumber += ion.charAt(x);
        		    		} else {
                		        toBeAdded += ion.charAt(x);
                		    }
        		    	}
        		    	
        		    	//Last case will not trigger inside the loop
        		    	if (!toBeAdded.isEmpty() && !tempNumber.equals("")) { 
		    				//Has a number after it. Multiply whatever it is by the ionNum
		    				int actualNumber = Integer.parseInt(tempNumber) * Integer.parseInt(ionNum);
        		            elements.add(toBeAdded + actualNumber);
        		        } else if (!toBeAdded.isEmpty()){
        		        	//No number after it, so ionNum * 1 still equals ionNum
        		        	elements.add(toBeAdded + ionNum);
        		        }
        		    	
        		    	//Modifies the iterator so it doesn't look at any part of the polyatomic ion again by mistake
        		    	i = i + ion.length() + ionNum.length() + 1;
        		    	
        		    } else {
        		        s += formula.charAt(i);
        		    }
        		}
        		
        		//Add last case (if it exists) because it is not processed during loop
        		if(!s.equals("")){
        			if(isParsable(Character.toString(s.charAt(s.length() - 1)))){
            			elements.add(s); 
            		} else {
            			elements.add(s + "1");
            		} 
        		}  

        		//New list for percent mass output
        		final List percentList = new List();
                percentList.setFont(new Font("Century Gothic", Font.PLAIN, 24));
                percentList.setBackground(Color.WHITE);
                percentList.setBounds(146, 193, 128, 262);
        		
        		//Handles the list which contains the kind and amount of atoms
        		final List list = new List();
                list.setFont(new Font("Century Gothic", Font.PLAIN, 24));
                list.setBackground(Color.WHITE);
                list.setBounds(12, 193, 128, 262);  
                
                //Create for later
        		double totalMass = 0.0;        		
        		ArrayList<Double> relativeMass = new ArrayList<Double>();   
        		//For the total number atoms display box
    			int totalNumAtoms = 0;
        		
        		//We have an array of element characters followed by number
        		for(int x = 0;  x< elements.size(); x++){
        			String test = elements.get(x);
        			
        			//This used to be a duplicate, and we already analyzed it
        			if(test.equals("")){
        				elements.remove(x);
        				x--;
        				continue;
        			}
        			
        			//Atom is the one or two chars that represent element. Num is the subscript number
        			String atom = "";
        			String num = "";
        			
        			boolean switched = false;
        			for(int j = 0; j < test.length(); j++){
        				String letter = Character.toString(test.charAt(j));
        				if(switched){
        					num += letter;
        				} else {
        					if(isParsable(letter)){
        						switched = true;
        						num = letter;
        					} else {
        						atom += letter;
        					}
        				}
        			}
        			
        			int number = Integer.parseInt(num);
        			for(int j = x + 1; j < elements.size(); j++){
        				String test2 = elements.get(j);
        				if(test2.contains(atom)){
        					//This is a duplicate.
        					test2 = test2.replace(atom, "");

        					//Get the number and work with it - we know it has a number because we added understood 1's
    						int secondNum = Integer.parseInt(test2);
    						number += secondNum;
        					
        					elements.set(j, "");//So this index is not analyzed when the outer for loop comes to it        					
        					
        				}
        			}
        			
        			//Adds the number of this atom to the number of total atoms for the display box
        			totalNumAtoms += number;
        			
        			if(amu.containsKey(atom)){
        				double mass = amu.get(atom) * number;
        				relativeMass.add(mass);
        				totalMass += mass;
        				//Adds the formatted output to the JList
            			list.add(atom + ": " + number);
        			} else {
        				System.out.println("There is no such element as: " + atom + ". Please check your input for errors.");
        			}
        			
        			//Sets the element entry equal to atom so no superfluous numbers appear in the % mass box
        			elements.set(x, atom);
        			
        		}
        		
        		
        		
        		//Gets the relative masses - we need to do this after the total mass is calculated
        		for(int x = 0; x < relativeMass.size(); x++){
        			double massPercentage = (relativeMass.get(x) / totalMass) * 100;
        			//Rounds to the nearest thousandth place
        			DecimalFormat df = new DecimalFormat("##.###");
        			String massPercentageRounded = df.format(massPercentage);
        			String atom = elements.get(x);
        			percentList.add(atom + ": " + massPercentageRounded + "%");
        		}
        		
        		//Formats the output so it can be added to the JTextField display
        		//Rounds to the nearest thousandth place - TODO possibly make this a method? Only two lines but still repeated
    			DecimalFormat df = new DecimalFormat("##.###");
    			String totalMassString = df.format(totalMass);
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
                textField.setText(String.valueOf(elements.size()));
                textField.setBounds(333, 265, 116, 22);
                contentPane.add(textField);
                textField.setColumns(10);
                
                //Num of atoms setup
                textField_1 = new JTextField();
                textField_1.setText(String.valueOf(totalNumAtoms));
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
	
	public static boolean isParsable(String input){
	    boolean parsable = true;
	    try{
	        Integer.parseInt(input);
	    }catch(NumberFormatException e){
	        parsable = false;
	    }
	    return parsable;
	}
	
}
