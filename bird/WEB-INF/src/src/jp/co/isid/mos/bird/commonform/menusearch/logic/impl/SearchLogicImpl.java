/**
 * 
 */
package jp.co.isid.mos.bird.commonform.menusearch.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.commonform.common.util.IndexSearchUtil;
import jp.co.isid.mos.bird.commonform.menusearch.dao.UIMenuInfoDao;
import jp.co.isid.mos.bird.commonform.menusearch.dto.RequestDto;
import jp.co.isid.mos.bird.commonform.menusearch.logic.SearchLogic;
import jp.co.isid.mos.bird.commonform.menusearch.util.MenuSearchUtil;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.exception.MissingDataException;
import jp.co.isid.mos.bird.framework.exception.NoInputException;
import jp.co.isid.mos.bird.framework.exception.NotExistException;
import jp.co.isid.mos.bird.framework.util.formatter.CodeFormatter;
import jp.co.isid.mos.bird.framework.util.verifier.HankakuEisujiVerifier;

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
    public static final String LOGIC_ID = MenuSearchUtil.SCREEN_ID+"L02";
	/** DAO【社内メニュー情報】*/
	private UIMenuInfoDao menuSearchUIMenuInfoDao;
	
	/**
	 * 実行処理
	 * @see jp.co.isid.mos.bird.commonform.menusearch.logic.SearchLogic#execute(jp.co.isid.mos.bird.commonform.menusearch.dto.MenuSearchDto)
	 */
	public List execute(RequestDto requestDto) {
        //１．事前条件判断処理
        validate(requestDto);
        String searchType =null;
        //検索対象　開始コード
        String cdFrom = requestDto.getMenuCdFrom();
        //検索対象　終了コード
        String cdTo = requestDto.getMenuCdTo();
        //検索対象　メニュー名称
        String name1 = requestDto.getMenuNameKj();
        String name2 = null;
        String name3 = null;
        String name4 = null;
        
        if(CommonUtil.isNull(name1)){
        	if(!CommonUtil.isNull(requestDto.getSearch50Key())){
        		searchType ="50";
        		List indexSearchList = IndexSearchUtil
                .getIndexSearchKeyList(new Integer(requestDto.getSearch50Key()).intValue());
        		if(indexSearchList.size()>=1) {
	            	//50音検索の場合、頭文字検索として設定します。
	        		name1 = ((String)indexSearchList.get(0)).trim()+"%";
        		}
        		if(indexSearchList.size()>=2) {
	            	//50音検索の場合、頭文字検索として設定します。
	        		name2 = ((String)indexSearchList.get(1)).trim()+"%";
        		}
        		if(indexSearchList.size()>=3) {
	            	//50音検索の場合、頭文字検索として設定します。
	        		name3 = ((String)indexSearchList.get(2)).trim()+"%";
        		}
        		if(indexSearchList.size()>=4) {
	            	//50音検索の場合、頭文字検索として設定します。
	        		name4 = ((String)indexSearchList.get(3)).trim()+"%";
        		}
        	}
        }else{
        	name1 = "%"+ name1.trim()+"%";
        }
        //検索対象　システム日付
        String sysDate = requestDto.getSysDate();
        //２．Dao【社内メニュー情報】．検索 を実行し、
        //    実行結果[[社内メニュー情報]]を取得する。
        List list = getMenuSearchUIMenuInfoDao().select(searchType
        		, cdFrom, cdTo
        		, name1, name2, name3, name4
        		, requestDto.getMenuBunrui(), requestDto.getSortType(), sysDate);
        
        //３．処理２の結果件数が０件の場合、下記のエラーを実行する。
        //Exception:    MSG【E0103】(”該当データ”が存在しません。）
        if(list == null || list.size() == 0){
            throw new NotExistException("該当データ");
        }
        //４．[[社内メニュー情報]]をリターンする。
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
        //検索対象　開始コード
        String cdFrom = requestDto.getMenuCdFrom();
        //検索対象　終了コード
        String cdTo = requestDto.getMenuCdTo();
        //検索対象　メニュー名称
        String menuName = requestDto.getMenuNameKj();
        if (CommonUtil.isNull(cdFrom) 
                && CommonUtil.isNull(cdTo)
                	&& CommonUtil.isNull(menuName)
                		&& CommonUtil.isNull(requestDto.getMenuBunrui())
                			&& CommonUtil.isNull(requestDto.getSearch50Key())) {
            //MSG【E0507】’を入力してください。'
            throw new NoInputException(
                    "メニュー名、メニューコード範囲又はメニュー分類のいずれか"
                    , "menuNameKj", 0);
        }
        CodeFormatter cdf = new CodeFormatter(6, "000000");
        cdf.setFormatPattern("000000");
        HankakuEisujiVerifier hankakuEisu = new HankakuEisujiVerifier();
        if(!CommonUtil.isNull(cdFrom)){
            if(hankakuEisu.validate(cdFrom)){
            	requestDto.setMenuCdFrom(cdf.format(cdFrom, true));
            }
            else{
                //MSG【E0505】’が正しくありません。'
                throw new InvalidInputException("メニューコードFROM", "menuCdFrom", 0);
            }
        }
        if(!CommonUtil.isNull(cdTo)){        
            if(hankakuEisu.validate(cdTo)){
            	requestDto.setMenuCdTo(cdf.format(cdTo, true));
            }
            else{
                //MSG【E0505】’が正しくありません。'
                throw new InvalidInputException("メニューコードTO", "menuCdTo", 0);
            }
        }
    }
	/**
	 * DAO【社内メニュー情報】取得処理
	 * 
	 * @return menuSearchUIMenuInfoDao を戻します。
	 */
	public UIMenuInfoDao getMenuSearchUIMenuInfoDao() {
		return menuSearchUIMenuInfoDao;
	}
	/**
	 * DAO【社内メニュー情報】設定処理
	 * 
	 * @param dao を クラス変数menuSearchUIMenuInfoDaoへ設定します。
	 */
	public void setMenuSearchUIMenuInfoDao(
			UIMenuInfoDao dao) {
		this.menuSearchUIMenuInfoDao = dao;
	}

}