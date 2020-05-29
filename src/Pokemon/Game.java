package Pokemon;

import java.awt.BorderLayout;
import javax.swing.*;

@SuppressWarnings("serial")
public class Game extends JFrame {
	public Game() {
		super("Pokemon");
		Player Player = new Player("_");
		super.setLayout(new BorderLayout());
		HotBar hotBarButtons = new HotBar(Player); //this will be used in the gui map
		JPanel panelButtons = hotBarButtons;
		super.add(panelButtons, BorderLayout.SOUTH);
		GuiMap panelMap = new GuiMap(Player, 600, 600);
		JPanel panel = panelMap;
		super.add(panel, BorderLayout.CENTER);
		panelMap.updateHotBar(hotBarButtons);
		hotBarButtons.setPanel(panelMap);
	}
	public static void main(String[] args) {
		Game window = new Game();
		window.setBounds(0, 0, 616, 680);
		window.setLocationRelativeTo(null);
		window.setDefaultCloseOperation(EXIT_ON_CLOSE);
		window.setVisible(true);
	}
}
