package cucumber;

import java.util.ArrayList;

public class ReusableClass implements Runnable {

    public ReusableClass(ArrayList<String> steps){}

    @Override
    public void run(){doIt();}

    public void doIt(){

    }
}
