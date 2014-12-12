package web.service;

import java.awt.Graphics;
import java.io.IOException;
import pal.tree.Tree;


public interface PalTreeService extends BaseService {
	public Tree getTree();
	public void setTree(Tree tree);


	public Graphics getGraphics();
	public void setGraphics(Graphics g);
	public void paintPalTree(String title,Graphics g, int displayWidth,int displayHeight);
	
	public void createImage(String outImageFileName) throws IOException ;
	
	public void graphicsGeneration() throws IOException ;

	public void graphicsGeneration(Tree tree, int imageWidth, int imageHeight,
			String outImageFileName)  throws IOException ;
		

}
