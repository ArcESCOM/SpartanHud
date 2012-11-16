import javax.swing.*;
import java.awt.*;




public class ArcViewer extends JPanel {
	
	private JPanel ventana;
	private JLabel imagen;

	private JButton presentacion;
	private JButton siguente;
	private JButton atras;

	private JSlider zoom;

	private JButton carpeta;
	private JFileChooser chooser;

	private JMenuBar menu;

	public void init() {
		
		ventana = new JPanel();
		imagen = new JLabel("Aca va la imagen");

		presentacion = new JButton("play");
		siguente = new JButton("next");
		atras = new JButton("previous");

		zoom = new JSlider();

		carpeta = new JButton("Seleccionar Carpeta");

		ventana.add(imagen);
		ventana.add(presentacion);
		ventana.add(siguente);
		ventana.add(atras);
		ventana.add(zoom);
		ventana.add(carpeta);

	}

	public ArcViewer() {

		init(); 
		//start();

		JFrame frame = new JFrame("hola");
		frame.add("Center", this);
		frame.setSize(900, 600);
		frame.setVisible(true); }

	public static void main(String[] args) {
		
		new ArcViewer();
	}
}