package jp.co.isid.mos.bird.storeinfo.miseref.dao;

import java.util.List;

import jp.co.isid.mos.bird.storeinfo.miseref.entity.MstTODlJyoho;

public interface MstTODlJyohoDao {

	public static final Class BEAN = MstTODlJyoho.class;
    public static final String selectTODlJyoho_ARGS = "condCompanyCd, condMiseCd";

    public List selectTODlJyoho(String condCompanyCd, String condMiseCd);
}
