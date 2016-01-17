import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Display;

public class Map {
	
	public int WIDTH = 600;
    public int HEIGHT = 600;
    public int STEP = 30;
    public int arr[][] = new int[WIDTH/STEP][HEIGHT/STEP];
    
    public Display display;
    public Image Map;
    public Image Bonus;
    public Image TheEnd;
	
    
	public Map(){
		
		loadImage();	
		initMap();
	}
	
	
	private void loadImage() {

        Map = new Image(null, "Map.png");
        
        Bonus = new Image(null, "Bonus.png");
        
        TheEnd = new Image(null, "TheEnd.jpg");
	
	}
	
		
	private void initMap(){
		
		arr[0][0] = 1;		arr[11][5] = 1;		arr[11][12] = 1;		arr[9][18] = 1;
		arr[1][0] = 1;		arr[12][5] = 1;		arr[4][11] = 1;			arr[19][17] = 1;
		arr[2][0] = 1;		arr[12][5] = 1;		arr[4][12] = 1;			arr[19][18] = 1;
		arr[3][0] = 1;		arr[13][5] = 1;		arr[4][13] = 1;			arr[19][19] = 1;
		arr[0][1] = 1;		arr[14][5] = 1;		arr[4][14] = 1;			arr[18][19] = 1;
		arr[0][2] = 1;		arr[14][6] = 1;		arr[5][14] = 1;			arr[17][19] = 1;
		arr[4][4] = 1;		arr[14][7] = 1;		arr[6][14] = 1;			arr[16][19] = 1;
		arr[5][4] = 1;		arr[8][9] = 1;		arr[16][12] = 1;
		arr[6][4] = 1;		arr[9][9] = 1;		arr[16][13] = 1;
		arr[7][4] = 1;		arr[8][10] = 1;		arr[16][14] = 1;
		arr[11][1] = 1;		arr[9][10] = 1;		arr[16][15] = 1;
		arr[11][2] = 1;		arr[10][11] = 1;	arr[8][17] = 1;
		arr[12][1] = 1;		arr[11][11] = 1;	arr[9][17] = 1;
		arr[12][2] = 1;		arr[10][12] = 1;	arr[8][18] = 1;
		
		for(int i = 0; i < WIDTH/STEP; i ++)
			for(int j = 0; j < HEIGHT/STEP; j++)
				if (arr[i][j] != 1)
					arr[i][j] = 2;
		
		
		arr[1][1] = 0;
		
	}
	
}
	
	

