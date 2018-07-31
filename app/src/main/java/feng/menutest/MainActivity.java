package feng.menutest;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    //一级菜单控件
    private ListView mFirstList;
    //一级菜单适配器
    private FirstAdapter mFirstAdapter;
    //一级菜单数据List
    private List<String> mFirstItemList = new ArrayList<String>();

    //二级菜单控件
    private ListView mSecondList;
    //二级菜单适配器
    private SecondAdapter mSecondAdapter;
    //二级菜单数据List
    private List<String> mSecondItemList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFirstList = (ListView) findViewById(R.id.FirstList);
        mSecondList = (ListView) findViewById(R.id.SecondList);

        mFirstAdapter = new FirstAdapter(this);
        mFirstList.setAdapter(mFirstAdapter);
        mFirstList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //选中一级菜单点击位置
                mFirstAdapter.setSelectedPosition(position);
                //将二级菜单上次的点击位置回置
                mSecondAdapter.setSelectedPosition(0);
                //刷新点击位置
                mFirstAdapter.notifyDataSetInvalidated();
                //获取一级菜单点击位置数据
                String ItemCode = mFirstItemList.get(position);
                //通过点击数据获取二级菜单数据
                getSecond(ItemCode);
            }
        });

        mSecondAdapter = new SecondAdapter(this);
        mSecondList.setAdapter(mSecondAdapter);
        mSecondList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mSecondAdapter.setSelectedPosition(position);
                mSecondAdapter.notifyDataSetInvalidated();
            }
        });

        //获取一级菜单数据
        getFirst();
    }

    private void getFirst() {
        for (int i = 0; i < 20; i++) {
            mFirstItemList.add(i + "");
        }
        mFirstAdapter.notifyDataSetChanged();
    }

    private void getSecond(String first) {
        mSecondItemList.clear();
        for (int i = 0; i < 20; i++) {
            mSecondItemList.add(first + " - " + i);
        }
        mSecondAdapter.notifyDataSetChanged();
    }

    public class FirstAdapter extends BaseAdapter {
        private LayoutInflater mInflater;

        //点击位置
        private int selectedPosition = -1;

        //设置点击位置给Adapter
        public void setSelectedPosition(int selectedPosition) {
            this.selectedPosition = selectedPosition;
        }

        public FirstAdapter(Context context) {
            this.mInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return mFirstItemList.size();
        }

        @Override
        public Object getItem(int arg0) {
            return null;
        }

        @Override
        public long getItemId(int arg0) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder = null;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = mInflater.inflate(R.layout.item_simple, null);
                viewHolder.date = (TextView) convertView.findViewById(R.id.text);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            //判断当前位置是否为点击位置
            if (selectedPosition == position) {
                //设置选中状态背景色
                viewHolder.date.setBackgroundColor(Color.parseColor("#8a8a8a"));
            } else {
                viewHolder.date.setBackgroundColor(Color.TRANSPARENT);
            }
            viewHolder.date.setText(mFirstItemList.get(position));
            return convertView;
        }
    }

    public class SecondAdapter extends BaseAdapter {
        private LayoutInflater mInflater;

        private int selectedPosition = -1;

        public void setSelectedPosition(int selectedPosition) {
            this.selectedPosition = selectedPosition;
        }

        public SecondAdapter(Context context) {
            this.mInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return mSecondItemList.size();
        }

        @Override
        public Object getItem(int arg0) {
            return null;
        }

        @Override
        public long getItemId(int arg0) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder = null;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = mInflater.inflate(R.layout.item_simple, null);
                viewHolder.date = (TextView) convertView.findViewById(R.id.text);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            if (selectedPosition == position) {
                viewHolder.date.setBackgroundColor(Color.parseColor("#8a8a8a"));
            } else {
                viewHolder.date.setBackgroundColor(Color.TRANSPARENT);
            }
            viewHolder.date.setText(mSecondItemList.get(position));
            return convertView;
        }
    }

    private static class ViewHolder {
        TextView date;
    }
}
