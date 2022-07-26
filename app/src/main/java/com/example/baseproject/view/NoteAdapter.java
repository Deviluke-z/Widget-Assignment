package com.example.baseproject.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baseproject.R;
import com.example.baseproject.databinding.ItemLayoutBinding;
import com.example.baseproject.room.model.ToDoModel;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {
  private final Context mContext;
  private List<ToDoModel> mListData;
  private CallBack mCallBack;
  
  public NoteAdapter(Context context, CallBack callBack) {
    this.mContext = context;
    this.mCallBack = callBack;
    mListData = new ArrayList<>();
  }
  
  @SuppressLint("NotifyDataSetChanged")
  public void setListData(List<ToDoModel> mListData) {
    this.mListData = mListData;
    notifyDataSetChanged();
  }
  
  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    ItemLayoutBinding itemView =
      ItemLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
    return new ViewHolder(itemView);
  }
  
  @SuppressLint("SetTextI18n")
  @Override
  public void onBindViewHolder(ViewHolder holder, int position) {
    ToDoModel toDoModel = mListData.get(position);
    holder.itemView.setOnClickListener(v -> mCallBack.onClickNote(toDoModel));
    holder.mBinding.tvToDoTitle.setText(toDoModel.getToDoTitle());
    if (toDoModel.isIsDone()) {
      holder.mBinding.tvToDoTitle.setTextColor(ContextCompat.getColor(mContext, R.color.red));
    } else {
      holder.mBinding.tvToDoTitle.setTextColor(ContextCompat.getColor(mContext, R.color.purple_200));
    }
    
  }
  
  @Override
  public int getItemCount() {
    return this.mListData.size();
  }
  
  public interface CallBack {
    void onClickNote(ToDoModel toDoModel);
  }
  
  public static class ViewHolder extends RecyclerView.ViewHolder {
    private final ItemLayoutBinding mBinding;
    
    public ViewHolder(@NonNull ItemLayoutBinding binding) {
      super(binding.getRoot());
      mBinding = binding;
    }
  }
  
}
