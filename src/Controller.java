import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Controller {
    private App view;
    private ConnectionManager model;

    public Controller() {
        this.view = new App(this);
        this.model = new ConnectionManager(this);

        view.displayTable();
        add();
        //Tror dessa är i fel ordning eller ja det blir iaf som en stutter vid launch av program, där olika tables visas.
        view.products(model.selectProducts());
        //om jag förstått rätt så ska all info visas om products när man startar



    }
    public void showProductsTable(){
        //av någon anledning så hamnar den man gör något med sist i listan, tex lägger till i cart, någon konstig sorteringsgrej
        view.products(model.selectProducts());
    }
    public void showCartTable(){
        view.cart(model.selectCart());
    }

    public void add() {
        System.out.println("TEST!");
        ArrayList<ArrayList<String>> data = model.selectProducts();

        for (int i = 0; i < data.size(); i++) {
            view.addRow(data.get(i));
        }

    }
    public void login(String username, String password){
        model.loginCheck(username,password);
    }
    public void loginOK(boolean admin){
        view.initLogin(admin);
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

    public void addProduct(String id, int count) {
        System.out.println(id + " " + count);
        model.addProduct(id, count);
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

    public void search(boolean discountOnly, String textField) {
        // Med discount produkter och namnsöl metod
        if (discountOnly && !Objects.equals(textField, "")) {
            System.out.println("Search Discount1");
            view.products(model.displayDiscountProductsWithNameSearch(textField));
        // Med discount produkter utan namnsök
        } else if (discountOnly && textField.equals("")){
            // displaya vanligt
            view.products(model.displayDiscountProducts1());
            System.out.println("Search Discount2");

            // Ingen discount och namnsök metod.
        } else if (!discountOnly && !Objects.equals(textField, "")){

            view.products(model.displayProductsWithNameSearch(textField));
            System.out.println("Search1");
        }
            // Ingen discount utan namnsök.
        else if (!discountOnly && textField.equals("")){
            view.products(model.displayProducts());
            System.out.println("Search2");
        }
    }
}
