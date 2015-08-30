package com.tipstream.adapter;

import android.content.Context;
import android.support.v7.util.SortedList;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.tipstream.R;
import com.tipstream.util.Util;

public class MainRecyclerAdapter extends RecyclerView.Adapter<MainRecyclerAdapter.ViewHolder> {

    private Context mContext;
    private ImageLoader mImageLoader;
    private SortedList<Util.Tipcard> mTipcards;

    // Provide a reference to the views for each data item
    public class ViewHolder extends RecyclerView.ViewHolder implements
            View.OnClickListener {
        public ImageView avatarImg;
        public TextView usernameTxt;
        public TextView titleTxt;
        public ImageButton favoriteBtn;
        public ImageButton shareBtn;
        public TextView commentTxt;
        public TextView nameTxt;
        public TextView addressTxt;

        public ViewHolder(View v) {
            super(v);
            avatarImg = (ImageView) v.findViewById(R.id.tipcard_avatar_img);
            usernameTxt = (TextView) v.findViewById(R.id.tipcard_username_txt);
            titleTxt = (TextView) v.findViewById(R.id.tipcard_title_txt);
            favoriteBtn = (ImageButton) v.findViewById(R.id.tipcard_favorite_btn);
            shareBtn = (ImageButton) v.findViewById(R.id.tipcard_share_btn);
            commentTxt = (TextView) v.findViewById(R.id.tipcard_comment_txt);
            nameTxt = (TextView) v.findViewById(R.id.tipcard_name_txt);
            addressTxt = (TextView) v.findViewById(R.id.tipcard_address_txt);

            favoriteBtn.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            actionsClickListener.onItemClick(v, getAdapterPosition());
        }

        OnItemClickListener actionsClickListener = new OnItemClickListener() {

            @Override
            public void onItemClick(View v, int position) {
                if (v.getId() == R.id.tipcard_favorite_btn) {
                    setFavorite(v, position);
                } else {
                    like(position);
                }
            }

            private void setFavorite(View v, int position) {
                mTipcards.get(position).isFavorite = !mTipcards.get(position).isFavorite();
                updateFavoriteState((ImageButton) v, position);
            }

            private void like(int position) {
                mTipcards.get(position).isLiked = !mTipcards.get(position).isLiked();
                if (mTipcards.get(position).isLiked) {
                    mTipcards.get(position).rating++;
                } else {
                    mTipcards.get(position).rating--;
                }
                mTipcards.updateItemAt(position, mTipcards.get(position));
            }

        };
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }


    // Provide a suitable constructor
    public MainRecyclerAdapter(Context context, SortedList<Util.Tipcard> tipcards) {
        mContext = context;
        mTipcards = tipcards;

        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder().cacheInMemory(true)
                .cacheOnDisk(true)
                .displayer(new FadeInBitmapDisplayer(250, true, false, false))
                .showImageOnLoading(R.color.avatar_placeholder).build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                context).defaultDisplayImageOptions(defaultOptions).build();
        ImageLoader.getInstance().init(config);
        mImageLoader = ImageLoader.getInstance();
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MainRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                             int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_tripcard, parent, false);

        return new ViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        mImageLoader.displayImage(mTipcards.get(position).getAvatar(), holder.avatarImg);
        holder.usernameTxt.setText("By @" + mTipcards.get(position).getUsername());
        holder.titleTxt.setText(mTipcards.get(position).getTitle());
        //holder.favoriteBtn.setText(mTipcards.get(position).getTitle());
        holder.commentTxt.setText(mTipcards.get(position).getComment());
        holder.nameTxt.setText(mTipcards.get(position).getName());
        holder.addressTxt.setText(mTipcards.get(position).getAddress());
        updateFavoriteState(holder.favoriteBtn, position);
    }

    private void updateFavoriteState(ImageButton favoriteBtn, int position) {
        if (mTipcards.get(position).isFavorite()) {
            favoriteBtn.setImageResource(R.drawable.ic_favorite_checked);
        } else {
            favoriteBtn.setImageResource(R.drawable.ic_favorite);
        }
    }


    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mTipcards.size();
    }
}