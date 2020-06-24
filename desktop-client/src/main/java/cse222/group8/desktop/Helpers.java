package cse222.group8.desktop;

/**
 * The type Helpers.
 */
public class Helpers {
    /**
     * Array contains boolean.
     *
     * @param <E>    the type parameter
     * @param arr    the arr
     * @param toFind the to find
     * @return the boolean
     */
    public static <E> boolean arrayContains(E[] arr, E toFind){
        for(E e: arr){
            if(e.equals(toFind)){
                return true;
            }
        }
        return false;
    }

}
