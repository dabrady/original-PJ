import java.util.*;
import java.lang.reflect.*;

public class Experiment{
	public static void main(String[] args) throws ClassNotFoundException{
		Class cls = Class.forName(args[0]);
		Move x = createNew1(cls);
		/*Move*/ Object y = createNew2(cls); // <---- Why must this be assigned to type Object, when all the evidence suggests that it is return an instance of type Move?
		// Move z = cls.cast(y);
		System.out.println(cls);
		// Move z = createNew3(cls);
		System.out.println(x);
		System.out.println(y);
		// System.out.println(z);
		System.out.println("You can't tell from this that one of these is not a Move, but an Object masquerading as a Move...");
	}
	
	//Passing and using an array of the type I wish to return is the only way I've figured out how to return something other than a generic Object.
	private static Move createNew1(Class cls){
		try{
			return cls.newInstance();
		}catch(Throwable t){}
		return null;
	}

	private static <T> T createNew2(Class<T> cls){
		T a = null;
		try{
			a = cls.cast(cls.newInstance());
		}catch(Throwable t){}
		System.out.println("\nClass name of a: " + a.getClass().getName());
		System.out.println("Simple class name of a (as it appears in source code): " + a.getClass().getSimpleName());
		System.out.println("Is a instanceof class Move? : " + (a instanceof Move));
		System.out.println("a isInstance of class Move? : " + cls.isInstance(a));
		System.out.println("Is a an assignable form of class Move? : " + a.getClass().isAssignableFrom(cls));
		System.out.println("\nThen why the hell does this method return an Object and not a Move?!");
		System.out.println("I even specify the return-type of this method to be the same as the class that is passed to it!");
		System.out.println("And the element being returned is declared as being of type T, which is the type of class being passed!");
		System.out.println("Grrrrr...this is so frustrating. I need a method that can create and return an object of a preexisting class specified at run-time.\n");
		return a;
	}
	
	//In progress:
	
	// private static <T> Move createNew3(Class<T> cls){
		// Object a = null;
		// try{
			// Class<?>[] partypes = new Class<?>[0];
			// Constructor ctor = cls.getConstructor(partypes);
			// Object[] arglist = new Object[0];
			// a = ctor.newInstance(arglist);
		// }catch(Throwable t){}
		// return cls.cast(a);  // <---Why doesn't this cast a to Tackle?
	// }
}