/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adminpanelpage;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author labib
 */
public class StatisticsPageController implements Initializable {

    @FXML
    private AnchorPane statisticsAnchorPane;
    @FXML
    private BarChart<?, ?> barChart;
    @FXML
    private LineChart<?, ?> lineChart;
    @FXML
    private NumberAxis BY;
    @FXML
    private CategoryAxis BX;
    @FXML
    private NumberAxis LY;
    @FXML
    private CategoryAxis LX;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        XYChart.Series set = new XYChart.Series<>();
        set.getData().add(new XYChart.Data("O+", 100));
        set.getData().add(new XYChart.Data("O-", 60));
        set.getData().add(new XYChart.Data("A+", 71));
        set.getData().add(new XYChart.Data("A-", 67));
        set.getData().add(new XYChart.Data("B+", 80));
        set.getData().add(new XYChart.Data("B-", 56));
        set.getData().add(new XYChart.Data("AB+", 20));
        set.getData().add(new XYChart.Data("AB-", 4));     
        barChart.getData().addAll(set);
        
        
        XYChart.Series set2 = new XYChart.Series<>();
        set2.getData().add(new XYChart.Data("O+", 30));
        set2.getData().add(new XYChart.Data("O-", 18));
        set2.getData().add(new XYChart.Data("A+", 21));
        set2.getData().add(new XYChart.Data("A-", 8));
        set2.getData().add(new XYChart.Data("B+", 23));
        set2.getData().add(new XYChart.Data("B-", 12));
        set2.getData().add(new XYChart.Data("AB+", 2));
        set2.getData().add(new XYChart.Data("AB-", 1));     
        lineChart.getData().addAll(set2);
        
    }    
    
}
