package Library;

public class Book extends Item{
    private String author;
    private int pages;

    public Book(int id,String title, int year, String author, int pages){
        super(id, title, year);
        setAuthor(author);
        setPages(pages);
    }

    public String getAuthor(){
        return author;
    }
    public int getPages(){
        return pages;
    }

    public void setAuthor(String author){
        if(author == null || author.trim().isEmpty()){
            throw new IllegalArgumentException("Author can't be empty");
        }
        this.author=author;
    }
    public void setPages(int pages){
        if(pages<=0){
            throw new IllegalArgumentException("Pages must be greater than 0");
        }
        this.pages=pages;
    }

    @Override
    public String getInfo() {
        return "ID: "+getId()+". Title: "+getTitle()+". Year: "+getYear()+". Author: "+author+". Pages: "+pages;
    }
}
