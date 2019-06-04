import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class ObservablePropertyDemo {
  public static void main(String[] args) {
    DoubleProperty balance = new SimpleDoubleProperty();
    balance.addListener(new InvalidationListener() {
      public void invalidated(Observable ov) {
        System.out.println("The new value is " + 
          balance.doubleValue());
      }
    });

    balance.set(4.5);
    balance.set(4.5);
    
    balance.set(4.3455);
    balance.set(4.56756757);
    balance.set(4.5);
    balance.set(4.34535);
    balance.set(4.34535);
    balance.set(4.34535);
    balance.set(4.34535);
    balance.set(4.34535);
    balance.set(4.34535);
    balance.set(4.34535);
    balance.set(4.34535); 
    balance.set(4.345334535);
    
  }
}