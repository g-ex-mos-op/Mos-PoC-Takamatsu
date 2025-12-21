package jp.co.isid.mos.bird.framework.entity;

//TODO 暫定テーブル用。仕様が確定したら変更する事。 2005/11/30
/**
 * FullTextSearch検索 DB上のファイル情報
 * @author xnkusama
 */
public interface FileAccessInfoEntity {
//    public static final String TABLE = "WR05CONT";

//    /* タイトル */
//    public String getTitle();
//    public void setTitle(final String title);
//
//  /* 掲載日From */
//  public String getListFromDt();
//  public void setListFromDt(final String listFromDt);
//
//  /* 掲載日To */
//  public String getListToDt();
//  public void setListToDt(final String listToDt);

    /* ファイル名 */
    public String getFileName();
    public void setFileName(final String fileName);

    /* マッチング用ファイル名 */
    public String getMatchingFileName();
    public void setMatchingFileName(final String fileName);

    /* 要約 */
    public String getDescription();
    public void setDescription(final String description);

}
