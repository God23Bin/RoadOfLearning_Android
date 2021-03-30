//package com.bin23.nbatoday.utils;
//
//import android.annotation.SuppressLint;
//import android.content.Context;
//import android.graphics.drawable.Drawable;
//
//import com.bin23.nbatoday.R;
//import com.bin23.nbatoday.entity.NewsBean;
//
//import java.util.ArrayList;
//
//public class NewsUtil {
//
//    @SuppressLint("UseCompatLoadingForDrawables")
//    public static ArrayList<NewsBean> getAllNews(){
//        ArrayList<NewsBean> arrayList = new ArrayList<>();
//        for (int i = 0; i < 5; i++){
//            NewsBean newsBean1 = new NewsBean();
//            newsBean1.setNewsTitle("汤普森国籍由加拿大改为美国，这个国家一共有多少NBA球员");
//            newsBean1.setNewsDes("今天体育圈关于特里斯坦-汤普森改国籍为美国的消息刷了屏，小编这才想起来他原来是一个加拿大人，之前一直以为他是美国球员，这下才真正成为美国球员。那么，现役在NBA打球的球员中有多少加拿大籍的球员呢？");
//            newsBean1.setNewsPic(R.drawable.news0);
//            newsBean1.setNewsUrl("https://new.qq.com/rain/a/20201129A0BKPB00");
//
//            NewsBean newsBean2 = new NewsBean();
//            newsBean2.setNewsTitle("新赛季湖人战力排名西部第1，火箭名列第7，那么快船勇士呢？");
//            newsBean2.setNewsDes("NBA联盟官网在最近统计排列东西赛区的球队战力名次，其中在整体实力更加出色的西部赛区这一边，作为19-20赛季的NBA联盟总冠军球队，湖人队也是当仁不让稳居战力榜的头把交椅。在目前的NBA休赛季之中，湖人队管理层也是围绕詹姆斯、浓眉哥两大超巨继续补强，虽然湖人队最终也是失去隆多、霍华德、布拉德利等球员，但在自由球员的签约引援方面，湖人队管理层则是成绩斐然，最终顺利完成小加索尔、马修斯、施罗德、哈雷尔等人的签约引进。");
//            newsBean2.setNewsPic(R.drawable.news1);
//            newsBean2.setNewsUrl("https://new.qq.com/rain/a/20201129A0CDYN00");
//
//            NewsBean newsBean3 = new NewsBean();
//            newsBean3.setNewsTitle("欧文再次取关詹姆斯");
//            newsBean3.setNewsDes("NBA的训练营即将开启，不管是杜兰特和欧文的联手的篮网，还是阵容补强的卫冕冠军湖人，都让大家非常期待。然而有细心的网友发现，篮网球星欧文的关注列表里，詹姆斯已经不在了。");
//            newsBean3.setNewsPic(R.drawable.news2);
//            newsBean3.setNewsUrl("https://new.qq.com/omn/20201201/20201201A0CXIY00.html");
//
//            NewsBean newsBean4 = new NewsBean();
//            newsBean4.setNewsTitle("湖人坏消息，新援不想替补，公开挑衅詹姆斯，这新人疯了吧");
//            newsBean4.setNewsDes("湖人的新人施罗德加入湖人后，今天在接受媒体采访时表示，希望自己能在湖人担任首发。");
//            newsBean4.setNewsPic(R.drawable.news3);
//            newsBean4.setNewsUrl("https://new.qq.com/omn/20201201/20201201A0CEX000.html");
//
//            arrayList.add(newsBean1);
//            arrayList.add(newsBean2);
//            arrayList.add(newsBean3);
//            arrayList.add(newsBean4);
//        }
//        return arrayList;
//    }
//}
