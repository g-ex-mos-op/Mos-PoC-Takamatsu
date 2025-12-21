package jp.co.isid.mos.bird.storemanage.mlcompletionregist.logic.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.NotExistException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.storemanage.mlcompletionregist.dao.CodSibuDao;
import jp.co.isid.mos.bird.storemanage.mlcompletionregist.dto.MlCompletionregistDto;
import jp.co.isid.mos.bird.storemanage.mlcompletionregist.logic.MlCompletionregistLogic;

/**
 * マスタライセンス研修修了登録
 * 支部データ検索ロジック
 * 
 * 条件画面会社プルダウン変更時出力
 * @author xkinu
 */
public class SibuListLogicImpl implements MlCompletionregistLogic {
    
    /* ロジックID */    
    public static final String LOGIC_ID = "BSM006L05";
    
    /*【DAO】支部コード*/
    private CodSibuDao codSibuDao;
    
    /**
     * 事前条件処理
     * 
     * @param dto
     * @throws ApplicationException
     */
    public void validate(MlCompletionregistDto dto) throws ApplicationException {
        // 画面DTO
        if (dto == null) {
            throw new NotNullException("画面DTO");
        }
        //会社コード
        String companyCd = dto.getCompanyCd();
        if (isNull(companyCd)) {
            throw new NotNullException("会社コード");
        }
    }
    
    /**
     * 条件画面出力データ検索を行う
     * 
     * 条件画面に必要な全てのデータを戻します。
     * 
     * @param MlCompletionregistDto dto 画面データ保持クラス
     * @exception ApplicationException
     */
    public Map execute(MlCompletionregistDto dto) throws ApplicationException {
        
        //返却マップ
        Map returnMap = new HashMap();

        // 支部データ取得
        returnMap.put("listSibu", executeListSibu(dto.getCompanyCd()));
        
        //検索データを戻す。
        return returnMap;
    }
    /**
     * 支部の種類を取得
     * 
     * @param String 会社コード
     * @return List 支部データ
     * @exception ApplicationException
     */
    private List executeListSibu(final String companyCd) throws ApplicationException {
        List listSibu = getSibuDao().select(companyCd);
        
        if (listSibu == null || listSibu.size() == 0) {
            throw new NotExistException("支部情報");
        }
        return listSibu;
    }
    /**
     * 支部情報Dao取得処理
     * @return　CodSibuDao
     */
	public CodSibuDao getSibuDao() {
		return codSibuDao;
	}
	/**
	 * 支部情報Dao設定処理
	 * 
	 * @param CodSibuDao 支部情報 
	 */
	public void setSibuDao(CodSibuDao sibuDao) {
		this.codSibuDao = sibuDao;
	}
    /**
     * Null判断処理
     * @param str
     * @return boolean true：空かNullの場合
     */
    private boolean isNull(String str) {
        return (str == null || "".equals(str.trim())) ? true : false;
    }
}
