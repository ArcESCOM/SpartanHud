import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.*;
import java.io.File;
import java.util.*;


class SlideShow  implements Runnable {

	boolean comparador = true;
	Thread slideshow;
	int timesleep = 2000;
	int posicion;
	JLabel imagen;
	ArrayList<ImagenBean> imagenesbean;
	ImageIcon hola = new ImageIcon("default.jpg");
	ajustaImagen ajustar = new ajustaImagen();
	JPanel areaventana;
	JSlider ptiempo;

	public void setTodo(int posicion, JLabel imagen, ArrayList<ImagenBean> imagenesbean, JPanel areaventana, JSlider ptiempo) {
		this.comparador = true;
		this.posicion = posicion;
		this.imagen = imagen;
		this.imagenesbean = new ArrayList<ImagenBean>(imagenesbean);
		this.areaventana = areaventana;
		this.ptiempo = ptiempo;
	}

	public void run () {

		while (comparador == true) {

			timesleep = ptiempo.getValue()*50;
			posicion++;
			
			if (posicion >= imagenesbean.size()) {
				posicion = 0;
			}

			//imagen.setIcon(new ImageIcon(imagenesbean.get(posicion).getIcon()));

			imagen.setIcon(ajustar.ajusteImg(new ImageIcon(imagenesbean.get(posicion).getIcon()),
				imagenesbean.get(posicion).getAncho(),
				imagenesbean.get(posicion).getAlto(),
				areaventana.getWidth()-50, areaventana.getHeight()
				));

			try	{
				System.out.println(posicion);
				slideshow.sleep(timesleep);
			}
			catch (Exception e) {
				return;
			}
		}
	}


	public void detener () {
		this.comparador = false; 
	}


	public int getPosicion () {
		return posicion;
	}
}