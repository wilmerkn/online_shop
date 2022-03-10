import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;



public class App  implements ActionListener {

    // Swing Components
    private JPanel mainPanel;
    private JPanel westPanel;
    private JPanel eastPanel;
    private JTabbedPane tabbedPane;
    private JTextField textField1;
    private JButton searchButton;
    private JButton addButton;
    private JSpinner spinner;
    private JTable table1;
    private JButton cartConfirm;
    private JButton emptyCartButton;
    private JTextArea textArea1;
    private JCheckBox discountOnlyCheckBox;
    private JPanel orderPanel;
    private JPanel cartPanel;
    private JPanel historyPanel;
    private JPanel productsPanel;
    private JPanel suppliersPanel;
    private JPanel discountsPanel;
    private JButton loginButton;
    private JPasswordField passwordField;
    private JTextField usernameField;
    private JPanel loginPanel;
    private JButton registerButton;
    private JButton confirmOrderButton;
    private JTable ordersTable;
    private JButton addSuppliersButton;
    private JButton removeSuppliersButton;
    private JScrollPane scroll;
    private JTable suppliersTable;
    private JButton newProductButton;
    private JButton removeProductButton;
    private JTable productsTable;
    private JSpinner spinner1;
    private JButton addProductButton;
    private JTable discountTable;
    private JButton newDiscountButton;
    private JButton removeDiscountButton;
    private JButton addDiscountButton;
    private JFrame frame;
    private RegisterForm register;


    // Jtable produkter
    private JTable productTable;
    private JTable orderHistory;
    private JTable productsInOrderTable;
    private DefaultTableModel productModel;
    private Controller controller;
    private String[] col = {"Produkt","Pris"};

    private DefaultTableModel supplierModel;


    public App(Controller controller) {
        frame = new JFrame("Title");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.pack();
        eastPanel.setVisible(false);
        addButton.setVisible(false);
        spinner.setVisible(false);
        register = new RegisterForm();
        initListeners();
        this.controller = controller;



    }

    private void initListeners() {
        this.addButton.addActionListener(this);
        this.loginButton.addActionListener(this);
        this.registerButton.addActionListener(this);
        this.searchButton.addActionListener(this);

        //Product Tab.
        this.addProductButton.addActionListener(this);
        this.newProductButton.addActionListener(this);
        this.addDiscountButton.addActionListener(this);
        this.removeProductButton.addActionListener(this);

        //Suppliers Tab.
        this.addSuppliersButton.addActionListener(this);
        this.removeSuppliersButton.addActionListener(this);

        //Discounts Tab.
        this.newDiscountButton.addActionListener(this);
        this.removeDiscountButton.addActionListener(this);

        //Orders Tab.
        this.confirmOrderButton.addActionListener(this);

        //Cart Tab.
        this.cartConfirm.addActionListener(this);
        this.emptyCartButton.addActionListener(this);

    }

    public void initLogin(boolean isAdmin) {

        if (!isAdmin) {
            tabbedPane.remove(orderPanel);
            tabbedPane.remove(productsPanel);
            tabbedPane.remove(discountsPanel);
            tabbedPane.remove(suppliersPanel);
            spinner.setVisible(true);
            addButton.setVisible(true);
            frame.setTitle("[Logged in]");
        } else {
            tabbedPane.remove(historyPanel);
            tabbedPane.remove(cartPanel);
            frame.setTitle("[Logged in - ADMIN]");
        }

        eastPanel.setVisible(true);
        loginPanel.setVisible(false);
        register.setVisible(false);
    }




    public void addRow(ArrayList<String> a) {
        productModel.addRow(a.toArray());
    }



    public void displayTable() {

        this.productModel = new DefaultTableModel(col,0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        productTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        productTable.getTableHeader().setReorderingAllowed(false);
        productTable.setModel(productModel);


    }



    public void suppliers(ArrayList<ArrayList<String>> a) {
        String[] col = {"Company Name","Address","Phone"};
        this.supplierModel = new DefaultTableModel(col,0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        suppliersTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        suppliersTable.getTableHeader().setReorderingAllowed(false);
        suppliersTable.setModel(supplierModel);

        for (int i = 0; i < a.size(); i++) {
            supplierModel.addRow(a.get(i).toArray());
        };
    }

    public void products(ArrayList<ArrayList<String>> a) {
        String[] col = {"Product_id","Product_name","Description","Quantity", "Price","Supplier", "Discount rate"};
        this.productModel = new DefaultTableModel(col,0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        productTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        productTable.getTableHeader().setReorderingAllowed(false);
        productTable.setModel(productModel);

        for (int i = 0; i < a.size(); i++) {
            productModel.addRow(a.get(i).toArray());
        };
    }

    public void supplierGetValue() {
        System.out.println(suppliersTable.getSelectedRow());
    }


    private void getValue(JTable t) {
        int a =productTable.getSelectedRow();
        System.out.println(a);
        System.out.println(productModel.getValueAt(a,0));
    }

    public String searchTxtField(){
        String name = textField1.getText();
        return name;
    }



    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == loginButton) {
            //ska gå till connectionmanager och kolla om login i textboxes finns, om det inte finns, felmeddelande,
            // annars skicka boolean isadmin till initlogin
            String passString = new String(passwordField.getPassword());
            controller.login(usernameField.getText(),passString);
        }

        if (e.getSource() == registerButton) {
            register.setVisible(true);
        }

        if (e.getSource() == addButton) {

            controller.addProduct(productTable.getValueAt(productTable.getSelectedRow(),0).toString(), (Integer) spinner.getValue());
        }

        if (e.getSource() == cartConfirm) {
            controller.order();
        }

        if (e.getSource() == newProductButton) {
            controller.newProduct();
        }

        if (e.getSource() == removeProductButton) {
            controller.removeProduct();
        }

        if (e.getSource() == addProductButton) {
            //controller.addProduct();
        }

        if (e.getSource() == addDiscountButton) {
            controller.addDiscount();
        }

        if (e.getSource() == addSuppliersButton) {
            controller.addSupplier();
            System.out.println(supplierModel.getValueAt(suppliersTable.getSelectedRow(),1));
        }

        if (e.getSource() == removeSuppliersButton) {
            controller.removeSupplier();
        }

        if (e.getSource() == newDiscountButton) {
            controller.newDiscount();
        }

        if (e.getSource() == removeDiscountButton) {
            controller.removeDiscount();
        }

        if (e.getSource() == confirmOrderButton) {
            controller.confirmOrder();
        }

        if (e.getSource() == emptyCartButton) {
            controller.emptyCart();
        }

        if (e.getSource() == cartConfirm) {
            controller.cartConfirm();
        }

        if (e.getSource() == searchButton) {
            controller.search(discountOnlyCheckBox.isSelected(), searchTxtField());
        }
    }
}
