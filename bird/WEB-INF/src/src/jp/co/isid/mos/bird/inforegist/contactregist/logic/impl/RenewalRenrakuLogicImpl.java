/*
 * 作成日: 2006/01/25
 *
 */
package jp.co.isid.mos.bird.inforegist.contactregist.logic.impl;

import java.sql.Timestamp;
import java.util.ArrayList;

import jp.co.isid.mos.bird.common.dto.PublicTargetDto;
import jp.co.isid.mos.bird.common.logic.RegKanrenBunshoLogic;
import jp.co.isid.mos.bird.common.logic.RegKoukaiTaishoLogic;
import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.framework.util.formatter.Formatter;
import jp.co.isid.mos.bird.inforegist.contactregist.dao.UIRenrakuInfoDao;
import jp.co.isid.mos.bird.inforegist.contactregist.dto.ContactRegistDto;
import jp.co.isid.mos.bird.inforegist.contactregist.logic.RenewalRenrakuLogic;

/**
 * 連絡情報の更新ロジック
 * 
 * @author xyuchida 2006/04/03 排他制御追加
 */
public class RenewalRenrakuLogicImpl implements RenewalRenrakuLogic {

    public static final String LOGIC_ID = "BIF001L03";

    private UIRenrakuInfoDao uIRenrakuInfoDao;

    // 公開対象の登録ロジック
    private RegKoukaiTaishoLogic regKoukaiTaishoLogic;

    // 関連文書の登録ロジック
    private RegKanrenBunshoLogic regKanrenBunshoLogic;

    private Formatter formatter;

    // 情報種別
    private static final String INFO_SHU = "01";

    /**
     * @return uIRenrakuInfoDao を戻します。
     */
    public UIRenrakuInfoDao getUIRenrakuInfoDao() {
        return uIRenrakuInfoDao;
    }

    /**
     * @param renrakuInfoDao
     *            uIRenrakuInfoDao を設定。
     */
    public void setUIRenrakuInfoDao(UIRenrakuInfoDao renrakuInfoDao) {
        uIRenrakuInfoDao = renrakuInfoDao;
    }

    /**
     * 公開対象の登録ロジックを取得します。
     * 
     * @return 公開対象の登録ロジック
     */
    public RegKoukaiTaishoLogic getRegKoukaiTaishoLogic() {
        return regKoukaiTaishoLogic;
    }

    /**
     * 公開対象の登録ロジックを設定します。
     * 
     * @param 公開対象の登録ロジック
     */
    public void setRegKoukaiTaishoLogic(
            RegKoukaiTaishoLogic regKoukaiTaishoLogic) {
        this.regKoukaiTaishoLogic = regKoukaiTaishoLogic;
    }

    /**
     * 関連文書の登録ロジックを取得します。
     * 
     * @return 関連文書の登録ロジック
     */
    public RegKanrenBunshoLogic getRegKanrenBunshoLogic() {
        return regKanrenBunshoLogic;
    }

    /**
     * 関連文書の登録ロジックを設定します。
     * 
     * @param 関連文書の登録ロジック
     */
    public void setRegKanrenBunshoLogic(
            RegKanrenBunshoLogic regKanrenBunshoLogic) {
        this.regKanrenBunshoLogic = regKanrenBunshoLogic;
    }

    /**
     * @return formatter を戻します。
     */
    public Formatter getFormatter() {
        return formatter;
    }

    /**
     * @param formatter
     *            formatter を設定。
     */
    public void setFormatter(Formatter formatter) {
        this.formatter = formatter;
    }

    /**
     * 連絡情報の更新
     */
    public void execute(ContactRegistDto contactRegistDto,
            PublicTargetDto publicTargetDto) {

        // 編集モード判定
        if (contactRegistDto.getEditMode() == ContactRegistDto.EDIT_MODE_REGIST) {

            // 登録

            // シーケンス番号設定
            String seq = formatter.format(String.valueOf(uIRenrakuInfoDao
                    .getNumber(contactRegistDto.getRegDate()) + 1), false);
            contactRegistDto.setSeq(seq);
            publicTargetDto.setSeq(seq);

            // 登録、更新日付設定
            Timestamp currentTimestamp = DateManager.getCurrentTimestamp();
            contactRegistDto.setFirstTmsp(currentTimestamp);
            contactRegistDto.setLastTmsp(currentTimestamp);

            // 連絡情報登録
            uIRenrakuInfoDao.insertRenraku(contactRegistDto);

            // 公開対象情報登録
            regKoukaiTaishoLogic.execute(publicTargetDto, contactRegistDto
                    .getLastUser(), contactRegistDto.getLastPgm());
            // 関連文書登録
            regKanrenBunshoLogic.execute(INFO_SHU, contactRegistDto
                    .getRegDate(), contactRegistDto.getSeq(), contactRegistDto
                    .getListKanrenBunsho(), contactRegistDto.getLastUser(),
                    contactRegistDto.getLastPgm());

        } else if (contactRegistDto.getEditMode() == ContactRegistDto.EDIT_MODE_UPDATE) {
            Timestamp beforeUpDate = contactRegistDto.getLastTmsp();
            // 更新
            uIRenrakuInfoDao.updateRenraku(contactRegistDto);
            // 公開対象情報登録
            regKoukaiTaishoLogic.execute(publicTargetDto, contactRegistDto
                    .getLastUser(), contactRegistDto.getLastPgm());
            // 関連文書登録
            regKanrenBunshoLogic.execute(INFO_SHU, contactRegistDto
                    .getRegDate(), contactRegistDto.getSeq(), contactRegistDto
                    .getListKanrenBunsho(), contactRegistDto.getLastUser(),
                    contactRegistDto.getLastPgm());
            
            contactRegistDto.setLastTmsp(beforeUpDate);

        } else if (contactRegistDto.getEditMode() == ContactRegistDto.EDIT_MODE_DELETE) {

            // 削除
            uIRenrakuInfoDao.deleteRenraku(contactRegistDto.getRegDate(),
                    contactRegistDto.getSeq());
            
            // 公開対象情報削除
            publicTargetDto.setListTrnControlCompany(new ArrayList());
            publicTargetDto.setListTrnControlGyotai(new ArrayList());
            publicTargetDto.setListTrnControlGyotaiKobetu(new ArrayList());
            publicTargetDto.setListTrnControlGyotaiTenpo(new ArrayList());
            publicTargetDto.setListTrnControlShozoku(new ArrayList());
            getRegKoukaiTaishoLogic().execute(
                    publicTargetDto, contactRegistDto.getLastUser(), contactRegistDto.getLastPgm());

            //関連文書の削除
            contactRegistDto.setListKanrenBunsho(new ArrayList());
            getRegKanrenBunshoLogic().execute(INFO_SHU, contactRegistDto.getRegDate(), contactRegistDto.getSeq(), contactRegistDto.getListKanrenBunsho(),
                    contactRegistDto.getLastUser(), contactRegistDto.getLastPgm());

        }
    }
}
