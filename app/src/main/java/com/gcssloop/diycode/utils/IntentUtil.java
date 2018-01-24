package com.gcssloop.diycode.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.customtabs.CustomTabsIntent;
import android.widget.Toast;

import com.gcssloop.diycode.utils.customtabs.CustomTabsHelper;

/**
 * Created by zhangyuanyuan on 2017/12/13.
 */

public class IntentUtil {

    public static void openUrl(Context context, String url) {
        if (null == url || url.isEmpty()) {
            return;
        }
        CustomTabsHelper.openUrl(context, url);
    }

    public static void openAlipay(Context context) {
        if (AppUtil.isAvailable(context, "com.eg.android.AlipayGphone")) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            String QRCode = "HTTPS://QR.ALIPAY.COM/FKX07101FYSJGTNCAPQW39";
            intent.setData(Uri.parse("alipayqr://platformapi/startapp?saId=10000007&qrcode=" + QRCode));
            context.startActivity(intent);

        } else {
            Toast.makeText(context, "你没有捐赠的权限", Toast.LENGTH_SHORT).show();
        }
    }
}
