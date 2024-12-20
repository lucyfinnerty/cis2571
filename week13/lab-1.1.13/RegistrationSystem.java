// Name: Lucy Finnerty
// Date: 11/11/24
// Purpose: This application allows the user to fill out a registration form using radio buttons,
// check boxes, and utilizes an event handler to calculate total fees based on user selection of options.
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

public class RegistrationSystem extends Application {
    // constant prices for registration, optional dinner, and chosen workshops
    private final double GENERAL_REG_FEE = 895.0;
    private final double STUDENT_REG_FEE = 495.0;
    private final double DINNER_FEE = 30.0;
    private final double WORKSHOP_ECOM_FEE = 295.0;
    private final double WORKSHOP_WEB_FEE = 295.0;
    private final double WORKSHOP_ADV_JAVA_FEE = 395.0;
    private final double WORKSHOP_NET_SEC_FEE = 395.0;

    // UI elements as instance variables
    private RadioButton generalButton; // if user is not a student, this button may be selected
    private RadioButton studentButton; // if user is a student, this button may be selected
    private CheckBox dinnerCheckBox; // check box indicating optional dinner
    private ListView<String> workshopsListView; // list view that displays all 4 workshops
    private Label resultLabel; // label next to calculated result

    public static void main(String[] args) {
        launch(args); // launches start method
    }
    /**
     * This method starts the application by creating a VBox layout with other VBox sections
     * for each section of the Conference Registration System. Each Section utilizes radio buttons
     * or check boxes, as well as labels to create the UI.
     * 
     * @param primaryStage
     */
    @Override
    public void start(Stage primaryStage) {
        // layout for future buttons/labels
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));

        // registration type section
        Label registrationLabel = new Label("Select Registration Type:"); // label for the registration type selection section
        generalButton = new RadioButton("General ($895)"); // radio button for general registration
        studentButton = new RadioButton("Student ($495)"); // radio button for student registration
        ToggleGroup registrationGroup = new ToggleGroup(); // a ToggleGroup to ensure only one registration type can be selected at a time
        generalButton.setToggleGroup(registrationGroup); // add general registration button to ToggleGroup
        studentButton.setToggleGroup(registrationGroup); // add student registration button to ToggleGroup
        // adds the registration label, general registration, and student registration radio buttons to another VBox
        VBox registrationBox = new VBox(5, registrationLabel, generalButton, studentButton);

        // optional dinner checkbox
        dinnerCheckBox = new CheckBox("Opening Night Dinner with Keynote Speech ($30)");

        // workshops section
        Label workshopsLabel = new Label("Select Optional Workshops:"); //label for the worshops selction section
        workshopsListView = new ListView<>(FXCollections.observableArrayList( // list view displays all 4 workshops which allows
                "Introduction to E-commerce ($295)",                          // for more than 1 selection using ctrl + left click on item
                "The Future of the Web ($295)",
                "Advanced Java Programming ($395)",
                "Network Security ($395)"
        ));
        // set list view selection mode to MULTIPLE, so more than 1 workshop can be selected
        workshopsListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        VBox workshopsBox = new VBox(5, workshopsLabel, workshopsListView);

        // calculate Button and display result
        Button calculateButton = new Button("Calculate Total");
        resultLabel = new Label("Total: $0.00");

        // add all components to main layout
        layout.getChildren().addAll(registrationBox, dinnerCheckBox, workshopsBox, calculateButton, resultLabel);

        calculateButton.setOnAction(new CalcButtonHandler()); // set event hadler for calc button to compute total cost when clicked

        Scene scene = new Scene(layout, 400, 400); // create new scene
        primaryStage.setScene(scene); // set scene on stage
        primaryStage.setTitle("Conference Registration System"); // give gui window a name
        primaryStage.show(); // display
    }
    /**
     * Event handler class for calculateButton
     */
    class CalcButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            // final fee
            double total = 0;
            // if user is not a student, fee is $895
            if (generalButton.isSelected()) {
                total += GENERAL_REG_FEE; // +$895
                // if user is a student, fee is $495
            } else if (studentButton.isSelected()) {
                total += STUDENT_REG_FEE; // +$495
            }
            // add dinner fee if selected
            if (dinnerCheckBox.isSelected()) {
                total += DINNER_FEE; // // +$30
            }
            // handle multiple workshop selections
            for (String selectedWorkshop : workshopsListView.getSelectionModel().getSelectedItems()) {
                // add workshop fees if selected
                if (selectedWorkshop.contains("E-commerce")) {
                    total += WORKSHOP_ECOM_FEE; // +$295
                } else if (selectedWorkshop.contains("Web")) {
                    total += WORKSHOP_WEB_FEE; // +$295
                } else if (selectedWorkshop.contains("Java")) {
                    total += WORKSHOP_ADV_JAVA_FEE; // +$395
                } else if (selectedWorkshop.contains("Security")) {
                    total += WORKSHOP_NET_SEC_FEE; // +$395
                }
            }
            // update result label
            resultLabel.setText(String.format("Total: $%.2f", total));
       }
    }
}