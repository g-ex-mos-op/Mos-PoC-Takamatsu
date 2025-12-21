package jp.co.isid.mos.bird.entry.mlviewlist.dao;

import java.util.List;

import jp.co.isid.mos.bird.entry.mlviewlist.entity.UIMlResultStatus;


/**
 * マスターライセンス一覧情報(UIMlResultStatusDao)
 * @author Aspac
 */
public interface UIMlResultStatusDao {

    public static final Class  BEAN = UIMlResultStatus.class;
    public static final String getMlResultStatus_ARGS = "totalLastYear, totalLastKai";
    public static final String updateEntity_PERSISTENT_PROPS = "examNo, lastUser, lastPgm, lastTmsp";

    /**
     * マスターライセンス結果状況履歴取得
     * @param entryYear
     * @param entryKai
     * @return List 検索結果
     */
    public List getMlResultStatus(String totalLastYear, String totalLastKai);

    /**
     * マスターライセンス結果状況履歴の更新
     * @param UIMlResultStatus
     */
    public int updateEntity(UIMlResultStatus entity);
}

