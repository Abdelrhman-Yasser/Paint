/*
 * 
 */
package saveLoad;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import state.State;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.CompositeClassLoader;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import com.thoughtworks.xstream.io.xml.DomDriver;
 
/**
 * The Class SaveLoad.
 */
public class SaveLoad {
	
	private static SaveLoad instance;

	private SaveLoad() {
	
	}

	public static SaveLoad getInstance() {
		if (instance == null) {
			instance = new SaveLoad();
		}
		return instance;
	}
	
	/**
	 * Save json.
	 *
	 * @param state the state
	 * @param file the file
	 */
	public void saveJson(State state, String file) {
		try {
			XStream xstream = new XStream(new JettisonMappedXmlDriver());
			ObjectOutputStream objectOutputStream = xstream.createObjectOutputStream(new FileOutputStream(file));
			objectOutputStream.writeObject(state);
			objectOutputStream.close();
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Folder not found");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error Saving");
		}
		
	}
	
	/**
	 * Save XML.
	 *
	 * @param state the state
	 * @param file the file
	 */
	public void saveXML (State state, String file) {
		try {
			XStream xstream = new XStream(new DomDriver());
			ObjectOutputStream objectOutputStream = xstream.createObjectOutputStream(new FileOutputStream(file));
			objectOutputStream.writeObject(state);
			objectOutputStream.close();
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Folder not found");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error Saving");
		}
	
	}
	
	/**
	 * Load XML.
	 *
	 * @param file the file
	 * @return the state
	 */
	public State loadXML(File file, ArrayList<Class<?>> classes) {
		try {
			XStream xstream = new XStream(new DomDriver());
			CompositeClassLoader classLoader = new CompositeClassLoader();			
			for (Class<?> class1 : classes) {
				classLoader.add(class1.getClassLoader());	
			}
			xstream.setClassLoader(classLoader);
			ObjectInputStream objectInputStream = xstream.createObjectInputStream(new FileInputStream(file.getAbsolutePath()));
			State xml = (State) objectInputStream.readObject();
			objectInputStream.close();
			return xml;
		} catch (FileNotFoundException e) {
			return null;
		} catch (ClassNotFoundException e) {
			return null;
		} catch (IOException e) {
			return null;
		}
	}
	
	/**
	 * Load json.
	 *
	 * @param file the file
	 * @return the state
	 */
	public State loadJson(File file, ArrayList<Class<?>> classes) {
		try {
			XStream xstream = new XStream(new JettisonMappedXmlDriver());
			CompositeClassLoader classLoader = new CompositeClassLoader();			
			for (Class<?> class1 : classes) {
				classLoader.add(class1.getClassLoader());	
			}
			xstream.setClassLoader(classLoader);
			ObjectInputStream objectInputStream = xstream.createObjectInputStream(new FileInputStream(file.getAbsolutePath()));
			State json = (State) objectInputStream.readObject();
			objectInputStream.close();
			return json;
		} catch (FileNotFoundException e) {
			return null;
		} catch (ClassNotFoundException e) {
			return null;
		} catch (IOException e) {
			return null;
		}
	}
}