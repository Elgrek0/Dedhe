package Analytics;

import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.RectangleInsets;

/**
 * An example of a time series chart. For the most part, default settings are
 * used, except that the renderer is modified to show filled shapes (as well as
 * lines) at each data point.
 */
public class GraphPanel {

    public ChartPanel panel;

    public GraphPanel(ResultSet rs) {

        TimeSeries s1 = new TimeSeries("Current of powerstation_name");

        try {
            while (rs.next()) {
                java.util.Date date;
                Timestamp timestamp = rs.getTimestamp(1);
                if (timestamp != null) {
                    date = new java.util.Date(timestamp.getTime());
                    // System.out.println(date.toString());
                    s1.add(new Minute(date), rs.getFloat(2));
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(GraphPanel.class.getName()).log(Level.SEVERE, null, ex);
        }

        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(s1);

        JFreeChart chart = createChart(dataset);
        panel = new ChartPanel(chart);
        panel.setFillZoomRectangle(false);
        panel.setMouseWheelEnabled(true);
        
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
