package de.topicmapslab.tmcledit.diagram.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;

import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.batik.svggen.SVGGeneratorContext;
import org.apache.batik.svggen.SVGGraphics2D;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.SWTGraphics;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.ImageLoader;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.w3c.dom.Document;

public class ImageExporter {
	
	public static void exportSvg(IPrintableDiagramEditor editor, File file) {
		Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor().getSite().getShell();
		FileDialog dlg = new FileDialog(shell, SWT.SAVE);
		dlg.setFilterExtensions(new String[] { "*.svg" });
		dlg.setText("Save as...");
		
		
		Graphics g = null;
		Image image = null;
		try {
			String svgNS = "http://www.w3.org/2000/svg";

			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().getDOMImplementation().createDocument(
			        svgNS, "svg", null);
			
			SVGGeneratorContext ctx = SVGGeneratorContext.createDefault(doc);
			ctx.setComment("Generated by Onotoa with Batik SVG Generator");
			ctx.setEmbeddedFontsOn(true);
			ctx.setPrecision(3);
			SVGGraphics2D svgGraphics2d = new SVGGraphics2D(ctx, true);
			image = getImage(editor);
			g = new GraphicsToGraphics2DAdaptor(svgGraphics2d, image.getBounds());
			
			
			IFigure fig = editor.getPrintableFigure();
			g.translate(fig.getBounds().getLocation().getCopy().scale(-1.));
			fig.paint(g);

			FileWriter writer = new FileWriter(file);
			svgGraphics2d.stream(writer);
			writer.flush();
			
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (g != null) {
				g.dispose();
			}
			if (image!=null)
				image.dispose();
		}
	}

	public static void exportPng(IPrintableDiagramEditor editor, File file) {
		Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor().getSite().getShell();
		FileDialog dlg = new FileDialog(shell, SWT.SAVE);
		dlg.setFilterExtensions(new String[] { "*.png" });
		dlg.setText("Save as...");
		
		
		Image image = null;
		try {
			FileOutputStream result = new FileOutputStream(file);
			image = getImage(editor);
			
			ImageLoader imageLoader = new ImageLoader();
			imageLoader.data = new ImageData[] { image.getImageData() };
			imageLoader.save(result, SWT.IMAGE_PNG);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (image != null) {
				image.dispose();
			}
		}
	}
	
	private static  Image getImage(IPrintableDiagramEditor editor) {
		IFigure figure = editor.getPrintableFigure();
		Device device = Display.getCurrent();
		Rectangle r = figure.getBounds();
		Image image = null;
		GC gc = null;
		Graphics g = null;
		try {
			image = new Image(device, r.width, r.height);
			gc = new GC(image);
			g = new SWTGraphics(gc);
			g.translate(r.x * -1, r.y * -1);

			figure.paint(g);

			return image;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (gc!=null)
				gc.dispose();
			if (g!=null)
				g.dispose();
		}
	}
}
