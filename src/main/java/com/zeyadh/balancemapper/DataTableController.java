package com.zeyadh.balancemapper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;

public class DataTableController implements Initializable{
    @FXML
    private TableColumn<cashRec, Double> cashCol;

    @FXML
    private TableView<cashRec> cashTable;

    @FXML
    private TableColumn<cashRec, String> categoryCol;

    @FXML
    private TableColumn<cashRec, Date> dateCol;

    @FXML
    private TableColumn<cashRec, String> commCol;

    @FXML
    private TableColumn<cashRec, Integer> idCol;

    @FXML
    private TableColumn<cashRec, String> nameCol;
    int id;
    double cash;
    String name, category, comment;
    Date date;

    ObservableList<cashRec> data;

    public DataTableController() throws SQLException {
        DBConnection.getConnect();
        ResultSet resultSet = DBConnection.statement.executeQuery("SELECT * FROM `homedata`");
        data = FXCollections.observableArrayList();
        resultSet.beforeFirst();
        while (resultSet.next()){
            id = resultSet.getInt("id");
            cash = resultSet.getDouble("cash");
            name = resultSet.getString("cashExp");
            category = resultSet.getString("category");
            date = resultSet.getDate("cashDate");
            comment = resultSet.getString("cashComms");
            data.add(new cashRec(id, name, cash, category, date, comment));
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        cashCol.setCellValueFactory(new PropertyValueFactory<>("cash"));
        categoryCol.setCellValueFactory(new PropertyValueFactory<>("category"));
        commCol.setCellValueFactory(new PropertyValueFactory<>("comments"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        cashTable.setItems(data);
    }
}
