package demo.xuqingqi.cardviewpager;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

/**
 * Created on 2016/8/29.
 */
public class CardAdapter extends PagerAdapter implements View.OnClickListener{

    private Context mContext;
    private LayoutInflater mInflater;
    private List<Section> mDataList;
    private LinkedList<View> mViewList;

    public CardAdapter(Context context) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
    }

    public void setData(List<Section> dataList) {
        this.mDataList = dataList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (this.mDataList == null) {
            return 0;
        }
        return this.mDataList.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View page = getView(position);
        container.addView(page);
        return page;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        if (object instanceof View) {
            View page = (View) object;
            container.removeView(page);
            this.mViewList.addLast(page);
        }
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    public View getView(final int position) {
        //reuse or create item view
        View view = null;
        if (this.mViewList == null) {
            this.mViewList = new LinkedList<>();
        }
        if (this.mViewList.size() > 0) {
            view = this.mViewList.poll();
        }
        if (view == null) {
            view = this.mInflater.inflate(R.layout.item_card, null);
        }

        //bind ViewHolder
        ViewHolder holder = null;
        Object tag = view.getTag();
        if (tag != null && tag instanceof ViewHolder) {
            holder = (ViewHolder) tag;
        }
        if (holder == null) {
            holder = new ViewHolder(view);
        }

        //bind data
        Section section = this.mDataList.get(position);
        holder.tvTitle.setText("第" + (position + 1) + "页");

        return holder.itemView;
    }

    @Override
    public void onClick(View v) {

    }

    public class ViewHolder {

        private View itemView;
        private TextView tvTitle;

        public ViewHolder(View view) {
            view.setTag(this);
            itemView = view;
            tvTitle = findView(itemView, R.id.tv_title);
        }

    }

    public<V> V findView(View view, int id) {
        return (V) view.findViewById(id);
    }

}
