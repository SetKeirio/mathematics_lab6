package algorithm;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.ImageTitle;
import org.jfree.data.xy.DefaultXYDataset;
import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.util.ArrayList;
public class Graphics {

    public void chart(BigDecimal a, BigDecimal b, int functionNumber) {
        JFrame frame = new JFrame("Уравнение");
        GroupLayout layout = new GroupLayout(frame.getContentPane());
        frame.setLayout(layout);

        JFreeChart chart = generateChart(a, b, functionNumber);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(1000, 700));
        chartPanel.setMouseZoomable(true);
        chartPanel.setMouseWheelEnabled(true);
        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addComponent(chartPanel)
        );

        layout.setVerticalGroup(
                layout.createParallelGroup()
                        .addComponent(chartPanel)
        );

        frame.getContentPane().setBackground(Color.WHITE);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.pack();
        frame.setLocationRelativeTo(null);
    }

    public void chartEquations(BigDecimal a, BigDecimal b, int functionNumber) {
        JFrame frame = new JFrame("Уравнения");
        GroupLayout layout = new GroupLayout(frame.getContentPane());
        frame.setLayout(layout);

        ChartPanel chart = generateChartEquations(a, b, functionNumber);

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addComponent(chart)
        );

        layout.setVerticalGroup(
                layout.createParallelGroup()
                        .addComponent(chart)
        );

        frame.getContentPane().setBackground(Color.WHITE);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.pack();
        frame.setLocationRelativeTo(null);
    }

    public JFreeChart generateInterpolation(ArrayList<BigDecimal> data, ArrayList<BigDecimal> a, int functionNumber){
        DefaultXYDataset dataset = new DefaultXYDataset();
        double [][] e = new double[2][data.size() / 2];
        dataset.addSeries("Входные данные", e);
        for (int i = 0; i < data.size(); i++){
            if (i % 2 == 0) {
                e[0][i / 2] = data.get(i).doubleValue();
            }
            else if (i % 2 == 1) {
                e[1][i / 2] = data.get(i).doubleValue();
            }
        }
        BigDecimal minimum = new BigDecimal(1000);
        BigDecimal maximum = new BigDecimal(-1000);
        for (int i = 0; i < data.size(); i++){
            if (i % 2 == 0){
                if (data.get(i).compareTo(maximum) > 0){
                    maximum = data.get(i);
                }
                if (data.get(i).compareTo(minimum) < 0){
                    minimum = data.get(i);
                }
            }
        }
        dataset.addSeries("Аппроксимация", generateApproximationData(minimum.doubleValue(), maximum.doubleValue(), a, functionNumber));
        JFreeChart chart = ChartFactory.createScatterPlot("", "x", "y", dataset, PlotOrientation.VERTICAL, true, false, false);
        //ImageIcon image = new ImageIcon(equation.getImage());
        //chart.addSubtitle(new ImageTitle(image.getImage()));

        chart.getXYPlot().setRangeZeroBaselineVisible(true);
        chart.getXYPlot().setDomainZeroBaselineVisible(true);

        NumberAxis xAxis = new NumberAxis("x");
        NumberAxis yAxis = new NumberAxis("f(x)");

        xAxis.setRange(minimum.doubleValue(), maximum.doubleValue());
        yAxis.setRange(-10, 10);

        chart.getXYPlot().setDomainAxis(xAxis);
        chart.getXYPlot().setRangeAxis(yAxis);

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(1000, 700));
        chartPanel.setMouseZoomable(true);
        chartPanel.setMouseWheelEnabled(true);

        return chart;
    }

    public JFreeChart generateDifferentiation(ArrayList<BigDecimal> data, ArrayList<BigDecimal> a, int functionNumber){
        DefaultXYDataset dataset = new DefaultXYDataset();
        double [][] e = new double[2][data.size() / 2];
        dataset.addSeries("Входные данные", e);
        for (int i = 0; i < data.size(); i++){
            if (i % 2 == 0) {
                e[0][i / 2] = data.get(i).doubleValue();
            }
            else if (i % 2 == 1) {
                e[1][i / 2] = data.get(i).doubleValue();
            }
        }
        BigDecimal minimum = new BigDecimal(1000);
        BigDecimal maximum = new BigDecimal(-1000);
        for (int i = 0; i < data.size(); i++){
            if (i % 2 == 0){
                if (data.get(i).compareTo(maximum) > 0){
                    maximum = data.get(i);
                }
                if (data.get(i).compareTo(minimum) < 0){
                    minimum = data.get(i);
                }
            }
        }
        dataset.addSeries("Частное решение", generateDifferentiationData(minimum.doubleValue(), maximum.doubleValue(), a, functionNumber));
        JFreeChart chart = ChartFactory.createScatterPlot("", "x", "y", dataset, PlotOrientation.VERTICAL, true, false, false);
        //ImageIcon image = new ImageIcon(equation.getImage());
        //chart.addSubtitle(new ImageTitle(image.getImage()));

        chart.getXYPlot().setRangeZeroBaselineVisible(true);
        chart.getXYPlot().setDomainZeroBaselineVisible(true);

        NumberAxis xAxis = new NumberAxis("x");
        NumberAxis yAxis = new NumberAxis("f(x)");

        xAxis.setRange(minimum.doubleValue(), maximum.doubleValue());
        yAxis.setRange(-10, 10);

        chart.getXYPlot().setDomainAxis(xAxis);
        chart.getXYPlot().setRangeAxis(yAxis);

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(1000, 700));
        chartPanel.setMouseZoomable(true);
        chartPanel.setMouseWheelEnabled(true);

        return chart;
    }


    public JFreeChart generatePolynomial(ArrayList<BigDecimal> data, BigDecimal x, ArrayList<BigDecimal> a, int functionNumber){
        DefaultXYDataset dataset = new DefaultXYDataset();
        double [][] e = new double[2][data.size() / 2];
        dataset.addSeries("Входные данные", e);
        for (int i = 0; i < data.size(); i++){
            if (i % 2 == 0) {
                e[0][i / 2] = data.get(i).doubleValue();
            }
            else if (i % 2 == 1) {
                e[1][i / 2] = data.get(i).doubleValue();
            }
        }
        BigDecimal minimum = new BigDecimal(1000);
        BigDecimal maximum = new BigDecimal(-1000);
        for (int i = 0; i < data.size(); i++){
            if (i % 2 == 0){
                if (data.get(i).compareTo(maximum) > 0){
                    maximum = data.get(i);
                }
                if (data.get(i).compareTo(minimum) < 0){
                    minimum = data.get(i);
                }
            }
        }
        dataset.addSeries("Интерполяция", generatePolynomialData(minimum.doubleValue(), maximum.doubleValue(), a, x, functionNumber));
        double[][] temp = new double[][] {{x.doubleValue()}, {Functions.polynomial(a, x).doubleValue()}};
        System.out.println(temp[0][0]);
        System.out.println(temp[1][0]);
        dataset.addSeries("Исходная точка", new double[][] {{x.doubleValue()}, {Functions.polynomial(a, x).doubleValue()}});
        JFreeChart chart = ChartFactory.createScatterPlot("", "x", "y", dataset, PlotOrientation.VERTICAL, true, false, false);
        //ImageIcon image = new ImageIcon(equation.getImage());
        //chart.addSubtitle(new ImageTitle(image.getImage()));

        chart.getXYPlot().setRangeZeroBaselineVisible(true);
        chart.getXYPlot().setDomainZeroBaselineVisible(true);

        NumberAxis xAxis = new NumberAxis("x");
        NumberAxis yAxis = new NumberAxis("f(x)");

        xAxis.setRange(minimum.doubleValue(), maximum.doubleValue());
        yAxis.setRange(-10, 10);

        chart.getXYPlot().setDomainAxis(xAxis);
        chart.getXYPlot().setRangeAxis(yAxis);

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(1000, 700));
        chartPanel.setMouseZoomable(true);
        chartPanel.setMouseWheelEnabled(true);

        return chart;
    }

    public JFreeChart generateApproximation(ArrayList<BigDecimal> data, ArrayList<BigDecimal> a, int functionNumber){
        DefaultXYDataset dataset = new DefaultXYDataset();
        double [][] e = new double[2][data.size() / 2];
        dataset.addSeries("Входные данные", e);
        for (int i = 0; i < data.size(); i++){
            if (i % 2 == 0) {
                e[0][i / 2] = data.get(i).doubleValue();
            }
            else if (i % 2 == 1) {
                e[1][i / 2] = data.get(i).doubleValue();
            }
        }
        BigDecimal minimum = new BigDecimal(1000);
        BigDecimal maximum = new BigDecimal(-1000);
        for (int i = 0; i < data.size(); i++){
            if (i % 2 == 0){
                if (data.get(i).compareTo(maximum) > 0){
                    maximum = data.get(i);
                }
                if (data.get(i).compareTo(minimum) < 0){
                    minimum = data.get(i);
                }
            }
        }
        dataset.addSeries("Аппроксимация", generateApproximationData(minimum.doubleValue(), maximum.doubleValue(), a, functionNumber));
        JFreeChart chart = ChartFactory.createScatterPlot("", "x", "y", dataset, PlotOrientation.VERTICAL, true, false, false);
        //ImageIcon image = new ImageIcon(equation.getImage());
        //chart.addSubtitle(new ImageTitle(image.getImage()));

        chart.getXYPlot().setRangeZeroBaselineVisible(true);
        chart.getXYPlot().setDomainZeroBaselineVisible(true);

        NumberAxis xAxis = new NumberAxis("x");
        NumberAxis yAxis = new NumberAxis("f(x)");

        xAxis.setRange(minimum.doubleValue(), maximum.doubleValue());
        yAxis.setRange(-10, 10);

        chart.getXYPlot().setDomainAxis(xAxis);
        chart.getXYPlot().setRangeAxis(yAxis);

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(1000, 700));
        chartPanel.setMouseZoomable(true);
        chartPanel.setMouseWheelEnabled(true);

        return chart;
    }

    public JFreeChart generateChart(BigDecimal a, BigDecimal b, int functionNumber) {
        DefaultXYDataset dataset = new DefaultXYDataset();
        dataset.addSeries("Функция", generateData(a.doubleValue(), b.doubleValue(), functionNumber));

        JFreeChart chart = ChartFactory.createXYLineChart("", "x", "y", dataset, PlotOrientation.VERTICAL, true, false, false);

        //ImageIcon image = new ImageIcon(equation.getImage());
        //chart.addSubtitle(new ImageTitle(image.getImage()));

        chart.getXYPlot().setRangeZeroBaselineVisible(true);
        chart.getXYPlot().setDomainZeroBaselineVisible(true);

        NumberAxis xAxis = new NumberAxis("x");
        NumberAxis yAxis = new NumberAxis("f(x)");

        xAxis.setRange(a.doubleValue(), b.doubleValue());
        yAxis.setRange(-10, 10);

        chart.getXYPlot().setDomainAxis(xAxis);
        chart.getXYPlot().setRangeAxis(yAxis);

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(1000, 700));
        chartPanel.setMouseZoomable(true);
        chartPanel.setMouseWheelEnabled(true);

        return chart;
    }

    public JFreeChart generateRectanglesChart(BigDecimal a, BigDecimal b, int functionNumber) {
        DefaultXYDataset dataset = new DefaultXYDataset();
        dataset.addSeries("Функция", generateData(a.doubleValue(), b.doubleValue(), functionNumber));
        //dataset.addSeries();

        JFreeChart chart = ChartFactory.createXYLineChart("", "x", "y", dataset, PlotOrientation.VERTICAL, true, false, false);

        //ImageIcon image = new ImageIcon(equation.getImage());
        //chart.addSubtitle(new ImageTitle(image.getImage()));

        chart.getXYPlot().setRangeZeroBaselineVisible(true);
        chart.getXYPlot().setDomainZeroBaselineVisible(true);

        NumberAxis xAxis = new NumberAxis("x");
        NumberAxis yAxis = new NumberAxis("f(x)");

        xAxis.setRange(a.doubleValue(), b.doubleValue());
        yAxis.setRange(-10, 10);

        chart.getXYPlot().setDomainAxis(xAxis);
        chart.getXYPlot().setRangeAxis(yAxis);

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(1000, 700));
        chartPanel.setMouseZoomable(true);
        chartPanel.setMouseWheelEnabled(true);

        return chart;
    }

    public ChartPanel generateChartEquations(BigDecimal a, BigDecimal b, int functionNumber) {
        if (functionNumber == 1) {
            DefaultXYDataset dataset = new DefaultXYDataset();
            dataset.addSeries("Функция", generateDataEquations(a.doubleValue(), b.doubleValue(), functionNumber));
            dataset.addSeries("Функция 2", generateDataEquations(2 * a.doubleValue(), 2 * b.doubleValue(), functionNumber + 1));
            JFreeChart chart = ChartFactory.createXYLineChart("", "x", "y", dataset, PlotOrientation.VERTICAL, true, false, false);

            //ImageIcon image = new ImageIcon(equation.getImage());
            //chart.addSubtitle(new ImageTitle(image.getImage()));

            chart.getXYPlot().setRangeZeroBaselineVisible(true);
            chart.getXYPlot().setDomainZeroBaselineVisible(true);

            NumberAxis xAxis = new NumberAxis("x");
            NumberAxis yAxis = new NumberAxis("f(x)");

            xAxis.setRange(a.doubleValue(), b.doubleValue());
            yAxis.setRange(a.doubleValue(), b.doubleValue());

            chart.getXYPlot().setDomainAxis(xAxis);
            chart.getXYPlot().setRangeAxis(yAxis);

            ChartPanel chartPanel = new ChartPanel(chart);
            chartPanel.setPreferredSize(new Dimension(1000, 700));
            chartPanel.setMouseZoomable(true);
            chartPanel.setMouseWheelEnabled(true);

            return chartPanel;
        }
        else{
            DefaultXYDataset dataset = new DefaultXYDataset();
            dataset.addSeries("Функция", generateDataEquations(a.doubleValue(), b.doubleValue(), functionNumber));
            dataset.addSeries("Функция 2 (первый полукруг)", generateDataEquations(a.doubleValue(), b.doubleValue(), functionNumber + 1));
            dataset.addSeries("Функция 2 (второй полукруг)", generateDataEquations(a.doubleValue(), b.doubleValue(), functionNumber + 2));
            JFreeChart chart = ChartFactory.createXYLineChart("", "x", "y", dataset, PlotOrientation.VERTICAL, true, false, false);

            //ImageIcon image = new ImageIcon(equation.getImage());
            //chart.addSubtitle(new ImageTitle(image.getImage()));

            chart.getXYPlot().setRangeZeroBaselineVisible(true);
            chart.getXYPlot().setDomainZeroBaselineVisible(true);

            NumberAxis xAxis = new NumberAxis("x");
            NumberAxis yAxis = new NumberAxis("f(x)");

            xAxis.setRange(a.doubleValue(), b.doubleValue());
            yAxis.setRange(a.doubleValue(), b.doubleValue());

            chart.getXYPlot().setDomainAxis(xAxis);
            chart.getXYPlot().setRangeAxis(yAxis);

            ChartPanel chartPanel = new ChartPanel(chart);
            chartPanel.setPreferredSize(new Dimension(1000, 700));
            chartPanel.setMouseZoomable(true);
            chartPanel.setMouseWheelEnabled(true);

            return chartPanel;
        }
    }

    public double[][] generatePolynomialData(double a, double b, ArrayList<BigDecimal> data, BigDecimal t, int functionNumber) {
        java.util.List<Double> x = new ArrayList<>();
        java.util.List<Double> y = new ArrayList<>();

        for (int i = 0; a < b; i++) {
            if (((t.doubleValue() - a) >= 0.05) || ((t.doubleValue() - a) <= -0.05)) {
                x.add(a);
                BigDecimal d = Functions.polynomial(data, new BigDecimal(a));
                y.add(d.doubleValue());

                double f1 = Math.abs(Functions.polynomial(data, new BigDecimal(a)).doubleValue());
                double f2 = Math.abs(Functions.polynomial(data, new BigDecimal(a + 0.01)).doubleValue());
                /*if (f1 < 1 && f1 > 0.0001) {
                    a += 0.01 * f1;
                }
                else if (f2 < 1) {
                    a += Math.max(0.000001, 0.0001 * f2);
                }
                else {*/
            }
            a += 0.005;
            //}
        }
        x.add(b);
        BigDecimal d = Functions.polynomial(data, new BigDecimal(b));
        y.add(d.doubleValue());

        double[][] data1 = new double[2][x.size()];
        for (int i = 0; i < x.size(); i++) {
            data1[0][i] = x.get(i);
            data1[1][i] = y.get(i);
        }
        return data1;
    }

    public double[][] generateApproximationData(double a, double b, ArrayList<BigDecimal> data, int functionNumber) {
        java.util.List<Double> x = new ArrayList<>();
        java.util.List<Double> y = new ArrayList<>();

        for (int i = 0; a < b; i++) {
            x.add(a);
            BigDecimal d = Functions.function_approximation(new BigDecimal(a), data, functionNumber);
            y.add(d.doubleValue());

            double f1 = Math.abs(Functions.function_approximation(new BigDecimal(a), data, functionNumber).doubleValue());
            double f2 = Math.abs(Functions.function_approximation(new BigDecimal(a + 0.01), data, functionNumber).doubleValue());
            /*if (f1 < 1 && f1 > 0.0001) {
                a += 0.01 * f1;
            }
            else if (f2 < 1) {
                a += Math.max(0.000001, 0.0001 * f2);
            }
            else {*/
            a += 0.005;
            //}
        }
        x.add(b);
        BigDecimal d = Functions.function_approximation(new BigDecimal(b), data, functionNumber);
        y.add(d.doubleValue());

        double[][] data1 = new double[2][x.size()];
        for (int i = 0; i < x.size(); i++) {
            data1[0][i] = x.get(i);
            data1[1][i] = y.get(i);
        }
        return data1;
    }

    public double[][] generateDifferentiationData(double a, double b, ArrayList<BigDecimal> data, int functionNumber) {
        java.util.List<Double> x = new ArrayList<>();
        java.util.List<Double> y = new ArrayList<>();
        BigDecimal c = data.get(0);
        for (int i = 0; a < b; i++) {
            x.add(a);
            BigDecimal d = Functions.function_differentiation_first(new BigDecimal(a), c, functionNumber);
            y.add(d.doubleValue());

            double f1 = Math.abs(Functions.function_differentiation_first(new BigDecimal(a), c, functionNumber).doubleValue());
            double f2 = Math.abs(Functions.function_differentiation_first(new BigDecimal(a + 0.01), c, functionNumber).doubleValue());
            /*if (f1 < 1 && f1 > 0.0001) {
                a += 0.01 * f1;
            }
            else if (f2 < 1) {
                a += Math.max(0.000001, 0.0001 * f2);
            }
            else {*/
            a += 0.005;
            //}
        }
        x.add(b);
        BigDecimal d = Functions.function_differentiation_first(new BigDecimal(b), c, functionNumber);
        y.add(d.doubleValue());

        double[][] data1 = new double[2][x.size()];
        for (int i = 0; i < x.size(); i++) {
            data1[0][i] = x.get(i);
            data1[1][i] = y.get(i);
        }
        return data1;
    }

    public double[][] generateData(double a, double b, int functionNumber) {
        java.util.List<Double> x = new ArrayList<>();
        java.util.List<Double> y = new ArrayList<>();

        for (int i = 0; a < b; i++) {
            x.add(a);
            BigDecimal d = Functions.function(new BigDecimal(a), functionNumber);
            y.add(d.doubleValue());

            double f1 = Math.abs(Functions.function(new BigDecimal(a), functionNumber).doubleValue());
            double f2 = Math.abs(Functions.function(new BigDecimal(a + 0.01), functionNumber).doubleValue());
            if (f1 < 1 && f1 > 0.0001) {
                a += 0.01 * f1;
            }
            else if (f2 < 1) {
                a += Math.max(0.000001, 0.0001 * f2);
            }
            else {
                a += 0.005;
            }
        }
        x.add(b);
        BigDecimal d = Functions.function(new BigDecimal(b), functionNumber);
        y.add(d.doubleValue());

        double[][] data = new double[2][x.size()];
        for (int i = 0; i < x.size(); i++) {
            data[0][i] = x.get(i);
            data[1][i] = y.get(i);
        }
        return data;
    }

    private double[][] generateDataEquations(double a, double b, int functionNumber) {
        if (functionNumber == 1) {
            java.util.List<Double> x = new ArrayList<>();
            java.util.List<Double> y = new ArrayList<>();

            for (int i = 0; a < b; i++) {
                x.add(a);
                BigDecimal d = Functions.e1_y(new BigDecimal(a));
                y.add(d.doubleValue());

                double f1 = Math.abs(Functions.e1_y(new BigDecimal(a)).doubleValue());
                double f2 = Math.abs(Functions.e1_y(new BigDecimal(a + 0.01)).doubleValue());
                if (f1 < 1 && f1 > 0.0001) {
                    a += 0.01 * f1;
                } else if (f2 < 1) {
                    a += Math.max(0.000001, 0.0001 * f2);
                } else {
                    a += 0.005;
                }
            }
            x.add(b);
            BigDecimal d = Functions.e1_y(new BigDecimal(a));
            y.add(d.doubleValue());

            double[][] data = new double[2][x.size()];
            for (int i = 0; i < x.size(); i++) {
                data[0][i] = x.get(i);
                data[1][i] = y.get(i);
            }
            return data;
        }
        if (functionNumber == 2) {
            java.util.List<Double> x = new ArrayList<>();
            java.util.List<Double> y = new ArrayList<>();

            for (int i = 0; a < b; i++) {
                y.add(a);
                BigDecimal d = Functions.e2_x(new BigDecimal(a));
                x.add(d.doubleValue());

                double f1 = Math.abs(Functions.e2_x(new BigDecimal(a)).doubleValue());
                double f2 = Math.abs(Functions.e2_x(new BigDecimal(a)).doubleValue());
                if (f1 < 1 && f1 > 0.0001) {
                    a += 0.01 * f1;
                } else if (f2 < 1) {
                    a += Math.max(0.000001, 0.0001 * f2);
                } else {
                    a += 0.005;
                }
            }
            y.add(b);
            BigDecimal d = Functions.e2_x(new BigDecimal(b));
            x.add(d.doubleValue());

            double[][] data = new double[2][x.size()];
            for (int i = 0; i < x.size(); i++) {
                data[0][i] = x.get(i);
                data[1][i] = y.get(i);
            }
            return data;
        }
        if (functionNumber == 3) {
            java.util.List<Double> x = new ArrayList<>();
            java.util.List<Double> y = new ArrayList<>();

            for (int i = 0; a < b; i++) {
                x.add(a);
                BigDecimal d = Functions.e3_y(new BigDecimal(a));
                y.add(d.doubleValue());

                double f1 = Math.abs(Functions.e3_y(new BigDecimal(a)).doubleValue());
                double f2 = Math.abs(Functions.e3_y(new BigDecimal(a + 0.01)).doubleValue());
                if (f1 < 1 && f1 > 0.0001) {
                    a += 0.01 * f1;
                } else if (f2 < 1) {
                    a += Math.max(0.000001, 0.0001 * f2);
                } else {
                    a += 0.005;
                }
            }
            x.add(b);
            BigDecimal d = Functions.e3_y(new BigDecimal(a));
            y.add(d.doubleValue());

            double[][] data = new double[2][x.size()];
            for (int i = 0; i < x.size(); i++) {
                data[0][i] = x.get(i);
                data[1][i] = y.get(i);
            }
            return data;
        }
        if (functionNumber == 4){
            java.util.List<Double> x = new ArrayList<>();
            java.util.List<Double> y = new ArrayList<>();
            a = -1.414;
            b = 1.403;
            for (int i = 0; a < b; i++) {
                //System.out.println(a);
                x.add(a);
                BigDecimal d = Functions.e4_y1(new BigDecimal(a));
                y.add(d.doubleValue());

                double f1 = Math.abs(Functions.e4_y1(new BigDecimal(a)).doubleValue());
                double f2 = Math.abs(Functions.e4_y1(new BigDecimal(a + 0.01)).doubleValue());
                if (f1 < 1 && f1 > 0.0001) {
                    a += 0.01 * f1;
                } else if (f2 < 1) {
                    a += Math.max(0.000001, 0.0001 * f2);
                } else {
                    a += 0.005;
                }
            }
            x.add(b);
            BigDecimal d = Functions.e4_y1(new BigDecimal(a));
            y.add(d.doubleValue());

            double[][] data = new double[2][x.size()];
            for (int i = 0; i < x.size(); i++) {
                data[0][i] = x.get(i);
                data[1][i] = y.get(i);
            }
            return data;
        }
        else{
            java.util.List<Double> x = new ArrayList<>();
            java.util.List<Double> y = new ArrayList<>();
            a = -1.414;
            b = 1.403;
            for (int i = 0; a < b; i++) {
                //System.out.println(a);
                x.add(a);
                BigDecimal d = Functions.e4_y2(new BigDecimal(a));
                y.add(d.doubleValue());

                double f1 = Math.abs(Functions.e4_y2(new BigDecimal(a)).doubleValue());
                double f2 = Math.abs(Functions.e4_y2(new BigDecimal(a + 0.01)).doubleValue());
                if (f1 < 1 && f1 > 0.0001) {
                    a += 0.01 * f1;
                } else if (f2 < 1) {
                    a += Math.max(0.000001, 0.0001 * f2);
                } else {
                    a += 0.005;
                }
            }
            x.add(b);
            BigDecimal d = Functions.e4_y2(new BigDecimal(a));
            y.add(d.doubleValue());

            double[][] data = new double[2][x.size()];
            for (int i = 0; i < x.size(); i++) {
                data[0][i] = x.get(i);
                data[1][i] = y.get(i);
            }
            return data;
        }

    }



}
