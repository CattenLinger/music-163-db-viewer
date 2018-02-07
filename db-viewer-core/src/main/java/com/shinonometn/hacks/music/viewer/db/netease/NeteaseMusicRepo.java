package com.shinonometn.hacks.music.viewer.db.netease;

import com.fasterxml.jackson.core.type.TypeReference;
import com.shinonometn.hacks.music.viewer.db.MusicRepo;
import com.shinonometn.hacks.music.viewer.db.netease.entities.playlist.NEPlayList;
import com.shinonometn.hacks.music.viewer.db.netease.entities.track.NETrackInfo;
import com.shinonometn.hacks.music.viewer.db.netease.entities.users.NEPlayerUser;
import com.shinonometn.hacks.music.viewer.info.PlayList;
import com.shinonometn.hacks.music.viewer.info.PlayerUser;
import com.shinonometn.hacks.music.viewer.info.TrackInfo;
import com.shinonometn.hacks.music.viewer.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NeteaseMusicRepo implements MusicRepo {

    private final static Logger logger = LoggerFactory.getLogger(NeteaseMusicRepo.class);

    private final JdbcTemplate jdbcTemplate;

    NeteaseMusicRepo(DriverManagerDataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<PlayerUser> getUsers() {
        return this.jdbcTemplate.query(
                "SELECT uid AS id, pids AS playlistIds FROM web_user_playlist wup",
                (resultSet, i) -> {
                    NEPlayerUser user = new NEPlayerUser();
                    user.setId(resultSet.getInt("id"));
                    user.setPlayListIds(Stream
                            .of(resultSet.getString("playlistIds").split(","))
                            .filter(input -> input.matches("\\d+"))
                            .map(Integer::parseInt)
                            .collect(Collectors.toList()));

                    return user;
                }
        );
    }

    @Override
    public List<PlayList> getLists(PlayerUser playerUser) {
        NEPlayerUser nePlayerUser = (NEPlayerUser) playerUser;

        if (nePlayerUser.getPlayListIds().size() <= 0) {
            return Collections.emptyList();
        } else {
            StringBuilder stringBuilder = new StringBuilder("(");
            for (Integer id : nePlayerUser.getPlayListIds()) {
                stringBuilder.append(id).append(",");
            }
            stringBuilder.deleteCharAt(stringBuilder.length() - 1).append(")");

            return jdbcTemplate.query(
                    "SELECT playlist FROM web_playlist WHERE pid in " + stringBuilder.toString(),
                    (resultSet, i) -> JsonUtils.read(resultSet.getBinaryStream("playlist"), new TypeReference<NEPlayList>() {
                    })
            );
        }
    }

    @Override
    public List<PlayList> getLists() {
        return jdbcTemplate.query(
                "SELECT playlist FROM web_playlist",
                (resultSet, i) -> JsonUtils.read(resultSet.getBinaryStream("playlist"), new TypeReference<NEPlayList>() {
                })
        );
    }

    @Override
    public List<TrackInfo> getTracks(PlayList playList) {
        NEPlayList nePlayList = (NEPlayList) playList;

        return jdbcTemplate.query("SELECT\n" +
                        "  wpt.\"order\" AS 'order',\n" +
                        "  wt.track AS track\n" +
                        "  FROM web_playlist_track wpt\n" +
                        "    LEFT JOIN web_track wt ON wt.tid = wpt.tid\n" +
                        "    LEFT JOIN web_playlist wp ON wp.pid = wpt.pid\n" +
                        "  WHERE wpt.pid = ?\n" +
                        "  ORDER BY 'order'",
                (resultSet, i) -> JsonUtils.read(resultSet.getBinaryStream("track"), new TypeReference<NETrackInfo>() {
                }),
                nePlayList.getId());
    }

    @Override
    public String getProviderName() {
        return "Netease Music Database Reader (By shinonometn)";
    }

    /*
    *
    * private procedure
    *
    * */

}
