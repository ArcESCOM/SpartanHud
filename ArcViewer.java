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

	
	public JPanel areaventana;
	public JPanel areabotones;
	private JLabel imagen;

	private JButton presentacion;
	private JButton siguiente;
	private JButton atras;
	private JButton carpeta;
	private JButton grid;
	private JButton bcomentario;
	private JButton zoom;
	
	private JSlider ptiempo;
	private JFileChooser chooser;
	//private JScrollPane desplazamiento;
	
	public int returnChooser;
	
	public ArrayList<String> imagenes;
	public ArrayList<ImagenBean> imagenesbean = new ArrayList<ImagenBean>();

	ajustaImagen ajustar = new ajustaImagen();


	JButton imgButtonArray[];

	
	boolean corrobora =false;
	boolean isPresentacion = false;
	int indexaux = 0;
	int posicion = -2;
	Color colorGris = new Color(0x202020);
	Color colorBlue = new Color(0x00B7FF);
	Color color111 = new Color(0x111111);

	ListFiles lista = new ListFiles();
	SlideShow slide = new SlideShow();


	ImageIcon imgPlay = new ImageIcon("play.png");
	ImageIcon imgPausa = new ImageIcon("pause.png");
	ImageIcon imgSiguiente = new ImageIcon("siguiente.png");
	ImageIcon imgAnterior = new ImageIcon("anterior.png");
	ImageIcon imgNuevaCarpeta = new ImageIcon("import.png");
	ImageIcon imgGrid = new ImageIcon("grid.png");
	ImageIcon imgComentario = new ImageIcon("comment.png");
	ImageIcon imgZoom = new ImageIcon("zoom.png");


	public void init() {
		
		areaventana = new JPanel();
		areabotones = new JPanel();
		imagen = new JLabel("");
		presentacion = new JButton(imgPlay);
		siguiente = new JButton(imgSiguiente);
		atras = new JButton(imgAnterior);
		ptiempo = new JSlider();
		carpeta = new JButton(imgNuevaCarpeta);
		grid = new JButton(imgGrid);
		bcomentario = new JButton(imgComentario);
		zoom = new JButton(imgZoom);



		/* Agregando los componentes */

		areaventana.add(imagen);
		//areaventana.add(desplazamiento);

		areabotones.add(grid);
		areabotones.add(atras);
		areabotones.add(presentacion);
		areabotones.add(siguiente);
		areabotones.add(ptiempo);
		areabotones.add(carpeta);
		areabotones.add(bcomentario);
		areabotones.add(zoom);

		areabotones.setBackground(colorGris);
		areaventana.setBackground(colorGris);

		//desplazamiento.setVisible(false);
		//areaventana.add(desplazamiento); 



		/* GUI GUI GUI GUI  */
		presentacion.setBackground(colorGris);
		atras.setBackground(colorGris);
		siguiente.setBackground(colorGris);
		carpeta.setBackground(colorGris);
		grid.setBackground(colorGris);
		ptiempo.setBackground(colorGris);
		bcomentario.setBackground(colorGris);
		zoom.setBackground(colorGris);

		presentacion.setPreferredSize(new Dimension(100, 80));
		atras.setPreferredSize(new Dimension(100, 80));
		siguiente.setPreferredSize(new Dimension(100, 80));
		carpeta.setPreferredSize(new Dimension(100, 80));
		grid.setPreferredSize(new Dimension(100, 80));
		bcomentario.setPreferredSize(new Dimension(100, 80));
		zoom.setPreferredSize(new Dimension(100, 80));

		grid.setFocusPainted(false);
		atras.setFocusPainted(false);
		siguiente.setFocusPainted(false);
		carpeta.setFocusPainted(false);
		presentacion.setFocusPainted(false);
		bcomentario.setFocusPainted(false);
		zoom.setFocusPainted(false);
	
		grid.setBorder(null);
		atras.setBorder(null);
		siguiente.setBorder(null);
		carpeta.setBorder(null);
		presentacion.setBorder(null);
		areabotones.setBorder(null);
		areaventana.setBorder(null);
		bcomentario.setBorder(null);
		zoom.setBorder(null);
		/* GUI GUI GUI GUI  */




		/* Action Listeners */
		siguiente.addActionListener(this);
		atras.addActionListener(this);
		presentacion.addActionListener(this);
		carpeta.addActionListener(this);
		grid.addActionListener(this);
		bcomentario.addActionListener(this);
		zoom.addActionListener(this);



		this.setLayout (new BorderLayout());
		add(areaventana, BorderLayout.CENTER);
		add(areabotones, BorderLayout.SOUTH);


		ptiempo.setVisible(false);

		siguiente.setEnabled(false);
		atras.setEnabled(false);
		presentacion.setEnabled(false);
		grid.setEnabled(false);
		bcomentario.setEnabled(false);
		zoom.setEnabled(false);

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
			zoom.setEnabled(true);

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

		else {
			System.out.println("No Selection");
			carpeta.setEnabled(true);
		}






		// for (int asd1=0; asd1 < imagenes.size(); asd1++) {
		// 	imagenesbean.add(new ImagenBean(imagenes.get(asd1),0,0));
		// }
		// String getImgSelected = chooser.getSelectedFile().getPath();
		// for (int index=0; index < imagenesbean.size(); index++) {
		// 	if (getImgSelected.equals( imagenesbean.get(index).getIcon() )) {
		// 		imagen.setIcon(new ImageIcon(imagenesbean.get(index).getIcon()));
		// 		indexaux = index;
		// 	}
		// }

		
	}






	public void actionPerformed (ActionEvent event) {



		
		if (posicion == -2)
			posicion = indexaux;



		//Abrir nueva imagen
		if (event.getSource() == carpeta) {

			siguiente.setEnabled(true);
			atras.setEnabled(true);
			presentacion.setEnabled(true);
			grid.setEnabled(true);
			bcomentario.setEnabled(true);
			zoom.setEnabled(true);

			imagenesbean.clear();

			if (imagenes != null) {
				imagenes.clear();
			}

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

			imagen.setIcon(ajustar.ajusteImg(new ImageIcon(imagenesbean.get(posicion).getIcon()),
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

			imagen.setIcon(ajustar.ajusteImg(new ImageIcon(imagenesbean.get(posicion).getIcon()),
				imagenesbean.get(posicion).getAncho(),
				imagenesbean.get(posicion).getAlto(),
				areaventana.getWidth()-50, areaventana.getHeight()
				));
		}



		//Presentacion iniciar/detener
		if (event.getSource() == presentacion) {


			if (isPresentacion == false) {
				grid.setVisible(false);
				atras.setVisible(false);
				siguiente.setVisible(false);
				carpeta.setVisible(false);
				bcomentario.setVisible(false);
				zoom.setVisible(false);
				ptiempo.setVisible(true);	
			}

			if (isPresentacion == true) {
				grid.setVisible(true);
				atras.setVisible(true);
				siguiente.setVisible(true);
				carpeta.setVisible(true);
				bcomentario.setVisible(true);
				zoom.setVisible(true);
				ptiempo.setVisible(false);
			}


			if (presentacion.getIcon() == imgPausa) {
				slide.detener();
				posicion = slide.getPosicion();
				presentacion.setIcon(imgPlay);
				isPresentacion = false;
				return;
			}

			if (presentacion.getIcon() == imgPlay) {
				slide.setTodo(posicion, imagen, imagenesbean, areaventana, ptiempo);
				new Thread (slide,"prueba").start();
				presentacion.setIcon(imgPausa);
				isPresentacion = true;
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
			zoom.setVisible(false);
			carpeta.setVisible(false);
			ptiempo.setVisible(false);

			//desplazamiento.setVisible(true);  

			if(corrobora==true){
				for(int celular=0; celular<imgButtonArray.length; celular++){
					imgButtonArray[celular].setVisible(true);
				}
				
			}

			if(corrobora == false){
				imgButtonArray = new JButton[imagenesbean.size()];

				for(int goku=0; goku<imagenesbean.size(); goku++){
					areaventana.add(imgButtonArray[goku] = new JButton());
					imgButtonArray[goku].setPreferredSize(new Dimension(200,200));
					imgButtonArray[goku].addActionListener(this);
					imgButtonArray[goku].setBackground(colorGris);
					imgButtonArray[goku].setIcon(ajustar.ajusteCuadrado(new ImageIcon(imagenesbean.get(goku).getIcon())));
					corrobora=true;
				
				}
			}
		}		




		//Comentario
		if (event.getSource() == bcomentario) {
			new Comentario(imagenesbean.get(posicion), posicion);
		}





		//Cuando se apreta un boton de la rejilla
		if (corrobora == true) {
			for(int alice=0; alice<imgButtonArray.length; alice++){
				if(event.getSource() == imgButtonArray[alice]){
					for(int wonderland=0; wonderland<imgButtonArray.length; wonderland++){
						imgButtonArray[wonderland].setVisible(false);
					}
					posicion=alice;
					
					imagen.setIcon(ajustar.ajusteImg(new ImageIcon(imagenesbean.get(posicion).getIcon()),
					imagenesbean.get(posicion).getAncho(),
					imagenesbean.get(posicion).getAlto(),
					areaventana.getWidth()-50, areaventana.getHeight()
					));

					imagen.setVisible(true);
					siguiente.setVisible(true);
					atras.setVisible(true);
					presentacion.setVisible(true);
					grid.setVisible(true);
					bcomentario.setVisible(true);
					zoom.setVisible(true);
					carpeta.setVisible(true);

				}
			}
		}





		if (event.getSource() == zoom) {
			System.out.println("Haciendo un ZOOOOOOOOOOOOM");
		}



			
	}





	public ArcViewer() {

		JFrame frame = new JFrame("ArcViewer v.0.9");			
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		frame.add("Center", this);
		frame.setSize(900, 600);
		frame.setVisible(true);

		init();
	
	}





	public static void main(String[] args) {

		new ArcViewer();
	
	}



}