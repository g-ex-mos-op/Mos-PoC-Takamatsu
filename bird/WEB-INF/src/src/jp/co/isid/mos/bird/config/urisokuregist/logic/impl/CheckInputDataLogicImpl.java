/**
 * 
 */
package jp.co.isid.mos.bird.config.urisokuregist.logic.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.common.dao.MstShanaiMenuDao;
import jp.co.isid.mos.bird.common.entity.MstShanaiMenu;
import jp.co.isid.mos.bird.common.entity.UILists;
import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.config.urisokuregist.dto.SessionDto;
import jp.co.isid.mos.bird.config.urisokuregist.entity.UIMiseCnt;
import jp.co.isid.mos.bird.config.urisokuregist.entity.UIUsrCamp;
import jp.co.isid.mos.bird.config.urisokuregist.entity.UIUsrMenu;
import jp.co.isid.mos.bird.config.urisokuregist.logic.CheckInputDataLogic;
import jp.co.isid.mos.bird.config.urisokuregist.util.UrisokuRegistUtil;
import jp.co.isid.mos.bird.framework.exception.GenericMessageException;
import jp.co.isid.mos.bird.framework.exception.IllegalOperationException;
import jp.co.isid.mos.bird.framework.exception.InputConstraintException;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.exception.MissingDataException;
import jp.co.isid.mos.bird.framework.exception.NotExistException;
import jp.co.isid.mos.bird.framework.exception.NotRelevantException;
import jp.co.isid.mos.bird.framework.util.formatter.CodeFormatter;
import jp.co.isid.mos.bird.framework.util.verifier.CodeVerifier;
import jp.co.isid.mos.bird.framework.util.verifier.NumericVerifier;

/**
 * [機能設定]【売上速報設定】
 * 入力データチェックロジック
 * 
 * 作成日:2008/08/19
 * @author xkinu
 *
 */
public class CheckInputDataLogicImpl implements CheckInputDataLogic {
    /** ロジックID */
    public static final String LOGIC_ID = UrisokuRegistUtil.SCREEN_ID+"L03";

    private MstShanaiMenuDao mstShanaiMenuDao;

	/**
	 * 実行処理
	 * @see jp.co.isid.mos.bird.config.urisokuregist.logic.CheckInputDataLogic#execute(jp.co.isid.mos.bird.config.urisokuregist.dto.SessionDto)
	 * 
	 * @param sessionDto DTO【Session】
	 * @param dataKbn チェック対象データ区分（VIEW_IDが指定されます。)
	 */
	public Map execute(SessionDto sessionDto, String dataKbn) {
        //１．事前条件判断処理
        validate(sessionDto);
        //２．リターンMapをインスタンス化します。
        Map reParam = new HashMap();
    	List listRegistData = sessionDto.getListRegistData();
    	for(int i=0; i<listRegistData.size(); i++) {
    		UILists dataInfo = (UILists)listRegistData.get(i);
    		if(DATA_KBN_ALL.equals(dataKbn) 
    				|| dataInfo.getKey().equals(dataKbn)) {
    			if(UrisokuRegistUtil.VIEW_ID_EDIT_MISECNT.equals(dataInfo.getKey())) {
    				checkMiseCntData(dataInfo.getListData(), reParam);
    			}
    			if(UrisokuRegistUtil.VIEW_ID_EDIT_MENU.equals(dataInfo.getKey())) {
    				checkMenuData(dataInfo.getListData(), reParam);
    			}
    			if(UrisokuRegistUtil.VIEW_ID_EDIT_CAMP.equals(dataInfo.getKey())) {
    				checkCampData(dataInfo.getListData(), reParam);
    			}
    		}
    		if(reParam.get(RP_ERR_EXCEPTION) !=null) {
    			break;
    		}
    	}
    	return reParam;
	}
    /**
     * 事前条件処理
     * 
     * @param taishoYm
     * @param frameKbn
     * @throws MissingDataException
     */
    private void validate(final SessionDto sessionDto) {
        if(sessionDto == null){
            //MSG【E0202】(がありません。)
            throw new MissingDataException("自画面SessionDto");
        }
    	List listRegistData = sessionDto.getListRegistData();
        if(listRegistData== null){
            //MSG【E0202】(がありません。)
            throw new MissingDataException("編集情報");
        }
    }
    /**
     * 店舗数入力値チェック処理
     * 
     * @param listData
     * @param reParam
     */
    private void checkMiseCntData(final List listData, Map reParam) {
    	//１．for分でList[[編集データ]]の件数分下記の処理を行います。
    	for(int i=0; i<listData.size(); i++) {
    		//for-1.現行インデックスの位置にある[編集データ]を取得します。
    		UIMiseCnt entity = (UIMiseCnt)listData.get(i);
    		String miseCnt = entity.getMiseCnt();
    		//for-4.処理2のメニューコードがnull又は空でない場合は、
    		//      下記のメニューコードチェックを行います。
    		if(CommonUtil.isNull(miseCnt)) {
    			entity.setMiseCnt("0");
    			miseCnt = entity.getMiseCnt();
    		}
            //マイナス値は不可にします。
            boolean isMinus = false;
            //数値チェックをインスタンス化します。
            NumericVerifier codeVeri = new NumericVerifier(isMinus, 4);
            if(!codeVeri.validate(miseCnt)) {
            	reParam.put(RP_ERR_EXCEPTION, new InputConstraintException("店舗数", "半角数値", "miseCnt", i));
            	reParam.put(RP_ERR_TAB_MAIN, UrisokuRegistUtil.VIEW_ID_EDIT_MISECNT);
            	break;
            }
            BigDecimal miseCntAll = new BigDecimal(miseCnt);
            entity.setMiseCntAll(miseCntAll);
    	}
    }
    /**
     * 商品メニュー入力値チェック処理
     * 
     * @param listData
     * @param reParam
     */
    private void checkMenuData(final List listData, Map reParam) {
    	//１．for分でList[[編集データ]]の件数分下記の処理を行います。
    	for(int i=0; i<listData.size(); i++) {
    		//for-1.現行インデックスの位置にある[編集データ]を取得します。
    		UILists frame = (UILists)listData.get(i);
    		for(int f=0; f<frame.getListData().size(); f++) {
	    		int duplicateDataCnt = -1;
	    		//for-1.現行インデックスの位置にある[編集データ]を取得します。
	    		UIUsrMenu entity = (UIUsrMenu)frame.getListData().get(f);
	    		//for-2.[編集データ].データ区分へ"0"(コメント又は空白)を取得します。
	    		entity.setDataKbn(UrisokuRegistUtil.DATA_KBN_COMMENT);
	    		//for-3.処理for-1の[編集データ].メニューコードを取得します。
	    		String menuCd = entity.getMenuCd();
	    		//for-4.処理2のメニューコードがnull又は空でない場合は、
	    		//      下記のメニューコードチェックを行います。
	    		if(CommonUtil.isNull(menuCd)) {
	    			entity.setMenuCd("");
	    			entity.setMenuNameKj("");
	    		}
	    		else {
	                //アルファベットは不可にします。
	                boolean isAlphabet = true;
	                //コードチェックをインスタンス化します。
	                CodeVerifier codeVeri = new CodeVerifier(isAlphabet);
	                if(!codeVeri.validate(menuCd) && reParam.get(RP_ERR_EXCEPTION) == null) {
	                	reParam.put(RP_ERR_EXCEPTION, new InvalidInputException("メニューコード", "menuCd", f));
	                	reParam.put(RP_ERR_TAB_MAIN, UrisokuRegistUtil.VIEW_ID_EDIT_MENU);
	        	    	reParam.put(RP_ERR_TAB_SUB, frame.getKey());
	        	    	break;
	                }
	                else {
		                CodeFormatter cdf = new CodeFormatter(6, "000000");
		                cdf.setFormatPattern("000000");
		                menuCd = cdf.format(menuCd, true);
		                List listMenuCd = new ArrayList(0);
		                listMenuCd.add(menuCd);
		                List listMenuInfo = getMstShanaiMenuDao().select(listMenuCd, null);
		                if(listMenuInfo.size() == 0) {
		                	if(reParam.get(RP_ERR_EXCEPTION) == null) {
		                		reParam.put(RP_ERR_EXCEPTION, new NotExistException("メニューコード", "menuCd", f));
		                    	reParam.put(RP_ERR_TAB_MAIN, UrisokuRegistUtil.VIEW_ID_EDIT_MENU);
		            	    	reParam.put(RP_ERR_TAB_SUB, frame.getKey());
			        	    	break;
			                }
		                }
		                else {
			                entity.setMenuCd(menuCd);
			                MstShanaiMenu menuInfo = (MstShanaiMenu)listMenuInfo.get(0);
			                entity.setMenuNameKj(menuInfo.getMenuNameKj());
			                duplicateDataCnt++;
		                }
	                }
	    		}
	    		//for-3.処理2の処理for-1の[編集データ].表示文字列がnull又は空でない場合は、
	    		//      下記の表示文字列チェックを行います。
	    		if(CommonUtil.isNull(entity.getDispWord())) {
	    			entity.setDispWord("");
	    		}
	    		else {
	                if((entity.getDispWord().trim()).getBytes().length > 30) {
	                	if(reParam.get(RP_ERR_EXCEPTION) == null) {
	                		reParam.put(RP_ERR_EXCEPTION, new NotRelevantException("表示文字列", "全角１５文字以内", "menuCd", f));
	                    	reParam.put(RP_ERR_TAB_MAIN, UrisokuRegistUtil.VIEW_ID_EDIT_MENU);
	            	    	reParam.put(RP_ERR_TAB_SUB, frame.getKey());
		        	    	break;
	                	}
	                }
	                else {
	                	duplicateDataCnt++;
	                }
	    		}
	    		//for-4.確認画面遷移時で同じ行にメニューコードと表示文字列が両方入力されていた場合、
	    		//下記のExceptionを発生させる。
	    		if(duplicateDataCnt>0 && reParam.get(RP_ERR_EXCEPTION) == null) {
	    			reParam.put(RP_ERR_EXCEPTION, new IllegalOperationException("メニューコードと表示文字列", "同じ行に指定", "menuCd", f));
	    	    	reParam.put(RP_ERR_TAB_MAIN, UrisokuRegistUtil.VIEW_ID_EDIT_MENU);
	    	    	reParam.put(RP_ERR_TAB_SUB, frame.getKey());
        	    	break;
	    		}
    		}//end of for(int f=0; f<frame.getListData().size(); f++)
    		if(reParam.get(RP_ERR_EXCEPTION) != null) {
    	    	break;
    		}
    	}
    }
    /**
     * キャンペーン入力値チェック処理
     * 
     * 表示タイトルの上限は8行です。
     * 表示メニューの上限は24行です。
     * 
     * @param listData
     * @param reParam
     */
    private void checkCampData(final List listData, Map reParam) {
    	//１．変数.対象売上速報表示メニュー数を生成し、0を設定します。
    	int taishoSeqNo = 0;
    	//２．変数.表示タイトル数を生成し、0を設定します。
    	int titleCnt =0;
    	//３．変数.表示メニュー数を生成し、0を設定します。
    	int jissekiMenuCnt =0;
    	boolean thisCampTitleFlg = false;
    	boolean thisCampJissekiFlg = false;
    	//４．for分でList[[編集データ]]の件数分下記の処理を行います。
    	for(int i=0; i<listData.size(); i++) {
    		//for-1.現行インデックスの位置にある[編集データ]を取得します。
    		UIUsrCamp entity = (UIUsrCamp)listData.get(i);
    		String seqNo = entity.getMenuSeqNo();
    		if(seqNo.equals("1")) {
    			thisCampTitleFlg = entity.isTitle();
    			thisCampJissekiFlg = entity.isJisseki();
    		}
    		else {
    			entity.setTitle(thisCampTitleFlg);
    			entity.setJisseki(thisCampJissekiFlg);
    		}
    		if((entity.isTitle() || entity.isJisseki())) {
	    		if(seqNo.equals("1")) {
	    			if(entity.isJisseki()) {
	    				//合計行の1行分をカウントアップします。
	    				jissekiMenuCnt++;
	    				if(jissekiMenuCnt>1) {
	    					//キャンペーン２つ目以降は空白行の1行分もカウントアップします。
		    				jissekiMenuCnt++;
	    				}
	    			}
	    			taishoSeqNo++;
	    			if(entity.isTitle()) {titleCnt++;}
	    			entity.setTaishoSeqNo(String.valueOf(taishoSeqNo));
	    		}
    			if(entity.isJisseki()) {jissekiMenuCnt++;}
    		}
    	}
    	//２．確認ボタン押下時で、タイトル表示に選択されているキャンペーンが8件を超えたの場合
    	//    下記のExceptionを発生させます。
    	if(titleCnt>8 && reParam.get(RP_ERR_EXCEPTION) == null) {
    		reParam.put(RP_ERR_EXCEPTION, new NotRelevantException("タイトル表示するキャンペーン", "8件以下"));
        	reParam.put(RP_ERR_TAB_MAIN, UrisokuRegistUtil.VIEW_ID_EDIT_CAMP);
    		return;
    	}
    	//３．確認ボタン押下時で、実績表示に選択されているキャンペーンの対象メニューの件数が24件を超えている場合
    	//    下記のExceptionを発生させます。
//2008/10/23 update 登録可能件数を２１件から２４件に変更        
//        if((jissekiMenuCnt)>21 && reParam.get(RP_ERR_EXCEPTION) == null) {
    	if((jissekiMenuCnt)>24 && reParam.get(RP_ERR_EXCEPTION) == null) {
    		reParam.put(RP_ERR_EXCEPTION, new GenericMessageException("実績表示するメニュー数が上限を超えています。"));
        	reParam.put(RP_ERR_TAB_MAIN, UrisokuRegistUtil.VIEW_ID_EDIT_CAMP);
    		return;
    	}
    }
	/**
	 * @return mstShanaiMenuDao を戻します。
	 */
	public MstShanaiMenuDao getMstShanaiMenuDao() {
		return mstShanaiMenuDao;
	}
	/**
	 * @param mstShanaiMenuDao mstShanaiMenuDaoへ設定します。
	 */
	public void setMstShanaiMenuDao(MstShanaiMenuDao mstShanaiMenuDao) {
		this.mstShanaiMenuDao = mstShanaiMenuDao;
	}
}
