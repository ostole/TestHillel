package test;

import java.util.ArrayList;
import java.util.Collection;

public class Test2 {
    static public Collection masCol(Object[] obj,Collection col){
        for (Object i:obj){
            col.add(i);
        }
        return  col;
    }
    public static void main(String[] args) {
        Object[] obj = new  Object[3];
        Collection <Integer> col = new ArrayList<>();


        obj[0] = new Integer(2) ;
        obj[1] = new Integer(65);
        obj[2] = new Integer(5);

        col = masCol(obj,col);

        for(Object object:col){
            System.out.println((Integer) object);
        }


    }
}
