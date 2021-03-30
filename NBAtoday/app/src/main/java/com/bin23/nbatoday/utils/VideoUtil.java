package com.bin23.nbatoday.utils;

import com.bin23.nbatoday.entity.BilibiliVideoBean;

import java.util.List;

public class VideoUtil {
    /**
     * 因为ResultBean有类别，我们只需要类别为video的ResultBean，所以遍历下存放ResultBean这个集合
     * @return
     */

    public static BilibiliVideoBean.DataBean.ResultBean getResultBeanOfVideo(List<BilibiliVideoBean.DataBean.ResultBean> resultList) {
        BilibiliVideoBean.DataBean.ResultBean resultBean = null;
        for (int i = 0; i < resultList.size(); i++) {
            resultBean = resultList.get(i);
            if ("video".equals(resultBean.getResult_type())) {
                System.out.println("");
                break;
            }
        }
        return resultBean;
    }
}
