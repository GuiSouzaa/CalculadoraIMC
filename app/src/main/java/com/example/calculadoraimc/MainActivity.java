package com.example.calculadoraimc;


import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.google.android.material.textfield.TextInputEditText;
import androidx.appcompat.app.AppCompatActivity;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void calcularImc(View view) {
        TextInputEditText campoNome = findViewById(R.id.textinputNome);
        TextInputEditText campoPeso = findViewById(R.id.textinputPeso);
        TextInputEditText campoAltura = findViewById(R.id.textinputAltura);
        TextView resultado = findViewById(R.id.textResultado2);

        String nome = campoNome.getText().toString();
        String peso = campoPeso.getText().toString();
        String altura = campoAltura.getText().toString();

        if (!peso.isEmpty() && !altura.isEmpty()) {
            try {
                Double numPeso = Double.parseDouble(peso);
                Double numAltura = Double.parseDouble(altura);
                if (numAltura <= 0) {
                    resultado.setText("Erro: altura deve ser maior que zero.");
                    resultado.setVisibility(View.VISIBLE);
                    return;
                }
                Double numImc = numPeso / (numAltura * numAltura);

                DecimalFormat df = new DecimalFormat("##.##");
                String imc = df.format(numImc);

                // Determinar a classificação de acordo com o IMC
                String classificacao;
                if (numImc < 18.5) {
                    classificacao = "Abaixo do peso";
                } else if (numImc >= 18.5 && numImc < 24.9) {
                    classificacao = "Peso normal";
                } else if (numImc >= 25 && numImc < 29.9) {
                    classificacao = "Sobrepeso";
                } else if (numImc >= 30 && numImc < 34.9) {
                    classificacao = "Obesidade Grau 1";
                } else if (numImc >= 35 && numImc < 39.9) {
                    classificacao = "Obesidade Grau 2";
                } else {
                    classificacao = "Obesidade Grau 3";
                }

                resultado.setText("IMC: " + imc + "\nClassificação: " + classificacao);
                resultado.setVisibility(View.VISIBLE);
            } catch (NumberFormatException e) {
                resultado.setText("Erro: insira valores válidos.");
                resultado.setVisibility(View.VISIBLE);
            }
        } else {
            resultado.setText("Erro: todos os campos devem ser preenchidos.");
            resultado.setVisibility(View.VISIBLE);
        }
    }

    public void limpaDados(View view) {
        TextInputEditText campoNome = findViewById(R.id.textinputNome);
        TextInputEditText campoPeso = findViewById(R.id.textinputPeso);
        TextInputEditText campoAltura = findViewById(R.id.textinputAltura);
        TextView resultado = findViewById(R.id.textResultado2);

        campoNome.setText("");
        campoPeso.setText("");
        campoAltura.setText("");
        resultado.setText("");
        resultado.setVisibility(View.GONE);
    }
}
