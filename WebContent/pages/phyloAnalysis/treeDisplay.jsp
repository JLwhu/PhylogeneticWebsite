<%@ page contentType="image/jpeg"
	import="java.awt.*,java.awt.image.*,
com.sun.image.codec.jpeg.*,java.util.*"%>
<%
	// Create image
	int width = 200, height = 200;
	BufferedImage image = new BufferedImage(width, height,
			BufferedImage.TYPE_INT_RGB);
	// Get drawing context
	Graphics g = image.getGraphics();
	// Fill background
	g.setColor(Color.white);
	g.fillRect(0, 0, width, height);
	// Create random polygon
	Polygon poly = new Polygon();
	Random random = new Random();
	for (int i = 0; i < 5; i++) {
		poly.addPoint(random.nextInt(width), random.nextInt(height));
	}
	// Fill polygon
	g.setColor(Color.cyan);
	g.fillPolygon(poly);
	// Dispose context
	g.dispose();
	// Send back image
	ServletOutputStream sos = response.getOutputStream();
	JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(sos);
	encoder.encode(image);
%>