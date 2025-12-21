/*
 * 作成日: 2006/2/1
 */
package jp.co.isid.mos.bird.portal.top.action.inside.impl;

import java.util.Iterator;

import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.portal.top.action.inside.OsiraseInsideaction;
import jp.co.isid.mos.bird.portal.top.dto.PortalTopDto;
import jp.co.isid.mos.bird.portal.top.entity.UIOsirase;
import jp.co.isid.mos.bird.portal.top.logic.GetOsiraseLogic;
import jp.co.isid.mos.bird.portal.top.util.PortalTopUtil;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * ポータル売上速報出力アクション
 * 
 * @author xkinu
 */
public class OsiraseInsideactionImpl implements OsiraseInsideaction {

    /* アクションID */
    public static String initialize_ACTION_ID    = PortalTopUtil.SCREEN_ID+"A31";

    /* portalTopDto */
    private PortalTopDto portalTopDto;
    /* GetOsiraseLogic */
    private GetOsiraseLogic portalTopGetOsiraseLogic;
    /* 登録日（お知らせ詳細画面パラメータ） */
    private String regDate;
    /* シーケンス（お知らせ詳細画面パラメータ）*/
    private String seq;


    /**
     * 初期処理
     * @return 画面遷移情報
     */
	public String initialize() {
        // 売上情報取得
        portalTopDto.setListOsirase(getPortalTopGetOsiraseLogic().execute(
        		getBirdUserInfo(),getBirdDateInfo(), portalTopDto));
        return null;
    }
    /**
     * お知らせ詳細起動処理
     * @return 画面遷移情報
     */
    public String startWhatsNew() {
        // お知らせ詳細情報をDtoにセット
        for (Iterator i = portalTopDto.getListOsirase().iterator(); i.hasNext();) {
            UIOsirase wnew = (UIOsirase) i.next();
            if (wnew.getRegDate().equals(regDate) && wnew.getSeq().equals(seq)) {
                portalTopDto.setPubDateFrom(wnew.getPubDateFrom());
                portalTopDto.setPubOrgName(wnew.getPubOrgName());
                portalTopDto.setTitle(wnew.getTitle());
                portalTopDto.setSimpleMsg(wnew.getSimpleMsg());
                portalTopDto.setMessage(wnew.getMessage());
                break;
            }
        }
        return null;
    }
    /**
     * 
     * @return
     */
    private S2Container getS2Container() {
        return SingletonS2ContainerFactory.getContainer();
    }

    /**
     * BIRD日付情報取得処理
     * @return birdDateInfo を戻します。
     */
    private BirdDateInfo getBirdDateInfo() {
    	return (BirdDateInfo) getS2Container().getComponent(BirdDateInfo.class);
    }

    /**
     * BIRDユーザー情報取得処理
     * @return birdUserInfo を戻します。
     */
    private BirdUserInfo getBirdUserInfo() {
        return (BirdUserInfo) getS2Container().getComponent(BirdUserInfo.class);
    }
    /**
     * ポータルページ用Dto設定処理
     * @return portalTopDto
     */
    public PortalTopDto getPortalTopDto() {
        return portalTopDto;
    }
    /**
     * ポータルページ用Dto設定処理
     * @param portalTopDto
     */
    public void setPortalTopDto(PortalTopDto portalTopDto) {
        this.portalTopDto = portalTopDto;
    }

	/**
	 * @return portalTopGetOsiraseLogic を戻します。
	 */
	public GetOsiraseLogic getPortalTopGetOsiraseLogic() {
		return portalTopGetOsiraseLogic;
	}
	/**
	 * @param portalTopGetOsiraseLogic を クラス変数portalTopGetOsiraseLogicへ設定します。
	 */
	public void setPortalTopGetOsiraseLogic(GetOsiraseLogic portalTopGetOsiraseLogic) {
		this.portalTopGetOsiraseLogic = portalTopGetOsiraseLogic;
	}
    /**
     * お知らせ詳細登録日の設定
     * @return regDate を戻します。
     */
    public String getRegDate() {
        return regDate;
    }
    /**
     * お知らせ詳細登録日の設定
     * @param regDate regDate を設定。
     */
    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }
    /**
     * お知らせ詳細シーケンスの設定
     * @return seq を戻します。
     */
    public String getSeq() {
        return seq;
    }
    /**
     * お知らせ詳細シーケンスの設定
     * @param seq seq を設定。
     */
    public void setSeq(String seq) {
        this.seq = seq;
    }

}

