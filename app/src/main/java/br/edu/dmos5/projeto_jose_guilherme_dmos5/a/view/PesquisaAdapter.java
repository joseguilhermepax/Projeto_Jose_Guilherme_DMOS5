package br.edu.dmos5.projeto_jose_guilherme_dmos5.a.view;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.edu.dmos5.projeto_jose_guilherme_dmos5.R;
import br.edu.dmos5.projeto_jose_guilherme_dmos5.a.Model.Pesquisa;

public class PesquisaAdapter extends RecyclerView.Adapter<PesquisaAdapter.PesquisaViewHolder> {

    private List<Pesquisa> pesquisaList;

    public PesquisaAdapter(@NonNull List<Pesquisa> pesquisas){
        this.pesquisaList = pesquisas;
    }

    @NonNull
    @Override
    public PesquisaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_pesquisa_adapter, parent, false);

        PesquisaViewHolder viewHolder = new PesquisaViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PesquisaViewHolder holder, int position) {
        holder.pesquisaTextView.setText(pesquisaList.get(position).getNomePesquisa());
    }

    @Override
    public int getItemCount() {
        return pesquisaList.size();
    }

    public class PesquisaViewHolder extends RecyclerView.ViewHolder{

        private TextView pesquisaTextView;

        public PesquisaViewHolder(@NonNull View itemView) {
            super(itemView);
            pesquisaTextView =itemView.findViewById(R.id.textview_pesquisa);
        }
    }
}