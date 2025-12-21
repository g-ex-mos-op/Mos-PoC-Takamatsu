package jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.logic.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;
import jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.common.ProceedsCommon;
import jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.common.ProceedsConstants;
import jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.dao.MstTicketInfoDao;
import jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.dao.ProceedsManageGepoDao;
import jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.entity.MstTicketInfo;
import jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.entity.ProceedsManageGepoInfo;
import jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.logic.ProceedsManageGepoLogic;

/**
 * 売上金管理月報情報取得ロジック
 *
 * @author xjung
 */
public class ProceedsManageGepoLogicImpl implements ProceedsManageGepoLogic {
    /** 売上金管理月報情報取得ロジックID */
    public static final String LOGIC_ID = "BSM013L02";


    /** チケット情報Dao */
    private MstTicketInfoDao mstTicketInfoDao;

    /** 売上金管理月報情報Dao */
    private ProceedsManageGepoDao proceedsManageGepoDao;

    /**
     * 売上金管理月報情報を取得する
     * @param userType  ユーザタイプ
     * @param userId    ユーザID
     * @param limitKbn  制限フラグ
	 * @param onerCd	 オーナーコード
     * @param companyCd   会社コード
     * @param miseCd      店コード
     * @param taishoYM    対象年月
     * @return Map 売上金管理月報情報
	 */
    public Map execute (
        String userType,
        String userId,
        boolean limitKbn,
        String onerCd,
        String companyCd,
        String miseCd,
        String taishoYM) {


		Map resultMap = new HashMap();

        //入力チェック
        validate(userType, userId, companyCd, miseCd, taishoYM);

		// 対象期間From、To設定
        String kikanFrom = taishoYM + ProceedsConstants.FIRST_DAY;
        String kikanTo = taishoYM + ProceedsConstants.LAST_DAY;

        // 売上金管理月報情報取得
        List tempList = getProceedsManageGepoDao().selectGepo
            (userType, userId, limitKbn, onerCd, companyCd, miseCd, kikanFrom, kikanTo);

        // 検索結果が０件の場合
        if (tempList == null || tempList.isEmpty()) {
            throw new NoResultException();
        }
        ProceedsManageGepoInfo info = (ProceedsManageGepoInfo) tempList.get(0);
        if (ProceedsCommon.isNull(info.getEigyoDt())) {
            throw new NoResultException();
        }

        // 各項目の合計値を算出する
        ProceedsManageGepoInfo sumDetail =
            (ProceedsManageGepoInfo) tempList.get(tempList.size() - 1);
        sumDetail.setRClass(ProceedsConstants.TR_TOTAL_SUM);
        sumDetail.setEigyoDt(ProceedsConstants.LABEL_SUM);

        // チケット情報取得
        List tempTcktList = getMstTicketInfoDao().select(companyCd, miseCd);
        // チケット名称設定
        List tcktList = new ArrayList();
        for(int i = 1; i < 16; i++) {
            tcktList.add(getTcktName(i, tempTcktList));
        }

        // 明細リンク表示フラグ設定
        SimpleDateFormat sdf = new SimpleDateFormat(DateFormatter.PATTERN_MONTH);
        String meisaiDispFlg = "0";
//add 2019/07/15 USI張 #34 begin
        String uriageMeisaiDispFlg = "0";
//add 2019/07/15 USI張 #34 end
        try {
            Date taisyoYM = sdf.parse(taishoYM);
            if (taisyoYM.before(sdf.parse(ProceedsConstants.MEISAI_MIN_MONTH))) {
                meisaiDispFlg = "0";
            } else {
                meisaiDispFlg = "1";
            }
//add 2019/07/15 USI張 #34 begin
            //売上と消費税 明細リンク表示フラグ設定
            if (taisyoYM.before(sdf.parse(ProceedsConstants.URIAGE_MEISAI_MIN_MONTH))) {
                uriageMeisaiDispFlg = "0";
            } else {
                uriageMeisaiDispFlg = "1";
            }
//add 2019/07/15 USI張 #34 end
        } catch (ParseException e) {
            // 必要なし
        }

        resultMap.put(ProceedsConstants.MAP_MEISAI_DISP_FLG, meisaiDispFlg);
//add 2019/07/15 USI張 #34 begin
        resultMap.put(ProceedsConstants.MAP_URIAGE_MEISAI_DISP_FLG, uriageMeisaiDispFlg);
//add 2019/07/15 USI張 #34 end
        resultMap.put(ProceedsConstants.MAP_RST_LIST, tempList);
        resultMap.put(ProceedsConstants.MAP_TCK_NAME_LIST, tcktList);


        return resultMap;
    }

    /**
	 * 入力チェックをする
     * @param userType  ユーザタイプ
     * @param userId    ユーザID
     * @param companyCd 会社コード
     * @param miseCd    店コード
     * @param taishoYM  対象年月
	 */
	private void validate(String userType, String userId
			, String companyCd
			, String miseCd, String taishoYM) {
        if (ProceedsCommon.isNull(userType)) {
            throw new NotNullException(ProceedsConstants.MSG_USER_TYPE);
        }
        if (ProceedsCommon.isNull(userId)) {
            throw new NotNullException(ProceedsConstants.MSG_USER_ID);
        }
		if (ProceedsCommon.isNull(companyCd)) {
			throw new NotNullException(ProceedsConstants.MSG_COMPANY_CD);
		}
		if (ProceedsCommon.isNull(miseCd)) {
			throw new NotNullException(ProceedsConstants.MSG_TAISHO_TENPO);
		}
		if (ProceedsCommon.isNull(taishoYM)) {
			throw new NotNullException(ProceedsConstants.MSG_TAISHO_YM);
		}
	}


    /**
     * チケット販売名称を設定する
     * @param num         番号
     * @param tckNameList チケット販売名称リスト
     * @return String     チケット販売名称
     */
    private String getTcktName(int num, List tckNameList) {
        String srtNum = ProceedsCommon.getStringNum(num, 6);
        if (tckNameList != null && !tckNameList.isEmpty()) {
            for (Iterator it = tckNameList.iterator(); it.hasNext();) {
                MstTicketInfo info = (MstTicketInfo) it.next();
                if (srtNum.equals(info.getTcktCd())) {
                    return info.getTcktName();
                }
            }
        }
        return ProceedsConstants.LABEL_TICKET + String.valueOf(num);
    }

    /**
     * チケット情報Daoを取得する
     * @return チケット情報Dao
     */
    public MstTicketInfoDao getMstTicketInfoDao() {
        return mstTicketInfoDao;
    }

    /**
     * チケット情報Daoを設定する
     * @param mstTicketInfoDao チケット情報Dao
     */
    public void setMstTicketInfoDao(MstTicketInfoDao mstTicketInfoDao) {
        this.mstTicketInfoDao = mstTicketInfoDao;
    }

    /**
     * 売上金管理月報情報Daoを取得する
     * @return 売上金管理月報情報Dao
     */
    public ProceedsManageGepoDao getProceedsManageGepoDao() {
        return proceedsManageGepoDao;
    }

    /**
     * 売上金管理月報情報Daoを設定する
     * @param proceedsManageGepoDao 売上金管理月報情報Dao
     */
    public void setProceedsManageGepoDao(ProceedsManageGepoDao proceedsManageGepoDao) {
        this.proceedsManageGepoDao = proceedsManageGepoDao;
    }
}