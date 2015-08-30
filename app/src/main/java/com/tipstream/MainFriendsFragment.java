package com.tipstream;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.util.SortedList;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tipstream.adapter.MainRecyclerAdapter;
import com.tipstream.util.Util;

import butterknife.Bind;
import butterknife.ButterKnife;


public class MainFriendsFragment extends Fragment {

    private MainRecyclerAdapter mAdapter;
    private SortedList<Util.Tipcard> mTipcards = new SortedList<>
            (Util.Tipcard.class, new SortedList.Callback<Util.Tipcard>() {
                @Override
                public int compare(Util.Tipcard o1, Util.Tipcard o2) {
                    return o1.getId().compareTo(o2.getId());
                }

                @Override
                public void onInserted(int position, int count) {
                    mAdapter.notifyItemRangeInserted(position, count);
                }

                @Override
                public void onRemoved(int position, int count) {
                    mAdapter.notifyItemRangeRemoved(position, count);
                }

                @Override
                public void onMoved(int fromPosition, int toPosition) {
                    mAdapter.notifyItemMoved(fromPosition, toPosition);
                }

                @Override
                public void onChanged(int position, int count) {
                    mAdapter.notifyItemRangeChanged(position, count);
                }

                @Override
                public boolean areContentsTheSame(Util.Tipcard oldItem,
                                                  Util.Tipcard newItem) {
                    // return whether the items' visual representations are the same or not.
                    return oldItem.getTitle().equals(newItem.getTitle());
                }

                @Override
                public boolean areItemsTheSame(Util.Tipcard item1,
                                               Util.Tipcard item2) {
                    return item1.getId().equals(item2.getId());
                }
            });

    public static MainFriendsFragment newInstance() {
        return new MainFriendsFragment();
    }

    public MainFriendsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.fragment_main_friends, container, false);
        ButterKnife.bind(this, contentView);

        initRecycler();
        return contentView;
    }

    private void initRecycler() {
        mRecyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);

        mAdapter = new MainRecyclerAdapter(getActivity(), mTipcards);
        mTipcards.add(new Util.Tipcard("0", "http://www.simon-li.com/projects/mongkok/cs2/images/gallery/small/random-user-13.jpg", "The best coffee", "StStefan", "The best cofee I've tasted in London. Great service with a testful and relaxed ambience.", "Grind Coffee Bar", "79 Lower Richmond Rd, London", false, false, 127));
        mTipcards.add(new Util.Tipcard("1", "http://presswall1.landingfactory.ru/wp-content/themes/presswall/img/maria.png", "Super Pizza", "Michelle", "Probably the best pizza I've had in London. Very busy, but a must if you like traditional style pizza.", "Pizza East", "56 Shoereditch High St, London", false, false, 56));
        mRecyclerView.setAdapter(mAdapter);
    }

}
