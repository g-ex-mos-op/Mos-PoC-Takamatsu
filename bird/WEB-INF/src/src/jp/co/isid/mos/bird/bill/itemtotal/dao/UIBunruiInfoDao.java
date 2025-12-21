/*
 * ì¬“ú: 2006/08/21
 *
 */
package jp.co.isid.mos.bird.bill.itemtotal.dao;

import java.util.List;

import jp.co.isid.mos.bird.bill.itemtotal.entity.UIBunruiInfo;

/**
 *@•ª—Şî•ñæ“¾DAO
 * 
 * @author xlee
 */
public interface UIBunruiInfoDao {

    public static final Class BEAN = UIBunruiInfo.class;

    /**
     * •ª—Şî•ñ‚ğŒŸõ‚µ‚Ü‚·B
     * 
     * @return •ª—Şî•ñ@List
     */
    public List getBunruiInfo();
}
