import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Shell;

public class Game extends Canvas{
	
    private final int WIDTH = 600;
    private final int HEIGHT = 600;
    private final int DELAY = 150;
    private final int STEP = 30;
	
    private int map[][] = new int[WIDTH/STEP][HEIGHT/STEP];
	
    private Image Map;
    private Image Pacman_left;
    private Image Pacman_right;
    private Image Pacman_up;
    private Image Pacman_down;
    private Image Pacman;
    private Image Bonus;
    
    private int pacman_x = 30;
    private int pacman_y = 30;
    private int sumBonus = 0;
	
    private boolean left = false;
    private boolean right = true;
    private boolean up = false;
    private boolean down = false;
    private boolean inGame = true;
    
    private Display display;
    private Shell shell;
    private Runnable runnable;
	
	public Game(Shell shell) {
        
	super(shell, SWT.DOUBLE_BUFFERED);
        this.shell = shell;
        initMap();
        
    }
	
	private void initMap() {

        display = shell.getDisplay();
        addListener(SWT.Paint, event -> Paint(event));
        addListener(SWT.KeyDown, event -> onKeyDown(event));
        loadImages();
        setBackgroundImage(Map);
        intiWall();
        initGame();
    }
	
	private void intiWall(){
		map[0][0] = 1;		map[11][5] = 1;		map[11][12] = 1;		map[9][18] = 1;
		map[1][0] = 1;		map[12][5] = 1;		map[4][11] = 1;			map[19][17] = 1;
		map[2][0] = 1;		map[12][5] = 1;		map[4][12] = 1;			map[19][18] = 1;
		map[3][0] = 1;		map[13][5] = 1;		map[4][13] = 1;			map[19][19] = 1;
		map[0][1] = 1;		map[14][5] = 1;		map[4][14] = 1;			map[18][19] = 1;
		map[0][2] = 1;		map[14][6] = 1;		map[5][14] = 1;			map[17][19] = 1;
		map[4][4] = 1;		map[14][7] = 1;		map[6][14] = 1;			map[16][19] = 1;
		map[5][4] = 1;		map[8][9] = 1;		map[16][12] = 1;
		map[6][4] = 1;		map[9][9] = 1;		map[16][13] = 1;
		map[7][4] = 1;		map[8][10] = 1;		map[16][14] = 1;
		map[11][1] = 1;		map[9][10] = 1;		map[16][15] = 1;
		map[11][2] = 1;		map[10][11] = 1;	map[8][17] = 1;
		map[12][1] = 1;		map[11][11] = 1;	map[9][17] = 1;
		map[12][2] = 1;		map[10][12] = 1;	map[8][18] = 1;
		
		for(int i = 0; i < WIDTH/STEP; i ++)
			for(int j = 0; j < HEIGHT/STEP; j++)
				if (map[i][j] != 1)
					map[i][j] = 2;
	}
	
	private void loadImages() {

        ImageData imageMap = new ImageData("Map.png");
        Map = new Image(display, imageMap);

        ImageData imagePacman_left = new ImageData("Pacman_left.png");
        Pacman_left = new Image(display, imagePacman_left);
        
        ImageData imagePacman_right = new ImageData("Pacman_right.png");
        Pacman_right = new Image(display, imagePacman_right);
        
        ImageData imagePacman_up = new ImageData("Pacman_up.png");
        Pacman_up = new Image(display, imagePacman_up);
        
        ImageData imagePacman_down = new ImageData("Pacman_down.png");
        Pacman_down = new Image(display, imagePacman_down);
        
        Pacman = Pacman_right;
        
        ImageData imageBonus = new ImageData("Bonus.png");
        Bonus = new Image(display, imageBonus);

    }
	
	private void initGame() {

		runnable = new Runnable() {
            @Override
            public void run() {

                if (inGame) {
                	System.out.println(pacman_y);
                	checkCollision();
                	move();     
                	checkBonus();
                }
                
                display.timerExec(DELAY, this);
                redraw();
            }
        };

        display.timerExec(DELAY, runnable);
    };
    
    private void Paint(Event e) {

        GC gc = e.gc;
        gc.setAntialias(SWT.ON);
        drawObjects(e);

    }
    
    private void drawObjects(Event e) {

        GC gc = e.gc;
        gc.drawImage(Pacman, pacman_x, pacman_y);
        for (int i = 0; i < WIDTH/STEP; i++)
        	for (int j = 0; j < HEIGHT/STEP; j++)
        		if (map[i][j] == 2)
        			gc.drawImage(Bonus, i*STEP+STEP/4, j*STEP+STEP/4);
   
    }
    
    private void onKeyDown(Event e) {

        int key = e.keyCode;

        if (key == SWT.ARROW_LEFT) {
            left = true;
            right = false;
            up = false;
            down = false;
            Pacman = Pacman_left;
        }

        if (key == SWT.ARROW_RIGHT) {
            right = true;
            left = false;
            up = false;
            down = false;
            Pacman = Pacman_right;
        }

        if (key == SWT.ARROW_UP) {
            up = true;
            down = false;
            right = false;
            left = false;
            Pacman = Pacman_up;
        }

        if (key == SWT.ARROW_DOWN) {
            down = true;
            up = false;
            right = false;
            left = false;
            Pacman = Pacman_down;
        }
    }
    
    private void move() {

        if (left) {
            pacman_x -= STEP;
        }

        if (right) {
        	pacman_x += STEP;
        }

        if (up) {
            pacman_y -= STEP;
        }

        if (down) {
            pacman_y += STEP;
        }
    }
    
    public void checkCollision() {
        
        if (left) {
        	if (pacman_x == 0)
            	left = false;
        	if ((pacman_x!=0) && (map[((pacman_x-STEP)/STEP)][pacman_y/STEP] == 1))
            	left = false;
            
        }

        if (right) {
        	if (pacman_x == 570 )
            	right = false;
        	if ((pacman_x!=570) && (map[((pacman_x+STEP)/STEP)][pacman_y/STEP] == 1))
            	right = false;
            
        }

        if (up) {
        	if (pacman_y == 0 )
            	up = false;
        	if ((pacman_y!=0) && (map[pacman_x/STEP][((pacman_y-STEP)/STEP)] == 1))
            	up = false;
        }
  
        if (down) {
        	if (pacman_y == 570 )
            	down = false;
        	if ((pacman_y!=570) && (map[pacman_x/STEP][((pacman_y+STEP)/STEP)] == 1))
        		down = false;
        }
    }
    
    public void checkBonus(){
    	
    	if (map[pacman_x/STEP][pacman_y/STEP] == 2){
    		map[pacman_x/STEP][pacman_y/STEP] = 0;
    		sumBonus = sumBonus + 1;
    	}
    	
    }

}
