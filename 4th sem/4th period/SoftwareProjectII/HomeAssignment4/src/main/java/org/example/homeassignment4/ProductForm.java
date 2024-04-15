package org.example.homeassignment4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import com.mongodb.client.*;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;

public class ProductForm extends JFrame {

    private JTextField idField, ageField, nameField, cityField;
    private JButton addButton, readButton, updateButton, deleteButton;

    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> collection;

    public ProductForm() {
        // Connect to MongoDB server
        mongoClient = MongoClients.create("mongodb://localhost:27017");
        // Access database
        database = mongoClient.getDatabase("homeassignment3");
        // Access collection
        collection = database.getCollection("data");

        setTitle("MongoDB CRUD Operations");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2));

        JLabel idLabel = new JLabel("ID:");
        idField = new JTextField();
        panel.add(idLabel);
        panel.add(idField);

        JLabel ageLabel = new JLabel("Age:");
        ageField = new JTextField();
        panel.add(ageLabel);
        panel.add(ageField);

        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField();
        panel.add(nameLabel);
        panel.add(nameField);

        JLabel cityLabel = new JLabel("City:");
        cityField = new JTextField();
        panel.add(cityLabel);
        panel.add(cityField);

        addButton = new JButton("Add ");
        addButton.addActionListener(e -> addPerson());
        panel.add(addButton);

        readButton = new JButton("Read ");
        readButton.addActionListener(e -> readPeople());
        panel.add(readButton);

        updateButton = new JButton("Update ");
        updateButton.addActionListener(e -> updatePerson());
        panel.add(updateButton);

        deleteButton = new JButton("Delete ");
        deleteButton.addActionListener(e -> deletePerson());
        panel.add(deleteButton);

        add(panel);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addPerson() {
        int id = Integer.parseInt(idField.getText());
        int age = Integer.parseInt(ageField.getText());
        String name = nameField.getText();
        String city = cityField.getText();
        Document document = new Document()
                .append("id", id)
                .append("age", age)
                .append("name", name)
                .append("city", city);
        collection.insertOne(document);
        JOptionPane.showMessageDialog(this, "Person added successfully!");
        clearFields();
    }

    private void readPeople() {
        StringBuilder people = new StringBuilder("People:\n");
        FindIterable<Document> iterable = collection.find();
        for (Document document : iterable) {
            people.append("ID: ").append(document.getInteger("id"))
                    .append(", Age: ").append(document.getInteger("age"))
                    .append(", Name: ").append(document.getString("name"))
                    .append(", City: ").append(document.getString("city"))
                    .append("\n");
        }
        JOptionPane.showMessageDialog(this, people.toString());
    }

    private void updatePerson() {
        int id = Integer.parseInt(idField.getText());
        int age = Integer.parseInt(ageField.getText());
        String name = nameField.getText();
        String city = cityField.getText();
        Document filter = new Document("id", id);
        Document update = new Document("$set", new Document("age", age).append("name", name).append("city", city));
        UpdateOptions options = new UpdateOptions().upsert(true);
        UpdateResult result = collection.updateMany(filter, update, options);
        JOptionPane.showMessageDialog(this, "Updated " + result.getModifiedCount() + " people.");
        clearFields();
    }

    private void deletePerson() {
        int id = Integer.parseInt(idField.getText());
        Document filter = new Document("id", id);
        DeleteResult result = collection.deleteMany(filter);
        JOptionPane.showMessageDialog(this, "Deleted " + result.getDeletedCount() + " people.");
        clearFields();
    }

    private void clearFields() {
        idField.setText("");
        ageField.setText("");
        nameField.setText("");
        cityField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ProductForm::new);
    }
}

/*public class ProductForm extends JFrame {

    private JTextField nameField, priceField, descriptionField;
    private JButton addButton, readButton, updateButton, deleteButton;

    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> collection;

    public ProductForm() {
        // Connect to MongoDB server
        mongoClient = MongoClients.create("mongodb://localhost:27017");
        // Access database
        database = mongoClient.getDatabase("homeassignment3");
        // Access collection
        collection = database.getCollection("data");

        setTitle("MongoDB CRUD Operations");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2));

        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField();
        panel.add(nameLabel);
        panel.add(nameField);

        JLabel priceLabel = new JLabel("Price:");
        priceField = new JTextField();
        panel.add(priceLabel);
        panel.add(priceField);

        JLabel descriptionLabel = new JLabel("Description:");
        descriptionField = new JTextField();
        panel.add(descriptionLabel);
        panel.add(descriptionField);

        addButton = new JButton("Add ");
        addButton.addActionListener(e -> addProduct());
        panel.add(addButton);

        readButton = new JButton("Read ");
        readButton.addActionListener(e -> readProducts());
        panel.add(readButton);

        updateButton = new JButton("Update ");
        updateButton.addActionListener(e -> updateProduct());
        panel.add(updateButton);

        deleteButton = new JButton("Delete ");
        deleteButton.addActionListener(e -> deleteProduct());
        panel.add(deleteButton);

        add(panel);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addProduct() {
        String name = nameField.getText();
        double price = Double.parseDouble(priceField.getText());
        String description = descriptionField.getText();
        Document document = new Document()
                .append("name", name)
                .append("price", price)
                .append("description", description);
        collection.insertOne(document);
        JOptionPane.showMessageDialog(this, "Product added successfully!");
        clearFields();
    }

    private void readProducts() {
        StringBuilder products = new StringBuilder("Products:\n");
        FindIterable<Document> iterable = collection.find();
        for (Document document : iterable) {
            products.append("Name: ").append(document.getString("name"))
                    .append(", Price: ").append(document.getDouble("price"))
                    .append(", Description: ").append(document.getString("description"))
                    .append("\n");
        }
        JOptionPane.showMessageDialog(this, products.toString());
    }

    private void updateProduct() {
        String name = nameField.getText();
        double price = Double.parseDouble(priceField.getText());
        String description = descriptionField.getText();
        Document filter = new Document("name", name);
        Document update = new Document("$set", new Document("price", price).append("description", description));
        UpdateOptions options = new UpdateOptions().upsert(true);
        UpdateResult result = collection.updateMany(filter, update, options);
        JOptionPane.showMessageDialog(this, "Updated " + result.getModifiedCount() + " products.");
        clearFields();
    }

    private void deleteProduct() {
        String name = nameField.getText();
        Document filter = new Document("name", name);
        DeleteResult result = collection.deleteMany(filter);
        JOptionPane.showMessageDialog(this, "Deleted " + result.getDeletedCount() + " products.");
        clearFields();
    }

    private void clearFields() {
        nameField.setText("");
        priceField.setText("");
        descriptionField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ProductForm::new);
    }
}*/



/*public class ProductForm extends JFrame {
    private JTextField nameField, priceField, descriptionField;

    public ProductForm() {
        setTitle("Add Product");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));
        panel.add(new JLabel("Name:"));
        nameField = new JTextField();
        panel.add(nameField);
        panel.add(new JLabel("Price:"));
        priceField = new JTextField();
        panel.add(priceField);
        panel.add(new JLabel("Description:"));
        descriptionField = new JTextField();
        panel.add(descriptionField);
        JButton addButton = new JButton("Add Product");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addProduct(nameField.getText(), priceField.getText(), descriptionField.getText());
            }
        });
        panel.add(addButton);

        add(panel);
        setVisible(true);
    }

    private void addProduct(String name, String price, String description) {
        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = mongoClient.getDatabase("test");
            MongoCollection<Document> collection = database.getCollection("products");
            double parsedPrice = Double.parseDouble(price);
            Document document = new Document()
                    .append("name", name)
                    .append("price", parsedPrice)
                    .append("description", description);
            collection.insertOne(document);
            JOptionPane.showMessageDialog(this, "Product added successfully!");
            clearFields();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFields() {
        nameField.setText("");
        priceField.setText("");
        descriptionField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ProductForm();
            }
        });
    }
}*/




