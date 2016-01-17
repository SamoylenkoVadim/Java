import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Display;

public class Pacman {
	
	public Image Pacman_left;
    public Image Pacman_right;
    public Image Pacman_up;
    public Image Pacman_down;
    public Image Pacman;
    
    public boolean left = false;
    public boolean right = false;
    public boolean up = false;
    public boolean down = false;
    
    public int STEP = 30;
    
    public int pacman_x = STEP;
    public int pacman_y = STEP;
    public int sumBonus = 0;
    
    public Pacman(){
    	loadImage();
    	Pacman = Pacman_right;
    }
    
    private void loadImage(){
    	
        Pacman_left = new Image(null, "Pacman_left.png");
       
        Pacman_right = new Image(null, "Pacman_right.png");
       
        Pacman_up = new Image(null, "Pacman_up.png");
        
        Pacman_down = new Image(null, "Pacman_down.png");
    	
    }
    
    public void move() {

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

}
