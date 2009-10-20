/**
 * 
 */
package de.topicmapslab.tmcledit.domaindiagram.figures;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.ImageFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;

/**
 * @author niederhausen
 * 
 */
public class ScalableImageFigure extends Figure {

	private Image img;
	private Dimension size = new Dimension();
	private int alignment;
	private Dimension imageSize = null;

	/**
	 * Constructor<br>
	 * The default alignment is <code>PositionConstants.CENTER</code>.
	 */
	public ScalableImageFigure() {
		this(null, PositionConstants.CENTER);
	}

	/**
	 * Constructor<br>
	 * The default alignment is <code>PositionConstants.CENTER</code>.
	 * 
	 * @param image
	 *            The Image to be displayed
	 */
	public ScalableImageFigure(Image image) {
		this(image, PositionConstants.CENTER);
	}

	/**
	 * Constructor
	 * 
	 * @param image
	 *            The Image to be displayed
	 * @param alignment
	 *            A PositionConstant indicating the alignment
	 * 
	 * @see ImageFigure#setImage(Image)
	 * @see ImageFigure#setAlignment(int)
	 */
	public ScalableImageFigure(Image image, int alignment) {
		setImage(image);
		setAlignment(alignment);
	}

	/**
	 * @return The Image that this Figure displays
	 */
	public Image getImage() {
		return img;
	}

	/**
	 * Calculates the necessary size to display the Image within the figure's
	 * client area.
	 * 
	 * @see org.eclipse.draw2d.Figure#getPreferredSize(int, int)
	 */
	public Dimension getPreferredSize(int wHint, int hHint) {
		if (getInsets() == NO_INSETS)
			return size;
		Insets i = getInsets();
		if (imageSize == null)
			return size.getExpanded(i.getWidth(), i.getHeight());
		else
			return imageSize.getExpanded(i.getWidth(), i.getHeight());
	}

	/**
	 * @see org.eclipse.draw2d.Figure#paintFigure(Graphics)
	 */
	protected void paintFigure(Graphics graphics) {
		super.paintFigure(graphics);

		if (getImage() == null)
			return;

		int x, y;
		Rectangle area = getClientArea();
		Dimension imgSize = (imageSize == null) ? size : imageSize;
		switch (alignment & PositionConstants.NORTH_SOUTH) {
		case PositionConstants.NORTH:
			y = area.y;
			break;
		case PositionConstants.SOUTH:
			y = area.y + area.height - imgSize.height;
			break;
		default:
			y = (area.height - imgSize.height) / 2 + area.y;
			break;
		}
		switch (alignment & PositionConstants.EAST_WEST) {
		case PositionConstants.EAST:
			x = area.x + area.width - imgSize.width;
			break;
		case PositionConstants.WEST:
			x = area.x;
			break;
		default:
			x = (area.width - imgSize.width) / 2 + area.x;
			break;
		}
		if (imageSize == null)
			graphics.drawImage(getImage(), x, y);
		else
			graphics.drawImage(getImage(), 0, 0, size.width, size.height, x, y,
					imageSize.width, imageSize.height);
	}

	public void setImageSize(int width, int height) {
		imageSize = new Dimension(width, height);
	}

	/**
	 * Sets the alignment of the Image within this Figure. The alignment comes
	 * into play when the ImageFigure is larger than the Image. The alignment
	 * could be any valid combination of the following:
	 * 
	 * <UL>
	 * <LI>PositionConstants.NORTH</LI>
	 * <LI>PositionConstants.SOUTH</LI>
	 * <LI>PositionConstants.EAST</LI>
	 * <LI>PositionConstants.WEST</LI>
	 * <LI>PositionConstants.CENTER or PositionConstants.NONE</LI>
	 * </UL>
	 * 
	 * @param flag
	 *            A constant indicating the alignment
	 */
	public void setAlignment(int flag) {
		alignment = flag;
	}

	/**
	 * Sets the Image that this ImageFigure displays.
	 * <p>
	 * IMPORTANT: Note that it is the client's responsibility to dispose the
	 * given image.
	 * 
	 * @param image
	 *            The Image to be displayed. It can be <code>null</code>.
	 */
	public void setImage(Image image) {
		if (img == image)
			return;
		
		dispose();
		
		img = image;
		if (img != null)
			size = new Rectangle(image.getBounds()).getSize();
		else
			size = new Dimension();

		if ((img!=null)&&(imageSize != null)) {
			if ((image.getBounds().width != imageSize.width)
					|| (image.getBounds().height != imageSize.height)) {

				Image tmp = new Image(image.getDevice(), imageSize.width, imageSize.height);
				GC gc = new GC(tmp);
				gc.drawImage(img, 0, 0, size.width, size.height, 0, 0, imageSize.width, imageSize.height);
				
				img.dispose();
				img = tmp;
				size = new Rectangle(img.getBounds()).getSize();
			}
		}
		revalidate();
		repaint();
	}
	
	public void dispose() {
		if ( (img!=null) && (!img.isDisposed()) )
			img.dispose();
		img = null;
	}
}
