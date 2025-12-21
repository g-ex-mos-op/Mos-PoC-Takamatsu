/*
 * ì¬“ú: 2006/12/27
 */
package jp.co.isid.mos.bird.entry.longserviceviewlist.dao;

import java.util.List;

import jp.co.isid.mos.bird.entry.longserviceviewlist.entity.UILSViewListSibu;


/**
 * x•”î•ñæ“¾
 * @author xamaruyama
 */
public interface UILSViewListSibuDao {

    public static final Class BEAN = UILSViewListSibu.class;

    public static final String getSibu_ARGS = "limit, companyCd, userId";

    public List getSibu(boolean limit, String companyCd, String userId);
    
}