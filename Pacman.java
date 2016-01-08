import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class Pacman {
	
	   	private final int WIDTH = 600;
	    private final int HEIGHT = 600;

	    public Pacman(Display display) {
	        
	        initUI(display);
	    }
	    
	    private void initUI(Display display) {

	        Shell shell = new Shell(display, SWT.SHELL_TRIM | SWT.CENTER);
	        
	        FillLayout layout = new FillLayout();
	        shell.setLayout(layout);

	        Game game = new Game(shell);        

	        shell.setText("Pacman v1.0");
	        int borW = shell.getSize().x - shell.getClientArea().width;
	        int borH = shell.getSize().y - shell.getClientArea().height;
	        shell.setSize(WIDTH + borW, HEIGHT + borH);
	      
	        shell.open();

	        while (!shell.isDisposed()) {
	          if (!display.readAndDispatch()) {
	            display.sleep();
	          }
	        }
	    }
		
	public static void main(String[] args) {
		Display display = new Display();
		Pacman window = new Pacman(display);
    display.dispose();
	}

}
