package jp.co.isid.mos.bird.config.campaignmasterregist.logic.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.config.campaignmasterregist.common.CampaignMasterRegistConst;
import jp.co.isid.mos.bird.config.campaignmasterregist.dao.MstCampSumMenuDao;
import jp.co.isid.mos.bird.config.campaignmasterregist.dao.MstCampaignDao;
import jp.co.isid.mos.bird.config.campaignmasterregist.dao.MstMenuDao;
import jp.co.isid.mos.bird.config.campaignmasterregist.dto.CampaignMasterRegistDto;
import jp.co.isid.mos.bird.config.campaignmasterregist.entity.MstCampaign;
import jp.co.isid.mos.bird.config.campaignmasterregist.entity.MstMenu;
import jp.co.isid.mos.bird.config.campaignmasterregist.entity.UICampMenu;
import jp.co.isid.mos.bird.config.campaignmasterregist.entity.UISibuList;
import jp.co.isid.mos.bird.config.campaignmasterregist.logic.CheckCampaignInfoLogic;
import jp.co.isid.mos.bird.framework.exception.CannotExecuteException;
import jp.co.isid.mos.bird.framework.exception.CannotInputException;
import jp.co.isid.mos.bird.framework.exception.DuplicateDataException;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.GenericMessageException;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.exception.NoInputException;
import jp.co.isid.mos.bird.framework.exception.NotExistException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.exception.NotRelevantException;
import jp.co.isid.mos.bird.framework.exception.NotSelectedException;
import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.framework.util.verifier.DateVerifier;

/**
 * 入力内容チェック
 *
 * 更新日：2010/09/01 表示キャンペーン上限数変更対応
 *        1.表示キャンペーン上限数を10件から15件に変更しました。
 *        2.既に表示期間内のキャンペーンが10件以上になっている場合は、
 *          ダイアログメッセージを表示し登録の有無を確認する処理を追加しました。
 * 更新日：2018/11/19 表示期間中キャンペーン上限数変更対応
 *        1.表示キャンペーン上限数を15件から20件に変更しました。
 * 更新日：2018/12/11 表示期間中キャンペーン上限数変更対応
 *        1.表示キャンペーン上限数を20件から25件に変更しました。
 * 更新日：2021/04/21 表示期間中キャンペーン上限数変更対応
 *        1.表示キャンペーン上限数を25件から30件に変更しました。
 * @author xnkusama
 *
 */
public class CheckCampaignInfoLogicImpl implements CheckCampaignInfoLogic {

    /** ロジックID */
    public static final String LOGIC_ID = "BCF006L03";
    /**
     * 表示期間中キャンペーン登録上限数:30件
     * 2021/04/21仕様変更（前回は25件でした）
     */
    private static final int MAX_CAMP_CNT = 30;
    /** DAO */
    private MstMenuDao campmasterregistMstMenuDao;
    private MstCampaignDao campmasterregistMstCampaignDao;
    private MstCampSumMenuDao campmasterregistMstCampSumMenuDao;


    /**
     * 入力内容チェック
     *
     * 対象期間FROMから対象期間TOが123日以内
     *
     * @param dto
     */
    public void execute(CampaignMasterRegistDto dto) {
        //エラー情報クリア
        clearErrorInfo(dto);

        MstCampaign mstCamp = dto.getEntityMstCampaign();
        /* タイトルの入力チェック */
        if (isNull(mstCamp.getCampTitle())) {
            throw new NotNullException("タイトル", "campTitle", 0);
        }
        if (mstCamp.getCampTitle().getBytes().length > 40) {
            throw new GenericMessageException("タイトルは４０バイト以内で入力してください。", "campTitle", 0);
        }

        /* 日付項目チェック */
        //対象期間開始日
        checkDate(mstCamp.getCampFrom(), "対象期間開始日", "campFrom");
        //対象期間終了日
        checkDate(mstCamp.getCampTo(), "対象期間終了日", "campTo");
        //表示期間開始日
        checkDate(mstCamp.getDispFrom(), "表示期間開始日", "dispFrom");
        //表示期間終了日
        checkDate(mstCamp.getDispTo(), "表示期間終了日", "dispTo");

        // 対象期間開始日 ＞ 対象期間終了日の場合
        if (mstCamp.getCampFrom().compareTo(mstCamp.getCampTo()) > 0) {
            throw new NotRelevantException("対象期間開始日", "対象期間終了日以前の日付", "dispFrom", 0);
        }

        // 表示期間開始日 ＞ 表示期間終了日の場合
        if (mstCamp.getDispFrom().compareTo(mstCamp.getDispTo()) > 0) {
            throw new NotRelevantException("表示期間開始日", "表示期間終了日以前の日付", "dispFrom", 0);
        }

        // 表示期間開始日＜対象期間開始日＋１の場合
        String date = getNextDate(mstCamp.getCampFrom(), 1);
        if (mstCamp.getDispFrom().compareTo(date) < 0) {
            throw new NotRelevantException("表示期間開始日", "対象期間開始日の翌日以降", "dispFrom", 0);
        }

        // 表示期間終了日＜＝対象期間終了日の場合
        if (mstCamp.getDispTo().compareTo(mstCamp.getCampTo()) <= 0) {
            throw new NotRelevantException("表示期間終了日", "対象期間終了日の翌日以降の日付", "campFrom", 0);
        }

        // 対象期間終了日＜表示期間開始日の場合
        if (mstCamp.getCampTo().compareTo(mstCamp.getDispFrom()) < 0) {
            throw new NotRelevantException("表示期間開始日", "対象期間終了日以前の日付", "campFrom", 0);
        }
        // （対象期間終了日 － 対象期間開始日）の日数が92日よりも長かった場合
        date = getNextDate(mstCamp.getCampFrom(), 92);
        if (mstCamp.getCampTo().compareTo(date) >= 0) {
            throw new NotRelevantException("対象期間", "９２日以内", "campFrom", 0);
        }

        // （表示期間終了日 － 表示期間開始日）の日数が123日よりも長かった場合
        date = getNextDate(mstCamp.getDispFrom(), 123);
        if (mstCamp.getDispTo().compareTo(date) >= 0) {
            throw new NotRelevantException("表示期間", "１２３日以内", "dispFrom", 0);
        }

        // 対象期間開始日＜システム日付－92日の場合
        date = getNextDate(dto.getSysDate(), -92);
        if (mstCamp.getCampFrom().compareTo(date) < 0) {
            throw new CannotInputException("対象期間開始日に、９２日以前の日付", "campFrom", 0);
        }
        // 表示期間終了日が本日以前の日付であった場合
        if (mstCamp.getDispTo().compareTo(dto.getSysDate()) <= 0) {
            throw new NoInputException("表示期間終了日は、翌日以降の日付", "dispTo", 0);
        }
        // （表示期間終了日 － 対象期間開始日）の日数が124日よりも長かった場合
        date = getNextDate(mstCamp.getCampFrom(), 124);
        if (mstCamp.getDispTo().compareTo(date) >= 0) {
            throw new NotRelevantException("対象期間開始日から表示期間終了日", "１２４日以内", "campFrom", 0);
        }


        // 表示期間中のキャンペーン数チェック
        int counter = 0;
        //表示期間中の最大キャンペーン数
        int maxCnt = 0;
        while (mstCamp.getDispTo().compareTo(getNextDate(mstCamp.getDispFrom(), counter)) > 0) {
            int campCnt = getCampmasterregistMstCampaignDao().getCountViewCamp(getNextDate(mstCamp.getDispFrom(), counter), dto.getCampId());
            if (campCnt >= MAX_CAMP_CNT) {
                throw new CannotExecuteException("表示期間内にキャンペーンが30個登録されているため、登録");
            }
            if (maxCnt < campCnt) {
            	maxCnt = campCnt;
            }
            counter++;
        }

        // メニューの入力チェック
        boolean flgInput = false;

        Map mapMenu = new HashMap();
        for (int i = 0; i < dto.getEntityMstMenuList().size(); i++) {
            List listOyaMenu = (List) dto.getEntityMstMenuList().get(i);
            for (int j = 0; j < listOyaMenu.size(); j++) {
                UICampMenu entity = (UICampMenu) listOyaMenu.get(j);
                // メニューコードの重複・存在チェック
                if (!isNull(entity.getMenuCd())) {
                	flgInput = true;
                    // 重複チェック
                    if (mapMenu.containsKey(entity.getMenuCd())) {
                        setErrorInfo(i, j, "menuCd", dto);
                        throw new DuplicateDataException("メニューコード");
                    }
                    // メニューの存在チェック
                    if (entity.getMenuCd().getBytes().length > 6) {
                        setErrorInfo(i, j, "menuCd", dto);
                        throw new NotExistException("メニューコード");
                    }
                    MstMenu entityMenu = getCampmasterregistMstMenuDao().getMenuName(entity.getMenuCd());
                    if (entityMenu == null || isNull(entityMenu.getMenuCd())) {
                        setErrorInfo(i, j, "menuCd", dto);
                        throw new NotExistException("メニューコード");
                    }
                    else {
                        entity.setMenuNameKj(entityMenu.getMenuNameKj());
                        mapMenu.put(entity.getMenuCd(), "");
                    }
                }
            }
        }// end of for(int i = 0; i < dto.getEntityMstMenuList().size(); i++)
        if(!flgInput) {
        	//メニューが未入力の場合
            setErrorInfo(0, 0, "menuCd", dto);
            throw new NoInputException("メニューコード");
        }

        // 対象支部のチェック
        if (CampaignMasterRegistConst.TARGET_KBN_SIBU.equals(dto.getCondTaishoTenpo())) {
            Iterator ite;
            if (dto.isFlgAreaDai()) {
                ite = dto.getListAreaDai().iterator();
            }
            else {
                ite = dto.getListSibu().iterator();
            }
            boolean flgSibu = false;
            for (;ite.hasNext();) {
                UISibuList entity = (UISibuList) ite.next();
                if (entity.getChkSentaku()) {
                    flgSibu = true;
                    break;
                }
            }
            if (!flgSibu) {
                throw new NotSelectedException("対象支部");
            }
        }

        /* ワーニング */
        checkWarning(dto);
        /* 確認ダイアログ用チェック */
        checkConfirm(dto, maxCnt);
    }

    /**
     * ワーニングチェック
     */
    private void checkWarning(CampaignMasterRegistDto dto) {
        //メッセージクリア
        dto.setWarningMsg1("");
        dto.setWarningMsg2("");

        //対象期間開始日翌日以降に、対象期間開始日を変更された場合
        MstCampaign entity = dto.getEntityMstCampaign();
        if (CampaignMasterRegistConst.REGIST_MODE_UPDATE == dto.getRegistMode()) {
            if (!entity.getCampFrom().equals(entity.getCampFromEditBef())) {
                if (entity.getCampFromEditBef().compareTo(dto.getSysDate()) < 0) {
                    dto.setWarningMsg1(CampaignMasterRegistConst.WARNING_MSG1);
                }
            }
        }
        //対象期間終了日がシステム日付前日以前であった場合
        if (CampaignMasterRegistConst.REGIST_MODE_UPDATE == dto.getRegistMode()) {
            if (entity.getCampTo().compareTo(dto.getSysDate()) < 0) {
                dto.setWarningMsg2(CampaignMasterRegistConst.WARNING_MSG2);
            }
        }
    }
    /**
     * 確認ダイアログ用チェック
     *
     * @param dto
     * @param maxCampCnt
     * @author ASPAC
     */
    private void checkConfirm(CampaignMasterRegistDto dto, int maxCampCnt) {
        //メッセージクリア
        dto.setConfirmMsg("");
        if(10 <= maxCampCnt) {
        	dto.setConfirmMsg("表示期間内にキャンペーンが１０個以上登録されています。登録してもよろしいですか？");
        }
    }

    /**
     * エラーインデックスセット
     * @param oya
     * @param ko
     * @param element
     */
    private void setErrorInfo(int oya, int ko, String element, CampaignMasterRegistDto dto) {
        dto.setErrIndexOya(oya);
        dto.setErrIndexKo(ko);
        dto.setErrElement(element);
    }
    /**
     * エラーインデックスクリア
     */
    private void clearErrorInfo(CampaignMasterRegistDto dto) {
        dto.setErrIndexOya(0);
        dto.setErrIndexKo(0);
        dto.setErrElement("");
    }


    private boolean isNull(String value) {
        return value == null || value.trim().equals("") ? true : false;
    }

    private String getNextDate(String date, int val) {
        try {
            return DateManager.getNextDate(date, val);
        }
        catch (Exception ex) {
            throw new FtlSystemException("入力チェック");
        }
    }

    private void checkDate(String date, String name, String htmlName) {
        if (isNull(date)) {
            throw new NotNullException(name, htmlName, 0);
        }

        DateVerifier df = new DateVerifier(DateVerifier.DATE_TYPE_YMD);
        if (!df.validate(date)) {
            throw new InvalidInputException(name, htmlName, 0);
        }
    }

    public MstMenuDao getCampmasterregistMstMenuDao() {
        return campmasterregistMstMenuDao;
    }

    public void setCampmasterregistMstMenuDao(MstMenuDao campmasterregistMstMenuDao) {
        this.campmasterregistMstMenuDao = campmasterregistMstMenuDao;
    }

    public MstCampaignDao getCampmasterregistMstCampaignDao() {
        return campmasterregistMstCampaignDao;
    }

    public void setCampmasterregistMstCampaignDao(
            MstCampaignDao campmasterregistMstCampaignDao) {
        this.campmasterregistMstCampaignDao = campmasterregistMstCampaignDao;
    }

    public MstCampSumMenuDao getCampmasterregistMstCampSumMenuDao() {
        return campmasterregistMstCampSumMenuDao;
    }

    public void setCampmasterregistMstCampSumMenuDao(
            MstCampSumMenuDao campmasterregistMstCampSumMenuDao) {
        this.campmasterregistMstCampSumMenuDao = campmasterregistMstCampSumMenuDao;
    }
}
