package web.service.impl;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import pal.gui.Painter;
import pal.gui.TreePainterCircular;
import pal.gui.TreePainterNormal;
import pal.tree.Node;
import pal.tree.NodeUtils;
import pal.tree.SimpleTree;
import pal.tree.Tree;
import web.dao.SpeciesNameDao;
import web.dao.impl.SpeciesNameDaoImpl;
import web.exception.DaoException;
import web.exception.ServiceException;
import web.service.PalTreeService;

public class PalTreeServiceImpl extends BaseServiceImpl implements PalTreeService{
	
	private Tree tree;
	private Painter painter;
	private Graphics g;
	private String outImageFileName = "Tree.jpg";
	private BufferedImage image;

	private int imageWidth = 300;
	private int imageHeight =300;
	

	
	public Tree getTree(){
		return this.tree;
	}
	public void setTree(Tree tree){
		this.tree = tree;
	}
	public Graphics getGraphics(){
		return this.g;
	}
	public void setGraphics(Graphics g){
		this.g = g;
	}
	public void paintPalTree(String title,Graphics g, int displayWidth,int displayHeight){ 
		TreePainterNormal tpn = new TreePainterNormal(tree,title,true);
		tpn.paint(g, displayWidth, displayHeight);	
	}
	
	public void createImage(String outImageFileName) throws IOException {
		FileOutputStream fos = new FileOutputStream(outImageFileName);
		BufferedOutputStream bos = new BufferedOutputStream(fos);
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(bos);
		encoder.encode(image);
		bos.close();
	}
	
	public void graphicsGeneration() throws IOException {
		graphicsGeneration(tree, imageWidth, imageHeight, outImageFileName);
	}
	
	public void graphicsGeneration(Tree tree,String outImageFileName ) throws IOException {
		graphicsGeneration(tree, imageWidth, imageHeight, outImageFileName);
	}

	public void graphicsGeneration(Tree tree, int imageWidth, int imageHeight,
			String outImageFileName) throws IOException {

		int scale=1;
		int nodecount = tree.getExternalNodeCount();
		scale = nodecount/20;
		if (scale<1) scale=1;
		imageWidth = Math.round(imageWidth*scale);
		if (imageWidth>900) imageWidth = 900;
		imageHeight = Math.round(imageHeight*scale);
		if (imageHeight>60000) imageHeight = 60000;
		image = new BufferedImage(imageWidth, imageHeight,
				BufferedImage.TYPE_INT_RGB);
		Graphics graphics = image.getGraphics();
		paintPalTree("", graphics, imageWidth, imageHeight);
		createImage(outImageFileName);
	}
	

	
}
