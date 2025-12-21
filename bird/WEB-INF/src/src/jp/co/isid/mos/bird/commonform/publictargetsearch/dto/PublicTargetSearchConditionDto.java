/*
 * 作成日: 2006/01/16
 *
 */
package jp.co.isid.mos.bird.commonform.publictargetsearch.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.model.SelectItem;

import org.apache.myfaces.custom.fileupload.UploadedFile;

import jp.co.isid.mos.bird.commonform.publictargetsearch.entity.MstGyotiUneiCompany;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.CsvInputDto;


/**
 * @author xytamura
 *
 */
public class PublicTargetSearchConditionDto implements CsvInputDto{
    
    /**
     * 個別設定が選択された業態の会社コード
     */
    private String selectedGyotaiCompanyCd;
    
    /**
     * 個別設定が選択された業態の業態区分
     */
    private String selectedGyotiKbn;
    
    /**
     * 個別設定が選択された業態運営
     */
    private MstGyotiUneiCompany selectedMstGyotiUneiCompany;

    /**
     * 個別設定が選択された行
     */
    private int selectedIndex;
    
    /**
     * 所属
     */
    private List listCodShozoku;
    
    /**
     * 会社
     */
    private List listCtlUserCompany;
    
    /**
     * 業態
     */
    private List listMstGyotiUneiCompany;
    
    /**
     * 支部取込
     */
    private List listMstCompanySibuTorikomi;
       
    /**
     * 個店指定ファイル
     */
    private UploadedFile uploadFile;
    
    /**
     * 取込ＯＫ店舗
     */
    private List listOKMise;
    
    /**
     * 取込ＮＧ店舗
     */
    private List listNGMise;
    
    /**
     * ユーザ情報
     */
    private String userId;

    private Map mapCompanysSibu = new HashMap();
    private List listCompanysSibu = new ArrayList(0);
    /**
     * 公開対象会社 選択Index
     */
    private int selectedCompanyIndex = -1;

    /**
     * 選択モード
     */
    private String selectMode = MODE_GYOTAI;
    
    //切り替えモード
    public static final String MODE_GYOTAI = "1";
    public static final String MODE_SIBU = "2";
    
    /**
     * ユーザ情報
     */
    private BirdUserInfo birdUserInfo;
    
    private List listCompanyCd = new ArrayList(0);
    /**
     * ユーザIDを設定します。
     * @param  ユーザID
     */
    public void setUserid(final String usertId){
       this.userId = usertId;    
    }

    /**
     * ユーザIDを取得します。
     * @return ユーザID
     */
    public String getUserId(){
        return userId;
    }
        
    /**
     * 所属による絞込み情報を取得します。
     * @return 所属による絞込み情報
     */
    public List getListCodShozoku() {
        return listCodShozoku;
    }
    /**
     * 所属による絞込み情報を設定します。
     * @param listCodShozoku 所属による絞込み情報
     */
    public void setListCodShozoku(List listCodShozoku) {
        this.listCodShozoku = listCodShozoku;
    }
    /**
     * 会社による絞込み情報を取得します。
     * @return listCtlUserCompany を戻します。
     */
    public List getListCtlUserCompany() {
        return listCtlUserCompany;
    }
    
    /**
     * 会社による絞込み情報を設定します。
     * @param listCtlUserCompany listCtlUserCompany を設定。
     */
    public void setListCtlUserCompany(List listCtlUserCompany) {
        this.listCtlUserCompany = listCtlUserCompany;
    }

    /**
     * 業態による絞込み情報を取得します。
     * @return listMstGyotiUneiCompany を戻します。
     */
    public List getListMstGyotiUneiCompany() {
        return listMstGyotiUneiCompany;
    }
    /**
     * 業態による絞込み情報を取得します。
     * @param listMstGyotiUneiCompany listMstGyotiUneiCompany を設定。
     */
    public void setListMstGyotiUneiCompany(List listMstGyotiUneiCompany) {
        this.listMstGyotiUneiCompany = listMstGyotiUneiCompany;
    }
    /**
     * @param selectedGyotiKbn selectedGyotiKbn を設定。
     */
    public void setSelectedGyotiKbn(String selectedGyotiKbn) {
        this.selectedGyotiKbn = selectedGyotiKbn;
    }
    /**
     * @return selectedGyotiKbn を戻します。
     */
    public String getSelectedGyotiKbn() {
        return selectedGyotiKbn;
    }
    
    
    /**
     * @return listMstCompanySibuTorikomi を戻します。
     */
    public List getListMstCompanySibuTorikomi() {
        return listMstCompanySibuTorikomi;
    }
    
    /**
     * @param listMstCompanySibuTorikomi listMstCompanySibuTorikomi を設定。
     */
    public void setListMstCompanySibuTorikomi(List listMstCompanySibuTorikomi) {
        this.listMstCompanySibuTorikomi = listMstCompanySibuTorikomi;
    }
    
    
    /**
     * @return mstGyotiUneiCompany を戻します。
     */
    public String getSelectedGyotiaName() {
        return selectedMstGyotiUneiCompany.getGyotaiName();
    }

    /**
     * 個店別設定があるかどうか
     * @return mstGyotiUneiCompany を戻します。
     */
    public String getKotenFlg() {
        List mise = getSelectedMstGyotiUneiCompany().getKobetuSetMise();
        
        if(mise == null || mise.size() == 0){
            return "";
        }
        return "有";
    }

    /**
     * 選択された業態を設定
     * @param mstGyotiUneiCompany mstGyotiUneiCompany を設定。
     */
    public void setSelectedMstGyotiUneiCompany(MstGyotiUneiCompany selectedMstGyotiUneiCompany) {
        this.selectedMstGyotiUneiCompany = selectedMstGyotiUneiCompany;
    }

    /**
     * 選択された業態を取得。
     * @param mstGyotiUneiCompany mstGyotiUneiCompany を設定。
     */
    public MstGyotiUneiCompany getSelectedMstGyotiUneiCompany() {
        return selectedMstGyotiUneiCompany;
    }
    
    
    
    /**
     * @see jp.co.isid.mos.bird.framework.dto.CsvInputDto#getUploadedFile()
     */
    public UploadedFile getUploadedFile() {
        return uploadFile;
    }

    /**
     * @see jp.co.isid.mos.bird.framework.dto.CsvInputDto#setUploadedFile(org.apache.myfaces.custom.fileupload.UploadedFile)
     */
    public void setUploadedFile(UploadedFile uploadFile) {
        this.uploadFile = uploadFile;
    }
    
    /**
     * CSV取込がNGだった店の一覧を取得します。
     * @return CSV取込がNGだった店の一覧
     */
    public List getListNGMise() {
        return listNGMise;
    }
    
    /**
     * CSV取込がNGだった店の一覧を設定します。
     * @param listNGMise CSV取込がNGだった店の一覧
     */
    public void setListNGMise(List listNGMise) {
        this.listNGMise = listNGMise;
    }
    
    /**
     * CSV取込がOKだった店の一覧を取得します。
     * @return CSV取込がOKだった店
     */
    public List getListOKMise() {
        return listOKMise;
    }
    
    /**
     * CSV取込がOKだった店の一覧を設定します。
     * @param listOKMise CSV取込がOKだった店
     */
    public void setListOKMise(List listOKMise) {
        this.listOKMise = listOKMise;
    }
    
    /**
     * 正常取込の店件数を取得
     * @return 店件数
     */
    public int getOkMiseCount(){
        if(listOKMise == null){
            return 0;
        }
        return listOKMise.size();
    }

    /**
     * 取込失敗の店件数を取得
     * @return 店件数
     */
    public int getNgMiseCount(){
        if(listNGMise == null){
            return 0;
        }
        return listNGMise.size();
    }
      
    /**
     *個別設定が選択された行を取得します。
     * @return 個別設定が選択された行
     */
    public int getSelectedIndex() {
        return selectedIndex;
    }
    /**
     * 個別設定が選択された行を設定します。
     * @param selectedIndex 個別設定が選択された行
     */
    public void setSelectedIndex(int selectedIndex) {
        this.selectedIndex = selectedIndex;
    }
    
//    /**
//     * 初期化フラグを取得します。
//     * @return 初期化フラグ
//     */
//    public boolean isInitFlg() {
//        return initFlg;
//    }
//    /**
//     * 初期化フラグを設定します。
//     * @param initFlg 初期化フラグ
//     */
//    public void setInitFlg(boolean initFlg) {
//        this.initFlg = initFlg;
//    }
    
    /**
     * 設定されたデータをクリアします。
     */
    public void clearData(){
          selectedGyotiKbn = "";
          selectedMstGyotiUneiCompany = null;
          selectedIndex = 0;
          listCodShozoku = null;
          listCtlUserCompany = null;
          listMstGyotiUneiCompany = null;
          listMstCompanySibuTorikomi = null;
          uploadFile = null;
          listOKMise = null;
          listNGMise = null;
          selectMode = MODE_GYOTAI;
    }
    /**
     * 切り替えプルダウンを取得します。
     * @return
     */
    public static List getlistKirikae(){
        List l = new ArrayList();
        l.add(new SelectItem("1", "公開対象業態選択"));
        l.add(new SelectItem("2", "公開対象支部選択"));

        return l;
    }

    /**
     * 選択モードを取得します。
     * @return 選択モード 
     */
    public String getSelectMode() {
        return selectMode;
    }

    /**
     * 選択モードを設定します。
     * @param 選択モード
     */
    public void setSelectMode(String selectMode) {
        this.selectMode = selectMode;
    }

    /**
     * ユーザ情報を取得します。
     * @return ユーザ情報 
     */
    public BirdUserInfo getBirdUserInfo() {
        return birdUserInfo;
    }

    /**
     * ユーザ情報を設定します。
     * @param ユーザ情報
     */
    public void setBirdUserInfo(BirdUserInfo birdUserInfo) {
        this.birdUserInfo = birdUserInfo;
    }

    public String getSelectedGyotaiCompanyCd() {
        return selectedGyotaiCompanyCd;
    }

    public void setSelectedGyotaiCompanyCd(String selectedGyotaiCompanyCd) {
        this.selectedGyotaiCompanyCd = selectedGyotaiCompanyCd;
    }

    public int getSelectedCompanyIndex() {
        return selectedCompanyIndex;
    }

    public void setSelectedCompanyIndex(int selectedCompanyIndex) {
        this.selectedCompanyIndex = selectedCompanyIndex;
    }

	/**
	 * @return クラス変数mapCompanysSibu を戻します。
	 */
	public Map getMapCompanysSibu() {
		return mapCompanysSibu;
	}

	/**
	 * @param mapCompanysSibu を クラス変数mapCompanysSibuへ設定します。
	 */
	public void setMapCompanysSibu(Map mapCompanysSibu) {
		this.mapCompanysSibu = mapCompanysSibu;
	}
	public List getListCompanysSibu() {
		return listCompanysSibu;
	}

	/**
	 * @param listCompanysSibu を クラス変数listCompanysSibuへ設定します。
	 */
	public void setListCompanysSibu(List listCompanysSibu) {
		this.listCompanysSibu = listCompanysSibu;
	}

	/**
	 * @return クラス変数listCompanyCd を戻します。
	 */
	public List getListCompanyCd() {
		return listCompanyCd;
	}

	/**
	 * @param listCompanyCd を クラス変数listCompanyCdへ設定します。
	 */
	public void setListCompanyCd(List listCompanyCd) {
		this.listCompanyCd = listCompanyCd;
	}
	/**
	 * 画面表示用公開対象支部会社情報
	 * (公開対象支部時のみ使用)
	 * 
	 * TRタグ１段につき２個まで表示可能
	 * @return
	 */
	public List getCompanyScreenList() {
		List companyScreenList = new ArrayList(0);
		if(getListCompanyCd() != null
				&& getListCompanyCd().size()>0) {
			//TRタグの段数を算出します。
			int trCnt = getListCompanyCd().size()/2+(getListCompanyCd().size()%2>0?1:0);
			for (int r=0; r<trCnt; r++) {
				//段数分のリストを格納します。
				companyScreenList.add(new ArrayList(0));
			}
			for (int i=0; i<getListCompanyCd().size(); i++) {
				//現行行[ユーザー対象オーナー]の格納対象Entity[段]のインデックスを算出します。
				int rowIndex = i/2;
				//格納対象List[[段]]から格納対象Entity[段]を取得します。
				List listRow = (List)companyScreenList.get(rowIndex);
				//格納対象Entity[段]へ現行行[ユーザー対象オーナー]を格納します。
				listRow.add(getListCompanyCd().get(i));
			}
		}
		return companyScreenList;
	}
}
