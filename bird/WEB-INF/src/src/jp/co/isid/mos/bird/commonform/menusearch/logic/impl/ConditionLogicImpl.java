/**
 * 
 */
package jp.co.isid.mos.bird.commonform.menusearch.logic.impl;

import jp.co.isid.mos.bird.commonform.menusearch.dao.CodMenuBunruiDao;
import jp.co.isid.mos.bird.commonform.menusearch.dto.RequestDto;
import jp.co.isid.mos.bird.commonform.menusearch.dto.SessionDto;
import jp.co.isid.mos.bird.commonform.menusearch.logic.ConditionLogic;
import jp.co.isid.mos.bird.commonform.menusearch.util.MenuSearchUtil;
import jp.co.isid.mos.bird.framework.exception.MissingDataException;

/**
 * [呼び出し専用共通]メニュー選択画面
 * 
 * 条件項目情報の取得と設定
 * ロジック
 * 
 * 作成日:2008/08/20
 * @author xkinu
 *
 */
public class ConditionLogicImpl implements ConditionLogic {
    /** ロジックID */
    public static final String LOGIC_ID = MenuSearchUtil.SCREEN_ID+"L01";

	/** DAO【メニュー分類】*/
	private CodMenuBunruiDao menuSearchCodMenuBunruiDao;

	/**
	 *  実行処理
	 * 
	 * @see jp.co.isid.mos.bird.commonform.menusearch.logic.ConditionLogic#execute(jp.co.isid.mos.bird.commonform.menusearch.dto.SessionDto, jp.co.isid.mos.bird.commonform.menusearch.dto.RequestDto)
	 */
	public void execute(SessionDto sessionDto, RequestDto requestDto) {
        //１．事前条件判断処理
        validate(sessionDto, requestDto);
        //２．DAO【メニュー分類】検索を実行し、検索結果[[メニュー分類]]を取得します。
        //３．DTO【自画面Session】.List[[メニュー分類]]へ検索結果[[メニュー分類]]を設定します。
        sessionDto.setListMenuBunrui(getMenuSearchCodMenuBunruiDao().select());
        //４．DTO【自画面Request】.販売終了メニューフラグへtrue(含む)を設定します。
        requestDto.setHanbaiEndIn(false);
        //５．DTO【自画面Request】.ソート順へ"コード"を設定します。
        requestDto.setSortType(MenuSearchUtil.SORT_TYPE_CODE);
        //６．DTO【自画面Request】.LIKE時メニュー名称指定位置へ"から始まる"を設定します。
        requestDto.setLikePoint(MenuSearchUtil.LIKE_POINT_LEFT);
	}
    /**
     * 事前条件処理
     * 
     * @param dto
     */
    private void validate(SessionDto sessionDto, RequestDto requestDto)
    {
        if (sessionDto == null) {
            throw new MissingDataException("セッション情報");
        }
        if (requestDto == null) {
            throw new MissingDataException("リクエスト情報");
        }
    }
	/**
	 * DAO【メニュー分類】取得処理
	 * @return menuSearchCodMenuBunruiDao を戻します。
	 */
	public CodMenuBunruiDao getMenuSearchCodMenuBunruiDao() {
		return menuSearchCodMenuBunruiDao;
	}
	/**
	 * DAO【メニュー分類】設定処理
	 * 
	 * @param menuSearchCodMenuBunruiDao を クラス変数menuSearchCodMenuBunruiDaoへ設定します。
	 */
	public void setMenuSearchCodMenuBunruiDao(
			CodMenuBunruiDao menuSearchCodMenuBunruiDao) {
		this.menuSearchCodMenuBunruiDao = menuSearchCodMenuBunruiDao;
	}

}
