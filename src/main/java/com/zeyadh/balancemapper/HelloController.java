package com.zeyadh.balancemapper;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private Label home_cash, postal_cash, spent_cash;

    @FXML
    private TextField explain_input,
            cash_input,
            source_amount,
            postal_input;
    @FXML
    ChoiceBox<String> category_check, source_check;
    private final String[] categoryChoices = {"مشوار كلية", "مستلزمات بيت", "فواتير", "مستلزمات خاصة", "اخري"};
    private final String[] moneySources = {"أستاذ علاء", "أستاذ عبدالقادر", "أستاذة مروة", "المعاش", "فيزا توفير", "فيزا"};


    @FXML
    private DatePicker date_input, date_input2, date_input3;
    @FXML
    private TextArea comms_input;

    ResultSet resultSet;

    public HelloController() {
    }

    @FXML
    protected void addSpentValue() throws SQLException {
        DBConnection.getConnect();
        resultSet = DBConnection.statement.executeQuery("SELECT * FROM `homedata`");
        resultSet.moveToInsertRow();
        resultSet.updateDouble("cash", Double.parseDouble(cash_input.getText()));
        resultSet.updateString("cashEXP",explain_input.getText());
        resultSet.updateString("category", category_check.getValue());
        if (date_input2.getValue() != null)
            resultSet.updateDate("cashDate", Date.valueOf(date_input2.getValue()));

        if (!comms_input.getText().equals(""))
            resultSet.updateString("cashComms", comms_input.getText());
        resultSet.insertRow();

        updateLabels();
        clearFields();
    }

    @FXML
    protected void addPostalValue() throws SQLException {
        DBConnection.getConnect();
        resultSet = DBConnection.statement.executeQuery("SELECT * FROM `postamoney`");
        resultSet.moveToInsertRow();
        resultSet.updateDouble("cash", Double.parseDouble(postal_input.getText()));
        if (date_input3.getValue() != null)
            resultSet.updateDate("date", Date.valueOf(date_input3.getValue()));
        resultSet.insertRow();
        updateLabels();
        clearFields();
    }

    @FXML
    protected void addSourceValue() throws SQLException {
        DBConnection.getConnect();
        resultSet = DBConnection.statement.executeQuery("SELECT * FROM `in_money`");
        resultSet.moveToInsertRow();
        resultSet.updateDouble("cash", Double.parseDouble(source_amount.getText()));
        resultSet.updateString("cash_source", source_check.getValue());
        if (date_input.getValue() != null)
            resultSet.updateDate("date", Date.valueOf(date_input2.getValue()));
        resultSet.insertRow();
        updateLabels();
        clearFields();
    }

    private double getSourceCash() throws SQLException {
        double currentCash = 0;
        DBConnection.getConnect();
        resultSet = DBConnection.statement.executeQuery("SELECT `cash` FROM `in_money`");
        resultSet.beforeFirst();
        while (resultSet.next())
            currentCash += resultSet.getDouble("cash");
        return currentCash;
    }
    protected double getSpentCash() throws SQLException {
        double currentCash = 0;
        DBConnection.getConnect();
        resultSet = DBConnection.statement.executeQuery("SELECT `cash` FROM `homedata`");
        resultSet.beforeFirst();
        while (resultSet.next())
            currentCash += resultSet.getDouble("cash");
        return currentCash;
    }
    protected double getPostalValue() throws SQLException {
        double currentCash = 0;
        DBConnection.getConnect();
        resultSet = DBConnection.statement.executeQuery("SELECT `cash` FROM `postamoney`");
        resultSet.beforeFirst();
        while (resultSet.next())
            currentCash += resultSet.getDouble("cash");
        return currentCash;
    }
    @FXML
    protected double currentHomeCash() throws SQLException {
        return getSourceCash() - (getPostalValue() + getSpentCash());
    }

    @FXML
    private void updateLabels() throws SQLException {
        spent_cash.setText(String.valueOf(getSpentCash()));
        home_cash.setText(String.valueOf(currentHomeCash()));
        postal_cash.setText(String.valueOf(getPostalValue()));
    }
    @FXML
    private void clearFields(){
        cash_input.clear();
        explain_input.clear();
        comms_input.clear();
        postal_input.clear();
        source_amount.clear();
        date_input.setValue(null);
        date_input2.setValue(null);
        date_input3.setValue(null);
    }
    @FXML
    private void openInvoiceWindow() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("dataTable.fxml"));
        Scene invoiceScene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("سجل المصروفات");

        Image icon = new Image("moneyicon.png");
        stage.getIcons().add(icon);

        stage.setScene(invoiceScene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        category_check.setValue("مشوار كلية");
        source_check.setValue("المعاش");
        source_check.getItems().addAll(moneySources);
        category_check.getItems().addAll(categoryChoices);
        try {
            updateLabels();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}