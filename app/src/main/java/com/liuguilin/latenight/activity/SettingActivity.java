package com.liuguilin.latenight.activity;

/*
 *  项目名：  lateNight
 *  包名：    com.liuguilin.latenight.activity
 *  文件名:   SettingActivity
 *  创建者:   LGL
 *  创建时间:  2016/10/20 12:24
 *  描述：    设置
 */

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.liuguilin.gankclient.R;
import com.liuguilin.latenight.entity.Constants;
import com.liuguilin.latenight.util.DataCleanManager;
import com.liuguilin.latenight.view.CustomDialog;
import com.sdsmdg.tastytoast.TastyToast;

public class SettingActivity extends BaseActivity implements View.OnClickListener {

    //版本号
    private TextView tv_version;
    //版本更新
    private LinearLayout ll_update_version;
    //关于
    private LinearLayout ll_about_app;
    //清理缓存
    private LinearLayout ll_clear_data;
    //缓存大小
    private TextView tv_data;
    //提示框
    private CustomDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        initView();
    }

    //初始化View
    private void initView() {

        //初始化提示框
        dialog = new CustomDialog(this, 0, 0, R.layout.dialog_loding, R.style.Theme_dialog, Gravity.CENTER, R.style.pop_anim_style);
        TextView tv_login = (TextView) dialog.findViewById(R.id.tv_loding_text);
        tv_login.setText("检查新版本...");
        //屏幕外点击无效
        dialog.setCancelable(false);

        tv_version = (TextView) findViewById(R.id.tv_version);
        tv_version.setText(Constants.getVersion(this));
        ll_update_version = (LinearLayout) findViewById(R.id.ll_update_version);
        ll_update_version.setOnClickListener(this);
        ll_about_app = (LinearLayout) findViewById(R.id.ll_about_app);
        ll_about_app.setOnClickListener(this);
        ll_clear_data = (LinearLayout) findViewById(R.id.ll_clear_data);
        ll_clear_data.setOnClickListener(this);
        tv_data = (TextView) findViewById(R.id.tv_data);
        try {
            //获取缓存大小
            tv_data.setText(DataCleanManager.getTotalCacheSize(this));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_update_version:
                dialog.show();
//                RxVolley.get(Constants.UPDATE_APP_URL, new HttpCallback() {
//                    @Override
//                    public void onSuccess(String t) {
//                        dialog.dismiss();
//                        //在这里判断是否有新版本，有的话提示更新，没有的话就GG
//                    }
//                });
                break;
            case R.id.ll_about_app:
                startActivity(new Intent(this, AboutActivity.class));
                break;
            case R.id.ll_clear_data:
                DataCleanManager.clearAllCache(this);
                TastyToast.makeText(this, "清理成功", TastyToast.LENGTH_LONG, TastyToast.SUCCESS);
                break;
        }
    }
}
