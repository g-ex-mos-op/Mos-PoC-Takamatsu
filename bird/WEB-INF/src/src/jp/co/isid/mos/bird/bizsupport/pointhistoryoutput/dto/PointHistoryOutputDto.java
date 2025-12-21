/**
 * 作成日: 2017/02/24
 */
package jp.co.isid.mos.bird.bizsupport.pointhistoryoutput.dto;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.pointhistoryoutput.entity.CodKbCompanyJoho;
import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;
import jp.co.isid.mos.bird.framework.dto.DownloadDto;

/**
 * ポイント履歴出力DTO
 * @author Yuichi Tamura(ISID-AO)
 */
public class PointHistoryOutputDto implements DownloadDto, CsvOutputDto {

    /** 会社リスト */
    private List listCompany;
    /** 年度(From) */
    private String nendoFrom;
    /** 年度(To) */
    private String nendoTo;
	/** 会社コード */
    private String kbCompanyCd;
    /** 社員番号 */
    private String userId;

    /** 選択ラジオボタンIndex */
    //処理区分
    private String shoriKbnIndex;
    //退職含む/含まない
    private String taishokuIndex;

    /* CSVデータ */
    private List csvList;

    /* システム日付 */
    private String sysDate;

    public void clear() {
        setCsvList(null);
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
     * 会社リストの取得
     * @return 会社リストを戻します。
     */
    public List getListCompany() {
        return listCompany;
    }
    /**
     * 会社リストの設定
     * @param listCompany 会社リストを設定。
     */
    public void setListCompany(List listCompany) {
        this.listCompany = listCompany;
    }


    /**
     * 条件画面で選択された年度(From)取得
     * @return 年度(From) を戻します。
     */
    public String getNendoFrom() {
        return nendoFrom;
    }
    /**
     * 条件画面で選択された年度(From)設定
     * @param nendoFrom 年度(From)を設定。
     */
    public void setNendoFrom(String nendoFrom) {
        this.nendoFrom = nendoFrom;
    }

    /**
     * 条件画面で選択された年度(To)取得
     * @return 年度(To) を戻します。
     */
    public String getNendoTo() {
        return nendoTo;
    }
    /**
     * 条件画面で選択された年度(To)設定
     * @param nendoFrom 年度(To)を設定。
     */
    public void setNendoTo(String nendoTo) {
        this.nendoTo = nendoTo;
    }


    /**
     * 条件画面で選択された会社コードを取得
     * @return 会社コードを戻します。
     */
    public String getKbCompanyCd() {
    	if(kbCompanyCd == null){
    		return "";
    	}
        return kbCompanyCd;
    }
    /**
     * 条件画面で選択された会社コードを設定
     * @param kbCompanyCd 会社コードを設定。
     */
    public void setKbCompanyCd(String kbCompanyCd) {
        this.kbCompanyCd = kbCompanyCd;
    }

    /**
     * 条件画面で選択された会社名称取得
     * @return 会社名を戻します。
     */
    public String getKbCompanyName() {

        String companyKbName = "";
        String selectCompanyCd = getKbCompanyCd();

        List listPullCompany = getListCompany();
        if (listPullCompany != null) {
            for (int i = 0; i < listPullCompany.size(); i++) {
            	CodKbCompanyJoho codKbCompanyJoho = (CodKbCompanyJoho) listPullCompany.get(i);
                if (selectCompanyCd.equals(codKbCompanyJoho.getKbCompanyCd())) {
                	companyKbName = codKbCompanyJoho.getKbCompanyName();
                    break;
                }
            }
        }

        return companyKbName;
    }

    /**
     * 条件画面で選択された社員番号を取得
     * @return 社員番号を戻します。
     */
    public String getUserId() {
        return userId;
    }
    /**
     * 条件画面で選択された社員番号設定
     * @param nendoFrom 社員番号を設定。
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 処理区分選択ラジオボタンIndexの設定
     * @return 処理区分選択ラジオボタンIndexを戻します。
     */
    public String getShoriKbnIndex() {
        return shoriKbnIndex;
    }
    /**
     * 処理区分選択ラジオボタンIndexの設定
     * @param String 処理区分選択ラジオボタンIndexを設定。
     */
    public void setShoriKbnIndex(String shoriKbnIndex) {
        this.shoriKbnIndex = shoriKbnIndex;
    }
    /**
     * 退職含む/含まない選択ラジオボタンIndexの設定
     * @return 退職含む/含まない選択ラジオボタンIndexを戻します。
     */
    public String getTaishokuIndex() {
        return taishokuIndex;
    }
    /**
     * 退職含む/含まない選択ラジオボタンIndexの設定
     * @param String 退職含む/含まない選択ラジオボタンIndexを設定。
     */
    public void setTaishokuIndex(String taishokuIndex) {
        this.taishokuIndex = taishokuIndex;
    }



    public String getSysDate() {
        return sysDate;
    }

    public void setSysDate(String sysDate) {
        this.sysDate = sysDate;
    }


}
