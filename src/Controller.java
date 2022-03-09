import java.util.ArrayList;
import java.util.Arrays;

public class Controller {
    private App view;
    private ConnectionManager model;

    public Controller() {
        this.view = new App(this);
        this.model = new ConnectionManager(this);
        view.displayTable();
        add();

        view.suppliers(model.selectSuppliers());

        view.products(model.displayProducts());
    }

    public void add() {
        System.out.println("TEST!");
        ArrayList<ArrayList<String>> data = model.selectProducts();

        for (int i = 0; i < data.size(); i++) {
            view.addRow(data.get(i));
        }

    }

    public void order() {
        view.suppliers(model.selectSuppliers());
    }

    public void newProduct() {
        System.out.println("New Product");
    }

    public void removeProduct() {
        System.out.println("Remove Product");
    }

    public void addProduct() {
        System.out.println("Add Product");
    }

    public void addDiscount() {
        System.out.println("Add Discount");
    }

    public void addSupplier() {
        System.out.println("Add Supplier");
        view.supplierGetValue();
    }

    public void removeSupplier() {
        System.out.println("Remove Supplier");
    }

    public void newDiscount() {
        System.out.println("New Discount");
    }

    public void removeDiscount() {
        System.out.println("Remove Discount");
    }

    public void confirmOrder() {
        System.out.println("Confirm Order (Admin)");
    }

    public void cartConfirm() {
        System.out.println("Confirm Order (Customer)");
    }

    public void emptyCart() {
        System.out.println("Empty Cart");
    }

    public void search(boolean discountOnly) {
        if (discountOnly) {
            System.out.println("Search Discount");
        } else {
            System.out.println("Search");
        }
    }
}
