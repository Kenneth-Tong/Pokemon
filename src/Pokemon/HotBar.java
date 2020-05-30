package Pokemon;

import jdk.nashorn.internal.scripts.JO;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.sound.sampled.Clip;
import javax.swing.*;

public class HotBar extends JPanel implements ActionListener {
	private Player player;
	private JButton inventory, pokemon, stats, loadout, key;
	private JPanel panel;
	//Add during attacking
	private JButton pokemonAttack, attackItems, pokemonTeamAttack, fleeAttack; //attack means it's a move only during pokemonfight
	private boolean playerFight, caught = false;
	private Pokemon opponentPokemon;

	public HotBar(Player n) {
		player = n;

		key = new JButton("Key");
		key.addActionListener(this);
		super.add(key);

		inventory = new JButton("Inventory");
		inventory.addActionListener(this);
		super.add(inventory);
		
		pokemon = new JButton("Pokemon");
		pokemon.addActionListener(this);
		super.add(pokemon);
		
		stats = new JButton("Stats");
		stats.addActionListener(this);
		super.add(stats);
		
		loadout = new JButton("Change Pokemon Team");
		loadout.addActionListener(this);
		super.add(loadout);
	}
	public void setPanel(JPanel x) {
		panel = x;
	}
	public void update(Player p, Pokemon opponentPokemon, boolean playerFight) {
		super.remove(pokemon);
		super.remove(inventory);
		super.remove(key);
		super.remove(stats);
		super.remove(loadout);

		pokemonAttack = new JButton("Attack");
		pokemonAttack.addActionListener(this);
		super.add(pokemonAttack);

		pokemonTeamAttack = new JButton("Switch Pokemon");
		pokemonTeamAttack.addActionListener(this);
		super.add(pokemonTeamAttack);

		attackItems = new JButton("Inventory");
		attackItems.addActionListener(this);
		super.add(attackItems);

		fleeAttack = new JButton("Flee");
		fleeAttack.addActionListener(this);
		super.add(fleeAttack);

		player = p;
		this.playerFight = playerFight;
		this.opponentPokemon = opponentPokemon;

		panel.revalidate();
		panel.repaint();
	}
	public void reset() {
		super.remove(pokemonTeamAttack);
		super.remove(attackItems);
		super.remove(pokemonAttack);
		super.remove(fleeAttack);

		key = new JButton("Key");
		key.addActionListener(this);
		super.add(key);

		inventory = new JButton("Inventory");
		inventory.addActionListener(this);
		super.add(inventory);

		pokemon = new JButton("Pokemon");
		pokemon.addActionListener(this);
		super.add(pokemon);

		stats = new JButton("Stats");
		stats.addActionListener(this);
		super.add(stats);

		loadout = new JButton("Change Pokemon Team");
		loadout.addActionListener(this);
		super.add(loadout);

		panel.revalidate();
		panel.repaint();
	}
	public void actionPerformed(ActionEvent e) {
		Pokemon[] pokemonList = getPokemonTeam();
		String[] pokemonTeamNames = getPokemonTeamNames();
		String[] movesName  = getMovesName();
		String[] inventoryList = getInventory();
		PokemonMove[] moves = getMoves();
		String[] pokemonNames = getPokemonNames();
		JButton btn = (JButton) e.getSource();

		if(!GuiMap.pokemonFight) { //only run when not fighting
			if (btn == key) {
				JOptionPane.showMessageDialog(null,
						"Map:" +
								"\nLight Green - Grass" +
								"\nDark Green - Tall Grass" +
								"\nLight Blue - Water" +
								"\nlight Gray - Path" +
								"\nGray - Wall" +
								"\nDark Green - Tree" +
								"\nOrange - Sign" +
								"\nLight Brown - Chest" +
								"\nDark Brown - Wood" +
								"\nPurple - NPC" +
								"\nDark Gray - Gravel",
						"Key", JOptionPane.INFORMATION_MESSAGE);
			}
			if (btn == stats) {
				if (pokemonList.length != 0)
					JOptionPane.showMessageDialog(null, "Name: " + player.getName() + "\nTrainer Type: " + player.getTrainerType() + "\nFavorite Pokemon: " + pokemonList[0] + "\nPokemon: " + pokemonList.length + "\nItems: " + inventoryList.length + "\nMoney: " + player.getPokemonDollar(), "Pokemon", JOptionPane.PLAIN_MESSAGE);
				else
					JOptionPane.showMessageDialog(null, "Name: " + player.getName() + "\nTrainer Type: " + player.getTrainerType() + "\nFavorite Pokemon: None\nPokemon: " + pokemonList.length + "\nItems: " + inventoryList.length + "\nMoney: " + player.getPokemonDollar(), "Pokemon", JOptionPane.PLAIN_MESSAGE);
			}

			if (btn == pokemon) {
				inspectPokemon(pokemonList, pokemonNames);
			}
			if (btn == inventory) {
				useInventory(inventoryList, pokemonList);
			}
			if (btn == loadout) {
				switchPokemonTeam(pokemonTeamNames, pokemonList);
			}
			panel.requestFocus();
		} else { //players turn
			if(GuiMap.playerTurn) {
				if (btn == pokemonAttack) {
					pokemonAttackOpponent(movesName, moves, pokemonList);
				}
				if (btn == fleeAttack) {
					playerFlee();
				}
				if (btn == attackItems) {
					useInventory(inventoryList, pokemonList);
				}
				if (btn == pokemonTeamAttack) {
					switchPokemonTeam(pokemonTeamNames, pokemonList);
				}
			} else {
				JOptionPane.showMessageDialog(null, "It is not your turn yet", "Battle", JOptionPane.ERROR_MESSAGE);
			}
		}
		panel.requestFocus();
	}
	public void inspectPokemon(Pokemon[] pokemonList, String[] pokemonNames) {
		if (pokemonList.length == 0) {
			JOptionPane.showMessageDialog(null, "You don't have any pokemon", "Pokemon", JOptionPane.ERROR_MESSAGE);
		} else {
			String pokemon = (String) JOptionPane.showInputDialog(null,
					"Select a Pokemon:",
					"Pokemon",
					JOptionPane.PLAIN_MESSAGE,
					null,
					pokemonNames,
					pokemonNames[0]);
			if (pokemon == null || (("".equals(pokemon)))) { //nothing is inputted
				panel.requestFocus();
				return;
			}
			int pokemonLocation = -1;
			for (int k = 0; k < pokemonNames.length; k++) {
				if (pokemonNames[k].equals(pokemon))
					pokemonLocation = k;
			}
			String moves = "";
			for (int m = 0; m < 4; m++) { //4 moves per pokemon
				moves += pokemonList[pokemonLocation].getAttack(m) + "| Damage: " + pokemonList[pokemonLocation].getAttack(m).getMax() + " - " + pokemonList[pokemonLocation].getAttack(m).getMin() + " Move Point: " + pokemonList[pokemonLocation].getAttack(m).getCurrentMovePoint() + "/" + pokemonList[pokemonLocation].getAttack(m).getMovePoint() + "\n";
			}
			JOptionPane.showMessageDialog(null,
					pokemonList[pokemonLocation].getName() +
							"\nType: " +
							pokemonList[pokemonLocation].getType() +
							"\nMoves: \n" +
							moves +
							"Health: " +
							pokemonList[pokemonLocation].getHealth() + "/" +
							pokemonList[pokemonLocation].getMaxHealth(),
					pokemonNames[pokemonLocation],
					JOptionPane.INFORMATION_MESSAGE);
		}
	}
	public void playerFlee() {
		if(!playerFight) {
			int escaped = (int) (Math.random() * 3);
			if (escaped == 0) {
				JOptionPane.showMessageDialog(null, "You fled successfully!", "Flee", JOptionPane.INFORMATION_MESSAGE);
				GuiMap.pokemonFight = false;
				panel.requestFocus();
				return;
			} else {
				JOptionPane.showMessageDialog(null, "You were unable to flee!", "Flee", JOptionPane.INFORMATION_MESSAGE);
				GuiMap.playerTurn = false;
			}
		} else {
			JOptionPane.showMessageDialog(null, "You cannot run from a duel!", "Flee", JOptionPane.ERROR_MESSAGE);
		}
	}
	public void pokemonAttackOpponent(String[] movesName, PokemonMove[] moves, Pokemon[] pokemonList) {
		String moveSelected = (String) JOptionPane.showInputDialog(null,
				"Select move do you want to use:",
				"Moves",
				JOptionPane.PLAIN_MESSAGE,
				null,
				movesName,
				movesName[0]);
		if (moveSelected == null || (("".equals(moveSelected)))) { //nothing is inputted
			panel.requestFocus();
			return;
		}
		int moveLocation = -1;
		for (int k = 0; k < moves.length; k++) {
			if (moves[k].getName().equals(moveSelected))
				moveLocation = k;
		}

		int moveDone = moves[moveLocation].doMove(); //flat damage no reduction or addiction
		if(moveDone > 0) {
			if(moves[moveLocation].isHeal()) {
				pokemonList[0].heal(moveDone);
				JOptionPane.showMessageDialog(null, pokemonList[0].getName() + " healed using " + moveSelected + "!");
				JOptionPane.showMessageDialog(null, pokemonList[0].getName() + " has been healed to " + pokemonList[0].getHealth() + " hp", "Pokemon Battle", JOptionPane.INFORMATION_MESSAGE);
			} else {
				int damage = opponentPokemon.damageTaken(moveDone, moves[moveLocation].getType());

				JOptionPane.showMessageDialog(null, pokemonList[0].getName() + " used " + moveSelected + "!");
				switch(opponentPokemon.getEffective()) { //only if it's effective or not
					case "no":
						JOptionPane.showMessageDialog(null, "It was not very effective...", "Pokemon Battle", JOptionPane.INFORMATION_MESSAGE);
						break;
					case "yes":
						JOptionPane.showMessageDialog(null, "It was super effective!", "Pokemon Battle", JOptionPane.INFORMATION_MESSAGE);
						break;
				}
				JOptionPane.showMessageDialog(null, "Damage Dealt: " + damage, "Move", JOptionPane.INFORMATION_MESSAGE);
				if(opponentPokemon.getHealth() < 0) //don't go negative!
					JOptionPane.showMessageDialog(null, opponentPokemon.getName() + " now has 0 hp left", "Pokemon Battle", JOptionPane.INFORMATION_MESSAGE);
				else
					JOptionPane.showMessageDialog(null, opponentPokemon.getName() + " now has " + opponentPokemon.getHealth() + " hp left", "Pokemon Battle", JOptionPane.INFORMATION_MESSAGE);
			}
			if(opponentPokemon.hasFainted()) {
				JOptionPane.showMessageDialog(null, opponentPokemon.getName() + " has fainted!", "Pokemon Battle", JOptionPane.INFORMATION_MESSAGE);
				JOptionPane.showMessageDialog(null, "You've won!", "Pokemon Battle", JOptionPane.INFORMATION_MESSAGE);
				int money = (int) (Math.random() * 10) + 5;
				player.addPokemonDollar(money);
				JOptionPane.showMessageDialog(null, "You've gained " + money + " Pokedollars", "Pokemon Battle", JOptionPane.INFORMATION_MESSAGE);
				GuiMap.clipFight.stop();
				GuiMap.clipMusic.setMicrosecondPosition(GuiMap.clipTimePosition);
				GuiMap.clipMusic.loop(Clip.LOOP_CONTINUOUSLY);
				GuiMap.pokemonFight = false;
				panel.requestFocus();
				return;
			}
			GuiMap.playerTurn = false;
		} else {
			JOptionPane.showMessageDialog(null, "You have no more move points for " + moves[moveLocation], "Inventory", JOptionPane.ERROR_MESSAGE);
		}
	}
	public void switchPokemonTeam(String[] pokemonTeamNames, Pokemon[] pokemonList) {
		String pokemonSwitch1 = (String) JOptionPane.showInputDialog(null,
				"Select a Pokemon to switch:",
				"Team",
				JOptionPane.PLAIN_MESSAGE,
				null,
				pokemonTeamNames,
				pokemonTeamNames[0]);
		if (pokemonSwitch1 == null || (("".equals(pokemonSwitch1)))) { //nothing is inputted
			panel.requestFocus();
			return;
		}
		int pokemonLocation1 = -1;
		for (int k = 0; k < pokemonTeamNames.length; k++) {
			if (pokemonTeamNames[k].equals(pokemonSwitch1))
				pokemonLocation1 = k;
		}
		String pokemonSwitch2;
		int pokemonLocation2;
		if(GuiMap.pokemonFight) {
			pokemonSwitch2 = (String) JOptionPane.showInputDialog(null,
					"Select another Pokemon to switch:",
					"Team",
					JOptionPane.PLAIN_MESSAGE,
					null,
					pokemonTeamNames,
					pokemonTeamNames[0]);
			if (pokemonSwitch2 == null || (("".equals(pokemonSwitch2)))) { //nothing is inputted
				panel.requestFocus();
				return;
			}
			pokemonLocation2 = -1;
			for (int k = 0; k < pokemonTeamNames.length; k++) {
				if (pokemonTeamNames[k].equals(pokemonSwitch2))
					pokemonLocation2 = k;
			}
		} else { //you can switch with entire team
			pokemonSwitch2 = (String) JOptionPane.showInputDialog(null,
					"Select another Pokemon to switch:",
					"Team",
					JOptionPane.PLAIN_MESSAGE,
					null,
					getPokemonNames(),
					pokemonTeamNames[0]);
			if (pokemonSwitch2 == null || (("".equals(pokemonSwitch2)))) { //nothing is inputted
				panel.requestFocus();
				return;
			}
			pokemonLocation2 = -1;
			for (int k = 0; k < getPokemonNames().length; k++) {
				if (getPokemonNames()[k].equals(pokemonSwitch2))
					pokemonLocation2 = k;
			}
		}
		player.switchPokemon(pokemonList[pokemonLocation1], pokemonList[pokemonLocation2]);
		if(GuiMap.pokemonFight)
			JOptionPane.showMessageDialog(null, "I choose you, " + player.getPokemon().get(pokemonLocation2).getName() + "!" + player.getPokemon().get(0).getName(), "Team", JOptionPane.INFORMATION_MESSAGE);
		else
			JOptionPane.showMessageDialog(null, player.getPokemon().get(pokemonLocation2).getName() + " has been swapped with " + player.getPokemon().get(pokemonLocation1).getName(), "Team", JOptionPane.INFORMATION_MESSAGE);
	}
	public void useInventory(String[] inventoryList, Pokemon[] pokemonList) {
		if (inventoryList.length == 0)
			JOptionPane.showMessageDialog(null, "You have no items in your backpack", "Inventory", JOptionPane.ERROR_MESSAGE);
		else {
			String item = (String) JOptionPane.showInputDialog(null,
					"Select item do you want to use:",
					"Inventory",
					JOptionPane.PLAIN_MESSAGE,
					null,
					inventoryList,
					inventoryList[0]);
			if (item == null || (("".equals(item)))) { //nothing is inputted
				panel.requestFocus();
				return;
			}
			int inventoryLocation = -1;
			for (int k = 0; k < inventoryList.length; k++) {
				if (inventoryList[k].equals(item))
					inventoryLocation = k;
			}
			if (inventoryLocation == -1) { //shouldn't be
				panel.requestFocus();
				return;
			}
			if(player.getInventory(inventoryLocation, false) instanceof HealItem) {
				String pokemon;
				if(GuiMap.pokemonFight) { //only get pokemon team
					pokemon = (String) JOptionPane.showInputDialog(null,
							"Which Pokemon do you want to use the " + item + " on:",
							"Pokemon",
							JOptionPane.QUESTION_MESSAGE,
							null,
							getPokemonTeamNames(),
							getPokemonTeamNames()[0]);
				} else { //get all pokemon
					pokemon = (String) JOptionPane.showInputDialog(null,
							"Which Pokemon do you want to use the " + item + " on:",
							"Pokemon",
							JOptionPane.QUESTION_MESSAGE,
							null,
							getPokemonNames(),
							getPokemonNames());
				}
				if (pokemon == null || (("".equals(pokemon)))) { //nothing is inputted
					panel.requestFocus();
					return;
				}
				int pokemonLocation = -1;
				for (int k = 0; k < pokemonList.length; k++) {
					if (pokemonList[k].getName().equals(pokemon))
						pokemonLocation = k;
				}
				player.getPokemon().get(pokemonLocation).heal(((HealItem) (player.getInventory(inventoryLocation, true))).getHeal()); //is a healing item
				player.getInventory().remove(inventoryLocation); //the item has been used
				JOptionPane.showMessageDialog(null, player.getPokemon().get(pokemonLocation).getName() + " has been healed to " + player.getPokemon().get(pokemonLocation).getHealth() + " hp", "Healed", JOptionPane.INFORMATION_MESSAGE);
			} else if(GuiMap.pokemonFight) {
				if (player.getInventory(inventoryLocation, false) instanceof Bike) {
					JOptionPane.showMessageDialog(null, "You cannot use that item during this time.", "Inventory", JOptionPane.ERROR_MESSAGE);
					return;
				} else if (player.getInventory(inventoryLocation, false) instanceof Badge) {
					JOptionPane.showMessageDialog(null, "You cannot use that item during this time.", "Inventory", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (player.getInventory(inventoryLocation, true) instanceof Pokeball && !playerFight) {
					JOptionPane.showMessageDialog(null, "You used a Pokeball on " + opponentPokemon.getName() + "!", "Catching", JOptionPane.INFORMATION_MESSAGE);
					catchPokemon();
					if (caught) {
						player.addPokemon(opponentPokemon);
						JOptionPane.showMessageDialog(null, "You caught " + opponentPokemon.getName() + "!", "Catching", JOptionPane.INFORMATION_MESSAGE);
						JOptionPane.showMessageDialog(null, "[Check your Pokedex for more information]", "Catching", JOptionPane.INFORMATION_MESSAGE);
						GuiMap.pokemonFight = false;
						panel.requestFocus();
						return;
					} else {
						JOptionPane.showMessageDialog(null, "The " + opponentPokemon.getName() + " was not caught.", "Catching", JOptionPane.ERROR_MESSAGE);
						GuiMap.playerTurn = false;
					}
					return;
				} else if (player.getInventory(inventoryLocation, false) instanceof Pokeball && playerFight) {
					JOptionPane.showMessageDialog(null, "You cannot catch already caught Pokemon!", "Inventory", JOptionPane.ERROR_MESSAGE);
					return;
				}
			} else {
				if(player.getInventory(inventoryLocation, false) instanceof Bike) {
					if(!player.getIsOnBike()) {
						int answer = JOptionPane.showConfirmDialog(null,
								"Do you want to equip the Bike?",
								"Inventory",
								JOptionPane.YES_NO_OPTION,
								JOptionPane.QUESTION_MESSAGE);
						if (answer == JOptionPane.YES_OPTION) {
							((Bike) player.getInventory(inventoryLocation, false)).setBike(true);
							JOptionPane.showMessageDialog(null, "You equipped the Bike");
						}
					} else {
						int answer = JOptionPane.showConfirmDialog(null,
								"Do you want to unequip the Bike?",
								"Inventory",
								JOptionPane.YES_NO_OPTION,
								JOptionPane.QUESTION_MESSAGE);
						if (answer == JOptionPane.YES_OPTION) {
							((Bike) player.getInventory(inventoryLocation, false)).setBike(false);
							JOptionPane.showMessageDialog(null, "You unequipped the Bike");
						}
					}
				} else { //it's not a bike and heal
					JOptionPane.showMessageDialog(null, player.getInventory(inventoryLocation, false).getDescription(), "Inventory", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}
	}
	public void catchPokemon() {
		int random = (int) (Math.random() * 4); //2/3ds catch chance
		if(random != 3)
			caught = true;
		else
			caught = false;
	}
	public String[] updateTeam() {
		String[] names;
		if(player.getPokemon().size() > 5) { //this is a full team
			names = new String[6];
			for (int i = 0; i < 6; i++) {
				names[i] = player.getPokemon().get(i).getName();
			}
		} else {
			names = new String[player.getPokemon().size()];
			for (int i = 0; i < player.getPokemon().size(); i++) {
				names[i] = player.getPokemon().get(i).getName();
			}
		}
		return names;
	}
	public void checkFight() {
		if(player.getPokemon().get(0).hasFainted() && player.hasPokemon()) {
			String[] names = getPokemonTeamNames();
			String pokemonSwitch1 = (String) JOptionPane.showInputDialog(null,
					player.getPokemon().get(0) + " has fainted!" +
							"\nSelect a pokemon to switch in:",
					"Team",
					JOptionPane.PLAIN_MESSAGE,
					null,
					names,
					names[0]);
			if (pokemonSwitch1 == null || (("".equals(pokemonSwitch1)))) { //nothing is inputted
				panel.requestFocus();
				return;
			}
			int pokemonLocation1 = -1;
			for (int k = 0; k < names.length; k++) {
				if (names[k].equals(pokemonSwitch1))
					pokemonLocation1 = k;
			}
			player.switchPokemon(player.getPokemon().get(pokemonLocation1), player.getPokemon().get(0));
			JOptionPane.showMessageDialog(null, "I choose you, " + player.getPokemon().get(pokemonLocation1).getName() + "!", "Team", JOptionPane.INFORMATION_MESSAGE);
		} else if(!player.hasPokemon()) { //is lost for the trainer
			player.setLocation(392, 95);
			JOptionPane.showMessageDialog(null, "Your team has fainted!", "Team", JOptionPane.INFORMATION_MESSAGE);
			GuiMap.pokemonFight = false;
			player.setLost(true);
			GuiMap.clipFight.stop();
			GuiMap.clipMusic.setMicrosecondPosition(GuiMap.clipTimePosition);
			GuiMap.clipMusic.loop(Clip.LOOP_CONTINUOUSLY);
			reset();
		}
		panel.requestFocus();
	}
	public String[] getPokemonTeamNames() {
		String[] pokemonNames = new String[getPokemonTeam().length];
		for(int i = 0; i < getPokemonTeam().length; i++) {
			pokemonNames[i] = getPokemonTeam()[i].getName();
		}
		return pokemonNames;
	}
	public Pokemon[] getPokemonTeam() {
		Pokemon[] pokemonList;
		if (player.getPokemon().size() > 5) {
			pokemonList = new Pokemon[6];
			for (int i = 0; i < 6; i++) {
				pokemonList[i] = player.getPokemon().get(i);
			}
		} else {
			pokemonList = new Pokemon[player.getPokemon().size()];
			for (int i = 0; i < player.getPokemon().size(); i++) {
				pokemonList[i] = player.getPokemon().get(i);
			}
		}
		return pokemonList;
	}
	public String[] getInventory() {
		String[] list = new String[player.getInventory().size()]; //creating multiple buttons
		for (int i = 0; i < player.getInventory().size(); i++) {
			list[i] = player.getInventory().get(i).getName();
		}
		return list;
	}
	public String[] getMovesName() {
		String[] moves = new String[4];
		for(int i = 0; i < 4; i++) {
			moves[i] = getPokemon()[0].getPokemonMoves(i).getName();
		}
		return moves;
	}
	public PokemonMove[] getMoves() {
		PokemonMove[] moves = new PokemonMove[4];
		for(int i = 0; i < 4; i++) {
			moves[i] = getPokemon()[0].getPokemonMoves(i);
		}
		return moves;
	}
	public Pokemon[] getPokemon() {
		Pokemon[] pokemon = new Pokemon[player.getPokemon().size()];
		for (int i = 0; i < player.getPokemon().size(); i++) {
			pokemon[i] = player.getPokemon().get(i);
		}
		return pokemon;
	}
	public String[] getPokemonNames() {
		String[] names = new String[player.getPokemon().size()];
		for (int i = 0; i < player.getPokemon().size(); i++) {
			names[i] = player.getPokemon().get(i).getName();
		}
		return names;
	}
}
