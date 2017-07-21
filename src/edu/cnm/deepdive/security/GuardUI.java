package edu.cnm.deepdive.security;

import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GuardUI extends Application {

  private static final String STYLE_SHEET_PATH = "/styles/application.css";
  private static final String LAYOUT_RESOURCE = "/layouts/Generate.fxml";
  private static final String UI_BUNDLE = "resources/ui";
  
  private static final String WINDOW_TITLE_KEY = "window.title";
  
  @FXML
  VBox passwordOptions;
  @FXML
  VBox passphraseOptions;
  @FXML
  CheckBox upperIncluded;
  @FXML
  CheckBox lowerIncluded;
  @FXML
  CheckBox punctuationIncluded;
  @FXML
  CheckBox ambiguousExcluded;
  
  private SecurePasswordGenerator passwordGenerator = new SecurePasswordGenerator();

  public static void main(String[] args) {
    launch(args);

  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    ResourceBundle uiBundle = ResourceBundle.getBundle(UI_BUNDLE);
    Pane pane = FXMLLoader.load(getClass().getResource(LAYOUT_RESOURCE), uiBundle);
    Scene scene = new Scene(pane, 400, 400);
    scene.getStylesheets().add(getClass().getResource(STYLE_SHEET_PATH).toExternalForm());
    primaryStage.setScene(scene);
    primaryStage.setTitle(uiBundle.getString(WINDOW_TITLE_KEY));
    upperIncluded.setSelected(passwordGenerator.isUpperCaseIncluded());
    lowerIncluded.setSelected(passwordGenerator.isLowerCaseIncluded());
    punctuationIncluded.setSelected(passwordGenerator.isPunctuationIncluded());
    ambiguousExcluded.setSelected(passwordGenerator.isAmbiguousExcluded());
    primaryStage.show();
  }

}
