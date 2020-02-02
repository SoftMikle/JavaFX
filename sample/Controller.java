package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.File;

public class Controller {

    @FXML
    private Button btnRefresh;



    @FXML
    private void clickRefresh(ActionEvent event){
        File file = new File(".");
        String[] files = file.list();
        StringBuffer stringBuffer = new StringBuffer();
        for (String tmpString : files) {
            stringBuffer.append(tmpString).append(" \n");
        }
    }

//
//        File fileBuffer;
//
//        GridPane grid = new GridPane();
//        grid.setAlignment(Pos.TOP_LEFT);
//        grid.setHgap(10);
//        grid.setVgap(10);
//        grid.setPadding(new Insets(25, 25, 25, 25));
//
//        Scene scene = new Scene(grid, 720, 480);
//        primaryStage.setScene(scene);
//
//        Text sceneTitle = new Text("File manager");
//        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 18));
//        grid.add(sceneTitle, 0, 0, 2, 1);
//
//        Label fileName = new Label("File Name:");
//        grid.add(fileName, 0, 1);
//
//        TextField fileTextField = new TextField();
//        grid.add(fileTextField, 1, 1);
//
//
//        StringBuffer stringBuffer = new StringBuffer();
//        for (String tmpString : files) {
//            stringBuffer.append(tmpString).append(" \n");
//        }
//
//        Text filesToScreen = new Text(stringBuffer.toString());
//        grid.add(filesToScreen, 0, 4);
//
//
//        primaryStage.show();
//    }


}