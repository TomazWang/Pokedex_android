package idv.tomazwang.app.pokedex;

import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class MainFragment extends Fragment {

    private final static String TAG = MainFragment.class.getSimpleName();

    public static final int LINER_LAYOUT = 0;
    public static final int GRID_LAYOUT = 1;
    private static final String KEY_LAYOOUT_MABAGER_TYPE = "layoutManagerType";

    @IntDef({LINER_LAYOUT, GRID_LAYOUT})
    public @interface LayoutManagerType {
    }

    @LayoutManagerType
    protected int mCurrentLayoutManagerType = LINER_LAYOUT;

    private int mSpanCount = 3;


    protected RecyclerView mRecyclerView;
    protected RecyclerView.LayoutManager mLayoutManager;
    protected PokedexAdapter mAdapter;

    private ArrayList<String> mDataSet = new ArrayList<>();

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.list_poke);

        // get previous layout type
        if (savedInstanceState != null) {

            switch (savedInstanceState.getInt(KEY_LAYOOUT_MABAGER_TYPE)) {

                case GRID_LAYOUT:
                    mCurrentLayoutManagerType = GRID_LAYOUT;
                    break;

                case LINER_LAYOUT:
                default:
                    mCurrentLayoutManagerType = LINER_LAYOUT;
                    break;
            }
        }

        // init LayoutManager
        setRecyclerViewLayoutManager(mCurrentLayoutManagerType);

        mAdapter = new PokedexAdapter(mDataSet);

        mRecyclerView.setAdapter(mAdapter);


        return rootView;
    }

    private void setRecyclerViewLayoutManager(@LayoutManagerType int type) {
        int scrollPosition = 0;

        // if layout manager has already been set, get current scroll position.
        if(mRecyclerView.getLayoutManager() != null){

            // GridLayoutManager extends LinearLayoutManager.
            scrollPosition = ((LinearLayoutManager)mRecyclerView.getLayoutManager()).findLastVisibleItemPosition();
        }


        switch (type){
            case GRID_LAYOUT:
                mLayoutManager = new GridLayoutManager(getActivity(),mSpanCount);
                mCurrentLayoutManagerType = GRID_LAYOUT;
                break;

            case LINER_LAYOUT:
            default:
                mLayoutManager = new LinearLayoutManager(getActivity());
                mCurrentLayoutManagerType = LINER_LAYOUT;
                break;
        }

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.scrollToPosition(scrollPosition);

    }


    private void initData() {

        // TODO: replace these fake data here!
        for(int i = 0; i<= 80 ;i++){
            mDataSet.add("Pokemon "+i);
        }
    }

}
