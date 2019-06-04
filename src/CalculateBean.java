import javafx.application.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.event.ActionEvent;

interface A {
	public void calculate(String InvestmentAmount, String years,
			String AnnualInRate);
}

class Expression {

	public static void callambda(A e, String text, String text2, String text3) {
		// TODO Auto-generated method stub
		e.calculate(text, text2, text3);
	}
}

public class CalculateBean extends Application {

	public void start(Stage stage) {
		// TODO Auto-generated method stub
		FlowPane pane = new FlowPane();
		pane.setPadding(new Insets(11, 12, 13, 14));
		pane.setHgap(5);
		pane.setVgap(5);
		Text h1 = new Text("Investment Amount 	");
		Text h2 = new Text("years	   	  	");
		Text h3 = new Text("Annual Interest Rate	");
		Text h4 = new Text("Feture value	");

		// t1.setPrefColumnCount(5);
		TextField t1 = new TextField();
		t1.setPrefColumnCount(5);
		TextField t2 = new TextField();
		t2.setPrefColumnCount(5);
		TextField t3 = new TextField();
		t3.setPrefColumnCount(5);
		TextField t4 = new TextField();
		t4.setPrefColumnCount(5);

		h1.setFont(Font
				.font("Courier", FontWeight.BOLD, FontPosture.ITALIC, 15));
		h2.setFont(Font
				.font("Courier", FontWeight.BOLD, FontPosture.ITALIC, 15));
		h3.setFont(Font
				.font("Courier", FontWeight.BOLD, FontPosture.ITALIC, 15));
		h4.setFont(Font.font("Courier", FontWeight.BLACK, FontPosture.ITALIC,
				15));

		Button bOk = new Button("Calculate");

		String s = "123";
		System.out.println(s.getBytes());
		;
		
		

		bOk.setOnAction(e -> {
			Expression.callambda( 
					(e1,  e2,  e3) -> {
						double a = Double.valueOf(e1)* Math.pow(Double.valueOf(e2) + 1,Double.valueOf(e3) * 12);
						t4.setText(String.valueOf(a));
						},
					t1.getText(), t2.getText(), t3.getText()
					);
		});

		pane.getChildren().addAll(h1, t1, h2, t2, h3, t3, h4, t4, bOk);

		Scene scene = new Scene(pane, 300, 150);

		stage.setTitle("CalcualteBean");
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String args[]) {
		launch(args);
	}

}