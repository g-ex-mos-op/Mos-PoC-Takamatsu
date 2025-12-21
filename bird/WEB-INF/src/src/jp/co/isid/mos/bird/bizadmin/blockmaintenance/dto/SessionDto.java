package jp.co.isid.mos.bird.bizadmin.blockmaintenance.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.bizadmin.blockmaintenance.common.BlockMaintenanceUtil;
import jp.co.isid.mos.bird.bizadmin.blockmaintenance.entity.UIMiseBlockInfo;
import jp.co.isid.mos.bird.common.entity.MstSibu;
import jp.co.isid.mos.bird.common.entity.MstUserCompany;

/**
 * ブロックメンテナンス DTO
 * 
 * ブロックメンテナンス画面用ののDTOです。
 * 
 * @author xkinu
 *
 */
public class SessionDto {
    /**
     * 最大ウィンドウID
     */
    private int maxWindowId = 0;
    /* 現行セッションKey */
    private String nowSessionKey;
    /* セッションKey保持Map */
    private Map sessionKey = new HashMap();

    /* 条件フィールド：対象会社コード  */
    private String targetCompanyCd;
    /* 条件フィールド：対象支部コード */
    private String targetSibuCd;
    /* 条件項目：[[会社]] */
    private List listCompany = null;
    /* 条件項目：[[対象支部]]を保持する会社別支部情報 */
    private List listSibu = null;
    /* 条件項目：[[対象ブロックリスト]] */
    private List listDispBlock = null;
    /* 条件項目：[[移動先(ブロック)リスト]] */
    private List listBlock = null;

    /* 編集フィールド：対象ブロック  */
    private String objectBlock;
    /* 編集フィールド：移動先 */
    private String moveBlockCd;
    
    /* 条件項目：[[登録対象データ]] */
    private List listSearchData = null;
    
    
    /**
     * クリア処理
     *
     */
    public void clear(){
        //条件項目値クリア
        setTargetCompanyCd(null);
        setTargetSibuCd(null);
        setListCompany(null);
        setListSibu(null);
        setListSearchData(null);
   }

    /**
     * 最大ウィンドウIDを取得します。
     * @return 最大ウィンドウID
     */
    public int getMaxWindowId() {
        return maxWindowId;
    }
    
    /**
     * 最大ウィンドウIDを設定します。
     * @param maxWindowId　最大ウィンドウID
     */
    public void setMaxWindowId(int maxWindowId) {
        this.maxWindowId = maxWindowId;
    }
    /**
     * ウィンドウIDを生成します。
     */
    public int createWindowId() {
        int newWindowId = getMaxWindowId() + 1;
        setMaxWindowId(newWindowId);
        return newWindowId;
    }

    /**
     * @return listSearchData を戻します。
     */
    public List getListSearchData() {
        return listSearchData;
    }
    /**
     * @param listSearchData 設定する listSearchData。
     */
    public void setListSearchData(List listSearchData) {
        this.listSearchData = listSearchData;
    }
	/**
	 * @return nowSessionKey を戻します。
	 */
	public String getNowSessionKey() {
		return nowSessionKey;
	}
	/**
	 * @param nowSessionKey 設定する nowSessionKey。
	 */
	public void setNowSessionKey(String nowSessionKey) {
		this.nowSessionKey = nowSessionKey;
	}
	/**
	 * セッションKey保持MapからセッションKeyの取得
	 * @return sessionKey を戻します。
	 */
	public String getSessionKey(int windowId) {
		return (String)sessionKey.get(new Integer(windowId));
	}
    /**
     * セッションKey保持MapへセッションKeyの設定
     * @param sessionKey sessionKey を設定。
     */
    public void setSessionKey(int windowId, String sessionKey) {
        this.sessionKey.put(new Integer(windowId), sessionKey);
    }
	/**
	 * @return targetCompanyCd を戻します。
	 */
	public int getTargetCompanyCdIndex() {
		for(int i=0; i<listCompany.size(); i++) {
			MstUserCompany entity = (MstUserCompany)listCompany.get(i);
			if(entity.getCompanyCd().equals(targetCompanyCd)) {
				return i;
			}
		}
		return 0;
	}
	/**
	 * @return targetCompanyCd を戻します。
	 */
	public String getTargetCompanyCd() {
		return targetCompanyCd;
	}
	/**
	 * @param targetCompanyCd 設定する targetCompanyCd。
	 */
	public void setTargetCompanyCd(String targetCompanyCd) {
		this.targetCompanyCd = targetCompanyCd;
	}
	/**
	 * 会社名称取得処理
	 * 
	 * @return
	 */
	public String getTargetCompanyName() {
		for(int i=0; i<listCompany.size(); i++) {
			MstUserCompany entity = (MstUserCompany)listCompany.get(i);
			if(entity.getCompanyCd().equals(targetCompanyCd)) {
				return entity.getCompanyName();
			}
		}
		return "";
	}
	/**
	 * @return targetSibuCd を戻します。
	 */
	public String getTargetSibuCd() {
		return targetSibuCd;
	}
	/**
	 * @param targetSibuCd 設定する targetSibuCd。
	 */
	public void setTargetSibuCd(String targetSibuCd) {
		this.targetSibuCd = targetSibuCd;
	}
	/**
	 * 支部名称取得処理
	 * 
	 * @return
	 */
	public String getTargetSibuName() {
		List listSibu = getListSibu();
		for(int i=0; i<listSibu.size(); i++) {
			MstSibu entity = (MstSibu)listSibu.get(i);
			if(entity.getSibuCd().equals(targetSibuCd)) {
				return entity.getSibuName();
			}
		}
		return "";
		
	}
	/**
	 * @return listCompany を戻します。
	 */
	public List getListCompany() {
		return listCompany;
	}
	/**
	 * @param listCompany 設定する listCompany。
	 */
	public void setListCompany(List listCompany) {
		this.listCompany = listCompany;
	}
	/**
	 * @return moveBlockCd を戻します。
	 */
	public String getMoveBlockCd() {
		return moveBlockCd;
	}

	/**
	 * @param moveBlockCd 設定する moveBlockCd。
	 */
	public void setMoveBlockCd(String moveBlockCd) {
		this.moveBlockCd = moveBlockCd;
	}

	/**
	 * @return objectBlock を戻します。
	 */
	public String getObjectBlock() {
		return objectBlock;
	}

	/**
	 * @param objectBlock 設定する objectBlock。
	 */
	public void setObjectBlock(String objectBlock) {
		this.objectBlock = objectBlock;
	}

	/**
	 * @return listBlock を戻します。
	 */
	public List getListBlock() {
		return listBlock;
	}

	/**
	 * @param listBlock 設定する listBlock。
	 */
	public void setListBlock(List listBlock) {
		this.listBlock = listBlock;
	}

	/**
	 * @return listSibu を戻します。
	 */
	public List getListSibu() {
		return listSibu;
	}

	/**
	 * @param listSibu 設定する listSibu。
	 */
	public void setListSibu(List listSibu) {
		this.listSibu = listSibu;
	}

	/**
	 * 対象ブロックリスト取得処理
	 * 
	 * @return listDispBlock を戻します。
	 */
	public List getListDispBlock() {
		return listDispBlock;
	}

	/**
	 * 対象ブロックリスト設定処理
	 * 
	 * @param listDispBlock 設定する listDispBlock。
	 */
	public void setListDispBlock(List listDispBlock) {
		this.listDispBlock = listDispBlock;
	}
    /**
     * 確認画面用リスト作成処理
     * 
     * @param listSearchData
     * @return
     */
    public List getListConfirmData(){
    	List listSearchData = getListSearchData();
        if(listSearchData == null || listSearchData.size() <= 0){
            return null;
        }
        //リターン値[[確認画面用リスト]]をインスタンス化する。
        List listConfirmMiseList = new ArrayList();
        //３．登録データ件数分下記の処理を行う。
        for (int i=0; i<listSearchData.size(); i++){
        	//
        	UIMiseBlockInfo entity = (UIMiseBlockInfo)listSearchData.get(i);
        	//1.[登録データ].移動先ブロックコードがNullまたは空でない場合、
        	//  以下の処理を行う。
        	if(!BlockMaintenanceUtil.isNull(entity.getMoveBlockCd())) {
        		//1-1.新しく[店舗ブロック設定情報]をインスタンス化する。
        		//　　以下の値を設定する。
        		UIMiseBlockInfo regData = new UIMiseBlockInfo();
        		//管理会社企業コード
        		regData.setCompanyCd(entity.getCompanyCd());
        		//店コード
        		regData.setMiseCd(entity.getMiseCd());
        		regData.setMiseNameKj(entity.getMiseNameKj());
        		regData.setCloseMj(entity.getCloseMj());
        		//ブロックコード
        		regData.setBlockCd(entity.getBlockCd());
        		regData.setBlockName(entity.getBlockName());
        		//支部クコード
        		regData.setSibuCd(entity.getSibuCd());
        		regData.setSibuName(entity.getSibuName());
        		//新ブロックコード
        		regData.setMoveBlockCd(entity.getMoveBlockCd());
        		regData.setMoveBlockName(entity.getMoveBlockName());
        		//新画面変更フラグ
        		regData.setMoveBlockChangeFlg(entity.getMoveBlockChangeFlg());
        		
                //[店舗ブロック設定情報].登録ユーザー ＝　[登録データ].ユーザーID
            	regData.setFirstUser(entity.getFirstUser());
                //[店舗ブロック設定情報].登録PGM ＝　[登録データ].登録PGM
            	regData.setFirstPgm(entity.getFirstPgm());
                //[店舗ブロック設定情報].登録TMSP ＝　[登録データ].登録TMSP
            	regData.setFirstTmsp(entity.getFirstTmsp());
	            //更新ユーザー
        		regData.setLastUser(entity.getLastUser());
	            //更新PGM
        		regData.setLastPgm(entity.getLastPgm());
                //[店舗ブロック設定情報].更新TMSP ＝　[登録データ].更新TMSP
            	regData.setLastTmsp(entity.getLastTmsp());
        		listConfirmMiseList.add(regData);
        	}
        }
        /**
         * ソートクラス
         * 
         * ≪ソート順≫
         *  1.新ブロックコード
         *  2.店舗コード
         */
        Comparator sortComparator = new Comparator() {
            public boolean equals(Object obj) {
                return (super.equals(obj));
            }
            public int compare(Object obj1, Object obj2) {
                String val1 = getSortVal(obj1);               
                String val2 = getSortVal(obj2);
                
                return val1.compareTo(val2);
            }
            /**
             * ソート順判断値生成取得処理
             * 
             * @param obj
             * @return
             */
            private String getSortVal(Object obj){
                String val = "";
                String moveBlockCd = ((UIMiseBlockInfo) obj).getMoveBlockCd();
                String miseCd = ((UIMiseBlockInfo) obj).getMiseCd();
                val = moveBlockCd
                    + miseCd;
                return val;
            }
        };
        Collections.sort(listConfirmMiseList, sortComparator);
        //[[確認画面用リスト]]をリターンする。
        return listConfirmMiseList;
    }
    
}
