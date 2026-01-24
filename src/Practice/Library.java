package Practice;

import java.util.ArrayList;
import java.util.List;

public class Library {
    public static void main(String[] args){
        List<Item> list=new ArrayList<>();

        list.add(new Book(1, "Harry Potter", 1999, "Dorian Grey", 124));
        list.add(new Book(2, "Dorian grey", 1999, "Harry potter", 201));
        list.add(new Book(3, "Abyl Izm", 2011, "Izma Kratos", 314));
        list.add(new Magazine(4, "Gulnar", 2015, 123456, "July"));
        list.add(new Magazine(5, "Zhaina", 1995, 123488, "June"));

        for(int i=0; i<list.size(); i++){
            System.out.println(list.get(i).getInfo());
        }


    }
}
