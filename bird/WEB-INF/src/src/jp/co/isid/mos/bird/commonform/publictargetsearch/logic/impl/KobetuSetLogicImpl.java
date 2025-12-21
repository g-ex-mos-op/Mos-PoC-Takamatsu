/*
 * 作成日: 2006/01/17
 *
 */
package jp.co.isid.mos.bird.commonform.publictargetsearch.logic.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.commonform.publictargetsearch.dao.MstCompanySibuTorikomiDao;
import jp.co.isid.mos.bird.commonform.publictargetsearch.dto.PublicTargetSearchConditionDto;
import jp.co.isid.mos.bird.commonform.publictargetsearch.entity.MstCompanySibuTorikomi;
import jp.co.isid.mos.bird.commonform.publictargetsearch.entity.MstGyotiUneiCompany;
import jp.co.isid.mos.bird.commonform.publictargetsearch.logic.KobetuSetLogic;

/**
 * 個別設定情報を取得ロジック
 * @author xytamura
 */
public class KobetuSetLogicImpl implements KobetuSetLogic {

    public static final String LOGIC_ID = "BCO002L04";
    /**
     * 支部取込情報Dao
     */
    private MstCompanySibuTorikomiDao mstCompanySibuTorikomiDao;

    /**
     * 個別設定情報を取得します。
     */
    public PublicTargetSearchConditionDto execute(
            PublicTargetSearchConditionDto publicTargetSearchCondionDto) {
        //        String comanyCd =
        // publicTargetSearchCondionDto.getSelectedCompanyCd();
        String gyotaiKbn = publicTargetSearchCondionDto.getSelectedGyotiKbn();

        List sibutorikomi = mstCompanySibuTorikomiDao
                .getSibuTorikomi(gyotaiKbn);

        //既存データの反映
        sibutorikomi = reflectionSelectData(sibutorikomi,
                publicTargetSearchCondionDto);

        publicTargetSearchCondionDto
                .setListMstCompanySibuTorikomi(sibutorikomi);

        return publicTargetSearchCondionDto;

    }

    /**
     * 支部取込情報Daoを設定します。
     * @param codCompanySibuTorikomiDao codCompanySibuTorikomiDao を設定。
     */
    public void setMstCompanySibuTorikomiDao(
            MstCompanySibuTorikomiDao mstCompanySibuTorikomiDao) {
        this.mstCompanySibuTorikomiDao = mstCompanySibuTorikomiDao;
    }

    /**
     * 既存データの反映
     * @param sibutorikomi 検索結果
     * @param publicTargetSearchCondionDto 現在の設定情報
     * @return データの反映後の設定情報
     */
    private List reflectionSelectData(List sibutorikomi,
            PublicTargetSearchConditionDto publicTargetSearchCondionDto) {
        int selectIndex = publicTargetSearchCondionDto.getSelectedIndex();

        MstGyotiUneiCompany entity = (MstGyotiUneiCompany) publicTargetSearchCondionDto
                .getListMstGyotiUneiCompany().get(selectIndex);
        List kizonSet = entity.getKobetuSetSibu();
        if (kizonSet == null) {
            return sibutorikomi;
        }
        //情報取得用マップを作成
        Map kizonInfo = new HashMap();
        for (int i = 0; i < kizonSet.size(); i++) {
            MstCompanySibuTorikomi mstCompanySibuTorikomi = (MstCompanySibuTorikomi) kizonSet
                    .get(i);
            kizonInfo.put(mstCompanySibuTorikomi.getGyotaiKbn()
                    + mstCompanySibuTorikomi.getSibuCd(),
                    mstCompanySibuTorikomi);

        }
        
        for (int j = 0; j < sibutorikomi.size(); j++) {
            MstCompanySibuTorikomi selectResult = (MstCompanySibuTorikomi) sibutorikomi
                        .get(j);
            String key = selectResult.getGyotaiKbn()+selectResult.getSibuCd();
            //支部コード、業態区分が同一
            if (kizonInfo.containsKey(key)){
                //チェックがＯＮの場合
                if (((MstCompanySibuTorikomi)kizonInfo.get(key)).getSentakuFlg()) {
                    selectResult.setSentakuFlg(true);
                }

            }    
        }
        return sibutorikomi;

    }

}