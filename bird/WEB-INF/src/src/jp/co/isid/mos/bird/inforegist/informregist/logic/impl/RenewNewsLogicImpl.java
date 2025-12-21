/*
 * 作成日: 2006/2/9
 */
package jp.co.isid.mos.bird.inforegist.informregist.logic.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import jp.co.isid.mos.bird.common.dto.PublicTargetDto;
import jp.co.isid.mos.bird.common.logic.RegKanrenBunshoLogic;
import jp.co.isid.mos.bird.common.logic.RegKoukaiTaishoLogic;
import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.framework.util.formatter.CodeFormatter;
import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;
import jp.co.isid.mos.bird.inforegist.informregist.dao.UINewsDao;
import jp.co.isid.mos.bird.inforegist.informregist.dto.InformRegistDto;
import jp.co.isid.mos.bird.inforegist.informregist.entity.UINews;
import jp.co.isid.mos.bird.inforegist.informregist.logic.RenewNewsLogic;

/**
 * お知らせ情報更新処理ロジック
 * 
 * 2006/04/03 排他制御対応
 * 
 * @author itamoto
 */
public class RenewNewsLogicImpl implements RenewNewsLogic {

    /* ロジックID */
    public static final String LOGIC_ID = "BIF004L03";

    /* UINewsDao */
    private UINewsDao uiNewsDao;

    // LOGIC
    private RegKoukaiTaishoLogic regKoukaiTaishoLogic;

    private RegKanrenBunshoLogic regKanrenBunshoLogic;

    /**
     * 登録内容のチェック取得Daoを取得します。
     * 
     * @return uiNewsDao を戻します。
     */
    public UINewsDao getUiNewsDao() {
        return uiNewsDao;
    }

    /**
     * 登録内容のチェック取得Daoを設定します。
     * 
     * @param uiNewsDao
     *            uiNewsDao を設定。
     */
    public void setUiNewsDao(UINewsDao uiNewsDao) {
        this.uiNewsDao = uiNewsDao;
    }

    /**
     * 公開対象登録ロジックの設定
     * 
     * @return regKoukaiTaishoLogic を戻します。
     */
    public RegKoukaiTaishoLogic getRegKoukaiTaishoLogic() {
        return regKoukaiTaishoLogic;
    }

    /**
     * 公開対象登録ロジックの設定
     * 
     * @param regKoukaiTaishoLogic
     *            regKoukaiTaishoLogic を設定。
     */
    public void setRegKoukaiTaishoLogic(
            RegKoukaiTaishoLogic regKoukaiTaishoLogic) {
        this.regKoukaiTaishoLogic = regKoukaiTaishoLogic;
    }

    /**
     * 関連文書登録ロジックを取得します。
     * 
     * @return 関連文書登録ロジック
     */
    public RegKanrenBunshoLogic getRegKanrenBunshoLogic() {
        return regKanrenBunshoLogic;
    }

    /**
     * 関連文書登録ロジックを設定します。
     * 
     * @param 関連文書登録ロジック
     */
    public void setRegKanrenBunshoLogic(
            RegKanrenBunshoLogic regKanrenBunshoLogic) {
        this.regKanrenBunshoLogic = regKanrenBunshoLogic;
    }

    /**
     * お知らせ情報更新処理
     * 
     * @param uiNews
     */
    public List execute(UINews uiNews, InformRegistDto informRegistDto,
            PublicTargetDto publicTargetDto) {
        // お知らせ情報設定
        uiNews.setSakujoFlg((informRegistDto.getMode() == 3) ? "1" : "0");
        uiNews.setLastTmsp(DateManager.getCurrentTimestamp());
        uiNews.setLastPgm(informRegistDto.VIEW_ID);
        // 発信日フォーマット
        DateFormatter dateFormatter = new DateFormatter(
                DateFormatter.DATE_TYPE_YMD, DateFormatter.PATTERN_SLASH);
        uiNews.setPubDateFrom(dateFormatter.format(uiNews.getPubDateFrom(),
                DateFormatter.PATTERN_NORMAL, DateFormatter.DATE_TYPE_YMD));

        // 新規
        if (informRegistDto.getMode() == 1) {
            // 現在のMAX SEQ番号
            int maxSeq = uiNewsDao.getNumber(uiNews.getRegDate()) + 1;
            // SEQをセットしてから登録
            CodeFormatter formatter = new CodeFormatter(4, "0000");
            formatter.setFormatPattern("0000");
            uiNews.setSeq(formatter.format(String.valueOf(maxSeq), false));
            uiNews.setFirstTmsp(DateManager.getCurrentTimestamp());
            uiNews.setFirstPgm(informRegistDto.VIEW_ID);
            uiNewsDao.insertNews(uiNews);

            // 公開対象情報登録
            publicTargetDto.setInfoShu(informRegistDto.INFO_SHU);
            publicTargetDto.setSeq(uiNews.getSeq());
            publicTargetDto.setRegDate(uiNews.getRegDate());
            getRegKoukaiTaishoLogic().execute(publicTargetDto,
                    uiNews.getLastUser(), uiNews.getLastPgm());

            // 関連文書登録
            regKanrenBunshoLogic.execute(informRegistDto.INFO_SHU, uiNews
                    .getRegDate(), uiNews.getSeq(), informRegistDto
                    .getListKanrenBunsho(), uiNews.getLastUser(), uiNews
                    .getLastPgm());

        }
        // 更新
        if (informRegistDto.getMode() == 2 ) {
            Timestamp beforeUpdate = uiNews.getLastTmsp();
            uiNewsDao.updateNews(uiNews);
            uiNews.setLastTmsp(beforeUpdate);
            
            // 公開対象情報登録
            publicTargetDto.setInfoShu(informRegistDto.INFO_SHU);
            publicTargetDto.setSeq(uiNews.getSeq());
            publicTargetDto.setRegDate(uiNews.getRegDate());
            getRegKoukaiTaishoLogic().execute(publicTargetDto,
                    uiNews.getLastUser(), uiNews.getLastPgm());

            // 関連文書登録
            regKanrenBunshoLogic.execute(informRegistDto.INFO_SHU, uiNews
                    .getRegDate(), uiNews.getSeq(), informRegistDto
                    .getListKanrenBunsho(), uiNews.getLastUser(), uiNews
                    .getLastPgm());

        }
        // 削除
        if (informRegistDto.getMode() == 3) {
            Timestamp beforeUpdate = uiNews.getLastTmsp();
            uiNewsDao.updateNews(uiNews);
            uiNews.setLastTmsp(beforeUpdate);
            
            // 公開対象情報削除
            publicTargetDto.setInfoShu(informRegistDto.INFO_SHU);
            publicTargetDto.setSeq(uiNews.getSeq());
            publicTargetDto.setRegDate(uiNews.getRegDate());
            publicTargetDto.setListTrnControlCompany(new ArrayList());
            publicTargetDto.setListTrnControlGyotai(new ArrayList());
            publicTargetDto.setListTrnControlGyotaiKobetu(new ArrayList());
            publicTargetDto.setListTrnControlGyotaiTenpo(new ArrayList());
            publicTargetDto.setListTrnControlShozoku(new ArrayList());
            getRegKoukaiTaishoLogic().execute(publicTargetDto,
                    uiNews.getLastUser(), uiNews.getLastPgm());

            // 関連文書削除
            informRegistDto.setListKanrenBunsho(new ArrayList());
            regKanrenBunshoLogic.execute(informRegistDto.INFO_SHU, uiNews
                    .getRegDate(), uiNews.getSeq(), informRegistDto
                    .getListKanrenBunsho(), uiNews.getLastUser(), uiNews
                    .getLastPgm());
        }

        return null;
    }
}
