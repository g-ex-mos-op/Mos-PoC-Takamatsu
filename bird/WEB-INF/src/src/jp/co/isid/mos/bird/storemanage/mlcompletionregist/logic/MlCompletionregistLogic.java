/*
 * 作成日: 2006/04/11
 */
package jp.co.isid.mos.bird.storemanage.mlcompletionregist.logic;

import java.util.Map;

import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.storemanage.mlcompletionregist.dto.MlCompletionregistDto;

/**
 * マスタライセンス研修修了登録
 * ロジック インターフェイス
 * 
 * @author xkinu
 */
public interface MlCompletionregistLogic {
	/** 画面ID：BSM006 */
	public static final String SCREEN_ID = "BSM006";
    /** ロジックID: BSM006L01 条件画面出力データ検索ロジック */
    public static final String LOGICID_CONDITION = SCREEN_ID+"L01";
    /** ロジックID: BSM006L02 編集画面出力データ検索ロジック */
    public static final String LOGICID_EDIT      = SCREEN_ID+"L02";
    /** ロジックID: BSM006L03 登録内容のチェックロジック */
    public static final String LOGICID_CHECK     = SCREEN_ID+"L03";
    /** ロジックID: BSM006L04 入力データ更新 ロジック */
    public static final String LOGICID_REGIST   = SCREEN_ID+"L04";
    /** モス会社コード */
    public static final String COMPANY_CD_MOS = "00";

   /**
     * 処理実行
     * @param MlCompletionregistDto 画面データ保持クラス
     * @exception ApplicationException
     */
    public Map execute(MlCompletionregistDto dto) throws ApplicationException;
}