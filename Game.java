import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Shell;

public class Game extends Canvas{
	
	private final int WIDTH = 600;
    private final int HEIGHT = 600;
	private final int DELAY = 150;
	private final int STEP = 30;		
	
	private Display display;
    private Shell shell;
    private Runnable runnable;
    private Image MapBack;
    
    private boolean inGame = true;
    private boolean timer_flag = true;
    
    Pacman p1 = new Pacman();
    Pacman p2 = new Pacman();
    Map map = new Map();
    

    public static timer timer;
    private timer timerGame = new timer();
    
	public Game(Shell shell) {
        
			super(shell,SWT.DOUBLE_BUFFERED);
			this.shell = shell;
			MapBack = map.Map;
			setBackgroundImage(MapBack);
			initListener();
			
			initGame();
    }
	
	private void initListener(){
			display = shell.getDisplay();
			addListener(SWT.Paint, event -> Paint(event));
			addListener(SWT.KeyDown, event -> onKeyDown(event));
			addListener(SWT.Dispose, event -> {
				timerGame.stop();
				
	        });
	}
	
	private void Paint(Event e) {

			GC gc = e.gc;
	        gc.setAntialias(SWT.ON);
	        
	        if (inGame) {
	            drawObjects(e);
	        } else {
	        	TheEnd(e);
	        }
		
    }
	
	private void drawObjects(Event e) {

			GC gc = e.gc;
	        gc.drawImage(p1.Pacman, p1.pacman_x, p1.pacman_y);
	        for (int i = 0; i < WIDTH/STEP; i++)
	        	for (int j = 0; j < HEIGHT/STEP; j++)
	        		if (map.arr[i][j] == 2)
	        			gc.drawImage(map.Bonus, i*STEP+STEP/4, j*STEP+STEP/4);
	   
	    }
	
	private void onKeyDown(Event e) {

        int key = e.keyCode;
        if(timer_flag){
        	timerGame.start();
        	timer_flag = false;
        }

        if (key == SWT.ARROW_LEFT) {
            p1.left = true;
            p1.right = false;
            p1.up = false;
            p1.down = false;
            p1.Pacman = p1.Pacman_left;
        }

        if (key == SWT.ARROW_RIGHT) {
            p1.right = true;
            p1.left = false;
            p1.up = false;
            p1.down = false;
            p1.Pacman = p1.Pacman_right;
        }

        if (key == SWT.ARROW_UP) {
            p1.up = true;
            p1.down = false;
            p1.right = false;
            p1.left = false;
            p1.Pacman = p1.Pacman_up;
        }

        if (key == SWT.ARROW_DOWN) {
            p1.down = true;
            p1.up = false;
            p1.right = false;
            p1.left = false;
            p1.Pacman = p1.Pacman_down;
        }
    }
	
	private void initGame() {

		runnable = new Runnable() {
            @Override
            public void run() {

                if (inGame) {
                	checkCollision();
                	p1.move(); 
                	//p2.move();
                	checkBonus();
                	checkTheEnd();
                }
                
                display.timerExec(DELAY, this);
                redraw();
            }
        };

        display.timerExec(DELAY, runnable);
    };
	    
    public void checkCollision() {
        
        if (p1.left) {
        	if (p1.pacman_x == 0)
            	p1.left = false;
        	if ((p1.pacman_x!=0) && (map.arr[((p1.pacman_x-STEP)/STEP)][p1.pacman_y/STEP] == 1))
            	p1.left = false;
            
        }

        if (p1.right) {
        	if (p1.pacman_x == WIDTH - STEP )
            	p1.right = false;
        	if ((p1.pacman_x!=WIDTH - STEP) && (map.arr[((p1.pacman_x+STEP)/STEP)][p1.pacman_y/STEP] == 1))
            	p1.right = false;
            
        }

        if (p1.up) {
        	if (p1.pacman_y == 0 )
            	p1.up = false;
        	if ((p1.pacman_y!=0) && (map.arr[p1.pacman_x/STEP][((p1.pacman_y-STEP)/STEP)] == 1))
            	p1.up = false;
        }
  
        if (p1.down) {
        	if (p1.pacman_y == HEIGHT - STEP )
            	p1.down = false;
        	if ((p1.pacman_y!=HEIGHT - STEP) && (map.arr[p1.pacman_x/STEP][((p1.pacman_y+STEP)/STEP)] == 1))
        		p1.down = false;
        }
    }
    
		
		public void checkBonus(){
	    	
	    	if (map.arr[p1.pacman_x/STEP][p1.pacman_y/STEP] == 2){
	    		map.arr[p1.pacman_x/STEP][p1.pacman_y/STEP] = 0;
	    		p1.sumBonus = p1.sumBonus + 1;
	    	}
	    	
	    }
		
		public void checkTheEnd(){
			
			inGame = false;
			for(int i = 0; i < WIDTH/STEP; i ++)
				for(int j = 0; j < HEIGHT/STEP; j++)
					if (map.arr[i][j] == 2){
						inGame = true;
						i = WIDTH/STEP;
						j = HEIGHT/STEP;
					}
		}
		
		public void TheEnd(Event e){
			
			GC gc = e.gc;
	        String msg = "Игра пройдена! Ваш счет:: " + p1.sumBonus + "  Время ::  " + timerGame.getTime();
	        
	        timerGame.stop();
	        MapBack = map.TheEnd;
			setBackgroundImage(MapBack);

	        Font font = new Font(e.display, "Helvetica", 20, SWT.NORMAL);
	        Color whiteCol = new Color(e.display, 50, 50, 50);

	        gc.setForeground(whiteCol);
	        gc.setFont(font);

	        Point size = gc.textExtent(msg);

	        gc.drawText(msg, (WIDTH - size.x) / 2, (HEIGHT - size.y) / 2);

	        font.dispose();
	        whiteCol.dispose();
	        
	        display.timerExec(-1, runnable);
	    }
			
		

}
