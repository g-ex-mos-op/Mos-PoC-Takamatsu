package jp.co.isid.mos.bird.bizadmin.blockmaintenance.dto;

import jp.co.isid.mos.bird.common.entity.MstUserCompany;

/**
 * ブロックメンテナンス DTO
 * 
 * ブロックメンテナンス画面用ののDTOです。
 * 
 * @author xkinu
 *
 */
public class RequestDto {
    /**
     * リクエストウィンドウID
     */
    private int windowId = 0;

    /* 条件フィールド：対象会社コード  */
    private String targetCompanyCd;
    /* 条件フィールド：対象支部コード */
    private String targetSibuCd;

    /* 編集フィールド：対象ブロック  */
    private String dispBlockCd;
    /* 編集フィールド：移動先 */
    private String moveBlockCd;
    /* DTO【自画面SessionDto】 */
    private SessionDto blockMainteSesDto;
    /* 編集された先頭の店舗インデックス */
    private int focusIndex = 0;
    /* ソート最後の店舗インデックス */
    private int focusInitIndex = 0;
    
    /**
     * クリア処理
     *
     */
    public void clear(){
        //条件項目値クリア
        setTargetCompanyCd(null);
        setTargetSibuCd(null);
        setDispBlockCd(null);
        setMoveBlockCd(null);
   }
    
    /**
	 * @return blockMainteSesDto を戻します。
	 */
	public SessionDto getBlockMainteSesDto() {
		return blockMainteSesDto;
	}
	/**
	 * @param blockMainteSesDto 設定する blockMainteSesDto。
	 */
	public void setBlockMainteSesDto(SessionDto blockMainteSesDto) {
		this.blockMainteSesDto = blockMainteSesDto;
	}
	/**
	 * @return targetCompanyCd を戻します。
	 */
	public int getTargetCompanyCdIndex() {
		for(int i=0; i<getBlockMainteSesDto().getListCompany().size(); i++) {
			MstUserCompany entity = (MstUserCompany)getBlockMainteSesDto().getListCompany().get(i);
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
	 * @return windowId を戻します。
	 */
	public int getWindowId() {
		return windowId;
	}
	/**
	 * @param windowId 設定する windowId。
	 */
	public void setWindowId(int windowId) {
		this.windowId = windowId;
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
	 * @return blockCd を戻します。
	 */
	public String getDispBlockCd() {
		return dispBlockCd;
	}

	/**
	 * @param blockCd 設定する blockCd。
	 */
	public void setDispBlockCd(String blockCd) {
		this.dispBlockCd = blockCd;
	}

	/**
	 * @return focusndex を戻します。
	 */
	public int getFocusIndex() {
		return focusIndex;
	}

	/**
	 * @param focusndex 設定する focusndex。
	 */
	public void setFocusIndex(int focusIndex) {
		this.focusIndex = focusIndex;
	}
	/**
	 * @return focusInitIndex を戻します。
	 */
	public int getFocusInitIndex() {
		return focusInitIndex;
	}

	/**
	 * @param focusInitIndex 設定する focusInitIndex。
	 */
	public void setFocusInitIndex(int focusInitIndex) {
		this.focusInitIndex = focusInitIndex;
	}
    
}
