package com.start.bike.typehandler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.*;

@MappedTypes(Blob.class)
@MappedJdbcTypes(JdbcType.BLOB)
public class BlobToByteArrayTypeHandler extends BaseTypeHandler<Blob> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Blob parameter, JdbcType jdbcType) throws SQLException {
        ps.setBlob(i, parameter);
    }

    @Override
    public Blob getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return rs.getBlob(columnName);
    }

    @Override
    public Blob getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return rs.getBlob(columnIndex);
    }

    @Override
    public Blob getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return cs.getBlob(columnIndex);
    }
}