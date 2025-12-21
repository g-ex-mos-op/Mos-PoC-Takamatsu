package jp.co.isid.mos.bird.entry.projectplanstatusinfo.dao;

import java.util.List;

import jp.co.isid.mos.bird.entry.projectplanstatusinfo.entity.UISibuList;


/**
 * x•”î•ñ
 * @author xamaruyama
 */
public interface UISibuListDao {

    public static final Class BEAN = UISibuList.class;

    public static final String getSibuList_ARGS = "limit, companyCd, userId";

    public List getSibuList(boolean limit, String companyCd, String userId);
    
}