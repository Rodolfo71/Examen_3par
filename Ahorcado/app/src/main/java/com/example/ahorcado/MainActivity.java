import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private String palabraOculta;
    private TextView textViewPalabra;
    private EditText editTextLetra;
    private Button buttonAdivinar;
    private Button buttonReiniciar;
    private ImageView imageViewAhorcado;
    private int intentos = 6;
    private int letrasAdivinadas = 0;
    private boolean juegoTerminado = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewPalabra = findViewById(R.id.textViewPalabra);
        editTextLetra = findViewById(R.id.editTextLetra);
        buttonAdivinar = findViewById(R.id.buttonAdivinar);
        buttonReiniciar = findViewById(R.id.buttonReiniciar);
        imageViewAhorcado = findViewById(R.id.imageViewAhorcado);

        palabraOculta = "ahorcado"; // Palabra a adivinar

        buttonAdivinar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!juegoTerminado) {
                    String letra = editTextLetra.getText().toString();
                    if (letra.length() == 1) {
                        comprobarLetra(letra.toLowerCase());
                    }
                    editTextLetra.setText("");
                }
            }
        });

        buttonReiniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reiniciarJuego();
            }
        });
    }

    private void comprobarLetra(String letra) {
        boolean acierto = false;
        char[] palabraArray = palabraOculta.toCharArray();
        char[] palabraMostradaArray = textViewPalabra.getText().toString().toCharArray();

        for (int i = 0; i < palabraArray.length; i++) {
            if (palabraArray[i] == letra.charAt(0)) {
                palabraMostradaArray[i] = letra.charAt(0);
                acierto = true;
                letrasAdivinadas++;
            }
        }

        if (!acierto) {
            intentos--;
            actualizarImagenAhorcado();
        }

        textViewPalabra.setText(String.valueOf(palabraMostradaArray));

        if (letrasAdivinadas == palabraArray.length) {
            juegoTerminado = true;
            mostrarMensaje("¡Ganaste!");
        } else if (intentos == 0) {
            juegoTerminado = true;
            mostrarMensaje("¡Perdiste! La palabra era: " + palabraOculta);
        }
    }

    private void actualizarImagenAhorcado() {
        switch (intentos) {
            case 5:
                imageViewAhorcado.setImageResource(R.drawable.hangman_5);
                break;
            case 4:
                imageViewAhorcado.setImageResource(R.drawable.hangman_4);
                break;
            case 3:
                imageViewAhorcado.setImageResource(R.drawable.hangman_3);
                break;
            case 2:
                imageViewAhorcado.setImageResource(R.drawable.hangman_2);
                break;
            case 1:
                imageViewAhorcado.setImageResource(R.drawable.hangman_1);
                break;
            case 0:
                imageViewAhorcado.setImageResource(R.drawable.hangman_0);
                break;
        }
    }

    private void mostrarMensaje(String mensaje) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(mensaje)
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        reiniciarJuego();
                    }
                });
        builder.create().show();
    }

    private void reiniciarJuego() {
        intentos = 6;
        letrasAdivinadas = 0;
        juegoTerminado = false;
        textViewPalabra.setText("");
        editTextLetra.setText("");
        imageViewAhorcado.setImageResource(R.drawable.hangman_6);
        palabraOculta = "ahorcado";
    }
}
