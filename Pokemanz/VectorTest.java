/*  Might be useful to hold scores or points or something
 *  Vectors are expandable arrays (similar to arraylists)
 */

import java.util.*;

class VectorTest{
	public static Vector getRandomScores(){
		Random rand = new Random();
		
		//generate between 500 and 1000 random scores
		int numElements = 500 + Math.abs(rand.nextInt()) % 501;
		
		//create vector and fill with random scores
		Vector v = new Vector(numElements);
		
		while(numElements > 0){
			//add an integer between 0 and 2000
			v.add(new Integer(Math.abs(rand.nextInt() % 2001)));
			numElements--;
		}
		
		return v;
	}
	
	//finds greatest score within a vector of random scores
	public static void main(String[] args){
		int highestScore = 0;
		
		Vector scores = getRandomScores();
		
		//cycle through enumeration of higest scores and choose the highest
		//**An 'enumeration' represents a series of elements
		for (Enumeration e = scores.elements(); e.hasMoreElements();){
			Integer score = (Integer) (e.nextElement());
			
			if (score.intValue() > highestScore){
				highestScore = score.intValue();
			}
		}
		
		System.out.println(highestScore);
	}
}