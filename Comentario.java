
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.*;
import java.io.File;
import java.util.*;

public class Comentario extends JPanel implements ActionListener{
	
	public ImagenBean imgBean;
	public int posicion;

	private JFrame frame;
	private JPanel panelComentario;
	private JPanel panelControles;

	// private JLabel comment;
	private JTextArea comment;
	private JTextArea nuevoComment;
	private JButton guardar;
	private JButton salir;


	Color colorGris = new Color(0x202020);
	Color colorCasiBlanco = new Color(0xDEDEDE);

	public void init() {

		panelComentario = new JPanel();
		panelControles = new JPanel();
		comment = new JTextArea("Comentarios: ");
		nuevoComment = new JTextArea();

		guardar = new JButton("Guardar");
		salir = new JButton("Salir");

		panelComentario.add(comment);
		panelControles.add(nuevoComment);
		panelControles.add(guardar);
		panelControles.add(salir);

		panelComentario.setBackground(colorCasiBlanco);
		panelControles.setBackground(colorGris);

		nuevoComment.setColumns(30);

		comment.setEditable(false);
		comment.setForeground(colorGris);
		comment.setBackground(colorCasiBlanco);
		comment.setPreferredSize(new Dimension(400, 90));
		
		guardar.addActionListener(this);
		salir.addActionListener(this);

		this.setLayout (new BorderLayout());
		add(panelComentario, BorderLayout.CENTER);
		add(panelControles, BorderLayout.SOUTH);

		comment.setText(imgBean.getComentario());

		nuevoComment.requestFocusInWindow();

	}

	public void actionPerformed (ActionEvent event){
		
		if (event.getSource() == guardar){
			imgBean.SetComentario(
				"> " +
				nuevoComment.getText());
			frame.setVisible(false);
		}

		if(event.getSource() == salir){
			frame.setVisible(false);
		}

	}

	public Comentario(ImagenBean imagenesbean, int posicion){
		
		imgBean = imagenesbean;
		this.posicion = posicion;
		frame = new JFrame("Comentarios");			
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE); //Cierra el programa por la buena

		frame.add("Center", this);
		frame.setSize(600, 150);
		frame.setVisible(true);

		init();
	}
}