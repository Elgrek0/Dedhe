package panels.Analytics;

import org.jfree.chart.ChartUtilities;
import org.jfree.chart.ChartFactory;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.LogarithmicAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import java.awt.geom.Rectangle2D;

import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import org.jfree.ui.ApplicationFrame;

import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;

import java.awt.Frame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.*;

/**
 *
 * @author Paris
 */
public class MultiGraphPanel {

    public static void main(String args[]) {
        String sYAxis = "Strain";
        String sYAxisUOM = "J";
        String sYAxisLabel = sYAxis + "(" + sYAxisUOM + ")";
        String sXAxis = "Temperature";
        String sXAxisUOM = "C";
        String sXAxisLabel = sXAxis + "(" + sXAxisUOM + ")";

        String sY2Axis = "Stress";
        String sY2AxisUOM = "KJ";
        String sY2AxisLabel = sY2Axis + "(" + sY2AxisUOM + ")";
        String sX2Axis = "Viscosity";
        String sX2AxisUOM = "J/C";
        String sX2AxisLabel = sX2Axis + "(" + sX2AxisUOM + ")";

        XYSeriesCollection dataset = new XYSeriesCollection();
        XYSeriesCollection dataset1 = new XYSeriesCollection();

        XYSeries series = new XYSeries("XYGraph1");
        series.add(3, 2);
        series.add(2, 5);
        series.add(6, 3);
        series.add(4, 9);
        series.add(5, 10);

        XYSeries series1 = new XYSeries("XYGraph2");
        series1.add(3, 521);
        series1.add(4, 622);
        series1.add(2, 531);
        series1.add(6, 639);
        series1.add(2, 413);

        JFreeChart chart = ChartFactory.createXYLineChart(
                "Heading", // Title 
                sXAxisLabel, // x-axis Label 
                sYAxisLabel, // y-axis Label 
                dataset, // Dataset 
                PlotOrientation.VERTICAL, // Plot Orientation 
                true, // Show Legend 
                true, // Use tooltips 
                false // Configure chart to generate URLs? 
        );

        XYPlot xyplot = chart.getXYPlot();

        NumberAxis domainAxis = new NumberAxis(sXAxisLabel);
        NumberAxis secdomainAxis = new NumberAxis(sX2AxisLabel);
        NumberAxis rangeAxis = new NumberAxis(sYAxisLabel);
        NumberAxis secrangeAxis = new NumberAxis(sY2AxisLabel);

        dataset.addSeries(series);
        xyplot.setDataset(0, dataset);
        xyplot.setDomainAxis(domainAxis);
        xyplot.setRangeAxis(rangeAxis);

        dataset1.addSeries(series1);
        xyplot.setDataset(1, dataset1);
        xyplot.mapDatasetToRangeAxis(1, 1);
        xyplot.setRangeAxis(1, secrangeAxis);
        xyplot.setRangeAxisLocation(1, AxisLocation.BOTTOM_OR_RIGHT);

        chart.setBackgroundPaint(Color.white);

        XYLineAndShapeRenderer rr = new XYLineAndShapeRenderer();
        rr.setSeriesLinesVisible(2, true);
        rr.setSeriesShapesVisible(2, true);
        chart.getXYPlot().setRenderer(rr);

        BufferedImage bImage1 = (BufferedImage) chart.createBufferedImage(800, 400);
        JLabel label1 = new JLabel();
        ImageIcon imageIcon1 = new ImageIcon(bImage1);
        label1.setIcon(imageIcon1);

        Frame frame = new Frame(" Graph");
        frame.add(label1, BorderLayout.NORTH);

        frame.setSize(1000, 700);
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

    }
}
