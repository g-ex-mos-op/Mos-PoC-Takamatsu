/**
 * 
 */
package jp.co.isid.mos.bird.commonform.screensearch.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.commonform.screensearch.dao.UISearchInfoDao;
import jp.co.isid.mos.bird.commonform.screensearch.dto.RequestDto;
import jp.co.isid.mos.bird.commonform.screensearch.logic.SearchLogic;
import jp.co.isid.mos.bird.commonform.screensearch.util.ScreenSearchUtil;
import jp.co.isid.mos.bird.framework.exception.MissingDataException;
import jp.co.isid.mos.bird.framework.exception.NoInputException;
import jp.co.isid.mos.bird.framework.exception.NotExistException;

/**
 * [呼び出し専用共通]メニュー選択画面
 * 
 * 検索処理と検索情報の取得
 * ロジック
 * 
 * 作成日:2008/08/20
 * @author xkinu
 *
 */
public class SearchLogicImpl implements SearchLogic {
    /** ロジックID */
    public static final String LOGIC_ID = ScreenSearchUtil.SCREEN_ID+"L02";
	/** DAO【検索情報】*/
	private UISearchInfoDao screenSearchUIInfoDao;
	
	/**
	 * 実行処理
	 * @see jp.co.isid.mos.bird.commonform.menusearch.logic.SearchLogic#execute(jp.co.isid.mos.bird.commonform.menusearch.dto.ScreenSearchDto)
	 */
	public List execute(RequestDto requestDto) {
        //１．事前条件判断処理
        validate(requestDto);
        //検索対象　文字列
        String searchWord = requestDto.getSearchWord();
       	searchWord = "%"+ searchWord.trim()+"%";
        //２．Dao【検索情報】．検索 を実行し、
        //    実行結果[[検索情報]]を取得する。
        List list = getScreenSearchUIInfoDao().select(requestDto.getUserId(), searchWord);
        
        //３．処理２の結果件数が０件の場合、下記のエラーを実行する。
        //Exception:    MSG【E0103】(”該当データ”が存在しません。）
        if(list == null || list.size() == 0){
            throw new NotExistException("該当データ");
        }
        //４．[[検索情報]]をリターンする。
        return list;
	}
    /**
     * 事前条件処理
     * 
     * @param dto
     */
    private void validate(RequestDto requestDto)
    {
        if (requestDto == null) {
            throw new MissingDataException("リクエスト情報");
        }
        //検索対象　文字列
        String searchWord = requestDto.getSearchWord();
        if (CommonUtil.isNull(searchWord)) {
            //MSG【E0507】’を入力してください。'
            throw new NoInputException("検索文字", "searchWord", 0);
        }
    }
	/**
	 * DAO【検索情報】取得処理
	 * 
	 * @return screenSearchUIInfoDao を戻します。
	 */
	public UISearchInfoDao getScreenSearchUIInfoDao() {
		return screenSearchUIInfoDao;
	}
	/**
	 * DAO【検索情報】設定処理
	 * 
	 * @param dao を クラス変数screenSearchUIInfoDaoへ設定します。
	 */
	public void setScreenSearchUIInfoDao(
			UISearchInfoDao dao) {
		this.screenSearchUIInfoDao = dao;
	}

}