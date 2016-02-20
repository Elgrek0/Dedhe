package panels.Analytics;

import DB_data_loader.data_classes.ElectricalValue;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Vector;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.Minute;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.Year;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.RectangleInsets;

/**
 * An example of a time series chart. For the most part, default settings are
 * used, except that the renderer is modified to show filled shapes (as well as
 * lines) at each data point.
 */
public class GraphPanel extends JPanel {

    public ChartPanel panel;

    public GraphPanel(Vector<ElectricalValue> values) {

        TimeSeries s1 = new TimeSeries("Current of powerstation_name");

        for (int i = 0; i < values.size(); i++) {
            s1.add(new Minute(values.get(i).datetime.toDate()), values.get(i).value);
        }

        TimeSeriesCollection dataset = new TimeSeriesCollection(s1);

        JFreeChart chart = createChart(dataset);
        panel = new ChartPanel(chart);
        panel.setFillZoomRectangle(false);
        panel.setMouseWheelEnabled(true);
        this.add(panel);
        this.setSize(800, 500);
        this.setVisible(true);

    }

    private static JFreeChart createChart(XYDataset dataset) {

        JFreeChart chart = ChartFactory.createTimeSeriesChart(
                "data on date", // title
                "Date", // x-axis label
                "Current", // y-axis label
                dataset, // data
                true, // create legend?
                true, // generate tooltips?
                false // generate URLs?
        );

        chart.setBackgroundPaint(Color.white);

        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);
        plot.setAxisOffset(new RectangleInsets(5.0, 5.0, 5.0, 5.0));
        plot.setDomainCrosshairVisible(true);
        plot.setRangeCrosshairVisible(true);

        XYItemRenderer r = plot.getRenderer();
        if (r instanceof XYLineAndShapeRenderer) {
            XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) r;
            renderer.setBaseShapesVisible(true);
            renderer.setBaseShapesFilled(true);
            renderer.setDrawSeriesLineAsPath(true);
        }

        DateAxis axis = (DateAxis) plot.getDomainAxis();
        axis.setDateFormatOverride(new SimpleDateFormat("HH-mm"));

        return chart;

    }

}
