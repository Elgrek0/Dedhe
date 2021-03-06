package Gui.panels.analyticsguipanels;

import DB_data_loader.data_classes.ElectricalValue;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Vector;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartMouseEvent;
import org.jfree.chart.ChartMouseListener;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.StandardCrosshairLabelGenerator;
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
import org.joda.time.DateTime;

/**
 * An example of a time series chart. For the most part, default settings are
 * used, except that the renderer is modified to show filled shapes (as well as
 * lines) at each data point.
 */
public class GraphPanel extends JPanel implements ChartMouseListener {

    public ChartPanel chartpanel;
    private Crosshair yCrosshair;
    private Crosshair xCrosshair;
    int splines;
    TimeSeriesCollection dataset=new TimeSeriesCollection();
    public JTextField datetime_textfield = new JTextField();

    public GraphPanel(Vector<ElectricalValue> values) {

        createDataset(values);
        splines = 1;
        add(createContent(dataset));
        setVisible(true);

    }

    public GraphPanel(Vector<ElectricalValue> values, int splines) {

        createDataset(values);
        this.splines = splines;
        add(createContent(dataset));
        datetime_textfield.setSize(210, 60);
        datetime_textfield.setEditable(false);
        datetime_textfield.setHorizontalAlignment(JTextField.CENTER);
        datetime_textfield.setText("Hover Mouse over Chart");
        add(datetime_textfield);
        setVisible(true);

    }

    public GraphPanel(List<Vector<ElectricalValue>> valuelist, int splines) {

        for (int i = 0; i < valuelist.size(); i++) {
            createDataset(valuelist.get(i));
        }
        this.splines = splines;
        add(createContent(dataset));
        datetime_textfield.setSize(210, 60);
        datetime_textfield.setEditable(false);
        datetime_textfield.setHorizontalAlignment(JTextField.CENTER);
        datetime_textfield.setText("Hover Mouse over Chart");
        add(datetime_textfield);
        setVisible(true);

    }

    @Override
    public void setSize(int width, int height) {
        super.setSize(width, height);
        chartpanel.setSize(width - 10, height - 110);
    }

    private JFreeChart createChart(XYDataset dataset) {

        JFreeChart chart = ChartFactory.createTimeSeriesChart(
                "", // title
                "Date", // x-axis label
                "Value", // y-axis label
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
        axis.setDateFormatOverride(new SimpleDateFormat("MM-dd HH-mm"));
        return chart;

    }

    private JPanel createContent(TimeSeriesCollection dataset) {
        JFreeChart chart = createChart(dataset);
        chartpanel = new ChartPanel(chart);
        chartpanel.addChartMouseListener(this);
        CrosshairOverlay crosshairOverlay = new CrosshairOverlay();
        xCrosshair = new Crosshair(Double.NaN, Color.GRAY, new BasicStroke(0f));
        yCrosshair = new Crosshair(Double.NaN, Color.GRAY, new BasicStroke(0f));
        //xCrosshair.setLabelGenerator());

        xCrosshair.setLabelVisible(false);
        yCrosshair.setLabelVisible(true);
        crosshairOverlay.addDomainCrosshair(xCrosshair);
        crosshairOverlay.addRangeCrosshair(yCrosshair);
        chartpanel.addOverlay(crosshairOverlay);
        return chartpanel;
    }

    private void createDataset(Vector<ElectricalValue> values) {
        TimeSeries s1 = new TimeSeries("");

        for (ElectricalValue eval : values) {
            try {
                s1.add(new Minute(eval.datetime.toDate()), eval.value);
            } catch (SeriesException ex) {

            }
        }

        dataset.addSeries(s1);
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

        String date = new StandardCrosshairLabelGenerator("{0}", new SimpleDateFormat("MM-dd HH-mm").getNumberFormat()).generateLabel(xCrosshair);

        try {
            DateTime datetime = new DateTime(Long.parseLong(date));
            datetime_textfield.setText(datetime.toString("YYYY/MM/dd E HH:mm:ss"));

        } catch (Exception e) {
            datetime_textfield.setText("Hover Mouse over Chart");
        }
        xCrosshair.setValue(x);
        yCrosshair.setValue(y);
    }

}
