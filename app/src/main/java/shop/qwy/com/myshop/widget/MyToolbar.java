package shop.qwy.com.myshop.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.widget.TintTypedArray;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import shop.qwy.com.myshop.R;

/**
 * 作者：仇伟阳
 */
public class MyToolbar extends Toolbar{

    private LayoutInflater mInflate;
    private View mView;
    private TextView mTextTitle;
    private EditText mSearchView;
    private Button mRightButton;

    public MyToolbar(Context context) {
        this(context,null);
    }

    public MyToolbar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyToolbar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initView();
        setContentInsetsRelative(10,10);
        if (attrs != null) {


            final TintTypedArray a = TintTypedArray.obtainStyledAttributes(getContext(), attrs,
                    R.styleable.MyToolbar, defStyleAttr, 0);
            final Drawable rightIcon = a.getDrawable(R.styleable.MyToolbar_rightButtonIcon);
            if (rightIcon != null) {
                setRightIcon(rightIcon);
            }

            boolean isShowSearchView = a.getBoolean(R.styleable.MyToolbar_isShowSearchView, false);
            if(isShowSearchView){
                showSearchView();
                hideTitleView();
            }

            a.recycle();
        }
    }
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void setRightIcon(Drawable rightIcon) {
        if(mRightButton !=null){

            mRightButton.setBackground(rightIcon);
            mRightButton.setVisibility(VISIBLE);
        }
    }

    public  void setRightButtonOnClickListener(OnClickListener li){

        mRightButton.setOnClickListener(li);
    }

    public void setSearchViewOnClickListener(OnClickListener li){
        mSearchView.setOnClickListener(li);
    }
    private void initView() {
        if (mView == null) {
            mInflate = LayoutInflater.from(getContext());
            mView = mInflate.inflate(R.layout.toolbar, null);

            mTextTitle = (TextView) mView.findViewById(R.id.toolbar_title);
            mSearchView = (EditText) mView.findViewById(R.id.toolbar_searchview);
            mRightButton = (Button) mView.findViewById(R.id.toolbar_rightButton);
            LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT,
                    Gravity.CENTER_HORIZONTAL);
            addView(mView, layoutParams);

        }
    }
    @Override
    public void setTitle(@StringRes int resId) {
        setTitle(getContext().getText(resId));
    }

    @Override
    public void setTitle(CharSequence title) {
        initView();
        if(!TextUtils.isEmpty(title)){
            mTextTitle.setText(title);
            showTitleView();
        };
    }

    public  void showSearchView(){

        if(mSearchView !=null)
            mSearchView.setVisibility(VISIBLE);

    }


    public void hideSearchView(){
        if(mSearchView !=null)
            mSearchView.setVisibility(GONE);
    }

    public void showTitleView(){
        if(mTextTitle !=null)
            mTextTitle.setVisibility(VISIBLE);
    }


    public void hideTitleView() {
        if (mTextTitle != null)
            mTextTitle.setVisibility(GONE);

    }

    public void setRightButtonText(CharSequence text){
        mRightButton.setText(text);
        mRightButton.setVisibility(VISIBLE);
    }

    public void setRightButtonText(int id){
        setRightButtonText(getResources().getString(id));
    }



    public Button getRightButton(){

        return this.mRightButton;
    }
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void setRightButtonIcon(int rightIcon) {
        setRightIcon(getResources().getDrawable(rightIcon));
    }
}
