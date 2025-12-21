package jp.co.isid.mos.bird.commonform.publictargetsearch.entity;

import java.util.ArrayList;
import java.util.List;

public class MstGyotiUneiCompany {

    public static final String TABLE = "BM04GTCP";


    public static final String gyotaiKbn_COLUMN = "GYOTAI_KBN";

    public static final String gyotaiName_COLUMN = "GYOTAI_KBN_NAME";


    /**
     * 会社コード
     */
    private String companyCd;
    
    /**
     * 業態区分
     */
    private String gyotaiKbn;

    /**
     * 業態名称
     */
    private String gyotaiName;

    /**
     * 選択フラグ
     */
    private boolean sentakuFlg;


    /**
     * 個別設定(支部取込)
     */
    private List kobetuSetSibu = new ArrayList();

    /**
     * 個別設定(店)
     */
    private List kobetuSetMise = new ArrayList();


    /**
     * 業態区分を取得します。
     * @return 業態区分
     */
    public String getGyotaiKbn() {
        return gyotaiKbn;
    }

    /**
     * 業態区分を設定します。
     * @param gyotaiKbn 業態区分
     */
    public void setGyotaiKbn(String gyotaiKbn) {
        this.gyotaiKbn = gyotaiKbn;
    }

    /**
     * 業態名称を取得します。
     * @return 業態名称
     */
    public String getGyotaiName() {
        return gyotaiName;
    }

    /**
     * 業態名称を設定します。
     * @param gyotaiName 業態名称
     */
    public void setGyotaiName(String gyotaiName) {
        this.gyotaiName = gyotaiName;
    }

    /**
     * 選択フラグを取得します。
     * @return 選択フラグ
     */
    public boolean getSentakuFlg() {
        return sentakuFlg;
    }

    /**
     * 選択フラグを設定します。
     * @param sentakuFlg 選択フラグ
     */
    public void setSentakuFlg(boolean sentakuFlg) {
        this.sentakuFlg = sentakuFlg;
    }

    /**
     * 個別設定フラグを取得します。
     * @return 個別設定フラグ
     */
    public String getKogetuFlg() {
        //個別設定(支部取込コード)、個別設定(店コード)の両方が設定されていない場合
        if ((kobetuSetSibu.size() == 0) && (kobetuSetMise.size() == 0)) {
            return "";
        }

        if (kobetuSetSibu.size() != 0 || kobetuSetMise.size() != 0) {
            return "有";
        }

        return "";
    }


    /**
     * 個別設定(支部取込)を取得します。
     * @return 個別設定
     */
    public List getKobetuSetSibu() {
        return kobetuSetSibu;
    }

    /**
     * 個別設定(支部取込)を設定します。
     * @param kobetuSet 個別設定
     */
    public void setKobetuSetSibu(List kobetuSetSibu) {
        this.kobetuSetSibu = kobetuSetSibu;
    }

    /**
     * 個別設定(店)を取得します
     * @return 個別設定(店)
     */
    public List getKobetuSetMise() {
        return kobetuSetMise;
    }

    /**
     * 個別設定(店)を設定します。
     * @param kobetuSetMise 個別設定(店)
     */
    public void setKobetuSetMise(List kobetuSetMise) {
        this.kobetuSetMise = kobetuSetMise;
    }

    /**
     * 会社コードを取得します
     * @return 会社コード
     */
    public String getCompanyCd() {
        return companyCd;
    }

    /**
     * 会社コードを設定します。
     * @param companyCd 会社コード
     */
    public void setCompanyCd(String companyCd) {
        this.companyCd = companyCd;
    }
}