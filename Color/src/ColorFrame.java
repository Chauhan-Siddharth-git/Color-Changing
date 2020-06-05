import javax.swing.JFrame;
public class ColorFrame {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame j1 = new JFrame();
		j1.setSize(800, 700);
		j1.setTitle("COLOR");
		j1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		j1.setLocationRelativeTo(null);
		j1.add(new ColorComp());
		j1.setVisible(true);	
	}
}
