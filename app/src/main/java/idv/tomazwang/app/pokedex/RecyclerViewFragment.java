package idv.tomazwang.app.pokedex;/**
 * Created by Rbur on 2016/8/30.
 */

import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * @authour TomazWang
 */
public abstract class RecyclerViewFragment<D> extends Fragment {

    public static final int LINER_LAYOUT = 0;
    public static final int GRID_LAYOUT = 1;
    private static final String KEY_LAYOOUT_MABAGER_TYPE = "layoutManagerType";

    @IntDef({LINER_LAYOUT, GRID_LAYOUT})
    public @interface LayoutManagerType {
    }

    @LayoutManagerType
    protected int mCurrentLayoutManagerType = GRID_LAYOUT;

    protected int mSpanCount = 3;

    protected RecyclerView mRecyclerView;
    protected RecyclerView.LayoutManager mLayoutManager;

    protected RecyclerView.Adapter mAdapter;

    private ArrayList<D> mDataSet = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        initDataSet(mDataSet);
        mAdapter = createAdapter();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
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

        mRecyclerView.setAdapter(mAdapter);


        return rootView;
    }


    protected void changeRecyclerViewLayoutManager(){
        if(mCurrentLayoutManagerType == LINER_LAYOUT){
            setRecyclerViewLayoutManager(GRID_LAYOUT);
        }else{
            setRecyclerViewLayoutManager(LINER_LAYOUT);
        }
    }


    protected void setRecyclerViewLayoutManager(@LayoutManagerType int type) {
        int scrollPosition = 0;

        // if layout manager has already been set, get current scroll position.
        if(mRecyclerView.getLayoutManager() != null){

            // GridLayoutManager extends LinearLayoutManager.
            scrollPosition = ((LinearLayoutManager)mRecyclerView.getLayoutManager()).findFirstVisibleItemPosition();
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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_main_fragmet, menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.action_changeView){
            changeRecyclerViewLayoutManager();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    abstract protected void initDataSet(ArrayList<D> dataSet);

    abstract protected RecyclerView.Adapter createAdapter();

    protected ArrayList<D> getDataSet(){
        return mDataSet;
    }

    public RecyclerView.Adapter getAdapter() {
        return mAdapter;
    }

    public int getSpanCount() {
        return mSpanCount;
    }

    public void setSpanCount(int mSpanCount) {
        this.mSpanCount = mSpanCount;
    }

}
