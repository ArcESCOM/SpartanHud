/*

F:
cd F:\Hellvetica\ArcViewer
javac ListFiles.java
javac ImagenBean.java
javac SlideShow.java
javac ArcViewer.java

java ArcViewer

*/


import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
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
	
	
	public int returnChooser;
	public ArrayList<String> imagenes;
	public ArrayList<ImagenBean> imagenesbean = new ArrayList<ImagenBean>();
	ListFiles lista = new ListFiles();
	
	int indexaux = 0;
	int posicion = -2;
	
	Color colorGris = new Color(0x202020);


	SlideShow slide = new SlideShow();


	public void init() {
		
		areaventana = new JPanel();
		areabotones = new JPanel();
		imagen = new JLabel("");
		presentacion = new JButton("Diapositiva");
		siguiente = new JButton("next");
		atras = new JButton("previous");
		zoom = new JSlider();
		carpeta = new JButton("Seleccionar Carpeta");
		

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
		presentacion.addActionListener(this);
		carpeta.addActionListener(this);

		this.setLayout (new BorderLayout());
		add(areaventana, BorderLayout.CENTER);
		add(areabotones, BorderLayout.SOUTH);

		/* Abre el selector desde que inicia el programa */

		chooser = new JFileChooser();
		chooser.setDialogTitle("Holap");
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.setAcceptAllFileFilterUsed(false);
		

		//Abrir archivo es de acá...
		returnChooser = chooser.showOpenDialog(ArcViewer.this);
		imagenes = lista.Miranda(chooser, returnChooser);
		//Esto hace magia
		for (int asd1=0; asd1 < imagenes.size(); asd1++) {
			imagenesbean.add(new ImagenBean(imagenes.get(asd1),0,0));
		}
		String getImgSelected = chooser.getSelectedFile().getPath();
		for (int index=0; index < imagenesbean.size(); index++) {
			if (getImgSelected.equals( imagenesbean.get(index).getIcon() )) {
				imagen.setIcon(new ImageIcon(imagenesbean.get(index).getIcon()));
				indexaux = index;
			}
		}
		//Hasta acá...



	}






	public void actionPerformed (ActionEvent event) {

		if (posicion == -2)
			posicion = indexaux;



		//Abrir nueva imagen
		if (event.getSource() == carpeta) {			
			//Se repite lo de abrir archivo + IMPORTANTE -> limpiar lista anterior
			imagenesbean.clear();
			imagenes.clear();
			returnChooser = chooser.showOpenDialog(ArcViewer.this);
			imagenes = lista.Miranda(chooser, returnChooser);
			for (int asd1=0; asd1 < imagenes.size(); asd1++) {
				imagenesbean.add(new ImagenBean(imagenes.get(asd1),0,0));
			}
			String getImgSelected = chooser.getSelectedFile().getPath();
			for (int index=0; index < imagenesbean.size(); index++) {
				if (getImgSelected.equals( imagenesbean.get(index).getIcon() )) {
					imagen.setIcon(new ImageIcon(imagenesbean.get(index).getIcon()));
					indexaux = index;
				}
			}
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



		//Presentacion
		if (event.getSource() == presentacion) {

			if (presentacion.getText() == "Detener") {
				slide.detener();
				posicion = slide.getPosicion();
				presentacion.setText("Diapositiva");
				return;
			}

			if (presentacion.getText() == "Diapositiva") {
				slide.setTodo(posicion, imagen, imagenesbean);
				new Thread (slide,"prueba").start();
				presentacion.setText("Detener");
				return;
			}
		}
	

	}





	public ArcViewer() {


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