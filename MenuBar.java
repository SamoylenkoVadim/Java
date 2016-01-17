import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;

public class MenuBar {
	
	private	Menu menuBar, fileMenu, helpMenu;
    private MenuItem fileMenuHeader, helpMenuHeader;
    private MenuItem fileExitItem, fileSaveItem, helpGetHelpItem, filesSettingsItem;
    
    private Image Menu;
    private Image Menu_save;
    private Image Menu_help;
    private Image Menu_exit;
    private Image Menu_set;
    private Image Menu_get_help;
    private Image Reference;
    
    private Display display;

	public MenuBar(Window window){
		
		display = window.shell.getDisplay();
		loadImage();
		initMenu(window);
	
	}
	
	private void loadImage(){
		
		ImageData imageMenu = new ImageData("Menu.png");
        Menu = new Image(display, imageMenu);
        
        ImageData imageMenu_save = new ImageData("Menu_save.png");
        Menu_save = new Image(display, imageMenu_save);
        
        ImageData imageMenu_exit = new ImageData("Menu_exit.png");
        Menu_exit = new Image(display, imageMenu_exit);
        
        ImageData imageMenu_help = new ImageData("Help.png");
        Menu_help = new Image(display, imageMenu_help);
        
        ImageData imageMenu_set = new ImageData("Set.png");
        Menu_set = new Image(display, imageMenu_set);
        
        ImageData imageMenu_get_help = new ImageData("Menu_get_help.png");
        Menu_get_help = new Image(display, imageMenu_get_help);
        
        ImageData imageReference = new ImageData("Reference.png");
        Reference = new Image(display, imageReference);
		
	}
	
	public void initMenu(Window window){
		
		menuBar = new Menu(window.shell, SWT.BAR);
		
        fileMenuHeader = new MenuItem(menuBar, SWT.CASCADE);
        fileMenuHeader.setText("&Pacmans");
        fileMenuHeader.setImage(Menu);
        fileMenu = new Menu(window.shell, SWT.DROP_DOWN);
        fileMenuHeader.setMenu(fileMenu);
        
        fileSaveItem = new MenuItem(fileMenu, SWT.PUSH);
        fileSaveItem.setText("&Save");
        fileSaveItem.setImage(Menu_save);
        
        filesSettingsItem = new MenuItem(fileMenu, SWT.PUSH);
        filesSettingsItem.setText("&Settings");
        filesSettingsItem.setImage(Menu_set);
        
        fileExitItem = new MenuItem(fileMenu, SWT.PUSH);
        fileExitItem.setText("&Exit");
        fileExitItem.setImage(Menu_exit);
        
        helpMenuHeader = new MenuItem(menuBar, SWT.CASCADE);
        helpMenuHeader.setText("&Help");
        helpMenuHeader.setImage(Menu_help);
        helpMenu = new Menu(window.shell, SWT.DROP_DOWN);
        helpMenuHeader.setMenu(helpMenu);
        
        helpGetHelpItem = new MenuItem(helpMenu, SWT.PUSH);
        helpGetHelpItem.setText("&Get Help");
        helpGetHelpItem.setImage(Menu_get_help);
        
        fileExitItem.addSelectionListener(new SelectionAdapter(){
        	public void widgetSelected(SelectionEvent event) {
                window.shell.close();
                display.dispose();
              }
        });
        
        helpGetHelpItem.addSelectionListener(new SelectionAdapter(){
        	public void widgetSelected(SelectionEvent event) {
        		Shell shell_help = new Shell(display, SWT.SHELL_TRIM | SWT.CENTER);
    	        FillLayout layout = new FillLayout();
    	        shell_help.setLayout(layout);
    	        shell_help.setSize(window.WIDTH + window.getW(), window.HEIGHT +window.getH());
    	        shell_help.setLocation(window.getW(),window.getH());
    	        shell_help.setText("Get Help || Instructions for use || Pacmans v1.0");
    	        shell_help.setBackgroundImage(Reference);
    	        shell_help.open();
        	}
        });
        
        window.shell.setMenuBar(menuBar);
	}
}
