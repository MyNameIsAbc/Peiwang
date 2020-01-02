package com.example.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.db.Language;
import com.example.peiwang.R;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LanguageSetAdapter extends BaseAdapter {
    List<Language> mList = new CopyOnWriteArrayList<>();
    private LayoutInflater inflater;
    Context context;
    int clickPosition;
    private LanguageSetAdapter.OnItemClickListener listener;//点击事件监听器

    public LanguageSetAdapter(List<Language> mList, Context context) {
        this.mList.clear();
        this.mList.addAll(mList);
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    public void resetData(List<Language> mList) {
        this.mList.clear();
        this.mList.addAll(mList);
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.language_item, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickPosition = position;
                if (listener != null) {
                    listener.onItemClick(v, position);
                    clickPosition = position;
                }
                notifyDataSetChanged();
            }
        });

        //返回含有数据的view

        holder.setData(mList.get(position), context);
        if (clickPosition == position)
            holder.radioLanguage.setImageResource(R.mipmap.check_ck);
        else
            holder.radioLanguage.setImageResource(R.mipmap.check_nor);
        return convertView;
    }

    class ViewHolder {
        @BindView(R.id.tv_language)
        TextView tvLanguage;
        @BindView(R.id.iv_language)
        ImageView radioLanguage;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }

        public void setData(Language languageBean, Context context) {
            tvLanguage.setText(languageBean.getMomo());
        }
    }

    /**
     * 定义一个点击事件接口回调
     */
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(LanguageSetAdapter.OnItemClickListener onItemClickListener) {
        this.listener = onItemClickListener;
    }
}
