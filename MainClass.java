import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.Shell;

public class MainClass {
		
	public static void main(String[] args) {
		
		Display display = new Display();
		
		Window window = new Window();
		
		window.shell = new Shell(display, SWT.SHELL_TRIM | SWT.CENTER);
		window.layout = new FillLayout();
		window.shell.setText("Pacmans v1.0");
		window.shell.setLayout(window.layout);
		
		MenuBar menu = new MenuBar(window);
		
		window.shell.setSize(window.WIDTH+window.getW(), window.HEIGHT+window.getH());
		window.shell.setLocation(window.getH(),window.getH());
				
		Game game = new Game(window.shell);
		
		window.shell.open();
		
		while (!window.shell.isDisposed()) {
	          if (!display.readAndDispatch()) {
	            display.sleep();
	          }
	        }
		
        display.dispose();
	}


}
