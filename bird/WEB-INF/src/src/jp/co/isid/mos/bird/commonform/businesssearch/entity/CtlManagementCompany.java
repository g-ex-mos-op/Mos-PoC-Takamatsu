package jp.co.isid.mos.bird.commonform.businesssearch.entity;

/**
 * Entity【業態選択対象管理会社】クラス
 * 
 * @modifier xkinu 2013/01/24 海外売上集信対応　
 *
 */
public class CtlManagementCompany {
    
    public static final String TABLE = "BC05KCOM";
    
    public static final String companyCd_COLUMN = "COMPANY_CD";
    
    public static final String companyName_COLUMN = "COMPANY_NAME";
    
    public static final String foreignFlg_COLUMN = "FOREIGN_FLG";
    
    /**
     * 管理会社企業コード
     */
    private String companyCd;
    
    /**
     * 管理会社企業名称
     */
    private String companyName;
    
    /**
     * 海外フラグ
     */
    private String foreignFlg;
    
    /**
     * 管理会社企業コードを取得します。
     * @return 管理会社企業コード
     */
    public String getCompanyCd() {
        return companyCd;
    }
    /**
     * 管理会社企業コードを設定します。
     * @param companyCd 管理会社企業コード
     */
    public void setCompanyCd(String companyCd) {
        this.companyCd = companyCd;
    }
    
    /**
     * 管理会社企業名称を取得します。
     * @return 管理会社企業名称
     */
    public String getCompanyName() {
        return companyName;
    }
    /**
     * 管理会社企業名称を設定します。
     * @param companyName 管理会社企業名称
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
	/**
	 * 海外フラグ
	 * @return クラス変数foreignFlg を戻します。
     * @modifier xkinu 2013/01/24 海外売上集信対応　新規追加
	 */
	public String getForeignFlg() {
		return foreignFlg;
	}
	/**
	 * 海外フラグ設定処理
	 * @param flg を クラス変数foreignFlgへ設定します。
     * @modifier xkinu 2013/01/24 海外売上集信対応　新規追加
	 */
	public void setForeignFlg(String flg) {
		this.foreignFlg = flg;
	}
	/**
	 * 海外会社判断処理
	 * @return　true:海外の会社 ,false:国内の会社
     * @modifier xkinu 2013/01/24 海外売上集信対応　新規追加
	 */
    public boolean isForeign() {
    	return "1".equals(getForeignFlg());
    }
}
