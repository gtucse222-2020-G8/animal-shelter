package cse222.group8.desktop;

public class Helpers {
    public static <E> boolean arrayContains(E[] arr, E toFind){
        for(E e: arr){
            if(e.equals(toFind)){
                return true;
            }
        }
        return false;
    }
}
