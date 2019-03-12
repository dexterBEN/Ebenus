package com.cours.ebenus.main;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.stage.Stage;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MainApp extends Application {

    private static final Log logger = LogFactory.getLog(MainApp.class);

    @Override
    public void start(Stage stage) throws Exception {
        loadLogin(stage);
    }

    public void loadLogin(Stage stage) throws Exception {

    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
