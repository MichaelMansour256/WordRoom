package com.example.wordroom;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class WordAdapter extends RecyclerView.Adapter<WordAdapter.WordViewHolder> {
    private List<Words> mWords = new ArrayList<>();
    private OnItemClickListener mListener;

    @NonNull
    @NotNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.word_list_item,parent,false);
        return new WordViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull WordAdapter.WordViewHolder holder, int position) {
        Words currentWord=mWords.get(position);
        holder.textViewWord.setText(currentWord.getWordName());
        holder.textViewMeaning.setText(currentWord.getWordMeaning());
        holder.textViewType.setText(currentWord.getWordType());
    }

    public void setWords(List<Words> words) {
        mWords = words;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mWords.size();
    }

    public class WordViewHolder extends RecyclerView.ViewHolder{

        public TextView textViewWord;
        public TextView textViewMeaning;
        public TextView textViewType;

        public WordViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            textViewWord=itemView.findViewById(R.id.word_text_view);
            textViewMeaning=itemView.findViewById(R.id.meaning_text_view);
            textViewType=itemView.findViewById(R.id.type_text_view);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int index=getAdapterPosition();
                    if (mListener!=null && index!= RecyclerView.NO_POSITION){
                        mListener.onItemClick(mWords.get(index));
                    }
                }
            });
        }
    }
    public interface OnItemClickListener{
        void onItemClick(Words word);
    }
    public void onItemClickListener(OnItemClickListener listener){
        mListener=listener;
    }
    public Words getWordAt(int pos){
        return mWords.get(pos);
    }
}
