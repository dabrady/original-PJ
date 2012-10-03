package game.pokemanz;
import java.io.*;  // for executing application
import java.util.*;
import static java.nio.file.StandardCopyOption.*; //for using Files.copy() method

class createClass{
	//get name of new class and store as string
	//write to new file containing import statements and class structure
	static int create(int i){
		Path p1 = Paths.get("/Pokemon.java"); //creates path object specifying location of pokemon.java file
		Path p2 = Paths.get("/Species.java");
		String filename = p2.getFileName();
		try{  	//try to copy the Pokemon.java file to new file called Species.java
			Files.copy(p1, p2);
		}catch (FileAlreadyExistsException e){  //if Species.java already exists, create a new path and copy Pokemon.java to new file Species(i).java
			String newFileName = "/Species(%d).java", i;
			p2 = Paths.get(newFileName);
			filename = p2.getFileName();
			Files.copy(p1,p2);
			i++;
		}finally return i;  //i represents a counter that records how many times a new Species.java file has been created
		
		//use to compile new Species.java file
		try{
			Process process = new ProcessBuilder("cmd.exe /c javac %s", filename).start();
			InputStream is = process.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);     
		}catch(Exception exc){System.out.println("Compile block failed.");}
	}
}