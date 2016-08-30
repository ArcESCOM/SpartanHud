import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.*;
import java.io.File;
import java.util.*;

class ajustaImagen{
	ImageIcon ajusteImg(ImageIcon img, int ancho, int alto, int contenedorx, int contenedory) {

		//contenedory = contenedorx;
		Image aux_img;
		aux_img = img.getImage();

		//Si la imagen mide mas que el contenedor, en el lado que sea

		if ((ancho >= contenedorx ||  alto >= contenedorx || alto >= contenedory || ancho >= contenedory) || ((ancho <= contenedorx ||  alto <= contenedorx || alto <= contenedory || ancho <= contenedory))) {

			if(alto == ancho){
				Image newimg = aux_img.getScaledInstance(contenedory, contenedory, java.awt.Image.SCALE_SMOOTH);
				img = new ImageIcon(newimg);
				return img;
			}else if (ancho >= alto) {
				Image newimg = aux_img.getScaledInstance(contenedorx, contenedorx*alto/ancho, java.awt.Image.SCALE_SMOOTH);
				img = new ImageIcon(newimg);
				return img;
			}
			else {
				Image newimg = aux_img.getScaledInstance(contenedory*ancho/alto, contenedory, java.awt.Image.SCALE_SMOOTH);
				
				img = new ImageIcon(newimg);
				return img;
			}			
		}

		return img;


	}

	ImageIcon ajusteCuadrado(ImageIcon img){
		Image aux_img;
		aux_img = img.getImage();
		Image newimg = aux_img.getScaledInstance(210,210, java.awt.Image.SCALE_SMOOTH);
		img = new ImageIcon(newimg);
		return img;
	}
}
