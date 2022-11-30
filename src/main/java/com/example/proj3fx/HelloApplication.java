package com.example.proj3fx;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class HelloApplication extends Application {
    static Button calculatePath = new Button("Find");
    static int numOfClicks = 0;
    static ComboBox<String> comboBoxCitiesNamesSrc = new ComboBox<>();
    static ComboBox<String> comboBoxCitiesNamesDest = new ComboBox<>();
    private static Graph graph;
    Pane pane = new Pane();
    TextField textFieldPath = new TextField();
    TextField textFieldDistance = new TextField();
    File file;
    private ArrayList<Arrow> addedArrows = new ArrayList<>();

    public static void main(String[] args) {
        launch();
    }

    public static void handleEventForCircle(double x, double y, MouseEvent e) {


        print(x, y, e);

        //if (consumeEventCbx.isSelected()) {
        //e.consume();
        //}
    }

   /* public void handleEvent(MouseEvent e) {
        print(e);
    }*/

    public static void print(double xGiven, double yGiven, MouseEvent e) {
        String type = e.getEventType().getName();
        String source = e.getSource().getClass().getSimpleName();
        String target = e.getTarget().getClass().getSimpleName();

        // Get coordinates of the mouse relative to the event source
        double x = e.getX();
        double y = e.getY();
       // System.out.println("Type=" + type + ", Target=" + target + ", Source=" + source + ", location(" + x + ", " + y + ")");

        Vertex vertexFound = new Vertex();

        int ind = 0;
        for (; ind < graph.getArrayList().size(); ind++) {

            City cityLoop = new City();
            cityLoop.setX(x);
            cityLoop.setY(y);
            if (cityLoop.equalsCoordinates(graph.getArrayList().get(ind).getCity())) {
                vertexFound = graph.getArrayList().get(ind);
                break;
            }
        }
        if (numOfClicks < 2) {


            if ((x + 3 >= xGiven || x - 3 <= xGiven) && (y + 3 >= yGiven || y - 3 <= yGiven)) {


                if (numOfClicks == 0) { // src combobox


                    //comboBoxCitiesNamesSrc.getSelectionModel().select(vertexFound.getNumToIndexArray());
                    comboBoxCitiesNamesSrc.setValue(vertexFound.getCity().getName());
                   // System.out.println("ind:" + ind + "\n" + vertexFound.getCity().getName());

                    //vertexFound.getCity().


                    numOfClicks++;


                } else { // dest combobox
                    numOfClicks++;
                    //comboBoxCitiesNamesDest.getSelectionModel().select(vertexFound.getNumToIndexArray());
                    comboBoxCitiesNamesDest.setValue(vertexFound.getCity().getName());
                   // System.out.println("ind:" + ind + "\n" + vertexFound.getCity().getName());

                    calculatePath.fire();


                   // System.out.println("Type=" + type + ", Target=" + target + ", Source=" + source + ", location(" + x + ", " + y + ")");


                }
            }
        } else { // src combobox (re-write the src(if it is the 3rd click))
            //comboBoxCitiesNamesSrc.getSelectionModel().select(vertexFound.getNumToIndexArray());
            comboBoxCitiesNamesSrc.setValue(vertexFound.getCity().getName());
          //  System.out.println("ind:" + ind + "\n" + vertexFound.getCity().getName());

            numOfClicks = 1;
           // System.out.println("Type=" + type + ", Target=" + target + ", Source=" + source + ", location(" + x + ", " + y + ")");


        }

    }

    public static void addCircleToPane(City city, Pane pane) {

        Circle circle = new Circle();
        circle.setRadius(3);
        circle.setCenterX(city.getX());
        circle.setCenterY(city.getY());
        circle.setFill(Color.RED);
        Label labelForCity = new Label();
        labelForCity.setFont(Font.font("arial", FontWeight.THIN, FontPosture.REGULAR, 9));

        labelForCity.setLabelFor(circle);
        labelForCity.setText(city.getName());
        labelForCity.relocate(city.getX() - 1, city.getY() - 1);
        pane.getChildren().addAll(labelForCity, circle);
        //Label label =new Label("Aws");


        //HBox hBoxForCityName = new HBox(2);
        //hBoxForCityName.getChildren().addAll(circle,new Label(city.getName()));
        //pane.getChildren().add(hBoxForCityName);
        //pane.getChildren().add(new Label("Aws is here"));
        EventHandler<MouseEvent> circleMeHandler = e -> handleEventForCircle(city.getX(), city.getY(), e);
        circle.addEventHandler(MouseEvent.MOUSE_CLICKED, circleMeHandler);

    }

    @Override
    public void start(Stage stage) throws IOException {
        /*FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();*/

        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(10, 10, 70, 10));

        Label welcomeLabel = new Label("Welcome to Dijkstra Shortest path finder");
        welcomeLabel.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
        welcomeLabel.setTextFill(Color.DARKGRAY);

        borderPane.setTop(welcomeLabel);
        BorderPane.setAlignment(welcomeLabel, Pos.TOP_CENTER);

        VBox vBox = new VBox(10);

        Label srcLabel = new Label("Source");
        Label destLabel = new Label("Target");

        HBox hBoxSrc = new HBox(10);
        HBox hBoxDest = new HBox(10);

        /*comboBoxCitiesNamesSrc.getItems().addAll("search a name",
                "Average Frequency of a name",
                "Name with max frequency",
                "Total number of babies in a selected year",
                "Export the data to a file");*/

        Button readFileButton = new Button("Read File");

        hBoxSrc.getChildren().addAll(srcLabel, comboBoxCitiesNamesSrc);

        /*comboBoxCitiesNamesDest.getItems().addAll("search a name",
                "Average Frequency of a name",
                "Name with max frequency",
                "Total number of babies in a selected year",
                "Export the data to a file");*/
        hBoxDest.getChildren().addAll(destLabel, comboBoxCitiesNamesDest);

       /* ComboBox<String> comboBoxCitiesNamesDest = comboBoxCitiesNamesSrc;
        hBoxDest.getChildren().addAll(destLabel, comboBoxCitiesNamesDest);*/


        vBox.getChildren().addAll(readFileButton, hBoxSrc, hBoxDest);


        Label pathLabel = new Label("Path:");
        Label distanceLabel = new Label("Distance:");


        textFieldPath.setPrefWidth(500);
        vBox.getChildren().addAll(pathLabel, textFieldPath, distanceLabel, textFieldDistance, calculatePath);
       // borderPane.setRight(vBox);
       // borderPane.setCenter(vBox);
        borderPane.setBottom(vBox);
       // Image image1 = new Image(new File("C:\\Users\\hp\\OneDrive\\Desktop\\Semesters\\Pure_Semesters\\Third-Year_121\\121-1\\Palestine_location_map_wide.png").toURI().toURL().toExternalForm());
        Image image1 = new Image(new File("C:\\Users\\hp\\OneDrive\\Desktop\\Semesters\\Pure_Semesters\\Third-Year_121\\121-1\\algorithm-comp336\\projects\\proj3\\270190248.png").toURI().toURL().toExternalForm());



        readFileButton.setOnAction(actionEvent -> {
            fileStage(new Label("a"), pane, comboBoxCitiesNamesSrc, comboBoxCitiesNamesDest).show();
        });
       /* Circle circle = new Circle();
        circle.setRadius(5);
        circle.setCenterX(20);
        circle.setCenterY(10);
        circle.setFill(Color.RED);*/


        ImageView imageView = new ImageView();
        imageView.setImage(image1);

        imageView.setFitHeight(712);
        imageView.setFitWidth(570);
        //imageView.ma
        pane.getChildren().add(imageView);
        //pane.setPadding(new Insets(10));

        //pane.getChildren().add(circle);


        borderPane.setLeft(pane);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setPadding(new Insets(10));

        scrollPane.setContent(borderPane);

        Scene scene = new Scene(scrollPane, 950, 505);
        //scene.getStylesheets().add("C:\\Users\\hp\\Desktop\\intelijWorkSpace\\alrorithm-course-projects\\proj3-fx\\proj3-fx\\src\\main\\java\\com\\example\\proj3fx\\style.css");
        //scene.getStylesheets().add(getClass().getResource("C:\\Users\\hp\\Desktop\\intelijWorkSpace\\alrorithm-course-projects\\proj3-fx\\proj3-fx\\src\\main\\java\\com\\example\\proj3fx\\style.css").toExternalForm());

        stage.setTitle("Dijkstra shortest path");
        stage.setScene(scene);

        /**/
        /*addCircleToPane(50, 50, pane);
        addCircleToPane(70, 70, pane);
        addCircleToPane(80, 80, pane);
        addCircleToPane(90, 90, pane);


        addArrow(50, 50, 70, 70, pane);*/
        /**/

        //EventHandler<MouseEvent> handler = e -> handleEvent(e);
        //EventHandler<MouseEvent> circleMeHandler = e -> handleEventForCircle(e);

        //stage.addEventHandler(MouseEvent.MOUSE_CLICKED, handler);
        //scene.addEventHandler(MouseEvent.MOUSE_CLICKED, handler);
        //pane.addEventHandler(MouseEvent.MOUSE_CLICKED, handler);
        //circle.addEventHandler(MouseEvent.MOUSE_CLICKED, circleMeHandler);

        calculatePath.setOnAction(actionEvent -> {

            Dijkstra dijkstra = new Dijkstra();
            int srcIndex = comboBoxCitiesNamesSrc.getSelectionModel().getSelectedIndex();
            int destIndex = comboBoxCitiesNamesDest.getSelectionModel().getSelectedIndex();

            Vertex src = graph.getArrayList().get(srcIndex);
            Vertex dest = graph.getArrayList().get(destIndex);


            //////


            /////////

            dijkstra.dijkstra(graph.getG(), src,dest);
            //ArrayList<Double> cost = new ArrayList<>();


            ArrayList<Vertex> results = dijkstra.getResultsPaths(src, dest);
            String resultText = "";


            pane.getChildren().removeAll(addedArrows);

            addedArrows.clear();

            double resCost = dijkstra.getPathDist()[dest.getNumToIndexArray()].getCost();
            textFieldDistance.setText(resCost + "");
            if (resCost == Integer.MAX_VALUE) {
                textFieldPath.setText("No Path");
                textFieldDistance.setText("∞");
                return;
            }

            if (results.get(0) != results.get(1)) {
                for (Vertex obj : results) {
                    resultText += obj.getCity().getName() + "-> ";
                    //resCost += obj.getCost();


                }

                for (int i = 0; i < results.size() - 1; i++) {
                    //pane.getChildren().
                    addArrow(results.get(i).getCity().getX(), results.get(i).getCity().getY(),
                            results.get(i + 1).getCity().getX(), results.get(i + 1).getCity().getY(), pane);

                }
                textFieldPath.setText(resultText);
            } else {
                textFieldPath.setText("Direct");

                addArrow(src.getCity().getX(), src.getCity().getY(),
                        dest.getCity().getX(), dest.getCity().getY(), pane);

            }


        });


        stage.show();
    }

    public void addArrow(double x1, double y1, double x2, double y2, Pane pane) {

        Arrow arrow = new Arrow();
        arrow.setStartX(x1);
        arrow.setStartY(y1);
        arrow.setEndX(x2);
        arrow.setEndY(y2);
        addedArrows.add(arrow);
        pane.getChildren().add(arrow);
    }

    /*public void putData(File file, Pane pane) throws Exception {
        try {
            // graph = Operations.readFile(new File("C:\\Users\\hp\\Desktop\\intelijWorkSpace\\alrorithm-course-projects\\proj3\\proj3\\src\\inputFile.txt"));
            //graph = Operations.readFile(file, pane);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }*/


    private Stage fileStage(Label outputLabel, Pane pane, ComboBox<String> comboBoxCitiesNamesSrc, ComboBox<String> comboBoxCitiesNamesDest) {

        Stage inputFileStage = new Stage();
        inputFileStage.setTitle("File chooser");

        Label inputFileLabel = new Label("Please Choose the file");
        inputFileLabel.setFont(Font.font("Verdana", 20));

        BorderPane inputFileBorderPane = new BorderPane();
        inputFileBorderPane.setPadding(new Insets(15));

        inputFileBorderPane.setTop(inputFileLabel);

        Button fileButton = new Button("Browse the file");
        inputFileBorderPane.setCenter(fileButton);

        BorderPane.setAlignment(inputFileLabel, Pos.TOP_CENTER);
        BorderPane.setAlignment(fileButton, Pos.CENTER);

        Scene fileScene = new Scene(inputFileBorderPane, 400, 300);
        inputFileStage.setScene(fileScene);

        FileChooser fileChooser = new FileChooser();

        fileChooser.setTitle("Open the File");

        fileButton.setOnAction(actionEvent -> {


            File file = fileChooser.showOpenDialog(inputFileStage);

            if (file != null) {

                this.file = file;
                //linesArrayList.clear();

                try {
                    graph = Operations.readFile(file, pane, comboBoxCitiesNamesSrc, comboBoxCitiesNamesDest);
                    // City city =  comboBoxCitiesNamesSrc.getSelectionModel().getSelectedItem()


                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
            //CreateHuffman.readFile(file, linesArrayList);
            inputFileStage.close();

        });

        return inputFileStage;
    }
    /*
    private  class CircleClickHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {

            Dijkstra dijkstra = new Dijkstra();
            int srcIndex = comboBoxCitiesNamesSrc.getSelectionModel().getSelectedIndex();
            int destIndex = comboBoxCitiesNamesDest.getSelectionModel().getSelectedIndex();

            Vertex src = graph.getArrayList().get(srcIndex);
            Vertex dest = graph.getArrayList().get(destIndex);


            //////


            /////////

            dijkstra.dijkstra(graph.getG(), src);
            //ArrayList<Double> cost = new ArrayList<>();


            ArrayList<Vertex> results = dijkstra.getResultsPaths(src, dest);
            String resultText = "";


            pane.getChildren().removeAll(addedArrows);

            addedArrows.clear();

            double resCost = dijkstra.getPathDist()[dest.getNumToIndexArray()].getCost();
            textFieldDistance.setText(resCost + "");
            if (resCost == Integer.MAX_VALUE) {
                textFieldPath.setText("No Path");
                return;
            }

            if (results.get(0) != results.get(1)) {
                for (Vertex obj : results) {
                    resultText += obj.getCity().getName() + ", ";
                    //resCost += obj.getCost();


                }

                for (int i = 0; i < results.size() - 1; i++) {
                    //pane.getChildren().
                    addArrow(results.get(i).getCity().getLongitude(), results.get(i).getCity().getLatitude(),
                            results.get(i + 1).getCity().getLongitude(), results.get(i + 1).getCity().getLatitude(), pane);

                }
                textFieldPath.setText(resultText);
            } else {
                textFieldPath.setText("Direct");

                addArrow(src.getCity().getLongitude(), src.getCity().getLatitude(),
                        dest.getCity().getLongitude(), dest.getCity().getLatitude(), pane);

            }
        }
    }*/
}