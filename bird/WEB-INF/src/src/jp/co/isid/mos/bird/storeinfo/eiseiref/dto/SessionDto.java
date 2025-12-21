/**
 * 
 */
package jp.co.isid.mos.bird.storeinfo.eiseiref.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 店舗衛生結果
 * DTO【SessionDto】
 * 
 * 作成日:2012/12/05
 * @author xkinu
 *
 */
public class SessionDto {
    /**
     * 最大ウィンドウID
     */
    private int maxWindowId = 0;
    /**
     * 実績年度リスト
     */
    private List listNendo = new ArrayList(0);
    /**
     * 保有店リスト
     */ 
    private List listMise = new ArrayList(0);
    /** DTO【Request情報】 */
    private Map requestDto = new HashMap();
    /** DTO【検索結果】 */
    private Map resultDto = new HashMap();
    
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
     * ウィンドウIDを生成します。
     */
    public int createWindowId() {
        int newWindowId = getMaxWindowId() + 1;
        setMaxWindowId(newWindowId);
        return newWindowId;
    }
	/**
	 * 保有店リスト
	 * @return クラス変数listMise を戻します。
	 */
	public List getListMise() {
		return listMise;
	}
	/**
	 * 保有店リスト
	 * @param listMise を クラス変数listMiseへ設定します。
	 */
	public void setListMise(List listMise) {
		this.listMise = listMise;
	}
	/**
	 * 実績年度リスト
	 * @return クラス変数listNendo を戻します。
	 */
	public List getListNendo() {
		return listNendo;
	}
	/**
	 * 実績年度リスト
	 * @param listNendo を クラス変数listNendoへ設定します。
	 */
	public void setListNendo(List listNendo) {
		this.listNendo = listNendo;
	}

	/**
	 * DTO【検索結果】取得処理
	 * @param windowId
	 * @return クラス変数requestDto を戻します。
	 */
	public RequestDto getResultDto(int windowId) {
		return (RequestDto)resultDto.get(new Integer(windowId));
	}

	/**
	 * @param resultDto を クラス変数resultDtoへ設定します。
	 */
	public void setResultDto(int windowId, RequestDto resultDto) {
		this.resultDto.put(new Integer(windowId), resultDto);
	}

	/**
	 * DTO【Request情報】
	 * @param windowId
	 * @return クラス変数requestDto を戻します。
	 */
	public RequestDto getRequestDto(int windowId) {
		return (RequestDto)requestDto.get(new Integer(windowId));
	}

	/**
	 * @param requestDto を クラス変数requestDtoへ設定します。
	 */
	public void setRequestDto(int windowId, RequestDto requestDto) {
		this.requestDto.put(new Integer(windowId), requestDto);
	}
}
