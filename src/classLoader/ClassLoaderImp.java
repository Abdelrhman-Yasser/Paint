package classLoader;

import java.awt.Color;
import java.io.File;
import java.lang.reflect.Modifier;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import shapes.Shape;

public class ClassLoaderImp {

	public Pack packer(ArrayList<Class<?>> classes) {
		Class<?> loaded = toLoad();
		if (check(loaded, classes)) {
			JButton button = null;
			boolean flag = true;
			if (JOptionPane.showConfirmDialog(null, "Add .png picture to your button") == JOptionPane.YES_OPTION) {
				JFileChooser fileChooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter(".png Files", "png");
				fileChooser.setFileFilter(filter);
				int returnVal = fileChooser.showOpenDialog(null);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					String fileName = fileChooser.getSelectedFile().getName();
					String extension = fileName.substring(fileName.lastIndexOf("."),fileName.length());
					if (extension.equals(".png") || extension.equals(".PNG")) {
						Icon image = new ImageIcon(fileChooser.getSelectedFile().getAbsolutePath());
						button = new JButton(image);
						button.setBackground(Color.GRAY);
						flag = false;
					}
				}
			}
			if (flag) {
				button = new JButton(loaded.getName());
			}
			button.setBackground(Color.GRAY);
			return new Pack(button, loaded);
		}
		return null;
	}

	private boolean check(Class<?> loaded, ArrayList<Class<?>> classes) {
		if (loaded == null || loaded.getName().equals("shapes.Line") || Modifier.isAbstract(loaded.getModifiers())) {
			return false;
		}
		for (Class<?> class1 : classes) {
			if (class1.getName().equals(loaded.getName())) {
				return false;
			}
		}
		return true;
	}
	
	private Class<?> toLoad() {
		JFileChooser fileChooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter(".class Files", "class");
		fileChooser.setFileFilter(filter);				
		int returnVal = fileChooser.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			String fileName = fileChooser.getSelectedFile().getName();
			String extension = fileName.substring(fileName.lastIndexOf("."),fileName.length());
			if (extension.equals(".class")) {
				try {
					File file  = new File(fileChooser.getSelectedFile().getParentFile().getParentFile().getAbsolutePath());
					ClassLoader parentLoader = Shape.class.getClassLoader();
					URLClassLoader ucl = URLClassLoader.newInstance(new URL[]{file.toURI().toURL()}, parentLoader);
					String dad = fileChooser.getSelectedFile().getParentFile().getName();
					String name = dad.concat("." + fileChooser.getSelectedFile().getName());
					name = name.substring(0, name.length()-6);
					Class<?> loadedClass = (Class<?>) ucl.loadClass(name);
					return loadedClass;
				} catch (MalformedURLException e) {
				} catch (ClassNotFoundException e) {
				} catch (NoClassDefFoundError e) {	
				}
			}
		}
		return null;
	}
}
