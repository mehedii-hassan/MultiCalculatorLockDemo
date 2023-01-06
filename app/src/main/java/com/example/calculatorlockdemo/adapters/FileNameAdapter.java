package com.example.calculatorlockdemo.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.calculatorlockdemo.databinding.FilesRowItemDesignBinding;

import java.util.List;

public class FileNameAdapter extends RecyclerView.Adapter<FileNameAdapter.FileViewHolder> {

    private List<Integer> filesIconList;
    private String[] fileNames;

    public FileNameAdapter(List<Integer> filesIconList, String[] fileNames) {
        this.filesIconList = filesIconList;
        this.fileNames = fileNames;
    }

    @NonNull
    @Override
    public FileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        FilesRowItemDesignBinding binding = FilesRowItemDesignBinding.inflate(LayoutInflater
                .from(parent.getContext()), parent, false);
        return new FileViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull FileViewHolder holder, int position) {
        holder.binding.ivFilesRowDesign.setImageResource(filesIconList.get(position));
        holder.binding.tvFolderName.setText(fileNames[position]);

    }

    @Override
    public int getItemCount() {
        return fileNames.length;
    }

    class FileViewHolder extends RecyclerView.ViewHolder {
        private FilesRowItemDesignBinding binding;

        public FileViewHolder(FilesRowItemDesignBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
