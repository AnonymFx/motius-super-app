package de.thomas.pettinger.motiussuperapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class UseCaseRecyclerViewAdapter extends RecyclerView.Adapter<UseCaseRecyclerViewAdapter.ViewHolder> {

    private final List<UseCase> mValues;

    public UseCaseRecyclerViewAdapter(List<UseCase> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_usecase_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mTitleView.setText(mValues.get(position).getTitle());
        holder.mBodyView.setText(mValues.get(position).getBodyMax250());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), v.getContext().getString(R.string.list_item_on_click_message), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mTitleView;
        public final TextView mBodyView;
        public UseCase mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mTitleView = (TextView) view.findViewById(R.id.listitem_title);
            mBodyView = (TextView) view.findViewById(R.id.listitem_desc);

        }

        @Override
        public String toString() {
            return super.toString() + " '" + mBodyView.getText() + "'";
        }
    }
}
