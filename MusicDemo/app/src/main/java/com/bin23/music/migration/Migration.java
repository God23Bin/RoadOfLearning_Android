package com.bin23.music.migration;

import io.realm.DynamicRealm;
import io.realm.RealmMigration;
import io.realm.RealmSchema;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Migration implements RealmMigration {
   @Override
   public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {

       RealmSchema schema = realm.getSchema();
//        第一次迁移
       if (oldVersion == 0) {
//            schema.remove("UserModel");
//            schema.create("UserModel")
//                    .addField("phone", String.class)
//                    .addField("password", String.class)
//                    .addField("username", String.class)
//                    .addField("sign", String.class)
//                    .addField("sex", String.class)
//                    .addField("area", String.class)
//                    .addField("avatarUrl", String.class);

           schema.create("MusicModel")
                   .addField("musicId", String.class)
                   .addField("name", String.class)
                   .addField("poster", String.class)
                   .addField("path", String.class)
                   .addField("author", String.class);

           schema.create("AlbumModel")
                   .addField("albumId", String.class)
                   .addField("name", String.class)
                   .addField("poster", String.class)
                   .addField("playNum", String.class)
                   .addRealmListField("list", schema.get("MusicModel"));

           schema.create("MusicSourceModel")
                   .addRealmListField("album", schema.get("AlbumModel"))
                   .addRealmListField("hot", schema.get("MusicModel"));

           oldVersion = newVersion;
       }

   }
}
