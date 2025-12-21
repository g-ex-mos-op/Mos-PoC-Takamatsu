/**
 * 
 */
package jp.co.isid.mos.bird.bizsupport.tenantpaypdfregist.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.bizsupport.tenantpaypdfregist.entity.MstCategoryShubetuInfo;
import jp.co.isid.mos.bird.bizsupport.tenantpaypdfregist.entity.TrnPayDetaileStatement;
import jp.co.isid.mos.bird.bizsupport.tenantpaypdfregist.util.TenantPayPdfRegistUtil;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.UploadDto;
import jp.co.isid.mos.bird.framework.util.MakeSessionKey;

import org.apache.myfaces.custom.fileupload.UploadedFile;

/**
 * テナント入金明細登録セッションDTO
 * 
 * 作成日:2009/06/19
 * @author xkinu
 *
 */
public class SessionDto implements UploadDto {
    /* セッションキー作成クラス生成 */
    private MakeSessionKey mkSession = new MakeSessionKey();
    /* 現行セッションKey */
    private String nowSessionKey;
    /**
     * ログインユーザー情報
     */
    private BirdUserInfo birdUserInfo;
    /**
     * BIRD日付情報
     */
    private BirdDateInfo birdDateInfo;
    
    /**
     * 最大ウィンドウID
     */
    private int maxWindowId = 0;
    /**
     * 最大データ保持件数
     */
    private int maxSize=5;
    
    /**
     * [条件項目]店ーコード
     */ 
    private Map miseCd = new HashMap();
    /**
     * [条件項目]対象年月
     */ 
    private Map taishoYm = new HashMap();
    /**
     * [条件項目]回数
     */ 
    private Map kaisuu = new HashMap();

    /**
     * 検索結果一覧
     */
    private List listSearchData;

    /**
     * [条件項目]対象年月リスト
     */
    private List listTaishoYm;
    
    /**
     * 検索済みフラグ
     */
    private Map searched = new HashMap();
    /**
     * 登録対象インデックス
     * 1:新規登録　2:更新　9:削除
     */
    private int registType=1;
    /**
     * 共通画面遷移時のDTO保持オブジェクト
     */
    private Map holdReqDto = new HashMap();
    /**
     * 登録対象カテゴリー情報エンティティ
     */
    private MstCategoryShubetuInfo categoryShubetuInfo;
    /**
     * 登録対象エンティティ
     */
    private TrnPayDetaileStatement registDataEntity;
    /**
     * アップロードファイルオブジェクト
     */
    private UploadedFile uploadedFile;
    /**
     * 
     */
    private String tempFileName;
    /**
	 * @return holdReqDto を戻します。
	 */
	public RequestDto getHoldReqDto(int windowId) {
		return (RequestDto)holdReqDto.get(new Integer(windowId));
	}

	/**
	 * @param holdReqDto を クラス変数holdReqDtoへ設定します。
	 */
	public void setHoldReqDto(int windowId, RequestDto holdReqDto) {
		this.holdReqDto.put(new Integer(windowId), holdReqDto);
	}
    /**
     * 検索済みフラグ
     * @return
     */
    public boolean isSearched(int windowId) {
        if (searched.containsKey(new Integer(windowId))) {
            if (Boolean.TRUE.equals((Boolean) searched.get(new Integer(windowId)))) {
                return true;
            }
        }
        return false;
    }
    /**
     * 検索済みフラグ設定処理
     * @param flg  true:検索済み
     */
    public void setSearched(int windowId, boolean flg) {
        searched.put(new Integer(windowId), new Boolean(flg));
    }
    /**
     * 全WindowIDの検索済みフラグクリア
     */
    public void clearSearched() {
        searched = new HashMap();
    }

    /**
	 * @return listTaishoYm を戻します。
	 */
	public List getListTaishoYm() {
		return listTaishoYm;
	}

	/**
	 * @param listTaishoYm を クラス変数listTaishoYmへ設定します。
	 */
	public void setListTaishoYm(List listTaishoYm) {
		this.listTaishoYm = listTaishoYm;
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
     * ウィンドウIDを更新します。
     */
    public int updateWindowid() {
       return createWindowId();
    }
	/**
	 * 店コード取得処理
	 * @param windowId
	 * @return miseCd を戻します。
	 */
	public String getMiseCd(int windowId) {
		return (String)miseCd.get(new Integer(windowId));
	}
	/**
	 * 店コード設定処理
	 * 
	 * @param windowId
	 * @param miseCd 設定する miseCd。
	 */
	public void setMiseCd(int windowId, String miseCd) {
		this.miseCd.put(new Integer(windowId), miseCd);
	}
	/**
	 * 対象年月取得処理
	 * 
	 * @return taishoYm を戻します。
	 */
	public String getTaishoYm(int windowId) {
		return (String)taishoYm.get(new Integer(windowId));
	}

	/**
	 * 対象年月設定処理
	 * 
	 * @param taishoYm を クラス変数taishoYmへ設定します。
	 */
	public void setTaishoYm(int windowId, String taishoYm) {
		this.taishoYm.put(new Integer(windowId), taishoYm);
	}
	/**
	 * 回数取得処理
	 * 
	 * @return kaisuu を戻します。
	 */
	public String getKaisuu(int windowId) {
		return (String)kaisuu.get(new Integer(windowId));
	}

	/**
	 * 回数設定処理
	 * 
	 * @param taishoYm を クラス変数taishoYmへ設定します。
	 */
	public void setKaisuu(int windowId, String kaisuu) {
		this.kaisuu.put(new Integer(windowId), kaisuu);
	}
	/**
	 * @return birdDateInfo を戻します。
	 */
	public BirdDateInfo getBirdDateInfo() {
		return birdDateInfo;
	}

	/**
	 * @param birdDateInfo を クラス変数birdDateInfoへ設定します。
	 */
	public void setBirdDateInfo(BirdDateInfo birdDateInfo) {
		this.birdDateInfo = birdDateInfo;
	}

	/**
	 * @return birdUserInfo を戻します。
	 */
	public BirdUserInfo getBirdUserInfo() {
		return birdUserInfo;
	}

	/**
	 * @param birdUserInfo を クラス変数birdUserInfoへ設定します。
	 */
	public void setBirdUserInfo(BirdUserInfo birdUserInfo) {
		this.birdUserInfo = birdUserInfo;
	}
	/**
	 * @return maxSize を戻します。
	 */
	public int getMaxSize() {
		return maxSize;
	}

	/**
	 * @param maxSize を クラス変数maxSizeへ設定します。
	 */
	public void setMaxSize(int maxSize) {
		this.maxSize = maxSize;
	}

	/**
	 * @return maxWindowId を戻します。
	 */
	public int getMaxWindowId() {
		return maxWindowId;
	}

	/**
	 * @param maxWindowId を クラス変数maxWindowIdへ設定します。
	 */
	public void setMaxWindowId(int maxWindowId) {
		this.maxWindowId = maxWindowId;
	}

	/**
	 * @return nowSessionKey を戻します。
	 */
	public String getNowSessionKey() {
		return nowSessionKey;
	}

	/**
	 * @param nowSessionKey を クラス変数nowSessionKeyへ設定します。
	 */
	public void setNowSessionKey(String nowSessionKey) {
		this.nowSessionKey = nowSessionKey;
	}
	/**
	 * 新規セッションKey生成処理
	 * 
	 * １．クラス変数【セッションキー作成クラス】で新規セッションKeyを生成する。
	 * ２．処理７で生成した新規セッションKeyを
	 *         DTO【自画面Session】.現行セッションKeyへ設定する。
	 * @param windowId
	 */
	public String makeSessionKey() {
		//１．クラス変数【セッションキー作成クラス】で新規セッションKeyを生成する。
		String key = mkSession._makeSessionKey();
		//２．処理７で生成した新規セッションKeyを
		//        DTO【自画面Session】.現行セッションKeyへ設定する。
		setNowSessionKey(key);
		return key;
	}

    /**
     * 操作エラー判断処理
     * 
     * 現行のウィンドウが無効の場合は false
     *                   有効の場合は true
     * 
     * @param RequestDto
     * @return
     */
    public boolean isValidSessionKey(RequestDto reqDto){
        return mkSession.isValidSessionKey( 
                     getNowSessionKey(),  reqDto.getSesstionKey() );
    }

	/**
	 * 登録対象エンティティ取得処理
	 * 
	 * @return registDataEntity を戻します。
	 */
	public TrnPayDetaileStatement getRegistDataEntity() {
		return registDataEntity;
	}

	/**
	 * 登録対象エンティティ設定処理
	 * @param entity を クラス変数registDataEntityへ設定します。
	 */
	public void setRegistDataEntity(TrnPayDetaileStatement entity) {
		this.registDataEntity = entity;
	}

	/* (非 Javadoc)
	 * @see jp.co.isid.mos.bird.framework.dto.UploadDto#getTempFileName()
	 */
	public String getTempFileName() {
		return tempFileName;
	}

	/* (非 Javadoc)
	 * @see jp.co.isid.mos.bird.framework.dto.UploadDto#getUploadedFile()
	 */
	public UploadedFile getUploadedFile() {
		// TODO 自動生成されたメソッド・スタブ
		return uploadedFile;
	}
	/* (非 Javadoc)
	 * @see jp.co.isid.mos.bird.framework.dto.UploadDto#setUploadedFile(org.apache.myfaces.custom.fileupload.UploadedFile)
	 */
	public void setUploadedFile(UploadedFile uploadFile) {
		// TODO 自動生成されたメソッド・スタブ
		this.uploadedFile =uploadFile;
	}


	/* (非 Javadoc)
	 * @see jp.co.isid.mos.bird.framework.dto.UploadDto#getUploadFileName()
	 */
	public String getUploadFileName() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	/* (非 Javadoc)
	 * @see jp.co.isid.mos.bird.framework.dto.UploadDto#setTempFileName(java.lang.String)
	 */
	public void setTempFileName(String tempFileName) {
		this.tempFileName = tempFileName;		
	}

	/* (非 Javadoc)
	 * @see jp.co.isid.mos.bird.framework.dto.UploadDto#setUploadFileName(java.lang.String)
	 */
	public void setUploadFileName(String uploadFileName) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	/**
	 * @return listSearchData を戻します。
	 */
	public List getListSearchData() {
		return listSearchData;
	}

	/**
	 * @param listSearchData を クラス変数listSearchDataへ設定します。
	 */
	public void setListSearchData(List listSearchData) {
		this.listSearchData = listSearchData;
	}
	/**
	 * @return registType を戻します。
	 */
	public int getRegistType() {
		return registType;
	}

	/**
	 * @param registType を クラス変数registTypeへ設定します。
	 */
	public void setRegistType(int registType) {
		this.registType = registType;
	}
	/**
	 * @return registType を戻します。
	 */
	public String getRegistTypeName() {
		return TenantPayPdfRegistUtil.getRegistTypeName(registType);
	}

	public MstCategoryShubetuInfo getCategoryShubetuInfo() {
		return categoryShubetuInfo;
	}

	public void setCategoryShubetuInfo(MstCategoryShubetuInfo categoryShubetuInfo) {
		this.categoryShubetuInfo = categoryShubetuInfo;
	}
}
