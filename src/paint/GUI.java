package paint;

// Using AWT's Graphics and Color
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;





// Using Swing's components and containers
import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;

import saveLoad.SaveLoad;
import shapes.ControlPixel;
import shapes.Line;
import shapes.IShape;
import state.State;
import undoRedo.UR;
import classLoader.ClassLoaderImp;
import classLoader.Pack;

import com.thoughtworks.xstream.mapper.CannotResolveClassException;

/**
 * The Class GUI Main core for application.
 */
public class GUI extends JFrame {

	public static final int CANVAS_WIDTH = 10;
	public static final int CANVAS_HEIGHT = 10;
	
	JFrame frame;

	private ArrayList<IShape> grid;
	private ArrayList<JButton> buttons;
	private ArrayList<Class<?>> classes;

	// variables used in logic
	private IShape selected;
	
	private boolean drawing = false, coloring = false;
	
	private String selectedControl = "";
	private int choose;
	
	private int beginx, beginy, endx, endy;
	private UR undoRedo;
	private SaveLoad saveLoad;

	// gui variables
	private DrawCanvas canvas;
	Container full;
	Box Rbuttons, Lbuttons, paintArea, colorchooser;
	JColorChooser c;
	Icon loadImage, saveImage, brushImage, undoImage, redoImage ,deleteImage , lineImage, classImage;
	JButton save, brush, undo, redo ,delete, line, classLoader, load;
	//circle, square, rectangle, Triangle, Ellipse,

	/**
	 * Instantiates a new window application.
	 */
	
	private void define() {
		grid = new ArrayList<IShape>();
		buttons = new ArrayList<JButton>();
		classes = new ArrayList<Class<?>>();

		undoRedo = UR.getInstance();
		saveLoad = SaveLoad.getInstance();
		
		full = getContentPane();
		full.setSize(10, 10);
		setResizable(false);

		Rbuttons = Box.createVerticalBox();
		Lbuttons = Box.createVerticalBox();
		paintArea = Box.createVerticalBox();
		colorchooser = Box.createHorizontalBox();
		c = new JColorChooser();
		c.setPreviewPanel(new JPanel());

		c.setSize(50, 50);
		colorchooser.setSize(800, 50);
		c.setColor(Color.white);
		colorchooser.add(c, BorderLayout.CENTER);

		// Construct the drawing canvas
		canvas = new DrawCanvas(); 
		canvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
		canvas.setBackground(Color.white);

		deleteImage = new ImageIcon("paintPictures/deleteImage.png");
		delete = new JButton(deleteImage);
		delete.setBackground(Color.GRAY);

		undoImage = new ImageIcon("paintPictures/undoImage.png");
		undo = new JButton(undoImage);
		undo.setBackground(Color.GRAY);

		redoImage = new ImageIcon("paintPictures/redoImage.png");
		redo = new JButton(redoImage);
		redo.setBackground(Color.GRAY);

		brushImage = new ImageIcon("paintPictures/brushImage.png");
		brush = new JButton(brushImage);
		brush.setBackground(Color.GRAY);

		lineImage = new ImageIcon("paintPictures/lineImage.png");
		line = new JButton(lineImage);
		line.setBackground(Color.GRAY);

		saveImage = new ImageIcon("paintPictures/saveImage.png");
		save = new JButton(saveImage);
		save.setBackground(Color.GRAY);

		loadImage = new ImageIcon("paintPictures/loadImage.png");
		load = new JButton(loadImage);
		load.setBackground(Color.GRAY);
		
		classImage = new ImageIcon("paintPictures/loader.png");
		classLoader = new JButton(classImage);
		classLoader.setBackground(Color.GRAY);

		Lbuttons.add(Box.createVerticalStrut(20));
		Lbuttons.add(line, BorderLayout.NORTH);
		Lbuttons.add(Box.createVerticalStrut(5));
		Lbuttons.add(brush, BorderLayout.NORTH);

		Rbuttons.add(Box.createVerticalStrut(20));
		Rbuttons.add(save, BorderLayout.NORTH);
		Rbuttons.add(Box.createVerticalStrut(5));
		Rbuttons.add(load, BorderLayout.NORTH);
		Rbuttons.add(Box.createVerticalStrut(5));
		Rbuttons.add(undo, BorderLayout.NORTH);
		Rbuttons.add(Box.createVerticalStrut(5));
		Rbuttons.add(redo, BorderLayout.NORTH);
		Rbuttons.add(Box.createVerticalStrut(5));
		Rbuttons.add(delete, BorderLayout.NORTH);
		Rbuttons.add(Box.createVerticalStrut(5));
		Rbuttons.add(classLoader, BorderLayout.NORTH);
		
		paintArea.add(canvas);

		full.add(colorchooser, BorderLayout.NORTH);
		full.add(paintArea, BorderLayout.CENTER);
		full.add(Lbuttons, BorderLayout.WEST);
		full.add(Rbuttons, BorderLayout.EAST);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1500, 800);
		setTitle("Paint");
		setVisible(true);		
	}

	public GUI() {
		define();
		// paint area event handlers
		canvas.addMouseMotionListener(new MouseMotionListener() {
			@Override
			public void mouseDragged(java.awt.event.MouseEvent e) {
				if (!drawing) {
					if (selectedControl != "") {
						grid.remove(selected);
						endx = e.getX();
						endy = e.getY();
						reModify(selectedControl);
						repaint();
					}
				} else {
					endx = e.getX();
					endy = e.getY();
					modify("resize");
					repaint();
				}
			}

			@Override
			public void mouseMoved(java.awt.event.MouseEvent arg0) {
			}
		});

		canvas.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(java.awt.event.MouseEvent e) {
				if (drawing) {
					drawing = !drawing;
					clearControls();
				}
				if (coloring) {
					coloring = !coloring;
					clearControls();
				}
				if (selected != null) {
					grid.get(grid.indexOf(selected)).deSelect();
					selected.deSelect();		
				}
				State toAdd = new State(grid);
				State current = undoRedo.current();
				
				if (!current.toString().equals(toAdd.toString())) {
					undoRedo.addState(toAdd);
				}
				if (selected != null) {
					grid.get(grid.indexOf(selected)).select();
					selected.select();
				}
			}

			@Override
			public void mousePressed(java.awt.event.MouseEvent e) {

				if (drawing) {
					beginx = e.getX();
					beginy = e.getY();
					modify("intiate");
					repaint();
				} else if (!drawing && !coloring) {
					for (IShape i : grid) {
						Point p = new Point(e.getX(), e.getY());
						if (!i.getClass().equals(Line.class) && i.isSelected()) {
							if (i.getDownControl().pointed(p)) {
								beginx = e.getX();
								beginy = e.getY();
								selectedControl = "Down";
								break;
							} else if (i.getUpControl().pointed(p)) {
								beginx = e.getX();
								beginy = e.getY();
								selectedControl = "Up";
								break;
							} else if (i.getLeftControl().pointed(p)) {
								beginx = e.getX();
								beginy = e.getY();
								selectedControl = "Left";
								break;
							} else if (i.getRightControl().pointed(p)) {
								beginx = e.getX();
								beginy = e.getY();
								selectedControl = "Right";
								break;
							} else if (i.getCenterControl().pointed(p)) {
								beginx = e.getX();
								beginy = e.getY();
								selectedControl = "Center";
								break;
							} else {
								clearControls();
								selectedControl = "";
							}
						} else if (i.getClass().equals(Line.class) && i.isSelected()) {
							beginx = e.getX();
							beginy = e.getY();
							if (i.getPoint1Control().pointed(p)) {
								selectedControl = "P1";
								break;
							} else if (i.getPoint2Control().pointed(p)) {
								selectedControl = "P2";
								break;
							} else if (i.getCenterControl().pointed(p)) {
								selectedControl = "Center";
								break;
							} else {
								clearControls();
								selectedControl = "";
							}
						}
					}
				} else if (!drawing && coloring) {
					Point p = new Point(e.getX(), e.getY());
					for (int i = grid.size() - 1; i >= 0; i--) {
						if (grid.get(i).contains(p)) {
							grid.get(i).setColor(c.getColor());
							repaint();
							break;
						}
					}
				}else {
					clearControls();
					selectedControl = "";
				}
				
			}

			@Override
			public void mouseExited(java.awt.event.MouseEvent e) {
			}

			@Override
			public void mouseEntered(java.awt.event.MouseEvent e) {
			}

			@Override
			public void mouseClicked(java.awt.event.MouseEvent e) {
				if (!drawing && !coloring) {
					Point p = new Point(e.getX(), e.getY());
					for (int i = grid.size() - 1; i >= 0; i--) {
						if (grid.get(i).contains(p)) {
							grid.get(i).select();
							selected = grid.get(i);
							repaint();
							break;
						}
					}
				}
			}
		});

		delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(selected != null){
					grid.remove(selected);
					selected = null;
					undoRedo.addState(new State(grid));
					repaint();
				}
				else {
					 final JPanel panel = new JPanel();
					 JOptionPane.showMessageDialog(panel, "please,select an item first", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		undo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (undoRedo.canUndo()) {
					retain(undoRedo.undo());
				}
				repaint();
			}
		});
		
		redo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (undoRedo.canRedo())
					retain(undoRedo.redo());
				repaint();
			}
		});

		classLoader.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ClassLoaderImp cli = new ClassLoaderImp();
				Pack temp = cli.packer(classes);
				if (temp != null) {
					try {
						IShape kolo = (IShape) temp.getLoaded().getDeclaredConstructor(double.class, double.class, double.class, 
								double.class, Color.class).newInstance(beginx, beginy, beginx, beginy, new Color(255, 255, 255));			
						temp.getButton().addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								int index = buttons.indexOf(e.getSource());
								drawing = true;
							  	choose = index; 
							  	clearControls(); 
							  	selectedControl = "";
							}
						});
						Lbuttons.add(Box.createVerticalStrut(5));
						Lbuttons.add(temp.getButton(), BorderLayout.NORTH);
						Lbuttons.revalidate();
						buttons.add(temp.getButton());
						classes.add(temp.getLoaded());
					} catch (InstantiationException e1) {
						JOptionPane.showMessageDialog(null,  "Make sure the class you want to load is inside a folder called 'shapes' and that it inherit from MShape class.");
					} catch (IllegalAccessException e1) {
						JOptionPane.showMessageDialog(null,  "Make sure the class you want to load is inside a folder called 'shapes' and that it inherit from MShape class.");
					} catch (IllegalArgumentException e1) {
						JOptionPane.showMessageDialog(null,  "Make sure the class you want to load is inside a folder called 'shapes' and that it inherit from MShape class.");
					} catch (InvocationTargetException e1) {
						JOptionPane.showMessageDialog(null,  "Make sure the class you want to load is inside a folder called 'shapes' and that it inherit from MShape class.");
					} catch (NoSuchMethodException e1) {
						JOptionPane.showMessageDialog(null,  "Make sure the class you want to load is inside a folder called 'shapes' and that it inherit from MShape class.");
					} catch (SecurityException e1) {
						JOptionPane.showMessageDialog(null,  "Make sure the class you want to load is inside a folder called 'shapes' and that it inherit from MShape class.");
					}
				} else {
					JOptionPane.showMessageDialog(null,  "Make sure the class you want to load is inside a folder called 'shapes' and that it inherit from Shape class.");
				}
			}
		});
		
		// enable coloring for objects
		brush.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				coloring = true;
			}
		});

		// line intializing button
		line.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				drawing = true;
				choose = -1;
				clearControls();
				selectedControl = "";
			}
		});
		
		// save intializing button
		save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JCheckBox checkbox = new JCheckBox(".json");
				JCheckBox checkbox2 = new JCheckBox(".xml");
				String message = "Choose type saving!";
				Object[] params = {message, checkbox, checkbox2};
				if (JOptionPane.showConfirmDialog(null, params, "Disconnect Products", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					boolean saveJason = checkbox.isSelected();
					boolean saveXML = checkbox2.isSelected();
					if (saveJason || saveXML) {
						JFileChooser fileChooser = new JFileChooser();
						fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
						fileChooser.setAcceptAllFileFilterUsed(false);
						int returnVal = fileChooser.showOpenDialog(null);
						if(returnVal == JFileChooser.APPROVE_OPTION) {
							if (selected != null) {
								grid.get(grid.indexOf(selected)).deSelect();
								selected.deSelect();
							}
							if (saveJason) {
								StringBuilder name = new StringBuilder();
								name.append("/");
								name.append(JOptionPane.showInputDialog("Name of the .json file without extension"));
								name.append(".json");
								saveLoad.saveJson(new State(grid), fileChooser.getSelectedFile() + name.toString());								
							}
							if (saveXML) {
								StringBuilder name = new StringBuilder();
								name.append("/");
								name.append(JOptionPane.showInputDialog("Name of the .xml file without extension"));
								name.append(".xml");
								saveLoad.saveXML(new State(grid), fileChooser.getSelectedFile() + name.toString());
							}
						}
					}					
				}
			}
		});

		// load intializing button
		load.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Json & XML Files", "json", "xml");
				fileChooser.setFileFilter(filter);
				int returnVal = fileChooser.showOpenDialog(null);
				if(returnVal == JFileChooser.APPROVE_OPTION) {
					if (filter.accept(fileChooser.getSelectedFile())) {
						String fileName = fileChooser.getSelectedFile().getName();
						String extension = fileName.substring(fileName.lastIndexOf("."),fileName.length());
						try {
							if (extension.equals(".json")) {
								State load = saveLoad.loadJson(fileChooser.getSelectedFile(), classes);
								if (load != null) {
									retain(load);
									undoRedo.addState(new State(grid));
								}
							} else if (extension.equals(".xml")) {
								State load = saveLoad.loadXML(fileChooser.getSelectedFile(), classes);
								if (load != null) {
									retain(load);
									undoRedo.addState(new State(grid));	
								}
							}
						} catch (Exception e1) {
							JOptionPane.showMessageDialog(null, "Make sure the classes needed are loaded.");
						}
					} else {
						JOptionPane.showMessageDialog(null, "File extension is not correct");
					}
				}
				repaint();
			}
		});
	}

	/**
	 * Sets the selected object.
	 *
	 * @param selected selected object by the user.
	 */
	public void setSelected(IShape selected) {
		this.selected = selected;
	}
	
	/**
	 * Clear pixel controls.
	 */
	private void clearControls() {
		for (IShape i : grid) {
			i.deSelect();			
		}
		selected = null;
		repaint();
	}

	/**
	 * initialize and modify dimensions of drawn object.
	 *
	 * @param ch the name of shape user choose to draw.
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	private void modify(String ch) {
		try {
			if (ch.equals("intiate")) {
				if (choose == -1) {
					grid.add(new Line(beginx, beginy, beginx, beginy, Color.black));				
				} else {
					grid.add((IShape) classes.get(choose).getDeclaredConstructor(double.class, double.class, double.class, 
							double.class, Color.class).newInstance(beginx, beginy, beginx, beginy, new Color(255, 255, 255)));
				}
			} else if (ch.equals("resize")) {
				grid.remove(grid.size() - 1);
				if (choose == -1) {
					grid.add(new Line(beginx, beginy, endx, endy, Color.BLACK));				
				} else {
					grid.add((IShape) classes.get(choose).getDeclaredConstructor(double.class, double.class, double.class, 
							double.class, Color.class).newInstance(beginx, beginy, endx, endy, new Color(255, 255, 255)));
				}
			}
		} catch (InstantiationException e) {
			JOptionPane.showMessageDialog(null, "Class is not compatible with parameters");
		} catch (IllegalAccessException e) {
			JOptionPane.showMessageDialog(null, "Illegal access exception");
		} catch (IllegalArgumentException e) {
			JOptionPane.showMessageDialog(null, "Class is not compatible with parameters");
		} catch (InvocationTargetException e) {
			JOptionPane.showMessageDialog(null, "Class is not compatible with parameters");
		} catch (NoSuchMethodException e) {
			JOptionPane.showMessageDialog(null, "Class is not compatible with parameters");
		} catch (SecurityException e) {
			JOptionPane.showMessageDialog(null, "Security Exception");
		}
	}

	/**
	 * applies motion modification on object.
	 *
	 * @param position position of pressed pixel control.
	 */
		private void reModify(String position) {
			// center control pixel
			if (position == "Center")
				drag();
			// left control pixel
			else if (position == "Left")
				shiftL();
			// right control pixel
			else if (position == "Right")
				shiftR();
			// down control pixel
			else if (position == "Down")
				shiftD();
			// up control pixel
			else if (position == "Up")
				shiftU();
			else if (position == "P1") {
				shiftP1();
			} else if (position == "P2") {
				shiftP2();
			}
			// set object selected and push it in grid
			selected.select();
			grid.add(selected);
		}
		
		/**
		 * change position of line first point.
		 */
		private void shiftP1(){
			setSelected(selected.movePoint1(endx, endy));
			
		}

		/**
		 * change position of line second point.
		 */
		private void shiftP2(){
			setSelected(selected.movePoint2(endx, endy));
		}

		/**
		 * Move selected object to another location on paint area.
		 */
		private void drag() {
			setSelected(selected.drag(endx, endy));
		}

		/**
		 * Shift selected object to the left.
		 */
		private void shiftL() {
			setSelected(selected.changeWidthLeft(endx));
		}

		/**
		 * Shift selected object to the right.
		 */
		private void shiftR() {
			setSelected(selected.changeWidthRight(endx));
		}

		/**
		 * Shift selected object downward.
		 */
		private void shiftD() {
			setSelected(selected.changeWidthDown(endy));
		}

		/**
		 * Shift selected object upward.
		 */
		private void shiftU() {
			setSelected(selected.changeWidthUp(endy));
		}

	/**
	 * release current state of application and change it to another state .
	 *
	 * @param s state required to be override the current state of paint.
	 */
	private void retain(State s) {
		grid = new ArrayList<IShape>(s.getGrid());
		selected = null;
		selectedControl = "";
		choose = -1;
		coloring = false;
		drawing = false;
	}

	/**
	 * paint area for drawing with 2d graphics to draw on jpanel.
	 */ 
	private class DrawCanvas extends JPanel {
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g); // paint parent's background
			Graphics2D g2 = (Graphics2D) g;
			for (IShape i : grid) {
				i.draw(g2);
			}
		}
	}
	
	/**
	 * instantiate java paint application.
	 *
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new GUI();
			}
		});
	}

}