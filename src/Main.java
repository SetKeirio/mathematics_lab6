import algorithm.AlgorithmManager;
import algorithm.Functions;
import algorithm.Graphics;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.DefaultXYDataset;
import support.input.Console;
import support.input.Validate;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.SortedMap;

public class Main {
    private static AlgorithmManager solver = new AlgorithmManager();
    private static ChartPanel p;
    private static Validate validator = new Validate();
    private static Graphics g = new Graphics();

    private static DefaultTableModel model = new DefaultTableModel();
    private static JTable table = new JTable(model);


    static class InputTableModel extends AbstractTableModel{

        Validate validator = new Validate();
        class Point {
            BigDecimal x, y;

            public BigDecimal getX() {
                return x;
            }

            public void setX(BigDecimal x) {
                this.x = x;
            }

            public BigDecimal getY() {
                return y;
            }

            public void setY(BigDecimal y) {
                this.y = y;
            }
            public Point(){
                x = new BigDecimal(0);
                y = new BigDecimal(0);
            }

            public Point(BigDecimal x, BigDecimal y){
                this.x = x;
                this.y = y;
            }
        }

        private ArrayList<Point> numbers = new ArrayList<Point>();

        private String[] numbers1 = new String[] {"x", "y"};

        @Override
        public String getColumnName(int column) {
            return numbers1[column];
        }



        @Override
        public int getRowCount() {
            return numbers.size();
        }

        @Override
        public int getColumnCount() {
            return numbers1.length;
        }

        @Override
        public Object getValueAt(int row, int col) {
            BigDecimal answer = new BigDecimal(-1);
            if (col == 0){
                answer = numbers.get(row).x;
            }
            else if (col == 1){
                answer = numbers.get(row).y;
            }

            return answer;

        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return true;
        }

        public void setValueAt(Object value, int row, int col) {

            if (!validator.validateNumber((String) value)){
                return;
            }
            else {
                String temp = (String) value;
                temp = temp.replace(",", ".");
                if (col == 0) {
                    numbers.get(row).setX(new BigDecimal(temp));
                } else if (col == 1) {
                    numbers.get(row).setY(new BigDecimal(temp));
                }
            }
            //fireTableCellUpdated(row, col);
        }

        public void addPoint(BigDecimal x, BigDecimal y){
            numbers.add(new Point(x, y));
            fireTableDataChanged();
        }

        public void deletePoint(){
            if (numbers.size() > 0) {
                numbers.remove(numbers.size() - 1);
                fireTableDataChanged();
            }
        }
    }
    private static InputTableModel input = new InputTableModel();

    public static JFrame getWindow() {
        JFrame window = new JFrame();
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        window.setBounds(0, 0, dimension.width, dimension.height);
        window.setTitle("mathematics_lab3_335099");
        return window;

    }

    public static void main(String[] args) {
        int functionNumber = 1;
        int methodNumber = 1;
        JFrame window = getWindow();
        JPanel panel = new JPanel();
        Label label = new Label();
        GridLayout dataAndGraphics = new GridLayout(1, 3);
        GridLayout grid = new GridLayout(5, 2);
        panel.setLayout(grid);
        grid.setHgap(10);
        CheckboxGroup oneFunction = new CheckboxGroup();
        Checkbox firstFunction = new Checkbox("Задача Коши методом Эйлера", oneFunction, true);
        Checkbox secondFunction = new Checkbox("Задача Коши методом Рунге-Кутта", oneFunction, false);
        Checkbox thirdFunction = new Checkbox("Задача Коши методом Адамса", oneFunction, false);
        panel.add(firstFunction);
        panel.add(secondFunction);
        panel.add(thirdFunction);

        CheckboxGroup oneEquation = new CheckboxGroup();
        Checkbox firstEquation = new Checkbox("y' = 3x^3 + 2x^2 + x - 3 + 10y", oneEquation, true);
        Checkbox secondEquation = new Checkbox("y' = 1 + 0.5y - x", oneEquation, false);
        Checkbox thirdEquation = new Checkbox("y' = y + sin(x)", oneEquation, false);
        panel.add(firstEquation);
        panel.add(secondEquation);
        panel.add(thirdEquation);
        /*panel.add(fourthFunction);
        panel.add(fifthFunction);
        panel.add(sixthFunction);
        panel.add(function);*/
        /*input.addPoint(new BigDecimal(-3), new BigDecimal(-2.9).setScale(1, RoundingMode.HALF_EVEN));
        input.addPoint(new BigDecimal(-2), new BigDecimal(-1.6).setScale(1, RoundingMode.HALF_EVEN));
        input.addPoint(new BigDecimal(-1), new BigDecimal(-0.9).setScale(1, RoundingMode.HALF_EVEN));
        input.addPoint(new BigDecimal(0), new BigDecimal(1));
        input.addPoint(new BigDecimal(1), new BigDecimal(1.9).setScale(1, RoundingMode.HALF_EVEN));
        input.addPoint(new BigDecimal(10), new BigDecimal(10));*/


        JTextField equationsInput = new JTextField();
        //JTable binarySearchInput = new JTable(input);
        String[] temp = new String[]{"x", "y"};
        JButton binarySearchButton = new JButton("Решить задачу Коши для данной функции!");

        Label binarySearchLabel = new Label();
        /*JButton add = new JButton("Добавить точку для функции, заданной таблично!");
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                input.addPoint(new BigDecimal(0), new BigDecimal(0));
            }
        });
        JButton delete = new JButton("Удалить точку для функции, заданной таблично!");
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                input.deletePoint();
            }
        });*/

        /*JTextField secantsInput = new JTextField();
        JButton secantsButton = new JButton("Посчитать методом секущих!");
        Label secantsLabel = new Label();
        JTextField iterationInput = new JTextField();
        JButton iterationButton = new JButton("Посчитать методом простой итерации!");
        Label iterationLabel = new Label();*/
        //panel.add(binarySearchInput);
        panel.add(equationsInput);
        //panel.add(binarySearchLabel);
        panel.add(binarySearchButton);
        //panel.add(add);
        //panel.add(delete);
        panel.add(binarySearchLabel);

        /*panel.add(secantsInput);
        panel.add(secantsButton);
        panel.add(secantsLabel);
        panel.add(iterationInput);
        panel.add(iterationButton);
        panel.add(iterationLabel);*/

        binarySearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (oneFunction.getSelectedCheckbox() == null){
                    binarySearchLabel.setText("Выберите, пожалуйста, одну функцию из списка");
                }
                if (!(validator.validateNumbers(equationsInput.getText()))){
                    binarySearchLabel.setText("Введите, пожалуйста, y0 пробел x0 пробел xn пробел h пробел e (0.000001 <= e <= 10)");
                }
                else if (validator.validateNumbers(equationsInput.getText())){
                    binarySearchLabel.setText("");
                    BigDecimal first, second, h, accuracy;
                    ArrayList<BigDecimal> data = new ArrayList<BigDecimal>();
                    String[] temp = equationsInput.getText().strip().split(" ");
                    for (int i = 0; i < temp.length; i++){
                        temp[i] = temp[i].replace(",", ".");
                    }
                    data.add(new BigDecimal(temp[0]));
                    data.add(new BigDecimal(temp[1]));
                    data.add(new BigDecimal(temp[2]));
                    data.add(new BigDecimal(temp[3]));
                    data.add(new BigDecimal(temp[4]));
                    /*first = new BigDecimal(temp[1]);
                    second = new BigDecimal(temp[2]);
                    h = new BigDecimal(temp[3]);
                    while (first.compareTo(second) <= 0){
                        data.add(first);
                        first = first.add(h);
                    }*/
                    /*for (int i = 0; i < input.getRowCount(); i++){
                        data.add((BigDecimal) input.getValueAt(i, 0));
                        data.add((BigDecimal) input.getValueAt(i, 1));
                    }*/
                    Console.printEquations(data);
                    String function = oneFunction.getSelectedCheckbox().getLabel();
                    String method = oneEquation.getSelectedCheckbox().getLabel();
                    int functionNumber, methodNumber;
                    functionNumber = -1;
                    methodNumber = -1;
                    switch (function){
                        case "Задача Коши методом Эйлера":
                            functionNumber = 1;
                            break;
                        case "Задача Коши методом Рунге-Кутта":
                            functionNumber = 2;
                            break;
                        case "Задача Коши методом Адамса":
                            functionNumber = 3;
                            break;
                    }
                    switch (method){
                        case "y' = 3x^3 + 2x^2 + x - 3 + 10y":
                            methodNumber = 1;
                            break;
                        case "y' = 1 + 0.5y - x":
                            methodNumber = 2;
                            break;
                        case "y' = y + sin(x)":
                            methodNumber = 3;
                            break;
                    }



                    /*if (functionNumber == 3){
                        BigDecimal temp;
                        for (int i = 0; i < (data.size() - 5) / 2; i++){
                            temp = data.get(2 * (i + 1) + 1).subtract(data.get(2 * i + 1));
                            if (temp.subtract(data.get(2 * (i + 2) + 1).subtract(data.get(2 * (i + 1) + 1))).abs().compareTo(new BigDecimal(0.01)) > 0){
                                binarySearchLabel.setText("Расстояния между точками неодинаковы!");
                                return;
                            }
                        }

                    }*/

                    ArrayList<BigDecimal> answer = solver.solve(functionNumber, methodNumber, data);
                    //DefaultTableModel model = new DefaultTableModel();
                    model.setRowCount(0);
                    model.setColumnCount(0);
                    table = new JTable(model);
                    /*for (int i = 0; i < answer.get(0).size(); i++){
                        Console.print(answer.get(0).get(i).toString(), "г");
                    }*/
                    /*for (int i = 0; i < answer.get(2).size(); i++){
                        Console.print(answer.get(2).get(i).toString(), "г");
                    }*/
                    BigDecimal[] temp1 = new BigDecimal[4];
                    String[] temp2;
                    switch (functionNumber){
                        case 1:
                            temp2 = new String[]{"x", "y", "h * f(x, y)"};
                            break;
                        case 2:
                            temp2 = new String[]{"x", "y", "k1", "k2", "k3", "k4"};
                            break;
                        case 3:
                            temp2 = new String[]{"x", "y", "h * f(x, y)", "h^2 * df(x, y) / 2", "5h^3 * d^2f(x, y) / 12", "3h^4 * d^3f(x, y) / 8"};
                            break;
                        case 4:
                            temp2 = new String[]{"a", "b", "e"};
                            break;
                        case 5:
                            temp2 = new String[]{"a", "b", "e"};
                            break;
                        case 6:
                            temp2 = new String[]{"a", "b", "e"};
                            break;
                        default:
                            if (answer.size() == 4){
                                temp2 = new String[]{"a", "b", "e"};
                            }
                            else if (answer.size() == 5){
                                temp2 = new String[]{"a", "b", "c", "e"};
                            }
                            else {
                                temp2 = new String[]{"a", "b", "c", "d", "e"};
                            }
                    }
                    //String[] temp2 = new String[]{"Индекс", "x", "y", "Добавлено в значение интеграла"};
                    for (int i = 0; i < temp2.length; i++){
                        model.addColumn(i);
                    }
                    model.addRow(temp2);
                    ArrayList<BigDecimal> k = new ArrayList<BigDecimal>();
                    switch (methodNumber){
                        case 1:
                            k.add(Functions.g1_second(data.get(1), data.get(0)));
                            break;
                        case 2:
                            k.add(Functions.g2_second(data.get(1), data.get(0)));
                            break;
                        case 3:
                            k.add(Functions.g3_second(data.get(1), data.get(0)));
                            break;
                    }
                    int n = 0;
                    if (functionNumber == 1){
                        n = 3;
                    }
                    else if (functionNumber == 2){
                        n = 6;
                    }
                    else if (functionNumber == 3){
                        n = 6;
                    }
                    Console.printEquations(k);

                    if (functionNumber == 3){
                        for (int i = 0; i <= 3; i++){
                            temp2 = new String[]{answer.get(2 * i).toString(), answer.get(2 * i + 1).toString(), "", "", "", ""};
                            model.addRow(temp2);
                        }
                        for (int i = 0; i <= 3; i++){
                            answer.remove(0);
                            answer.remove(0);
                        }
                    }
                    //Console.printEquations(answer);
                    //System.out.println(Math.exp(-2));
                    for (int i = 0; i < (answer.size() / n); i++){
                        if (functionNumber == 1){
                            temp2 = new String[]{answer.get(3 * i).toString(), answer.get(3 * i + 1).toString(), answer.get(3 * i + 2).toString()};
                        }
                        if (functionNumber == 2){
                            temp2 = new String[]{answer.get(6 * i).toString(), answer.get(6 * i + 1).toString(), answer.get(6 * i + 2).toString(), answer.get(6 * i + 3).toString(), answer.get(6 * i + 4).toString(), answer.get(6 * i + 5).toString()};
                        }
                        if (functionNumber == 3){
                            temp2 = new String[]{answer.get(6 * i).toString(), answer.get(6 * i + 1).toString(), answer.get(6 * i + 2).toString(), answer.get(6 * i + 3).toString(), answer.get(6 * i + 4).toString(), answer.get(6 * i + 5).toString()};
                        }
                        model.addRow(temp2);
                        /*if (i < 3) {
                            model.addColumn("");
                        }*/
                    }
                    temp2 = new String[3];
                    temp2[0] = "f(x) = ";
                    /*for (int i = 0; i < answer.size(); i++){
                        String temp = "";
                        temp += answer.get(i).setScale(5, RoundingMode.HALF_EVEN) + "x^" + (answer.size() - i - 1) + "+";
                        temp2[i % 3] = temp;
                        if ((i + 1) % 3 == 0) {
                            Console.print(temp2[0] + temp2[1] + temp2[2], "г");
                            model.addRow(temp2);
                            temp2 = new String[3];
                        }
                    }*/
                    /*if (answer.size() % 3 != 0) {
                        model.addRow(temp2);
                    }*/
                    /*for (int i = 0; i < answer.get(0).size(); i++){
                        if (i < (answer.get(0).size() - 1)) {
                            temp1[0] = new BigDecimal(i);
                        }
                        else{
                            temp1[0] = new BigDecimal(0);
                        }
                        temp1[1] = answer.get(0).get(i);
                        temp1[2] = answer.get(1).get(i);
                        temp1[3] = answer.get(2).get(i);
                        model.addRow(temp1);
                    }*/
                    /*JFrame tableWindow = getWindow();
                    JPanel tablePanel = new JPanel();
                    tablePanel.add(table);
                    tableWindow.add(tablePanel);
                    tableWindow.setVisible(true);*/
                    JFreeChart chart;
                    ArrayList<BigDecimal> temp3 = new ArrayList<BigDecimal>();
                    for (int i = 0; i < (answer.size() / n); i++){
                        temp3.add(answer.get(i * n));
                        temp3.add(answer.get(i * n + 1));
                    }
                    chart = g.generateDifferentiation(temp3, k, methodNumber);
                    /*BigDecimal x = new BigDecimal(equationsInput.getText());
                    if (functionNumber != 7) {
                        Console.printEquations(data);
                        chart = g.generatePolynomial(data, x, answer, functionNumber);
                        switch (functionNumber){
                            case 1:
                                temp2 = new String[]{"f(x) = ", answer.get(0).setScale(3, RoundingMode.HALF_EVEN).toString() + "x + ", answer.get(1).setScale(3, RoundingMode.HALF_EVEN).toString()};
                                break;
                            case 2:
                                temp2 = new String[]{"f(x) = ", answer.get(0).setScale(3, RoundingMode.HALF_EVEN).toString() + "x^2 + ", answer.get(1).setScale(3, RoundingMode.HALF_EVEN).toString() + "x + ", answer.get(2).setScale(3, RoundingMode.HALF_EVEN).toString()};
                                break;
                            case 3:
                                temp2 = new String[]{"f(x) = ", answer.get(0).setScale(3, RoundingMode.HALF_EVEN).toString() + "x^3 + ", answer.get(1).setScale(3, RoundingMode.HALF_EVEN).toString() + "x^2 + ", answer.get(2).setScale(3, RoundingMode.HALF_EVEN).toString() + "x + ", answer.get(3).setScale(3, RoundingMode.HALF_EVEN).toString()};
                                break;
                            case 4:
                                temp2 = new String[]{"f(x) = ", answer.get(0).setScale(3, RoundingMode.HALF_EVEN).toString() + "e^(", answer.get(1).setScale(3, RoundingMode.HALF_EVEN).toString() + "x)"};
                                break;
                            case 5:
                                temp2 = new String[]{"f(x) = ", answer.get(0).setScale(3, RoundingMode.HALF_EVEN).toString() + "ln(x) + ", answer.get(1).setScale(3, RoundingMode.HALF_EVEN).toString()};
                                break;
                            case 6:
                                temp2 = new String[]{"f(x) = ", answer.get(0).setScale(3, RoundingMode.HALF_EVEN).toString() + "x^(", answer.get(1).setScale(3, RoundingMode.HALF_EVEN).toString() + ")"};
                                break;
                            default:
                                temp2 = new String[]{};
                        }
                        //model.addRow(temp2);
                    }
                    /*else{
                        functionNumber = answer.get(answer.size() - 1).intValue();
                        switch (functionNumber){
                            case 0:
                                temp2 = new String[]{"f(x) = ", answer.get(0).setScale(3, RoundingMode.HALF_EVEN).toString() + "x + ", answer.get(1).setScale(3, RoundingMode.HALF_EVEN).toString()};
                                break;
                            case 1:
                                temp2 = new String[]{"f(x) = ", answer.get(0).setScale(3, RoundingMode.HALF_EVEN).toString() + "x^2 + ", answer.get(1).setScale(3, RoundingMode.HALF_EVEN).toString() + "x + ", answer.get(2).setScale(3, RoundingMode.HALF_EVEN).toString()};
                                break;
                            case 2:
                                temp2 = new String[]{"f(x) = ", answer.get(0).setScale(3, RoundingMode.HALF_EVEN).toString() + "x^3 + ", answer.get(1).setScale(3, RoundingMode.HALF_EVEN).toString() + "x^2 + ", answer.get(2).setScale(3, RoundingMode.HALF_EVEN).toString() + "x + ", answer.get(3).setScale(3, RoundingMode.HALF_EVEN).toString()};
                                break;
                            case 3:
                                temp2 = new String[]{"f(x) = ", answer.get(0).setScale(3, RoundingMode.HALF_EVEN).toString() + "e^(", answer.get(1).setScale(3, RoundingMode.HALF_EVEN).toString() + "x)"};
                                break;
                            case 4:
                                temp2 = new String[]{"f(x) = ", answer.get(0).setScale(3, RoundingMode.HALF_EVEN).toString() + "ln(x) + ", answer.get(1).setScale(3, RoundingMode.HALF_EVEN).toString()};
                                break;
                            case 5:
                                temp2 = new String[]{"f(x) = ", answer.get(0).setScale(3, RoundingMode.HALF_EVEN).toString() + "x^(", answer.get(1).setScale(3, RoundingMode.HALF_EVEN).toString() + ")"};
                                break;
                            default:
                                temp2 = new String[]{};
                        }
                        model.addRow(temp2);
                        System.out.println(functionNumber);
                        answer.remove(answer.size() - 1);
                        for (int i = 0; i < answer.size(); i++){
                            System.out.println(answer.get(i));
                        }
                        chart = g.generateApproximation(data, answer, functionNumber + 1);
                    }*/
                    p.setChart(chart);
                }
                else{
                    //solver.solve(1, oneFunction.getSelectedCheckbox());
                    binarySearchLabel.setText("Число пробел число пробел число(0.000001 <= точность <= 1.0)");
                }
            }
        });
        //panel.add(label);
        window.add(panel);
        window.setLayout(dataAndGraphics);
        /*JPanel functions = new JPanel();
        GridLayout grid2 = new GridLayout(8, 1);
        CheckboxGroup oneEquation = new CheckboxGroup();
        Checkbox firstEquation = new Checkbox("3x^3 + 2x^2 + x - 3", oneEquation, true);
        Checkbox secondEquation = new Checkbox("0.5x^2 - x", oneEquation, false);
        JButton generate = new JButton("Сгенерировать 10 точек, начиная с данного x с шагом 1!");
        generate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!(validator.validateNumber(equationsInput.getText()))){
                    binarySearchLabel.setText("Введите, пожалуйста, только одно действительное число");
                }
                else if (validator.validateNumber(equationsInput.getText())){
                    binarySearchLabel.setText("");
                    while (input.getRowCount() > 0) {
                        input.deletePoint();
                    }
                    BigDecimal x = new BigDecimal(equationsInput.getText());
                    String function = oneEquation.getSelectedCheckbox().getLabel();
                    int functionNumber;
                    functionNumber = -1;
                    switch (function){
                        case "3x^3 + 2x^2 + x - 3":
                            functionNumber = 1;
                            break;
                        case "0.5x^2 - x":
                            functionNumber = 2;
                            break;
                    }
                    if (functionNumber == 1) {
                        for (int i = 0; i < 10; i++) {
                            input.addPoint(x, Functions.f1(x).setScale(2, RoundingMode.HALF_EVEN));
                            x = x.add(new BigDecimal(1));
                        }
                    }
                    else if (functionNumber == 2){
                        for (int i = 0; i < 10; i++) {
                            input.addPoint(x, Functions.f2(x).setScale(2, RoundingMode.HALF_EVEN));
                            x = x.add(new BigDecimal(1));
                        }
                    }
                }
            }
        });
        functions.setLayout(grid2);
        functions.add(equationsInput);
        functions.add(firstFunction);
        functions.add(secondFunction);
        functions.add(thirdFunction);
        functions.add(firstEquation);
        functions.add(secondEquation);
        functions.add(generate);
        window.add(functions);*/
        JPanel newPanel = new JPanel();
        JFreeChart chart = g.generateChart(new BigDecimal(-3), new BigDecimal(3), 3);
        p = new ChartPanel(chart);
        p.setPreferredSize(new Dimension(400, 700));
        p.setMouseZoomable(true);
        p.setMouseWheelEnabled(true);
        newPanel.add(p);
        JPanel tablePanel = new JPanel();
        tablePanel.add(table);
        window.add(newPanel);
        window.add(tablePanel);
        window.getLayout().removeLayoutComponent(newPanel);
        window.setVisible(true);
        /*JTable table = new JTable(100, 10);
        JFrame tableWindow = getWindow();
        JPanel tablePanel = new JPanel();
        tablePanel.add(table);
        tableWindow.add(tablePanel);
        tableWindow.setVisible(true);*/

        /*secantsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //solver.solve();
            }
        });

        iterationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //solver.solve();
            }
        });*/


    }

}