import javax.swing.*;
import java.awt.*;




public class ArcViewer extends JPanel {
	
	private JPanel areaventana;
	private JPanel areabotones;
	private JLabel imagen;

	private JButton presentacion;
	private JButton siguente;
	private JButton atras;

	private JSlider zoom;

	private JButton carpeta;
	private JFileChooser chooser;

	private JMenuBar menu;

	Color colorGris = new Color(0x999999);

	public void init() {
		
		areaventana = new JPanel();
		areabotones = new JPanel();
		imagen = new JLabel("Aca va la imagen");

		presentacion = new JButton("play");
		siguente = new JButton("next");
		atras = new JButton("previous");

		zoom = new JSlider();

		carpeta = new JButton("Seleccionar Carpeta");

		/* Agregando los componentes */

		atras.setBackground(colorGris);

		add(imagen);
		areabotones.add(presentacion);
		areabotones.add(siguente);
		areabotones.add(atras);
		areabotones.add(zoom);
		areabotones.add(carpeta);


		this.setLayout (new BorderLayout()); //layout para todo el programa
		add(areaventana, BorderLayout.CENTER); //layout solo para el area de muestra de imagen
		add(areabotones, BorderLayout.SOUTH); //layout para todos los botones
	}

	public ArcViewer() {

		init(); 
		//start();

		JFrame frame = new JFrame("ArcViewer v.0.1");
			
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //Cierra el programa por la buena

		frame.add("Center", this);
		frame.setSize(900, 600);
		frame.setVisible(true); }


	public static void main(String[] args) {
		new ArcViewer(); }
}