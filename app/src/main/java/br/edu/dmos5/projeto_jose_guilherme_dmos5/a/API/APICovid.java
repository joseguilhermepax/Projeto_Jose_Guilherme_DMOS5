package br.edu.dmos5.projeto_jose_guilherme_dmos5.a.API;

import br.edu.dmos5.projeto_jose_guilherme_dmos5.a.Model.Pesquisa;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface APICovid {

    String URL = "https://covid19-brazil-api.now.sh/api/report/v1/brazil/uf/";

    @GET("{estado}")
    Call<Pesquisa> getDados(@Path("estado") String uf);
}
