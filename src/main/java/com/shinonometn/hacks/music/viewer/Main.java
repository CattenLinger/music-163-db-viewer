package com.shinonometn.hacks.music.viewer;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shinonometn.hacks.music.viewer.db.PlaylistRepo;
import com.shinonometn.hacks.music.viewer.db.UserInfoRepo;
import com.shinonometn.hacks.music.viewer.utils.SqliteDBHelper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by cattenlinger on 2018/1/30.
 */
public class Main {
    public static void main(String[] args) throws SQLException {
        SqliteDBHelper dbHelper = SqliteDBHelper.build("db/sqlite_storage.sqlite3");
        if(dbHelper != null){
            Set<String> tables = dbHelper.getTableList();
            System.out.println("Tables in db :");
            tables.forEach(System.out::println);
        }
        System.out.println();

        ObjectMapper objectMapper = new ObjectMapper();

        UserInfoRepo userInfoRepo = new UserInfoRepo(dbHelper);
        PlaylistRepo playlistRepo = new PlaylistRepo(dbHelper);

        userInfoRepo.getUsers().forEach(userId -> {
            System.out.printf("User %010d playlists : \n\n", userId);

            try{
                UserInfoRepo._UserInfo userInfo = userInfoRepo.getUserInfo(userId);
                userInfo.playlistIds.forEach(playlistId -> {

                    try {
                        PlaylistRepo._Playlist playlist = playlistRepo.getPlaylistById(playlistId);

                        Map<String,Object> objectMap = objectMapper.readValue(playlist.playlistContent, new TypeReference<Map<String,Object>>() {});
                        System.out.printf("%010d\t%s\n",playlist.playlistId,objectMap.get("name"));

                    } catch (Exception e){
                        System.out.println("\n------------ ERROR -------------\n");
                    }

                });
            }catch (Exception e){
                System.out.println("\n------------ ERROR -------------\n");
            }

            System.out.println();

        });
    }
}
