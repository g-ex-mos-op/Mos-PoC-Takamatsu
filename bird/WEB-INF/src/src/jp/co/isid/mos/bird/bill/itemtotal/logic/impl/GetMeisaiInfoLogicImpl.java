/*
 * 作成日: 2006/08/
 *
 */
package jp.co.isid.mos.bird.bill.itemtotal.logic.impl;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import jp.co.isid.mos.bird.bill.itemtotal.dao.UIMeisaiInfoDao;
import jp.co.isid.mos.bird.bill.itemtotal.logic.GetMeisaiInfoLogic;
import jp.co.isid.mos.bird.framework.exception.InvalidDataException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.util.Converter;

/**
 * オーナーコード報取得ロジック
 *
 * @author xlee
 */
public class GetMeisaiInfoLogicImpl implements GetMeisaiInfoLogic {

    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BBS011L06";

    /**
     * 商品別明細情報DAOを取得します。
     */
    private UIMeisaiInfoDao uiMeisaiInfoDao;

    /**
     * 商品別明細情報DAOを取得します。
     * @return 商品別明細情報DAO
     */
    public UIMeisaiInfoDao getUIMeisaiInfoDao() {
        return uiMeisaiInfoDao;
    }

    /**
     * 商品別明細情報DAOを設定します。
     * @param uiMeisaiInfoDao 商品別明細情報DAO
     */
    public void setUIMeisaiInfoDao(UIMeisaiInfoDao uiMeisaiInfoDao) {
        this.uiMeisaiInfoDao = uiMeisaiInfoDao;
    }

    /**
     * 商品別明細情報を取得
     * @param　miseCd　　店コード
     * @param　shoJituCd　実商品コード
     * @param　urikakeYm　売掛年月
     * @return  前年同月対象当年明細（同月売上）
     * @throws ParseException
     */
    public List execute(String miseCd, String shoJituCd, String condYm){

    	//エラー処理：
    	if(isNull(miseCd)){
            throw new NotNullException("店コード"); //E0506 店コード
        }

    	//エラー処理：
    	if(isNull(shoJituCd)){
            throw new NotNullException("実商品コード"); //E0506 実商品コード
        }

    	//エラー処理：
    	if(isNull(condYm)){
            throw new NotNullException("対象年月"); //E0506 対象年月
        }
		// 対象年月
		Date dt;
		Date dtAfter;
		String uriendYm = null;
		try {
			dt = Converter.stringToDate("yyyyMM", condYm);
			Calendar rightNow = Calendar.getInstance();
			rightNow.setTime(dt);
			rightNow.add(Calendar.MONTH, +1);
			rightNow.add(Calendar.DAY_OF_YEAR, -1);
			dtAfter = rightNow.getTime();
			uriendYm = Converter.dateToString("yyyyMMdd", dtAfter);
		} catch (Exception e) {
			throw new InvalidDataException("対象年月");
		}

    	List tmpResult = (List) getUIMeisaiInfoDao().getMeisaiInfo(miseCd, shoJituCd, condYm,uriendYm);
        return tmpResult;
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
