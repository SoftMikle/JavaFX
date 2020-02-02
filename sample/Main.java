package sample;

import javafx.application.Application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("JavaFX File manager");

//        Group root = new Group();

        File fileList = new File(".");
        String[] files = fileList.list();

        List<Node> nodes = new ArrayList<Node>();

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_LEFT);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Scene scene = new Scene(grid, 720, 480);
        primaryStage.setScene(scene);

        Text sceneTitle = new Text("File manager");
        nodes.add(sceneTitle);
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 18));
        grid.add(sceneTitle, 0, 0, 2, 1);

        Label fileName = new Label("File or directory Name/ Path:");
        grid.add(fileName, 0, 1);
        nodes.add(fileName);

        TextField fileNameEnteringField = new TextField();
        grid.add(fileNameEnteringField, 1, 1);
        nodes.add(fileNameEnteringField);


        Button btnRemember = new Button("Remember");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_LEFT);
        hbBtn.getChildren().add(btnRemember);
        grid.add(hbBtn, 0, 2);
        nodes.add(hbBtn);


        StringBuffer stringBuffer = new StringBuffer();
        for (String tmpString : files) {
            stringBuffer.append(tmpString).append(" \n");
        }

        Label rememberedFileName = new Label("default");
        nodes.add(rememberedFileName);
        grid.add(rememberedFileName, 1, 2);


        btnRemember.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String tmpFileName = fileNameEnteringField.getText();
                rememberedFileName.setAccessibleText(tmpFileName);
                rememberedFileName.setText(tmpFileName);
                grid.getChildren().setAll(nodes);
            }
        });


        Button btnCreate = new Button("Create remembered instance");
        grid.add(btnCreate, 0, 3);
        nodes.add(btnCreate);


        btnCreate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                File file = new File(nodes.get(4).getAccessibleText());
                if (!file.exists()){
                    try {
                        file.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Button btnDelete = new Button("Delete remembered instance");
        grid.add(btnDelete, 0, 4);
        nodes.add(btnDelete);

        Label deletedFileName = new Label();
        nodes.add(deletedFileName);
        grid.add(deletedFileName, 1, 4);


        btnDelete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                File file = new File(nodes.get(4).getAccessibleText());
                if(!file.exists()){
                    deletedFileName.setText("File/directory " + nodes.get(4).getAccessibleText() + " not found or directory not empty");
                } else {
                    if(file.isFile()){
                        if(file.delete()){
                            deletedFileName.setText("File deleted");
                        } else {
                            deletedFileName.setText("Error");
                        }
                    } else {
                        if(file.delete()){
                            deletedFileName.setText("Directory deleted");
                        } else {
                            deletedFileName.setText("Error");
                        }
                    }
                }


            }
        });


        Button btnShowRootDirectory = new Button("Show root directory");
        grid.add(btnShowRootDirectory, 0, 5);
        nodes.add(btnShowRootDirectory);


        Label filesToScreen = new Label(stringBuffer.toString());
        nodes.add(filesToScreen);
        grid.add(filesToScreen, 0, 6);


        btnShowRootDirectory.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                File fileList = new File(".");
                String[] files = fileList.list();
                StringBuffer stringBuffer = new StringBuffer();
                for (String tmpString : files) {
                    stringBuffer.append(tmpString).append(" \n");
                }
                filesToScreen.setText(stringBuffer.toString());
                grid.getChildren().setAll(nodes);
            }
        });


        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
