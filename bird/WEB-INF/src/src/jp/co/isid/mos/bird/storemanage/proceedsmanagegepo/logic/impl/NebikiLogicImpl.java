package jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.logic.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.common.ProceedsCommon;
import jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.common.ProceedsConstants;

import jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.dao.MstNebikiKbnInfoDao;
import jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.dao.NebikiInfoDao;
import jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.entity.MstNebikiKbnInfo;
import jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.entity.NebikiInfo;
import jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.entity.ProceedsManageGepoInfo;
import jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.logic.NebikiLogic;


/**
 * 値引情報取得ロジック
 *
 * @author xsong
 */
public class NebikiLogicImpl implements NebikiLogic {
    /** 値引情報取得ロジックID */
    public static final String LOGIC_ID = "BSM013L05";

    /** 値引区分情報Dao */
    private MstNebikiKbnInfoDao mstNebikiKbnInfoDao;

    /** 値引情報Dao */
    private NebikiInfoDao nebikiInfoDao;
    
    /**
     * 売上金の取得日基準の値引情報を取得する
     * @param userType  ユーザタイプ
     * @param userId    ユーザID
     * @param limitKbn  制限フラグ 
	 * @param onerCd	 オーナーコード
     * @param companyCd 会社コード
     * @param miseCd    店コード
     * @param taishoYM  対象年月
     * @param list 売上金管理月報情報リスト
     * @return Map 値引情報
	 */
    public Map execute (
        String userType,
        String userId,
        boolean limitKbn,
        String onerCd,
        String companyCd,
        String miseCd,
        String taishoYM, List list) {
        
		Map resultMap = new HashMap();

        //入力チェック
        validate(userType, userId, companyCd, miseCd, taishoYM, list);

		// 対象期間From、To設定
        String kikanFrom = taishoYM + ProceedsConstants.FIRST_DAY;
        String kikanTo = taishoYM + ProceedsConstants.LAST_DAY;

        // 値引情報取得
        List tempList = getNebikiInfoDao().selectNebiki
            (userType, userId, limitKbn, onerCd, companyCd, miseCd, kikanFrom, kikanTo);
        
        
        // 検索結果が０件の場合
        if (tempList == null || tempList.isEmpty()) {
        	
        	//各項目すべて０設定
            tempList = setDefaultNebikiData(list);
            
        } else {
               
        	NebikiInfo info = (NebikiInfo) tempList.get(0);
         
        	// 各項目すべて０設定
        	List resultList = setDefaultNebikiData(list);
        	
        	if (!ProceedsCommon.isNull(info.getEigyoDt())) {
        		
	        	for (int i = 0; i < resultList.size() -1 ; i++){
	        		
	        		NebikiInfo nebiki = (NebikiInfo) resultList.get(i);
	        		
	        		for (Iterator itData = tempList.iterator(); itData.hasNext();) {
	        			
	        			NebikiInfo tempNebiki = (NebikiInfo)itData.next();
	        	
	        			//売上金で取得した日付で、値引情報のデータが存在する場合
	        			if(nebiki.getEigyoDt().equals(tempNebiki.getEigyoDt())){
	        			   
	        				nebiki = tempNebiki;
	        	          }
	        			
	        			resultList.set(i, nebiki);
	        		}
	        	 
	        	}
	        	
	        	// 各項目の合計値をセット
	        	resultList.set(resultList.size()-1, totalValue(resultList));
	          }
        	        	
        	tempList = resultList;
         }
        
        // 各項目の合計値を算出する
        NebikiInfo sumDetail =
            (NebikiInfo) tempList.get(tempList.size() - 1);
        sumDetail.setRClass(ProceedsConstants.TR_TOTAL_SUM);
        sumDetail.setEigyoDt(ProceedsConstants.LABEL_SUM);
 
        // 値引区分情報取得
        List tempNebikiKbnList = getMstNebikiKbnInfoDao().select();
        // 値引区分名称設定
        List kaikeiKbnList = new ArrayList();
        for(int i = 1; i < 10; i++) {
            kaikeiKbnList.add(getNebikiKbnName(i, tempNebikiKbnList));
        }
   
        resultMap.put(ProceedsConstants.MAP_NEBIKI_RST_LIST, tempList);
        resultMap.put(ProceedsConstants.MAP_NK_NAME_LIST, kaikeiKbnList);
        

        return resultMap;
    }

	/**
	 * 値引合計を求める。
	 * @param nebikiDataList
	 * @return NebikiInfo　値引情報
	 */
    private NebikiInfo totalValue(List nebikiDataList) {
    	
    	BigDecimal totalNebikiKen_1 = new BigDecimal(ProceedsConstants.ZERO);
    	BigDecimal totalNebikiKen_2 = new BigDecimal(ProceedsConstants.ZERO);
		BigDecimal totalNebikiKen_3 = new BigDecimal(ProceedsConstants.ZERO);
		BigDecimal totalNebikiKen_4 = new BigDecimal(ProceedsConstants.ZERO);
		BigDecimal totalNebikiKen_5 = new BigDecimal(ProceedsConstants.ZERO);
		BigDecimal totalNebikiKen_6 = new BigDecimal(ProceedsConstants.ZERO);
		BigDecimal totalNebikiKen_7 = new BigDecimal(ProceedsConstants.ZERO);
		BigDecimal totalNebikiKen_8 = new BigDecimal(ProceedsConstants.ZERO);
		BigDecimal totalNebikiKen_9 = new BigDecimal(ProceedsConstants.ZERO);
		BigDecimal totalNebikiKin_1 = new BigDecimal(ProceedsConstants.ZERO);
		BigDecimal totalNebikiKin_2 = new BigDecimal(ProceedsConstants.ZERO);
		BigDecimal totalNebikiKin_3 = new BigDecimal(ProceedsConstants.ZERO);
		BigDecimal totalNebikiKin_4 = new BigDecimal(ProceedsConstants.ZERO);
		BigDecimal totalNebikiKin_5 = new BigDecimal(ProceedsConstants.ZERO);
		BigDecimal totalNebikiKin_6 = new BigDecimal(ProceedsConstants.ZERO);
		BigDecimal totalNebikiKin_7 = new BigDecimal(ProceedsConstants.ZERO);
		BigDecimal totalNebikiKin_8 = new BigDecimal(ProceedsConstants.ZERO);
		BigDecimal totalNebikiKin_9 = new BigDecimal(ProceedsConstants.ZERO);
		BigDecimal totalNebikiTax_1 = new BigDecimal(ProceedsConstants.ZERO);
		BigDecimal totalNebikiTax_2 = new BigDecimal(ProceedsConstants.ZERO);
		BigDecimal totalNebikiTax_3 = new BigDecimal(ProceedsConstants.ZERO);
		BigDecimal totalNebikiTax_4 = new BigDecimal(ProceedsConstants.ZERO);
		BigDecimal totalNebikiTax_5 = new BigDecimal(ProceedsConstants.ZERO);
		BigDecimal totalNebikiTax_6 = new BigDecimal(ProceedsConstants.ZERO);
		BigDecimal totalNebikiTax_7 = new BigDecimal(ProceedsConstants.ZERO);
		BigDecimal totalNebikiTax_8 = new BigDecimal(ProceedsConstants.ZERO);
		BigDecimal totalNebikiTax_9 = new BigDecimal(ProceedsConstants.ZERO);
    	
		//取得した値引データの値の合計を計算
    	for(Iterator it = nebikiDataList.iterator(); it.hasNext();) {
    		
    		NebikiInfo info = (NebikiInfo) it.next();
    		
    		totalNebikiKen_1 = totalNebikiKen_1.add(info.getNebikiKen_1());
    		totalNebikiKen_2 = totalNebikiKen_2.add(info.getNebikiKen_2());
    		totalNebikiKen_3 = totalNebikiKen_3.add(info.getNebikiKen_3());
    		totalNebikiKen_4 = totalNebikiKen_4.add(info.getNebikiKen_4());
    		totalNebikiKen_5 = totalNebikiKen_5.add(info.getNebikiKen_5());
    		totalNebikiKen_6 = totalNebikiKen_6.add(info.getNebikiKen_6());
    		totalNebikiKen_7 = totalNebikiKen_7.add(info.getNebikiKen_7());
    		totalNebikiKen_8 = totalNebikiKen_8.add(info.getNebikiKen_8());
    		totalNebikiKen_9 = totalNebikiKen_9.add(info.getNebikiKen_9());
    		totalNebikiKin_1 = totalNebikiKin_1.add(info.getNebikiKin_1());
    		totalNebikiKin_2 = totalNebikiKin_2.add(info.getNebikiKin_2());
    		totalNebikiKin_3 = totalNebikiKin_3.add(info.getNebikiKin_3());
    		totalNebikiKin_4 = totalNebikiKin_4.add(info.getNebikiKin_4());
    		totalNebikiKin_5 = totalNebikiKin_5.add(info.getNebikiKin_5());
    		totalNebikiKin_6 = totalNebikiKin_6.add(info.getNebikiKin_6());
    		totalNebikiKin_7 = totalNebikiKin_7.add(info.getNebikiKin_7());
    		totalNebikiKin_8 = totalNebikiKin_8.add(info.getNebikiKin_8());
    		totalNebikiKin_9 = totalNebikiKin_9.add(info.getNebikiKin_9());
    		totalNebikiTax_1 = totalNebikiTax_1.add(info.getNebikiTax_1());
    		totalNebikiTax_2 = totalNebikiTax_2.add(info.getNebikiTax_2());
    		totalNebikiTax_3 = totalNebikiTax_3.add(info.getNebikiTax_3());
    		totalNebikiTax_4 = totalNebikiTax_4.add(info.getNebikiTax_4());
    		totalNebikiTax_5 = totalNebikiTax_5.add(info.getNebikiTax_5());
    		totalNebikiTax_6 = totalNebikiTax_6.add(info.getNebikiTax_6());
    		totalNebikiTax_7 = totalNebikiTax_7.add(info.getNebikiTax_7());
    		totalNebikiTax_8 = totalNebikiTax_8.add(info.getNebikiTax_8());
    		totalNebikiTax_9 = totalNebikiTax_9.add(info.getNebikiTax_9());
    		
    	}
    	//合計値をセット
    	NebikiInfo infoData = new NebikiInfo();
    	infoData.setEigyoDt(ProceedsConstants.EMPTY);
    	infoData.setNebikiKen_1(totalNebikiKen_1);
		infoData.setNebikiKen_2(totalNebikiKen_2);
		infoData.setNebikiKen_3(totalNebikiKen_3);
		infoData.setNebikiKen_4(totalNebikiKen_4);
		infoData.setNebikiKen_5(totalNebikiKen_5);
		infoData.setNebikiKen_6(totalNebikiKen_6);
		infoData.setNebikiKen_7(totalNebikiKen_7);
		infoData.setNebikiKen_8(totalNebikiKen_8);
		infoData.setNebikiKen_9(totalNebikiKen_9);
		infoData.setNebikiKin_1(totalNebikiKin_1);
		infoData.setNebikiKin_2(totalNebikiKin_2);
		infoData.setNebikiKin_3(totalNebikiKin_3);
		infoData.setNebikiKin_4(totalNebikiKin_4);
		infoData.setNebikiKin_5(totalNebikiKin_5);
		infoData.setNebikiKin_6(totalNebikiKin_6);
		infoData.setNebikiKin_7(totalNebikiKin_7);
		infoData.setNebikiKin_8(totalNebikiKin_8);
		infoData.setNebikiKin_9(totalNebikiKin_9);
		infoData.setNebikiTax_1(totalNebikiTax_1);
		infoData.setNebikiTax_2(totalNebikiTax_2);
		infoData.setNebikiTax_3(totalNebikiTax_3);
		infoData.setNebikiTax_4(totalNebikiTax_4);
		infoData.setNebikiTax_5(totalNebikiTax_5);
		infoData.setNebikiTax_6(totalNebikiTax_6);
		infoData.setNebikiTax_7(totalNebikiTax_7);
		infoData.setNebikiTax_8(totalNebikiTax_8);
		infoData.setNebikiTax_9(totalNebikiTax_9);
		infoData.setRClass(ProceedsConstants.EMPTY);
    	
    	return infoData;
    }
    
    /**
     * 値引情報の各項目データを０に設定する。
     * @param list 売上金管理月報情報リスト
     * @return tempList　値引情報リスト
     */
	private List setDefaultNebikiData(List list) {
		
		List tempList = new ArrayList();        	
		//売上金で取得した営業日分まで、値引データに"０"設定
		for (Iterator it = list.iterator(); it.hasNext();) {
		
			ProceedsManageGepoInfo info = (ProceedsManageGepoInfo) it.next();
			
			NebikiInfo nebiki =  new NebikiInfo();
		
			nebiki.setEigyoDt(info.getEigyoDt());
			nebiki.setNebikiKen_1(new BigDecimal(ProceedsConstants.ZERO));
			nebiki.setNebikiKen_2(new BigDecimal(ProceedsConstants.ZERO));
			nebiki.setNebikiKen_3(new BigDecimal(ProceedsConstants.ZERO));
			nebiki.setNebikiKen_4(new BigDecimal(ProceedsConstants.ZERO));
			nebiki.setNebikiKen_5(new BigDecimal(ProceedsConstants.ZERO));
			nebiki.setNebikiKen_6(new BigDecimal(ProceedsConstants.ZERO));
			nebiki.setNebikiKen_7(new BigDecimal(ProceedsConstants.ZERO));
			nebiki.setNebikiKen_8(new BigDecimal(ProceedsConstants.ZERO));
			nebiki.setNebikiKen_9(new BigDecimal(ProceedsConstants.ZERO));
			nebiki.setNebikiKin_1(new BigDecimal(ProceedsConstants.ZERO));
			nebiki.setNebikiKin_2(new BigDecimal(ProceedsConstants.ZERO));
			nebiki.setNebikiKin_3(new BigDecimal(ProceedsConstants.ZERO));
			nebiki.setNebikiKin_4(new BigDecimal(ProceedsConstants.ZERO));
			nebiki.setNebikiKin_5(new BigDecimal(ProceedsConstants.ZERO));
			nebiki.setNebikiKin_6(new BigDecimal(ProceedsConstants.ZERO));
			nebiki.setNebikiKin_7(new BigDecimal(ProceedsConstants.ZERO));
			nebiki.setNebikiKin_8(new BigDecimal(ProceedsConstants.ZERO));
			nebiki.setNebikiKin_9(new BigDecimal(ProceedsConstants.ZERO));
			nebiki.setNebikiTax_1(new BigDecimal(ProceedsConstants.ZERO));
			nebiki.setNebikiTax_2(new BigDecimal(ProceedsConstants.ZERO));
			nebiki.setNebikiTax_3(new BigDecimal(ProceedsConstants.ZERO));
			nebiki.setNebikiTax_4(new BigDecimal(ProceedsConstants.ZERO));
			nebiki.setNebikiTax_5(new BigDecimal(ProceedsConstants.ZERO));
			nebiki.setNebikiTax_6(new BigDecimal(ProceedsConstants.ZERO));
			nebiki.setNebikiTax_7(new BigDecimal(ProceedsConstants.ZERO));
			nebiki.setNebikiTax_8(new BigDecimal(ProceedsConstants.ZERO));
			nebiki.setNebikiTax_9(new BigDecimal(ProceedsConstants.ZERO));
			nebiki.setRClass(ProceedsConstants.EMPTY);
			tempList.add(nebiki);
		 }
		return tempList;
	}
    
    /**
	 * 入力チェックをする
     * @param userType  ユーザタイプ
     * @param userId    ユーザID
     * @param companyCd 会社コード
     * @param miseCd    店コード
     * @param list      売上金管理月報情報
     * @param taishoYM  対象年月
	 */
	private void validate(String userType, String userId, String companyCd,
			String miseCd, String taishoYM, List list) {
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
		if (list == null || list.isEmpty()) {
			throw new NotNullException("売上金管理月報の日付情報");
		}
	}
    
    /**
     * 値引区分名称を設定する
     * @param num            番号
     * @param kaikeiInfoList 値引区分名称リスト
     * @return String        値引区分名称
     */
    private String getNebikiKbnName(int num, List nebikiInfoList) {
        
    	String srtNum = ProceedsCommon.getStringNum(num, 2);
        
        if (nebikiInfoList != null && !nebikiInfoList.isEmpty()) {
            for (Iterator it = nebikiInfoList.iterator(); it.hasNext();) {
                MstNebikiKbnInfo info = (MstNebikiKbnInfo) it.next();
                if (srtNum.equals(info.getNkbnCd())) {
                    return info.getNkbnName();
                }
            }
        }
        return ProceedsConstants.LABEL_NEBIKI + String.valueOf(num);
    }

    /**
     * 値引区分情報Daoを取得する
     * @return mstNebikiKbnInfoDao 値引区分情報Dao
     */
	public MstNebikiKbnInfoDao getMstNebikiKbnInfoDao() {
		return mstNebikiKbnInfoDao;
	}

	/**
	 * 値引区分情報Daoを設定する
	 * @param mstNebikiKbnInfoDao 値引区分情報Dao
	 */
	public void setMstNebikiKbnInfoDao(MstNebikiKbnInfoDao mstNebikiKbnInfoDao) {
		this.mstNebikiKbnInfoDao = mstNebikiKbnInfoDao;
	}

	/**
	 * 値引情報Daoを取得する
	 * @return nebikiInfoDao 値引情報Dao
	 */
	public NebikiInfoDao getNebikiInfoDao() {
		return nebikiInfoDao;
	}

	/**
	 * 値引情報Daoを設定する
	 * @param nebikiInfoDao 値引情報Dao
	 */
	public void setNebikiInfoDao(NebikiInfoDao nebikiInfoDao) {
		this.nebikiInfoDao = nebikiInfoDao;
	}


}