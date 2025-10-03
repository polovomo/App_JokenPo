package br.ulbra.app_jokenpo;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    // Placar do jogador e do app
    int pontuacaoJogador = 0;
    int pontuacaoApp = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // Chamado quando o jogador seleciona pedra
    public void selecionadoPedra(View view) {
        this.opcaoSelecionado("pedra");
    }

    // Chamado quando o jogador seleciona papel
    public void selecionadoPapel(View view) {
        this.opcaoSelecionado("papel");
    }

    // Chamado quando o jogador seleciona tesoura
    public void selecionadoTesoura(View view) {
        this.opcaoSelecionado("tesoura");
    }

    // Processa a jogada e define o resultado
    public void opcaoSelecionado(String opcaoSelecionada) {
        ImageView imageResultado = findViewById(R.id.imgApp);
        TextView txtResult = findViewById(R.id.txtResultado);
        TextView txtPlacar = findViewById(R.id.txtPlacar);

        // Escolha aleatória do app
        String[] opcoes = {"pedra", "papel", "tesoura"};
        String opcaoApp = opcoes[new Random().nextInt(3)];

        // Atualiza imagem com base na escolha do app
        switch (opcaoApp) {
            case "pedra":
                imageResultado.setImageResource(R.drawable.pedra);
                break;
            case "papel":
                imageResultado.setImageResource(R.drawable.papel);
                break;
            case "tesoura":
                imageResultado.setImageResource(R.drawable.tesoura);
                break;
        }

        // Verifica resultado da rodada
        if ((opcaoApp.equals("tesoura") && opcaoSelecionada.equals("papel")) ||
                (opcaoApp.equals("papel") && opcaoSelecionada.equals("pedra")) ||
                (opcaoApp.equals("pedra") && opcaoSelecionada.equals("tesoura"))) {

            txtResult.setText("Resultado: Você PERDEU... :(");
            pontuacaoApp++;

        } else if ((opcaoSelecionada.equals("tesoura") && opcaoApp.equals("papel")) ||
                (opcaoSelecionada.equals("papel") && opcaoApp.equals("pedra")) ||
                (opcaoSelecionada.equals("pedra") && opcaoApp.equals("tesoura"))) {

            txtResult.setText("Resultado: Você GANHOU... ;D");
            pontuacaoJogador++;

        } else {
            txtResult.setText("Resultado: EMPATE... :|");
        }

        // Atualiza placar
        atualizarPlacar(txtPlacar);
    }

    // Atualiza o placar na interface
    public void atualizarPlacar(TextView txtPlacar) {
        txtPlacar.setText("Jogador: " + pontuacaoJogador + " - App: " + pontuacaoApp);
    }

    // Reinicia o jogo
    public void reiniciarJogo(View view) {
        pontuacaoJogador = 0;
        pontuacaoApp = 0;
        atualizarPlacar((TextView) findViewById(R.id.txtPlacar));

        // Restaura imagem e texto padrão
        ImageView imageResultado = findViewById(R.id.imgApp);
        imageResultado.setImageResource(R.drawable.padrao);

        TextView txtResult = findViewById(R.id.txtResultado);
        txtResult.setText("Resultado: ");
    }
}
