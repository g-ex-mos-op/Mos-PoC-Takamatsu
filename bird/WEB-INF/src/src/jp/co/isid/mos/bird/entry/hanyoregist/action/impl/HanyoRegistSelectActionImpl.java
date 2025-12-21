/*
 * 作成日: 2006/4/17
 */
package jp.co.isid.mos.bird.entry.hanyoregist.action.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.entry.common.entity.UIEntryCourse;
import jp.co.isid.mos.bird.entry.common.logic.SearchDefaultCourseListLogic;
import jp.co.isid.mos.bird.entry.hanyoregist.action.HanyoRegistSelectAction;
import jp.co.isid.mos.bird.entry.hanyoregist.common.HanyoRegistCommon;
import jp.co.isid.mos.bird.entry.hanyoregist.dto.HanyoRegistDto;
import jp.co.isid.mos.bird.entry.hanyoregist.dto.SessionKeyDto;
import jp.co.isid.mos.bird.entry.hanyoregist.entity.UIEntryDate;
import jp.co.isid.mos.bird.entry.hanyoregist.entity.UIEntryMst;
import jp.co.isid.mos.bird.entry.hanyoregist.entity.UIEntryNotice;
import jp.co.isid.mos.bird.entry.hanyoregist.logic.SearchEntryInfoLogic;
import jp.co.isid.mos.bird.entry.hanyoregist.logic.SearchEntryLogic;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;
import jp.co.isid.mos.bird.framework.exception.IllegalOperationException;
import jp.co.isid.mos.bird.framework.exception.NotSelectedException;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * 汎用研修マスタ登録　条件画面アクションクラス
 * @author itamoto
 */
public class HanyoRegistSelectActionImpl implements HanyoRegistSelectAction {

    /* アクションID */
    public static String initialize_ACTION_ID     = "BEN004A01";
    public static String changePullDown_ACTION_ID = "BEN004A02";
    public static String regist_ACTION_ID         = "BEN004A03";
    public static String edit_ACTION_ID           = "BEN004A04";
    public static String delete_ACTION_ID         = "BEN004A05";

    /* 汎用研修マスタ登録用DTO */
    private HanyoRegistDto hanyoRegistDto;
    /* セッションキー保持DTO */
    private SessionKeyDto hanyoRegistSessionKeyDto;
    
    /* エントリーマスタ管理の検索ロジック */
    private SearchEntryLogic searchEntryLogic;
    private SearchEntryInfoLogic searchEntryInfoLogic;
    private SearchDefaultCourseListLogic searchDefaultCourseListLogic;
    /* ラヂオ選択index */
    private int index;

	/**
	 * 汎用研修マスタ登録用DTOの設定
	 * @return hanyoRegistDto を戻します。
	 */
	public HanyoRegistDto getHanyoRegistDto() {
		return hanyoRegistDto;
	}
	/**
	 * 汎用研修マスタ登録用DTOの設定
	 * @param hanyoRegistDto hanyoRegistDto を設定。
	 */
	public void setHanyoRegistDto(HanyoRegistDto hanyoRegistDto) {
		this.hanyoRegistDto = hanyoRegistDto;
	}

	/**
	 * エントリーマスタ管理の検索ロジックの設定
	 * @return searchEntryLogic を戻します。
	 */
	public SearchEntryLogic getSearchEntryLogic() {
		return searchEntryLogic;
	}
	/**
	 * エントリーマスタ管理の検索ロジックの設定
	 * @param searchEntryLogic searchEntryLogic を設定。
	 */
	public void setSearchEntryLogic(SearchEntryLogic searchEntryLogic) {
		this.searchEntryLogic = searchEntryLogic;
	}

    /**
     * エントリーコース管理の検索ロジックの設定
     * @return searchDefaultCourseListLogic を戻します。
     */
    public SearchDefaultCourseListLogic getSearchDefaultCourseListLogic() {
        return searchDefaultCourseListLogic;
    }
    /**
     * エントリーコース管理の検索ロジックの設定
     * @param searchDefaultCourseListLogic searchDefaultCourseListLogic を設定。
     */
    public void setSearchDefaultCourseListLogic(
            SearchDefaultCourseListLogic searchDefaultCourseListLogic) {
        this.searchDefaultCourseListLogic = searchDefaultCourseListLogic;
    }

    /**
     * ラヂオ選択インデックス設定
     * @return index
     */
    public int getIndex() {
        return index;
    }
    /**
     * ラヂオ選択インデックス設定
     * @param index
     */
    public void setIndex(int index) {
        this.index = index;
    }

	/**
     * 初期処理
     * @return 画面遷移情報
     */
	public String initialize() {
		// 初期処理
		if (getPullDownMenuDto().isClearFlg()) {
            // Session内の検索結果をクリア
            clearResult();
            
            getHanyoRegistDto().setBirdDateInfo(getBirdDateInfo());
            getHanyoRegistDto().setBirdUserInfo(getBirdUserInfo());
            
            
			// １．ロジック【エントリーマスタ管理の検索】を実行する。
			// パラメータ　：　エントリーコード：全て
			getHanyoRegistDto().setEntryCd("ZZ");
			changePullDown();
			
            // ２．１で取得したデータを初期画面にセットする。ラジオボタンは未選択状態とする。
			// ３．研修選択プルダウンにフォーカスをセットする。
			getPullDownMenuDto().setClearFlg(false);
		}
        // 登録処理終了後の初期化処理
        if (getHanyoRegistDto().isCondClearFlg()) {
            getHanyoRegistDto().setEntryCd("ZZ");
            getHanyoRegistDto().setCondClearFlg(false);
            changePullDown();
        }
        // ラジオボタンの選択状態をクリア
        getHanyoRegistDto().setSelectIndex(0);
        return null;
    }

    /**
     * プルダウン変更
     * @return 画面遷移情報
     */
    public String changePullDown() {
        // 前回の検索結果をクリア
        clearResult();
        
    	// １．ロジック【エントリーマスタ管理の検索】を実行する。
    	// パラメータ　：　プルダウンで選択されたエントリーコード（05：出張特別研修   30：更新研修）
		getSearchEntryLogic().execute(getHanyoRegistDto());

		// ２．１で取得したデータを初期画面にセットする。ラジオボタンは未選択状態とする。
    	// ３．研修選択プルダウンにフォーカスをセットする。
    	
    	return null;
	}

    /**
     * 新規登録
     * @return 画面遷移情報
     */
    public String regist() {
        // ０．「全て」で新規は不可
        if ("ZZ".equals(getHanyoRegistDto().getEntryCd())) {
            throw new NotSelectedException("研修");
        }
        
    	// １．登録画面を表示する。
        getHanyoRegistDto().setEditMode(HanyoRegistCommon.EDIT_MODE_INSERT);
    	// ２．新規．[エントリーマスタ管理]を作成し、下記デフォルト値をセットする。
    	// ・ 新規．[エントリーマスタ管理].申込定員← 30
    	// ・ 新規．[エントリーマスタ管理].会場定員← 35
        UIEntryMst uiEntryMst = new UIEntryMst();
        uiEntryMst.setNumberLimit("30");
        uiEntryMst.setPlaceLimit("35");
    	// ３．２．で作成した新規．[エントリーマスタ管理]を登録画面にセットする。
        getHanyoRegistDto().setUiEntryMst(uiEntryMst);
    	// ４．新規．[エントリー日付管理]を作成し、下記デフォルト値をセットする。
    	// ・ 新規．[エントリー日付管理].ユーザタイプコード← ’99’
    	// ・ 新規．[エントリー日付管理].日付区分← ’01’(開催日)
    	// ５．４．で作成した新規．[エントリー日付管理].開始日・終了日を、登録画面の’開催日FROM’・’開催日TO’項目にセットする。
        UIEntryDate uiEntryDateKaisai = new UIEntryDate();
        uiEntryDateKaisai.setUsertypeCd(HanyoRegistCommon.USERTYPE_CD_KANRI);
        uiEntryDateKaisai.setDayKbn(HanyoRegistCommon.DAY_KBN_KAISAI);
        getHanyoRegistDto().setUiEntryDateKaisai(uiEntryDateKaisai);
    	// ６．’本部項目用’新規データ作成。
    	// ① 新規．[エントリー日付管理]を作成し、下記デフォルト値をセットする。
    	// ・ 新規．[エントリー日付管理].ユーザタイプコード← ’01’(本部)
    	// ・ 新規．[エントリー日付管理].日付区分← ’03’(表示)
    	// ② ６．①で作成した新規．[エントリー日付管理].開始日・終了日を登録画面の’本部：表示開始’・’本部：表示終了’項目にセットする。
        UIEntryDate uiEntryDateHonbuHyoji = new UIEntryDate();
        uiEntryDateHonbuHyoji.setUsertypeCd(HanyoRegistCommon.USERTYPE_CD_HONBU);
        uiEntryDateHonbuHyoji.setDayKbn(HanyoRegistCommon.DAY_KBN_HYOJI);
        getHanyoRegistDto().setUiEntryDateHonbuHyoji(uiEntryDateHonbuHyoji);
    	// ③ 新規．[エントリー日付管理]を作成し、下記デフォルト値をセットする。
    	// ・ 新規．[エントリー日付管理].ユーザタイプコード← ’01’(本部)
    	// ・ 新規．[エントリー日付管理].日付区分← ’04’(登録)
    	// ④ ６．③で作成した新規．[エントリー日付管理].開始日・終了日を登録画面の’本部：受付開始’・’本部：受付終了’項目にセットする。
        UIEntryDate uiEntryDateHonbuToroku = new UIEntryDate();
        uiEntryDateHonbuToroku.setUsertypeCd(HanyoRegistCommon.USERTYPE_CD_HONBU);
        uiEntryDateHonbuToroku.setDayKbn(HanyoRegistCommon.DAY_KBN_TOROKU);
        getHanyoRegistDto().setUiEntryDateHonbuToroku(uiEntryDateHonbuToroku);
    	// ７．’オーナー項目用’新規データ作成。
    	// ① 新規．[エントリー日付管理]を作成し、下記デフォルト値をセットする。
    	// ・ 新規．[エントリー日付管理].ユーザタイプコード← ’02’(オーナー)
    	// ・ 新規．[エントリー日付管理].日付区分← ’03’(表示)
    	// ② ７．①で作成した新規．[エントリー日付管理].開始日・終了日を登録画面の’ｵｰﾅｰ：表示開始’・’ｵｰﾅｰ：表示終了’項目にセットする。
        UIEntryDate uiEntryDateOnerHyoji = new UIEntryDate();
        uiEntryDateOnerHyoji.setUsertypeCd(HanyoRegistCommon.USERTYPE_CD_ONER);
        uiEntryDateOnerHyoji.setDayKbn(HanyoRegistCommon.DAY_KBN_HYOJI);
        getHanyoRegistDto().setUiEntryDateOnerHyoji(uiEntryDateOnerHyoji);
    	// ③ 新規．[エントリー日付管理]を作成し、下記デフォルト値をセットする。
    	// ・ 新規．[エントリー日付管理].ユーザタイプコード← ’02’(オーナー)
    	// ・ 新規．[エントリー日付管理].日付区分← ’04’(登録)
    	// ④ ７．③で作成した新規．[エントリー日付管理].開始日・終了日を登録画面の’ｵｰﾅｰ：受付開始’・’ｵｰﾅｰ：受付終了’項目にセットする。
        UIEntryDate uiEntryDateOnerToroku = new UIEntryDate();
        uiEntryDateOnerToroku.setUsertypeCd(HanyoRegistCommon.USERTYPE_CD_ONER);
        uiEntryDateOnerToroku.setDayKbn(HanyoRegistCommon.DAY_KBN_TOROKU);
        getHanyoRegistDto().setUiEntryDateOnerToroku(uiEntryDateOnerToroku);
        // ８． 新規．[エントリー日付管理]を作成し、下記デフォルト値をセットする。
        // ・ 新規．[エントリー日付管理].ユーザタイプコード← ’01’(本部)
        // ・ 新規．[エントリー日付管理].日付区分← ’06’(結果登録)
        // ④ ７．③で作成した新規．[エントリー日付管理].開始日・終了日を登録画面の’結果開始’・’結果終了’項目にセットする。
        UIEntryDate uiEntryDateKekka = new UIEntryDate();
        uiEntryDateKekka.setUsertypeCd(HanyoRegistCommon.USERTYPE_CD_HONBU);
        uiEntryDateKekka.setDayKbn(HanyoRegistCommon.DAY_KBN_KEKKA_TOROKU);
        getHanyoRegistDto().setUiEntryDateKekka(uiEntryDateKekka);
    	// ９．研修選択プルダウンにフォーカスをセットする。
//add start マスタライセンス4次対応 20070110  inazawa   	
        //１０.文言編集
        UIEntryNotice entryNotice = new UIEntryNotice();
        getHanyoRegistDto().setUIEntryNotice(entryNotice);
//end        
        // エントリーコース管理
        getHanyoRegistDto().setUiEntryCourse(new UIEntryCourse());

        // デフォルトコースリスト取得
        getHanyoRegistDto().setCourseList(getSearchDefaultCourseListLogic().execute(getHanyoRegistDto().getEntryCd()));

        // セッションキー作成
        String sessionKey = HanyoRegistCommon.makeSessionKey();
        getHanyoRegistDto().setSessionKey(sessionKey);
        getHanyoRegistSessionKeyDto().setSessionKey(sessionKey);
        
    	return HanyoRegistCommon.EDIT_VIEW_ID;
	}

    /**
	 * 編集
     * @return 画面遷移情報
     */
    public String edit() {
        // 選択行の情報をDTOへセット
        setSelectedEntryInfo();
        
        // １．登録画面を表示する。
        getHanyoRegistDto().setEditMode(HanyoRegistCommon.EDIT_MODE_UPDATE);
    	// ２．初期画面で選択されたデータが下記条件の場合、登録画面の下記項目を入力不可（ラベル表示）にする。
    	// 【条件】
    	// ( オーナー：受付開始 <= システム日付＋1 AND オーナー：受付終了 >= システム日付)
    	// OR
    	// ( 本部：受付開始 <= システム日付＋1 AND 本部：受付終了 >= システム日付)
    	// 【入力不可対象項目（ラベル表示）】
    	// ・ 開催日FROM
    	// ・ 本部：受付開始
    	// ・ 本部：表示開始
    	// ・ オーナー：受付開始
    	// ・ オーナー：表示開始
        
    	// ３．研修名項目に初期画面で選択された研修名をラベル表示する。
    	// ４．登録画面の開始日FROMにフォーカスをセットする。

        // 編集前申込開始日取得
        String onerTorokuFromDt = getHanyoRegistDto().getUiEntryDateOnerToroku().getFromDt();
        String honbuTorokuFromDt = getHanyoRegistDto().getUiEntryDateHonbuToroku().getFromDt();
        // オーナー、本部の申込開始日の早い日付を取得する
        getHanyoRegistDto().setApplyFromDt(
                (honbuTorokuFromDt == null || (onerTorokuFromDt != null && onerTorokuFromDt.compareTo(honbuTorokuFromDt) < 0)) ? onerTorokuFromDt : honbuTorokuFromDt);

        // デフォルトコースリスト取得
        getHanyoRegistDto().setCourseList(getSearchDefaultCourseListLogic().execute(getHanyoRegistDto().getEntryCd()));

        // セッションキー作成
        String sessionKey = HanyoRegistCommon.makeSessionKey();
        getHanyoRegistDto().setSessionKey(sessionKey);
        getHanyoRegistSessionKeyDto().setSessionKey(sessionKey);
        
    	return HanyoRegistCommon.EDIT_VIEW_ID;
	}

    /**
	 * 削除
     * @return 画面遷移情報
     */
    public String delete() {
        getHanyoRegistDto().setEditMode(HanyoRegistCommon.EDIT_MODE_DELETE);
        
        // 選択行の情報をDTOへセット
        setSelectedEntryInfo();
        
        // 削除は、対象が申込期間前の場合のみ可能
        String sysDate = getBirdDateInfo().getSysDate();
        UIEntryDate uiEntryDateOnerTouroku = getHanyoRegistDto().getUiEntryDateOnerToroku();
        if (sysDate.compareTo(uiEntryDateOnerTouroku.getFromDt()) >= 0) {
            throw new IllegalOperationException("申込開始後の研修", "削除");
        }
        
    	// １．確認画面を表示する。
		// ２．初期画面で選択されたデータを確認画面にセットする。

        // セッションキー作成
        String sessionKey = HanyoRegistCommon.makeSessionKey();
        getHanyoRegistDto().setSessionKey(sessionKey);
        getHanyoRegistSessionKeyDto().setSessionKey(sessionKey);
        
    	return HanyoRegistCommon.CONFIRM_VIEW_ID;
	}

    /**
     * 検索結果クリア
     */
    private void clearResult() {
        getHanyoRegistDto().setListEntryDate(null);
        getHanyoRegistDto().setListEntryMst(null);
    }
    
//    /**
//	 * nullチェック
//	 */
//    private boolean isNull(String val) {
//        if (val == null) {
//            return true;
//        }
//        if ("".equals(val.trim())) {
//            return true;
//        }
//        return false;
//    }

    private S2Container getS2Container() {
        return SingletonS2ContainerFactory.getContainer(); 
    }
    private PullDownMenuDto getPullDownMenuDto() {
        return (PullDownMenuDto) getS2Container().getComponent(PullDownMenuDto.class);
    }
    private BirdUserInfo getBirdUserInfo() {
        return (BirdUserInfo) getS2Container().getComponent(BirdUserInfo.class);
    }
    private BirdDateInfo getBirdDateInfo() {
        return (BirdDateInfo) getS2Container().getComponent(BirdDateInfo.class);
    }
    
    /**
     * 編集、削除時に選択行のエントリー情報をDTOへセットする
     */
    private void setSelectedEntryInfo() {
        // 選択行インデックス
        int selectIndex = getHanyoRegistDto().getSelectIndex();    
        // 選択された行のキー情報を取得
        UIEntryMst entityMst = (UIEntryMst) getHanyoRegistDto().getListEntryMst().get(selectIndex);
        getHanyoRegistDto().setEntryCd(entityMst.getEntryCd());
        getHanyoRegistDto().setEntryYear(entityMst.getEntryYear());
        getHanyoRegistDto().setEntryKai(entityMst.getEntryKai());
        // 選択された行のEntity情報を取得
        Map mapEntry = getSearchEntryInfoLogic()
                            .execute(getHanyoRegistDto().getEntryCd(),
                                    getHanyoRegistDto().getEntryYear(),
                                    getHanyoRegistDto().getEntryKai());
        
        List listEntryMst = (List) mapEntry.get("UIEntryMst");
        List listEntryDate = (List) mapEntry.get("UIEntryDate");
        List listEntryCourse = (List) mapEntry.get("UIEntryCourse");
//      add start inazawa マスタライセンス４次対応
        UIEntryNotice entryNotice = (UIEntryNotice) mapEntry.get("UIEntryNotice");
        getHanyoRegistDto().setUIEntryNotice(entryNotice);
//      add end
        //エントリーマスタ管理
        for (Iterator ite = listEntryMst.iterator(); ite.hasNext();) {
            UIEntryMst entity = (UIEntryMst) ite.next();
            if (getHanyoRegistDto().getEntryCd().equals(entity.getEntryCd())
                    && getHanyoRegistDto().getEntryYear().equals(entity.getEntryYear())
                    && getHanyoRegistDto().getEntryKai().equals(entity.getEntryKai()))
            {
                getHanyoRegistDto().setUiEntryMst(entity);
                break;
            }
        }
        //エントリーマスタ日付管理
        for (Iterator ite = listEntryDate.iterator(); ite.hasNext();) {
            UIEntryDate entity = (UIEntryDate) ite.next();
            if (getHanyoRegistDto().getEntryCd().equals(entity.getEntryCd())
                    && getHanyoRegistDto().getEntryYear().equals(entity.getEntryYear())
                    && getHanyoRegistDto().getEntryKai().equals(entity.getEntryKai()))
            {
                if (entity.getUsertypeCd().equals(HanyoRegistCommon.USERTYPE_CD_KANRI)
                        && entity.getDayKbn().equals(HanyoRegistCommon.DAY_KBN_KAISAI))
                {
                    getHanyoRegistDto().setUiEntryDateKaisai(entity);
                }
                else if (entity.getUsertypeCd().equals(HanyoRegistCommon.USERTYPE_CD_HONBU)
                        && entity.getDayKbn().equals(HanyoRegistCommon.DAY_KBN_HYOJI))
                {
                    getHanyoRegistDto().setUiEntryDateHonbuHyoji(entity);
                }
                else if (entity.getUsertypeCd().equals(HanyoRegistCommon.USERTYPE_CD_HONBU)
                        && entity.getDayKbn().equals(HanyoRegistCommon.DAY_KBN_TOROKU))
                {
                    getHanyoRegistDto().setUiEntryDateHonbuToroku(entity);
                }
                else if (entity.getUsertypeCd().equals(HanyoRegistCommon.USERTYPE_CD_ONER)
                        && entity.getDayKbn().equals(HanyoRegistCommon.DAY_KBN_HYOJI))
                {
                    getHanyoRegistDto().setUiEntryDateOnerHyoji(entity);
                }
                else if (entity.getUsertypeCd().equals(HanyoRegistCommon.USERTYPE_CD_ONER)
                        && entity.getDayKbn().equals(HanyoRegistCommon.DAY_KBN_TOROKU))
                {
                    getHanyoRegistDto().setUiEntryDateOnerToroku(entity);
                }
                else if (entity.getUsertypeCd().equals(HanyoRegistCommon.USERTYPE_CD_HONBU)
                        && entity.getDayKbn().equals(HanyoRegistCommon.DAY_KBN_KEKKA_TOROKU))
                {
                    getHanyoRegistDto().setUiEntryDateKekka(entity);
                }
            }
        }

        UIEntryCourse uiEntryCourse = null;
        if (listEntryCourse != null && !listEntryCourse.isEmpty()) {
            uiEntryCourse = (UIEntryCourse) listEntryCourse.get(0);
        } else {
            uiEntryCourse = new UIEntryCourse();
        }
        getHanyoRegistDto().setUiEntryCourse(uiEntryCourse);
    }
    public SessionKeyDto getHanyoRegistSessionKeyDto() {
        return hanyoRegistSessionKeyDto;
    }
    public void setHanyoRegistSessionKeyDto(SessionKeyDto sessionKeyDto) {
        this.hanyoRegistSessionKeyDto = sessionKeyDto;
    }
    public SearchEntryInfoLogic getSearchEntryInfoLogic() {
        return searchEntryInfoLogic;
    }
    public void setSearchEntryInfoLogic(SearchEntryInfoLogic searchEntryInfoLogic) {
        this.searchEntryInfoLogic = searchEntryInfoLogic;
    }
}