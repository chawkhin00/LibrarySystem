import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

class Book {
    private String title;
    private String author;
    private int quantity;

    public Book(String title, String author, int quantity) {
        this.title = title;
        this.author = author;
        this.quantity = quantity;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

class Library {
    private List<Book> books;

    public Library() {
        this.books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public Book findBook(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }
}

public class LibrarySystem {
    private static Library library = new Library();

    public static void main(String[] args) {
        JFrame frame = new JFrame("Library System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);

        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setVisible(true);
    }

    private static void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel titleLabel = new JLabel("Book Title:");
        titleLabel.setBounds(10, 20, 80, 25);
        panel.add(titleLabel);

        JTextField titleText = new JTextField(20);
        titleText.setBounds(100, 20, 200, 25);
        panel.add(titleText);

        JLabel quantityLabel = new JLabel("Quantity:");
        quantityLabel.setBounds(10, 50, 80, 25);
        panel.add(quantityLabel);

        JTextField quantityText = new JTextField(20);
        quantityText.setBounds(100, 50, 200, 25);
        panel.add(quantityText);

        JButton addButton = new JButton("Add Book");
        addButton.setBounds(10, 80, 120, 25);
        panel.add(addButton);

        JButton borrowButton = new JButton("Borrow Book");
        borrowButton.setBounds(140, 80, 120, 25);
        panel.add(borrowButton);

        JButton returnButton = new JButton("Return Book");
        returnButton.setBounds(270, 80, 120, 25);
        panel.add(returnButton);

        JTextArea resultArea = new JTextArea();
        resultArea.setBounds(10, 120, 380, 130);
        panel.add(resultArea);

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String title = titleText.getText();
                int quantity = Integer.parseInt(quantityText.getText());
                Book existingBook = library.findBook(title);
                if (existingBook != null) {
                    existingBook.setQuantity(existingBook.getQuantity() + quantity);
                    resultArea.setText("Book quantity updated.");
                } else {
                    library.addBook(new Book(title, "", quantity));
                    resultArea.setText("Book added to the library.");
                }
            }
        });

        borrowButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String title = titleText.getText();
                int quantity = Integer.parseInt(quantityText.getText());
                Book existingBook = library.findBook(title);
                if (existingBook != null) {
                    if (existingBook.getQuantity() >= quantity) {
                        existingBook.setQuantity(existingBook.getQuantity() - quantity);
                        resultArea.setText("Successfully borrowed " + quantity + " copies of " + title);
                    } else {
                        resultArea.setText("Not enough copies available for borrowing.");
                    }
                } else {
                    resultArea.setText("Book not found in the library.");
                }
            }
        });

        returnButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String title = titleText.getText();
                int quantity = Integer.parseInt(quantityText.getText());
                Book existingBook = library.findBook(title);
                if (existingBook != null) {
                    existingBook.setQuantity(existingBook.getQuantity() + quantity);
                    resultArea.setText("Successfully returned " + quantity + " copies of " + title);
                } else {
                    resultArea.setText("Book not found in the library.");
                }
            }
        });
    }
}
