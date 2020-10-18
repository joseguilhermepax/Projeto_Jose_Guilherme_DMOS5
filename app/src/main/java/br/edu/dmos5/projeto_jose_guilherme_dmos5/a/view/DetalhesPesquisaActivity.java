package br.edu.dmos5.projeto_jose_guilherme_dmos5.a.view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import br.edu.dmos5.projeto_jose_guilherme_dmos5.R;
import br.edu.dmos5.projeto_jose_guilherme_dmos5.a.Dao.PesquisaDao;
import br.edu.dmos5.projeto_jose_guilherme_dmos5.a.Model.Pesquisa;

public class DetalhesPesquisaActivity extends AppCompatActivity implements View.OnClickListener {

    public static final int REQUESTCODE_DETALHE = 97;

    private TextView ufTextView;
    private TextView estadoTextView;
    private TextView casosTextView;
    private TextView mortesTextView;
    private TextView suspeitosTextView;
    private TextView recusadosTextView;
    private TextView dataTextView;

    PesquisaDao pesquisaDao;
    Pesquisa pesquisa;
    private PesquisaAdapter pesquisaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_pesquisa);

        ufTextView        = findViewById(R.id.textview_uf);
        estadoTextView    = findViewById(R.id.textview_estado);
        casosTextView     = findViewById(R.id.textview_casos);
        mortesTextView    = findViewById(R.id.textview_mortes);
        suspeitosTextView = findViewById(R.id.textview_suspeitos);
        recusadosTextView = findViewById(R.id.textview_recusados);
        dataTextView      = findViewById(R.id.textview_data);

        //Recuperar dados do intent
        Intent intent = getIntent();
        Integer id = intent.getIntExtra("ID", 1);

        //Recuperar dados do BD
        pesquisaDao = new PesquisaDao(this);
        pesquisa = pesquisaDao.recupera(id);

        //Configura apresentacao de dados
        ufTextView.setText(pesquisa.getUf());
        estadoTextView.setText(pesquisa.getState());
        casosTextView.setText(pesquisa.getCasos());
        mortesTextView.setText(pesquisa.getMortes());
        suspeitosTextView.setText(pesquisa.getSuspeitos());
        recusadosTextView.setText(pesquisa.getRecusados());
        dataTextView.setText(pesquisa.getData());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUESTCODE_DETALHE){
            if(resultCode == RESULT_OK){

                pesquisa = pesquisaDao.recupera(pesquisa.getId());
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}