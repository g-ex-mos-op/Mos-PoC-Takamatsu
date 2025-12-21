/*
 * 作成日: 2006/02/13
 */
package jp.co.isid.mos.bird.common.entity;

/**
 * 連絡、通達情報等の公開対象制限用インターフェイス
 * 下記画面のEntityは、当インターフェイスを実装して下さい。
 * 　対象画面：連絡、通達、文書、フォーム、お知らせ (2006/02/13現在)
 * @author xnkusama
 */
public interface TrnInfoAccessControl {
    /* 情報登録日 */
    public String getRegDate();
    public void setRegDate(String regDate);
    
    /* 情報シーケンス番号 */
    public String getSeq();
    public void setSeq(String seq);
    
    /* 業態区分 */
    public String getGyotaiKbn();
    public void setGyotaiKbn(String gyotaiKbn);
    
    /* 個別設定フラグ */
    public String getKobetsuFlg();
    public void setKobetsuFlg(String kobetsuFlg);
    
    /* 個店別設定フラグ */
    public String getMiseFlg();
    public void setMiseFlg(String miseFlg);
    
}
