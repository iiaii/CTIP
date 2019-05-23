package Logic;

import java.util.LinkedList;

public class Main {

    public static void main(String[] args){

        LinkedList<String> ll = new LinkedList<String>();
        String a,b,c,d;
        a = "aaa";
        b = "bbb";
        c = "ccc";
        d = "ddd";
        ll.add(a);
        ll.add(b);
        ll.add(c);
        ll.add(d);

        ll.remove(c);

        while(!ll.isEmpty()) {
            System.out.println(ll.indexOf(d));

        }
       // System.out.println("asdfasdf");
    }


}
