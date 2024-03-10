package com.example.miprimerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText number_a;
    private EditText number_b;
    private RadioGroup radioGroup;
    private RadioButton aritmetica;
    private Button btnCalcular;
    private TextView resultado;
    private Switch btnCambiarPantalla;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //INICIALIZAR VARIABLES
        number_a = findViewById(R.id.number_a);
        number_b = findViewById(R.id.number_b);
        radioGroup = findViewById(R.id.radiobtnGroup);
        btnCalcular = findViewById(R.id.btnCalcular2);
        resultado = findViewById(R.id.resultado);
        cargarDatosPantallaSpinner();

        //mensaje al usuario
        Toast.makeText(this,"app calculadora Button", Toast.LENGTH_SHORT).show();
    }

    private void cargarDatosPantallaSpinner() {
        //almacenar los datos de la pantalla Buton
        String sn1 = getIntent().getStringExtra("sn1");
        String sn2 = getIntent().getStringExtra("sn2");
        String sr = getIntent().getStringExtra("sr");
        //establecer los datos en la pantalla actual
        number_a.setText(sn1);
        number_b.setText(sn2);
        resultado.setText(sr);


    }

    /**
     * Metodo para realizar operaciones aritmeticas
     * @param view
     */
    public void calcular(View view){
        //comprobar si los campos estan vacios
        if(number_a.getText().toString().isEmpty() || number_b.getText().toString().isEmpty()){
            Toast.makeText(this, "Por favor, introducir numeros", Toast.LENGTH_SHORT).show();
            return;
        }
        //almacenar los numeros introducidos por usuario
        double numero1 = Double.parseDouble(number_a.getText().toString());
        double numero2 = Double.parseDouble(number_b.getText().toString());

        //alamcenar el radioBtn seleccionado
        int checkedRadioButtonId = radioGroup.getCheckedRadioButtonId();

        //comprobar si el radioBtn ha sido seleccionado
        if(checkedRadioButtonId == -1){
            Toast.makeText(this, "Por favor, seleccione una operacion", Toast.LENGTH_SHORT).show();
            return;
        }

        //almacenar el tipo de aritmetica
        aritmetica = findViewById(checkedRadioButtonId);
        //convertir a minuscula el texto recibido
        String lowerCase = aritmetica.getText().toString().toLowerCase();

        double operacion = 0;
        switch (lowerCase){
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

    /**
     * Metodo para limpiar los campos introducidos
     * @param view
     */
    public void reset(View view){
        number_a.setText("");
        number_b.setText("");
        aritmetica.setChecked(false);
        resultado.setText("Resultado");

    }

    public void cambiarPantallaSpinner(View view){
        //instanciar para el cambio de pantalla
        Intent pantallaSpinner = new Intent(this, CalculadoraSpinner.class);
        //compartir los datos de la actual pantalla
        pantallaSpinner.putExtra("n1", number_a.getText().toString());
        pantallaSpinner.putExtra("n2", number_b.getText().toString());
        pantallaSpinner.putExtra("r", resultado.getText().toString());
        //comenzar activity
        startActivity(pantallaSpinner);
    }

}