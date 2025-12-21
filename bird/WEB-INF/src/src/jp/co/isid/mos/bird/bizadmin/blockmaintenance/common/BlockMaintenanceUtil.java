package jp.co.isid.mos.bird.bizadmin.blockmaintenance.common;

import java.util.List;

import jp.co.isid.mos.bird.bizadmin.blockmaintenance.entity.CodBlock;
import jp.co.isid.mos.bird.bizadmin.blockmaintenance.entity.UIMiseBlockInfo;

/**
 * static 処理保持クラス
 * 
 * @author xkinu
 *
 */
public class BlockMaintenanceUtil {
    /* 画面ID */
    public static final String SCREEN_ID = "BBA003";
    /* VIEWID：複数Window機能の操作エラー画面 */
    public static final String operationErr_VIEW_ID = "operation.Err";
    /* VIEWID：初期(条件)画面 */
    public static final String VIEW_ID_CONDITION = SCREEN_ID+"V01";
    /* VIEWID：編集画面 */
    public static final String VIEW_ID_EDIT = SCREEN_ID+"V02";
    /* VIEWID：編集画面 */
    public static final String VIEW_ID_CONFIRM = SCREEN_ID+"V03";
    
    /* ACTIONID：初期(条件)画面 */
    public static final String ACTION_ID_CONDITION = SCREEN_ID+"A1";
    /* ACTIONID：編集画面 */
    public static final String ACTION_ID_EDIT = SCREEN_ID+"A2";
    /* ACTIONID：編集画面 */
    public static final String ACTION_ID_CONFIRM = SCREEN_ID+"A3";
    
    /* LOGICID：初期(条件)画面項目情報の取得 */
    public static final String LOGIC_ID_CONDITION = SCREEN_ID+"L01";
    /* LOGICID：検索対象情報の取得 */
    public static final String LOGIC_ID_SEARCH = SCREEN_ID+"L02";
    /* LOGICID：選択店舗新ブロック設定 */
    public static final String LOGIC_ID_SETMOVEBLOCK = SCREEN_ID+"L03";
    /* LOGICID：新ブロックリセット */
    public static final String LOGIC_ID_RESETBLOCK = SCREEN_ID+"L04";
    /* LOGICID：入力値のチェック */
    public static final String LOGIC_ID_CHECKDATA = SCREEN_ID+"L05";
    /* LOGICID：データ登録 */
    public static final String LOGIC_ID_REGIST = SCREEN_ID+"L06";
    
    /* ブロックコード：すべて */
    public static final String BLOCK_CD_ALL = "ALL";
    /* ブロックコード：未登録 */
    public static final String BLOCK_CD_MISETTEI = "000";
    
    /**
     * コンストラクター
     *
     */
    private BlockMaintenanceUtil(){
        super();
    }
    /**
     * 表示対象ブロック変更処理
     * 
     * @param list　　　　登録対象データ
     * @param dispBlockCd 対象ブロックコード
     * @return データが有るか無いか。
     */
    public static boolean changeDispFlg(List list, String dispBlockCd) {
    	boolean existFlg = false;
    	for (int i=0; i<list.size(); i++) {
    		UIMiseBlockInfo entity = (UIMiseBlockInfo)list.get(i);
    		if(BLOCK_CD_ALL.equals(dispBlockCd)
    				|| dispBlockCd.equals(entity.getBlockCd())) {
    			entity.setDispFlg(true);
    			existFlg = true;
    		}
    		else {
    			entity.setDispFlg(false);
    		}
    	}
    	return existFlg;
    }
    /**
     * ブロックエンティティー取得処理
     * 
     * @param listBlock
     * @param blockCd
     * @return
     */
    public static CodBlock getCodBlock(List listBlock, String blockCd) {
    	CodBlock targetEntity = null;
    	for (int i=0; i<listBlock.size(); i++) {
    		CodBlock entity = (CodBlock)listBlock.get(i);
    		if(entity.getBlockCd().equals(blockCd)) {
    			targetEntity = entity;
    			break;
    		}
    	}
    	return targetEntity;
    }
    /**
     * 画面変更フラグ != 1 の最後の行データインデックス取得処理
     * 
     * @param listSearchData
     * @return
     */
    public static int getFocusInitIndex(List listSearchData) {
    	//変数.リターン値インデックス
    	int initIndex=-1;
    	//[[検索結果]]件数文for文でチェックしていきます。
    	for(int i=0; i<listSearchData.size(); i++) {
    		UIMiseBlockInfo entity = (UIMiseBlockInfo)listSearchData.get(i);
			if((initIndex > -1 && !entity.isDispFlg())
					|| !entity.isChange())
			{
    			break;
	   		}
			if(entity.isDispFlg()){
				initIndex = i;
			}
    	}
    	//変数.リターン値インデックスをリターンする。
    	return initIndex;
    }
    /**
     * String用Null判断処理
     * @param str
     * @return boolean true：空かNullの場合
     */
    public static boolean isNull(String str) {
        return (str == null || "".equals(str.trim())) ? true : false;
    }

}
