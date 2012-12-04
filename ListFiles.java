/*

F:
cd F:\Hellvetica\ArcViewer
javac -Xlint:unchecked ListFiles.java ListFiles.java




*/

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.*;
import java.io.File;
import java.util.*;


public class ListFiles {

	ArrayList aux = new ArrayList();
	String files;
	ArrayList<String> lista = new ArrayList<String>();

	ArrayList<String> Miranda (JFileChooser chooser, int returnChooser) {

		if (returnChooser == JFileChooser.APPROVE_OPTION) {
			
			String folderpath =  chooser.getCurrentDirectory().getPath();
			String getName = chooser.getSelectedFile().getPath();
			String hola;
			File folder = new File(folderpath);
			File[] archivos = folder.listFiles();

			int listacounter = 0;

			for (int count=0; count < archivos.length; count++) {

				if (archivos[count].isFile()) {
					files = archivos[count].getName();

					if (
						files.endsWith(".jpg") || files.endsWith(".jpeg") ||	
						files.endsWith(".png") || files.endsWith(".gif") ||
						files.endsWith(".JPG") || files.endsWith(".JPEG") ||
						files.endsWith(".PNG") || files.endsWith(".GIF") ) {

						listacounter++;
						lista.add(archivos[count].getPath());
					}
				}
			}


		}

	return lista;

}


}
