package jp.co.isid.mos.bird.entry.mlviewlist.logic.impl;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import jp.co.isid.mos.bird.entry.mlviewlist.dao.UIMlEntryCsvListDao;
import jp.co.isid.mos.bird.entry.mlviewlist.dao.UIMlEntryListDao;
import jp.co.isid.mos.bird.entry.mlviewlist.logic.GetMlEntryListLogic;
import jp.co.isid.mos.bird.framework.exception.NoInputException;

/**
 * マスターライセンスエントリー申込状況一覧取得ロジック
 * @author Aspac
 */
public class GetMlEntryListLogicImpl implements GetMlEntryListLogic {

    /* ロジックID */
    public static final String LOGIC_ID = "BEN009L02";

    /**
     * マスターライセンス申込状況一覧情報（UIMlEntryListDao）
     */
    private UIMlEntryListDao uIMlEntryListDao;
    
    /**
     * マスターライセンス申込状況一覧情報（UIMlEntryCsvListDao）
     */
    private UIMlEntryCsvListDao uIMlEntryCsvListDao;
    
	/**
	 * マスターライセンス申込状況一覧Daoの設定
	 * @return codCompanyJohoDao を戻します。
	 */
	public UIMlEntryListDao getUIMlEntryListDao() {
		return uIMlEntryListDao;
	}
	/**
	 * マスターライセンス申込状況一覧Daoの設定
	 * @param UIMlEntryListDao uIMlEntryListDao を設定。
	 */
	public void setUIMlEntryListDao(UIMlEntryListDao uIMlEntryListDao) {
		this.uIMlEntryListDao = uIMlEntryListDao;
	}
    
    /**
     * マスターライセンス申込状況一覧Daoの設定
     * @return codCompanyJohoDao を戻します。
     */
    public UIMlEntryCsvListDao getUIMlEntryCsvListDao() {
        return uIMlEntryCsvListDao;
    }
    /**
     * マスターライセンス申込状況一覧Daoの設定
     * @param UIMlEntryCsvListDao uIMlEntryCsvListDao を設定。
     */
    public void setUIMlEntryCsvListDao(UIMlEntryCsvListDao uIMlEntryCsvListDao) {
        this.uIMlEntryCsvListDao = uIMlEntryCsvListDao;
    }

	/**
	 * マスターライセンス申込状況一覧の検索
	 * @return 検索結果
	 */
	public List execute(String kbn, String sysdate, String entryCd, String entryYear, String entryKai, String searchSelectFlg, String sibuCd, String onerCd, String miseCd) {
        // 入力チェック
        validate(searchSelectFlg, sibuCd, onerCd, miseCd);
        
        List mlList;
        
        //エントリー年をもとに再エントリー基準年度を算出
        List listReEntryBaseYear = new ArrayList();
        listReEntryBaseYear.add(entryYear);
        listReEntryBaseYear.add((new BigDecimal(entryYear)).subtract(new BigDecimal("1")).toString());
        
        if(!kbn.equals("CSV")){
            // マスターライセンス申込状況一覧取得
            mlList = uIMlEntryListDao.getMlEntryInfo(sysdate, entryCd, entryYear, entryKai, searchSelectFlg, sibuCd, onerCd, miseCd);
        }else{
            // CSVマスターライセンス申込状況一覧データ取得
//            mlList = uIMlEntryCsvListDao.getMlEntryCsvInfo(sysdate, entryCd, entryYear, entryKai, searchSelectFlg, sibuCd, onerCd, miseCd);
            mlList = uIMlEntryCsvListDao.getMlEntryCsvInfo(sysdate, entryCd, entryYear, entryKai, searchSelectFlg, sibuCd, onerCd, miseCd, listReEntryBaseYear);
        }

		return mlList;
	}
    
    /**
     * パラメータチェック
     */
    private void validate(String searchSelectFlg, String sibuCd, String onerCd, String miseCd) {
        if ("0".equals(searchSelectFlg)) {
            if (isNull(sibuCd)) {
                throw new NoInputException("支部", "sitenS", null);
            }
        }
        else if ("1".equals(searchSelectFlg)) {
            if (isNull(onerCd)) {
                throw new NoInputException("オーナーコード", "onerT", null);
            }
        }
        else if ("2".equals(searchSelectFlg)) {
            if (isNull(miseCd)) {
                throw new NoInputException("店コード", "miseT", null);
            }
        }
    }
    
    /**
     * null、空文字チェック
     */
    private boolean isNull(String value) {
        return value == null || "".equals(value.trim()) ? true : false;
    }
}
