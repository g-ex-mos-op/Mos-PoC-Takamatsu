/**
 * 
 */
package jp.co.isid.mos.bird.config.urisokuregist.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.common.entity.UILists;
import jp.co.isid.mos.bird.config.urisokuregist.dto.RequestDto;
import jp.co.isid.mos.bird.config.urisokuregist.dto.SessionDto;
import jp.co.isid.mos.bird.config.urisokuregist.entity.UIUsrMenu;
import jp.co.isid.mos.bird.config.urisokuregist.logic.CopyMenuDataLogic;
import jp.co.isid.mos.bird.config.urisokuregist.logic.GetShohinMenuLogic;
import jp.co.isid.mos.bird.config.urisokuregist.util.UrisokuRegistUtil;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.util.DateManager;

/**
 * [機能設定]【売上速報設定】
 * 前月分メニュー情報コピー処理
 * ロジック
 * 
 * 作成日:2008/08/28
 * @author xkinu
 *
 */
public class CopyMenuDataLogicImpl implements CopyMenuDataLogic {
    /** ロジックID */
    public static final String LOGIC_ID = UrisokuRegistUtil.SCREEN_ID+"L05";
	/** LOGIC【売上速報レポート用商品メニューの取得】*/
	private GetShohinMenuLogic urisokuRegistGetShohinMenuLogic;

	/**
	 * 実行処理
	 * 
	 * @see jp.co.isid.mos.bird.config.urisokuregist.logic.CopyMenuDataLogic#execute(java.lang.String, java.lang.String)
	 */
	public List execute(SessionDto sessionDto, RequestDto requestDto) {
		//２．変数.前月の年月へDTO【自画面Session】.対象年月から1月引いた値を設定します。
		String lastMonth = null;
		try {
			lastMonth = DateManager.getPrevMonth(sessionDto.getTaishoYm(), 1);
			
		}
		catch(Exception ex) {
    		throw new FtlSystemException("【売上速報設定】前月分メニュー情報コピー処理ロジッククラス.前月分コピー処理"
    				, "DateManager.getPrevMonthメソッド処理で例外が発生しました。"
    				, ex);			
		}
		//３．LOGIC【売上速報レポート用商品メニューの取得】を実行し、戻り値List[[前月分商品メニュー情報]]を取得します。
		List listLastMonthData = getUrisokuRegistGetShohinMenuLogic().execute(
				lastMonth, requestDto.getSelectedTabSub());
		//４．前月分コピー先対象データを取得します。
		UILists frame = UrisokuRegistUtil.getFrameData(sessionDto, requestDto.getSelectedTabSub());
		List listSaki = frame.getListData();
		//５．コピー先対象データ.List[[データリスト]]へList[[前月分商品メニュー情報]]の値を設定します。
		for(int i=0; i<listSaki.size(); i++) {
			UIUsrMenu copyMoto = (UIUsrMenu)listLastMonthData.get(i);
			UIUsrMenu copySaki = (UIUsrMenu)listSaki.get(i);
			copySaki.setMenuCd(copyMoto.getMenuCd());
			copySaki.setMenuNameKj(copyMoto.getMenuNameKj());
			copySaki.setDispWord(copyMoto.getDispWord());
			copySaki.setDataKbn(copyMoto.getDataKbn());
		}
		return null;
	}
	/**
	 * @return urisokuRegistGetShohinMenuLogic を戻します。
	 */
	public GetShohinMenuLogic getUrisokuRegistGetShohinMenuLogic() {
		return urisokuRegistGetShohinMenuLogic;
	}

	/**
	 * @param urisokuRegistGetShohinMenuLogic を クラス変数urisokuRegistGetShohinMenuLogicへ設定します。
	 */
	public void setUrisokuRegistGetShohinMenuLogic(
			GetShohinMenuLogic urisokuRegistGetShohinMenuLogic) {
		this.urisokuRegistGetShohinMenuLogic = urisokuRegistGetShohinMenuLogic;
	}


}
