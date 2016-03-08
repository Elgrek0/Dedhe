package panels.Analytics;

import DB_data_loader.data_classes.ElectricalValue;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.text.SimpleDateFormat;
import java.util.Vector;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartMouseEvent;
import org.jfree.chart.ChartMouseListener;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.panel.CrosshairOverlay;
import org.jfree.chart.plot.Crosshair;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.general.SeriesException;
import org.jfree.data.time.Minute;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RectangleInsets;

/**
 * An example of a time series chart. For the most part, default settings are
 * used, except that the renderer is modified to show filled shapes (as well as
 * lines) at each data point.
 */
public class GraphPanel extends JPanel implements ChartMouseListener {

    public ChartPanel chartpanel;
    private Crosshair xCrosshair;
    private Crosshair yCrosshair;
    int splines;

    public GraphPanel(Vector<ElectricalValue> values) {

        TimeSeriesCollection dataset = createDataset(values);
        splines = 1;
        add(createContent(dataset));
        setVisible(true);

    }

    public GraphPanel(Vector<ElectricalValue> values, int splines) {

        TimeSeriesCollection dataset = createDataset(values);
        this.splines = splines;
        add(createContent(dataset));
        setVisible(true);

    }

    @Override
    public void setSize(int width, int height) {
        super.setSize(width, height);
        chartpanel.setSize(width-10, height-10);
    }

    private JFreeChart createChart(XYDataset dataset) {

        JFreeChart chart = ChartFactory.createTimeSeriesChart(
                "", // title
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

        XYSplineRenderer renderer = new XYSplineRenderer(splines);
        plot.setRenderer(renderer);

        renderer.setBaseShapesVisible(false);

        DateAxis axis = (DateAxis) plot.getDomainAxis();
        axis.setDateFormatOverride(new SimpleDateFormat("HH-mm MM-dd"));

        return chart;

    }

    private JPanel createContent(TimeSeriesCollection dataset) {
        JFreeChart chart = createChart(dataset);
        chartpanel = new ChartPanel(chart);
        chartpanel.addChartMouseListener(this);
        CrosshairOverlay crosshairOverlay = new CrosshairOverlay();
        xCrosshair = new Crosshair(Double.NaN, Color.GRAY, new BasicStroke(0f));
        xCrosshair.setLabelVisible(false);
        yCrosshair = new Crosshair(Double.NaN, Color.GRAY, new BasicStroke(0f));
        yCrosshair.setLabelVisible(true);
        crosshairOverlay.addDomainCrosshair(xCrosshair);
        crosshairOverlay.addRangeCrosshair(yCrosshair);
        chartpanel.addOverlay(crosshairOverlay);
        return chartpanel;
    }

    private TimeSeriesCollection createDataset(Vector<ElectricalValue> values) {
        TimeSeries s1 = new TimeSeries("Current of powerstation_name");

        for (ElectricalValue eval : values) {
            try {
                s1.add(new Minute(eval.datetime.toDate()), eval.value);
            } catch (SeriesException ex) {

            }
        }

        TimeSeriesCollection dataset = new TimeSeriesCollection(s1);

        return dataset;
    }

    @Override
    public void chartMouseClicked(ChartMouseEvent event) {
        // ignore
    }

    @Override
    public void chartMouseMoved(ChartMouseEvent event) {
        Rectangle2D dataArea = chartpanel.getScreenDataArea();
        JFreeChart chart = event.getChart();
        XYPlot plot = (XYPlot) chart.getPlot();
        ValueAxis xAxis = plot.getDomainAxis();
        double x = xAxis.java2DToValue(event.getTrigger().getX(), dataArea,
                RectangleEdge.BOTTOM);
        double y = DatasetUtilities.findYValue(plot.getDataset(), 0, x);

        xCrosshair.setValue(x);
        yCrosshair.setValue(y);
    }

}
