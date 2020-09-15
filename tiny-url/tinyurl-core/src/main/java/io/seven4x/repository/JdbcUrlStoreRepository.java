package io.seven4x.repository;

import io.seven4x.service.UrlInfo;
import io.seven4x.service.UrlStore;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcUrlStoreRepository implements UrlStore {

    JdbcTemplate jdbcTemplate;

    String INSERT_SQL = "insert into tb_url_mapping(origin_url,tiny_url) values(?,?)";
    String QUERY_SQL = "select origin_url from  tb_url_mapping where tiny_url = ?";

    public JdbcUrlStoreRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int storeMapping(String longUrl, String shortUrl) {
        return jdbcTemplate.update(INSERT_SQL, longUrl, shortUrl);
    }

    @Override
    public UrlInfo getUrlInfoByShortUrl(String shortUrl) {
        // fixme need test
        String s = jdbcTemplate.queryForObject(QUERY_SQL, (RowMapper<String>) (rs, rowNum) -> rs.getString(1), shortUrl);
        return new UrlInfoData(s);
    }
}
