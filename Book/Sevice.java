package Book;

import java.util.ArrayList;
import java.util.List;

public class Sevice {
    List<Book> books=new ArrayList<>();

    private long nextId = 1;

    public long generateId() {
        return nextId++;
    }

    public List<Book> getAllBooks(){    //read
        return new ArrayList<>(books);
    }

    public void addBook(Book book){
        if(book==null){
            throw new IllegalArgumentException("Book can't be empty");
        }
        books.add(book);
    }

    public boolean removeById(long id){
        for(int i=0; i<books.size(); i++){
            if(books.get(i).getId()==id){
                books.remove(i);
                return true;
            }
        }
        return false;
    }


    public boolean updateStatus(long id, BookStatus status) {
        if (status == null) throw new IllegalArgumentException("Status can't be null");
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getId() == id) {
                books.get(i).setStatus(status);
                return true;
            }
        }
        return false;
    }

    // optional "удобные" методы
    public boolean updateStatusDone(long id) {
        return updateStatus(id, BookStatus.AVAILABLE);
    }

    public boolean updateStatusToDo(long id) {
        return updateStatus(id, BookStatus.BORROWED);
    }

    public boolean updateGenre(long id, BookGenre genre) {
        if (genre == null) throw new IllegalArgumentException("Genre can't be null");
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getId() == id) {
                books.get(i).setGenre(genre);
                return true;
            }
        }
        return false;
    }


    // optional "удобные" методы
    public boolean updateGenreToHorror(long id) {
        return updateGenre(id, BookGenre.HORROR);
    }

    public boolean updateGenreToAction(long id) {
        return updateGenre(id, BookGenre.ACTION);
    }


}
