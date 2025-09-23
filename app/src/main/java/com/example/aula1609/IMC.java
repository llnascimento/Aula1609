package com.example.aula1609;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class IMC extends AppCompatActivity {

    private EditText editPeso, editAltura;
    private Button btnCalcular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_imc);

        // Ajuste de padding das barras do sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Referenciar os elementos da tela
        editPeso = findViewById(R.id.num1); // Peso
        editAltura = findViewById(R.id.num2); // Altura
        btnCalcular = findViewById(R.id.btnSoma);

        // Clique do botão
        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcularIMC();
            }
        });
    }

    private void calcularIMC() {
        String pesoStr = editPeso.getText().toString();
        String alturaStr = editAltura.getText().toString();

        if (pesoStr.isEmpty() || alturaStr.isEmpty()) {
            Toast.makeText(this, "Preencha peso e altura", Toast.LENGTH_SHORT).show();
            return;
        }

        double peso = Double.parseDouble(pesoStr);
        double altura = Double.parseDouble(alturaStr);

        if (altura <= 0) {
            Toast.makeText(this, "Altura deve ser maior que 0", Toast.LENGTH_SHORT).show();
            return;
        }

        double imc = peso / (altura * altura);

        // Classificação opcional
        String classificacao;

        if (imc <= 18.5) {
            classificacao = "Magreza";
        }

        else if (imc > 18.5 && imc <= 24.9) {
            classificacao = "Peso normal";
        }

        else if (imc > 24.9 && imc <= 29.9) {
            classificacao = "Sobrepeso";
        }

        else if (imc > 29.9 && imc <= 39.9) {
            classificacao = "Obesidade";
        }


        else {
            classificacao = "Obesidade grave";
        }

        String resultado = String.format("IMC: %.2f - %s", imc, classificacao);

        // Exibir Toast
        Toast.makeText(this, resultado, Toast.LENGTH_LONG).show();
    }
}
