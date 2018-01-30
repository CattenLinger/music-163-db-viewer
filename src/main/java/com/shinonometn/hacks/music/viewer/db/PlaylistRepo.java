package com.shinonometn.hacks.music.viewer.db;

import com.shinonometn.hacks.music.viewer.utils.SqliteDBHelper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlaylistRepo extends BaseRepo{

    public PlaylistRepo(SqliteDBHelper dbHelper) {
        super(dbHelper);

    }

    public _Playlist getPlaylistById(Integer id) throws SQLException {
        PreparedStatement preparedStatement = connection
                .prepareStatement("SELECT pid as id, playlist as content FROM web_playlist where pid = ?");

        preparedStatement.setInt(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();
        try{
            if(resultSet.next()) {
                return new _Playlist(resultSet.getInt("id"), resultSet.getString("content"));
            }else {
                return null;
            }
        }finally {
            resultSet.close();
            preparedStatement.close();
        }
    }

    public List<_Playlist> getPlaylists() throws SQLException {
        PreparedStatement preparedStatement = connection
                .prepareStatement("SELECT pid as id, playlist as content FROM web_playlist");

        ResultSet resultSet = preparedStatement.executeQuery();

        try {
            ArrayList<_Playlist> webTracks = new ArrayList<>();
            while (resultSet.next()){
                webTracks.add(new _Playlist(resultSet.getInt("id"), resultSet.getString("content")));
            }

            return webTracks;
        } finally {
            resultSet.close();
            preparedStatement.close();
        }

    }

    public class _Playlist {
        public Integer playlistId;
        public String playlistContent;

        _Playlist(Integer playlistId, String playlistContent) {
            this.playlistId = playlistId;
            this.playlistContent = playlistContent;
        }
    }
}
