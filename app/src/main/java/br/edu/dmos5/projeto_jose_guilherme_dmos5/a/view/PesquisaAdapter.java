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
    private static PesquisaClickListener clickListener;

    public PesquisaAdapter(@NonNull List<Pesquisa> pesquisas){
        this.pesquisaList = pesquisas;
    }

    public void setClickListener(PesquisaClickListener clickListener) {
        PesquisaAdapter.clickListener = clickListener;
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
        holder.pesquisaIDTextView.setText((pesquisaList.get(position).getId().toString()));
    }

    @Override
    public int getItemCount() {
        return pesquisaList.size();
    }

    public class PesquisaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView pesquisaTextView;
        private TextView pesquisaIDTextView;

        public PesquisaViewHolder(@NonNull View itemView) {

            super(itemView);

            pesquisaTextView   = itemView.findViewById(R.id.textview_pesquisa);
            pesquisaIDTextView = itemView.findViewById(R.id.textview_pesquisa_id);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(clickListener != null){
                clickListener.onPesquisaClick(getAdapterPosition());
            }
        }
    }
}