import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.*;
import java.io.File;
import java.util.*;



public class ImagenBean {

	String iconpath;
	String comentario;
	int alto;
	int ancho;


	ImagenBean (String ruta, int alto, int ancho) {

		iconpath = ruta;
		comentario = "Comenta algo...";
		this.alto = alto;
		this.ancho = ancho;
	}


	String getIcon () {
		return iconpath;
	}

	void setIcon (String iconpath) {
		this.iconpath = iconpath;
	}

	String getComentario () {
		return comentario;
	}

	void SetComentario (String comentario) {
		this.comentario = comentario;
	}

	int getAlto () {
		return alto;
	}

	void setAlto (int alto) {
		this.alto = alto;
	}

	int getAncho () {
		return ancho;
	}
	void setAncho (int ancho) {
		this.ancho = ancho;
	}

}