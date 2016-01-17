import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Shell;

public class Window {
	
	public int WIDTH = 600;
    public int HEIGHT = 600;
	public Shell shell;
	public FillLayout layout;
	public Canvas canvas;
	
	public int getW(){
		return shell.getSize().x - shell.getClientArea().width;
	}
	public int getH(){
		return shell.getSize().y - shell.getClientArea().height;
	}
	
	
}
