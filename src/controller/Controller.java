package controller;

import model.*;
import view.App;

import java.util.ArrayList;
import java.util.Objects;

public class Controller {
    private final App view;
    private final ConnectionManager model;
    private Login login;

    public Controller() {
        this.view = new App(this);
        this.model = new ConnectionManager(this);

        view.displayTable();
        add();
        //Tror dessa är i fel ordning eller ja det blir iaf som en stutter vid launch av program, där olika tables visas.
        view.products(Product.selectProducts(model.getConnection()));
        //om jag förstått rätt så ska all info visas om products när man startar



    }
    public void showProductsTable(){
        //av någon anledning så hamnar den man gör något med sist i listan, tex lägger till i cart, någon konstig sorteringsgrej
        view.products(Product.selectProducts(model.getConnection()));
    }
    public void showCartTable(){
        view.cart(Cart.selectCart(model.getConnection()));
    }

    public void add() {
        ArrayList<ArrayList<String>> data = Product.selectProducts(model.getConnection());

        for (int i = 0; i < data.size(); i++) {
            view.addRow(data.get(i));
        }

    }
    public void login(String username, String password){
        boolean loginOK = Login.loginCheck(model.getConnection(), username, password);
        if (loginOK == true) {
            loginOK(Login.isAdmin());
        }
        else System.out.println("Can't log in");

    }
    public void loginOK(boolean admin){
        view.initLogin(admin);
    }

    public void order() {
        view.suppliers(Supplier.selectSuppliers(model.getConnection()));
    }

    public void newProduct() {
        System.out.println("New Product");
    }

    public void removeProduct() {
        System.out.println("Remove Product");
    }

    public void addProduct(String id, int count) {
        boolean stockOK = Cart.addProduct(model.getConnection(), id, count);
        if (stockOK) {
            boolean cartAdded = Cart.addToCart(model.getConnection(), id, count);
            if (cartAdded) {
                showCartTable();
            }
        }
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
       // Cart.confirmOrder(model.getConnection());
    }

    public void cartConfirm() {
        System.out.println("Confirm Order (Customer)");
    }

    public void emptyCart() {
        Cart.emptyCart(model.getConnection());
        showCartTable();
    }

    public void search(boolean discountOnly, String textField) {
        // Med discount produkter och namnsöl metod
        if (discountOnly && !Objects.equals(textField, "")) {
            System.out.println("Search Discount1");
            view.products(Product.displayDiscountProductsWithNameSearch(model.getConnection(),textField));
        // Med discount produkter utan namnsök
        } else if (discountOnly && textField.equals("")){
            // displaya vanligt
            view.products(Product.displayDiscountProducts1(model.getConnection()));
            System.out.println("Search Discount2");

            // Ingen discount och namnsök metod.
        } else if (!discountOnly && !Objects.equals(textField, "")){

            view.products(Product.displayProductsWithNameSearch(model.getConnection(),textField));
            System.out.println("Search1");
        }
            // Ingen discount utan namnsök.
        else if (!discountOnly && textField.equals("")){
            view.products(Product.displayProducts(model.getConnection()));
            System.out.println("Search2");
        }
    }
}
