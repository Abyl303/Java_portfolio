package Book;

import java.time.Year;

public class Book {
    private long id;
    private String title;
    private String author;
    private BookGenre genre;
    private int year;
    private BookStatus status;

    public Book(long id, String title, String author,BookGenre genre,int year,BookStatus status ){
        this.id=id;
        setTitle(title);
        setAuthor(author);
        setGenre(genre);
        setYear(year);
        setStatus(status);
    }

    public long getId(){
        return id;
    }
    public String getTitle(){
        return title;
    }
    public String getAuthor(){
        return author;
    }
    public BookGenre getGenre(){
        return genre;
    }
    public int getYear(){
        return year;
    }
    public BookStatus getStatus(){
        return status;
    }

    public void setTitle(String title){
        if(title==null || title.trim().isEmpty()){
            throw new IllegalArgumentException("Title can't be empty!");
        }
        this.title=title;
    }

    public void setAuthor(String author){
        if(author==null || author.trim().isEmpty()){
            throw new IllegalArgumentException("Author can't be empty!");
        }
        this.author=author;
    }

    public void setGenre(BookGenre genre){
        if(genre==null){
            throw new IllegalArgumentException("Genre can't be empty!");
        }
        this.genre=genre;
    }

    public void setYear(int year){
        if (year < 1450 || year > Year.now().getValue()) {
            throw new IllegalArgumentException("Incorrect year!");
        }
        this.year=year;
    }

    public void setStatus(BookStatus status){
        if(status==null){
            throw new IllegalArgumentException("Status can't be empty!");
        }
        this.status=status;
    }

    @Override
    public String toString(){
        return "ID: "+id+". Title: "+title+". Author: "+author+". Genre: "+genre+". Year: "+year+". Status: "+status;
    }

}
