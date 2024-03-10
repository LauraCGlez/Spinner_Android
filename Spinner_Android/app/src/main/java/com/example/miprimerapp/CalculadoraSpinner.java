package com.example.miprimerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class CalculadoraSpinner extends AppCompatActivity {
    private EditText num_a;
    private EditText num_b;
    private Spinner operadores;
    private Button btnCalcular;
    private TextView resultado;
    private Spinner btnCambiarPantallaSpinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculadora_spinner);
        //INICIALIZAR VARIABLES
        num_a = findViewById(R.id.num_a);
        num_b = findViewById(R.id.num_b);
        operadores = (Spinner) findViewById(R.id.operadores);
        btnCalcular = findViewById(R.id.btnCalcular1);
        resultado = findViewById(R.id.resultado);
        cargarDatosPantallaBtn();
        cargarOperadorSpinner();

        //mensaje al usuario
        Toast.makeText(this,"app calculadora Spinner", Toast.LENGTH_SHORT).show();

    }

    private void cargarDatosPantallaBtn() {
        //almacenar los datos de la pantalla Buton
        String n1 = getIntent().getStringExtra("n1");
        String n2 = getIntent().getStringExtra("n2");
        String r = getIntent().getStringExtra("r");
        //establecer los datos en la pantalla actual
        num_a.setText(n1);
        num_b.setText(n2);
        resultado.setText(r);
    }

    public void calcularOperacion(View view) {
        //comprobar si los campos estan vacios
        if(num_a.getText().toString().isEmpty() || num_b.getText().toString().isEmpty()){
            Toast.makeText(this, "Por favor, introducir numeros", Toast.LENGTH_SHORT).show();
            return;
        }
        //almacenar los numeros introducidos por usuario
        double numero1 = Double.parseDouble(num_a.getText().toString());
        double numero2 = Double.parseDouble(num_b.getText().toString());

        //almacenar el spinner seleccionado
        String operadorSeleccionado = operadores.getSelectedItem().toString();

        double operacion = 0;
        switch (operadorSeleccionado){
            case "sumar":
                operacion = numero1 + numero2;
                break;
            case "restar":
                operacion = numero1 - numero2;
                break;
            case "dividir":
                if(numero2 != 0){
                    operacion = numero1 / numero2;
                }else {
                    Toast.makeText(this,"Division entre 0 no es permitida", Toast.LENGTH_SHORT).show();
                    return;
                }
                break;
            case "multiplicar":
                operacion = numero1 * numero2;
                break;
        }

        resultado.setText("Resultado: " + String.valueOf(operacion));
    }

    private void cargarOperadorSpinner() {
        //cargar listado de operadores al spinner
        String[] opciones = {"sumar","restar","multiplicar","dividir"};
        //instanciar los datos al spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.spinner_item,opciones);
        operadores.setAdapter(adapter);
        operadores.setPrompt("");

    }

    public void cambiarPantallaButton(View view){
        Intent pantallaButton = new Intent(this, MainActivity.class);
        //compartir los datos de la actual pantalla
        pantallaButton.putExtra("sn1", num_a.getText().toString());
        pantallaButton.putExtra("sn2", num_b.getText().toString());
        pantallaButton.putExtra("sr", resultado.getText().toString());
        startActivity(pantallaButton);
    }
}