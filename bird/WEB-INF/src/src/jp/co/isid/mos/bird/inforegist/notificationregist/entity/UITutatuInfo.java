package jp.co.isid.mos.bird.inforegist.notificationregist.entity;

import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;

import javax.faces.model.SelectItem;

/**
 * 通達情報
 * 
 * 更新日：2006/03/16 xkinu 排他制御対応
 * @author 
 *
 */
public class UITutatuInfo {
    
    public static final String TABLE = "BT02NTCM";
    
    public static final String regDate_COLUMN = "REG_DATE";
    
    public static final String seq_COLUMN = "SEQ";
    
    public static final String cateId_COLUMN = "CATE_ID";
    
    public static final String cateName_COLUMN = "CATE_NAME";
    
    public static final String title_COLUMN = "TITLE";
    
    public static final String subTitle_COLUMN = "SUB_TITLE";
    
    public static final String pubDate_COLUMN = "PUB_DATE";
    
    public static final String pubUser_COLUMN = "PUB_USER";
    
    public static final String userNamekj_COLUMN = "USER_NAME_KJ";
    
    public static final String rCompanyCd_COLUMN = "R_COMPANY_CD";
    
    public static final String pubPrg_COLUMN = "PUB_ORG";
    
    public static final String pubOrgName_COLUMN = "PUB_ORG_NAME";
    
    public static final String kanriNo_COLUMN = "KANRI_NO";
    
    public static final String fileName_COLUMN = "FILE_NAME";
    
    public static final String attachName1_COLUMN = "ATTACH_NAME1";
    
    public static final String attachName2_COLUMN = "ATTACH_NAME2";
    
    public static final String attachName3_COLUMN = "ATTACH_NAME3";
    
    public static final String attachFl1_COLUMN = "ATTACH_FL1";
    
    public static final String attachFl2_COLUMN = "ATTACH_FL2";
    
    public static final String attachFl3_COLUMN = "ATTACH_FL3";
    
    public static final String sakujoFlg_COLUMN = "SAKUJO_FLG";
    
    public static final String firstUser_COLUMN = "FIRST_USER";
    
    public static final String firstPgm_COLUMN = "FIRST_PGM";
    
    public static final String firstTmsp_COLUMN = "FIRST_TMSP";
    
    public static final String lastUser_COLUMN = "LAST_USER";
    
    public static final String lastPgm_COLUMN = "LAST_PGM";
    
    public static final String lastTmsp_COLUMN = "LAST_TMSP";
//2006/03/16 xkinu 排他制御対応
    public static final String TIMESTAMP_PROPERTY = "lastTmsp";
//2006/03/16 xkinu 排他制御対応
    
    /**
     * 登録日
     */
    private String regDate;
    /**
     * シーケンス番号
     */
    private String seq;
    /**
     * カテゴリID
     */
    private String cateId;
    /**
     * カテゴリ名
     */
    private String cateName;
    /**
     * タイトル
     */
    private String title;
    /**
     * サブタイトル
     */
    private String subTitle;
    /**
     * 公開開始日
     */
    private String pubDate;
    /**
     * 発行者
     */
    private String pubUser;
    /**
     * ユーザ名称
     */
    private String userNamekj;
    /**
     * 企業コード
     */
    private String rCompanyCd;
    /**
     * 発信元
     */
    private String pubOrg;
    /**
     * 発信元名
     */
    private String pubOrgName;
    /**
     * 通達管理NO
     */
    private String kanriNo;
    /**
     * ファイル名
     */
    private String fileName;
    
    /**
     * 添付タイトル１
     */
    private String attachName1;
    
    /**
     * 添付タイトル２
     */
    private String attachName2;
    
    /**
     * 添付タイトル３
     */
    private String attachName3;
    
    /**
     * 添付ファイル名１
     */
    private String attachFl1;
    
    /**
     * 添付ファイル名２
     */
    private String attachFl2;
    
    /**
     * 添付ファイル名３
     */
    private String attachFl3;
    
    /**
     * 削除フラグ
     */
    private String sakujoFlg;
    
    /**
     * 登録ユーザー
     */
    private String firstUser;
    
    /**
     * 登録プログラム
     */
    private String firstPgm;
    
    /**
     * 登録時ﾀｲﾑｽﾀﾝﾌﾟ
     */
    private Timestamp firstTmsp;
    
    /**
     * 修正ユーザー
     */
    private String lastUser;
    
    /**
     * 修正プログラム
     */
    private String lastPgm;
    
    /**
     * 修正時ﾀｲﾑｽﾀﾝﾌﾟ
     */
    private Timestamp lastTmsp;
    
    /**
     * 添付ファイル 選択インデックス
     */ 
    private int selectedAttachFileIndex;

    /**
     * 登録日を取得します。
     * @return 登録日
     */
    public String getRegDate() {
        return (regDate == null) ? "" : regDate;
    }
    /**
     * 登録日を設定します。
     * @param regDate 登録日
     */
    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }
    
    /**
     * シーケンス番号を取得します。
     * @return シーケンス番号
     */
    public String getSeq() {
        return (seq == null) ? "" : seq;
    }
    /**
     * シーケンス番号を設定します。
     * @param seq シーケンス番号
     */
    public void setSeq(String seq) {
        this.seq = seq;
    }
    
    /**
     * カテゴリIDを取得します。
     * @return カテゴリID
     */
    public String getCateId() {
        return (cateId == null) ? "" : cateId;
    }
    /**
     * カテゴリIDを設定します。
     * @param cateId カテゴリID
     */
    public void setCateId(String cateId) {
        this.cateId = cateId;
    }
    /**
     * カテゴリ名を取得します。
     * @return カテゴリ名
     */
    public String getCateName() {
        return (cateName == null) ? "" : cateName;
    }
    /**
     * カテゴリ名を設定します。
     * @param cateName カテゴリ名
     */
    public void setCateName(String cateName) {
        this.cateName = cateName;
    }
    /**
     * タイトルを取得します。
     * @return タイトル
     */
    public String getTitle() {
        return (title == null) ? "" : title;
    }
    /**
     * タイトルを設定します。
     * @param title タイトル
     */
    public void setTitle(String title) {
        this.title = title;
    }
    
    /**
     * サブタイトルを取得します。
     * @return サブタイトル
     */
    public String getSubTitle() {
        return (subTitle == null) ? "" : subTitle;
    }
    /**
     * サブタイトルを設定します。
     * @param subTitle サブタイトル
     */
    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }
    
    /**
     * 公開開始日を取得します。
     * @return 公開開始日
     */
    public String getPubDate() {
        return (pubDate == null) ? "" : pubDate;
    }
    /**
     * 公開開始日を設定します。
     * @param pubDate 公開開始日
     */
    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }
    
    /**
     * 発行者を取得します。
     * @return 発行者
     */
    public String getPubUser() {
        return (pubUser == null) ? "" : pubUser;
    }
    /**
     * 発行者を設定します。
     * @param pubUser 発行者
     */
    public void setPubUser(String pubUser) {
        this.pubUser = pubUser;
    }
    
    /**
     * ユーザ名称を取得します。
     * @return ユーザ名称
     */
    public String getUserNamekj() {
        return (userNamekj == null) ? "" : userNamekj;
    }
    /**
     * ユーザ名称を設定します。
     * @param userNamekj ユーザ名称
     */
    public void setUserNamekj(String userNamekj) {
        this.userNamekj = userNamekj;
    }
    
    /**
     * 企業コードを取得します。
     * @return 企業コード
     */
    public String getRCompanyCd() {
        return (rCompanyCd == null) ? "" : rCompanyCd;
    }
    /**
     * 企業コードを設定します。
     * @param rCompanyCd 企業コード
     */
    public void setRCompanyCd(String rCompanyCd) {
        this.rCompanyCd = rCompanyCd;
    }
    
    /**
     * 発信元を取得します。
     * @return 発信元
     */
    public String getPubOrg() {
        return (pubOrg == null) ? "" : pubOrg;
    }
    /**
     * 発信元を設定します。
     * @param pubOrg 発信元
     */
    public void setPubOrg(String pubOrg) {
        this.pubOrg = pubOrg;
    }
    
    /**
     * 発信元名を取得します。
     * @return 発信元名
     */
    public String getPubOrgName() {
        return (pubOrgName == null) ? "" : pubOrgName;
    }
    /**
     * 発信元名を設定します。
     * @param pubOrgName 発信元名
     */
    public void setPubOrgName(String pubOrgName) {
        this.pubOrgName = pubOrgName;
    }
    
    /**
     * 通達管理NOを取得します。
     * @return 通達管理NO
     */
    public String getKanriNo() {
        return (kanriNo == null) ? "" : kanriNo;
    }
    /**
     * 通達管理NOを設定します。
     * @param kanriNo 通達管理NO
     */
    public void setKanriNo(String kanriNo) {
        this.kanriNo = kanriNo;
    }
    
    /**
     * ファイル名を取得します。
     * @return ファイル名
     */
    public String getFileName() {
        return (fileName == null) ? "" : fileName;
    }
    /**
     * ファイル名を設定します。
     * @param fileName ファイル名
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    
    /**
     * 添付タイトル１を取得します。
     * @return 添付タイトル１
     */
    public String getAttachName1() {
        return (attachName1 == null) ? "" : attachName1;
    }
    /**
     * 添付タイトル１を設定します。
     * @param attachName1 添付タイトル１
     */
    public void setAttachName1(String attachName1) {
        this.attachName1 = attachName1;
    }
    
    /**
     * 添付タイトル２を取得します。
     * @return 添付タイトル２
     */
    public String getAttachName2() {
        return (attachName2 == null) ? "" : attachName2;
    }
    /**
     * 添付タイトル２を設定します。
     * @param attachName2 添付タイトル２
     */
    public void setAttachName2(String attachName2) {
        this.attachName2 = attachName2;
    }
    
    /**
     * 添付タイトル３を取得します。
     * @return 添付タイトル３
     */
    public String getAttachName3() {
        return (attachName3 == null) ? "" : attachName3;
    }
    /**
     * 添付タイトル３を設定します。
     * @param attachName3 添付タイトル３
     */
    public void setAttachName3(String attachName3) {
        this.attachName3 = attachName3;
    }
    
    /**
     * 添付ファイル名１を取得します。
     * @return 添付ファイル名１
     */
    public String getAttachFl1() {
        return (attachFl1 == null) ? "" : attachFl1;
    }
    /**
     * 添付ファイル名１を設定します。
     * @param attachFl1 添付ファイル名１
     */
    public void setAttachFl1(String attachFl1) {
        this.attachFl1 = attachFl1;
    }
    
    /**
     * 添付ファイル名２を取得します。
     * @return 添付ファイル名２
     */
    public String getAttachFl2() {
        return (attachFl2 == null) ? "" : attachFl2;
    }
    /**
     * 添付ファイル名２を設定します。
     * @param attachFl2 添付ファイル名２
     */
    public void setAttachFl2(String attachFl2) {
        this.attachFl2 = attachFl2;
    }
    
    /**
     * 添付ファイル名３を取得します。
     * @return 添付ファイル名３
     */
    public String getAttachFl3() {
        return (attachFl3 == null) ? "" : attachFl3;
    }
    /**
     * 添付ファイル名３を設定します。
     * @param attachFl3 添付ファイル名３
     */
    public void setAttachFl3(String attachFl3) {
        this.attachFl3 = attachFl3;
    }
    
    /**
     * 削除フラグを取得します。
     * @return 削除フラグ
     */
    public String getSakujoFlg() {
        return (sakujoFlg == null) ? "" : sakujoFlg;
    }
    /**
     * 削除フラグを設定します。
     * @param sakujoFlg 削除フラグ
     */
    public void setSakujoFlg(String sakujoFlg) {
        this.sakujoFlg = sakujoFlg;
    }
    
    /**
     * 登録ユーザーを取得します。
     * @return 登録ユーザー
     */
    public String getFirstUser() {
        return (firstUser == null) ? "" : firstUser;
    }
    /**
     * 登録ユーザーを設定します。
     * @param firstUser 登録ユーザー
     */
    public void setFirstUser(String firstUser) {
        this.firstUser = firstUser;
    }
    
    /**
     * 登録プログラムを取得します。
     * @return 登録プログラム
     */
    public String getFirstPgm() {
        return (firstPgm == null) ? "" : firstPgm;
    }
    /**
     * 登録プログラムを設定します。
     * @param firstPgm 登録プログラム
     */
    public void setFirstPgm(String firstPgm) {
        this.firstPgm = firstPgm;
    }
    
    /**
     * 登録時ﾀｲﾑｽﾀﾝﾌﾟを取得します。
     * @return 登録時ﾀｲﾑｽﾀﾝﾌﾟ
     */
    public Timestamp getFirstTmsp() {
        return firstTmsp;
    }
    /**
     * 登録時ﾀｲﾑｽﾀﾝﾌﾟを設定します。
     * @param firstTmsp 登録時ﾀｲﾑｽﾀﾝﾌﾟ
     */
    public void setFirstTmsp(Timestamp firstTmsp) {
        this.firstTmsp = firstTmsp;
    }
    
    /**
     * 修正ユーザーを取得します。
     * @return 修正ユーザー
     */
    public String getLastUser() {
        return (lastUser == null) ? "" : lastUser;
    }
    /**
     * 修正ユーザーを設定します。
     * @param lastUser 修正ユーザー
     */
    public void setLastUser(String lastUser) {
        this.lastUser = lastUser;
    }
    
    /**
     * 修正プログラムを取得します。
     * @return 修正プログラム
     */
    public String getLastPgm() {
        return (lastPgm == null) ? "" : lastPgm;
    }
    /**
     * 修正プログラムを設定します。
     * @param lastPgm 修正プログラム
     */
    public void setLastPgm(String lastPgm) {
        this.lastPgm = lastPgm;
    }
    
    /**
     * 修正時ﾀｲﾑｽﾀﾝﾌﾟを取得します。
     * @return 修正時ﾀｲﾑｽﾀﾝﾌﾟ
     */
    public Timestamp getLastTmsp() {
        return lastTmsp;
    }
    /**
     * 修正時ﾀｲﾑｽﾀﾝﾌﾟを設定します。
     * @param lastTmsp 修正時ﾀｲﾑｽﾀﾝﾌﾟ
     */
    public void setLastTmsp(Timestamp lastTmsp) {
        this.lastTmsp = lastTmsp;
    }
    /**
     * 添付ファイル有効個数
     * @return
     */
    public int getListAttachFileSize() {
        int size = 0;
        
        if (!"".equals(getAttachName1()) && !"".equals(getAttachName1())) {
            size++;
        }
        if (!"".equals(getAttachName2()) && !"".equals(getAttachName2())) {
            size++;
        }
        if (!"".equals(getAttachName3()) && !"".equals(getAttachName3())) {
            size++;
        }
        return size;
    }
    
    public List getListAttachFile() {
        List list = new ArrayList();
        
        if (!"".equals(getAttachName1()) && !"".equals(getAttachName1())) {
            SelectItem item  =new SelectItem("1", getAttachName1());
            list.add(item);
        }
        if (!"".equals(getAttachName2()) && !"".equals(getAttachName2())) {
            SelectItem item  =new SelectItem("2", getAttachName2());
            list.add(item);
        }
        if (!"".equals(getAttachName3()) && !"".equals(getAttachName3())) {
            SelectItem item  =new SelectItem("3", getAttachName3());
            list.add(item);
        }
        return list;
    }
    
    /**
     * 添付ファイル 選択インデックスを設定します。
     * @param 添付ファイル 選択インデックス
     */
    public void setSelectedAttachFileIndex(int selectedAttachFileIndex) {
        this.selectedAttachFileIndex = selectedAttachFileIndex;
    }
    
    /**
     * 添付ファイル 選択インデックスを取得します。
     * @return 添付ファイル 選択インデックス
     */
    public int getSelectedAttachFileIndex() {
        return selectedAttachFileIndex;
    }
    /**
     * このエンティティーのクローンを生成します。
     * 
     */
    public UITutatuInfo cloneEntity() {
    	UITutatuInfo entity = new UITutatuInfo();
    	entity.setRegDate(getRegDate());
    	entity.setSeq(getSeq());
    	entity.setCateId(getCateId());
    	entity.setCateName(getCateName());
    	entity.setTitle(getTitle());
    	entity.setSubTitle(getSubTitle());
    	entity.setPubDate(getPubDate());
    	entity.setPubOrg(getPubOrg());
    	entity.setPubOrgName(getPubOrgName());
    	entity.setPubUser(getPubUser());
    	entity.setUserNamekj(getUserNamekj());
    	entity.setKanriNo(getKanriNo());
    	entity.setFileName(getFileName());
    	entity.setAttachFl1(getAttachFl1());
    	entity.setAttachFl2(getAttachFl2());
    	entity.setAttachFl3(getAttachFl3());
    	entity.setAttachName1(getAttachName1());
    	entity.setAttachName2(getAttachName2());
    	entity.setAttachName3(getAttachName3());
    	entity.setSakujoFlg(getSakujoFlg());
    	entity.setFirstUser(getFirstUser());
    	entity.setFirstPgm(getFirstPgm());
    	entity.setFirstTmsp(getFirstTmsp());
    	entity.setLastUser(getLastUser());
    	entity.setLastPgm(getLastPgm());
    	entity.setLastTmsp(getLastTmsp());
    	
    	return entity;
    }
}
