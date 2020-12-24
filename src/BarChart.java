import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class BarChart extends JFrame {

    private static final long serialVersionUID = 1L;

    public BarChart(String appTitle) {
        super(appTitle);

        // Create Dataset
        CategoryDataset dataset = createDataset();

        //Create chart
        JFreeChart chart=ChartFactory.createBarChart(
                "Psychomotor Performance Tests Results", //Chart Title
                "Test number", // Category axis
                "Points", // Value axis
                dataset,
                PlotOrientation.VERTICAL,
                true,true,false
        );

        ChartPanel panel=new ChartPanel(chart);
        setContentPane(panel);
    }

    private CategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // First test results
        dataset.addValue(Main.getPoints(), Main.getName() + " " + Main.getSurname() + " points", "First test");
        dataset.addValue(15, "Average points", "First test");

        // Second test results
        dataset.addValue(Main.getPoints(), Main.getName() + " " + Main.getSurname() + " points", "Second test");
        dataset.addValue(5, "Average points", "Second test");

        // Third test results
        dataset.addValue(Main.getPoints(), Main.getName() + " " + Main.getSurname() + " points", "Third test");
        dataset.addValue(35, "Average points", "Third test");

        return dataset;
    }

} 