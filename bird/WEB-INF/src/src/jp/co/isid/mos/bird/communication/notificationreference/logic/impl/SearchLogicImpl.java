/**
 * 更新日：2007/08/24 公開開始月をFrom～Toで指定できるように変更
 */
package jp.co.isid.mos.bird.communication.notificationreference.logic.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.bizsupport.bunsautoamtregist.util.BunsAutoAmtRegistUtil;
import jp.co.isid.mos.bird.common.code.InfoShu;
import jp.co.isid.mos.bird.common.code.UserType;
import jp.co.isid.mos.bird.communication.common.util.CommuniCationUtil;
import jp.co.isid.mos.bird.communication.notificationreference.dao.MstCategoryInfoDao;
import jp.co.isid.mos.bird.communication.notificationreference.dao.UIViewTutatuDao;
import jp.co.isid.mos.bird.communication.notificationreference.entity.MstCategoryInfo;
import jp.co.isid.mos.bird.communication.notificationreference.entity.UIViewTutatu;
import jp.co.isid.mos.bird.communication.notificationreference.entity.ViewTutatu;
import jp.co.isid.mos.bird.communication.notificationreference.logic.SearchLogic;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.FullTextSearchDto;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.MissingDataException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.exception.NotRelevantException;
//import com.ultraseek.xpa.search.SearchResult;
import net.n2sm.search.client.api.SearchResult;

/**
 * 通達照会 検索ロジック
 *
 * @author xkinu
 * 更新日：2008/02/19 ASPAC T.Kinugawa WAS高負荷対応
 * 更新日：2010/03/01 ASPAC T.Kinugawa 公開時間仕様追加対応
 *                    16時より前の場合はシステム日付の前日の日付を
 *                    16時以後の場合はシステム日付を、公開対象日の終了日として設定します。
 */
public class SearchLogicImpl implements SearchLogic {
	/** ロジックＩＤ：BCM002L04 */
    public static final String LOGIC_ID = "BCM002L04";
    /** DAO【通達情報】*/
    private UIViewTutatuDao uiViewTutatuDao;
    /** DAO【カテゴリ情報】*/
    private MstCategoryInfoDao mstCategoryInfoDao;

    /**
     * 事前条件処理
     *
     * @param map
     * @throws ApplicationException
     */
    private void validate(final Map params) {
        BirdUserInfo userInfo = (BirdUserInfo)params.get(PK_USERINFO);
        if(userInfo == null){
            //MSG【E0202】(がありません。)
            throw new MissingDataException("ユーザー情報");
        }
        //システム日付
        String sysDate = (String)params.get(PK_SYSDATE);
        if (BunsAutoAmtRegistUtil.isNull(sysDate)) {
            throw new MissingDataException("システム日付");
        }
        //公開開始月
        String pubDate = (String)params.get(PK_PUBDATE);
        if (BunsAutoAmtRegistUtil.isNull(pubDate)) {
            throw new NotNullException("公開開始月From", "inputPubDate", 0);
        }
        //公開開始月To
        String pubDateTo = (String)params.get(PK_PUBDATE_TO);
        if (BunsAutoAmtRegistUtil.isNull(pubDateTo)) {
            throw new NotNullException("公開開始月To", "inputPubDateTo", 0);
        }
        //公開開始月 From-Toチェック
        if (pubDate.compareTo(pubDateTo) > 0) {
            throw new NotRelevantException("公開開始月To", "公開開始月Fromと同じ又は未来月", "inputPubDate", 0);
        }

    }
	/* (非 Javadoc)
	 * @see jp.co.isid.mos.bird.communication.notificationreference.logic.SearchLogic#execute(java.util.Map)
	 */
	public Map execute(Map params) {
//Runtime rt = Runtime.getRuntime();
//System.out.println("★★★１．" + rt.freeMemory() + "　　　" + rt.totalMemory());
        //１．事前条件処理を実行する。
        validate(params);
        //２．リターン値Mapをインスタンス化する。
        Map rparam = new HashMap();

        //ユーザー情報
        BirdUserInfo userInfo = (BirdUserInfo)params.get(PK_USERINFO);
        String pubDate = (String)params.get(PK_PUBDATE) + "01";
        String pubDateTo = (String)params.get(PK_PUBDATE_TO) + "99";
        String gyotaiKbn = (String)params.get(PK_GYOTAIKBN);
        String title = (String)params.get(PK_TITLE);
        //システム日付
        String sysDate = (String)params.get(PK_SYSDATE);
        //FullTextSearch DTO
        FullTextSearchDto fullTextSearchDto = (FullTextSearchDto) params.get(PK_FULLTEXTSEARCH_DTO);
        //FullTextSearchの検索結果を元に検索条件を作成
        List fullTextSearchSearchWord = makeFullTextSearchSearchWord(fullTextSearchDto);

        // LIKE条件項目に%を付与
        title = (title == null || title.length() == 0) ? null : "%" + title + "%";
//        pubDate = (pubDate == null || pubDate.length() == 0) ? null : pubDate + "%";
//        System.out.println("★★★２（通達検索SQL実行前）．" + rt.freeMemory() + "　　　" + rt.totalMemory());
        // 通達管理情報取得【Dao:通達照会.通達情報の取得】
		String userTypeCd = userInfo.getMstUser().getUserTypeCd();
		String userId = userInfo.getUserID();
		boolean kobetsuFlg = false;
		boolean misebetsuFlg = false;
		/* BT15IAID 情報アクセス制御業態個別設定 */
		if (UserType.HONBU.equals(userTypeCd)) {
		    // 本部：ユーザー対応オーナーが存在する（＝販社）場合は、オーナーと同様の処理
		    if (userInfo.getUserOner() != null
		            && userInfo.getUserOner().size() > 0) {
		        // オーナー：
		    	kobetsuFlg = true;
		    	misebetsuFlg = true;
		    }
		}
		else if (UserType.ONER.equals(userTypeCd)) {
		    // オーナー：
			kobetsuFlg = true;
			misebetsuFlg = true;
		}
		else if (UserType.TENPO.equals(userTypeCd)) {
		    // 店舗：
			kobetsuFlg = true;
			misebetsuFlg = true;
		}
		else {
		    throw new FtlSystemException("公開対象制限");
		}
        String publicTutatuDate = CommuniCationUtil.getTutatuPubDate(sysDate);

        List listKoukai = getUiViewTutatuDao().select(
        		INFO_SHU
        		, pubDate, pubDateTo, gyotaiKbn, title
        		,  userId, userTypeCd
        		,  publicTutatuDate, fullTextSearchSearchWord
        		,  kobetsuFlg ,misebetsuFlg);
//System.out.println("★★★３（通達検索SQL実行後）．" + rt.freeMemory() + "　　　" + rt.totalMemory());
		// 検索用Entityから表示用Entityに変換
		List listTutatu = new ArrayList(0);
		int listSize = listKoukai.size();
		for (int i = 0; i < listSize; i++) {
		    listTutatu.add(i, doCastEntity((ViewTutatu) listKoukai.get(0)));
		    listKoukai.remove(0);
		}
//System.out.println("★★★４（検索用Entityから表示用Entityに変換後）．" + rt.freeMemory() + "　　　" + rt.totalMemory());
        // カテゴリリスト取得
        List listCategory = getMstCategoryInfoDao().getCategory(INFO_SHU
        		, title, pubDate, pubDateTo, gyotaiKbn, null, userInfo.getUserID(), fullTextSearchSearchWord);
//System.out.println("★★★５（カテゴリリスト取得後）．" + rt.freeMemory() + "　　　" + rt.totalMemory());
        //カテゴリー表示フラグ再設定処理
        resetCategory(listCategory, listTutatu);
//System.out.println("★★★６（カテゴリー表示フラグ再設定処理後）．" + rt.freeMemory() + "　　　" + rt.totalMemory());
        //カテゴリリストへ『全て』を追加。
        createDefaultCateGory(listCategory);
//System.out.println("★★★７（カテゴリリストへ『全て』を追加後）．" + rt.freeMemory() + "　　　" + rt.totalMemory());

        rparam.put(RK_LIST_SEARCHDATA, listTutatu);
        rparam.put(RK_LIST_SEARCHDATA_CATEGORY, listCategory);
//System.out.println("★★★８（リターン値Mapへ全てのデータを格納後）．" + rt.freeMemory() + "　　　" + rt.totalMemory());
		return rparam;
	}

    /**
     * カテゴリー表示フラグ再設定処理
     *
     * @param categoryList
     * @param tutatuList
     */
    private void resetCategory(List listCategory, List listTutatu) {
        //表示カテゴリ情報作成
        categLabel: for (Iterator cate = listCategory.iterator(); cate.hasNext();) {
            MstCategoryInfo eCategory = (MstCategoryInfo) cate.next();
            if(eCategory.getCateId() == null) {
            	continue;
            }
            for (Iterator it = listTutatu.iterator(); it.hasNext();) {
            	UIViewTutatu uiViewTutatu = (UIViewTutatu) it.next();
            	if(eCategory.getCateId().equals(uiViewTutatu.getCateId())) {
            		continue categLabel;
            	}
        	}//end of for (int i=0; i<tutatuList.size(); i++)
            // データ無しカテゴリはリストから除外
            cate.remove();
        }

    }
    /**
     * カテゴリリストへ『全て』を追加
     * @param categoryList
     *
     * 更新日2011/04/11：「全て」→「全てのお知らせ」に変更
     */
    private void createDefaultCateGory(List categoryList){
        MstCategoryInfo all = new MstCategoryInfo();
        all.setCateId(null);
        //
        all.setCateName("全ての"+InfoShu.TUTATU_NAME);
        categoryList.add(0, all);
    }

    /**
     * FullTextSearchの検索結果を元にファイル名の検索条件を作成
     * @param dto
     * @return
     */
    private List makeFullTextSearchSearchWord(FullTextSearchDto dto) {

        List fullTextSearchFileList = new ArrayList();
        if (dto != null) {
            for (Iterator ite = dto.getSearchResultList().iterator(); ite.hasNext();) {
                try {
                    String url = ((SearchResult) ite.next()).getUrlLink();
                    String filename = url.substring(url.lastIndexOf("/") + 1);
                	if(filename != null && filename.length() >= 13){
                		fullTextSearchFileList.add(filename.substring(0, 8) + filename.substring(9, 13));
                	}
                }
                catch (Exception ex) {
                	ex.printStackTrace();
                    throw new FtlSystemException("ファイル名", "", ex);
                }
            }
        }

        return fullTextSearchFileList.isEmpty() ? null : fullTextSearchFileList;
    }
    /**
     *
     * @param entity
     * @return
     */
    private UIViewTutatu doCastEntity(ViewTutatu entity) {
        UIViewTutatu uiEntity = new UIViewTutatu();

        uiEntity.setAttachFl1(entity.getAttachFl1());
        uiEntity.setAttachFl2(entity.getAttachFl2());
        uiEntity.setAttachFl3(entity.getAttachFl3());
        uiEntity.setAttachName1(entity.getAttachName1());
        uiEntity.setAttachName2(entity.getAttachName2());
        uiEntity.setAttachName3(entity.getAttachName3());
        uiEntity.setCateId(entity.getCateId());
        uiEntity.setCateName(entity.getCateName());
        uiEntity.setDescription(entity.getDescription());
        uiEntity.setFileIndex(entity.getFileIndex());
        uiEntity.setFileName(entity.getFileName());
        uiEntity.setGyotaiKbn(entity.getGyotaiKbn());
        uiEntity.setKanrenFileCnt(entity.getKanrenFileCnt());
        uiEntity.setKobetsuFlg(entity.getKobetsuFlg());
        uiEntity.setMiseFlg(entity.getMiseFlg());
        uiEntity.setPubDate(entity.getPubDate());
        uiEntity.setPubOrg(entity.getPubOrg());
        uiEntity.setPubOrgName(entity.getPubOrgName());
        uiEntity.setPubUser(entity.getPubUser());
        uiEntity.setRCompanyCd(entity.getRCompanyCd());
        uiEntity.setRegDate(entity.getRegDate());
        uiEntity.setSakujoFlg(entity.getSakujoFlg());
        uiEntity.setSeq(entity.getSeq());
        uiEntity.setSubTitle(entity.getSubTitle());
        uiEntity.setTitle(entity.getTitle());
        uiEntity.setUserNameKj(entity.getUserNameKj());
        entity = null;
        return uiEntity;
    }

	/**
	 * @return uiViewTutatuDao を戻します。
	 */
	public UIViewTutatuDao getUiViewTutatuDao() {
		return uiViewTutatuDao;
	}
	/**
	 * @param uiViewTutatuDao 設定する uiViewTutatuDao。
	 */
	public void setUiViewTutatuDao(UIViewTutatuDao uiViewTutatuDao) {
		this.uiViewTutatuDao = uiViewTutatuDao;
	}
	/**
	 * @return mstCategoryInfoDao を戻します。
	 */
	public MstCategoryInfoDao getMstCategoryInfoDao() {
		return mstCategoryInfoDao;
	}
	/**
	 * @param mstCategoryInfoDao 設定する mstCategoryInfoDao。
	 */
	public void setMstCategoryInfoDao(MstCategoryInfoDao mstCategoryInfoDao) {
		this.mstCategoryInfoDao = mstCategoryInfoDao;
	}
}
