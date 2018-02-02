package com.shinonometn.hacks.music.viewer.db.netease;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shinonometn.hacks.music.viewer.db.MusicRepo;
import com.shinonometn.hacks.music.viewer.db.netease.entities.playlist.NEPlaylist;
import com.shinonometn.hacks.music.viewer.db.netease.entities.track.NETrackInfo;
import com.shinonometn.hacks.music.viewer.db.netease.entities.users.NEUserInfo;
import com.shinonometn.hacks.music.viewer.info.Playlist;
import com.shinonometn.hacks.music.viewer.info.TrackInfo;
import com.shinonometn.hacks.music.viewer.info.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NeteaseMusicRepo implements MusicRepo{

    private final static Logger logger = LoggerFactory.getLogger(NeteaseMusicRepo.class);

    private final JdbcTemplate jdbcTemplate;

    private ObjectMapper objectMapper = new ObjectMapper();

    NeteaseMusicRepo(DriverManagerDataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<UserInfo> getUsers() {
        return this.jdbcTemplate.query(
                "SELECT uid as id, pids as playlistIds from web_user_playlist wup",
                new UserRowMapper()
        );
    }

    @Override
    public List<Playlist> getUserListIds(Integer userId) {
        UserInfo userInfo = jdbcTemplate.queryForObject(
                "SELECT uid as id, pids as playlistIds from web_user_playlist where uid = ?",
                new UserRowMapper(),
                userId);

        return userInfo != null ? userInfo.getPlayListIds()
                .stream()
                .map(this::getList)
                .collect(Collectors.toList()) : null;
    }

    @Override
    public List<Playlist> getAllList() {
        return jdbcTemplate.query(
                "select playlist from web_playlist",
                new PlaylistRowMapper()
        ).stream().map(i -> (Playlist)i).collect(Collectors.toList());
    }

    @Override
    public Playlist getList(Integer unifiedId) {
        return jdbcTemplate.queryForObject(
                "SELECT playlist from web_playlist WHERE pid = ?",
                new PlaylistRowMapper(),
                unifiedId
        );
    }

    @Override
    public List<TrackInfo> getListTracks(Playlist playlist) {
        return jdbcTemplate.query("SELECT\n" +
                        "  wpt.\"order\" as 'order',\n" +
                        "  wt.track as track\n" +
                        "  FROM web_playlist_track wpt\n" +
                        "    LEFT JOIN web_track wt on wt.tid = wpt.tid\n" +
                        "    LEFT JOIN web_playlist wp on wp.pid = wpt.pid\n" +
                        "  WHERE wpt.pid = ?\n" +
                        "  ORDER BY 'order'",
                new TrackRowMapper(),
                playlist.getUnifiedId()).stream().map(i -> (TrackInfo)i).collect(Collectors.toList());
    }

    /*
    *
    * private procedure
    *
    * */

    /*
    *
    * Row mappers
    *
    *
    * */

    class UserRowMapper implements RowMapper<UserInfo> {

        @Override
        public UserInfo mapRow(ResultSet resultSet, int i) throws SQLException {
            NEUserInfo userInfo = new NEUserInfo();
            userInfo.setId(resultSet.getInt("id"));
            userInfo.setPlaylistIds(Stream
                    .of(resultSet.getString("playlistIds").split(","))
                    .filter(input -> input.matches("\\d+"))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList()));

            return userInfo;
        }
    }

    class PlaylistRowMapper implements RowMapper<NEPlaylist> {

        @Override
        public NEPlaylist mapRow(ResultSet resultSet, int i) throws SQLException {
            try {
                return objectMapper.readValue(resultSet.getBinaryStream("playlist"), new TypeReference<NEPlaylist>() {
                });
            } catch (IOException e) {
                logger.error("Error accorded while mapping row to nePlaylist",e);
                return null;
            }
        }
    }

    class TrackRowMapper implements RowMapper<NETrackInfo> {

        @Override
        public NETrackInfo mapRow(ResultSet resultSet, int i) throws SQLException {
            try {
                return objectMapper.readValue(resultSet.getBinaryStream("track"), new TypeReference<NETrackInfo>(){});
            } catch (IOException e){
                logger.error("Error accorded while mapping row to neTrackInfo",e);
                return null;
            }
        }
    }
}
