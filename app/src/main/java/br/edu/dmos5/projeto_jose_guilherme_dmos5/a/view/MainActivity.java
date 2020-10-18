package br.edu.dmos5.projeto_jose_guilherme_dmos5.a.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import br.edu.dmos5.projeto_jose_guilherme_dmos5.R;
import br.edu.dmos5.projeto_jose_guilherme_dmos5.a.Dao.PesquisaDao;
import br.edu.dmos5.projeto_jose_guilherme_dmos5.a.Model.Pesquisa;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    public static final int REQUESTCODE_NOVA_PESQUISA = 99;
    public static final String KEY_PESQUISA = "pesquisa";


    private List<Pesquisa> pesquisaList;
    private PesquisaDao pesquisaDao;

    private PesquisaAdapter pesquisaAdapter;
    private RecyclerView recyclerView;
    private Button buscarButton;
    private Spinner spinner;

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recylerview_consultas_realizadas);
        buscarButton = findViewById(R.id.button_nova_busca);

        buscarButton.setOnClickListener(this);

        pesquisaDao = new PesquisaDao(this);
        pesquisaList = pesquisaDao.recuperaTodos();

        //RecyclerView
        pesquisaAdapter = new PesquisaAdapter(pesquisaList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(pesquisaAdapter);

        spinner = findViewById(R.id.estados_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.siglas_estados, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateUI();
    }

    @Override
    public void onClick(View view) {
        if(view == buscarButton ){
            realizaPesquisa();
        }
    }

    private void realizaPesquisa(){

        String uf = spinner.getSelectedItem().toString();

        Bundle argumentos = new Bundle();

        argumentos.putString(KEY_PESQUISA, uf);

        Intent intent = new Intent(this, PesquisaActivity.class);

        intent.putExtras(argumentos);
        startActivityForResult(intent, REQUESTCODE_NOVA_PESQUISA);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        switch (requestCode){
            case REQUESTCODE_NOVA_PESQUISA:

                if(resultCode == RESULT_OK){
                    updateDataSet();
                    recyclerView.getAdapter().notifyDataSetChanged();
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void updateUI(){

        if(pesquisaList.size() == 0){
            recyclerView.setVisibility(View.GONE);
        }
        else{
            recyclerView.setVisibility(View.VISIBLE);
        }
    }

    private void updateDataSet(){
        pesquisaList.clear();
        pesquisaList.addAll(pesquisaDao.recuperaTodos());
    }
}