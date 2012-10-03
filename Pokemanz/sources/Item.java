// package pokemanz;
import java.util.*;


public abstract class Item{
/*
 * This class models an item, an object in the game that players can pick up, keep in their bag,
 * and use in some manner. They have various uses, including healing, powering up, helping one
 * to catch Pokemon, or to access a new area.
 */
	protected String name;
	protected String type;
	protected String description;
	protected int price;
	//Sprite
	public boolean incrCritHit = false;  //if an item increases the crit.hit ratio of the holder, set to true
	
	public Item(){
		
	}
	
	public void applyEffects(Pokemon p){ //ex: item.applyEffects(Bulbasaur);
		//do something
	}
	
	//test it
	public static void main(String[] args){
		Item badge = new BoulderBadge();
		System.out.println("Hello, I'm a " + badge.name + "!");
		System.out.print("I am a type of " + badge.getClass().getSuperclass().getName() + ". ");
		System.out.println(badge.description + "\n");
		
		Item cardkey = new CardKey();
		System.out.println("Hello, I'm a " + cardkey.name + "!");
		System.out.print("I am a type of " + cardkey.getClass().getSuperclass().getName() + ". ");
		System.out.println(cardkey.description + "\n");
		
		Item statbooster = new HP_Up();
		System.out.println("Hello, I'm a " + statbooster.name + "!");
		System.out.print("I am a type of " + statbooster.getClass().getSuperclass().getName() + ". ");
		System.out.println(statbooster.description + "\n");
		
		Item battleEnhancer = new XAttack();
		System.out.println("Hello, I'm a " + battleEnhancer.name + "!");
		System.out.print("I am a type of " + battleEnhancer.getClass().getSuperclass().getName() + ". ");
		System.out.println(battleEnhancer.description);
	}
}

/***********************************************************************************************************************************************************************************************/
/**********************************************************************************************************************************************************************************************/
/**********************************************************************************************************************************************************************************************/

abstract class Badge extends Item{
/*
 * This class models a badge, an item which denotes a Pokemon Trainer as having defeated a Gym Leader.
 * Trainers need to collect a certain number of Gym Badges in order to qualify for a region's Pokémon League.
 * Badge effects (may) include: stat boost, Pokemon obedience, HMS, PokeMart stock, and entrance into the Pokemon League.
 * Badges have their own section in a trainer's bag (or, they are displayed on the Trainer's Card).
 */
}
	
	class BoulderBadge extends Badge{
	/*
	 * This class models a Boulder Badge, a badge that increases the attack stats of a trainer's Pokemon by 9/8 (1.125 points). ***Make it by 1 point
	 * It allows you to use the HM 'Flash'.
	 */
		BoulderBadge(){
			this.name = "Boulder Badge";
			this.description = "A badge that increases the attack stats of a trainer's Pokemon and allows them to use the HM 'Flash'.";
		}
	}
	
	class CascadeBadge extends Badge{
	/*
	 * This class models a Cascade Badge, a badge that allows you to command Pokemon up to level 30.
	 * It allows you to use the HM 'Cut'.
	 */
	}

	class ThunderBadge extends Badge{
	/*
	 * This class models a Thunder Badge, a badge that increases the speed stats of a trainer's Pokemon by 9/8 (1.125 points). ***Make it by 1 point
	 * It allows you to use the HM 'Fly'.
	 */
	}
	
	class RainbowBadge extends Badge{
	/*
	 * This class models a Rainbow Badge, a badge that allows you to command Pokemon up to level 50.
	 * It allows yoou to use the HM 'Strength'.
	 */
	}
	
	class SoulBadge extends Badge{
	/*
	 * This class models a Soul Badge, a badge that increases the defense stats of a trainer's Pokemon by 9/8 (1.125 points). ***Make it by 1 point
	 * It allows you to use the HM 'Surf'.
	 */
	}
	
	class MarshBadge extends Badge{
	/*
	 * This class models a Marsh Badge, a badge that allows you to command Pokemon up to level 70.
	 * It allows you to use the HM 'Rock Smash'.
	 */
	}
	
	class VolcanoBadge extends Badge{
	/*
	 * This class models a Volcano Badge, a badge that allows you to use the HM 'Waterfall'.
	 */
	}
	
	class EarthBadge extends Badge{
	/*
	 * This class models an Earth Badge, a badge that allows you to command Pokemon up to level 100.
	 * It allows you to use the HM 'Rock Climb'.
	 */
	}	
	
/**********************************************************************************************************************************************************************************************/

abstract class KeyItem extends Item{
/*
 * This class models a Key Item, a special item that players can only obtain once, and either aid the progression of the storyline or allow access to new areas.
 * These items cannot be bought, sold, disposed of, or removed from the trainer's bag in anyway.
 * Key items have their own section in a trainer's bag.
 */
}

	class TownMap extends KeyItem{
	/*
	 * This class models a Town Map, a very convenient map that can be viewed anytime. It even shows your present location. 
	 */
	}
	
	class RunningShoes extends KeyItem{
	/*
	 * This class models Running Shoes, which are a must-have for any Trainer wanting to go anywhere quikcly.
	 * By pressing a specified button, players are able to run instead of walk.
	 */
	}
	
	class Bicycle extends KeyItem{
	/*
	 * This class models a Bicyle, which, similar to Running Shoes, is an item that enables players to travel faster than walking.
	 */
	}
	
	class Rod extends KeyItem{
	/*
	 * This class models a Rod, an item that enables players to fish for Pokémon in lakes or the sea. 
	 */
	}
	
	class Key extends KeyItem{
	/*
	 * This class models a standard Key, an item that enable players to open doors and access new areas.
	 */
	}
		
		class CardKey extends Key{
		/*
		 * This class models a Card Key, an item that enable players to open doors and access new areas.
		 */
			CardKey(){
				this.name = "Card Key";
				this.description = "I open doors and allow you to access new areas!";
			}
		}
		
	class Tea extends KeyItem{
	/*
	 * This class models Tea, a drink-like item that cannot be drunk, only given to a guard to be granted access to an area.
	 */		
	}

/**********************************************************************************************************************************************************************************************/

abstract class Tool extends Item{
/*
 * This class models a Tool item, which is extended by all general items found in the game: Pokeballs, Escape ropes,
 * Repels, etc.
 * Tools have their own section within a trainer's bag.
 */
}

	class Ball extends Tool{
	/*
	 * This class models a Ball item, a mechanism in which a captured Pokémon is kept. 
	 * Poké Balls come in many varieties, each of different strength.
	 */
	}
	
	class EscapeRope extends Tool{
	/*
	 * This class models an Escape Rope, an item used to return to the entrance of a cave or dungeon.
	 */
	}
	
	abstract class XP_Affecting extends Tool{
	/*
	 * This class models an experience-affecting item, which affect the amount of XP that a Pokemon gains in battle.
	 * Can be held by a Pokemon.
	 */
	}
	
		class XP_Share extends XP_Affecting{
		/*
		 * This class models an XP Share item, whose holder gets a share of XP without having to battle.
		 */
		}
		
		class LuckyEgg extends XP_Affecting{
		/*
		 * This class models a Lucky Egg, an item that increases the experience gained by the holder.
		 * DESCRIPTION: An item to be held by a Pokémon. An egg filled with happiness that earns extra EXP. points in battle. 
		 */
		}
// THINK ABOUT CONSOLIDATING THE ESCAPE ITEMS, SINCE THEY ALL DO THE SAME THING, JUST HAVE DIFFERENT NAMES
	abstract class Escape extends Tool{
	/*
	 * This class models an Escape item, an item that can be used to distract a wild Pokémon, allowing the player to escape
	 * instantly regardless of any factors that would otherwise prevent escape.
	 */
	}
		
		class PokeDoll extends Escape{
		/*
		 * This class models an attractive item. Use it to flee from any battle with a wild Pokémon.
		 */
		}
		
		class FluffyTail extends Escape{
		/*
		 * This class models an attractive item. Use it to flee from any battle with a wild Pokémon.
		 */		
		}
		
		class PokeToy extends Escape{
		/*
		 * This class models an attractive item. Use it to flee from any battle with a wild Pokémon.
		 */		
		}
		
	abstract class Repel extends Tool{
	/*
	 * This class models a Repel item, an item which lets the player walk a certain number of steps without encountering wild Pokémon with a lower level
	 * than the first member in the player's party.
	 */
	}
	
		class StdRepel extends Repel{
		/*
		 * This class models a standard Repel item, which prevents weak wild Pokémon from appearing for 100 steps after its use. 
		 */
		}
		
		class SuperRepel extends Repel{
		/*
		 * This class models a Super Repel item, which prevents weak wild Pokémon from appearing for 200 steps after its use. 
		 */		
		}
		
		class MaxRepel extends Repel{
		/*
		 * This class models a Max Repel item, which prevents weak wild Pokémon from appearing for 250 steps after its use. 
		 */		
		}
		
		class CleanseTag extends Repel{
		/*
		 * This class models a Cleanse Tag. Though not exactly a Repel, this item lowers the encounter rate of wild Pokémon with a lower level than the holder,
		 * if the holder is first member in the player's party.
		 * Can be held by a Pokemon.
		 */
		}
		
	class Honey extends Tool{
	/*
	 * This class models Honey, an item to be used on special, golden-colored trees in Sinnoh to attract Pokémon—most of which can only be caught in the wild by use of these trees.
     * When activated in tall grass away from trees, Honey acts in a manner similar to the move Sweet Scent. 
	 */
	}
	
	abstract class TM extends Tool{
	/*
	 * This class models a Technical Machine, a machine used by Pokémon Trainers to teach a Pokémon a new move that it might not otherwise learn.
	 * These machines are single-use items.
	 */
	}
	//**********ADD TMs
	
	abstract class HM extends Tool{
	/*
	 * This class models a Hidden Machine, a machine similar to a Technical Machine that is used to teach a Pokémon a new move that it might not otherwise learn.
	 * However, HMs can be used an unlimited amount of times after being obtained.
	 * They can also not be 'forgotten' (under normal circumstances) by any Pokemon they are used on.
	 * They also can potentially be used outside of battles, provided you have the right badge(s).
	 */	
	}
	//**********ADD HMs
	
	class Valuable extends Tool{
	/*
	 * This class models a Valuable item, whose sole purpose is to be traded or sold for many dollars.
	 */
	}

/**********************************************************************************************************************************************************************************************/

abstract class Medicine extends Item{
/*
 * This class models a Medicine item. It is extended by all recovery and stat-affecting items found in the game: food, drinks, ethers,
 * herbal medicine, Evolutionary Stones, Potions, Revives, Stat Boosters, etc.
 * Medicine has it's own section within a trainer's bag.
 */
}

	abstract class StatBooster extends Medicine{
	/*
	 * This class models a Stat-boosting item and is extended by all items in the game that either boost stats for the
	 * long-term (Vitamins) or just in a battle (Battle items).
	 * Can be held by Pokemon.
	 * Are shown in the MEDICINE pocket of a trainer's bag.
	 */
	}

		abstract class Vitamin extends StatBooster{
		/*
		 * This class models a Vitamin.
		 * Vitamins give a Pokémon 10 EV points to a stat, only working if the EVs of the stat raised are less than 100, and if the total EVs are less than 510. 
		 * Vitamins also increase the Happiness of the affected Pokémon, if we decide to give them Happiness.
		 */
		}
			
			class HP_Up extends Vitamin{
			/*
			 * This class models an HP Up, a nutritious drink for Pokémon. It raises the base HP of a single Pokémon.
			 * EFFECT:
			 *    Adds 10 HP EVs to the target Pokémon per use, until it has 100 HP EVs.
			 *    Adds 2560 HP Stat Exp to the target Pokémon per use, until it has 25600 HP Stat Exp.
			 */
				HP_Up(){
					this.name = "HP Up";
					this.description = "A nutritious drink for Pokemon, I raise the base HP of a single Pokemon.";
				}
			}
			
			class Protein extends Vitamin{
			/*
			 * This class models a Protein, a nutritious drink for Pokémon. It raises the base Attack stat of one Pokémon. 
			 * EFFECT:
			 *    Adds 10 Attack EVs to the target Pokémon per use, until it has 100 Attack EVs.
			 *    Adds 2560 Attack Stat Exp to the target Pokémon per use, until it has 25600 Attack Stat Exp.
			 */
			}
			
			class Iron extends Vitamin{
			/*
			 * This class models an Iron, a nutritious drink for Pokémon. It raises the base Defense stat of one Pokémon. 
			 * EFFECT:
			 *    Adds 10 Defense EVs to the target Pokémon per use, until it has 100 Defense EVs.
			 *    Adds 2560 Defense Stat Exp to the target Pokémon per use, until it has 25600 Defense Stat Exp.
			 */
			}
			
			class Carbos extends Vitamin{
			/*
			 * This class models a Carbos, a nutritious drink for Pokémon. It raises the base Speed stat of one Pokémon.
			 * EFFECT:
			 *    Adds 10 Speed EVs to the target Pokémon per use, until it has 100 Speed EVs.
			 *    Adds 2560 Speed Stat Exp to the target Pokémon per use, until it has 25600 Speed Stat Exp.
			 */
			}
			
			class RareCandy extends Vitamin{
			/*
			 * This class models a Rare Candy. Rare Candies level up a Pokemon.
			 */	
			}
		
		abstract class PP_Raiser extends StatBooster{
		/*
		 * This class models a PP-raiser.
		 * PP-raisers increase the PP value of one of the Pokemon's moves. There are two types: PP Up and PP Max.
		 */
		}
		
			class PP_Up extends PP_Raiser{
			/*
			 * This class models a PP Up. PP UPs boost the move's PP by 20% of the original value, and up to three can be used on any one move
			 * for a total boost of 60% the original value.
			 */
			}
			
			class PP_Max extends PP_Raiser{
			/*
			 * This class models a PP Max item. PP Maxes boost the move's PP by 60% of the original value, and can only be use once (per move), counting as
			 * three PP Ups.
			 * If a PP Max is used on a move that has already been boosted by a PP Up, it will boost the PP to whatever the maximum would be,
			 * serving as two PP Ups if one has already been used, and as one if two have been. 
			 */
			}
		
		abstract class BattleEnhancer extends StatBooster{
		/*
		 * This class models a Battle item, which is given to a Pokemon during battle to enhance its stats. The stat rise is only temporary.
		 * These items cannot be used on a Pokémon if the specific stat is already raised by six levels. (meh...)
		 */	
		}
			
			class XAttack extends BattleEnhancer{
			/*
			 * This class models an X Attack item, which raises the Attack stat of a Pokémon in battle by 1 level. It wears off if the Pokémon is withdrawn. 
			 */
				XAttack(){
					this.name = "XAttack";
					this.description = "An item which raises the Attack stat of a Pokemon in battle by 1 level. It wears off if the Pokemon is withdrawn.";
				}
			}
			
			class XDefend extends BattleEnhancer{
			/*
			 * This class models an X Defend item, which raises the Defense stat of a Pokémon in battle by 1 level. It wears off if the Pokémon is withdrawn. 
			 */			
			}
			
			class XSpeed extends BattleEnhancer{
			/*
			 * This class models an X Speed item, which raises the Speed stat of a Pokémon in battle by 1 level. It wears off if the Pokémon is withdrawn. 
			 */			
			}
			
			class XAccuracy extends BattleEnhancer{
			/*
			 * This class models an X Accuracy item, which raises the Accuracy stat of a Pokémon in battle by 1 level. It wears off if the Pokémon is withdrawn. 
			 */			
			}
			
			class DireHit extends BattleEnhancer{
			/*
			 * This class models a Dire Hit item, which raises the critical-hit ratio of a Pokémon in battle by 1 level. It wears off if the Pokémon is withdrawn. 
			 */			
			}
			
			class EffectGuard extends BattleEnhancer{
			/*
			 * This class models an Effect Guard, which prevents stat reduction among the Trainer's party Pokémon for five turns after use.
			 */			
			}
			
			abstract class TypeEnhancer extends BattleEnhancer{
			/*
			 * This class models a Type Enhancer item, which when held, enhances the power of attacks with the corresponding type by 10%.
			 * To be held by Pokemon.
			 */
			}
			
				class BlackBelt extends TypeEnhancer{
				/*
				 * Boosts the power of Fighting-type moves by 10% when held. 
				 */
					BlackBelt(){
						this.name = "Black Belt";
						this.description = "A belt that boosts determination and Fighting-type moves.";
					}
				}
				
				class BlackGlasses extends TypeEnhancer{
				/*
				 * Boosts the power of Dark-type moves by 10% when held. 
				 */
					BlackGlasses(){
						this.name = "Black Glasses";
						this.description = "A shady-looking pair of glasses that boosts Dark-type moves.";
					}
				}
				
				class Charcoal extends TypeEnhancer{
				/*
				 * Boosts the power of Fire-type moves by 10% when held. 
				 */
					Charcoal(){
						this.name = "Charcoal";
						this.description = "A combustible fuel that boosts the power of Fire-type moves.";
					}
				}
				
				class DragonFang extends TypeEnhancer{
				/*
				 * Boosts the power of Dragon-type moves by 10% when held. 
				 */
					DragonFang(){
						this.name = "Dragon Fang";
						this.description = "A hard and sharp fang that boosts the power of Dragon-type moves.";
					}
				}
				
				class HardStone extends TypeEnhancer{
				/*
				 * Boosts the power of Rock-type moves by 10% when held. 
				 */
					HardStone(){
						this.name = "Hard Stone";
						this.description = "An unbreakable stone that boosts the power of Rock-type moves.";
					}
				}
				
				class Magnet extends TypeEnhancer{
				/*
				 * Boosts the power of Electric-type moves by 10% when held. 
				 */			
					Magnet(){
						this.name = "Magnet";
						this.description = "A powerful magnet that boosts the power of Electric-type moves.";
					}
				}
				
				class MetalCoat extends TypeEnhancer{
				/*
				 * Boosts the power of Steel-type moves by 10% when held. 
				 */				
					MetalCoat(){
						this.name = "Metal Coat";
						this.description = "A special metallic film that boosts the power of Steel-type moves.";
					}
				}
				
				class MiracleSeed extends TypeEnhancer{
				/*
				 * Boosts the power of Grass-type moves by 10% when held. 
				 */				
					MiracleSeed(){
						this.name = "Miracle Seed";
						this.description = "A seed imbued with life that boosts the power of Grass-type moves.";
					}
				}
				
				class MysticWater extends TypeEnhancer{
				/*
				 * Boosts the power of Water-type moves by 10% when held. 
				 */				
					MysticWater(){
						this.name = "Mystic Water";
						this.description = "A teardrop-shaped gem that boosts the power of Water-type moves.";
					}
				}
				
				class NeverMeltIce extends TypeEnhancer{
				/*
				 * Boosts the power of Ice-type moves by 10% when held. 
				 */				
					NeverMeltIce(){
						this.name = "NeverMeltIce";
						this.description = "A piece of ice that repels heat and boosts Ice-type moves.";					
					}
				}
							
				class PoisonBarb extends TypeEnhancer{
				/*
				 * Boosts the power of Poison-type moves by 10% when held. 
				 */				
					PoisonBarb(){
						this.name = "Poison Barb";
						this.description = "A small, poisonous barb that boosts the power of Poison-type moves.";					
					}
				}
				
				class SharpBeak extends TypeEnhancer{
				/*
				 * Boosts the power of Flying-type moves by 10% when held. 
				 */				
					SharpBeak(){
						this.name = "Sharp Beak";
						this.description = "A long, sharp beak that boosts the power of Flying-type moves.";
					}
				}
				
				class SilkScarf extends TypeEnhancer{
				/*
				 * Boosts the power of Normal-type moves by 10% when held. 
				 */				
					SilkScarf(){
						this.name = "Silk Scarf";
						this.description = "A sumptuous scarf that boosts the power of Normal-type moves.";
					}
				}
				
				class SilverPowder extends TypeEnhancer{
				/*
				 * Boosts the power of Bug-type moves by 10% when held. 
				 */				
					SilverPowder(){
						this.name = "Silver Powder";
						this.description = "A shiny silver powder that boosts the power of Bug-type moves.";			
					}
				}
				
				class SoftSand extends TypeEnhancer{
				/*
				 * Boosts the power of Ground-type moves by 10% when held. 
				 */				
					SoftSand(){
						this.name = "Soft Sand";
						this.description = "A loose, silky sand that boosts the power of Ground-type moves.";
					}
				}
				
				class SpellTag extends TypeEnhancer{
				/*
				 * Boosts the power of Ghost-type moves by 10% when held. 
				 */				
					SpellTag(){
						this.name = "Spell Tag";
						this.description = "A sinister, eerie tag that boosts Ghost-type moves.";
					}
				}
				
				class TwistedSpoon extends TypeEnhancer{
				/*
				 * Boosts the power of Psychic-type moves by 10% when held. 
				 */				
					TwistedSpoon(){
						this.name = "Twisted Spoon";
						this.description = "A spoon imbued with telekinetic power boosts Psychic-type moves.";
					}
				}
				
			abstract class InBattleEffect extends BattleEnhancer{
			/*
			 * This class models an In-Battle Effect item. In-battle effect items are held items that take effect during a battle. 
			 */
			}
			
				class ChoiceBand extends InBattleEffect{
				/*
				 * Boosts Attack by 50%, but only allows the use of the first move selected. 
				 */
					ChoiceBand(){
						this.name = "Choice Band";
						this.description = "It powers up one move, which becomes the only usable one.";
					}
				}
				
				class MentalHerb extends InBattleEffect{
				/*
				 * Removes the effects of Infatuation. Consumed after use.
				 */
					MentalHerb(){
						this.name = "Mental Herb";
						this.description = "It snaps the holder out of infatuation. It can be used only once.";				
					}
				}
				
				class WhiteHerb extends InBattleEffect{
				/*
				 * Removes any negative stat changes to holder. Consumed after use. 
				 */
					WhiteHerb(){
						this.name = "White Herb";
						this.description = "It restores any lowered stat in battle. It can be used only once.";
					}
				}
				
				class Leftovers extends InBattleEffect{
				/*
				 * Restores 1/16 of the Pokémon's maximum HP (rounded down) each turn when held.
				 */
					Leftovers(){
						this.name = "Leftovers";
						this.description = "The holding Pokémon gradually regains HP during battle.";
					}
				}
				
				class ShellBell extends InBattleEffect{
				/*
				 * Whenever the Pokémon holding it deals damage, it restores 1/8 of the damage dealt as HP. 
				 */
					ShellBell(){
						this.name = "Shell Bell";
						this.description = "The holding Pokémon regains some HP upon striking the foe.";
					}
				}
				
				class ScopeLens extends InBattleEffect{
				/*
				 * Increases the holder's critical hit ratio by one stage.
				 */
					ScopeLens(){
						this.name = "Scope Lens";
						this.description = "A lens that boosts the critical-hit ratio of the holding Pokémon.";
					}
				}
				
				class QuickClaw extends InBattleEffect{
				/*
				 * A hold item that gives the holder a 20% chance of going first in its priority bracket and ignores the effects of Trick Room. 
				 */
					QuickClaw(){
						this.name = "Quick Claw";
						this.description = "A light and sharp claw. The holder may be able to strike first.";
					}
				}
				
				class FocusBand extends InBattleEffect{
				/*
				 * There is a 10% chance that when the holder is hit by an attack that would otherwise cause fainting, it can survive with 1 HP.
				 * Can also protect against multi-hit moves, recoil damage, and self-inflicted confusion damage, but not from Future Sight or Doom Desire.
				 */
					FocusBand(){
						this.name = "Focus Band";
						this.description = "The holder may endure a potential KO attack, leaving it with just 1 HP.";
					}
				}
				
				class AmuletCoin extends InBattleEffect{
				/*
				 * Doubles money received after battle if the holder takes part in the battle.
				 */
					AmuletCoin(){
						this.name = "Amulet Coin";
						this.description = "It doubles the battle money if the holding Pokémon takes part.";					
					}
				}
				
				class BrightPowder extends InBattleEffect{
				/*
				 * Raises the holder's evasion by 10%.
				 */
					BrightPowder(){
						this.name = "Bright Powder";
						this.description = "It casts a tricky glare that lowers the opponent's accuracy.";
					}
				}
				
				class SmokeBall extends InBattleEffect{
				/*
				 * Allows definite escape from a wild Pokémon. 
				 */
					SmokeBall(){
						this.name = "Smoke Ball";
						this.description = "The holding Pokémon can flee from any wild Pokémon for sure.";
					}
				}
				
	abstract class EvolutionaryStone extends Medicine{
	/*
	 * This class models an Evolutionary Stone. Evolutionary Stones may be used at any time, and cause instant evolution in the Pokémon they are used on,
	 * which cannot be canceled. With the exception of the Everstone, which must be held for its effects to take place, all evolutionary stones are
	 * applied directly to the Pokémon. All stones that cause evolution in a Pokémon are consumed upon that Pokémon's evolution.
	 * Can be held by a Pokemon.
	 */	
	}
	
		class FireStone extends EvolutionaryStone{
		/*
		 * DESCRIPTION:
		 *   A peculiar stone that makes certain species of Pokémon evolve. It is colored orange.
		 * EFFECT:
		 *   Causes Vulpix to evolve into Ninetales.
		 *   Causes Growlithe to evolve into Arcanine.
		 *   Causes Eevee to evolve into Flareon.
		 */
		}
		
		class WaterStone extends EvolutionaryStone{
		/*
		 * DESCRIPTION:
		 *    A peculiar stone that makes certain species of Pokémon evolve. It is a clear light blue. 
		 * EFFECT:
		 *    Causes Poliwhirl to evolve into Poliwrath.
		 *    Causes Shellder to evolve into Cloyster.
		 *    Causes Staryu to evolve into Starmie.
		 *    Causes Eevee to evolve into Vaporeon.
		 */		
		}
		
		class ThunderStone extends EvolutionaryStone{
		/*
		 * DESCRIPTION:
		 *    A peculiar stone that makes certain species of Pokémon evolve. It has a thunderbolt pattern.  
		 * EFFECT:
		 *    Causes Pikachu to evolve into Raichu.
		 *	  Causes Eevee to evolve into Jolteon.
		 *	  Causes Eelektrik to evolve into Eelektross. 
		 */				
		}		
		
		class LeafStone extends EvolutionaryStone{
		 /*
		 * DESCRIPTION:
		 *    A peculiar stone that makes certain species of Pokémon evolve. It has a leaf pattern. 
		 * EFFECT:
		 *    Causes Gloom to evolve into Vileplume.
		 *    Causes Weepinbell to evolve into Victreebel.
		 *    Causes Exeggcute to evolve into Exeggutor.
		 */		
		}
		
		class MoonStone extends EvolutionaryStone{
		 /*
		 * DESCRIPTION:
		 *    A peculiar stone that makes certain species of Pokémon evolve. It is as black as the night sky. 
		 * EFFECT:
		 *    Causes Nidorina to evolve into Nidoqueen.
		 *    Causes Nidorino to evolve into Nidoking.
		 *    Causes Clefairy to evolve into Clefable.
		 *    Causes Jigglypuff to evolve into Wigglytuff.
		 */	
		}
		
		class SunStone extends EvolutionaryStone{
		/*
		 * DESCRIPTION:
		 *    A peculiar stone that makes certain species of Pokémon evolve. It is as red as the sun.
		 * EFFECT:
		 *    Causes Gloom to evolve into Bellossom.
		 */		
		}
					
		class Everstone extends EvolutionaryStone{
		/*
		 * DESCRIPTION:
		 *    An item to be held by a Pokémon. The holding Pokémon is prevented from evolving.
		 * EFFECT:
		 * 	  If held, prevents a Pokémon from evolving. 
		 */		
		}
		
	abstract class Drink extends Medicine{
	/*
	 * This class models a Drink, a cheap alternative to a Potion that can be used during or outside of battle to restore a Pokemon's HP.
	 * Bought from vending machines, not sold in stores.
	 */
	}
	
		
		class BerryJuice extends Drink{
		/*
		 * This class models Berry Juice, a drink that restores the HP of one Pokémon by 20 points. 
		 */		
		}
		
		class FreshWater extends Drink{
		/*
		 * This class models Fresh Water, a drink that restores the HP of one Pokémon by 50 points.
		 */
		}
		
		class SodaPop extends Drink{
		/*
		 * This class models Soda Pop, a drink that restores the HP of one Pokémon by 60 points. 
		 */
		}
		
		class Lemonade extends Drink{
		/*
		 * This class models Lemonade, a drink that restores the HP of one Pokémon by 80 points. 
		 */		
		}
		
		class MoomooMilk extends Drink{
		/*
		 * This class models Moomoo Milk, a drink that restores the HP of one Pokémon by 100 points. 
		 */		
		}
					
	abstract class Ether extends Medicine{
	/*
	 * This class models Ether and it's upgrade, Elixir, which can be used during or outside a battle to restore a Pokemon's PP.
	 */	
	}
		
		class StdEther extends Ether{
		/*
		 * This class models a standard Ether, which restores the PP of a Pokémon’s selected move by a maximum of 10 points.
		 */
		}
		
		class MaxEther extends Ether{
		/*
		 * This class models a Max Ether, which fully restores the PP of a single selected move that has been learned by the target Pokémon. 
		 */
		}
		
		class Elixir extends Ether{
		/*
		 * This class models a standard Elixir, which restores the PP of all the moves learned by the targeted Pokémon by 10 points each. 
		 */		
		}
		
		class MaxElixir extends Ether{
		/*
		 * This class models a Max Elixir, which fully restores the PP of all moves that have been learned by the target Pokémon. 
		 */		
		}		
		
	abstract class HerbalMedicine extends Medicine{
	/*
	 * This class models Herbal Medicine, which can be used during or outside of a battle to restore a Pokémon's HP, remove status ailments,
	 * or revive a Pokémon, all with a side effect of lowering a Pokémon's happiness due to their bitter taste.
	 */	
	}
	
		class HealPowder extends HerbalMedicine{
		/*
		 * This class models Heal Powder, an item that can be used during or outside of battle to heal any status ailment.
		 */
		}
		
		class EnergyPowder extends HerbalMedicine{
		/*
		 * This class models Energy Powder, an item that can be used during or outside of a battle to heal a Pokemon's HP by 50 points.
		 */		
		}
		
		class EnergyRoot extends HerbalMedicine{
		/*
		 * This class models Energy Root, an item that can be used during or outside of a battle to heal a Pokemon's HP by 200 points.
		 */		
		}
		
		class RevivalHerb extends HerbalMedicine{
		/*
		 * This class models a Revival Herb, an item that revives a fainted Pokemon to full HP.
		 */		
		}
	
	abstract class Potion extends Medicine{
	/*
	 * This class models a Potion, an item that can be used during or outside of a battle to restore a Pokémon's HP.
	 */	
	}
		class StdPotion extends Potion{
		/*
		 * This class models a standard Potion, an item that restores the HP of one Pokémon by 20 points. 
		 */
		}
		
		class SuperPotion extends Potion{
		/*
		 * This class models a Super Potion, an item that restores the HP of one Pokémon by 50 points. 
		 */		
		}
		
		class HyperPotion extends Potion{
		/*
		 * This class models a Hyper Potion, an item that restores the HP of one Pokémon by 200 points. 
		 */		
		}
		
		class MaxPotion extends Potion{
		/*
		 * This class models a Max Potion, an item that fully restores the HP of one Pokémon.
		 */		
		}
		
		class FullRestore extends Potion{
		/*
		 * This class models a Full Restore potion, an item that fully restores the HP and heals any status problems of one Pokémon. 
		 */		
		}
	
	abstract class Revive extends Medicine{
	/*
	 * This class models a Revive, an item that can be used during or out of battle to restore a Pokémon which has fainted.
	 */	
	}
		
		class StdRevive extends Revive{
		/*
		 * This class models a standard Revive, an item that revives a fainted Pokémon, restoring HP by half the maximum amount. 
		 */
		}
		
		class MaxRevive extends Revive{
		/*
		 * This class models a Max Revive, an item that revives a fainted Pokémon, restoring HP fully.
		 */
		}
	
	abstract class Berry extends Medicine{
	/*
	 * This class models a Berry, a small, juicy, fleshy fruit whose various effects include HP and status ailment restoration, stat enhancement, and even damage negation. 
	 */
	}
		
		class StdBerry extends Berry{
		/*
		 * This class models a standard berry, which restores a Pokemon's HP by 10 points.
		 */
		}
		
		class BitterBerry extends Berry{
		/*
		 * This class models a Bitter Berry, which cures a Pokemon of the status ailment Confusion.
		 */
		}
		
		class BurntBerry extends Berry{
		/*
		 * This class models a Burnt Berry, which cures a Pokemon of the status ailment Freeze.
		 */
		}

		class GoldBerry extends Berry{
		/*
		 * This class models a Gold Berry, which restores a Pokemon's HP by 30 points.
		 */
		}

		class IceBerry extends Berry{
		/*
		 * This class models a Ice Berry, which cures a Pokemon of the status ailment Burn.
		 */
		}

		class MintBerry extends Berry{
		/*
		 * This class models a Mint Berry, which cures a Pokemon of the status ailment Sleep.
		 */
		}

		class MiracleBerry extends Berry{
		/*
		 * This class models a Miracle Berry, which cures a Pokemon of any status ailment.
		 */
		}

		class MysteryBerry extends Berry{
		/*
		 * This class models a Mystery Berry, which restores a Pokemon's HP by 5 points. (Why not make it a random/unknown number of points?)
		 */
		}
		
		class PARALYSIS_CureBerry extends Berry{
		/*
		 * This class models a Paralysis Cure Berry, which cures a Pokemon of the status ailment Paralysis.
		 */
		}
		
		class POISON_CureBerry extends Berry{
		/*
		 * This class models a Poison Cure Berry, which cures a Pokemon of the status ailment Poison.
		 */
		}
		
	class SootheBell extends Medicine{
	/*
	 * This class models a Soothe Bell, a curious item that provides a comforting chime that calms the holder, boosting its happiness and making it friendly.
	 * Can be held by a Pokemon.
	 */
	}

/**********************************************************************************************************************************************************************************************/

abstract class MISC_ITEM extends Item{
/*
 * This class models any item that does not fit nicely into any of the previously defined categories.
 */
}
	
	abstract class Flute extends MISC_ITEM{
	/*
	 * This class models a Flute, an item made from blown glass that can have various effects when played. 
	 */
	}
		
		class BlackFlute extends Flute{
		/*
		 * This class models a Black Flute, which reduces the wild Pokemon encounter rate.
		 */
		}
		
		class WhiteFlute extends Flute{
		/*
		 * This class models a White Flute, which increases the wild Pokemon encounter rate.
		 */
		}
		
		class BlueFlute extends Flute{
		/*
		 * This class models a Blue Flute, a battle-item which awakens a single Pokemon from Sleep when played.
		 */
		}
		
		class RedFlute extends Flute{
		/*
		 * This class models a Red Flute, a battle-item which snaps a single Pokemon out of Infatuation when played.
		 */
		}
				
		class YellowFlute extends Flute{
		/*
		 * This class models a Yellow Flute, a battle-item which snaps a single Pokemon out of Confusion when played.
		 */		
		}
		
/**********************************************************************************************************************************************************************************************/
/**********************************************************************************************************************************************************************************************/
/**********************************************************************************************************************************************************************************************/