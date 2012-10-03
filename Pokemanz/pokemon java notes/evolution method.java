class <some-Pokemon> extends Pokemon{
  ...
  static <evolved-form> evolve (Pokemon p){
    System.out.println(p.name + " is evolving!"); //optional
	return new <evolved-form>(p.<stat>, p.<stat>, p.<stat>...//stats to transfer; <evolved-form> must have a constructor that takes these as arguments and assigns them
  }
}