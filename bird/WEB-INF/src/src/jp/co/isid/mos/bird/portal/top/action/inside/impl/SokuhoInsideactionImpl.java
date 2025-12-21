/*
 * 作成日: 2006/2/1
 */
package jp.co.isid.mos.bird.portal.top.action.inside.impl;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import jp.co.isid.mos.bird.common.code.UserType;
import jp.co.isid.mos.bird.common.entity.UILists;
import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.framework.control.BirdContext;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.entity.CtlUserCompany;
import jp.co.isid.mos.bird.framework.exception.FileNotFoundException;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.logic.DownloadLogic;
import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.portal.top.action.inside.SokuhoInsideaction;
import jp.co.isid.mos.bird.portal.top.dto.PortalTopDto;
import jp.co.isid.mos.bird.portal.top.entity.UISoku;
import jp.co.isid.mos.bird.portal.top.logic.GetSokuLogic;
import jp.co.isid.mos.bird.portal.top.util.PortalTopUtil;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * ポータル売上速報出力アクション
 * 
 * @author xkinu
 */
public class SokuhoInsideactionImpl implements SokuhoInsideaction {

    /* アクションID */
    public static String initialize_ACTION_ID    = PortalTopUtil.SCREEN_ID+"A21";
    /* アクションID */
    public static String download_ACTION_ID    = PortalTopUtil.SCREEN_ID+"A22";

    // ファイルセパレーター
    private static final String FILE_SEPARATOR = System.getProperties().getProperty("file.separator");
    /* portalTopDto */
    private PortalTopDto portalTopDto;
    /* GetSokuLogic */
    private GetSokuLogic portalTopGetSokuLogic;
    /**
     * 初期処理
     * @return 画面遷移情報
     */
	public String initialize() {
	    if (getPortalTopDto().getListSokuho() == null) {
            //モス閲覧権限有りフラグの設定をします。
            List listCompany = getBirdUserInfo().getUserCompany();
            for(int i=0; i<listCompany.size(); i++) {
            	CtlUserCompany entity = (CtlUserCompany)listCompany.get(i);
            	if(CommonUtil.COMPANY_CD_MOS.equals(entity.getRCompanyCd())) {
        			//モスの会社コードがある場合、モス閲覧権限有りとし、
        			//DTO【自画面】.モス閲覧権限有りフラグへtrueを設定します。
            		getPortalTopDto().setMosVisitor(true);
            		break;
            	}
            }
	    	List listSearchData = getPortalTopGetSokuLogic().execute(
            		getBirdUserInfo(),getBirdDateInfo(), getPortalTopDto());
	    	if(listSearchData == null) {
	    		getPortalTopDto().setListSokuho(new ArrayList(0));
	    		return null;
	    	}
	    	List listTab = null;
    		String targetDate = getBirdDateInfo().getAppDate();
			try {
				String appLastMonth = DateManager.getPrevMonth(targetDate.substring(0,6), 1);
				String lastday = DateManager.getLastDayOfMonth(appLastMonth);
				targetDate = appLastMonth+lastday;
				//前月末日を設定します。
				getPortalTopDto().setAppLastMonthDay(targetDate);
				
			}catch (Exception ex) {
				throw new FtlSystemException("売上速報初期処理", "営業日前月の年月日取得処理", ex);
			}
			
	    	if(UserType.HONBU.equals(getBirdUserInfo().getMstUser().getUserTypeCd())) {
	    		listTab = createListTabHonbu(listSearchData);
	    	}
	    	if(UserType.ONER.equals(getBirdUserInfo().getMstUser().getUserTypeCd())) {
	    		listTab = createListTabOner(listSearchData);
	    	}
	    	if(UserType.TENPO.equals(getBirdUserInfo().getMstUser().getUserTypeCd())) {
	    		listTab = createListTabMise(listSearchData);
	    	}
            // 売上情報取得
	    	getPortalTopDto().setListSokuho(listTab);
        }
	    return null;
    }
	/**
	 * 本部用タブ別速報情報作成処理
	 * 
	 * @return
	 */
	private List createListTabHonbu(List listSearchData) {
		List listTab = new ArrayList(0);
		listTab.add(new UILists("0", "全店"));
		listTab.add(new UILists("1", "既存店"));
    	for(int t=0; t<listTab.size(); t++) {
    		UILists tabData = (UILists)listTab.get(t);
    		//格納対象の既存フラグを取得します。
    		String kizonFlg = tabData.getKey();
    		//List[[全セグメント情報]]
    		List listSegmentData = new ArrayList(0);
    		for(int i=0; i<listSearchData.size(); i++) {
    			//[現行売上情報]へインデックスiのエンティティを設定します。
    			UISoku entity = (UISoku)listSearchData.get(i);
    			//格納対象セグメントタイプとして[現行売上情報].セグメントタイプの値を取得します。
    			String segmentType = entity.getSegmentType();
    			String segmentName = entity.getSegmentName();
    			//List[[各セグメント情報]]
    			List listData = new ArrayList(0);
				//格納対象の既存フラグと[現行売上情報].既存フラグが同じ場合下記の処理を行います。
    			while(kizonFlg.equals(entity.getKizonFlg())) {
    				//格納対象セグメントタイプと[現行売上情報].セグメントタイプが同じ場合下記の処理を行います。
    				if(segmentType.equals(entity.getSegmentType())) {
        				//List[[全セグメント情報]][[各セグメント情報]].[売上速報]へ[現行売上情報]を設定します。
    					listData.add(entity);
    					i++;
    					if(i>=listSearchData.size()) {
        					UILists segmentData = new UILists();
        					segmentData.setKey(segmentType);
        					segmentData.setKeyName(segmentName);
        					segmentData.setListData(listData);
        					listSegmentData.add(segmentData);
    						break;
    					}
    					entity = (UISoku)listSearchData.get(i);
    				}
    				else {
    					UILists segmentData = new UILists();
    					segmentData.setKey(segmentType);
    					segmentData.setKeyName(segmentName);
    					segmentData.setListData(listData);
    					listSegmentData.add(segmentData);
    					i--;
    					break;
    				}
    				
    			}// end of while(kizonFlg.equals(entity.getKizonFlg()))
    			if(!kizonFlg.equals(entity.getKizonFlg()) && listData.size()>0) {
					UILists segmentData = new UILists();
					segmentData.setKey(segmentType);
					segmentData.setKeyName(segmentName);
					segmentData.setListData(listData);
					listSegmentData.add(segmentData);
					break;
    			}
    		}//end of for(int i=0; i<sokuList.size(); i++)
			//タブ別のセグメント別データを設定します。
			if(listSegmentData.size()>0) {
				tabData.setListData(listSegmentData);
			}
    	}// end of for(int t=0; t<listTab.size(); t++)
		return listTab;
	}
	/**
	 * オーナー用タブ別速報情報作成処理
	 * 
	 * @return
	 */
	private List createListTabOner(List listSearchData) {
		List listTab = new ArrayList(0);
		UILists tabData = new UILists("0", "オーナー");
		//List[[全会社情報]]
		List listCompany = new ArrayList(0);
		for(int i=0; i<listSearchData.size(); i++) {
			UISoku entity = (UISoku)listSearchData.get(i);
			//名称を設定します。(※SEGMENT_NAMEには'全店'が設定されています。)
			String companyCd = entity.getSegmentType();
			String comapnyName = entity.getSegmentName();
			//List[[各会社情報]]
			List listData = new ArrayList(0);
			while(companyCd.equals(entity.getSegmentType())) {
				//List[[全会社情報]][[各会社情報]].[売上速報]へ[現行売上情報]を設定します。
				listData.add(entity);
				i++;
				if(i>=listSearchData.size()) {
					UILists companyData = new UILists();
					companyData.setKey(companyCd);
					companyData.setKeyName(comapnyName);
					companyData.setListData(listData);
					listCompany.add(companyData);
					break;
				}
				entity = (UISoku)listSearchData.get(i);
			}
			if(companyCd.equals(entity.getSegmentType()) == false) {
				UILists companyData = new UILists();
				companyData.setKey(companyCd);
				companyData.setKeyName(comapnyName);
				companyData.setListData(listData);
				listCompany.add(companyData);
				i--;
			}
		}
		//タブ別のセグメント別データを設定します。
		if(listCompany.size()>0) {
			if(listCompany.size()==2) {
				//所属会社が1社だけの場合は、先頭行の'全店'のみにする。
				listCompany.remove(0);
			}
			tabData.setListData(listCompany);
		}
		listTab.add(tabData);
		return listTab;
	}    /**
	/**
	 * 店舗用タブ別速報情報作成処理
	 * 
	 * @return
	 */
	private List createListTabMise(List listSearchData) {
		List listTab = new ArrayList(0);
		UILists tabData = new UILists("0", "店舗");
		List listSegment = new ArrayList(0);
		UILists segData = new UILists();
		if(listSearchData.size()>0) {
			UISoku eSoku = (UISoku)listSearchData.get(0);
			//店舗名称を設定します。(※SEGMENT_NAMEには店舗名称が設定されています。)
			segData.setKeyName(eSoku.getSegmentName());
		}
		segData.setListData(listSearchData);
		listSegment.add(segData);
		tabData.setListData(listSegment);
		listTab.add(tabData);
		return listTab;
	}
    /**
     * ダウンロード メイン処理
     * 
     */
    public void download() {
        FileInputStream finp = null;
        DataOutputStream dos = null;
        
        try {
    		String targetDate = getBirdDateInfo().getAppDate();
    		if("LAST".equals(getPortalTopDto().getPdfType())) {
    			//前月のＰＤＦの場合は、営業日の前月末日を設定します。
    			targetDate = getPortalTopDto().getAppLastMonthDay();
    		}
    		String pdfFileName = targetDate +".uriagereport.pdf";
            String pdfSaveFileName = targetDate +"_uriagereport.pdf";
    		//フルパス
        	String fullPan = BirdContext.getProperty("fileProperty","pdfSokuhoFilePath") + FILE_SEPARATOR + pdfFileName;
            //出力処理
            File file = new File(fullPan);
            
            // ファイル存在チェック
            if (!file.exists()) {
                throw new FileNotFoundException();
            }
            //サーブレットレスポンス
            HttpServletResponse response = (HttpServletResponse) getS2Container().getComponent("response");

            //ContentType指定
            response.setContentType(DownloadLogic.CONTENT_TYPE_PDF);
            //ファイル名の指定
            String filename = new String(pdfSaveFileName.getBytes("SJIS"), "ISO8859_1"); 
            response.setHeader("Content-Disposition", "attachment;filename=\"" + filename + "\"");
            
            
            finp = new FileInputStream(file);
            dos = new DataOutputStream(response.getOutputStream());
            
            byte buf[] = new byte[256];
            int len;
            while ((len = finp.read(buf)) != -1) {
                dos.write(buf, 0, len);
            }
            //
            FacesContext context = FacesContext.getCurrentInstance();
            context.responseComplete();
        }
        catch (IOException ioe) {
            //例外処理
            throw new FtlSystemException("売上速報PDFダウンロード", "", ioe);
        }
        finally {
            try {
                if (dos != null) {
                    dos.close();
                }
                if (finp != null) {
                    finp.close();
                }
            }
            catch (IOException ioe2) {
                throw new FtlSystemException("売上速報PDFダウンロード", "", ioe2);
            }
        }
    }
    /*
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
     * 売上情報取得ロジック設定処理
     * @return getSokuLogic を戻します。
     */
    public GetSokuLogic getPortalTopGetSokuLogic() {
        return portalTopGetSokuLogic;
    }
    /**
     * 売上情報取得ロジック設定処理
     * @param portalTopGetSokuLogic portalTopGetSokuLogic を設定。
     */
    public void setPortalTopGetSokuLogic(GetSokuLogic portalTopGetSokuLogic) {
        this.portalTopGetSokuLogic = portalTopGetSokuLogic;
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
}

