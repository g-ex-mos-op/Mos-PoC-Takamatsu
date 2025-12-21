/*
 * 作成日: 2006/12/26
 */
package jp.co.isid.mos.bird.entry.nationalentry.logic.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.entry.nationalentry.dao.UINatiEntryDutyInfoDao;
import jp.co.isid.mos.bird.entry.nationalentry.dao.UINatiEntryJoinInfoDao;
import jp.co.isid.mos.bird.entry.nationalentry.dao.UINatiEntryOnerInfoDao;
import jp.co.isid.mos.bird.entry.nationalentry.dao.UINatiEntrySelectInfoDao;
import jp.co.isid.mos.bird.entry.nationalentry.dao.UINatiEntryStateDao;
import jp.co.isid.mos.bird.entry.nationalentry.dto.NationalEntryDto;
import jp.co.isid.mos.bird.entry.nationalentry.entity.UINatiEntryDutyInfo;
import jp.co.isid.mos.bird.entry.nationalentry.entity.UINatiEntryJoinInfo;
import jp.co.isid.mos.bird.entry.nationalentry.entity.UINatiEntryOnerInfo;
import jp.co.isid.mos.bird.entry.nationalentry.entity.UINatiEntrySelectInfo;
import jp.co.isid.mos.bird.entry.nationalentry.entity.UINatiEntryState;
import jp.co.isid.mos.bird.entry.nationalentry.logic.GetNatiEntryInfoLogic;
import jp.co.isid.mos.bird.entry.nationalentry.util.NationalEntryUtil;
import jp.co.isid.mos.bird.framework.exception.NotNullException;

/**
 * 店舗情報取得ロジック
 *
 * @author xlee
 */
public class GetNatiEntryInfoLogicImpl implements GetNatiEntryInfoLogic {

    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BEN015L02";

    /**
     * オーナー情報DAOを取得します。
     */
    private UINatiEntryOnerInfoDao uiNatiEntryOnerInfoDao;

    /**
     * 申込責任者情報DAOを取得します。
     */
    private UINatiEntryDutyInfoDao uiNatiEntryDutyInfoDao;

    /**
     * 申込参加者情報DAOを取得します。
     */
    private UINatiEntryJoinInfoDao uiNatiEntryJoinInfoDao;

    /**
     * オーナー別エントリー状況DAOを取得します。
     */
    private UINatiEntryStateDao uiNatiEntryStateDao;

    /**
     * エントリーセレクション情報DAOを取得します。
     */
    private UINatiEntrySelectInfoDao uiNatiEntrySelectInfoDao;
    /**
     * オーナー情報DAOを取得します。
     * @return オーナー情報DAO
     */
    public UINatiEntryOnerInfoDao getUINatiEntryOnerInfoDao() {
        return uiNatiEntryOnerInfoDao;
    }

    /**
     * オーナー情報DAOを設定します。
     * @param uiOnerInfoDao オーナー情報DAO
     */
    public void setUINatiEntryOnerInfoDao(UINatiEntryOnerInfoDao uiNatiEntryOnerInfoDao) {
        this.uiNatiEntryOnerInfoDao = uiNatiEntryOnerInfoDao;
    }

    /**
     * 申込責任者情報DAOを取得します。
     * @return 申込責任者情報DAO
     */
    public UINatiEntryDutyInfoDao getUINatiEntryDutyInfoDao() {
        return uiNatiEntryDutyInfoDao;
    }

    /**
     * 申込責任者情報DAOを設定します。
     * @param uiNatiEntryDutyPersonInfoDao 申込責任者DAO
     */
    public void setUINatiEntryDutyInfoDao(UINatiEntryDutyInfoDao uiNatiEntryDutyInfoDao) {
        this.uiNatiEntryDutyInfoDao = uiNatiEntryDutyInfoDao;
    }

    /**
     * 申込参加者情報DAOを取得します。
     * @return 申込参加者情報DAO
     */
    public UINatiEntryJoinInfoDao getUINatiEntryJoinInfoDao() {
        return uiNatiEntryJoinInfoDao;
    }

    /**
     * 申込参加者情報DAOを設定します。
     * @param uiNatiEntryJoinPersonInfoDao 申込参加者情報DAO
     */
    public void setUINatiEntryJoinInfoDao(UINatiEntryJoinInfoDao uiNatiEntryJoinInfoDao) {
        this.uiNatiEntryJoinInfoDao = uiNatiEntryJoinInfoDao;
    }

    /**
     * オーナー別エントリー状況DAOを設定します。
     * @param uiNatiEntryStateDao　オーナー別エントリー状況DAO
     */
    public void setUINatiEntryStateDao(UINatiEntryStateDao uiNatiEntryStateDao) {
        this.uiNatiEntryStateDao = uiNatiEntryStateDao;
    }

    /**
     * オーナー別エントリー状況DAOを取得します。
     * @return オーナー別エントリー状況DAO
     */
    public UINatiEntryStateDao getUINatiEntryStateDao() {
        return uiNatiEntryStateDao;
    }

    /**
     * エントリーセレクション情報DAOを取得します。
     * @return エントリーセレクション情報DAO
     */
    public UINatiEntrySelectInfoDao getUINatiEntrySelectInfoDao() {
        return uiNatiEntrySelectInfoDao;
    }

    /**
     * エントリーセレクション情報DAOを設定します。
     * @param uiNatiEntrySelectInfoDao エントリーセレクション情報DAO
     */
    public void setUINatiEntrySelectInfoDao(UINatiEntrySelectInfoDao uiNatiEntrySelectInfoDao) {
        this.uiNatiEntrySelectInfoDao = uiNatiEntrySelectInfoDao;
    }

	/**
	 * 申込情報を取得します。
     * @param nationalEntryDto　全国大会申込ＤＴＯ情報
     * @return  申込情報リスト
	 */
    public Map execute(NationalEntryDto nationalEntryDto) {

    	String entryCd = nationalEntryDto.getCondEntryCd();
    	String entryYear = nationalEntryDto.getCondEntryYear();
    	String entryKai = nationalEntryDto.getCondEntryKai();
    	String companyCd = nationalEntryDto.getCondCompanyCd();
    	String onerCd = nationalEntryDto.getCondOnerCd();

    	//エラー処理：
    	if(CommonUtil.isNull(entryCd)){
            throw new NotNullException("エントリーコード"); //E0506 エントリーコード
        }
    	//エラー処理：
    	if(CommonUtil.isNull(entryYear)){
            throw new NotNullException("エントリー年"); //E0506 エントリー年
        }
    	//エラー処理：
    	if(CommonUtil.isNull(entryKai)){
            throw new NotNullException("エントリー回"); //E0506 E0506　エントリー回
        }
    	if(CommonUtil.isNull(companyCd)){
            throw new NotNullException("会社コード"); //E0506 会社コード
        }
    	if(CommonUtil.isNull(onerCd)){
            throw new NotNullException("オーナーコード"); //E0506 オーナーコード
        }
    	//リタンーするリストの生成
    	Map tmpResult = new HashMap();

    	//[オーナー別エントリー状況(String)],[エントリーセレクション(Map)],[申込責任者],[申込参加者List]
    	//１．オーナー別エントリー状況
    	UINatiEntryState uinatiEntryState =
    		getUINatiEntryStateDao().getNatiEntryState(entryCd, entryYear, entryKai, companyCd, onerCd);

    	//２．エントリーセレクション情報:Map
    	List selectInfoList =
    		getUINatiEntrySelectInfoDao().getNatiEntrySelectInfo(entryCd, entryYear, entryKai, NationalEntryDto.SELECTION1);
    	List optionInfoList =
    		getUINatiEntrySelectInfoDao().getNatiEntrySelectInfo(entryCd, entryYear, entryKai, NationalEntryDto.SELECTION2);

    	//「申込区分」非表示フラグ設定
    	NationalEntryUtil.setDisabledFlg(selectInfoList, 3);
    	//「オプショナル(宿泊関連)」非表示フラグ設定
    	List optionalStay = new ArrayList();
    	for(int i=0;i<optionInfoList.size();i++){
    		if(i<4 || (i>=10 && i < 16)){
    			UINatiEntrySelectInfo info = (UINatiEntrySelectInfo) optionInfoList.get(i);
        		optionalStay.add(info);
    		}
    	}
    	NationalEntryUtil.setDisabledFlg(optionalStay, 3);
    	//「オプショナル(ツアー関連)」非表示フラグ設定
    	NationalEntryUtil.setDisabledFlg(optionInfoList.subList(4, 10), 3);
    	//「オプショナル(その他)」非表示フラグ設定
    	NationalEntryUtil.setDisabledFlg(optionInfoList.subList(20, 30), 3);
    	Map selectionKbnMap = new HashMap();
    	selectionKbnMap.put(NationalEntryDto.SELECTION1, selectInfoList);
    	selectionKbnMap.put(NationalEntryDto.SELECTION2, optionInfoList);

    	//３．申込責任者
    	UINatiEntryDutyInfo uiNatiEntryDutyInfo =
    		getUINatiEntryDutyInfoDao().getNatiEntryDutyInfo(entryCd, entryYear, entryKai, companyCd, onerCd);

    	if(uiNatiEntryDutyInfo == null) {
    		//新規登録
    		nationalEntryDto.setEditKbn(NationalEntryDto.EDIT_KBN_INSERT);
    		//申込責任者の情報がNULLであれば、生成
    		uiNatiEntryDutyInfo = new UINatiEntryDutyInfo();
    		UINatiEntryOnerInfo uiNatiEntryOnerInfo = getUINatiEntryOnerInfoDao().getNatiEntryOnerInfo(companyCd, onerCd);
    		uiNatiEntryDutyInfo.setOnerNameKj(uiNatiEntryOnerInfo.getOnerNameKj());
    		uiNatiEntryDutyInfo.setTel(uiNatiEntryOnerInfo.getOnerTel());
    		uiNatiEntryDutyInfo.setName(uiNatiEntryOnerInfo.getOnerNameKj());
    	} else {
    		nationalEntryDto.setEditKbn(NationalEntryDto.EDIT_KBN_UPDATE);
    	}

    	//４．申込参加者:List
    	List NatiEntryJoinInfoList =
    		getUINatiEntryJoinInfoDao().getNatiEntryJoinInfo(entryCd, entryYear, entryKai, companyCd, onerCd);

    	//最初登録の場合、空白値をエンティティへ設定
    	if(NatiEntryJoinInfoList == null ||
    			NatiEntryJoinInfoList.size() == 0) {
    		//参加者初期情報を設定する
    		NatiEntryJoinInfoList.add(
    				NationalEntryUtil.setDefaultJoinInfo(nationalEntryDto.getUserId(),
    													 NationalEntryDto.JOIN_FIRST));
		}

    	//生年月日を西暦→和暦に変換
    	for (int i=0;i<NatiEntryJoinInfoList.size();i++) {
    		UINatiEntryJoinInfo uiNatiEntryJoinInfo = (UINatiEntryJoinInfo)NatiEntryJoinInfoList.get(i);
    		uiNatiEntryJoinInfo.setJobTypeCd(nationalEntryDto.getSyokuiCd(uiNatiEntryJoinInfo.getJobType()));

    	 	if(uiNatiEntryJoinInfo.getBirthday() != null && uiNatiEntryJoinInfo.getBirthday().trim().length() !=0 ){
    	 		String strBirthday = uiNatiEntryJoinInfo.getBirthday().trim();
	    		// 西暦-年セット
	    		uiNatiEntryJoinInfo.setBirthday_Year(strBirthday.substring(0,4));
	        	// 西暦-月セット
	    		uiNatiEntryJoinInfo.setBirthday_Month(strBirthday.substring(4,6));
	        	// 西暦-日セット
	    		uiNatiEntryJoinInfo.setBirthday_Day(strBirthday.substring(6,8));
	    		// 年齢再計算
	        	if (uiNatiEntryJoinInfo.getBirthday() == null || "".equals(uiNatiEntryJoinInfo.getBirthday())) {
	        		uiNatiEntryJoinInfo.setAge("");
	        	} else {
	        		uiNatiEntryJoinInfo.setAge(String.valueOf(NationalEntryUtil.chgToAgeFrDate(
	        				uiNatiEntryJoinInfo.getBirthday(), nationalEntryDto.getSysDate())));
	        	}
    	 	}
    	}

    	//５．Mapへ格納する
        tmpResult.put(NationalEntryDto.MAPKEY_STATE, uinatiEntryState);
        tmpResult.put(NationalEntryDto.MAPKEY_SELECTION, selectionKbnMap);
        tmpResult.put(NationalEntryDto.MAPKEY_DUTYINFO, uiNatiEntryDutyInfo);
        tmpResult.put(NationalEntryDto.MAPKEY_JOININFO, NatiEntryJoinInfoList);

        return tmpResult;
    }
}
