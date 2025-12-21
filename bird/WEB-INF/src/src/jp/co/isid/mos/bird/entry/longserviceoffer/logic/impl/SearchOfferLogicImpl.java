/*
 * 作成日: 2006/4/19
 */
package jp.co.isid.mos.bird.entry.longserviceoffer.logic.impl;

import java.util.ArrayList;
import java.util.List;

import jp.co.isid.mos.bird.entry.longserviceoffer.common.LongserviceOfferCommon;
import jp.co.isid.mos.bird.entry.longserviceoffer.common.LongserviceOfferConstants;
import jp.co.isid.mos.bird.entry.longserviceoffer.dao.UIOfferEntryDao;
import jp.co.isid.mos.bird.entry.longserviceoffer.dao.UIOfferMstDao;
import jp.co.isid.mos.bird.entry.longserviceoffer.dao.UIOfferOnerInfoDao;
import jp.co.isid.mos.bird.entry.longserviceoffer.dto.LongserviceOfferDto;
import jp.co.isid.mos.bird.entry.longserviceoffer.entity.UIOfferEntry;
import jp.co.isid.mos.bird.entry.longserviceoffer.entity.UIOfferMst;
import jp.co.isid.mos.bird.entry.longserviceoffer.entity.UIOfferOner;
import jp.co.isid.mos.bird.entry.longserviceoffer.logic.SearchOfferLogic;
import jp.co.isid.mos.bird.entry.nationalentry.dto.NationalEntryDto;
import jp.co.isid.mos.bird.entry.nationalentry.util.NationalEntryUtil;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;

/**
 * 永年勤続情報の検索ロジック
 * @author narita
 */
public class SearchOfferLogicImpl implements SearchOfferLogic {

    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BEN018L02";

    /** エントリー日付マスタ管理Dao */
    UIOfferMstDao uIOfferMstDao;

    /** エントリー日付マスタ管理Dao */
    UIOfferEntryDao uIOfferEntryDao;

    /** オーナーマスタ管理Dao */
    UIOfferOnerInfoDao uIOfferOnerInfoDao;

    /**
     * 永年勤続責任者情報リストを取得する
     * @param  longserviceOfferDto
     * @return 永年勤続責任者情報
     */
    public UIOfferMst executeMst(LongserviceOfferDto dto,NationalEntryDto nDto,int initFlg) throws ApplicationException {
        //検索キーのnullチェックを行う
        validate(dto);

        // 永年勤続責任者情報取得
        UIOfferMst result = getUIOfferMstDao().getOfferMst(
        		dto.getEntryCd(),dto.getEntryYear(),dto.getEntryKai(),
        		dto.getCompanyCd(),dto.getOnerCd(),dto.getAtesakiKbn());

        // 対象が存在しない場合は、デフォルトで入力項目を追加する。
        if(result == null){
        	result = new UIOfferMst();

			// キー情報のデフォルトセット
	        result.setEntryCd(dto.getEntryCd());
	        result.setEntryYear(dto.getEntryYear());
	        result.setEntryKai(dto.getEntryKai());
	        result.setCompanyCd(dto.getCompanyCd());
	        result.setOnerCd(dto.getOnerCd());
	        result.setAtesakiKbn(dto.getAtesakiKbn());
	        result.setAtesakiKbn(dto.getAtesakiKbn());

	        if(initFlg == LongserviceOfferConstants.INIT_FLG_NATIONAL){
				// 全国大会から遷移してきた場合、全国大会の入力値をセット
				result.setName( nDto.getPrmDutyName() );
				result.setSoufuName( nDto.getPrmDutySoufuName() );
				result.setTel( nDto.getPrmDutyTel() );
				result.setJobType( nDto.getPrmDutyJobType() );
			}else{
	        	// オーナー情報のセット
	        	result.setName(dto.getUIOnerInfo().getOnerNameKj());
	        	result.setTel(dto.getUIOnerInfo().getOnerTel());
			}
        }else{
        	// 空白除去
        	result.setName( result.getName().trim() );
        	result.setSoufuName( result.getSoufuName().trim() );
        	result.setTel( result.getTel().trim() );
        	result.setJobType( result.getJobType().trim() );
        }
    	// デフォルト値の保存
    	result.setDefName(result.getName());
    	result.setDefSoufuName(result.getSoufuName());
    	result.setDefTel(result.getTel());
    	result.setDefJobType(result.getJobType());

        return result;
    }

    /**
     * 永年勤続申請者情報リストを取得する
     * @param  longserviceOfferDto
     * @return 永年勤続申請者情報リスト
     */
    public List executeInfo(LongserviceOfferDto dto) throws ApplicationException {
        //検索キーのnullチェックを行う
        validate(dto);

        List result = new ArrayList();

        // 永年勤続申請者情報リスト取得
	    result = getUIOfferEntryDao().getOfferEntry(
	      		dto.getEntryCd(),dto.getEntryYear(),dto.getEntryKai(),
	       		dto.getCompanyCd(),dto.getOnerCd());

	    // 一軒も存在しない場合は、デフォルトで入力項目を追加する。
	    if(result.size() <= 0){
	    	// 入力欄の新規作成
	    	UIOfferEntry uIOfferEntry = new UIOfferEntry();
	    	// デフォルト値のセット
	    	uIOfferEntry.setEntryCd(dto.getEntryCd());
	    	uIOfferEntry.setEntryYear(dto.getEntryYear());
	    	uIOfferEntry.setEntryKai(dto.getEntryKai());
	    	uIOfferEntry.setCompanyCd(dto.getCompanyCd());
	    	uIOfferEntry.setOnerCd(dto.getOnerCd());
	    	uIOfferEntry.setSeqNo(LongserviceOfferConstants.SEQ_START);
	    	result.add(uIOfferEntry);
        }else{

        	for(int i = 0;i < result.size(); i++){
            	UIOfferEntry uIOfferEntry = (UIOfferEntry)result.get(i);
        	 	if(uIOfferEntry.getBirthday() != null && uIOfferEntry.getBirthday().trim().length() !=0 ){
        	 		String strBirthday = uIOfferEntry.getBirthday().trim();

	            	// 西暦-年セット
	            	uIOfferEntry.setBirthday_Year(strBirthday.substring(0,4) );
	            	// 西暦-月セット
	            	uIOfferEntry.setBirthday_Month(strBirthday.substring(4,6) );
	            	// 西暦-日セット
	            	uIOfferEntry.setBirthday_Day(strBirthday.substring(6,8) );
	            	// 年齢再計算
	            	if (uIOfferEntry.getBirthday() == null || "".equals(uIOfferEntry.getBirthday())) {
	            		uIOfferEntry.setAge("");
	            	} else {
	                	uIOfferEntry.setAge(String.valueOf(NationalEntryUtil.chgToAgeFrDate(
	                			uIOfferEntry.getBirthday(), dto.getSysDate())));
	            	}
        	 	}
        	 	if(uIOfferEntry.getEntryDate() != null && uIOfferEntry.getEntryDate().trim().length() !=0 ){
        	 		String strEntryDate = uIOfferEntry.getEntryDate().trim();
	            	// 西暦-年セット
	            	uIOfferEntry.setEntryDate_Year(strEntryDate.substring(0,4) );
	            	// 西暦-月セット
	            	uIOfferEntry.setEntryDate_Month(strEntryDate.substring(4,6) );
	            	// 西暦-日セット
	            	uIOfferEntry.setEntryDate_Day(strEntryDate.substring(6,8) );
        	 	}
            	// 空白除去
            	uIOfferEntry.setFNameKj( uIOfferEntry.getFNameKj().trim() );
            	uIOfferEntry.setLNameKj( uIOfferEntry.getLNameKj().trim() );
            	uIOfferEntry.setFNameRm( uIOfferEntry.getFNameRm().trim() );
            	uIOfferEntry.setLNameRm( uIOfferEntry.getLNameRm().trim() );
            	uIOfferEntry.setAge( uIOfferEntry.getAge().trim() );
        	}
        }

    	if(result.size() >= 29 ){
    		// 入力欄が30個になったら入力欄追加ボタンを無効する
    		dto.setInsertBtnFlg(false);
    	}else{
    		// 入力欄追加ボタンは有効
    		dto.setInsertBtnFlg(true);
    	}

        return result;
    }

    /**
     * 永年勤続申請者情報リストを取得する
     * @param  longserviceOfferDto
     * @return オーナー情報
     **/
    public UIOfferOner executeOner(String companyCd, String onerCd) throws ApplicationException {
        //検索キーのnullチェックを行う
    	validate(companyCd,onerCd);

        // 永年勤続申請者情報リスト取得
        UIOfferOner result = getUIOfferOnerInfoDao().getOfferOnerInfo(
        		companyCd,onerCd);

        // デフォルト値をセットする。
        if(result == null){
        	result = new UIOfferOner();
        }

        return result;
    }

    /**
     * 最大ソート番号を取得する
     * @param  longserviceOfferDto
     * @return 最大ソート番号
     */
    public String getMaxSeqNo(LongserviceOfferDto dto) throws ApplicationException {
        //検索キーのnullチェックを行う
        validate(dto);

        // 申請者情報の最大ソート番号を取得する
        String maxSeqNo = getUIOfferEntryDao().getMaxSeqNo(
	      		dto.getEntryCd(),dto.getEntryYear(),dto.getEntryKai(),
	       		dto.getCompanyCd(),dto.getOnerCd());

        // デフォルト値をセットする。
        if(LongserviceOfferCommon.isNull(maxSeqNo)){
        	maxSeqNo = LongserviceOfferConstants.SEQ_START;
        }

        return maxSeqNo;
    }

    /**
     * データカウントを取得する
     * @param  longserviceOfferDto
     * @return 最大ソート番号
     */
    public int getEntryCount(LongserviceOfferDto dto) throws ApplicationException {
        //検索キーのnullチェックを行う
        validate(dto);

    	// 永年勤続申請者情報のカウントの取得
	    int count = getUIOfferEntryDao().getOfferEntryCount(
	    		dto.getEntryCd(),dto.getEntryYear(),dto.getEntryKai(),
	    		dto.getCompanyCd(),dto.getOnerCd());

        return count;
    }

    /**
     * 検索キーのnullチェック
     * @param longserviceOfferDto
     */
    private void validate(LongserviceOfferDto dto){

        // エントリーコード
        if (LongserviceOfferCommon.isNull(dto.getEntryCd())) {
            throw new NotNullException("エントリーコード");
        }
        // エントリー年
        if (LongserviceOfferCommon.isNull(dto.getEntryYear())) {
            throw new NotNullException("エントリー年度");
        }
        // エントリー回数
        if (LongserviceOfferCommon.isNull(dto.getEntryKai())) {
            throw new NotNullException("エントリー回数");
        }
        // 企業コード
        if (LongserviceOfferCommon.isNull(dto.getCompanyCd())) {
            throw new NotNullException("企業コード");
        }
        // オーナーコード
        if (LongserviceOfferCommon.isNull(dto.getOnerCd())) {
            throw new NotNullException("オーナーコード");
        }
    }

    /**
     * 検索キーのnullチェック
     * @param longserviceOfferDto
     */
    private void validate(String companyCd, String onerCd){

        // 企業コード
        if (LongserviceOfferCommon.isNull(companyCd)) {
            throw new NotNullException("企業コード");
        }
        // オーナーコード
        if (LongserviceOfferCommon.isNull(onerCd)) {
            throw new NotNullException("オーナーコード");
        }
    }

	public UIOfferEntryDao getUIOfferEntryDao() {
		return uIOfferEntryDao;
	}

	public void setUIOfferEntryDao(UIOfferEntryDao offerEntryDao) {
		uIOfferEntryDao = offerEntryDao;
	}

	public UIOfferMstDao getUIOfferMstDao() {
		return uIOfferMstDao;
	}

	public void setUIOfferMstDao(UIOfferMstDao offerMstDao) {
		uIOfferMstDao = offerMstDao;
	}

	public UIOfferOnerInfoDao getUIOfferOnerInfoDao() {
		return uIOfferOnerInfoDao;
	}

	public void setUIOfferOnerInfoDao(UIOfferOnerInfoDao offerOnerInfoDao) {
		uIOfferOnerInfoDao = offerOnerInfoDao;
	}
}
