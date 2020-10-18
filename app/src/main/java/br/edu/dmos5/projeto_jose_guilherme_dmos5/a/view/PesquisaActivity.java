package br.edu.dmos5.projeto_jose_guilherme_dmos5.a.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import br.edu.dmos5.projeto_jose_guilherme_dmos5.R;
import br.edu.dmos5.projeto_jose_guilherme_dmos5.a.API.APICovid;
import br.edu.dmos5.projeto_jose_guilherme_dmos5.a.Dao.PesquisaDao;
import br.edu.dmos5.projeto_jose_guilherme_dmos5.a.Model.Pesquisa;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PesquisaActivity extends AppCompatActivity {

    private TextView ufTextView;
    private TextView estadoTextView;
    private TextView casosTextView;
    private TextView mortesTextView;
    private TextView suspeitosTextView;
    private TextView recusadosTextView;
    private TextView dataTextView;

    private static final int REQUEST_PERMISSION = 64;

    private String ufPesquisar;
    private PesquisaDao pesquisaDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisa);

        pesquisaDao = new PesquisaDao(this);

        ufTextView        = findViewById(R.id.textview_uf);
        estadoTextView    = findViewById(R.id.textview_estado);
        casosTextView     = findViewById(R.id.textview_casos);
        mortesTextView    = findViewById(R.id.textview_mortes);
        suspeitosTextView = findViewById(R.id.textview_suspeitos);
        recusadosTextView = findViewById(R.id.textview_recusados);
        dataTextView      = findViewById(R.id.textview_data);

        pegarUF();

        if(checaPermissao()){
            buscaAPI();
        }
        else{
            solicitaPermissao();
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case android.R.id.home:
                finish();
        }

        return super.onOptionsItemSelected(item);
    }

    private void pegarUF(){

        Intent intent = getIntent();
        Bundle embrulho = intent.getExtras();

        if(embrulho != null){
            ufPesquisar = embrulho.getString(MainActivity.KEY_PESQUISA);
        }

        Toast.makeText(PesquisaActivity.this, "Consultando API... ", Toast.LENGTH_SHORT).show();
    }

    private boolean checaPermissao(){

        return ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET) == PackageManager.PERMISSION_GRANTED;
    }

    private void solicitaPermissao(){

        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.INTERNET)) {

            final Activity activity = this;

            new AlertDialog.Builder(this)
            .setMessage("Para realizar a busca será necessário permitir a utilização de internet no aplicativo")
            .setPositiveButton("Permitir", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.INTERNET}, REQUEST_PERMISSION);
                }
            })
            .setNegativeButton("Não permitir", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            })
            .show();
        }
        else {
            ActivityCompat.requestPermissions(
            this,
            new String[]{
                    Manifest.permission.INTERNET
            },
            REQUEST_PERMISSION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_PERMISSION) {

            for (int i = 0; i < permissions.length; i++) {

                if (permissions[i].equalsIgnoreCase(Manifest.permission.INTERNET) && grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    buscaAPI();
                }
            }
        }
    }

    private void buscaAPI(){

        Retrofit retrofit = new Retrofit.Builder().baseUrl(APICovid.URL).addConverterFactory(GsonConverterFactory.create()).build();
        APICovid apiCovid = retrofit.create(APICovid.class);

        final Call<Pesquisa> call = apiCovid.getDados(ufPesquisar);

        call.enqueue(new Callback<Pesquisa>() {

            @Override
            public void onResponse(Call<Pesquisa> call, Response<Pesquisa> response) {

                if(response.isSuccessful()){

                    Toast.makeText(PesquisaActivity.this, "Busca realizada com sucesso! ", Toast.LENGTH_SHORT).show();

                    Pesquisa pesquisa = response.body();

                    pesquisaDao.adicionar(pesquisa);

                    setResult(Activity.RESULT_OK);

                    update(pesquisa);
                }
            }

            @Override
            public void onFailure(Call<Pesquisa> call, Throwable t) {

                Toast.makeText(PesquisaActivity.this, "Erro ai realizar a consulta na API", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void update(Pesquisa pesquisa){

        if(pesquisa != null){

            ufTextView.setText(pesquisa.getUf());
            estadoTextView.setText(pesquisa.getState());
            casosTextView.setText(pesquisa.getCasos());
            mortesTextView.setText(pesquisa.getMortes());
            suspeitosTextView.setText(pesquisa.getSuspeitos());
            recusadosTextView.setText(pesquisa.getRecusados());
            dataTextView.setText(pesquisa.getData());
        }
    }
}