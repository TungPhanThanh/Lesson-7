package android.tungpt.lesson7;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class RepoAdapter extends RecyclerView.Adapter<RepoAdapter.viewHolder> {
    private ArrayList<Repo> mRepos;
    private LayoutInflater mLayoutInflater;

    public RepoAdapter(ArrayList<Repo> mRepos) {
        this.mRepos = mRepos;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (mLayoutInflater == null) {
            mLayoutInflater = LayoutInflater.from(viewGroup.getContext());
        }
        View view = mLayoutInflater.inflate(R.layout.item_view, viewGroup, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder viewHolder, int i) {
        viewHolder.bindData(mRepos.get(i));
    }

    @Override
    public int getItemCount() {
        return mRepos != null ? mRepos.size() : 0;
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        private TextView mTextViewId;
        private TextView mTextViewName;
        private TextView mTextViewUrl;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            mTextViewId = itemView.findViewById(R.id.text_view_id);
            mTextViewName = itemView.findViewById(R.id.text_view_name);
            mTextViewUrl = itemView.findViewById(R.id.text_view_url);
        }

        public void bindData(Repo repo) {
            if (repo == null) return;
            mTextViewId.setText(repo.getmId());
            mTextViewName.setText(repo.getmName());
            mTextViewUrl.setText(repo.getmHtmlUrl());
        }
    }
}
