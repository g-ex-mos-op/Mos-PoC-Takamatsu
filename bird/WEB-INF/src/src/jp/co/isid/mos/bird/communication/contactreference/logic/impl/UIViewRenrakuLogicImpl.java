/*
 * 作成日: 2006/02/10
 *
 */
package jp.co.isid.mos.bird.communication.contactreference.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.common.logic.GetInfoAccessControlLogic;
import jp.co.isid.mos.bird.common.logic.GetKanrenBunshoInfoLogic;
import jp.co.isid.mos.bird.communication.common.util.CommuniCationUtil;
import jp.co.isid.mos.bird.communication.contactreference.dao.UIViewRenrakuDao;
import jp.co.isid.mos.bird.communication.contactreference.dto.ContactReferenceDto;
import jp.co.isid.mos.bird.communication.contactreference.entity.UIViewRenraku;
import jp.co.isid.mos.bird.communication.contactreference.logic.UIViewRenrakuLogic;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;

/**
 * 連絡文書の取得ロジック
 * @author xyuchida
 */
public class UIViewRenrakuLogicImpl implements UIViewRenrakuLogic {

    public static final String LOGIC_ID = "BCM001L01";

    private static final String INFO_SHU_CONTACT = "01";
    
    //ロジック
    private GetInfoAccessControlLogic getInfoAccessControlLogic;
    private GetKanrenBunshoInfoLogic getKanrenBunshoInfoLogic;
    
    private UIViewRenrakuDao uiViewRenrakuDao;

    public GetInfoAccessControlLogic getGetInfoAccessControlLogic() {
        return getInfoAccessControlLogic;
    }
    public void setGetInfoAccessControlLogic(
            GetInfoAccessControlLogic getInfoAccessControlLogic) {
        this.getInfoAccessControlLogic = getInfoAccessControlLogic;
    }
    public UIViewRenrakuDao getUiViewRenrakuDao() {
        return uiViewRenrakuDao;
    }
    public void setUiViewRenrakuDao(UIViewRenrakuDao uiViewRenrakuDao) {
        this.uiViewRenrakuDao = uiViewRenrakuDao;
    }
    
    
    /**
     * 連絡文書の取得
     * @param birdUserInfo ユーザ情報
     * @param contactReferenceDto
     * @return 連絡文書
     */
    public List execute(BirdUserInfo birdUserInfo, ContactReferenceDto contactReferenceDto, String sysDate) {

        String title = contactReferenceDto.getTitle();
        String pubDate = contactReferenceDto.getPubDate();

        // LIKE条件項目に%を付与
        title = (title == null || title.length() == 0) ? null : "%" + title + "%";
        pubDate = (pubDate == null || pubDate.length() == 0) ? null : pubDate + "%";

        // select
        List contactList = getUiViewRenrakuDao().getViewFrom(
                title, pubDate, contactReferenceDto.getGyotaiKbn(), contactReferenceDto.getCateId()
                , birdUserInfo.getUserID()
                , sysDate);

        // 個別アクセス制御
        contactList = getGetInfoAccessControlLogic().execute(
                INFO_SHU_CONTACT, birdUserInfo, contactList);

        // 全件数設定
        contactReferenceDto.setCount(contactList.size());

        // ページ範囲インデックス設定
        int fromIndex = contactReferenceDto.getPageFirstRecordNumber();
        int toIndex = fromIndex + contactReferenceDto.getMaxPageCount();
        fromIndex = (fromIndex > contactList.size()) ? contactList.size() : fromIndex;
        toIndex = (toIndex > contactList.size()) ? contactList.size() : toIndex;

        // ページ範囲リストを返却
        List result =contactList.subList(fromIndex, toIndex);
        
        //関連文書を設定
        //setKanrenBunsho(result);
        setKanrenBunsho(result,birdUserInfo,sysDate);
        return result;

    }

    /**
     * 関連文書を設定します。
     * @param listBunshoKokai 関連文書
     */
    private void setKanrenBunsho(List listBunshoKokai, BirdUserInfo birdUserInfo, String sysDate){
    	String publicTutatuDate = CommuniCationUtil.getTutatuPubDate(sysDate);
    	for(int i = 0; i < listBunshoKokai.size(); i++){
            UIViewRenraku entity = (UIViewRenraku)listBunshoKokai.get(i);
            // 関連文書情報取得
            List listKanren = getGetKanrenBunshoInfoLogic().execute(
            		birdUserInfo.getUserID()
            		, INFO_SHU_CONTACT, entity.getRegDate(), entity.getSeq()
            		, sysDate, publicTutatuDate);
            entity.setListKenrenFile(listKanren);
        }
    }

    /**
     * 関連文書検索ロジックを取得します。
     * @return 関連文書検索ロジック
     */
    public GetKanrenBunshoInfoLogic getGetKanrenBunshoInfoLogic() {
        return getKanrenBunshoInfoLogic;
    }

    /**
     * 関連文書検索ロジックを設定します。
     * @param 関連文書検索ロジック
     */
    public void setGetKanrenBunshoInfoLogic(
            GetKanrenBunshoInfoLogic getKanrenBunshoInfoLogic) {
        this.getKanrenBunshoInfoLogic = getKanrenBunshoInfoLogic;
    }

}
