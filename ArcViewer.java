import javax.swing.*;
import java.awt.*;
import java.io.*;

import java.awt.event.*;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.*;

public class ArcViewer extends JPanel implements ActionListener {

	
	private JPanel areaventana;
	private JPanel areabotones;
	private JLabel imagen;

	private JButton presentacion;
	private JButton siguente;
	private JButton atras;

	private JSlider zoom;

	private JButton carpeta;
	private JFileChooser chooser;

	private JMenuBar menu;


	Color colorGris = new Color(0x202020);





	public void init() {
		
		areaventana = new JPanel();
		areabotones = new JPanel();
		imagen = new JLabel("Aca va la imagen");

		presentacion = new JButton("play");
		siguente = new JButton("next");
		atras = new JButton("previous");

		zoom = new JSlider();

		carpeta = new JButton("Seleccionar Carpeta");
		carpeta.addActionListener(this);



		chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);


		/* Agregando los componentes */

		
		areaventana.setBackground(colorGris);
		areaventana.add(imagen);


		areabotones.add(presentacion);
		areabotones.add(siguente);
		areabotones.add(atras);
		areabotones.add(zoom);
		areabotones.add(carpeta);


		this.setLayout (new BorderLayout()); //layout para todo el programa
		add(areaventana, BorderLayout.CENTER); //layout solo para el area de muestra de imagen
		add(areabotones, BorderLayout.SOUTH); //layout para todos los botones
	}





	public void actionPerformed (ActionEvent event) {

		if (event.getSource() == carpeta) {
			int returnVal = chooser.showOpenDialog(ArcViewer.this);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = chooser.getSelectedFile(); }
			else { }
		}
	

	}





	public ArcViewer() {

		init(); 
		//start();

		JFrame frame = new JFrame("ArcViewer v.0.1");
			
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //Cierra el programa por la buena

		frame.add("Center", this);
		frame.setSize(900, 600);
		frame.setVisible(true);
	}


	public static void main(String[] args) {
		new ArcViewer();
	}
}