/**
 * 
 */
package jp.co.isid.mos.bird.config.urisokuregist.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.config.urisokuregist.dao.UIUsrMenuDao;
import jp.co.isid.mos.bird.config.urisokuregist.logic.GetShohinMenuLogic;
import jp.co.isid.mos.bird.config.urisokuregist.util.UrisokuRegistUtil;
import jp.co.isid.mos.bird.framework.exception.MissingDataException;

/**
 * [機能設定]【売上速報設定】
 * 商品メニュー取得ロジック
 * 
 * 作成日:2008/08/06
 * @author xkinu
 *
 */
public class GetShohinMenuLogicImpl implements GetShohinMenuLogic {
    /** ロジックID */
    public static final String LOGIC_ID = UrisokuRegistUtil.SCREEN_ID+"L01";

    /** DAO【売上速報レポート用商品グループ】*/
	private UIUsrMenuDao urisokuRegistUIUsrMenuDao;
	
	/* (非 Javadoc)
	 * @see jp.co.isid.mos.bird.config.urisokuregist.logic.GetShohinMenuLogic#execute(java.lang.String)
	 */
	public List execute(String taishoYm, String frameKbn) {
        //１．事前条件判断処理
        validate(taishoYm, frameKbn);
        //２．変数List[[編集情報]]へDAO【売上速報レポート用商品グループ】を実行し、取得した結果を代入します。
        List listMenuData = getUrisokuRegistUIUsrMenuDao().select(taishoYm, frameKbn);
        //３．処理２のList[[編集情報]]件数が0件の場合、下記の処理を行います。
        if(listMenuData.size()==0) {
        	listMenuData = UrisokuRegistUtil.createListUIUsrMenu(taishoYm, frameKbn);
        }
        
        //４．処理２のList[[編集情報]]をリターンします。
		return listMenuData;
	}
    /**
     * 事前条件処理
     * 
     * @param taishoYm
     * @param frameKbn
     * @throws MissingDataException
     */
    private void validate(final String taishoYm, String frameKbn) {
        if(CommonUtil.isNull(taishoYm)){
            //MSG【E0202】(がありません。)
            throw new MissingDataException("対象レポート年月");
        }
        if(CommonUtil.isNull(frameKbn)){
            //MSG【E0202】(がありません。)
            throw new MissingDataException("フレーム区分");
        }
    }
	/**
	 * @return urisokuRegistUIUsrMenuDao を戻します。
	 */
	public UIUsrMenuDao getUrisokuRegistUIUsrMenuDao() {
		return urisokuRegistUIUsrMenuDao;
	}
	/**
	 * @param urisokuRegistUIUsrMenuDao を クラス変数urisokuRegistUIUsrMenuDaoへ設定します。
	 */
	public void setUrisokuRegistUIUsrMenuDao(UIUsrMenuDao urisokuRegistUIUsrMenuDao) {
		this.urisokuRegistUIUsrMenuDao = urisokuRegistUIUsrMenuDao;
	}

}
