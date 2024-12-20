// Name: Lucy Finnerty
// Date: 12/5/24
// Purpose: A JavaFX application that creates and displays an array of 10 books (Fiction and NonFiction).
// The book details (title and price) are displayed in a graphical interface using a ListView.
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
public class BookArray extends Application {
    @Override
    public void start(Stage primaryStage) {
        // create an array of books
        Book[] books = new Book[10];
        books[0] = new Fiction("Fiction Book 1");
        books[1] = new NonFiction("NonFiction Book 1");
        books[2] = new Fiction("Fiction Book 2");
        books[3] = new NonFiction("NonFiction Book 2");
        books[4] = new Fiction("Fiction Book 3");
        books[5] = new NonFiction("NonFiction Book 3");
        books[6] = new Fiction("Fiction Book 4");
        books[7] = new NonFiction("NonFiction Book 4");
        books[8] = new Fiction("Fiction Book 5");
        books[9] = new NonFiction("NonFiction Book 5");

        // create a ListView to display book details
        ListView<String> bookListView = new ListView<>();

        // add book details to the ListView
        for (Book book : books) {
            bookListView.getItems().add("Title: " + book.getTitle() + " - Price: $" + book.getPrice());
        }
        // create a VBox layout and add the ListView
        VBox layout = new VBox(bookListView);

        // set up the scene
        Scene scene = new Scene(layout, 400, 300);

        // configure the primary stage
        primaryStage.setTitle("Book Details");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args); // launch the JavaFX application
    }
}