import java.util.Scanner;
import java.util.ArrayList;

public class libraryExample {
    static class Book {
        int bookId;
        String title, author;
        boolean isAvaliable;

        public Book() {
            Scanner sc = new Scanner(System.in);
            System.out.println("input format: '<bookId> <author> <title>'");
            bookId = sc.nextInt();
            author = sc.next();
            title = sc.next();
            sc.close();
        }

        public void displayBookInfo() {
            System.out.println("Title: " + title);
            System.out.println("Author: " + author);
            System.out.println("isAvaliable: " + isAvaliable);
            System.out.println("\n\n");
        }

        public boolean borrowBook() {
            if (isAvaliable) {
                isAvaliable = false;
                return true;

            }
            return false;
        }

        public void returnBook() {
            isAvaliable = true;
        }
    }

    static class Library {
        static ArrayList<Book> books = new ArrayList<>();

        public void addBook(Book book) {
            books.add(book);
        }

        public void removeBook(int bookId) {
            for (Book book : books) {
                if (book.bookId == bookId)
                    books.remove(book);
            }
        }

	public Book findBook(String title){
		for (Book book : books) {
			if (book.title == title)
				return book;
		}
	}

        public void listAvaliableBooks() {
            for (Book book : books) {
                if (book.isAvaliable)
                    book.displayBookInfo();
            }
        }
    }

    static class Student {
        int studentId;
        String name;
        ArrayList<Book> borrowedBooks = new ArrayList<>();

        public Student() {
            Scanner sc = new Scanner(System.in);
            System.out.println("input format: '<studentId> <name>'");
            studentId = sc.nextInt();
            name = sc.next();
            sc.close();
        }

        public void borrowBook(Book book) {
            if (book.isAvaliable && borrowedBooks.size() <= 3) {
                borrowedBooks.add(book);
                book.borrowBook();
            } else {
                System.out.println("The book is not Avaliable! or You have reached your borrowing limit!");
            }
        }

        public void returnBook(Book book) {
            if (borrowedBooks.contains(book)) {
                book.returnBook();
            } else {
                System.out.println("The book was not borrowed!");
            }
        }

        public void displayBorrowedBooks() {
            for (Book book : borrowedBooks) {
                book.displayBookInfo();
            }
        }
    }

    public static void main(String[] args) {
        Student s1 = new Student();
        Library saiReads = new Library();
        System.out.println("Hello, World!");
    }
}
