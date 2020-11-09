package dk.dd.cameldemo;

public class MyTransformation {
    public String toUpper(String s){
        return s.toUpperCase();
    }

    public String getLength(String s){
        return Integer.toString(s.length());
    }
}
