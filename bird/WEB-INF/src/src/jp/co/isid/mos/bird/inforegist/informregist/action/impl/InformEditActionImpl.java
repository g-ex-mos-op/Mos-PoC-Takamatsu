/*
 * 作成日: 2006/2/8
 */
package jp.co.isid.mos.bird.inforegist.informregist.action.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.model.SelectItem;

import jp.co.isid.mos.bird.common.dto.PublicTargetDto;
import jp.co.isid.mos.bird.common.entity.UIDocSearch;
import jp.co.isid.mos.bird.common.logic.GetKoukaiTaishoLogic;
import jp.co.isid.mos.bird.commonform.documentsearch.dto.DocumentSearchDto;
import jp.co.isid.mos.bird.commonform.publictargetsearch.dto.PublicTargetSearchDto;
import jp.co.isid.mos.bird.framework.action.FileUploadAction;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.GenericCommentException;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.logic.UploadLogic;
import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;
import jp.co.isid.mos.bird.framework.util.verifier.DefaultJapaneseVerifier;
import jp.co.isid.mos.bird.framework.util.verifier.LengthVerifier;
import jp.co.isid.mos.bird.inforegist.informregist.action.InformEditAction;
import jp.co.isid.mos.bird.inforegist.informregist.dto.InformRegistDto;
import jp.co.isid.mos.bird.inforegist.informregist.entity.UINews;
import jp.co.isid.mos.bird.inforegist.informregist.logic.CheckNewsKanrenBunshoLogic;
import jp.co.isid.mos.bird.inforegist.informregist.logic.CheckNewsLogic;

import org.apache.commons.beanutils.PropertyUtils;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * インフォメーション登録編集アクションクラス
 * @author itamoto
 * 
 * 更新日：2011/04/15 ASAPC 「お知らせ」から「インフォメーション」へ画面名称変更対応
 */
public class InformEditActionImpl implements InformEditAction {


    /* アクションID */
    public static String initialize_ACTION_ID        = "BIF004A06";
    public static String regist_ACTION_ID            = "BIF004A07";
    public static String cancel_ACTION_ID            = "BIF004A08";
    public static String startPublicTarget_ACTION_ID = "BIF004A09";
    
    /* informRegistDto */
    private InformRegistDto informRegistDto;
    
    private PublicTargetDto publicTargetDto;
    private PublicTargetSearchDto publicTargetSearchDto;

    private DocumentSearchDto documentSearchDto;

    
    /* インフォメーション情報 */
    private UINews uiNews;
    
    /* 公開対象取得ロジック */
    private GetKoukaiTaishoLogic getKoukaiTaishoLogic;
    /* 登録内容のチェックロジック */
    private CheckNewsLogic checkNewsLogic;
    /* 関連文書の重複チェックロジック */
    private CheckNewsKanrenBunshoLogic checkNewsKanrenBunshoLogic;
    
    
    /* ファイルアップロードアクション */
    private FileUploadAction fileUploadAction;
    /* ファイルアップロードロジック */
    private UploadLogic uploadLogic;
    
    /* ログイン情報 */
    private BirdUserInfo birdUserInfo;

    // ファイル名最大文字数
    private final static int FILE_NAME_MAX_LENGTH = 60;
    // タイトル最大文字数
    private final static int TITLE_MAX_LENGTH = 80; 
    //公開期間
    private final static int KOKAI_KAISI_MAX = 92;
    

    
    /**
     * インフォメーション検索用Dtoの設定
     * @return informRegistDto を戻します。
     */
    public InformRegistDto getInformRegistDto() {
        return informRegistDto;
    }
    /**
     * インフォメーション検索用Dtoの設定
     * @param informRegistDto informRegistDto を設定。
     */
    public void setInformRegistDto(InformRegistDto informRegistDto) {
        this.informRegistDto = informRegistDto;
    }
    
    /**
     * @return publicTargetDto を戻します。
     */
    public PublicTargetDto getPublicTargetDto() {
        return publicTargetDto;
    }
    /**
     * @param publicTargetDto publicTargetDto を設定。
     */
    public void setPublicTargetDto(PublicTargetDto publicTargetDto) {
        this.publicTargetDto = publicTargetDto;
    }
    /**
     * @return publicTargetSearchDto を戻します。
     */
    public PublicTargetSearchDto getPublicTargetSearchDto() {
        return publicTargetSearchDto;
    }
    /**
     * @param publicTargetSearchDto publicTargetSearchDto を設定。
     */
    public void setPublicTargetSearchDto(
            PublicTargetSearchDto publicTargetSearchDto) {
        this.publicTargetSearchDto = publicTargetSearchDto;
    }

	/**
	 * インフォメーション情報の設定
	 * @return uiNews を戻します。
	 */
	public UINews getUiNews() {
		return uiNews;
	}
	/**
	 * インフォメーション情報の設定
	 * @param uiNews uiNews を設定。
	 */
	public void setUiNews(UINews uiNews) {
		this.uiNews = uiNews;
	}

	/**
     * 公開対象取得ロジックの設定
     * @return getKoukaiTaishoLogic を戻します。
     */
    public GetKoukaiTaishoLogic getGetKoukaiTaishoLogic() {
        return getKoukaiTaishoLogic;
    }
    /**
     * 公開対象取得ロジックの設定
     * @param getKoukaiTaishoLogic getKoukaiTaishoLogic を設定。
     */
    public void setGetKoukaiTaishoLogic(
            GetKoukaiTaishoLogic getKoukaiTaishoLogic) {
        this.getKoukaiTaishoLogic = getKoukaiTaishoLogic;
    }

	/**
	 * 登録内容チェックロジックの設定
	 * @return checkNewsLogic を戻します。
	 */
	public CheckNewsLogic getCheckNewsLogic() {
		return checkNewsLogic;
	}
	/**
	 * 登録内容チェックロジックの設定
	 * @param checkNewsLogic checkNewsLogic を設定。
	 */
	public void setCheckNewsLogic(CheckNewsLogic checkNewsLogic) {
		this.checkNewsLogic = checkNewsLogic;
	}
	
	/**
	 * BIRDログイン情報の設定
	 * @return birdUserInfo を戻します。
	 */
	public BirdUserInfo getBirdUserInfo() {
		return birdUserInfo;
	}
	/**
	 * BIRDログイン情報の設定
	 * @param birdUserInfo birdUserInfo を設定。
	 */
	public void setBirdUserInfo(BirdUserInfo birdUserInfo) {
		this.birdUserInfo = birdUserInfo;
	}
	/**
     * 初期処理
     * @return 画面遷移情報
     */
	public String initialize() {
        // 公開対象選択の結果反映
        if (publicTargetSearchDto.isActionFlg()) {
            try {
				PropertyUtils.copyProperties(publicTargetDto,
						publicTargetSearchDto);
			} catch (Exception e) {
				throw new FtlSystemException("インフォメーション登録");
			}
            // アクションフラグクリア
            publicTargetSearchDto.setActionFlg(false);
        }
        
        // 関連文書情報
        if (getDocumentSearchDto().isActionFlg()) {
            List listAddKanrenBunsho = getDocumentSearchDto().getDocumentList();
            List listMarge = getCheckNewsKanrenBunshoLogic()
                                    .execute(getInformRegistDto().getListKanrenBunsho(),
                                           listAddKanrenBunsho);
            getInformRegistDto().setListKanrenBunsho(listMarge);
            getDocumentSearchDto().setActionFlg(false);
        }

        //公開開始のプルダウン作成
        makeKoukaiKikanPulldown();

        return null;
    }

	/**
     * 新規登録処理
     */
    public String regist(){
    	// 入力チェック
    	checkNewsLogic.execute(uiNews, publicTargetDto);
        return informRegistDto.confirm_VIEW_ID;
    }

    /**
     * 取消処理
     */
    public String cancel(){
        return informRegistDto.search_VIEW_ID;
    }

    /**
     * 公開対象の表示処理
     */
    public String startPublicTarget(){
        // 公開対象選択のパラメータ設定
    	publicTargetSearchDto.setReferenceFlg(false);
		publicTargetSearchDto.setNavigationCase(informRegistDto.edit_VIEW_ID);
		publicTargetSearchDto.setInitFlag(true);

		try {
			PropertyUtils.copyProperties(publicTargetSearchDto, publicTargetDto);
		} catch (Exception e) {
			throw new FtlSystemException("インフォメーション登録");
		}
        // 公開対象選択へ遷移
        return informRegistDto.publicTarget_VIEW_ID;
    }
    
    /**
     * 添付ファイル削除処理
     * @return
     */
    public String deleteAttachFile() {
        
        if (getInformRegistDto().isCmbDelFlg1()) {
            // 添付ファイル１情報削除
            getUiNews().setAttachFl1("");
            getUiNews().setAttachName1("");
            //getRegistFormDto().setUploadedAttachFile1(null);
            getInformRegistDto().setUploadFileNameTenpu1("");
            getInformRegistDto().setUploadTempFileNameTenpu1("");
            getInformRegistDto().setCmbDelFlg1(false);
        }
        if (getInformRegistDto().isCmbDelFlg2()) {
            // 添付ファイル２情報削除
            getUiNews().setAttachFl2("");
            getUiNews().setAttachName2("");
            //getRegistFormDto().setUploadedAttachFile2(null);
            getInformRegistDto().setUploadFileNameTenpu2("");
            getInformRegistDto().setUploadTempFileNameTenpu2("");
            getInformRegistDto().setCmbDelFlg2(false);
        }
        if (getInformRegistDto().isCmbDelFlg3()) {
            // 添付ファイル３情報削除
            getUiNews().setAttachFl3("");
            getUiNews().setAttachName3("");
            //getRegistFormDto().setUploadedAttachFile3(null);
            getInformRegistDto().setUploadFileNameTenpu3("");
            getInformRegistDto().setUploadTempFileNameTenpu3("");
            getInformRegistDto().setCmbDelFlg3(false);
        }
        return null;
    }
    
    /**
     * 添付ファイルアップロード処理
     * @return
     */
    public String uploadAttachFile() {
        
        
        if ( getInformRegistDto().getAttachName() == null ) {
            throw new NotNullException("添付ファイルタイトル", "uploadFileTenpuTitle", null);
        }
        
        if ( getInformRegistDto().getUploadedAttachFile() == null ) {
            throw new NotNullException("添付ファイル", "uploadFileTenpu", null);
        }
        
        LengthVerifier atachVeri = new LengthVerifier( TITLE_MAX_LENGTH );
        if ( !atachVeri.validate( getInformRegistDto().getAttachName()) ) {
            throw new GenericCommentException("添付ファイルタイトルは全角４０文字まで", "uploadFileTenpuTitle" ,null);
        }       
        
        DefaultJapaneseVerifier defJap = new DefaultJapaneseVerifier();
        if (!defJap.validate( getInformRegistDto().getAttachName()) ) {
            throw new InvalidInputException("添付ファイルタイトル", "uploadFileTenpuTitle" ,null);
        }
        // ファイル存在チェック（空のファイルは不可）
        if (getInformRegistDto().getUploadedAttachFile().getSize() <= 0) {
            throw new GenericCommentException("ファイルが存在しないか、空のファイル", "uploadFileTenpu", null);
        }

        
        getInformRegistDto().setUploadedFile(getInformRegistDto().getUploadedAttachFile());
        
        // アクションのプロパティ設定
        getFileUploadAction().setUploadDto(getInformRegistDto());
        getFileUploadAction().setUploadLogic(getUploadLogic());
        
        // ファイルアップロードアクション実行
        String ret = getFileUploadAction().uploadTemporary();

        // 共通アクションで設定された値を保持
        setAttachFileInfo();
        
        
        return ret;
    }
    
    
    /*
     * 添付ファイル１～３の登録可能箇所を取得
     */
    private int getEmptyAttachIndex() {
        int index = -1;
        if (isNull(getUiNews().getAttachName1())) {
            index = 1;
        }
        else if (isNull(getUiNews().getAttachName2())) {
            index = 2;
        }
        else if (isNull(getUiNews().getAttachName3())) {
            index = 3;
        }
        return index;
    }

    /*
     * Null、空文字チェック
     */
    private boolean isNull(String val) {
        return (val == null || "".equals(val.trim())) ? true : false;
    }

    
    /*
     * 添付ファイル情報設定処理
     */
    private void setAttachFileInfo() {
        
        LengthVerifier lent = new LengthVerifier( FILE_NAME_MAX_LENGTH );
        if (!lent.validate( getInformRegistDto().getUploadFileName()) ) {
            throw new GenericCommentException("ファイル名は全角３０文字まで");
        }
        
        DefaultJapaneseVerifier defJapanese = new DefaultJapaneseVerifier();
        if ( !defJapanese.validate( getInformRegistDto().getUploadFileName()) ) {
            throw new InvalidInputException("ファイル名"); 
        }
        // ファイル存在チェック（空のファイルは不可）
        if (getInformRegistDto().getUploadedFile().getSize() <= 0) {
            throw new GenericCommentException("ファイルが存在しないか、空のファイル");
        }
        
        switch (getEmptyAttachIndex()) {
            case 1:
                getInformRegistDto().setUploadFileNameTenpu1(getInformRegistDto().getUploadFileName());
                getInformRegistDto().setUploadTempFileNameTenpu1(getInformRegistDto().getTempFileName());
                getUiNews().setAttachFl1(getInformRegistDto().getUploadFileName());
                getUiNews().setAttachName1(getInformRegistDto().getAttachName());
                break;
            case 2:
                getInformRegistDto().setUploadFileNameTenpu2(getInformRegistDto().getUploadFileName());
                getInformRegistDto().setUploadTempFileNameTenpu2(getInformRegistDto().getTempFileName());
                getUiNews().setAttachFl2(getInformRegistDto().getUploadFileName());
                getUiNews().setAttachName2(getInformRegistDto().getAttachName());
                break;
            case 3:
                getInformRegistDto().setUploadFileNameTenpu3(getInformRegistDto().getUploadFileName());
                getInformRegistDto().setUploadTempFileNameTenpu3(getInformRegistDto().getTempFileName());
                getUiNews().setAttachFl3(getInformRegistDto().getUploadFileName());
                getUiNews().setAttachName3(getInformRegistDto().getAttachName());
                break;
            default:
                break;
        }
        getInformRegistDto().setAttachName("");
        getInformRegistDto().setUploadFileName("");
    }

    /**
     * @return fileUploadAction を戻します。
     */
    public FileUploadAction getFileUploadAction() {
        return fileUploadAction;
    }
    /**
     * @param fileUploadAction fileUploadAction を設定。
     */
    public void setFileUploadAction(FileUploadAction fileUploadAction) {
        this.fileUploadAction = fileUploadAction;
    }

    /**
     * @return uploadLogic を戻します。
     */
    public UploadLogic getUploadLogic() {
        return uploadLogic;
    }
    /**
     * @param uploadLogic uploadLogic を設定。
     */
    public void setUploadLogic(UploadLogic uploadLogic) {
        this.uploadLogic = uploadLogic;
    }


    /**
     * 公開開始日のプルダウンの作成
     */
    private void makeKoukaiKikanPulldown(){
        // コンポーネント取得
        S2Container container = SingletonS2ContainerFactory.getContainer();
        BirdDateInfo birdDateInfo = (BirdDateInfo) container.getComponent(BirdDateInfo.class);
        
        DateFormatter formatter = new DateFormatter();
        
        List listFrom = new ArrayList(0);
        Map mapkokaiKaisi = new HashMap();
                
        for (int i = 0; i < KOKAI_KAISI_MAX; i++) {
            String yyyymmdd = "";
            try {
                yyyymmdd = DateManager.getNextDate(birdDateInfo.getSysDate(), i);
            }
            catch (Exception ex) {
                throw new FtlSystemException("編集画面初期処理", ex.toString(), ex);
            }
                                     
            SelectItem item = new SelectItem(yyyymmdd, formatter.format(yyyymmdd, true)); 
            listFrom.add(item);
            mapkokaiKaisi.put(yyyymmdd, "");
        }
        //公開期間TOプルダウン作成
    	List listTo = new ArrayList(0);
        listTo.add(new SelectItem("99991231", formatter.format("99991231", true)));
        mapkokaiKaisi.put("99991231", "");
    	for(int i=0; i<listFrom.size(); i++) {
    		listTo.add(listFrom.get(i));
    	}
        //検索したデータがプルダウンに存在しない場合は追加
        String pubDateFrom = getUiNews().getPubDateFrom();
        if(pubDateFrom != null &&  pubDateFrom.trim().length() != 0l){
            if(!mapkokaiKaisi.containsKey(pubDateFrom)){
                SelectItem item = new SelectItem(pubDateFrom, formatter.format(pubDateFrom, true)); 
                listFrom.add(item);
            }
        }
        getInformRegistDto().setListPubDateFrom(listFrom);
        
        //検索したデータがプルダウンに存在しない場合は追加
        String pubDateTo = getUiNews().getPubDateTo();
        if(pubDateTo != null &&  pubDateTo.trim().length() != 0l){
            if(!mapkokaiKaisi.containsKey(pubDateTo)){
                listTo.add(new SelectItem(pubDateTo, formatter.format(pubDateTo, true)));
            }
        }
        getInformRegistDto().setListPubDateTo(listTo);

    }


    /**
     * 関連文書ＤＴＯを取得します。
     * @return 関連文書ＤＴＯ 
     */
    public DocumentSearchDto getDocumentSearchDto() {
        return documentSearchDto;
    }

    /**
     * 関連文書ＤＴＯを設定します。
     * @param 関連文書ＤＴＯ
     */
    public void setDocumentSearchDto(DocumentSearchDto documentSearchDto) {
        this.documentSearchDto = documentSearchDto;
    }

    
    /**
     * 関連文書の重複チェックロジックを取得します。
     * @return 関連文書の重複チェックロジック 
     */
    public CheckNewsKanrenBunshoLogic getCheckNewsKanrenBunshoLogic() {
        return checkNewsKanrenBunshoLogic;
    }
    
    /**
     * 関連文書の重複チェックロジックを設定します。
     * @param 関連文書の重複チェックロジック
     */
    public void setCheckNewsKanrenBunshoLogic(
            CheckNewsKanrenBunshoLogic checkNewsKanrenBunshoLogic) {
        this.checkNewsKanrenBunshoLogic = checkNewsKanrenBunshoLogic;
    }
    
    /**
     * 関連文書選択
     */
    public String callFormKanrenBunsho() {
        // 関連文書選択のパラメータ設定
        getDocumentSearchDto().setInitFlg(true);
        getDocumentSearchDto().setNavigationCase(getInformRegistDto().edit_VIEW_ID);

        return getInformRegistDto().docSearch_VIEW_ID;
    }
    
    /**
     * 関連文書の削除
     */
    public String deleteKanrenBunsho() {
        List kanrenBunshoList = getInformRegistDto().getListKanrenBunsho();
        
        for ( int i = kanrenBunshoList.size() - 1; i > - 1; i-- ) {
            UIDocSearch uIDocSearch = (UIDocSearch)kanrenBunshoList.get(i);
            if ( uIDocSearch.isCheckedDoc() ) {
                kanrenBunshoList.remove(i);
            }
        }
        return null;
    }



}

