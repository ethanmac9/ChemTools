import java.util.*;//TODO Integrate 2 letter elements and polyatomic ions
//Test equations (working): KO2+H2O=KOH+O2+H2O2 balanced is: 2KO2 + 2H2O = 2KOH + O2 + H2O2
//(not working): NH3+O2=NO+H2O

class ChemicalEquationBalancer {

//Useful general purpose method
public static boolean isParsable(String input){
    boolean parsable = true;
    try{
        Integer.parseInt(input);
    }catch(NumberFormatException e){
        parsable = false;
    }
    return parsable;
}

//Inserts understood 1's into the user's input for easier processing
public static String[] OnesInserted(String[] array){
	String[] out = new String[array.length];
	for(int i = 0; i < array.length; i++){
		String test = array[i];
		//If there's no number at the start we add a one
		if(!isParsable(test.substring(0,1))){
			test = "1" + test;
		}
		boolean onLet = false; //true if we just processed a letter
		for(int j = 1; j < test.length(); j++){
			String token = test.substring(j,j+1);
			if(isParsable(token)){
				onLet = false;
			} else if (Character.isUpperCase(test.charAt(j)) && onLet){
				//Upper case letter and onLet is true, we add a one before this place in the string.
				test = test.substring(0,j) + "1" + test.substring(j, test.length());
				onLet = false;
			}else if(Character.isUpperCase(test.charAt(j)) && j == test.length() - 1){
				//Last run and there's no number after this letter
				test = test + "1";
			} else {
				//It's another letter, whether upper or lower case
				onLet = true;
			}
		}
		out[i] = test;
	}
	return out;
}

//Removes understood 1's for pretty output
public static String OnesRemoved(String in){
   String num = "";
   String output = "";
   for(int k = 0; k < in.length(); k++){//Runs until a letter is found - then stops and lets the other logic take over
      String tester = in.substring(k, k + 1);
      if(isParsable(tester)){
         num += tester;
      } else {
         break;
      }
   }
   
   if(Integer.parseInt(num) != 1){
      output += num;
   }

   for(int i = num.length(); i < in.length(); i++){
      String test = in.substring(i, i + 1);
      if(isParsable(test)){
         if(Integer.parseInt(test) != 1){
            output += test;
         }
      } else {
         output += test;
      }
   }
   
   return output;
   
}


public static void main(String[] args){//Way too big- TODO methodize each section

	//One scanner handles int input, other handles String input
   Scanner input = new Scanner(System.in);
   Scanner inputInt = new Scanner(System.in);
   System.out.println("How many equations do you need analyzed?");
   int times = inputInt.nextInt();
   
   for(int i = 0; i < times; i ++){
   
      System.out.println("Enter your equation.");//Arrow is just an =, supscripts are just numbers, no spaces - for example, 2H2+O2=2H2O would be acceptable input
      String rawInput = input.nextLine();
      
      String[] sides = rawInput.split("="); //Only used here for ease
      String[] leftCompounds = sides[0].split("\\+"); //These are modified each run of the loop until it is balanced
      String[] rightCompounds = sides[1].split("\\+");
      
      //Insert understood 1's for processing
      leftCompounds = OnesInserted(leftCompounds);
      rightCompounds = OnesInserted(rightCompounds);
      
      boolean conservs = true;//This equation conserves matter
      while(conservs){
      
      ArrayList<String> leftSideAtoms = new ArrayList<String>();
      ArrayList<Integer> leftSideFreq = new ArrayList<Integer>(); 
      ArrayList<Integer> rightSideFreq = new ArrayList<Integer>();

      
      for(int x = 0; x < leftCompounds.length; x++){//Each compound is processed
         String compound = leftCompounds[x];
         String numCompound = "";//Build this string until it accurately shows how many molecules of compound there are, if there are more than 1
         for(int j = 0; j < compound.length(); j++){//Each letter in compound
         String test = compound.substring(j, j + 1);
            if(j + 1 == compound.length()){//Last run on this compound
               //Do nothing - we already processed it
            } else {//Middle run, we're good to search index 1 after
            
               if(isParsable(test)){
                  //Number, if it's at the start we have problems, in the middle, we already got it
                  if(j == 0){
                     numCompound = test;//Build this string until it accurately shows how many molecules of compound there are
                     for(int k = 1; k < compound.length(); k++){//Runs until a letter is found - then stops and lets the other logic take over TROUBLESHOOTING - this k++ was j++?
                        String tester = compound.substring(k, k + 1);
                        if(isParsable(tester)){
                           numCompound += tester;
                        } else {
                           break;
                        }
                     }
                  }
                                    
                  
               } else {
                  //Letter, we need to process
                 
                 if(leftSideAtoms.indexOf(test) == -1){
                     //Not in array yet, we need to add and create frequency thing 
                     leftSideAtoms.add(test);
                     String nextChar = compound.substring(j + 1, j + 2);
                     if(isParsable(nextChar)){
                        leftSideFreq.add(Integer.parseInt(nextChar) * Integer.parseInt(numCompound));
                     } else {
                        //TODO 2 character element support
                        
                     }
                 } else {
                     //Already in array, just modify frequency
                     int temp = leftSideFreq.get(leftSideAtoms.indexOf(test));
                     String nextChar = compound.substring(j + 1, j + 2);
                     if(isParsable(nextChar)){
                        temp += Integer.parseInt(nextChar) * Integer.parseInt(numCompound);
                        leftSideFreq.set(leftSideAtoms.indexOf(test), temp);
                     }else {
                        //TODO Add support, it has 2 letters
                     }
                 } 
                  
               }            
            }           
            
         }
      }
      

      for(int x = 0; x < leftSideFreq.size(); x++){//Fill with 0's so we don't get out of bounds errors because we can't access any given index here
         rightSideFreq.add(0);
      }
      
      for(int x = 0; x < rightCompounds.length; x++){//TODO Pretty much same as above - should be a method!
         String compound = rightCompounds[x];
         String numCompound = "";//Build this string until it accurately shows how many molecules of compound there are, if there are more than 1
         for(int j = 0; j < compound.length(); j++){//Each letter in compound
         String test = compound.substring(j, j + 1);
            if(j + 1 == compound.length()){//Last run on this compound
               //Do nothing - already processed
            } else {//Middle run, we're good to search index 1 after
            
               if(isParsable(test)){
               
                  //Number, if it's at the start we have problems, in the middle, we already got it
                  if(j == 0){
                     numCompound = test;//Build this string until it accurately shows how many molecules of compound there are
                     for(int k = 1; k < compound.length(); k++){//Runs until a letter is found - then stops and lets the other logic take over
                        String tester = compound.substring(k, k + 1);
                        if(isParsable(tester)){
                           numCompound += tester;
                        } else {
                           break;
                        }
                     }
                  }
                  
                  
               } else {
                  //Letter, we need to process
                 
                 if(leftSideAtoms.indexOf(test) == -1){
                     //Not in array yet, BREAKS CONSERVATION 
                     System.out.println("Congratulations - You just broke CONSERVATION OF MATTER!");//TODO test for all conservation denying cases
                     conservs = false;
                     break;
                 } else {
                     //Already in array, just modify frequency
                     int temp = rightSideFreq.get(leftSideAtoms.indexOf(test));
                     String nextChar = compound.substring(j + 1, j + 2);
                     if(isParsable(nextChar)){
                        temp += Integer.parseInt(nextChar) * Integer.parseInt(numCompound);
                        rightSideFreq.set(leftSideAtoms.indexOf(test), temp);
                     }else {
                        //TODO Add support, it has 2 letters
                     }
                 } 
                  
               }            
            }           
            
         }
      }
      

      ArrayList<String> missingFromL = new ArrayList<String>();
      ArrayList<String> missingFromR = new ArrayList<String>();
      
      boolean balanced = true;
      //Find out if there's a difference in the two sides
      for(int x = 0; x < leftSideFreq.size(); x++){
      
         int temp = leftSideFreq.get(x) - rightSideFreq.get(x);
         
         if(temp == 0){
            //Balanced, at least at this index
            continue;
         } else if (temp > 0) { //Right side is missing atoms
               String atom = leftSideAtoms.get(x);
               atom = atom + temp;
               missingFromR.add(atom);
               balanced = false;
         } else { //Left side is missing atoms
               String atom = leftSideAtoms.get(x);
               temp = -temp;
               atom = atom + temp;
               missingFromL.add(atom);
               balanced = false;
         }
      
      }
      
      if(balanced){
         System.out.println("This equation is balanced:");
         System.out.print(OnesRemoved(leftCompounds[0]));
         for(int x = 1; x < leftCompounds.length; x++){
            System.out.print(" + " + OnesRemoved(leftCompounds[x]));
         }
         System.out.print(" = " + OnesRemoved(rightCompounds[0]));
         for(int x = 1; x < rightCompounds.length; x++){
            System.out.print(" + " + OnesRemoved(rightCompounds[x]));
         }
         System.out.println();
         break; //End the while loop
      }

      
      //Equation is not balanced. Analyze and fix errors
      
      if(missingFromL.isEmpty()){
         if(missingFromR.isEmpty()){
            System.out.println("Something went wrong. Please check your input for errors.");
            break;
         } else {
            //So we know that only the right side is missing something
            boolean keepGoing = true;
            for(int x = 0; x < missingFromR.size(); x++){//TODO Picks first, not best, make it better
               for(int j = 0; j < rightCompounds.length; j++){
                  if(rightCompounds[j].contains(missingFromR.get(x))){//TODO Or a factor of the number that's missing, and then AHHHHHHHHHH COMPLEX
                     //This entry is the one we need! Or, at least a potential option. Do stuff with THE EXACT MATCH WOOHOO
                     
                     String temp = rightCompounds[j];

                        String num = temp.substring(0,1);

                           int number = Integer.parseInt(num);
                           number++; //TODO Find out what to do when have to have more than 1 to add
                           temp = number + temp.substring(1,temp.length());
                           rightCompounds[j] = temp;

                        //Got this difference resolved! I think?
                        keepGoing = false;
                        break; 
                              
                  }
               } 
               if(keepGoing == false){
                  break;
               }
            }
            
            if(keepGoing){
               //No exact matches
            	//TODO The algorithm
               
            }
            
         }
      } else {
         //We know left side is missing something, don't know about the right side
         boolean keepGoing = true;
         for(int x = 0; x < missingFromL.size(); x++){//TODO Picks first, not best, make it better
               for(int j = 0; j < leftCompounds.length; j++){
                  if(leftCompounds[j].contains(missingFromL.get(x))){//TODO Or a factor of the number that's missing, and then AHHHHHHHHHH COMPLEX
                     //This entry is the one we need! Or, at least a potential option. Do stuff with it
                     //Change the number before this compound
                     String temp = leftCompounds[j];
                     String num = temp.substring(0,1);//TODO Doesn't work with two digit nums or more
                     int number = Integer.parseInt(num);
                        number++; //TODO Find out what to do when its a factor, not an an exact match
                        temp = number + temp.substring(1,temp.length());//TODO Assumes only single digit
                        leftCompounds[j] = temp;

                     //Got this difference resolved! I think?
                     keepGoing = false;
                     break;                                     
                  }
               } 
               if(keepGoing == false){
                  break;
               }
            }
            
            if(keepGoing){
            //Nothing works on the left. Try the right.
            for(int x = 0; x < missingFromR.size(); x++){//TODO Picks first, not best, make it better
               for(int j = 0; j < rightCompounds.length; j++){
                  if(rightCompounds[j].contains(missingFromR.get(x))){//TODO Or a factor of the number that's missing, and then AHHHHHHHHHH COMPLEX
                     //This entry is the one we need! Or, at least a potential option. Do stuff with it
                     //Change the number before this compound
                     String temp = rightCompounds[j];
                     String num = temp.substring(0,1);
                     int number = Integer.parseInt(num);
                     number++; //TODO Find out what to do when have to have more than 1 to add
                     temp = number + temp.substring(1,temp.length());
                     rightCompounds[j] = temp;
                                      
                     //Got this difference resolved! I think?
                     keepGoing = false;
                     break;                                     
                  }
               } 
               if(keepGoing == false){
                  break;
               }
            }
            
            if(keepGoing){
               //No exact matches
            	//TODO Add algorithm
            }
            
         }
         
         }
         
         
         }//End while loop

      }
         
 }
        
 }