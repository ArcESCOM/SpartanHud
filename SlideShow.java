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

	public void setTodo(int posicion, JLabel imagen, ArrayList<ImagenBean> imagenesbean) {
		this.comparador = true;
		this.posicion = posicion;
		this.imagen = imagen;
		this.imagenesbean = new ArrayList<ImagenBean>(imagenesbean);
	}

	public void run () {

		while (comparador == true) {


			posicion++;
			
			if (posicion >= imagenesbean.size()) {
				posicion = 0;
			}

			imagen.setIcon(new ImageIcon(imagenesbean.get(posicion).getIcon()));

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