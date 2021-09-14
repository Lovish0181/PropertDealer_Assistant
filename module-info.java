module PropertyDealer {
	requires javafx.controls;
	requires javafx.graphics;
	requires java.sql;
	requires javafx.fxml;
	requires java.desktop;
	requires javafx.base;
	requires java.base;
	
	exports ForgotPassword;
	opens ForgotPassword to javafx.graphics, javafx.fxml;
	
	exports Developer;
	opens Developer to javafx.graphics, javafx.fxml;
	
	exports Login;
	opens Login to javafx.graphics, javafx.fxml;
	
	exports Signup;
	opens Signup to javafx.graphics, javafx.fxml;
	
	exports Dashboard;
	opens Dashboard to javafx.graphics, javafx.fxml;
	
	exports PicGallery;
	opens PicGallery to javafx.graphics, javafx.fxml;
	
	exports FindProperty;
	opens FindProperty to javafx.graphics, javafx.fxml;
	
	exports FindProperty1;
	opens FindProperty1 to javafx.graphics, javafx.fxml;
	
	exports CustomerDemand;
	opens CustomerDemand to javafx.graphics, javafx.fxml;
	
	exports CustomerView;
	opens CustomerView to javafx.graphics, javafx.fxml;
	
	
	exports DealsMatured;
	opens DealsMatured to javafx.graphics, javafx.fxml;
	
	exports CustomerRegistration;
	opens CustomerRegistration to javafx.graphics, javafx.fxml;
	
	exports PropertySales;
	opens PropertySales to javafx.graphics, javafx.fxml;

	
	opens application to javafx.graphics, javafx.fxml;
}
