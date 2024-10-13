package extensoInterface;

import codigo.Extenso;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class FXMLDocumentController implements Initializable {
    
    @FXML
    private TextField txtNumero;
    @FXML
    private Label lblResultado;

    public void transformarEmExtenso() {
        Extenso extenso = new Extenso();
        int numero = Integer.parseInt(txtNumero.getText());
        String numeroPorExtenso = extenso.transformarEmExtenso(numero);
        
        lblResultado.setText(numeroPorExtenso);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
