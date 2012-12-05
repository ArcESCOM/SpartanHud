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
	private JButton carpeta;
	private JButton grid;
	private JButton bcomentario;
	
	private JSlider zoom;
	private JFileChooser chooser;
	private JScrollBar desplazamiento;
	
	public int returnChooser;
	
	public ArrayList<String> imagenes;
	public ArrayList<ImagenBean> imagenesbean = new ArrayList<ImagenBean>();



	JButton imgButtonArray[];
	JButton imgButtonNoArray;

	

	int indexaux = 0;
	int posicion = -2;
	Color colorGris = new Color(0x202020);
	Color colorBlue = new Color(0x00B7FF);
	Color color111 = new Color(0x111111);

	ListFiles lista = new ListFiles();
	SlideShow slide = new SlideShow();


	ImageIcon imgPlay = new ImageIcon("play.png");
	ImageIcon imaPausa = new ImageIcon("pause.png");
	ImageIcon imgSiguiente = new ImageIcon("siguiente.png");
	ImageIcon imgAnterior = new ImageIcon("anterior.png");
	ImageIcon imgNuevaCarpeta = new ImageIcon("import.png");
	ImageIcon imgGrid = new ImageIcon("grid.png");
	ImageIcon imgComentario = new ImageIcon("comment.png");


	public void init() {
		
		areaventana = new JPanel();
		areabotones = new JPanel();
		imagen = new JLabel("");
		presentacion = new JButton(imgPlay);
		siguiente = new JButton(imgSiguiente);
		atras = new JButton(imgAnterior);
		zoom = new JSlider();
		carpeta = new JButton(imgNuevaCarpeta);
		grid = new JButton(imgGrid);
		desplazamiento = new JScrollBar();
		bcomentario = new JButton(imgComentario);




		/* Agregando los componentes */

		areaventana.add(imagen);
		areaventana.add(desplazamiento);

		areabotones.add(grid);
		areabotones.add(atras);
		areabotones.add(presentacion);
		areabotones.add(siguiente);
		areabotones.add(zoom);
		areabotones.add(carpeta);
		areabotones.add(bcomentario);

		areabotones.setBackground(colorGris);
		areaventana.setBackground(colorGris);

		desplazamiento.setVisible(false);




		/* GUI GUI GUI GUI  */
		presentacion.setBackground(colorGris);
		atras.setBackground(colorGris);
		siguiente.setBackground(colorGris);
		carpeta.setBackground(colorGris);
		grid.setBackground(colorGris);
		zoom.setBackground(colorGris);
		bcomentario.setBackground(colorGris);

		presentacion.setPreferredSize(new Dimension(50, 50));
		atras.setPreferredSize(new Dimension(50, 50));
		siguiente.setPreferredSize(new Dimension(50, 50));
		carpeta.setPreferredSize(new Dimension(50, 50));
		grid.setPreferredSize(new Dimension(50, 50));
		bcomentario.setPreferredSize(new Dimension(50, 50));

		grid.setFocusPainted(false);
		atras.setFocusPainted(false);
		siguiente.setFocusPainted(false);
		carpeta.setFocusPainted(false);
		presentacion.setFocusPainted(false);
		bcomentario.setFocusPainted(false);
	
		grid.setBorder(null);
		atras.setBorder(null);
		siguiente.setBorder(null);
		carpeta.setBorder(null);
		presentacion.setBorder(null);
		desplazamiento.setBorder(null);
		areabotones.setBorder(null);
		areaventana.setBorder(null);
		bcomentario.setBorder(null);
		/* GUI GUI GUI GUI  */




		/* Action Listeners */
		siguiente.addActionListener(this);
		atras.addActionListener(this);
		presentacion.addActionListener(this);
		carpeta.addActionListener(this);
		grid.addActionListener(this);
		bcomentario.addActionListener(this);



		this.setLayout (new BorderLayout());
		add(areaventana, BorderLayout.CENTER);
		add(areabotones, BorderLayout.SOUTH);



		siguiente.setEnabled(false);
		atras.setEnabled(false);
		presentacion.setEnabled(false);
		grid.setEnabled(false);
		bcomentario.setEnabled(false);

		/* Abre el selector desde que inicia el programa */

		chooser = new JFileChooser();
		chooser.setDialogTitle("Selecciona una imagen...");
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.setAcceptAllFileFilterUsed(false);

		//Abrir archivo es de acá...
		returnChooser = chooser.showOpenDialog(ArcViewer.this);

		if (returnChooser == 0) {
			imagenes = lista.Miranda(chooser, returnChooser);
			siguiente.setEnabled(true);
			atras.setEnabled(true);
			presentacion.setEnabled(true);
			grid.setEnabled(true);
			bcomentario.setEnabled(true);

		}

		// imagenes = lista.Miranda(chooser, returnChooser);
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

			imagen.setIcon(ajusteImg(new ImageIcon(imagenesbean.get(posicion).getIcon()),
				imagenesbean.get(posicion).getAncho(),
				imagenesbean.get(posicion).getAlto(),
				areaventana.getWidth()-50, areaventana.getHeight()
				));
		}



		//Imagen anterior
		if (event.getSource() == atras) {
			posicion--;
			if (posicion == -1) {
				posicion = imagenesbean.size()-1;	}

			imagen.setIcon(ajusteImg(new ImageIcon(imagenesbean.get(posicion).getIcon()),
				imagenesbean.get(posicion).getAncho(),
				imagenesbean.get(posicion).getAlto(),
				areaventana.getWidth()-50, areaventana.getHeight()
				));
		}



		//Presentacion iniciar/detener
		if (event.getSource() == presentacion) {

			if (presentacion.getIcon() == imaPausa) {
				slide.detener();
				posicion = slide.getPosicion();
				presentacion.setIcon(imgPlay);
				return;
			}

			if (presentacion.getIcon() == imgPlay) {
				slide.setTodo(posicion, imagen, imagenesbean);
				new Thread (slide,"prueba").start();
				presentacion.setIcon(imaPausa);
				return;
			}
		}

		
		//Modo rejilla
		if (event.getSource() == grid) {

			imagen.setVisible(false);
			siguiente.setVisible(false);
			atras.setVisible(false);
			presentacion.setVisible(false);
			grid.setVisible(false);
			bcomentario.setVisible(false);
			
			imgButtonNoArray = new JButton();

			for (int aux1 = 0; aux1 < imagenesbean.size(); aux1++) {
				
				areaventana.add(imgButtonNoArray = new JButton());

				// System.out.println(imagenesbean.get(aux1).getAncho() + "," +  imagenesbean.get(aux1).getAlto());

				imgButtonNoArray.setIcon(ajusteImg(
					new ImageIcon(imagenesbean.get(aux1).getIcon()),
					imagenesbean.get(aux1).getAncho(),
					imagenesbean.get(aux1).getAlto(),
					250, 250));


				imgButtonNoArray.setName("IMAGEN");
				imgButtonNoArray.setPreferredSize(new Dimension(200, 200));
				imgButtonNoArray.addActionListener(this);

			}			
		}		




		//Comentario
		if (event.getSource() == bcomentario) {
			new Comentario(imagenesbean.get(posicion), posicion);






		// JButton botonaux = (JButton)event.getSource();

		// System.out.println(botonaux.getBackground());
		// System.out.println(color111);

		// if (botonaux.getBackground() == color111) {
		// 	System.out.println("ASDASDSASDAASD");
		// 	siguiente.setEnabled(false);
		// 	atras.setEnabled(false);
		// 	presentacion.setEnabled(false);
		// 	grid.setEnabled(false);

		// }






		}
	}





	public ArcViewer() {

		JFrame frame = new JFrame("ArcViewer v.0.1");			
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		frame.add("Center", this);
		frame.setSize(900, 600);
		frame.setVisible(true);

		init();
	
	}





	public static void main(String[] args) {

		new ArcViewer();
	
	}





	ImageIcon ajusteImg(ImageIcon img, int ancho, int alto, int contenedorx, int contenedory) {

		contenedory = contenedorx;
		Image aux_img;
		aux_img = img.getImage();

		//Si la imagen mide mas que el contenedor, en el lado que sea

		if (ancho >= contenedorx ||  alto >= contenedorx || alto >= contenedory || ancho >= contenedory) {

			if (contenedorx >= contenedory) {
				contenedorx = contenedory;
			}
			if (contenedory > contenedorx) {
				contenedory = contenedorx;
			}

			if (ancho >= alto) {
				Image newimg = aux_img.getScaledInstance(contenedorx*ancho/alto, contenedory, java.awt.Image.SCALE_SMOOTH);
				img = new ImageIcon(newimg);
				return img;
			}
			else {
				Image newimg = aux_img.getScaledInstance(contenedory*alto/ancho, contenedory, java.awt.Image.SCALE_SMOOTH);
				
				img = new ImageIcon(newimg);
				return img;
			}			
		}
		return img;
	}



	// ImageIcon ajusteImg(ImageIcon img, int ancho, int alto, int contenedorx, int contenedory) {

	// 	contenedory = contenedorx;
	// 	Image aux_img;
	// 	aux_img = img.getImage();


	// 	if (ancho >= contenedorx || alto >= contenedory) {
			
	// 	}

	// 	if (contenedorx >= contenedory) {
	// 		contenedorx = contenedory; }
	// 	if (contenedory > contenedorx) {
	// 		contenedory = contenedorx;
	// 	}

	// 	if (ancho >= alto) {
	// 		Image newimg = aux_img.getScaledInstance(contenedorx, contenedory*alto/ancho, java.awt.Image.SCALE_SMOOTH);
	// 		img = new ImageIcon(newimg);
	// 		return img;
	// 	}
	// 	else {
	// 		Image newimg = aux_img.getScaledInstance(contenedorx*ancho/alto, contenedory, java.awt.Image.SCALE_SMOOTH);
	// 		img = new ImageIcon(newimg);
	// 		return img;
	// 	}
	// }



}