package shop.qwy.com.myshop.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import shop.qwy.com.myshop.bean.Wears;

/**
 * created by qwyAndroid on 2016/9/24
 */
public abstract class BaseAdapter<T,H extends BaseViewHolder> extends RecyclerView.Adapter<BaseViewHolder>{

    protected List<T> mDatas;
    protected int mResourceId;
    protected OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(View view,int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.mListener = listener;

    }
    public BaseAdapter(List<T> datas, int resourceId) {
        this.mDatas = datas;
        this.mResourceId = resourceId;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(mResourceId, parent, false);
        return new BaseViewHolder(view,mListener);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        T item = getItem(position);

        bindData((H)holder,item);
    }

    protected abstract void bindData(H holder, T item);

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public T getItem(int position){
        return mDatas.get(position);
    }

    public T getData(int position){
        return mDatas.get(position);
    }

    public List<T> getDatas(){
        return mDatas;
    }

    public void addData(List<T> data){
        addData(0,data);
    }
    public void clearData(){

        mDatas.clear();
        notifyItemRangeRemoved(0,mDatas.size());
    }
    public void addData(int i, List<T> data) {
        if (data != null && data.size()>0){
            mDatas.addAll(data);

            notifyItemChanged(i,mDatas.size());
        }
    }
}
