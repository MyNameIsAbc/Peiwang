package com.example.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.*;

import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by plcgo on 2016/12/29.
 */

public abstract class BaseAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int TYPE_ITEM_HEADER = 10000;
    public static final int TYPE_ITEM_NORMAL = 10001;
    public static final int TYPE_ITEM_FOOTER = 10002;
    protected List<T> mList = new CopyOnWriteArrayList<>();

    private View mHeaderView;
    private View mFooterView;
    private OnItemLongClickListener mOnItemLongClickListener;
    private OnItemClickListener mOnItemClickListener;

    protected OnViewClickListener mOnViewClickListener;

    public Context context;
    public LayoutInflater layoutInflater;

    public BaseAdapter(Context context) {
        this.context = context;
        this.layoutInflater=LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
         if (viewType == TYPE_ITEM_HEADER) {
            return new Holder(mHeaderView);
        } else if (viewType == TYPE_ITEM_FOOTER) {
            return new Holder(mFooterView);
        } else {
            return onCreate(parent, viewType);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (getItemViewType(position) == TYPE_ITEM_HEADER)
            return;
        if (getItemViewType(position) == TYPE_ITEM_FOOTER)
            return;
        final int pos = getRealPosition(holder);
        final T data = mList.get(pos);
        onBind(holder, pos, data);
        if (holder.itemView!=null){
            if (mOnItemClickListener != null) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mOnItemClickListener.onItemClick(pos, data);
                    }
                });
            }

            if (mOnItemLongClickListener != null) {
                holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        mOnItemLongClickListener.onItemLongClick(v, pos, data);
                        return true;
                    }
                });
            }
        }


    }

    @Override
    public int getItemCount() {
        if (mHeaderView == null && mFooterView == null) {//头布局和脚布局都为空则都是正常布局
            return mList.size();
        } else if (mHeaderView != null && mFooterView == null) {//只有头布局
            return mList.size() + 1;
        } else if (mHeaderView == null && mFooterView != null) {//只有脚布局
            return mList.size() + 1;
        } else {//头布局和脚布局都有
            return mList.size() + 2;
        }
    }

    public void removeData(int position) {
        mList.remove(position);
        notifyItemRemoved(position);
    }



    public List<T> addData(T data) {

            mList.add(data);
            notifyDataSetChanged();
            notifyItemChanged(mList.size()-1);
//        mRecyclerView.scrollToPosition(mList.size() - 1);
        return mList;
    }

    public List<T> addData(int pos, T data) {
        mList.add(pos, data);
        notifyItemChanged(pos);
//        mRecyclerView.scrollToPosition(mList.size() - 1);
        return mList;
    }

    @Override
    public int getItemViewType(int position) {

         if (mHeaderView == null && mFooterView == null) {//头布局和脚布局都为空则都是正常布局
            return TYPE_ITEM_NORMAL;
        } else if (mHeaderView != null && mFooterView == null) {//只有头布局
            if (position == 0)//是头布局
                return TYPE_ITEM_HEADER;
            else//正常布局
                return TYPE_ITEM_NORMAL;
        } else if (mHeaderView == null && mFooterView != null) {//只有脚布局
            if (position == mList.size())//是脚布局
                return TYPE_ITEM_FOOTER;
            else//正常布局
                return TYPE_ITEM_NORMAL;
        } else {//头布局和脚布局都有
            if (position == 0)
                return TYPE_ITEM_HEADER;//头布局
            else if (position == mList.size() + 1)
                return TYPE_ITEM_FOOTER;//脚布局
            else
                return TYPE_ITEM_NORMAL;//正常布局
        }
    }

    public int getRealPosition(RecyclerView.ViewHolder holder) {
        int position = holder.getLayoutPosition();
        return mHeaderView == null ? position : position - 1;
    }

    public void addDatas(List<T> datas) {
        mList.addAll(datas);
        notifyDataSetChanged();
    }

    public T getData(int position){
        return mList.get(position);
    }

    public List<T> getDatas() {
        return mList;
    }

    public void clear() {
        mList.clear();
    }

    public void setHeaderView(View headerView) {
        mHeaderView = headerView;
        notifyItemInserted(0);
    }

    public View getHeaderView() {
        return mHeaderView;
    }

    public void setFooterView(View footerView) {
        mFooterView = footerView;
        notifyDataSetChanged();
    }



    public abstract RecyclerView.ViewHolder onCreate(ViewGroup parent, final int viewType);

    public abstract void onBind(RecyclerView.ViewHolder viewHolder, int position, T data);

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        mOnItemLongClickListener = onItemLongClickListener;
    }

    public void setOnViewClickListener(OnViewClickListener onViewClickListener) {
        mOnViewClickListener = onViewClickListener;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) manager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return getItemViewType(position) == TYPE_ITEM_HEADER
                            ? gridManager.getSpanCount() : 1;
                }
            });
        }
    }

    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
        if (lp != null && lp instanceof StaggeredGridLayoutManager.LayoutParams
                && holder.getLayoutPosition() == 0) {
            StaggeredGridLayoutManager.LayoutParams p = (StaggeredGridLayoutManager.LayoutParams) lp;
            p.setFullSpan(true);
        }
    }

    public class Holder extends RecyclerView.ViewHolder {
        public Holder(View itemView) {
            super(itemView);
        }
    }

    public interface OnItemClickListener<T> {
        void onItemClick(int position, T data);
    }

    public interface OnItemLongClickListener<T> {
        void onItemLongClick(View view, int position, T data);
    }

    public interface OnViewClickListener<T> {
        void onViewClick(View view, int position, T data);
    }
}
