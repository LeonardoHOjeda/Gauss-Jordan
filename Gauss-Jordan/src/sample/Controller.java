package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    JFXButton botonCalcular;
    @FXML
    JFXTextField X1, Y1, Z1,  X2, X3,  Y2, Y3,  Z2, Z3,RES1,RES2,RES3;
    @FXML
    TextField xres,yres,zres;
    double valorX1, valorX2, valorX3, valorY1, valorY2,valorY3,valorZ1,valorZ2,valorZ3,valorRES1,valorRES2,valorRES3;
    double[][] matriz;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            botonCalcular.setOnAction(event -> getResult());
        }catch (Exception e){

        }
    }

    public double[][] matrix(){
        matriz = new double[3][4];
        /*Valores fila 1*/
        valorX1 = Double.parseDouble(X1.getText());
        valorY1 = Double.parseDouble(Y1.getText());
        valorZ1 = Double.parseDouble(Z1.getText());
        /*Valores fila 2*/
        valorX2 = Double.parseDouble(X2.getText());
        valorY2 = Double.parseDouble(Y2.getText());
        valorZ2 = Double.parseDouble(Z2.getText());
        /*Valores fila 3*/
        valorX3 = Double.parseDouble(X3.getText());
        valorY3 = Double.parseDouble(Y3.getText());
        valorZ3 = Double.parseDouble(Z3.getText());
        /*Valores resultado*/
        valorRES1 = Double.parseDouble(RES1.getText());
        valorRES2 = Double.parseDouble(RES2.getText());
        valorRES3 = Double.parseDouble(RES3.getText());

        /*Matriz fila 1*/
        matriz[0][0] = valorX1;
        matriz[0][1] = valorY1;
        matriz[0][2] = valorZ1;
        matriz[0][3] = valorRES1;
        /*Matriz fila 2*/
        matriz[1][0] = valorX2;
        matriz[1][1] = valorY2;
        matriz[1][2] = valorZ2;
        matriz[1][3] = valorRES2;
        /*Matriz fila 3*/
        matriz[2][0] = valorX3;
        matriz[2][1] = valorY3;
        matriz[2][2] = valorZ3;
        matriz[2][3] = valorRES3;



        return matriz;
    }
    public void obtenerX(){
        System.out.println(matrix());
        int pivote, fila, columna;
        double valorPivote, factor;
        int numeroVariables = 3;

        for(pivote = 0; pivote < numeroVariables; pivote++){
            valorPivote = matriz[pivote][pivote];

            imprimeMatriz(); /**Imprime la matriz*/

            for (columna = pivote; columna < numeroVariables + 1; columna++){ //Recorrer la fila del pivote
                matriz[pivote][columna] = matriz[pivote][columna] / valorPivote;
            }

            for (fila = 0; fila < numeroVariables; fila++){ //Recorre la columna del pivote para hacer 0 arriba y abajo
                if(fila != pivote){
                    factor = matriz[fila][pivote];
                    for(columna = pivote; columna < numeroVariables + 1; columna++){
                        matriz[fila][columna] = matriz[fila][columna] - (factor * matriz[pivote][columna]);
                    }
                    imprimeMatriz();
                }
            }
        }
    }

    public void getResult(){
        obtenerX();
        double resultado[] = new double[3];
        for(int fila=0; fila < 3; fila++){
            resultado[fila] = matriz[fila][3];
        }
        double resultado1 = resultado[0];
        double resultado2 = resultado[1];
        double resultado3 = resultado[2];

        if(Double.isNaN(resultado1)|| Double.isNaN(resultado2) || Double.isNaN(resultado3) ){
            xres.setText("ERROR!");
            yres.setText("ERROR!");
            zres.setText("ERROR!");
        } else {
            xres.setText(String.valueOf(resultado1));
            System.out.println(resultado1);
            yres.setText(String.valueOf(resultado2));
            System.out.println(resultado2);
            zres.setText(String.valueOf(resultado3));
            System.out.println(resultado3);
        }
    }

    public void imprimeMatriz(){
        for (int i = 0; i<3; i++){
            for (int k=0; k<4; k++){
                    System.out.print("|"+matriz[i][k]+"|");
            }
            System.out.println("");
        }
        System.out.println("=========================");
    }
}
