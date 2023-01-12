package Assignment4;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.*;
import javafx.scene.input.*;

public class ElectronicStoreApp extends Application {
    private ElectronicStoreView view;
    private ElectronicStore model;

    public void start(Stage primaryStage) {
        this.model = model.createStore();
        view = new ElectronicStoreView(model);

        view.getAddCartBut().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                handleAdd();
            }
        });

        view.getRemoveCartBut().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                handleRemove();
            }
        });

        view.getCompleteSaleBut().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                handleCompleteSale();
            }
        });

        view.getResetBut().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEVent) {
                handleReset();
            }
        });

        view.getStockList().setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                handleStockListSelection();
            }
        });

        view.getCartList().setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                handleCartListSelection();
            }
        });

        view.getSaleNumText().setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                handleFirstColSelection();
            }
        });

        view.getRevText().setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                handleFirstColSelection();
            }
        });

        view.getSaleValText().setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                handleFirstColSelection();
            }
        });

        view.getPopularItemList().setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                handleFirstColSelection();
            }
        });

        primaryStage.setTitle("Electronic Store Application - " + model.getName());
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene (view, 800,400));

        primaryStage.show();
        view.update();
    }

    public static void main(String[] args) {
        launch(args);
    }
    public void handleAdd() {
        int index = view.getStockList().getSelectionModel().getSelectedIndex();
        if (view.getStockProductList().get(index).getStockQuantity() == 1) {
            model.addToCart(view.getStockProductList().get(index));
            view.update();
            view.getAddCartBut().setDisable(true);
        }
        else {
            model.addToCart(view.getStockProductList().get(index));
            view.update();
            view.getAddCartBut().setDisable(false);
        }
    }

    public void handleRemove() {
        int index = view.getCartList().getSelectionModel().getSelectedIndex();
        model.removeFromCart(model.getCart().get(index));
        view.update();
        if (view.getCartStringList().size() > 0) {
            view.getRemoveCartBut().setDisable(false);
        }
    }

    public void handleCompleteSale() {
        model.completeSale(model.getCart());
        view.update();
    }

    public void handleReset() {
        model.reset();
        this.model = model.createStore();
        view.setModel(model);
        view.update();
    }

    public void handleStockListSelection() {
        view.getAddCartBut().setDisable(false);
        view.getRemoveCartBut().setDisable(true);
    }

    public void handleCartListSelection() {
        if (view.getCartStringList().size() > 0) {
            view.getRemoveCartBut().setDisable(false);
        }

        else {
            view.getRemoveCartBut().setDisable(true);
        }
        view.getAddCartBut().setDisable(true);
    }

    public void handleFirstColSelection() {
        view.getAddCartBut().setDisable(true);
        view.getRemoveCartBut().setDisable(true);
    }
}