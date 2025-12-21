/*
 * 作成日: 2006/07/05
 */
package jp.co.isid.mos.bird.storeinfo.miselumpextract.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.commonform.common.entity.CodSibu;
import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;
import jp.co.isid.mos.bird.framework.dto.DownloadDto;
import jp.co.isid.mos.bird.storeinfo.miselumpextract.entity.CodCompanyJoho;

/**
 * 個店情報一括抽出画面 Dto
 * @author xayumi
 */
public class MiseLumpExtractDto implements DownloadDto, CsvOutputDto {
   
    /** 支部コード */
    private String sibuCd;
    /** 会社コード */
    private String companyCd;
    /** ユーザーID */
    private String userId;
    /** 選択ラジオボタンIndex */
    private int index;
    /** 条件画面：会社リスト */
    private List listCompany;
    /** 条件画面：支部リスト保持Map */
    private Map mapPullSibu = new HashMap();
    /** クローズ店フラグ（含む／含まない) */
    private String closeFlg;
    
    private List listEvntMax;

    /* CSVデータ */
    private List csvList;
    
    /* システム日付 */
    private String sysDate;
    
    private List titleList;
    
    public void clear() {
        setListCompany(null);
        setMapPullSibu(new HashMap());
        setCsvList(null);
        setListEvntMax(null);
    }
    
    /**
     * 条件画面用：CSVタイトルリストの取得。
     * @return titleList を戻します。
     */
    public List getTitleList() {
        return titleList;
    }
    
    /**
     * CSVデータの取得
     * @return csvList を戻します。
     */
    public List getCsvList() {
        return csvList;
    }
    /**
     * CSVデータの設定
     * @param csvList を設定。
     */
    public void setCsvList(List csvList) {
        this.csvList = csvList;
    }
    
    /**
     * 条件画面用：会社リストの取得
     * @return listCompany を戻します。
     */
    public List getListCompany() {
        return listCompany;
    }
    /**
     * 条件画面用：会社リストの設定
     * @param listCompany listCompany を設定。
     */
    public void setListCompany(List listCompany) {
        this.listCompany = listCompany;
    }
    
    /**
     * 条件画面で選択された支部コード取得
     * @return sibuCd を戻します。
     */
    public String getSibuCd() {
        return sibuCd;
    }
    /**
     * 条件画面で選択された支部コード設定
     * @param String sibuCd を設定。
     */
    public void setSibuCd(String sibuCd) {
        this.sibuCd = sibuCd;
    }
    
    /**
     * 条件画面で選択された会社名称取得
     * @return sibuName を戻します。
     */
    public String getSibuName() {
        
        String sibuName     = "";
        String selectSibuCd = getSibuCd();
        List listPullSibu   = getListPullSibu();
        
        if (selectSibuCd!= null 
                && !selectSibuCd.equals("") 
                && listPullSibu != null) {
            
            for (int i = 0; i < listPullSibu.size(); i++) {
                CodSibu codSibu = (CodSibu) listPullSibu.get(i);
                if (selectSibuCd.equals(codSibu.getSibuCd())) {
                    sibuName = codSibu.getSibuName();
                    break;
                }
            }
        }
        
        return sibuName;
    }
    
    /**
     * 条件画面で選択された会社コード取得
     * @return companyCd を戻します。
     */
    public String getCompanyCd() {
        return companyCd;
    }
    /**
     * 条件画面で選択された会社コード設定
     * @param String companyCd を設定。
     */
    public void setCompanyCd(String companyCd) {
        this.companyCd = companyCd;
    }
    
    
    /**
     * 条件画面で選択された会社名称取得
     * @return companyName を戻します。
     */
    public String getCompanyName() {
        
        String companyName = "";
        String selectCompanyCd = getCompanyCd();
        
        List listPullCompany = getListCompany();
        if (listPullCompany != null) {
            for (int i = 0; i < listPullCompany.size(); i++) {
                CodCompanyJoho codCompanyJoho = (CodCompanyJoho) listPullCompany.get(i);
                if (selectCompanyCd.equals(codCompanyJoho.getCompanyCd())) {
                    companyName = codCompanyJoho.getCompanyName();
                    break;
                }
            }
        }
        
        return companyName;
    }

       
    /**
     * 条件画面の支部コードプルダウン用、支部リストの設定
     * @return listPullSibu を戻します。
     */
    public List getListPullSibu() {
    	if(mapPullSibu != null 
    			&& mapPullSibu.containsKey(getCompanyCd()))
    	{
    		return (List)mapPullSibu.get(getCompanyCd());
    	}
    	return null;
    }
    /**
     * イベント分類毎の、イベント種別の数のMAXリストの設定
     * @return listEvntMax を戻します。
     */
    public List getListEvntMax() {
        return listEvntMax;
    }
    /**
     * イベント分類毎の、イベント種別の数のMAXリストの設定
     * @param listEvntMax を設定。
     */
    public void setListEvntMax(List listEvntMax) {
        this.listEvntMax = listEvntMax;
    }
    
    /**
     * ユーザーIDの設定
     * @return String を戻します。
     */
    public String getUserId() {
        return userId;
    }
    /**
     * ユーザーIDの設定
     * @param String userId を設定。
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    /**
     * 選択ラジオボタンIndexの設定
     * @return int を戻します。
     */
    public int getIndex() {
        return index;
    }
    /**
     * 選択ラジオボタンIndexの設定
     * @param String index を設定。
     */
    public void setIndex(int index) {
        this.index = index;
    }
    /**
     * クローズ店フラグ（含む／含まない)の設定
     * @return closeFlg
     */
    public String getCloseFlg() {
        return this.closeFlg;
    }
    /**
     * クローズ店フラグ（含む／含まない)の設定
     * @param closeFlg を設定。
     */
    public void setCloseFlg( String closeFlg ) {
        this.closeFlg = closeFlg;
    }
    
    public String getCloseName() {
        
        String closeName = "含む";
        
        if(getCloseFlg().equals("outClose")){
            closeName = "含まない";
        }
        
        return closeName;
    }

    public String getSysDate() {
        return sysDate;
    }

    public void setSysDate(String sysDate) {
        this.sysDate = sysDate;
    }

	/**
	 * @return クラス変数mapPullSibu を戻します。
	 */
	public Map getMapPullSibu() {
		return mapPullSibu;
	}

	/**
	 * @param mapPullSibu を クラス変数mapPullSibuへ設定します。
	 */
	public void setMapPullSibu(Map mapPullSibu) {
		this.mapPullSibu = mapPullSibu;
	}

	/**
	 * @param titleList を クラス変数titleListへ設定します。
	 */
	public void setTitleList(List titleList) {
		this.titleList = titleList;
	}

}
