package com.example.my_exam;

import android.content.Context;
import android.support.constraint.solver.widgets.analyzer.VerticalWidgetRun;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    ListView listView;
    Button buttonAdd, buttonDel;
    private ArrayList<Integer> selectedB = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.input_f);
        buttonAdd = findViewById(R.id.btn_add);
        buttonDel = findViewById(R.id.btn_del);

        if (editText.getText().toString().isEmpty()) {
            buttonAdd.setClickable(false);
        } else {
            buttonAdd.setClickable(true);
        }

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.name.add(editText.getText().toString());
            }
        });

//        buttonDel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//            }
//        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        listView = findViewById(R.id.ListView_Show);
        listView.setAdapter(new EfficientAdapter(getApplicationContext()));
    }

    public class EfficientAdapter extends BaseAdapter {
        Context mContext;
        LayoutInflater mLayoutInflater;

        EfficientAdapter(Context context) {
            mContext = context;
            mLayoutInflater = LayoutInflater.from(mContext);
        }


        @Override
        public int getCount() {
            return data.name.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View convertView, ViewGroup viewGroup) {
            final ViewHolder holder;
            if (convertView == null) {
                convertView = mLayoutInflater.inflate(R.layout.data, null);
                holder = new ViewHolder();
                holder.textView1 = convertView.findViewById(R.id.name);
                holder.cb_select = convertView.findViewById(R.id.textView03);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.textView1.setText(String.valueOf(data.name.get(i)));

            buttonDel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (int i = 0; i < data.name.size(); i++) {
                        if (holder.cb_select.isChecked()) {
                            data.name.remove(i);
                            onResume();
                        }
                    }
                }
            });

            return convertView;
        }
    }

    public class ViewHolder {
        TextView textView1;
        CheckBox cb_select;
    }

    private void deleteCheckedItems() {
        int count = this.listView.getAdapter().getCount();
        for (int i = 0; i < count; i++) {
            if (this.listView.isItemChecked(i)) {
                data.name.remove(i);
            }
        }
    }

}
