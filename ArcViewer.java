/*

F:
cd F:\Hellvetica\ArcViewer
javac ListFiles.java
javac ArcViewer.java
javac ImagenBean.java
java ArcViewer

*/


import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.*;
import java.io.File;
import java.util.*;




public class ArcViewer extends JPanel implements ActionListener {

	
	private JPanel areaventana;
	private JPanel areabotones;
	private JLabel imagen;

	private JButton presentacion;
	private JButton siguiente;
	private JButton atras;

	private JSlider zoom;
	private JButton carpeta;
	private JFileChooser chooser;
	private JMenuBar menu;
	int posicion=-2;
	
	public int returnChooser;
	Color colorGris = new Color(0x202020);


	public ArrayList<String> imagenes;
	ListFiles lista = new ListFiles();
	ImagenBean Iaux;

	public ArrayList<ImagenBean> imagenesbean = new ArrayList<ImagenBean>();
	
	int indexaux = 0;

	







	public void init() {
		
		areaventana = new JPanel();
		areabotones = new JPanel();
		imagen = new JLabel("");
		presentacion = new JButton("play");
		siguiente = new JButton("next");
		atras = new JButton("previous");
		zoom = new JSlider();
		carpeta = new JButton("Seleccionar Carpeta");
		carpeta.addActionListener(this);

		/* Agregando los componentes */

		
		areaventana.setBackground(colorGris);
		areaventana.add(imagen);
		areabotones.add(presentacion);
		areabotones.add(atras);
		areabotones.add(siguiente);
		areabotones.add(zoom);
		areabotones.add(carpeta);
		siguiente.addActionListener(this);
		atras.addActionListener(this);
		this.setLayout (new BorderLayout()); //layout para todo el programa
		add(areaventana, BorderLayout.CENTER); //layout solo para el area de muestra de imagen
		add(areabotones, BorderLayout.SOUTH); //layout para todos los botones

		/* Abre el selector desde que inicia el programa */

		chooser = new JFileChooser();
		chooser.setDialogTitle("Holap");
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.setAcceptAllFileFilterUsed(false);
		returnChooser = chooser.showOpenDialog(ArcViewer.this);
		imagenes = lista.Miranda(chooser, returnChooser);


		//Esto hace magia
		for (int asd1=0; asd1 < imagenes.size(); asd1++) {
			imagenesbean.add(new ImagenBean(imagenes.get(asd1),0,0));
		}
		// imagen.setIcon(new ImageIcon(imagenesbean.get(0).getIcon()));



		String getImgSelected = chooser.getSelectedFile().getPath();
		
		for (int index=0; index < imagenesbean.size(); index++) {
			if (getImgSelected.equals( imagenesbean.get(index).getIcon() )) {
				imagen.setIcon(new ImageIcon(imagenesbean.get(index).getIcon()));
				indexaux = index;
			}
		}








	}



	public void actionPerformed (ActionEvent event) {

		if(posicion == -2)
			posicion = indexaux;


		//Abrir nueva imagen
		if (event.getSource() == carpeta) {
			
			for (int asd1=0; asd1 < imagenes.size(); asd1++) {
				imagenesbean.add(new ImagenBean(imagenes.get(asd1),0,0)); }
			imagen.setIcon(new ImageIcon(imagenesbean.get(posicion).getIcon()));
		}
		//Imagen siguiente
		if (event.getSource() == siguiente) {
			posicion++;
			if (posicion >= imagenesbean.size()) {
				posicion = 0; } 
			imagen.setIcon(new ImageIcon(imagenesbean.get(posicion).getIcon()));
		}
		//Imagen anterior
		if (event.getSource() == atras) {
			posicion--;
			if (posicion == -1) {
				posicion = imagenesbean.size()-1;	}
			imagen.setIcon(new ImageIcon(imagenesbean.get(posicion).getIcon()));
		}
	

	}





	public ArcViewer() {

		//start();

		JFrame frame = new JFrame("ArcViewer v.0.1");
			
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //Cierra el programa por la buena

		frame.add("Center", this);
		frame.setSize(900, 600);
		frame.setVisible(true);

		init(); 
	}


	public static void main(String[] args) {
		new ArcViewer();
	}
}