package classLoader;

import javax.swing.JButton;

public class Pack {

	private JButton button;
	private Class<?> loaded;
	
	public Pack(JButton button, Class<?> loaded) {
		this.button = button;
		this.loaded = loaded;
	}

	public JButton getButton() {
		return button;
	}

	public Class<?> getLoaded() {
		return loaded;
	}
}
