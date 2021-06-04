package com.bin23.music.model;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * json数据中，最外层的对象
 */
public class MusicSourceModel extends RealmObject {

   private RealmList<AlbumModel> album;
   private RealmList<MusicModel> hot;

   public RealmList<AlbumModel> getAlbum() {
       return album;
   }

   public void setAlbum(RealmList<AlbumModel> album) {
       this.album = album;
   }

   public RealmList<MusicModel> getHot() {
       return hot;
   }

   public void setHot(RealmList<MusicModel> hot) {
       this.hot = hot;
   }
}
