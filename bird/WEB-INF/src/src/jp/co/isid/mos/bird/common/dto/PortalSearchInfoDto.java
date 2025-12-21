/**
 * 
 */
package jp.co.isid.mos.bird.common.dto;

import java.util.List;

import javax.faces.model.SelectItem;

import jp.co.isid.mos.bird.common.code.SearchRequestViewId;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;
import jp.co.isid.mos.bird.framework.entity.CtlBirdMenu;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * ポータル総合検索機能
 * 検索情報保持DTO
 * 
 * 作成日:2008/11/14
 * @author xkinu
 * 
 * 更新日:2013/09/03 周春建　201308-004_e-mosslesツールバーにお知らせ（タイトル検索）を追加
 */
public class PortalSearchInfoDto {
    /**
     * メニューＤＴＯ
     */
    private PullDownMenuDto pullDownMenuDto;
    /** 
     * アクション区分
     * （-1:非稼動 0:初期値 1:決定 2:戻る）
     */
    private int actionKind = -1;

    /** リクエスト元(呼び出し元)ウィンドウID */
    private int requesterWindowId;
	/**
	 * 遷移先VIEWID
	 */
	private String requestViewId;
	/**
	 * 遷移元VIEWID
	 */
	private String requesterViewId;
	/**
	 * 検索文字列
	 */
	private String searchWord;
	
	/**
	 * 検索区別フラグ
	 * （"1":タイトル検索 "2":全文検索 ）
	 */
	private String searchKbnFlg;
	
	/**
	 * @return requesterViewId を戻します。
	 */
	public String getRequesterViewId() {
		return requesterViewId;
	}
	/**
	 * @param requesterViewId を クラス変数requesterViewIdへ設定します。
	 */
	public void setRequesterViewId(String requesterViewId) {
		this.requesterViewId = requesterViewId;
	}
	/**
	 * @return requestViewId を戻します。
	 */
	public String getRequestViewId() {
		return requestViewId;
	}
	/**
	 * @param requestViewId を クラス変数requestViewIdへ設定します。
	 */
	public void setRequestViewId(String requestViewId) {
		this.requestViewId = requestViewId;
	}
	/**
	 * @return searchWord を戻します。
	 */
	public String getSearchWord() {
		return searchWord;
	}
	/**
	 * @param searchWord を クラス変数searchWordへ設定します。
	 */
	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}
	/**
	 * @return actionKind を戻します。
	 */
	public int getActionKind() {
		return actionKind;
	}
	/**
	 * @param actionKind を クラス変数actionKindへ設定します。
	 */
	public void setActionKind(int actionKind) {
		this.actionKind = actionKind;
	}
	/**
	 * @return requesterWindowId を戻します。
	 */
	public int getRequesterWindowId() {
		return requesterWindowId;
	}
	/**
	 * @param requesterWindowId を クラス変数requesterWindowIdへ設定します。
	 */
	public void setRequesterWindowId(int requesterWindowId) {
		this.requesterWindowId = requesterWindowId;
	}
    /**
     * 
     * @return
     */
    private S2Container getS2Container() {
        return SingletonS2ContainerFactory.getContainer();
    }

    /**
     * BIRDユーザー情報取得処理
     * @return birdUserInfo を戻します。
     */
    private BirdUserInfo getBirdUserInfo() {
        return (BirdUserInfo) getS2Container().getComponent(BirdUserInfo.class);
    }
    /**
     * 
     * @return
     */
    public List getListRequestViewId() {
    	if(getBirdUserInfo()==null) {
    		return null;
    	}
    	String userTypeCd = getBirdUserInfo().getMstUser().getUserTypeCd();
    	List listPullDown = SearchRequestViewId.getPullDownList(userTypeCd);
    	for(int i=0; i<listPullDown.size(); i++) {
    		SelectItem item = (SelectItem)listPullDown.get(i);
	        String viewId = ((String)item.getValue()).substring(0,6);
	        if(!"BCO".equals(viewId.substring(0,3))) {
		        List menu1= getPullDownMenuDto().getMenu1st();
		        boolean isActMenu = isActMemnu(menu1, viewId);
		        if (!isActMenu) {
		            List menu2= getPullDownMenuDto().getMenu2nd();
		            isActMenu = isActMemnu(menu2, viewId);
		        }
		        if (!isActMenu) {
		            List registMenu1 = getPullDownMenuDto().getRegistMenu1st();
		            isActMenu = isActMemnu(registMenu1, viewId);
		        }
		        if (!isActMenu) {
		            List registMenu2 = getPullDownMenuDto().getRegistMenu2nd();
		            isActMenu = isActMemnu(registMenu2, viewId);
		        }
		        if(!isActMenu) {
		        	listPullDown.remove(i);
		        	i--;
		        }
	        }
    	}
        return listPullDown;

    }
    /**
     * アクセス権限有画面か否か判断処理
     * 
     * @param menu メインメニュー
     * @param selectViewId 選択されたメニュー
     */
    private boolean isActMemnu(List menu, String selectViewId){
        //選択されてたメインメニューのフラグをONに設定
        ofMenu:for(int i = 0; i < menu.size(); i++){
            List submenu= ((CtlBirdMenu)menu.get(i)).getSubMenuList();
            for(int j = 0; j < submenu.size(); j++){
            	CtlBirdMenu eSubMenu = ((CtlBirdMenu)submenu.get(j));
                String viewId = eSubMenu.getViewId();
                if(selectViewId.equals(viewId)){
                    return true;
                }
            }
        }
    	return false;
    }
	public PullDownMenuDto getPullDownMenuDto() {
		return pullDownMenuDto;
	}
	public void setPullDownMenuDto(PullDownMenuDto pullDownMenuDto) {
		this.pullDownMenuDto = pullDownMenuDto;
	}
	public String getSearchKbnFlg() {
		return searchKbnFlg;
	}
	public void setSearchKbnFlg(String searchKbnFlg) {
		this.searchKbnFlg = searchKbnFlg;
	}
}
