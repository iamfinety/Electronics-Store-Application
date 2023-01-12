package Assignment4;
import java.util.Arrays;
import javafx.scene.layout.Pane;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import java.util.ArrayList;

public class ElectronicStoreView extends Pane {
    private ElectronicStore model;
    private Button addCartBut;
    private Button removeCartBut;
    private Button completeSaleBut;
    private Button resetBut;
    private Label summaryTitle;
    private Label saleNumLabel;
    private Label revLabel;
    private Label saleValLabel;
    private Label popularItemLabel;
    private Label stockLabel;
    private Label cartLabel;
    private TextField saleNumText;
    private TextField revText;
    private TextField saleValText;
    private ListView<String> popularItemList;
    private ArrayList<String> popularItemStringList;
    private int[] sortList;
    private ListView<String> stockList;
    private ArrayList<String> stockStringList;
    private ArrayList<Product> stockProductList;
    private ListView<String> cartList;
    private ArrayList<String> cartStringList;

    public ElectronicStoreView(ElectronicStore model) {
        this.model = model;

        // Create the labels
        summaryTitle = new Label("Store Summary:");
        summaryTitle.relocate(15, 15);
        stockLabel = new Label("Store Stock:");
        stockLabel.relocate(222, 15);
        cartLabel = new Label("Current Cart:");
        cartLabel.relocate(530, 15);
        saleNumLabel = new Label("# Sales:");
        saleNumLabel.relocate(23, 37);
        saleNumLabel.setPrefSize(60.0D, 30.0D);
        revLabel = new Label("Revenue:");
        revLabel.relocate(15, 67);
        revLabel.setPrefSize(60.0D, 30.0D);
        saleValLabel = new Label("$ / Sale:");
        saleValLabel.relocate(27, 97);
        saleValLabel.setPrefSize(60.0D, 30.0D);
        popularItemLabel = new Label("Most Popular Items:");
        popularItemLabel.relocate(15, 130);
        popularItemLabel.setPrefSize(200.0D, 40.0D);

        // Create the TextFields
        saleNumText = new TextField();
        saleNumText.relocate(65, 33);
        saleNumText.setPrefSize(100,25);
        saleNumText.setEditable(false);
        revText = new TextField();
        revText.relocate(65, 63);
        revText.setPrefSize(100,25);
        revText.setEditable(false);
        saleValText = new TextField();
        saleValText.relocate(65, 93);
        saleValText.setPrefSize(100,25);
        saleValText.setEditable(false);

        // Create the lists
        popularItemList = new ListView<String>();
        popularItemList.relocate(15, 145);
        popularItemList.setPrefSize(150,195);

        stockList = new ListView<String>();
        stockList.relocate(220, 30);
        stockList.setPrefSize(240,310);

        cartList = new ListView<String>();
        cartList.relocate(530, 30);
        cartList.setPrefSize(240,310);

        // Create the buttons
        resetBut = new Button("Reset Store");
        resetBut.relocate(30, 350);
        resetBut.setPrefSize(120,40);

        addCartBut = new Button("Add to Cart");
        addCartBut.relocate(280, 350);
        addCartBut.setPrefSize(120,40);

        removeCartBut = new Button("Remove from Cart");
        removeCartBut.relocate(530, 350);
        removeCartBut.setPrefSize(120,40);

        completeSaleBut = new Button("Complete Sale");
        completeSaleBut.relocate(650, 350);
        completeSaleBut.setPrefSize(120,40);

        getChildren().addAll(summaryTitle, stockLabel, cartLabel, saleNumLabel, revLabel, saleValLabel, popularItemLabel, popularItemList, stockList,
                cartList, resetBut, addCartBut, removeCartBut, completeSaleBut, saleNumText, revText, saleValText);
    }

    public Button getAddCartBut() {
        return addCartBut;
    }

    public Button getResetBut() {
        return resetBut;
    }

    public Button getRemoveCartBut() {
        return removeCartBut;
    }

    public Button getCompleteSaleBut() {
        return completeSaleBut;
    }

    public ListView getStockList() {
        return stockList;
    }

    public ArrayList<Product> getStockProductList() {
        return stockProductList;
    }

    public ListView getCartList() {
        return cartList;
    }

    public ArrayList<String> getCartStringList() {
        return cartStringList;
    }

    public TextField getSaleNumText() {
        return saleNumText;
    }

    public TextField getRevText() {
        return revText;
    }

    public TextField getSaleValText() {
        return saleValText;
    }

    public ListView<String> getPopularItemList() {
        return popularItemList;
    }

    public void setModel(ElectronicStore model) {
        this.model = model;
    }

    public void update() {

        saleNumText.setText(Integer.toString(model.getSaleNum()));
        revText.setText(String.format("%.2f", model.getRevenue()));
        if (Double.toString(model.getRevenue() / model.getSaleNum()).equals("NaN")) {
            saleValText.setText("N/A");
        }
        else {
            saleValText.setText(String.format("%.2f", (model.getRevenue() / model.getSaleNum())));
        }

        stockStringList = new ArrayList<String>();
        stockProductList = new ArrayList<Product>();
        for (int i = 0; i < model.getStock().length; i++) {
            if ((model.getStock()[i] != null) && (model.getStock()[i].getStockQuantity() > 0)) {
                stockStringList.add(model.getStock()[i].toString());
                stockProductList.add(model.getStock()[i]);
            }
        }
        stockList.setItems(FXCollections.observableArrayList(stockStringList));
        popularItemStringList = new ArrayList<String>();
        sortList = new int[model.getStock().length];

        int counter = 0;

        for (int i = 0; i < model.getStock().length; i++) {
            if (model.getStock()[i] != null) {
                sortList[counter] = model.getStock()[i].getSoldQuantity();
                counter += 1;
            }
        }
        Arrays.sort(sortList);

        int first = sortList[sortList.length-1];
        int second = sortList[sortList.length-2];
        int third = sortList[sortList.length-3];

        for (int i = 0; i < model.getStock().length; i++) {
            if ((model.getStock()[i] != null) && (model.getStock()[i].getSoldQuantity() == first)) {
                if (!popularItemStringList.contains(model.getStock()[i].toString())) {
                    popularItemStringList.add(model.getStock()[i].toString());
                    break;
                }
            }
        }

        for (int i = 0; i < model.getStock().length; i++) {
            if ((model.getStock()[i] != null) && (model.getStock()[i].getSoldQuantity() == second)) {
                if (!popularItemStringList.contains(model.getStock()[i].toString())) {
                    popularItemStringList.add(model.getStock()[i].toString());
                    break;
                }
            }
        }

        for (int i = 0; i < model.getStock().length; i++) {
            if ((model.getStock()[i] != null) && (model.getStock()[i].getSoldQuantity() == third)) {
                if (!popularItemStringList.contains(model.getStock()[i].toString())) {
                    popularItemStringList.add(model.getStock()[i].toString());
                    break;
                }
            }
        }

        popularItemList.setItems(FXCollections.observableArrayList(popularItemStringList));

        cartStringList = new ArrayList<String>();

        for (int i = 0; i < model.getCart().size(); i++) {
            if (model.getCart().get(i) != null) {
                cartStringList.add(model.getCart().get(i).toString());
            }
        }
        cartList.setItems(FXCollections.observableArrayList(cartStringList));
        cartLabel.setText(String.format("Current Cart ($%.2f):", model.getCartValue()));
        if (model.getCart().size() > 0) {
            completeSaleBut.setDisable(false);
        }

        else {
            completeSaleBut.setDisable(true);;
        }

        removeCartBut.setDisable(true);
        addCartBut.setDisable(true);
    }

}