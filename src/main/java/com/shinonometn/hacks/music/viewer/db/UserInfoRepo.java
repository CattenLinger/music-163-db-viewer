package com.shinonometn.hacks.music.viewer.db;

import com.shinonometn.hacks.music.viewer.utils.SqliteDBHelper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UserInfoRepo extends BaseRepo{

    public UserInfoRepo(SqliteDBHelper dbHelper) {
        super(dbHelper);
    }

    public _UserInfo getUserInfo(Integer userId) throws SQLException {
        PreparedStatement preparedStatement = connection
                .prepareStatement("SELECT uid as id, pids as playlists FROM web_user_playlist WHERE id = ?");

        preparedStatement.setInt(1,userId);
        ResultSet resultSet = preparedStatement.executeQuery();

        try {
            if(resultSet.next()){
                _UserInfo userInfo = new _UserInfo();
                userInfo.id = resultSet.getInt("id");
                userInfo.playlistIds = Stream
                        .of(resultSet.getString("playlists").split(","))
                        .filter(i -> i.matches("\\d+"))
                        .map(Integer::parseInt)
                        .collect(Collectors.toList());
                return userInfo;
            }
        } finally {
            resultSet.close();
            preparedStatement.close();
        }

        return null;
    }

    public List<Integer> getUsers() throws SQLException {
        PreparedStatement preparedStatement = connection
                .prepareStatement("SELECT uid as id from web_user_playlist");

        ResultSet resultSet = preparedStatement.executeQuery();
        List<Integer> result = new ArrayList<>();

        try {
            while (resultSet.next()){
                result.add(resultSet.getInt("id"));
            }
        }finally {
            resultSet.close();
            preparedStatement.close();
        }

        return result;
    }

    public class _UserInfo {
        public Integer id;
        public List<Integer> playlistIds;
    }
}
