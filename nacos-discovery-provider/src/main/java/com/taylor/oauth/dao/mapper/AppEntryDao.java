package com.taylor.oauth.dao.mapper;

import com.taylor.oauth.dao.entity.AppEntry;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface AppEntryDao extends AppEntryMapper {

    @Select("SELECT ID AS ID ,APP_NAME AS appName, app_key as appKey, app_secret as appSecret, access_token as accessToken, disabled as disabled  from openapi_auth "
            + "where app_key=#{appKey} and app_secret=#{appSecret}  ")
    AppEntry findApp(AppEntry appEntry);

    @Select("SELECT ID AS ID ,APP_NAME AS appName, app_key as appKey, app_secret as appSecret, access_token as accessToken, disabled as disabled from openapi_auth "
            + "where app_key=#{appKey} and app_secret=#{appSecret}  ")
    AppEntry findAppId(@Param("appKey") String appKey);

    @Update(" update openapi_auth set access_token =#{accessToken} where app_key=#{appKey} ")
    int updateAccessToken(@Param("accessToken") String accessToken, @Param("appKey") String appId);
    
}
